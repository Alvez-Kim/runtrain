package pac.testcase.http;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HostParams;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
    static void crawlByXPath() throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse("http://stackoverflow.com/questions/12585253/how-to-remove-unused-imports-in-intellij-idea-on-commit");

        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();

        //<div class="post-text" itemprop="text"><p>When you commit, tick the <code>Optimize imports</code> option on the right.  This will become the default until you change it.</p><p>I prefer using the <code>Reformat code</code> option as well.</p></div>
        XPathExpression expression = xPath.compile("//div[class=post-text]/text()");

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
    public void crawlByHttpClientOfCommons(){
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

}