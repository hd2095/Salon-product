package org.net.erp.controller;

import java.io.ByteArrayInputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.net.erp.bo.InvoiceBO;
import org.net.erp.bo.SalesBO;
import org.net.erp.model.Client;
import org.net.erp.model.Invoice;
import org.net.erp.model.InvoiceDetails;
import org.net.erp.model.Master;
import org.net.erp.model.Product;
import org.net.erp.model.SaleDetails;
import org.net.erp.model.Sales;
import org.net.erp.model.Stock;
import org.net.erp.model.lastSevenDaysSales;
import org.net.erp.reports.GeneratePdfReport;
import org.net.erp.repository.ClientRepository;
import org.net.erp.repository.InvoiceDetailsRepository;
import org.net.erp.repository.InvoiceRepository;
import org.net.erp.repository.LastWeekSalesRepository;
import org.net.erp.repository.MasterRepository;
import org.net.erp.repository.SaleDetailsRepository;
import org.net.erp.repository.SalesNotInStockRepo;
import org.net.erp.repository.SalesRepository;
import org.net.erp.repository.StockRepository;
import org.net.erp.repository.SupplierRepository;
import org.net.erp.services.ClientService;
import org.net.erp.services.ProductService;
import org.net.erp.services.SalesService;
import org.net.erp.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("sell")
public class SalesController {
	@Autowired
	private MasterRepository masterRepo;

	@Autowired
	private ClientRepository clientRepo;

	@Autowired
	private ClientService clientService;

	@Autowired
	private SalesRepository salesRepo;

	@Autowired
	private SaleDetailsRepository saleDetailsRepo;

	@Autowired
	private LastWeekSalesRepository lastWeekSalesRepo;

	@Autowired
	private SupplierRepository supplierRepo;

	@Autowired
	private InvoiceRepository invoiceRepo;

	@Autowired
	private InvoiceDetailsRepository invoiceDetailsRepo;

	@Autowired
	private SalesNotInStockRepo salesNotInStockRepo;

	@Autowired
	private StockRepository stockRepo;

	@Autowired
	private SalesService salesService;

	@Autowired
	private ProductService productService;

	@Autowired
	private SalesBO salesBO;

	@Autowired
	private InvoiceBO invoiceBO;

	@GetMapping("/sales")
	public String showSalesPage(Model model) {
		try {
			model.addAttribute(Constants.SALES_FORM, new Sales());
			model.addAttribute(Constants.EDIT_SALES_FORM, new Sales());
			/* model.addAttribute("salesNotInStockForm", new SalesNotInStock()); */

		}catch(Exception e) {

		}
		return Constants.DISPLAY_FOLDER + Constants.FORWARD_SLASH +Constants.SALES_JSP;
	}

	@GetMapping("/createSale")
	public String showSalesForm(Model model) {
		model.addAttribute(Constants.SALES_FORM, new Sales());
		return Constants.FORM_FOLDER + Constants.FORWARD_SLASH +Constants.NEW_SALE_FORM;
	}

	@PostMapping("/createSale")
	public String handleSalesForm(@Valid @ModelAttribute(Constants.SALES_FORM) Sales sales,BindingResult bindingResult,HttpServletRequest request,Model model) {
		Master master = null;
		try {
			int master_id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			String totalElements = request.getParameter("sale_total_elements");
			int repeaterCount = 0;
			if(null == totalElements || Constants.EMPTY.equalsIgnoreCase(totalElements) || Integer.parseInt(totalElements) == 0) {
				repeaterCount = 0;
			}else {
				repeaterCount = Integer.parseInt(totalElements);
			}
			for(int i  = 0;i <= repeaterCount; i++) {	
				String product = request.getParameter("["+ i +"][sale_product]");
				String productSellingPrice = request.getParameter("["+ i +"][product_selling_price]");		
				String productQuantity = request.getParameter("["+ i +"][product_quantity]");
				if(i == 0) {
					master = masterRepo.findByMasterId(master_id);
					sales.setOrganization(master);					
					sales.setSaleDeleteStatuts(Constants.ACTIVE_STATUS);
					Client client = clientService.getClientById(sales.getClient().getClientId());
					client.setRevenue_generated(client.getRevenue_generated() + sales.getSaleTotal());
					sales.setClient(client);
					sales.setSaleInvoiceGenerated(false);
					salesRepo.saveAndFlush(sales);
				}
				SaleDetails saleDetails = new SaleDetails();
				int product_id = Integer.parseInt(product);
				Product productObj = productService.getProductById(product_id);
				saleDetails.setProduct(productObj);
				saleDetails.setSale(sales);
				saleDetails.setQuantity(Integer.parseInt(productQuantity));
				saleDetails.setSellingPrice(Float.parseFloat(productSellingPrice));
				saleDetails.setSaleDetailsDeleteStatuts(Constants.ACTIVE_STATUS);
				saleDetailsRepo.save(saleDetails);
				Stock stock = stockRepo.findByProductId(product_id);
				if(null != stock) {
					stock.setProduct(productObj);
					stock.setOrganization(master);
					stock.setStockId(stock.getStockId());
					stock.setStockQuantity(stock.getStockQuantity() - saleDetails.getQuantity());
					stock.setLastUpdatedDate(new Date());
					stockRepo.save(stock);
				}
				 lastSevenDaysSales existingSale = this.lastWeekSalesRepo.checkIfSaleExists(master_id, sales.getSaleDate());
                 if (null == existingSale) {
                     final lastSevenDaysSales lastSevenDaysSales = new lastSevenDaysSales();
                     lastSevenDaysSales.setSellingDate(sales.getSaleDate());
                     lastSevenDaysSales.setSellingPrice(sales.getSaleTotal());
                     lastSevenDaysSales.setOrganization(master);
                     this.lastWeekSalesRepo.save(lastSevenDaysSales);
                 }
                 else {
                     float newSaleTotal = existingSale.getSellingPrice() + sales.getSaleTotal();
                     this.lastWeekSalesRepo.updateSaleTotal(existingSale.getSalesId(), newSaleTotal);
                 }
			}
		}catch(Exception e) {

		}
		return Constants.DISPLAY_FOLDER + Constants.FORWARD_SLASH +Constants.SALES_JSP;
	}

	@RequestMapping("/getAllSales")
	public ResponseEntity<?> getAllSales(HttpServletRequest request) {
		String jsonValue = null;
		try {
			int id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			jsonValue = salesBO.parseFetchSales(salesRepo.findByMasterId(id));
		}catch(Exception e) {

		}
		return ResponseEntity.ok(jsonValue);
	}

	@RequestMapping("/getSalesDetails/{id}")
	public ResponseEntity<?> getSalesDetails(@PathVariable(value = "id") int id) {
		String jsonValue = null;
		try {
			List<SaleDetails> saleDetails = saleDetailsRepo.findBySaleId(id);
			jsonValue = salesBO.parseSaleDetails(saleDetails);
		}catch(Exception e) {

		}
		return ResponseEntity.ok(jsonValue);
	}

	@RequestMapping("/viewSaleDetails/{id}")
	public String viewSalesDetails(@PathVariable(value = "id") int id,Model model) {		
		try {
			Sales sale =  salesService.getSalesById(id);
			List<SaleDetails> saleDetails = saleDetailsRepo.findBySaleId(id);
			model.addAttribute("saleDate", DateFormat.getDateInstance().format(sale.getSaleDate()));
			model.addAttribute("sale", sale);
			model.addAttribute("saleDetails", saleDetails);
		}catch(Exception e) {

		}
		return Constants.VIEW_DETAILS_FOLDER + Constants.FORWARD_SLASH + Constants.VIEW_SALE_DETAILS;
	}


	@RequestMapping("/lastWeekSales")
	public ResponseEntity<?> getLastWeekSales(HttpServletRequest request) {
		String jsonValue = null;
		try {
			int id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);			
			List<lastSevenDaysSales> sales = lastWeekSalesRepo.lastWeekSales(id);
			Comparator<lastSevenDaysSales> sortedList = 
					(lastSevenDaysSales o1,lastSevenDaysSales o2) -> o1.getSellingDate().compareTo(o2.getSellingDate());
					Collections.sort(sales, sortedList);
					jsonValue = salesBO.parseLastWeekSales(sales);
		}catch(Exception e) {

		}

		return ResponseEntity.ok(jsonValue);
	}

	@RequestMapping("/getAllSalesNotInStock")
	public ResponseEntity<?> getAllSalesNotInStock(HttpServletRequest request) {
		int id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
		String jsonValue = salesBO.parseFetchSalesNotInStock(salesNotInStockRepo.findByMasterId(id));
		return ResponseEntity.ok(jsonValue);
	}

	@GetMapping("/deleteSale/{id}")
	public ResponseEntity<?> deleteSales(@PathVariable(value = "id") int id) {
		String jsonValue = null;
		try {
			Sales sale =  salesService.getSalesById(id);
			sale.setSaleDeleteStatuts(Constants.INACTIVE_STATUS);	
			salesRepo.save(sale);
			if(Constants.INACTIVE_STATUS == salesService.getSalesById(id).getSaleDeleteStatuts()) {
				List<SaleDetails> allSaleDetails = saleDetailsRepo.findBySaleId(id);
				for(SaleDetails saleDetails : allSaleDetails) {
					Stock stock = stockRepo.findByProductId(saleDetails.getProduct().getProductId());
					stock.setStockQuantity(stock.getStockQuantity() + saleDetails.getQuantity());
					stock.setLastUpdatedDate(new Date());
					stockRepo.save(stock);
					saleDetails.setSaleDetailsDeleteStatuts(Constants.INACTIVE_STATUS);
					saleDetailsRepo.save(saleDetails);
				}
				Client client = clientService.getClientById(sale.getClient().getClientId());
				client.setRevenue_generated(client.getRevenue_generated() - sale.getSaleTotal());
				clientRepo.save(client);
				jsonValue = salesBO.setDeleteOperationStatus(true);
			}else {
				jsonValue = salesBO.setDeleteOperationStatus(false);
			}
		}catch(Exception e) {
			return ResponseEntity.ok(jsonValue);
		}
		return ResponseEntity.ok(jsonValue);
	}

	@GetMapping("/editSale/{id}")
	public String editSales(@PathVariable(value = "id") int id,Model model) {
		try {
			Sales sales = this.salesService.getSalesById(id);
			model.addAttribute(Constants.EDIT_SALES_FORM, sales);	
		}catch(Exception e) {

		}
		return Constants.FORM_FOLDER + Constants.FORWARD_SLASH +Constants.EDIT_SALE_FORM;
	}

	@GetMapping("/generateSaleInvoice/{id}")
	public String previewInvoice(@PathVariable(value = "id") int id,Model model) {
		try {
			int totalSaleQuantity = 0;
			Sales sales = this.salesService.getSalesById(id);
			List<SaleDetails> allSaleDetails = saleDetailsRepo.findBySaleId(id);
			for(SaleDetails saleDetails : allSaleDetails) {
				totalSaleQuantity = totalSaleQuantity + saleDetails.getQuantity();
			}
			String initials = "INV-";
			int length = String.valueOf(sales.getOrganization().getInvoiceNo()).length();
			if(length == 3 && sales.getOrganization().getInvoiceNo() < 999) {
				initials += "0"+String.valueOf(sales.getOrganization().getInvoiceNo());
			}else if(length == 2 && sales.getOrganization().getInvoiceNo() < 99) {
				initials += "00"+String.valueOf(sales.getOrganization().getInvoiceNo());
			}else if(length == 1 && sales.getOrganization().getInvoiceNo() < 9) {
				initials += "000"+String.valueOf(sales.getOrganization().getInvoiceNo());
			}
			model.addAttribute(Constants.INVOICE_NO,initials);
			model.addAttribute(Constants.SALE_INVOICE_FORM, sales);	
			model.addAttribute(Constants.SALE_DETAILS_INVOICE_FORM, allSaleDetails);
			model.addAttribute(Constants.TOTAL_SALE_QTY, totalSaleQuantity);
			model.addAttribute(Constants.INVOICE_DATE, DateFormat.getDateInstance().format(new Date()));
			model.addAttribute(Constants.INVOICE_DETAILS_FORM, new InvoiceDetails());
		}catch(Exception e) {

		}
		return Constants.FORM_FOLDER + Constants.FORWARD_SLASH +Constants.GENERATE_IN_VOICE_FORM;
	}

	@PostMapping("/generateSaleInvoice/{id}")
	public String generateInvoice(@PathVariable(value = "id") int id,HttpServletRequest request,@ModelAttribute(Constants.INVOICE_DETAILS_FORM) InvoiceDetails invoiceDetails,ModelMap model) {
		float cgstAmount = 0;
		float discountAmount = 0;
		float sgstAmount = 0;
		int totalSaleQuantity = 0;
		int master_id = 0;
		float totalAfterTax = 0;
		float totalTax = 0;
		Sales sales = null;
		List<SaleDetails> allSaleDetails = null;
		try {
			master_id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			Master master = masterRepo.findByMasterId(master_id);
			String invoiceNo = request.getParameter(Constants.INVOICE_NO);
			String clientId = request.getParameter("clientId");
			sales = this.salesService.getSalesById(id);
			sales.setSaleInvoiceGenerated(true);
			Invoice invoice = new Invoice();
			invoice.setInvoiceNo(invoiceNo);
			invoice.setClient(clientService.getClientById(Integer.parseInt(clientId)));
			invoice.setMaster(master);
			invoice.setInvoiceDate(DateFormat.getDateInstance().format(new Date()));
			invoice.setSale(sales);
			invoiceRepo.saveAndFlush(invoice);
			master.setInvoiceNo(master.getInvoiceNo()+1);
			masterRepo.save(master);
			salesService.save(sales);
			invoiceDetails.setInvoice(invoice);			
			invoiceDetailsRepo.save(invoiceDetails);
			allSaleDetails = saleDetailsRepo.findBySaleId(id);
			for(SaleDetails saleDetails : allSaleDetails) {
				totalSaleQuantity = totalSaleQuantity + saleDetails.getQuantity();
			}
			if(invoiceDetails.getCgst() > 0) {
				cgstAmount = (invoiceDetails.getCgst() * sales.getSaleTotal())/100;
			} 
			if(invoiceDetails.getSgst() > 0) {
				sgstAmount = (invoiceDetails.getSgst() * sales.getSaleTotal())/100;
			} 
			if(invoiceDetails.getDiscount() > 0) {
				discountAmount = (invoiceDetails.getDiscount() * sales.getSaleTotal())/100;
			} 
			totalTax = cgstAmount + sgstAmount;			
			if(totalTax > 0 && discountAmount > 0) {
				totalAfterTax = sales.getSaleTotal() - (totalTax + discountAmount);
			}else if(totalTax > 0) {
				totalAfterTax = sales.getSaleTotal() - totalTax;
			}
		}catch(Exception e) {

		}
		model.addAttribute(Constants.TOTAL_TAX,totalTax);
		model.addAttribute(Constants.DISCOUNT,discountAmount);
		model.addAttribute(Constants.TOTAL_AFTER_TAX,totalAfterTax);
		model.addAttribute(Constants.CGST_AMT,cgstAmount);
		model.addAttribute(Constants.SGST_AMT,sgstAmount);
		model.addAttribute(Constants.SALE_INVOICE_FORM, sales);	
		model.addAttribute(Constants.SALE_DETAILS_INVOICE_FORM, allSaleDetails);
		model.addAttribute(Constants.TOTAL_SALE_QTY, totalSaleQuantity);
		model.addAttribute(Constants.INVOICE_DATE, DateFormat.getDateInstance().format(new Date()));
		model.addAttribute(Constants.INVOICE_DETAILS_FORM, invoiceDetails);
		return Constants.INVOICE + Constants.FORWARD_SLASH +Constants.FINAL_SALE_IN_VOICE_FORM;
	}


	@RequestMapping(value = "/saveSaleInvoice/saleId/{id}/invoiceId/{iId}", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> saveInvoice(@PathVariable(value = "id") int id,@PathVariable(value = "iId") int iId,HttpServletRequest request) {
		Master master = null;
		HashMap<String,String> pdfContents = new HashMap<String,String>();
		ByteArrayInputStream bis = null;
		HttpHeaders headers = new HttpHeaders();
		float cgstAmount = 0;
		float discountAmount = 0;
		float sgstAmount = 0;
		int totalSaleQuantity = 0;
		int master_id = 0;
		float totalAfterTax = 0;
		float totalTax = 0;
		Sales sales = null;
		List<SaleDetails> allSaleDetails = null;
		try {
			sales = salesService.getSalesById(id);
			InvoiceDetails invoiceDetails = invoiceDetailsRepo.findByInvoiceId(iId);
			master_id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			master = masterRepo.findByMasterId(master_id);	
			allSaleDetails = saleDetailsRepo.findBySaleId(id);
			for(SaleDetails saleDetails : allSaleDetails) {
				totalSaleQuantity = totalSaleQuantity + saleDetails.getQuantity();
			}
			if(invoiceDetails.getCgst() > 0) {
				cgstAmount = (invoiceDetails.getCgst() * sales.getSaleTotal())/100;
			} 
			if(invoiceDetails.getSgst() > 0) {
				sgstAmount = (invoiceDetails.getSgst() * sales.getSaleTotal())/100;
			} 
			if(invoiceDetails.getDiscount() > 0) {
				discountAmount = (invoiceDetails.getDiscount() * sales.getSaleTotal())/100;
			} 
			totalTax = cgstAmount + sgstAmount;			
			if(totalTax > 0 && discountAmount > 0) {
				totalAfterTax = sales.getSaleTotal() - (totalTax + discountAmount);
			}else if(totalTax > 0) {
				totalAfterTax = sales.getSaleTotal() - totalTax;
			}			
			pdfContents.put("title", invoiceDetails.getInvoice().getInvoiceNo());
			pdfContents.put("orgName", master.getOrganizationName());
			pdfContents.put("orgAddr",master.getOrganizationAddress());
			pdfContents.put("cgstPercent", String.valueOf(invoiceDetails.getCgst()));
			pdfContents.put("sgstPercent", String.valueOf(invoiceDetails.getSgst()));
			pdfContents.put("discountPercent", String.valueOf(invoiceDetails.getDiscount()));
			pdfContents.put("cgstAmt", String.valueOf(cgstAmount));
			pdfContents.put("sgstAmt", String.valueOf(sgstAmount));
			pdfContents.put("discountAmt", String.valueOf(discountAmount));
			pdfContents.put("totalTax", String.valueOf(totalTax));
			pdfContents.put("saleTotal",String.valueOf(sales.getSaleTotal()));
			pdfContents.put("totalAfterTax", String.valueOf(totalAfterTax));
			pdfContents.put("invoiceDate", invoiceDetails.getInvoice().getInvoiceDate());
			pdfContents.put("invoiceNo", invoiceDetails.getInvoice().getInvoiceNo());
			pdfContents.put("invoiceTo", invoiceDetails.getInvoice().getClient().getFullName());
			pdfContents.put("invoiceToNum", invoiceDetails.getInvoice().getClient().getMobileNumber());
			pdfContents.put("invoiceToEmail", invoiceDetails.getInvoice().getClient().getEmailId());
			pdfContents.put("invoiceToPin", invoiceDetails.getInvoice().getClient().getClientPincode());
			pdfContents.put("invoiceToAddr", invoiceDetails.getInvoice().getClient().getClient_address());
			bis = GeneratePdfReport.invoicePdf(pdfContents,allSaleDetails);		
			headers.add("Content-Disposition", "inline; filename="+invoiceDetails.getInvoice().getInvoiceNo()+".pdf");
		}catch(Exception e) {

		}	
		return ResponseEntity
				.ok()
				.headers(headers)
				.contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}

	@RequestMapping("/getAllSaleInvoices")
	public ResponseEntity<?> allSaleInvoices(HttpServletRequest request) {
		String jsonValue = null;
		try{
			int id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			jsonValue = invoiceBO.parseFetchSaleInvoice(invoiceRepo.findByMasterIdForSale(id));	
		}catch(Exception e) {

		}
		return ResponseEntity.ok(jsonValue);		
	}

	@RequestMapping("/showSaleInvoices")
	public String showSaleInvoices() {
		return Constants.INVOICE + Constants.FORWARD_SLASH +Constants.SHOW_SALE_IN_VOICE;		
	}

	@RequestMapping("/viewInvoiceDetails/{id}")
	public String viewInvoiceDetails(@PathVariable(value = "id") int id,HttpServletRequest request,Model model) {
		float cgstAmount = 0;
		float discountAmount = 0;
		float sgstAmount = 0;
		int totalSaleQuantity = 0;		
		float totalAfterTax = 0;
		float totalTax = 0;
		Sales sales = null;
		List<SaleDetails> allSaleDetails = null;
		Optional<Invoice> invoice = null;
		InvoiceDetails invoiceDetails = null;
		try {
			invoice = invoiceRepo.findById(id);
			invoiceDetails = invoiceDetailsRepo.findByInvoiceId(id);
			sales = invoice.get().getSale();
			allSaleDetails = saleDetailsRepo.findBySaleId(sales.getSaleId());
			for(SaleDetails saleDetails : allSaleDetails) {
				totalSaleQuantity = totalSaleQuantity + saleDetails.getQuantity();
			}
			if(invoiceDetails.getCgst() > 0) {
				cgstAmount = (invoiceDetails.getCgst() * sales.getSaleTotal())/100;
			} 
			if(invoiceDetails.getSgst() > 0) {
				sgstAmount = (invoiceDetails.getSgst() * sales.getSaleTotal())/100;
			} 
			if(invoiceDetails.getDiscount() > 0) {
				discountAmount = (invoiceDetails.getDiscount() * sales.getSaleTotal())/100;
			} 
			totalTax = cgstAmount + sgstAmount;			
			if(totalTax > 0 && discountAmount > 0) {
				totalAfterTax = sales.getSaleTotal() - (totalTax + discountAmount);
			}else if(totalTax > 0) {
				totalAfterTax = sales.getSaleTotal() - totalTax;
			}
		}catch(Exception e) {

		}
		model.addAttribute(Constants.TOTAL_TAX,totalTax);
		model.addAttribute(Constants.DISCOUNT,discountAmount);
		model.addAttribute(Constants.TOTAL_AFTER_TAX,totalAfterTax);
		model.addAttribute(Constants.CGST_AMT,cgstAmount);
		model.addAttribute(Constants.SGST_AMT,sgstAmount);
		model.addAttribute(Constants.SALE_INVOICE_FORM, sales);	
		model.addAttribute(Constants.SALE_DETAILS_INVOICE_FORM, allSaleDetails);
		model.addAttribute(Constants.TOTAL_SALE_QTY, totalSaleQuantity);
		model.addAttribute(Constants.INVOICE_DATE, invoice.get().getInvoiceDate());
		model.addAttribute(Constants.INVOICE_DETAILS_FORM, invoiceDetails);
		return Constants.INVOICE + Constants.FORWARD_SLASH +Constants.FINAL_SALE_IN_VOICE_FORM;
	}

	@RequestMapping("/deleteInvoice/{id}")
	public ResponseEntity<?> deleteSaleInvoice(@PathVariable(value = "id") int id,HttpServletRequest request) {
		String jsonValue = null;
		try {
			int master_id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			Master master = masterRepo.findByMasterId(master_id);
			master.setInvoiceNo(master.getInvoiceNo() - 1);
			Optional<Invoice> invoiceDetails = invoiceRepo.findById(id);
			Sales sales = salesService.getSalesById(invoiceDetails.get().getSale().getSaleId());
			sales.setSaleInvoiceGenerated(false);
			salesService.save(sales);
			masterRepo.save(master);
			invoiceRepo.deleteById(id);			
			if(invoiceRepo.findById(id).isPresent()) {
				jsonValue = invoiceBO.setDeleteOperationStatus(false);
			}else {
				jsonValue = invoiceBO.setDeleteOperationStatus(true);
			}
		}catch(Exception e) {

		}
		return ResponseEntity.ok(jsonValue);		
	}

	@PostMapping("/editSale/{id}")
	public String updateSales(@PathVariable(value = "id") int id,@ModelAttribute(Constants.EDIT_SALES_FORM) Sales sales,BindingResult bindingResult,HttpServletRequest request,Model model) {
		Master master = null;
		try {
			int master_id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);		
			String totalElements = request.getParameter("edit_sale_total_elements");
			int repeaterCount = 0;
			if(null == totalElements || Constants.EMPTY.equalsIgnoreCase(totalElements) || Integer.parseInt(totalElements) == 0) {
				repeaterCount = 0;
			}else {
				repeaterCount = Integer.parseInt(totalElements);
			}
			master = masterRepo.findByMasterId(master_id);
			for(int i  = 0;i <= repeaterCount; i++) {
				sales.setOrganization(master);
				sales.setSaleDeleteStatuts(Constants.ACTIVE_STATUS);
				String product = request.getParameter("["+ i +"][edit_sale_product]");
				String productSellingPrice = request.getParameter("["+ i +"][edit_product_selling_price]");		
				String productQuantity = request.getParameter("["+ i +"][edit_product_quantity]");
				String recordId = request.getParameter("["+ i +"][edit_sale_record_id]");
				SaleDetails saleDetails = null;
				if(null != recordId && !Constants.EMPTY.equalsIgnoreCase(recordId)) {
					saleDetails = saleDetailsRepo.findBySaleDetailsId(Integer.parseInt(recordId));
					if(saleDetails.getProduct().getProductId() == Integer.parseInt(product)) {
						if(saleDetails.getQuantity() > Integer.parseInt(productQuantity)) {
							Client client = clientService.getClientById(sales.getClient().getClientId());
							client.setRevenue_generated(client.getRevenue_generated() - (saleDetails.getSellingPrice() * (saleDetails.getQuantity() - Integer.parseInt(productQuantity))));
							sales.setClient(client);
							Stock stock = stockRepo.findByProductId(saleDetails.getProduct().getProductId());
							if(null != stock) {
								stock.setProduct(saleDetails.getProduct());
								stock.setOrganization(master);
								stock.setStockId(stock.getStockId());
								stock.setStockQuantity(stock.getStockQuantity() + (saleDetails.getQuantity() - Integer.parseInt(productQuantity)));
								stock.setLastUpdatedDate(new Date());
								stockRepo.save(stock);
							}																	
							saleDetails.setQuantity(saleDetails.getQuantity() - (saleDetails.getQuantity() - Integer.parseInt(productQuantity)));
						}else if(saleDetails.getQuantity() < Integer.parseInt(productQuantity)){
							Client client = clientService.getClientById(sales.getClient().getClientId());
							client.setRevenue_generated(client.getRevenue_generated() + (saleDetails.getSellingPrice() * (Integer.parseInt(productQuantity) - saleDetails.getQuantity())));
							sales.setClient(client);
							Stock stock = stockRepo.findByProductId(saleDetails.getProduct().getProductId());
							if(null != stock) {
								stock.setProduct(saleDetails.getProduct());
								stock.setOrganization(master);
								stock.setStockId(stock.getStockId());
								stock.setStockQuantity(stock.getStockQuantity() - (Integer.parseInt(productQuantity) - saleDetails.getQuantity()));
								stock.setLastUpdatedDate(new Date());
								stockRepo.save(stock);
							}													
							saleDetails.setQuantity(saleDetails.getQuantity() + (Integer.parseInt(productQuantity) - saleDetails.getQuantity()));
						}
					}
					saleDetailsRepo.save(saleDetails);
				}else {
					saleDetails = new SaleDetails();	
					int product_id = Integer.parseInt(product);
					Product productObj = productService.getProductById(product_id);
					saleDetails.setProduct(productObj);
					saleDetails.setSale(sales);
					saleDetails.setQuantity(Integer.parseInt(productQuantity));
					saleDetails.setSellingPrice(Float.parseFloat(productSellingPrice));
					saleDetails.setSaleDetailsDeleteStatuts(Constants.ACTIVE_STATUS);
					saleDetailsRepo.save(saleDetails);
					Stock stock = stockRepo.findByProductId(product_id);
					if(null != stock) {
						stock.setProduct(productObj);
						stock.setOrganization(master);
						stock.setStockId(stock.getStockId());
						stock.setStockQuantity(stock.getStockQuantity() - saleDetails.getQuantity());
						stock.setLastUpdatedDate(new Date());
						stockRepo.save(stock);
					}					
					Client client = clientService.getClientById(sales.getClient().getClientId());
					client.setRevenue_generated(client.getRevenue_generated() + (saleDetails.getSellingPrice() * saleDetails.getQuantity()));
					sales.setClient(client);					
				}	
				salesRepo.saveAndFlush(sales);
			}
		}catch(Exception e) {

		}
		model.addAttribute(Constants.SALES_FORM, new Sales());
		model.addAttribute(Constants.EDIT_SALES_FORM, new Sales());
		return Constants.REDIRECT_SELL_JSP;
	}

	@RequestMapping("/deleteProductFromSale/{id}")
	public ResponseEntity<?> deleteProductFromSale(@PathVariable(value = "id") int id,HttpServletRequest request) {
		String jsonValue = null;
		boolean redirect = false;
		try {
			SaleDetails productToDelete = saleDetailsRepo.findBySaleDetailsId(id);
			productToDelete.setSaleDetailsDeleteStatuts(Constants.INACTIVE_STATUS);
			Stock stock = stockRepo.findByProductId(productToDelete.getProduct().getProductId());
			stock.setStockQuantity(stock.getStockQuantity() + productToDelete.getQuantity());
			stock.setLastUpdatedDate(new Date());
			stockRepo.save(stock);		
			saleDetailsRepo.save(productToDelete);
			Sales sale = salesService.getSalesById(productToDelete.getSale().getSaleId());
			sale.setSaleTotal(sale.getSaleTotal() - (productToDelete.getSellingPrice() * productToDelete.getQuantity()));			
			if(saleDetailsRepo.findBySaleId(productToDelete.getSale().getSaleId()).size() == 0) {
				sale.setSaleDeleteStatuts(Constants.INACTIVE_STATUS);
				redirect = true;
			}
			salesService.save(sale);
			Client client = clientService.getClientById(sale.getClient().getClientId());
			client.setRevenue_generated(client.getRevenue_generated() - (productToDelete.getSellingPrice() * productToDelete.getQuantity()));
			clientRepo.save(client);
			if(Constants.INACTIVE_STATUS == saleDetailsRepo.findBySaleDetailsId(id).getSaleDetailsDeleteStatuts()) {
				if(redirect) {
					jsonValue = "{\r\n" + 
							"	\"redirect\": true\r\n" + 
							"}";
				}else {
					jsonValue = salesBO.setDeleteOperationStatus(true);	
				}				
			}else {
				jsonValue = salesBO.setDeleteOperationStatus(false);
			}
		}catch(Exception e) {

		}
		return ResponseEntity.ok(jsonValue);
	}

}
