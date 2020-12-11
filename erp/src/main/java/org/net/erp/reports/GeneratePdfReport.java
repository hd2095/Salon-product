package org.net.erp.reports;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.net.erp.model.AppointmentDetails;
import org.net.erp.model.SaleDetails;
import org.net.erp.util.Constants;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceCmyk;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.TabStop;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TabAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

@SuppressWarnings("deprecation")
public class GeneratePdfReport {

	public static ByteArrayInputStream invoicePdf(HashMap<String,String> pdfContents,List<SaleDetails> allSaleDetails) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PdfWriter writer = new PdfWriter(out); 
		PdfDocument pdfDoc = new PdfDocument(writer); 
		Document document = new Document(pdfDoc); 
		String orgAddress = Constants.EMPTY;
		for(int i = 0;i<pdfContents.get("orgAddr").split(Constants.COMMA).length;i++) {
			orgAddress += pdfContents.get("orgAddr").split(Constants.COMMA)[i] + "\r\n";
		}
		PdfFont code = PdfFontFactory.createFont(FontConstants.HELVETICA);
		Style style = new Style()
				.setFont(code)
				.setFontSize(24)
				.setFontColor(ColorConstants.GRAY);
		Paragraph para = new Paragraph(pdfContents.get("orgName"));
		document.add(para.addStyle(style));
		para = new Paragraph(orgAddress);
		style = new Style().setFontSize(10);
		document.add(para.addStyle(style));
		/*
		 * String imageFile = "C:\\Users\\Desai\\OneDrive\\Desktop\\pdf-image.png";
		 * ImageData data = ImageDataFactory.create(imageFile); Image img = new
		 * Image(data); img.setHeight(30); img.setWidth(70); img.setFixedPosition(450,
		 * 750); document.add(img);
		 */
		para = new Paragraph("\n");
		document.add(para);
		Color grayColor = new DeviceCmyk(0.f, 0.f, 0.f, 0.875f);
		SolidLine line = new SolidLine();
		line.setColor(grayColor);
		LineSeparator ls = new LineSeparator(line);
		document.add(ls);
		para = new Paragraph("\n");
		document.add(para);
		Style tableContentsStyle = new Style()
				.setFont(code)
				.setFontSize(11);
		Table table = new Table(UnitValue.createPercentArray(3)).useAllAvailableWidth();
		Cell cell = new Cell()
				.setBorder(Border.NO_BORDER);
		cell.add(new Paragraph("Invoice Date"));     
		table.addCell(cell);       
		cell = new Cell()
				.setBorder(Border.NO_BORDER);
		cell.add(new Paragraph("Invoice No"));     
		table.addCell(cell);                  
		cell = new Cell()
				.setBorder(Border.NO_BORDER);
		cell.add(new Paragraph("Invoice To"));     
		table.addCell(cell);  
		cell = new Cell()
				.setBorder(Border.NO_BORDER);
		cell.add(new Paragraph(pdfContents.get("invoiceDate")).addStyle(tableContentsStyle));
		table.addCell(cell);
		cell = new Cell()
				.setBorder(Border.NO_BORDER);
		cell.add(new Paragraph(pdfContents.get("invoiceNo")).addStyle(tableContentsStyle));
		table.addCell(cell);
		cell = new Cell()
				.setBorder(Border.NO_BORDER);		
		para = new Paragraph();
		Text grayText = new Text("Name : ")
				.setFontColor(ColorConstants.GRAY)
				.setFont(code);
		para.add(grayText);
		para.add(pdfContents.get("invoiceTo")+"\n");
		grayText = new Text("Number : ")
				.setFontColor(ColorConstants.GRAY)
				.setFont(code);
		para.add(grayText);		
		para.add(pdfContents.get("invoiceToNum")+"\n");
		grayText = new Text("Email : ")
				.setFontColor(ColorConstants.GRAY)
				.setFont(code);
		para.add(grayText);
		para.add(pdfContents.get("invoiceToEmail")+"\n");
		String address[] = pdfContents.get("invoiceToAddr").split(",");
		grayText = new Text("Address : ")
				.setFontColor(ColorConstants.GRAY)
				.setFont(code);
		para.add(grayText);
		for(int i = 0; i<address.length;i++) {
			para.add(address[i]);
		}
		para.add("\n");
		grayText = new Text("Pin code : ")
				.setFontColor(ColorConstants.GRAY)
				.setFont(code);
		para.add(grayText);
		para.add(pdfContents.get("invoiceToPin"));
		cell.add(para).addStyle(tableContentsStyle);   
		table.addCell(cell); 
		document.add(table); 
		para = new Paragraph("\n");
		document.add(para);
		table = new Table(UnitValue.createPercentArray(new float[] {1, 2, 1, 1, 1})).useAllAvailableWidth();
		cell = new Cell().add(new Paragraph("#"));
		cell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
		table.addCell(cell);
		cell = new Cell().add(new Paragraph("Item & Description"));
		cell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
		table.addCell(cell);
		cell = new Cell().add(new Paragraph("Qty"));
		cell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
		table.addCell(cell);
		cell = new Cell().add(new Paragraph("Rate"));
		cell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
		table.addCell(cell);
		cell = new Cell().add(new Paragraph("Amount"));
		cell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
		table.addCell(cell);
		int counter = 1;
		for(SaleDetails saleDetails : allSaleDetails) {
			cell = new Cell().add(new Paragraph(String.valueOf(counter++)));
			table.addCell(cell);
			cell = new Cell().add(new Paragraph(saleDetails.getProduct().getProductName()));
			table.addCell(cell);
			cell = new Cell().add(new Paragraph(String.valueOf(saleDetails.getQuantity())));
			table.addCell(cell);
			cell = new Cell().add(new Paragraph(String.valueOf(saleDetails.getSellingPrice())));
			table.addCell(cell);
			cell = new Cell().add(new Paragraph(String.valueOf(saleDetails.getSellingPrice() * saleDetails.getQuantity())));
			table.addCell(cell);           
		}
		document.add(table);
		para = new Paragraph("\n");
		document.add(para);
		line = new SolidLine();
		line.setColor(grayColor);
		ls = new LineSeparator(line);
		document.add(ls);
		para = new Paragraph("\n");
		document.add(para);
		table = new Table(UnitValue.createPercentArray(new float[] {3, 1, 1,})).useAllAvailableWidth();
		cell = new Cell()
				.setBorder(Border.NO_BORDER);
		cell.add(new Paragraph("Terms & Conditions"));     
		table.addCell(cell);       
		cell = new Cell()
				.setBorder(Border.NO_BORDER);
		cell.add(new Paragraph("Sub Total"));     
		table.addCell(cell);                  
		cell = new Cell()
				.setBorder(Border.NO_BORDER);
		cell.add(new Paragraph(Constants.RUPPEE+" "+pdfContents.get("saleTotal")));     
		table.addCell(cell);
		cell = new Cell()
				.setBorder(Border.NO_BORDER);
		cell.add(new Paragraph("1) Subject to our home jurisdiction.").addStyle(tableContentsStyle));     
		table.addCell(cell);       
		cell = new Cell()
				.setBorder(Border.NO_BORDER);
		cell.add(new Paragraph("CGST (%)").addStyle(tableContentsStyle));     
		table.addCell(cell);                  
		cell = new Cell()
				.setBorder(Border.NO_BORDER);
		cell.add(new Paragraph(pdfContents.get("cgstAmt") + "(" + pdfContents.get("cgstPercent") +")").addStyle(tableContentsStyle));     
		table.addCell(cell);
		cell = new Cell()
				.setBorder(Border.NO_BORDER);
		cell.add(new Paragraph("2) Our Responsibility Ceases as soon as goods leaves our premises.").addStyle(tableContentsStyle));     
		table.addCell(cell);       
		cell = new Cell()
				.setBorder(Border.NO_BORDER);
		cell.add(new Paragraph("SGST (%)").addStyle(tableContentsStyle));     
		table.addCell(cell);                  
		cell = new Cell()
				.setBorder(Border.NO_BORDER);
		cell.add(new Paragraph(pdfContents.get("sgstAmt") + "(" + pdfContents.get("sgstPercent") +")").addStyle(tableContentsStyle));     
		table.addCell(cell);
		cell = new Cell()
				.setBorder(Border.NO_BORDER);
		cell.add(new Paragraph("3) Goods once sold will not be taken back.").addStyle(tableContentsStyle));     
		table.addCell(cell);       
		cell = new Cell()
				.setBorder(Border.NO_BORDER);
		cell.add(new Paragraph("Total Tax").addStyle(tableContentsStyle));     
		table.addCell(cell);                  
		cell = new Cell()
				.setBorder(Border.NO_BORDER);
		cell.add(new Paragraph(pdfContents.get("totalTax")).addStyle(tableContentsStyle));     
		table.addCell(cell);
		cell = new Cell()
				.setBorder(Border.NO_BORDER);
		cell.add(new Paragraph("4) Delivery Ex-Premises.").addStyle(tableContentsStyle));     
		table.addCell(cell);       
		cell = new Cell()
				.setBorder(Border.NO_BORDER);
		cell.add(new Paragraph("Discount").addStyle(tableContentsStyle));     
		table.addCell(cell);                  
		cell = new Cell()
				.setBorder(Border.NO_BORDER);
		cell.add(new Paragraph(pdfContents.get("discountAmt")).addStyle(tableContentsStyle));     
		table.addCell(cell);
		cell = new Cell()
				.setBorder(Border.NO_BORDER);
		cell.add(new Paragraph("").addStyle(tableContentsStyle));     
		table.addCell(cell);       
		cell = new Cell()
				.setBorder(Border.NO_BORDER);
		cell.add(new Paragraph("Total After Tax"));     
		table.addCell(cell);                  
		cell = new Cell()
				.setBorder(Border.NO_BORDER);
		cell.add(new Paragraph(Constants.RUPPEE+" "+pdfContents.get("totalAfterTax")));     
		table.addCell(cell);
		document.add(table);
		document.close();
		return new ByteArrayInputStream(out.toByteArray()); 
	}

	public static ByteArrayInputStream invoiceAppointmentPdf(HashMap<String,String> pdfContents,List<AppointmentDetails> appointmentDetails) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PdfWriter writer = new PdfWriter(out); 
		PdfDocument pdfDoc = new PdfDocument(writer); 
		Document document = new Document(pdfDoc); 
		PdfFont code = PdfFontFactory.createFont(FontConstants.HELVETICA);
		Style style = new Style()
				.setFont(code);
		Paragraph para = new Paragraph("Invoice no. - "+pdfContents.get("invoiceNo"));
		document.add(para.addStyle(style));
		style = new Style()
				.setFont(code)
				.setBold()
				.setFontSize(24);
		para = new Paragraph("");
		para.add(new Tab());
		para.addTabStops(new TabStop(1000,TabAlignment.RIGHT));
		para.add(pdfContents.get("orgName"));
		document.add(para.addStyle(style));
		para = new Paragraph("");
		para.add(new Tab());
		para.addTabStops(new TabStop(1000,TabAlignment.RIGHT));
		para.add(pdfContents.get("orgAddr"));
		style = new Style().setFontSize(10);
		document.add(para.addStyle(style));
		para = new Paragraph("\n");
		document.add(para);
		Color grayColor = new DeviceCmyk(0.f, 0.f, 0.f, 0.875f);
		SolidLine line = new SolidLine();
		line.setColor(grayColor);
		LineSeparator ls = new LineSeparator(line);
		document.add(ls);
		para = new Paragraph("\n");
		document.add(para);
		Style tableContentsStyle = new Style()
				.setFont(code)
				.setFontSize(11);
		Table table = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();
		Cell cell = new Cell()
				.setBorder(Border.NO_BORDER);
		cell.add(new Paragraph("Invoice To"));     
		table.addCell(cell);                
		cell = new Cell()
				.setBorder(Border.NO_BORDER);
		cell.add(new Paragraph("Date: "+pdfContents.get("invoiceDate")));     
		table.addCell(cell.setTextAlignment(TextAlignment.RIGHT));  
		cell = new Cell()
				.setBorder(Border.NO_BORDER);		
		para = new Paragraph();
		Text grayText = new Text("Name : ")
				.setFontColor(ColorConstants.GRAY)
				.setFont(code);
		para.add(grayText);
		para.add(pdfContents.get("invoiceTo")+"\n");
		grayText = new Text("Number : ")
				.setFontColor(ColorConstants.GRAY)
				.setFont(code);
		para.add(grayText);		
		para.add(pdfContents.get("invoiceToNum")+"\n");
		grayText = new Text("Email : ")
				.setFontColor(ColorConstants.GRAY)
				.setFont(code);
		para.add(grayText);
		para.add(pdfContents.get("invoiceToEmail")+"\n");
		String address[] = pdfContents.get("invoiceToAddr").split(",");
		grayText = new Text("Address : ")
				.setFontColor(ColorConstants.GRAY)
				.setFont(code);
		para.add(grayText);
		for(int i = 0; i<address.length;i++) {
			para.add(address[i]);
		}
		para.add("\n");
		grayText = new Text("Pin code : ")
				.setFontColor(ColorConstants.GRAY)
				.setFont(code);
		para.add(grayText);
		para.add(pdfContents.get("invoiceToPin"));
		cell.add(para).addStyle(tableContentsStyle);   
		table.addCell(cell);
		cell = new Cell()
				.setBorder(Border.NO_BORDER);		
		para = new Paragraph();
		table.addCell(cell);
		document.add(table); 
		para = new Paragraph("\n");
		document.add(para);
		table = new Table(UnitValue.createPercentArray(new float[] {1, 2, 1})).useAllAvailableWidth();
		cell = new Cell().add(new Paragraph("Sr. no"));
		table.addCell(cell);
		cell = new Cell().add(new Paragraph("Service & Description"));
		table.addCell(cell);
		cell = new Cell().add(new Paragraph("Amount"));
		table.addCell(cell);
		int counter = 1;
		for(AppointmentDetails appDetails : appointmentDetails) {
			cell = new Cell().add(new Paragraph(String.valueOf(counter++)));
			table.addCell(cell);
			cell = new Cell().add(new Paragraph(appDetails.getService().getServiceName()));
			table.addCell(cell);
			cell = new Cell().add(new Paragraph(String.valueOf(appDetails.getServiceCost())));
			table.addCell(cell);			          
		}
		document.add(table);
		para = new Paragraph("\n");
		document.add(para);
		line = new SolidLine();
		line.setColor(grayColor);
		ls = new LineSeparator(line);
		document.add(ls);
		para = new Paragraph("\n");
		document.add(para);
		table = new Table(2);
		table.addCell(getCell("Sub Total", TextAlignment.LEFT));
		table.addCell(getCell(Constants.RUPPEE+" "+pdfContents.get("appointmentTotal"), TextAlignment.RIGHT));
		table.addCell(getCell("CGST (%)", TextAlignment.LEFT));
		table.addCell(getCell(pdfContents.get("cgstAmt") + " (" + pdfContents.get("cgstPercent") + ")", TextAlignment.RIGHT));
		table.addCell(getCell("SGST (%)", TextAlignment.LEFT));
		table.addCell(getCell(pdfContents.get("sgstAmt") + " (" + pdfContents.get("sgstPercent") + ")", TextAlignment.RIGHT));
		table.addCell(getCell("Total Tax", TextAlignment.LEFT));
		table.addCell(getCell(pdfContents.get("totalTax"), TextAlignment.RIGHT));
		table.addCell(getCell("Discount", TextAlignment.LEFT));
		table.addCell(getCell(pdfContents.get("discountAmt"), TextAlignment.RIGHT));
		table.addCell(getCell("Total After Tax", TextAlignment.LEFT));
		table.addCell(getCell(Constants.RUPPEE+" "+pdfContents.get("totalAfterTax"), TextAlignment.RIGHT));
		document.add(table);
		para = new Paragraph("\n");
		document.add(para);
		line = new SolidLine();
		line.setColor(grayColor);
		ls = new LineSeparator(line);
		document.add(ls);
		para = new Paragraph("\n");
		document.add(para);
		para = new Paragraph("Terms & Conditions\n1) Subject to our home jurisdiction\n2) Our Responsibility Ceases as soon as goods leaves our premises.\n3) Goods once sold will not be taken back.\n4) Delivery Ex-Premises.");
		document.add(para);
		PdfFont regular = PdfFontFactory.createFont(FontConstants.HELVETICA);
		PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
		Text first = new Text("Generated By - ").setFont(regular);
		Text second = new Text("OperateIN").setFont(bold);
		para = new Paragraph().add(first).add(second);
		document.add(para.setFixedPosition(400, 10, 500));
		document.close();
		return new ByteArrayInputStream(out.toByteArray()); 
	}

	public static Cell getCell(String text, TextAlignment alignment) {
	    Cell cell = new Cell().add(new Paragraph(text));
	    cell.setPadding(0);
	    cell.setTextAlignment(alignment);
	    cell.setBorder(Border.NO_BORDER);
	    return cell;
	}
	
}