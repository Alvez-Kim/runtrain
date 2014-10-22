package pac.testcase.http;

import org.apache.commons.lang3.StringUtils;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.StringTokenizer;


public class DynamicCrawler {

    public static void printYoumin() {
        String url = "http://www.gamersky.com/Special/hot/";
        Connection connection = Jsoup.connect(url).method(Connection.Method.POST);
        Connection.Response response = null;
        try {
            response = connection.execute();
            Elements elements = response.parse().getElementsByAttributeValueContaining("class", "dlist");
            for (Element element1 : elements) {
                Element element = element1.nextElementSibling().child(0);
                System.out.println(element.text().concat(":::").concat(element.attr("href")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printZhidao() {
        String url = "http://zhidao.baidu.com/daily";
        Connection connection = Jsoup.connect(url).method(Connection.Method.POST);
        Connection.Response response = null;

        try {
            response = connection.execute();

            Document doc = response.parse();
            Elements elements = doc.getElementsByTag("h2");

            for (Element element1 : elements) {
                Element element = element1.child(0);
                System.out.println(element.text().concat(":::").concat(element.attr("href")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void tt(String[] args)
            throws ParserConfigurationException, SAXException,
            IOException, XPathExpressionException {

        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true); // never forget this!
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        org.w3c.dom.Document doc = builder.parse("E:/test.html");

        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        XPathExpression expr
                = xpath.compile("//book[author='Neal Stephenson']/title/text()");

        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getNodeValue());
        }

    }

    public static void main(String[] args) throws IOException,
            XPatherException, ParserConfigurationException, SAXException {

        /*String url = "http://zhidao.baidu.com/daily";
//        url = "http://www.gamersky.com/Special/hot/";
        String contents = Jsoup.connect(url).post().body().html();
        long start = System.currentTimeMillis();
        HtmlCleaner hc = new HtmlCleaner();
        TagNode tn = hc.clean(contents);
        String xpath = "//h2/a";
        Object[] objects = tn.evaluateXPath(xpath);
        TagNode node = (TagNode)objects[0];
        System.out.println(node.getText()+":::"+node.getAttributeByName("href"));
        System.out.println(objects.length);
        System.out.println(System.currentTimeMillis()-start);*/


//        String html = Jsoup.connect("http://zhidao.baidu.com/daily").post().body().html();
//        long starttime = System.currentTimeMillis();
////        html = Jsoup.clean(html,"http://www.gamersky.com/Special/hot/",Whitelist.basic());
//
////        System.out.println(html);
//        Document document = Jsoup.parseBodyFragment(html,"http://zhidao.baidu.com/daily");
//        Elements elements = document.select("h2 a");
//        System.out.println(elements.size());
//        System.out.println(elements.get(0).attr("href"));
//        System.out.println(elements.get(0).text());
//        System.out.println(System.currentTimeMillis()-starttime);

        printByJsoup("http://zhidao.baidu.com/daily","h2 a");
        printByJsoup("http://www.gamersky.com/Special/hot/",".dlist~td a");
        printByJsoup("http://www.appinn.com/","h2.entry-title a");

    }

    public static void printByJsoup(String url,String exp){
        String html;
        try {
            html = Jsoup.connect(url).post().body().html();
            long starttime = System.currentTimeMillis();

            Document document = Jsoup.parse(html);
            Elements elements = document.select(exp);
            for (Element element : elements) {
                String href = element.attr("href");
                String txtUrl = StringUtils.startsWithIgnoreCase(href, "http://") ? href : url.concat(href);
                System.out.println(txtUrl+":::"+element.text());
            }

            System.out.println("costs::"+(System.currentTimeMillis() - starttime));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
