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

        String baseUrl = "http://zhidao.baidu.com/daily";
        String html = Jsoup.connect("http://zhidao.baidu.com/daily").post().body().html();
        long starttime = System.currentTimeMillis();
//        html = Jsoup.clean(html,"http://www.gamersky.com/Special/hot/",Whitelist.basic().addAttributes("td","class"));

        Document document = Jsoup.parse(html);
        Elements elements = document.select("h2 a");

        Element element = elements.get(0);
        String txtUrl = StringUtils.startsWithIgnoreCase(element.attr("href"), baseUrl) ? StringUtils.EMPTY : baseUrl.concat(element.attr("href"));
        System.out.println(txtUrl);
        System.out.println(element.text());
        System.out.println(Jsoup.parse(Jsoup.connect(txtUrl).post().body().html()).select(".d-detail-txt p:not([class])").html());

        System.out.println(System.currentTimeMillis() - starttime);


    }
}
