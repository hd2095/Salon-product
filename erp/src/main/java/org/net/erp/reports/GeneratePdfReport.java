package org.net.erp.reports;
import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;

import org.net.erp.model.InvoiceDetails;
import org.net.erp.model.SaleDetails;
import org.net.erp.util.Constants;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
public class GeneratePdfReport {

	public static ByteArrayInputStream invoicePdf(HashMap<String,String> pdfContents,List<SaleDetails> allSaleDetails) {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			Paragraph para1 = new Paragraph(pdfContents.get("orgName"));
			Paragraph para2 = new Paragraph(pdfContents.get("orgAddr"));
			LineSeparator ls = new LineSeparator();	
			PdfPTable table = new PdfPTable(3);
			table.setWidthPercentage(100);
			table.setWidths(new int[]{3, 3, 3});
			PdfPCell hcell = new PdfPCell(new Phrase("Invoice Date"));
			hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			hcell.setBorder(Rectangle.NO_BORDER);
			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Invoice No"));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBorder(Rectangle.NO_BORDER);
			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Invoice To"));
			hcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			hcell.setBorder(Rectangle.NO_BORDER);
			hcell.setPaddingRight(10);
			table.addCell(hcell);
			PdfPCell cell;
			cell = new PdfPCell(new Phrase(pdfContents.get("invoiceDate")));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(pdfContents.get("invoiceNo")));
			cell.setPaddingLeft(5);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			String addressArr[] = pdfContents.get("invoiceToAddr").split(",");
			String address = "";
			for(String temp : addressArr) {
				address += temp + "\r\n";
			}
			cell = new PdfPCell(new Phrase(pdfContents.get("invoiceTo")
					+"\r\n"+pdfContents.get("invoiceToNum")
					+"\r\n"+pdfContents.get("invoiceToEmail")
					+"\r\n"+address
					));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setPaddingRight(5);
			table.addCell(cell);
			PdfPTable salesTable = new PdfPTable(5);
			salesTable.setWidthPercentage(100);
			salesTable.setWidths(new int[]{1, 3, 1,2,2});
			hcell = new PdfPCell(new Phrase("#"));
			hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			salesTable.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Item & Description"));
			hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			salesTable.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Qty"));
			hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			salesTable.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Rate"));
			hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			salesTable.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Amount"));
			hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			salesTable.addCell(hcell);
			for(int i = 0;i<allSaleDetails.size();i++) {				
				cell = new PdfPCell(new Phrase(String.valueOf(i + 1)));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				salesTable.addCell(cell);	
				cell = new PdfPCell(new Phrase(allSaleDetails.get(i).getProduct().getProductName()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				salesTable.addCell(cell);					
				cell = new PdfPCell(new Phrase(String.valueOf(allSaleDetails.get(i).getQuantity())));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				salesTable.addCell(cell);
				cell = new PdfPCell(new Phrase(Constants.RUPPEE_SYMBOL+String.valueOf(allSaleDetails.get(i).getSellingPrice())));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				salesTable.addCell(cell);				
				cell = new PdfPCell(new Phrase(Constants.RUPPEE_SYMBOL+String.valueOf(allSaleDetails.get(i).getSellingPrice() * allSaleDetails.get(i).getQuantity())));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				salesTable.addCell(cell);
			}
			cell = new PdfPCell(new Phrase("Terms & Conditions"));
			cell.setColspan(3);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			salesTable.addCell(cell);
			cell = new PdfPCell(new Phrase("Sub Total"));
			cell.setColspan(1);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			salesTable.addCell(cell);
			cell = new PdfPCell(new Phrase(pdfContents.get("saleTotal")));
			cell.setColspan(1);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			salesTable.addCell(cell);			
			cell = new PdfPCell(new Phrase("1) Subject to our home jurisdiction. \r\n2) Our Responsibility Ceases as soon as goods leaves our premises.\r\n3) Goods once sold will not be taken back.\r\n4) Delivery Ex-Premises."));
			cell.setColspan(3);			
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			salesTable.addCell(cell);
			cell = new PdfPCell(new Phrase("CGST (%) \r\nSGST (%)\r\nTotal Tax\r\nDiscount\r\nTotal After Tax"));
			cell.setColspan(1);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			salesTable.addCell(cell);
			cell = new PdfPCell(new Phrase(pdfContents.get("cgstAmt")+"("+pdfContents.get("cgstPercent")+")"
			+"\r\n"+pdfContents.get("sgstAmt")+"("+pdfContents.get("sgstPercent")+")"
			+"\r\n"+pdfContents.get("totalTax")
			+"\r\n"+pdfContents.get("discountAmt")
			+"\r\n"+pdfContents.get("totalAfterTax")
					));
			cell.setColspan(1);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			salesTable.addCell(cell);			
			PdfWriter.getInstance(document, out);
			document.open();	
			/* document.addTitle(pdfContents.get("title"));
			document.add(para1);
			document.add(para2);
			document.add(new Chunk(ls));
			document.add(table); 
			document.add(new Chunk(ls));
			document.add(salesTable); */
			document.add(para1);
			document.close();
		} catch (DocumentException ex) {

		}

		return new ByteArrayInputStream(out.toByteArray());
	}
}
