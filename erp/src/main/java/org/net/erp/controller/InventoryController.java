package org.net.erp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("inventory")
public class InventoryController {

	@RequestMapping("products")
    public String showProductsPage() {
        return "display/display-products-page";
    }
	
	@RequestMapping("newOrder")
    public String showNewOrderPage() {
        return "display/display-new-order-page";
    }
	
	@RequestMapping("sales")
    public String showSalesPage() {
        return "display/display-sales-page";
    }
	
	@RequestMapping("stock")
    public String showStocksPage() {
        return "display/display-stock-page";
    }
	
	@RequestMapping("addSupplier")
    public String showAddSupplierPage() {
        return "display/display-new-supplier-page";
    }
	
	@RequestMapping("/inventory/getAllProducts")
    public ResponseEntity<?> getAllProducts() {		
		String value = "{\r\n" + 
				"	\"meta\": {\r\n" + 
				"		\"page\": 1,\r\n" + 
				"		\"pages\": 1,\r\n" + 
				"		\"perpage\": -1,\r\n" + 
				"		\"total\": 350,\r\n" + 
				"		\"sort\": \"asc\",\r\n" + 
				"		\"field\": \"ProductName\"\r\n" + 
				"	},\r\n" + 
				"	\"data\": [{\r\n" + 
				"			\"ProductName\": \"Cream\",\r\n" + 
				"			\"Brand\": \"L'Oreal\",\r\n" + 
				"			\"Barcode\": \"\",\r\n" + 
				"			\"Price\": \"95\",\r\n" + 
				"			\"Category\": \"Hair Care\"\r\n" + 
				"		},\r\n" + 
				"		{\r\n" + 
				"			\"ProductName\": \"oil\",\r\n" + 
				"			\"Brand\": \"Patanjali\",\r\n" + 
				"			\"Barcode\": \"\",\r\n" + 
				"			\"Price\": \"100\",\r\n" + 
				"			\"Category\": \"Hair Care\"\r\n" + 
				"		},\r\n" + 
				"		{\r\n" + 
				"			\"ProductName\": \"Clinic + Shampoo\",\r\n" + 
				"			\"Brand\": \"Clinic Plus\",\r\n" + 
				"			\"Barcode\": \"\",\r\n" + 
				"			\"Price\": \"120\",\r\n" + 
				"			\"Category\": \"Hair Care\"\r\n" + 
				"		}\r\n" + 
				"	]\r\n" + 
				"}";
        return ResponseEntity.ok(value);
    }
}
