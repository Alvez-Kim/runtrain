package pac.testcase.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class TestItextPdf {
	public static void main(String[] args) throws DocumentException,
			IOException {
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream("E:/test.pdf"));
		document.open();
		
		XMLWorkerHelper.getInstance().parseXHtml(writer, document,
				new FileInputStream("E:/test.txt"));
//		XMLWorkerHelper.getInstance().parseXHtml(writer, document,
//				new StringReader(""));
		document.close();

		System.out.println("PDF Created!");
	}
}