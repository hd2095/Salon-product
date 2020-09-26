package org.net.erp.reports;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.net.erp.model.SaleDetails;
import org.net.erp.util.Constants;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceCmyk;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.TabStop;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TabAlignment;
import com.itextpdf.layout.property.UnitValue;

@SuppressWarnings("deprecation")
public class GeneratePdfReport {

	public static ByteArrayInputStream invoicePdf(HashMap<String,String> pdfContents,List<SaleDetails> allSaleDetails) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PdfWriter writer = new PdfWriter(out); 
		PdfDocument pdfDoc = new PdfDocument(writer); 
		Document document = new Document(pdfDoc); 
		PdfPage page = pdfDoc.addNewPage();
		String orgAddress = Constants.EMPTY;
		for(int i = 0;i<pdfContents.get("orgAddr").split(Constants.COMMA).length;i++) {
			orgAddress += pdfContents.get("orgAddr").split(Constants.COMMA)[i] + "\r\n";
		}
		PdfFont code = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
		Style style = new Style()
				.setFont(code)
				.setFontSize(24)
				.setFontColor(ColorConstants.GRAY);
		Paragraph para = new Paragraph(pdfContents.get("orgName"));
		document.add(para.addStyle(style));
		para = new Paragraph(orgAddress);		
		document.add(para);
		Color grayColor = new DeviceCmyk(0.f, 0.f, 0.f, 0.875f);
		PdfCanvas canvas = new PdfCanvas(page);
		canvas.moveTo(30, 650); 
		canvas.lineTo(550, 650);	
		canvas.setLineWidth(0.5f)
		.setStrokeColor(grayColor);
		canvas.stroke();
		para = new Paragraph("\n");
		document.add(para);
		para = new Paragraph();
		para.addTabStops(new TabStop(200f, TabAlignment.LEFT));
		para.add("Invoice Date");
		para.add(new Tab());
		para.add("Invoice To");
		para.add(new Tab());
		para.add(new Tab());
		para.add(new Tab());
		para.add("Invoice No");
		document.add(para);
		para = new Paragraph();
		para.addTabStops(new TabStop(200f, TabAlignment.LEFT));
		para.add(pdfContents.get("invoiceDate"));
		para.add(new Tab());
		para.add("Name : "+pdfContents.get("invoiceTo"));
		para.add(new Tab());
		para.add(new Tab());
		para.add(new Tab());
		para.add(pdfContents.get("invoiceNo"));
		document.add(para);
		para = new Paragraph();
		para.addTabStops(new TabStop(200f, TabAlignment.LEFT));
		para.add("");
		para.add(new Tab());
		para.add("Number : "+pdfContents.get("invoiceToNum"));		
		document.add(para);
		para = new Paragraph();
		para.addTabStops(new TabStop(200f, TabAlignment.LEFT));
		para.add("");
		para.add(new Tab());	
		para.add("Email : "+pdfContents.get("invoiceToEmail"));
		document.add(para);
		String address[] = pdfContents.get("invoiceToAddr").split(",");
		for(int i = 0; i<address.length;i++) {
			if(i == 0) {
				para = new Paragraph();
				para.addTabStops(new TabStop(200f, TabAlignment.LEFT));
				para.add("");
				para.add(new Tab());		
				para.add("Address : "+address[i]);
				document.add(para);
			}else {
				para = new Paragraph();
				para.addTabStops(new TabStop(200f, TabAlignment.LEFT));
				para.add(new Tab());		
				para.add(address[i]);
				document.add(para);
			}
		}
		para = new Paragraph();
		para.addTabStops(new TabStop(200f, TabAlignment.LEFT));
		para.add("");
		para.add(new Tab());	
		para.add("Pin code : "+pdfContents.get("invoiceToPin"));
		document.add(para);
		document.close();
		return new ByteArrayInputStream(out.toByteArray()); 
	}
	
}