package org.net.erp.controller;

import java.io.ByteArrayInputStream;
import java.text.DateFormat;
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
import org.net.erp.services.ClientService;
import org.net.erp.services.ProductService;
import org.net.erp.services.SalesService;
import org.net.erp.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(SalesController.class);

	@GetMapping("/sales")
	public String showSalesPage(Model model,HttpServletRequest request) {
		int id = 0;
		try {
			id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			model.addAttribute(Constants.SALES_FORM, new Sales());
			model.addAttribute(Constants.EDIT_SALES_FORM, new Sales());
			Master master = masterRepo.findByMasterId(id);
			int entries = salesService.checkSaleEntries(id);
			if(master.getOrganizationPlan().equalsIgnoreCase(Constants.ORG_PLAN_BASIC)) {
				if(entries < 25) {
					model.addAttribute(Constants.SHOW_ADD_BUTTON, true);
				}
			}else if(master.getOrganizationPlan().equalsIgnoreCase(Constants.ORG_PLAN_STANDARD)) {
				if(entries < 500) {
					model.addAttribute(Constants.SHOW_ADD_BUTTON, true);
				}
			}else {
				model.addAttribute(Constants.SHOW_ADD_BUTTON, true);
			}
		}catch(Exception e) {
			LOGGER.error("Exception in showSalesPage for organization id :: "+id+" :: "+e.getMessage());
		}
		return Constants.DISPLAY_FOLDER + Constants.FORWARD_SLASH +Constants.SALES_JSP;
	}

	@GetMapping("/createSale")
	public String showSalesForm(Model model) {
		String returnValue = null;
		try {
			returnValue = Constants.FORM_FOLDER + Constants.FORWARD_SLASH +Constants.NEW_SALE_FORM;
			model.addAttribute(Constants.SALES_FORM, new Sales());	
		}catch(Exception e) {
			LOGGER.error("Exception in showSalesForm :: "+e.getMessage());
		}
		return returnValue;
	}

	@PostMapping("/createSale")
	public String handleSalesForm(@Valid @ModelAttribute(Constants.SALES_FORM) Sales sales,BindingResult bindingResult,HttpServletRequest request,Model model) {
		Master master = null;
		int master_id = 0;
		try {
			master_id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
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
				}else {
					stock = new Stock();
					stock.setProduct(productObj);
					stock.setOrganization(master);
					stock.setStockQuantity(0 - saleDetails.getQuantity());
					stock.setLastUpdatedDate(new Date());
					stockRepo.save(stock);
				}
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
				this.lastWeekSalesRepo.updateSaleTotal(existingSale.getSaleId(), newSaleTotal);
			}
		}catch(Exception e) {
			LOGGER.error("Exception in handleSalesForm for organization id :: "+master_id+" :: "+e.getMessage());
		}
		return "redirect:/sell/sales";
	}

	@RequestMapping("/getAllSales")
	public ResponseEntity<?> getAllSales(HttpServletRequest request) {
		String jsonValue = null;
		int orderByColumn = 0;
		List<Sales> sales = null;
		String order = null;
		String draw = null;
		int id = 0;
		String searchParam = null;
		try {
			id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			searchParam = request.getParameter("search[value]");
			if(null != searchParam && !Constants.EMPTY.equalsIgnoreCase(searchParam)) {
				sales = salesRepo.findByClientName(id,searchParam);
			}else {
				String orderable = request.getParameter("order[0][column]");
				draw = request.getParameter("draw");			
				if(null != draw) {
					int drawIndex = Integer.parseInt(draw);
					if(drawIndex != 1) {
						if(null != orderable) {
							orderByColumn = Integer.parseInt(orderable);
						}
						order = request.getParameter("order[0][dir]");
						if(orderByColumn == 0){
							if(null != order) {
								if(order.equalsIgnoreCase(Constants.SORT_DESC)) { //Default order is ASC by datatable but we want DESC so this discrepancy.
									sales = salesRepo.findByClientIdAsc(id);
								}else {
									sales = salesRepo.findByClientId(id);	
								}
							}
						} else if(orderByColumn == 1) {
							if(null != order) {
								if(order.equalsIgnoreCase(Constants.SORT_DESC)) {
									sales = salesRepo.findByMasterId(id);	
								}else {
									sales = salesRepo.findByMasterIdAsc(id);
								}
							}
						} else if(orderByColumn == 2) {
							if(null != order) {
								if(order.equalsIgnoreCase(Constants.SORT_DESC)) {
									sales = salesRepo.findByTotalAsc(id); 	//Default order is ASC by datatable but we want DESC so this discrepancy. Toggle 
								}else {
									sales = salesRepo.findByTotal(id);
								}
							}
						}
					}else {
						sales = salesRepo.findByMasterId(id);
					}	
				}
			}
			jsonValue = salesBO.parseFetchSales(sales);
		}catch(Exception e) {
			LOGGER.error("Exception in getAllSales for organization id :: "+id+" :: "+e.getMessage());
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
			LOGGER.error("Exception in getSalesDetails for sale id :: "+id+" :: "+e.getMessage());
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
			LOGGER.error("Exception in viewSalesDetails for sale id :: "+id+" :: "+e.getMessage());
		}
		return Constants.VIEW_DETAILS_FOLDER + Constants.FORWARD_SLASH + Constants.VIEW_SALE_DETAILS;
	}


	@RequestMapping("/lastWeekSales")
	public ResponseEntity<?> getLastWeekSales(HttpServletRequest request) {
		String jsonValue = null;
		int id = 0;
		try {
			id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);			
			List<lastSevenDaysSales> sales = lastWeekSalesRepo.lastWeekSales(id);
			Comparator<lastSevenDaysSales> sortedList = 
					(lastSevenDaysSales o1,lastSevenDaysSales o2) -> o1.getSellingDate().compareTo(o2.getSellingDate());
					Collections.sort(sales, sortedList);
					jsonValue = salesBO.parseLastWeekSales(sales);
		}catch(Exception e) {
			LOGGER.error("Exception in getLastWeekSales for organization id :: "+id+" :: "+e.getMessage());
		}
		return ResponseEntity.ok(jsonValue);
	}

	@RequestMapping("/getAllSalesNotInStock")
	public ResponseEntity<?> getAllSalesNotInStock(HttpServletRequest request) {
		int id = 0;
		String jsonValue = null;
		try {
			id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			jsonValue = salesBO.parseFetchSalesNotInStock(salesNotInStockRepo.findByMasterId(id));
		}catch(Exception e) {
			LOGGER.error("Exception in getAllSalesNotInStock for organization id :: "+id+" :: "+e.getMessage());
		}
		return ResponseEntity.ok(jsonValue);
	}

	@GetMapping("/deleteSale/{id}")
	public ResponseEntity<?> deleteSales(@PathVariable(value = "id") int id,HttpServletRequest request) {
		String jsonValue = null;
		try {
			Sales sale =  salesService.getSalesById(id);
			sale.setSaleDeleteStatuts(Constants.INACTIVE_STATUS);	
			salesRepo.save(sale);
			if(Constants.INACTIVE_STATUS == salesService.getSalesById(id).getSaleDeleteStatuts()) {
				int master_id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
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
				if(client.getRevenue_generated() > 0) {
					client.setRevenue_generated(client.getRevenue_generated() - sale.getSaleTotal());	
				}
				clientRepo.save(client);
				if(sale.isSaleInvoiceGenerated()) {
					Master master = masterRepo.findByMasterId(master_id);
					master.setInvoiceNo(master.getInvoiceNo() - 1);
					Invoice invoice = invoiceRepo.findInvoiceBySaleId(id);
					invoiceRepo.deleteById(invoice.getInvoiceId());	
				}
				lastSevenDaysSales existingSale = this.lastWeekSalesRepo.checkIfSaleExists(master_id, sale.getSaleDate());
				float newSaleTotal = existingSale.getSellingPrice() - sale.getSaleTotal();
				if(newSaleTotal == 0) {
					this.lastWeekSalesRepo.deleteById(existingSale.getSaleId());
				}else {
					this.lastWeekSalesRepo.updateSaleTotal(existingSale.getSaleId(), newSaleTotal);
				}	
				jsonValue = salesBO.setDeleteOperationStatus(true);
			}else {
				jsonValue = salesBO.setDeleteOperationStatus(false);
			}
		}catch(Exception e) {
			LOGGER.error("Exception in deleteSales for sale id :: "+id+" :: "+e.getMessage());
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
			LOGGER.error("Exception in editSales for sale id :: "+id+" :: "+e.getMessage());
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
			}else if(length == 2 && sales.getOrganization().getInvoiceNo() <= 99) {
				initials += "00"+String.valueOf(sales.getOrganization().getInvoiceNo());
			}else if(length == 1 && sales.getOrganization().getInvoiceNo() <= 9) {
				initials += "000"+String.valueOf(sales.getOrganization().getInvoiceNo());
			}
			model.addAttribute(Constants.INVOICE_NO,initials);
			model.addAttribute(Constants.SALE_INVOICE_FORM, sales);	
			model.addAttribute(Constants.SALE_DETAILS_INVOICE_FORM, allSaleDetails);
			model.addAttribute(Constants.TOTAL_SALE_QTY, totalSaleQuantity);
			model.addAttribute(Constants.INVOICE_DATE, DateFormat.getDateInstance().format(new Date()));
			model.addAttribute(Constants.INVOICE_DETAILS_FORM, new InvoiceDetails());
		}catch(Exception e) {
			LOGGER.error("Exception in previewInvoice for invoice id :: "+id+" :: "+e.getMessage());
		}
		return Constants.FORM_FOLDER + Constants.FORWARD_SLASH +Constants.GENERATE_IN_VOICE_FORM;
	}

	@PostMapping("/generateSaleInvoice/{id}")
	public String generateInvoice(@PathVariable(value = "id") int id,HttpServletRequest request,@ModelAttribute(Constants.INVOICE_DETAILS_FORM) InvoiceDetails invoiceDetails,ModelMap model) {
		float saleTotal = 0;
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
			if(invoiceDetails.getDiscount() > 0) {
				discountAmount = (invoiceDetails.getDiscount() * sales.getSaleTotal())/100;
			} 
			if(discountAmount > 0) {
				saleTotal = sales.getSaleTotal() - discountAmount;
			}
			if(invoiceDetails.getCgst() > 0) {
				if(saleTotal > 0) {
					cgstAmount = (invoiceDetails.getCgst() * saleTotal)/100;
				}else {
					cgstAmount = (invoiceDetails.getCgst() * sales.getSaleTotal())/100;
				}
			} 
			if(invoiceDetails.getSgst() > 0) {
				if(saleTotal > 0) {
					sgstAmount = (invoiceDetails.getSgst() * saleTotal)/100;
				}else {
					sgstAmount = (invoiceDetails.getSgst() * sales.getSaleTotal())/100;
				}
			} 
			totalTax = cgstAmount + sgstAmount;			
			if(totalTax > 0) {
				if(saleTotal > 0) {
					totalAfterTax = saleTotal + totalTax;
				}else {
					totalAfterTax = sales.getSaleTotal() - discountAmount;
				}			
			}else {
				totalAfterTax = sales.getSaleTotal() - discountAmount;
			}
		}catch(Exception e) {
			LOGGER.error("Exception in generateInvoice for organization id :: "+master_id+"for invoice id :: "+id+" :: "+e.getMessage());
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
		float saleTotal = 0;
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
			if(invoiceDetails.getDiscount() > 0) {
				discountAmount = (invoiceDetails.getDiscount() * sales.getSaleTotal())/100;
			} 
			if(discountAmount > 0) {
				saleTotal = sales.getSaleTotal() - discountAmount;
			}
			if(invoiceDetails.getCgst() > 0) {
				if(saleTotal > 0) {
					cgstAmount = (invoiceDetails.getCgst() * saleTotal)/100;
				}else {
					cgstAmount = (invoiceDetails.getCgst() * sales.getSaleTotal())/100;
				}
			} 
			if(invoiceDetails.getSgst() > 0) {
				if(saleTotal > 0) {
					sgstAmount = (invoiceDetails.getSgst() * saleTotal)/100;
				}else {
					sgstAmount = (invoiceDetails.getSgst() * sales.getSaleTotal())/100;
				}
			} 
			totalTax = cgstAmount + sgstAmount;			
			if(totalTax > 0) {
				if(saleTotal > 0) {
					totalAfterTax = saleTotal + totalTax;
				}else {
					totalAfterTax = sales.getSaleTotal() - discountAmount;
				}			
			}else {
				totalAfterTax = sales.getSaleTotal() - discountAmount;
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
			headers.add("Content-Disposition", "attachment; filename="+invoiceDetails.getInvoice().getInvoiceNo()+".pdf");
		}catch(Exception e) {
			LOGGER.error("Exception in saveInvoice for organization id :: "+master_id+" for invoice id :: "+id+" :: "+e.getMessage());
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
		int id = 0;
		try{
			id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			jsonValue = invoiceBO.parseFetchSaleInvoice(invoiceRepo.findByMasterIdForSale(id));	
		}catch(Exception e) {
			LOGGER.error("Exception in allSaleInvoices for organization id :: "+id+" :: "+e.getMessage());
		}
		return ResponseEntity.ok(jsonValue);		
	}

	@RequestMapping("/showSaleInvoices")
	public String showSaleInvoices() {
		String returnValue = null;
		try {
			returnValue = Constants.INVOICE + Constants.FORWARD_SLASH +Constants.SHOW_SALE_IN_VOICE;
		}catch(Exception e) {
			LOGGER.error("Exception in showSaleInvoices :: "+e.getMessage());
		}
		return returnValue;
	}

	@RequestMapping("/viewInvoiceDetails/{id}")
	public String viewInvoiceDetails(@PathVariable(value = "id") int id,Model model) {
		float cgstAmount = 0;
		float discountAmount = 0;
		float sgstAmount = 0;
		float saleTotal = 0;
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
			if(invoiceDetails.getDiscount() > 0) {
				discountAmount = (invoiceDetails.getDiscount() * sales.getSaleTotal())/100;
			} 
			if(discountAmount > 0) {
				saleTotal = sales.getSaleTotal() - discountAmount;
			}
			if(invoiceDetails.getCgst() > 0) {
				if(saleTotal > 0) {
					cgstAmount = (invoiceDetails.getCgst() * saleTotal)/100;
				}else {
					cgstAmount = (invoiceDetails.getCgst() * sales.getSaleTotal())/100;
				}
			} 
			if(invoiceDetails.getSgst() > 0) {
				if(saleTotal > 0) {
					sgstAmount = (invoiceDetails.getSgst() * saleTotal)/100;
				}else {
					sgstAmount = (invoiceDetails.getSgst() * sales.getSaleTotal())/100;
				}
			} 
			totalTax = cgstAmount + sgstAmount;			
			if(totalTax > 0) {
				if(saleTotal > 0) {
					totalAfterTax = saleTotal + totalTax;
				}else {
					totalAfterTax = sales.getSaleTotal() - discountAmount;
				}			
			}else {
				totalAfterTax = sales.getSaleTotal() - discountAmount;
			}
		}catch(Exception e) {
			LOGGER.error("Exception in viewInvoiceDetails :: "+e.getMessage());
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
		int master_id = 0;
		try {
			master_id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			Master master = masterRepo.findByMasterId(master_id);
			if(master.getInvoiceNo() > 1) {
				master.setInvoiceNo(master.getInvoiceNo() - 1);	
			}
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
			LOGGER.error("Exception in deleteSaleInvoice for organization id :: "+master_id+"for invoice id "+id+":: "+e.getMessage());
		}
		return ResponseEntity.ok(jsonValue);		
	}

	@PostMapping("/editSale/{id}")
	public String updateSales(@PathVariable(value = "id") int id,@ModelAttribute(Constants.EDIT_SALES_FORM) Sales sales,BindingResult bindingResult,HttpServletRequest request,Model model) {
		Master master = null;
		int master_id = 0;
		try {
			master_id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);		
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
							lastSevenDaysSales existingSale = this.lastWeekSalesRepo.checkIfSaleExists(master_id, sales.getSaleDate());
							float newSaleTotal = existingSale.getSellingPrice() - (saleDetails.getSellingPrice() * (saleDetails.getQuantity() - Integer.parseInt(productQuantity)));
							if(newSaleTotal == 0) {
								this.lastWeekSalesRepo.deleteById(existingSale.getSaleId());
							}else {
								this.lastWeekSalesRepo.updateSaleTotal(existingSale.getSaleId(), newSaleTotal);
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
							lastSevenDaysSales existingSale = this.lastWeekSalesRepo.checkIfSaleExists(master_id, sales.getSaleDate());
							float newSaleTotal = existingSale.getSellingPrice() + (saleDetails.getSellingPrice() * (saleDetails.getQuantity() - Integer.parseInt(productQuantity)));
							if(newSaleTotal == 0) {
								this.lastWeekSalesRepo.deleteById(existingSale.getSaleId());
							}else {
								this.lastWeekSalesRepo.updateSaleTotal(existingSale.getSaleId(), newSaleTotal);
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
					lastSevenDaysSales existingSale = this.lastWeekSalesRepo.checkIfSaleExists(master_id, sales.getSaleDate());
					float newSaleTotal = existingSale.getSellingPrice() + (saleDetails.getSellingPrice() * saleDetails.getQuantity());
					this.lastWeekSalesRepo.updateSaleTotal(existingSale.getSaleId(), newSaleTotal);					
				}	
				salesRepo.saveAndFlush(sales);
			}
		}catch(Exception e) {
			LOGGER.error("Exception in updateSales for organization id :: "+master_id+"for sale id "+id+":: "+e.getMessage());
		}
		model.addAttribute(Constants.SALES_FORM, new Sales());
		model.addAttribute(Constants.EDIT_SALES_FORM, new Sales());
		return Constants.REDIRECT_SELL_JSP;
	}

	@RequestMapping("/deleteProductFromSale/{id}")
	public ResponseEntity<?> deleteProductFromSale(@PathVariable(value = "id") int id,HttpServletRequest request) {
		String jsonValue = null;
		boolean redirect = false;
		int master_id = 0;
		try {
			master_id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
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
			if(client.getRevenue_generated() > 0) {
				client.setRevenue_generated(client.getRevenue_generated() - (productToDelete.getSellingPrice() * productToDelete.getQuantity()));	
			}
			clientRepo.save(client);
			lastSevenDaysSales existingSale = this.lastWeekSalesRepo.checkIfSaleExists(master_id, sale.getSaleDate());
			float newSaleTotal = existingSale.getSellingPrice() - (productToDelete.getSellingPrice() * productToDelete.getQuantity());
			if(newSaleTotal == 0) {
				this.lastWeekSalesRepo.deleteById(existingSale.getSaleId());
			}else {
				this.lastWeekSalesRepo.updateSaleTotal(existingSale.getSaleId(), newSaleTotal);
			}	
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
			LOGGER.error("Exception in deleteProductFromSale for organization id :: "+master_id+"for sale id "+id+":: "+e.getMessage());
		}
		return ResponseEntity.ok(jsonValue);
	}

}
