package pac.testcase.http;

import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Map;

/**
 * Created by Alvez on 14-10-27.
 */
public class CrawlByElementStr {

    @Test
    public void main() throws IOException, ParserConfigurationException {
        String url = "http://zhidao.baidu.com/daily/view?id=2992";
        String baseUrl = url.substring(0, url.indexOf("/", 8) + 1);
        Document document = getWithHeader(url);
        Element body = document.body();
        //<ul class="d-list" id="dailyList">

        //<div class="d-detail-txt">
        String nodeStr = "<div class=\"d-detail-txt\">";
        Elements elements = document.select(getCssQueryStr(nodeStr));
        Element element = null;
        if (elements != null && elements.size() > 0)
            element = elements.get(0);
        nodeStr = "class=\"hidden-mobile\"";
        System.out.println(getCssQueryStr(nodeStr));
        StringBuilder articleHtml = new StringBuilder();
        Elements ignores = document.select(getCssQueryStr(nodeStr));
        ignores.addAll(document.select(getCssQueryStr("<ul class=\"other-qts hidden-mobile\">")));
        int ignoreIndex = 0;
        for (Element element1 : element.children()) {
            if (!element1.equals(ignores.get(ignoreIndex))) {
                articleHtml.append(element1);
            } else {
                if (ignoreIndex < ignores.size() - 1) ignoreIndex++;
            }
        }

        System.out.println(getCssQueryStr("class=\"grid\" target=\"_blank\""));
    }

    static Map<String, String> getAttrs(String nodeStr) {
        Map<String, String> attrs = new HashMap<>();
        Matcher matcher = Pattern.compile("([\\w-]+\\s*=[\"']*[\\w\\s-]+[\"']*)\\s*").matcher(nodeStr);
        while (matcher.find()) {
            String attrStr = matcher.group(1);
            attrStr = StringUtils.replaceChars(attrStr, "\"'", "");
            int eqIndex = attrStr.indexOf("=");
            attrs.put(attrStr.substring(0, eqIndex), attrStr.substring(eqIndex + 1, attrStr.length()));
        }
        return attrs;
    }

    private String getTagNameStr(String nodeStr){
        if(StringUtils.isBlank(nodeStr) || !StringUtils.containsAny(nodeStr,'<'))return StringUtils.EMPTY;
        Matcher matcher = Pattern.compile("\\s*<(\\w+)").matcher(nodeStr);
        return matcher.find()?matcher.group(1): StringUtils.EMPTY;
    }

    private String getCssQueryStr(String nodeStr){
        String cssQueryStr = StringUtils.EMPTY;
        if(StringUtils.indexOfAny(nodeStr,'<','>','=')==-1){
            Matcher matcher = Pattern.compile("\\s*([\\.\\w-]+)").matcher(nodeStr);
            while(matcher.find())
                cssQueryStr+="[".concat(matcher.group(1)).concat("]");
        }else{
            cssQueryStr = getTagNameStr(nodeStr);
            Matcher matcher = Pattern.compile("([\\w-]+\\s*=[\"']*[/\\.\\w\\s:-]*[\"']*)\\s*").matcher(nodeStr);
            while(matcher.find()){
                String attrStr = matcher.group(1);
                if(attrStr.contains("javascript"))continue;
                attrStr = "[".concat(StringUtils.replaceChars(attrStr,"\"'","")).concat("]");
                cssQueryStr+=attrStr;
            }
        }
        return cssQueryStr;
    }

    private Document getWithHeader(String url) throws IOException {
        Connection connection = Jsoup.connect(url);
        return connection.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .header("Accept-Encoding", "gzip,deflate,sdch")
                .header("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,ja;q=0.4,ko;q=0.2,zh-TW;q=0.2")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 5.2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.104 Safari/537.36")
                .get();
    }
}
