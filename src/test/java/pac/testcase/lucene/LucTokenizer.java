package pac.testcase.lucene;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.util.Version;

public class LucTokenizer {
	public static void main(String[] args) throws IOException {
		Analyzer ana = new SmartChineseAnalyzer(Version.LUCENE_47);
		
		TokenStream ts = ana.tokenStream("name", "Pablo Diego José Francisco de Paula Juan Nepomuceno María de los Remedios Cipriano de la Santísima Trinidad Ruiz Picasso");

        CharTermAttribute score = ts.addAttribute(CharTermAttribute.class);
        ts.reset();
        while(ts.incrementToken()){
            System.out.println(score.toString());
        }
    }
}
