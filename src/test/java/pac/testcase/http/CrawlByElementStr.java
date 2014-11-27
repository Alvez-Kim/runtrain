package pac.testcase.http;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Alvez on 14-10-27.
 */
public class CrawlByElementStr {



    public String previewContent(String url,String articleFrom, List<String> ignoreStr) {
        if(StringUtils.isBlank(url))return StringUtils.EMPTY;

        Document document;
        try {
            document = getWithHeader(url);
        } catch (IOException e) {
            e.printStackTrace();
            return StringUtils.EMPTY ;
        }

        Elements elements = document.select(getCssQueryStr(articleFrom));

        Element articleFromElement = null;
        if(elements!=null && elements.size()>0) {
            articleFromElement = elements.get(0);
        }
        if(articleFromElement==null)return StringUtils.EMPTY;
        Elements ignores = new Elements();
        ignores.addAll(document.select("script"));
        if(ignoreStr !=null)
            for (String ignore : ignoreStr) {
                String queryStr = getCssQueryStr(ignore);
                if(StringUtils.isNotBlank(queryStr))
                    ignores.addAll(document.select(queryStr));
            }

        StringBuilder articleHtml = new StringBuilder();
        if(ignores.size()==0){
            for (Element element1 : articleFromElement.children())
                articleHtml.append(element1);
        }else{
            for (Element ignore : ignores) {
                if(ignore.parentNode()!=null)
                    ignore.remove();
            }
            for (Element child : articleFromElement.children()) {
                //remove a tags
                for (Element a : child.select("a")) {
                    child.append(a.html());
                    a.remove();
                }
                articleHtml.append(child);
            }
        }

        return String.valueOf(articleHtml);
    }

    public void previewList(String url, List<String> ignoreLinkElementStrs) {

        if(StringUtils.isBlank(url))return ;

        url = "http://".concat(url);
        String baseUrl = url.substring(0,url.indexOf("/",8));
        Document document;
        try {
            document = getWithHeader(url);
        } catch (IOException e) {
            e.printStackTrace();
            return ;
        }

        Elements relativeLinks = document.select("a[href^=/]");
        for (Element relativeLink : relativeLinks) {
            relativeLink.attr("href",baseUrl.concat(relativeLink.attr("href")));
        }
        Elements regularLinks = document.select("a[href^='" + baseUrl + "']");
        regularLinks.addAll(relativeLinks);

        Iterator<Element> regularLinkItr = regularLinks.iterator();
        if(ignoreLinkElementStrs !=null)
            while(regularLinkItr.hasNext()){
                String regularLink = regularLinkItr.next().toString();
                for (String ignoreLink : ignoreLinkElementStrs) {
                    if(regularLink.contains(ignoreLink)){
                        regularLinkItr.remove();break;
                    }
                }
            }

        regularLinkItr = regularLinks.iterator();
        while(regularLinkItr.hasNext()){
            Element link =regularLinkItr.next();
        }

    }

    private String getTagNameStr(String nodeStr){
        if(StringUtils.isBlank(nodeStr) || !StringUtils.containsAny(nodeStr,'<'))return StringUtils.EMPTY;
        Matcher matcher = Pattern.compile("\\s*<(\\w+)").matcher(nodeStr);
        return matcher.find()?matcher.group(1): StringUtils.EMPTY;
    }

    /**
     * convert html elements str to css query string
     * @param nodeStr could be html tag string or attributes string
     * @return css query string for Jsoup select method param
     */
    private String getCssQueryStr(String nodeStr) {
        String cssQueryStr = StringUtils.EMPTY;
        if (StringUtils.indexOfAny(nodeStr, '<', '>', '=') == -1) {
            Matcher matcher = Pattern.compile("\\s*([\\.\\w-]+)").matcher(nodeStr);
            while (matcher.find())
                cssQueryStr += "[".concat(matcher.group(1)).concat("]");
        } else {
            cssQueryStr = getTagNameStr(nodeStr);
            Matcher matcher = Pattern.compile("([\\w-]+\\s*=[\"']?[/\\.\\w\\s:-]*[\"']?)\\s*").matcher(nodeStr);
            while (matcher.find()) {
                String attrStr = matcher.group(1);
                if (attrStr.contains("javascript")) continue;
                attrStr = "[".concat(StringUtils.replaceChars(attrStr, "\"'", "")).concat("]");
                cssQueryStr += attrStr;
            }
            cssQueryStr = StringUtils.replace(cssQueryStr,"=]","]");
        }
        return cssQueryStr;
    }

    /**
     * send get request with simulated http header
     * @param url general url starts with http
     * @return Jsoup Document obj
     * @throws IOException
     */
    private static Document getWithHeader(String url) throws IOException {
        Connection connection = Jsoup.connect(url);
        return connection.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .header("Accept-Encoding", "gzip,deflate,sdch")
                .header("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,ja;q=0.4,ko;q=0.2,zh-TW;q=0.2")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 5.2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.104 Safari/537.36")
                .get();
    }

    @Test
    public void test() throws IOException, ParserConfigurationException {
        String url = "http://zhidao.baidu.com/daily/view?id=3211";
        List<String> ignores = new ArrayList<>();
        String previewContent = previewContent(url, "<div class=\"d-detail-txt\">", ignores);
        System.out.println(previewContent);
    }

}
