package pac.testcase.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HtmlCrawler {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, XPathExpressionException, IOException {
        Iterator<String> iterator = crawlByRegex().iterator();
        int i=0;
        while(iterator.hasNext())
            System.out.println((String.valueOf(++i)).concat(". ").concat(iterator.next()));

        crawlByXPath(); // causes org.xml.sax.SAXParseException
    }

    static List<String> crawlByRegex() throws IOException {
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

        return titles;
    }

    static void crawlByXPath() throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse("http://stackoverflow.com/questions/12585253/how-to-remove-unused-imports-in-intellij-idea-on-commit");

        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();

        //<div class="post-text" itemprop="text"><p>When you commit, tick the <code>Optimize imports</code> option on the right.  This will become the default until you change it.</p><p>I prefer using the <code>Reformat code</code> option as well.</p></div>
        XPathExpression expression = xPath.compile("//div[class=post-text]/text()");

    }

}