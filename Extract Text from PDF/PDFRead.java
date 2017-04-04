package DAO;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFRead {

	public static void main(String[] args) throws InvalidPasswordException, IOException{
		File file = new File("D:\\copart.pdf");
	      PDDocument document = PDDocument.load(file);

	      //Instantiate PDFTextStripper class
	      PDFTextStripper pdfStripper = new PDFTextStripper();

	      //Retrieving text from PDF document
	      String text = pdfStripper.getText(document);
	   System.out.println(text);

	      //Closing the document
	      document.close();

	     /* List<String> allMatches = new ArrayList<String>();
	      Matcher m = Pattern.compile("(\\d{5,5})")
	          .matcher(text);
	      while (m.find()) {
	        allMatches.add(m.group());*/
	      }
}
