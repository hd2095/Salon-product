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
	
	@RequestMapping("addSupplier")
    public String showAddSupplierPage() {
        return "display/display-new-supplier-page";
    }
	
}
