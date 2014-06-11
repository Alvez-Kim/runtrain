package pac.testcase.lucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.SortField.Type;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class LucBase {
	public static void main(String[] args) throws IOException, ParseException {
		
		File path = new File("E:/lucene_test/");
		for (File tmp : path.listFiles())tmp.delete();
		
		Directory dir = FSDirectory.open(path);
		
//		Analyzer ana = new StandardAnalyzer(Version.LUCENE_47);
		Analyzer ana  =new SmartChineseAnalyzer(Version.LUCENE_47);
		IndexWriterConfig writerConfig = new IndexWriterConfig(Version.LUCENE_47, ana);
		
		IndexWriter writer = new IndexWriter(dir, writerConfig);
		
		Document doc = new Document();
		//string field 不支持分词 跟 analyzer无关
		doc.add(new TextField("name", "King Jr", Store.YES));
		doc.add(new TextField("age", "20", Store.YES));
		
		writer.addDocument(doc);
		
		doc = new Document();
		doc.add(new TextField("name", "Jin Jr", Store.YES));
		doc.add(new TextField("age", "21", Store.YES));
		writer.addDocument(doc);
		
		doc = new Document();
		doc.add(new TextField("name", "Kim Jr", Store.YES));
		doc.add(new TextField("age", "19", Store.YES));
		writer.addDocument(doc);
		
		writer.commit();
		writer.close();
		
		IndexReader reader = DirectoryReader.open(dir);
		IndexSearcher searcher = new IndexSearcher(reader);
		
		Query q = new TermQuery(new Term("name","Jr"));
		
		QueryParser parser = new QueryParser(Version.LUCENE_47, "name", ana);
		q = parser.parse("Jr");
		
		Sort sort = new Sort(new SortField("age", Type.INT));
		//new Sort(SortField .. field);
		
		TopDocs td = searcher.search(q, 1000,sort);//find top 1000
//		new MatchAllDocsQuery();
		//td.scoreDocs  td.totalHits
		for (int i = 0; i < td.scoreDocs.length; i++) {
			//td.scoreDocs[i].doc hit doc number
			System.out.println("hit;:"+searcher.doc(td.scoreDocs[i].doc).get("name"));
		}
		
		reader.close();
		dir.close();
	}
	
	static void termSearch(Directory dir,String field,String value,Sort sort){
		try {
			IndexReader 	reader = DirectoryReader.open(dir);
			IndexSearcher searcher = new IndexSearcher(reader);
			
			Query query = new TermQuery(new Term(field,value));
			
			TopDocs td = searcher.search(query, 1000,sort);//find top 1000
			//td.scoreDocs  td.totalHits
			for (int i = 0; i < td.scoreDocs.length; i++) {
				System.out.println("hit;:"+searcher.doc(td.scoreDocs[i].doc).get("name"));//td.scoreDocs[i].doc hit doc number
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static void termSearch(Directory dir,String field,String value){
		;termSearch(dir, field, value, null);
	}
	
	
	static void simpleSearch(Directory dir,String field,String value){
		IndexReader reader;
		try {
			reader = DirectoryReader.open(dir);
			IndexSearcher searcher = new IndexSearcher(reader);
			
			QueryParser parser = new QueryParser(Version.LUCENE_47, field, new StandardAnalyzer(Version.LUCENE_47));
			Query query = parser.parse(value);
			
			TopDocs td = searcher.search(query, 100);
			for (int i = 0; i < td.scoreDocs.length; i++) {
				System.out.println("hit;:"+searcher.doc(td.scoreDocs[i].doc).get("name"));//td.scoreDocs[i].doc hit doc number
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
