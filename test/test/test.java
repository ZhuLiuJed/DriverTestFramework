package test;

import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import org.testng.annotations.Test;



public class test  {
	@Test  
	public void test() {  
	    File imageFile = new File("D:/validcode.jpg");  
	       Tesseract tessreact = new Tesseract();  
	       tessreact.setDatapath("C:/tessdata");  
	       try {  
	           String result = tessreact.doOCR(imageFile);  
	           System.out.println(result);  
	       } catch (TesseractException e) {  
	           System.err.println(e.getMessage());  
	       }  
	   }  
}
