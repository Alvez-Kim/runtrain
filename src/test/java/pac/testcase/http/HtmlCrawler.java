package pac.testcase.http;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.htmlcleaner.*;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HtmlCrawler {

    @Test
    public void crawlByRegex() throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://xianguo.com/section/EF2BBCB8E868A5E8951BBD4CF2AFE867");
        HttpResponse response = client.execute(post);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        response.getEntity().writeTo(byteArrayOutputStream);


        String result = byteArrayOutputStream.toString();

        String regex = "<a\\s*href=\"javascript:;\"\\s*class=\"art-title\">(.*)</a>";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(result);

        List<String> titles = new ArrayList<>();
        while (matcher.find()) {
            titles.add(matcher.group(1));
        }

        Iterator<String> iterator = titles.iterator();
        int i = 0;
        while (iterator.hasNext())
            System.out.println((String.valueOf(++i)).concat(". ").concat(iterator.next()));

    }

    /**
     * causes org.xml.sax.SAXParseException
     *
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws XPathExpressionException
     */
    static void crawlByXPath(String url) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {

        String html = Jsoup.connect(url).post().html();
        System.out.println(html);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(html);

        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();

        XPathExpression expression = xPath.compile("//div[class=post-text]/text()");
        expression.evaluate(html);

    }

    @Test
    public void crawlByJsoup() throws IOException {

        final String baseURL = "http://xianguo.com";

        Connection connection = Jsoup.connect(baseURL.concat("/lianbo/contents"));
        Connection.Response response = connection.execute();
        String html = response.body();
        org.jsoup.nodes.Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByAttributeValueContaining("class", "a cate-list");

        Iterator<org.jsoup.nodes.Element> iterator = elements.iterator();

        ExecutorService service = Executors.newFixedThreadPool(10);
        List<Future<Integer>> futures = new ArrayList<>();
        int index = 0;
        while (iterator.hasNext()) {
            final org.jsoup.nodes.Element element = iterator.next();
            System.out.println(String.valueOf(++index).concat(". ").concat(element.text()));

            futures.add(
                    service.submit(new Callable<Integer>() {
                        @Override
                        public Integer call() throws Exception {
                            return Jsoup.parse(Jsoup.connect(baseURL.concat(element.attr("href"))).execute().body()).getElementsByAttributeValueContaining("class", "sub-info").size();
                        }
                    }));

        }

        Iterator<Future<Integer>> futureIterator = futures.iterator();
        int sectionCount = 0;
        while (futureIterator.hasNext()) {
            try {
                sectionCount += futureIterator.next().get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println(sectionCount);
    }

    @Test
    public void crawlByHttpClientOfCommons() {
        org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();
        HostConfiguration configuration = new HostConfiguration();

        PostMethod postMethod = new PostMethod();
        postMethod.setPath("http://xianguo.com/section/EF2BBCB8E868A5E8951BBD4CF2AFE867");

        try {
            System.out.println(client.executeMethod(configuration, postMethod));
            System.out.println(new String(postMethod.getResponseBody()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, XPatherException, ParserConfigurationException, XPathExpressionException {
        String url = "http://zhidao.baidu.com/daily";
        url = "http://www.gamersky.com/Special/hot/";
        String exp = "//h2/a[contains(@href,'daily')]/@href";

        String html = null;
        try {
            Connection connect = Jsoup.connect(url);
            html = connect.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                    .header("Accept-Encoding","gzip,deflate,sdch")
                    .header("Accept-Language","zh-CN,zh;q=0.8,en;q=0.6,ja;q=0.4,ko;q=0.2,zh-TW;q=0.2")
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 5.2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.104 Safari/537.36")
                    .get().body().html();
        } catch (IOException e) {
            e.printStackTrace();
        }
       /* long start = System.currentTimeMillis();
        System.out.println(start);
        HtmlCleaner hc = new HtmlCleaner();
        TagNode tn = hc.clean(html);
        Document dom = new DomSerializer(new CleanerProperties()).createDOM(tn);
        XPath xPath = XPathFactory.newInstance().newXPath();
        Object result;
        result = xPath.evaluate(exp, dom, XPathConstants.NODESET);
        if (result instanceof NodeList) {
            NodeList nodeList = (NodeList) result;
            System.out.println(nodeList.getLength());
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                System.out.println(node.getNodeValue() == null ? node.getTextContent() : node.getNodeValue());
            }
        }*/
///daily/view?id=
        //<h2><a href="/daily/view\?id=?(\d{4})
        //<p style="text-align:center">([\s|.|\w|<|>|=|"|:|/|u4e00-u9fa5]*)
        //^s*|s*$
        //d-detail-txt
        //<div class="d-detail-txt">([\s|\w|<|>|.]+)
        //<div class="d-detail-txt">[\s,.,<,>,u4e00-u9fa5]*
        //u4e00-u9fa5
        //<table>([\s\w<>/[^u4e00-u9fa5]]*)</table>
        //"(\\s*<p>[\\s\\w<>/\"=:;?[^u4e00-u9fa5]]*?</p>)"
        //<div class="d-detail-txt">\s*<p>[\s\w<>/"=:;?[^u4e00-u9fa5]]*?</div>
        System.out.println("request succeed");
        System.out.println(html);
        String regex = "class=\"dlist\">&middot;</td>\\s*<td><a\\s*href=\"(.*?)\"";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(html);
        int count = 0;
        while(matcher.find()){
            count++;
            System.out.println("matched:::"+matcher.group(1));
        }
        System.out.println("count::"+count);
    }
}