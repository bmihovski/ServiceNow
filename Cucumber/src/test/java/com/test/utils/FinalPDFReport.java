package com.test.utils;

 
import java.io.FileOutputStream;
import java.util.HashMap;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class FinalPDFReport {
	
	public void createResultPDF(HashMap<String,String> testcaseDetails,HashMap<String,String> tSteps,HashMap<String,String> tStepsImages ){
		
		Font blueFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, new CMYKColor(255, 0, 0, 0));
		Font redFont = FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD, new CMYKColor(0, 255, 0, 0));
		Font yellowFont = FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new CMYKColor(0, 0, 255, 0));
	    Document document = new Document();
	    try
	    {
	        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:\\PDF_Test\\FinalResultPDFExample.pdf"));
	        document.open();
	 
	        PdfPTable table = new PdfPTable(1); // 3 columns.
	        table.setWidthPercentage(100); //Width 100%
	        table.setSpacingBefore(10f); //Space before table
	        table.setSpacingAfter(10f); //Space after table
	 
	        //Set Column widths
	        float[] columnWidths = {1f };
	        table.setWidths(columnWidths);
	        
	        PdfPCell testSuites = new PdfPCell(new Paragraph("TC_Suites :                                 "+testcaseDetails.get("tSdesc")   )); 
	        testSuites.setBorderColor(BaseColor.BLUE);
	        testSuites.setPaddingLeft(10);
	        testSuites.setHorizontalAlignment(Element.ALIGN_LEFT);
	        testSuites.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 

	        PdfPCell testCases = new PdfPCell(new Paragraph("TC_Name :                                   "+testcaseDetails.get("tCdesc") )); 
	        testCases.setBorderColor(BaseColor.BLUE);
	        testCases.setPaddingLeft(10);
	        testCases.setHorizontalAlignment(Element.ALIGN_LEFT);
	        testCases.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        PdfPCell testSteps = new PdfPCell(new Paragraph("TC_Name :                                   "+testcaseDetails.get("tStepdesc") )); 
	        testSteps.setBorderColor(BaseColor.BLUE);
	        testSteps.setPaddingLeft(10);
	        testSteps.setHorizontalAlignment(Element.ALIGN_LEFT);
	        testSteps.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        
	        PdfPCell testResult = new PdfPCell(new Paragraph("TC_Result :                                   "+testcaseDetails.get("TestCaseResult") )); 
	        testSteps.setBorderColor(BaseColor.BLUE);
	        testSteps.setPaddingLeft(10);
	        testSteps.setHorizontalAlignment(Element.ALIGN_LEFT);
	        testSteps.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        
	        table.addCell(testSuites);
	        table.addCell(testCases);
	        table.addCell(testSteps);
	        table.addCell(testResult);
	        document.add(table);
	        /*
	        Image img = Image.getInstance(IMAGES[0]);
	        Document document = new Document(img);
	        PdfWriter.getInstance(document, new FileOutputStream(dest));
	        document.open();
	        for (String image : IMAGES) {
	            img = Image.getInstance(image);
	            document.setPageSize(img);
	            document.newPage();
	           // img.setAbsolutePosition(50, 50);
	            img.scaleAbsolute(1300,800);
	            document.add(img);
	        }
	       */
	        document.close();
	        writer.close();
	    } catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	}
	
	

	public static void main(String[] args) {
	 
		HashMap<String,String> testcaseDetails= new HashMap<String,String>();
/*		testcaseDetails.put("tS", "TestSuite:");
		testcaseDetails.put("tc", "TestCases:");
		testcaseDetails.put("tStep", "TestSteps:");*/
		
		testcaseDetails.put("tSdesc", "LoginTestSuite");
		testcaseDetails.put("tCdesc", "TC_01_LoginTestCase");
		testcaseDetails.put("tStepdesc", "TestSteps:");
		testcaseDetails.put("TestCaseResult", "Passed");
		
		HashMap<String,String> tSteps=new HashMap<String,String>();
		tSteps.put("Step1 :", "Click on the URL");
		tSteps.put("Step2 :", "Enter the UserName");
		tSteps.put("Step3 :", "Enter the Password");
		tSteps.put("Step4 :", "Click on the Submit Button");
		
		HashMap<String,String> tStepsImages=new HashMap<String,String>();
		
		tStepsImages.put("Step1Image", "D:\\API\\Cloud Environment not accessible.png");
		tStepsImages.put("Step2Image", "D:\\API\\Cloud Environment not accessible.png");
		tStepsImages.put("Step3Image", "D:\\API\\Cloud Environment not accessible.png");
		tStepsImages.put("Step4Image", "D:\\API\\Cloud Environment not accessible.png");
		
		FinalPDFReport f=new FinalPDFReport();
		f.createResultPDF( testcaseDetails, tSteps,  tStepsImages );
		
	}

}
