package org.net.erp.reports;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.swing.text.StyleConstants;

import org.net.erp.model.SaleDetails;
import org.net.erp.util.Constants;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceCmyk;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDictionary;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.ColumnDocumentRenderer;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.BorderCollapsePropertyValue;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

/* import java.awt.Color;
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

	private static Font headerFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
			Font.BOLD,BaseColor.DARK_GRAY);

	public static ByteArrayInputStream invoicePdf(HashMap<String,String> pdfContents,List<SaleDetails> allSaleDetails) {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			Paragraph para1 = new Paragraph(pdfContents.get("orgName"),headerFont);
			String orgAddress = Constants.EMPTY;
			for(int i = 0;i<pdfContents.get("orgAddr").split(Constants.COMMA).length;i++) {
				orgAddress += pdfContents.get("orgAddr").split(Constants.COMMA)[i] + "\r\n";
			}
			Paragraph para2 = new Paragraph(orgAddress,new Font(Font.FontFamily.TIMES_ROMAN, 12,0,BaseColor.GRAY));
			LineSeparator ls = new LineSeparator();	
			ls.setLineColor(BaseColor.DARK_GRAY);
			Paragraph para3 = new Paragraph();
			Chunk chunk1 = new Chunk("Invoice Date");
			Chunk chunk2 = new Chunk("Invoice No");
			Chunk chunk3 = new Chunk("Invoice To");			
			para3.add(chunk1);
			para3.add(chunk2);
			para3.add(chunk3);
			para3.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 12,0,BaseColor.BLACK));
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
			document.addTitle(pdfContents.get("title"));			
			document.addTitle(pdfContents.get("title"));
			document.add(para1);
			document.add(para2);
			document.add(new Chunk(ls));
			document.add(table); 
			document.add(new Chunk(ls));
			document.add(salesTable); 
			document.add(para1);
			document.add(para2);
			document.add(new Chunk(ls));
			document.add(para3);
			document.close();
		} catch (DocumentException ex) {

		}

		return new ByteArrayInputStream(out.toByteArray());
	}
} */
public class GeneratePdfReport {

	public static ByteArrayInputStream invoicePdf(HashMap<String,String> pdfContents,List<SaleDetails> allSaleDetails) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PdfWriter writer = new PdfWriter(out); 
		PdfDocument pdfDoc = new PdfDocument(writer); 
		Document document = new Document(pdfDoc); 
		PdfPage page = pdfDoc.addNewPage();
		String para = pdfContents.get("orgName");
		String orgAddress = Constants.EMPTY;
		for(int i = 0;i<pdfContents.get("orgAddr").split(Constants.COMMA).length;i++) {
			orgAddress += pdfContents.get("orgAddr").split(Constants.COMMA)[i] + "\r\n";
		}
		PdfFont code = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
		Style style = new Style()
				.setFont(code)
				.setFontSize(24)
				.setFontColor(ColorConstants.GRAY);
		Paragraph para1 = new Paragraph(para);
		Paragraph para2 = new Paragraph(orgAddress);
		document.add(para1.addStyle(style));
		document.add(para2);
		Color grayColor = new DeviceCmyk(0.f, 0.f, 0.f, 0.875f);
		PdfCanvas canvas = new PdfCanvas(page);
		canvas.moveTo(30, 650); 
		canvas.lineTo(550, 650);	
		canvas.setLineWidth(0.5f)
		.setStrokeColor(grayColor);
		canvas.stroke();
		para1 = new Paragraph("\n");
		document.add(para1);
		Table table = new Table(UnitValue.createPercentArray(5)).useAllAvailableWidth();
		table.setBorderCollapse(BorderCollapsePropertyValue.SEPARATE);
	    table.setVerticalBorderSpacing(10);
	    table.setHorizontalBorderSpacing(10);
		Cell sn = new Cell(2, 1).add(new Paragraph("Invoice Date \n"+pdfContents.get("invoiceDate")));
		sn.setBackgroundColor(ColorConstants.YELLOW);
		table.addCell(sn);
		Cell name = new Cell(1, 3).add(new Paragraph("Name"));
		name.setBackgroundColor(ColorConstants.CYAN);
		table.addCell(name);
		Cell age = new Cell(2, 1).add(new Paragraph("Age"));
		age.setBackgroundColor(ColorConstants.GRAY);
		table.addCell(age);
		Cell surname = new Cell().add(new Paragraph("SURNAME"));
		surname.setBackgroundColor(ColorConstants.BLUE);
		table.addCell(surname);
		Cell firstname = new Cell().add(new Paragraph("FIRST NAME"));
		firstname.setBackgroundColor(ColorConstants.RED);
		table.addCell(firstname);
		Cell middlename = new Cell().add(new Paragraph("MIDDLE NAME"));
		middlename.setBackgroundColor(ColorConstants.GREEN);
		table.addCell(middlename);
		Cell f1 = new Cell().add(new Paragraph("1"));
		f1.setBackgroundColor(ColorConstants.PINK);
		table.addCell(f1);
		Cell f2 = new Cell().add(new Paragraph("James"));
		f2.setBackgroundColor(ColorConstants.MAGENTA);
		table.addCell(f2);
		Cell f3 = new Cell().add(new Paragraph("Fish"));
		f3.setBackgroundColor(ColorConstants.ORANGE);
		table.addCell(f3);
		Cell f4 = new Cell().add(new Paragraph("Stone"));
		f4.setBackgroundColor(ColorConstants.DARK_GRAY);
		table.addCell(f4);
		Cell f5 = new Cell().add(new Paragraph("17"));
		f5.setBackgroundColor(ColorConstants.LIGHT_GRAY);
		table.addCell(f5);
		document.add(table);
		//PdfFont co
		document.close();
		return new ByteArrayInputStream(out.toByteArray()); 
		/* ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {			
			PdfWriter writer = new PdfWriter(out); 
			HtmlConverter.convertToPdf(html, writer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ByteArrayInputStream(out.toByteArray()); */
	}
}