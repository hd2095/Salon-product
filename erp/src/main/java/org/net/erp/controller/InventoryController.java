package org.net.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("inventory")
public class InventoryController {

	@RequestMapping("products")
    public String showProductsPage() {
        return "display/display-products-page";
    }
		
	@RequestMapping("sales")
    public String showSalesPage() {
        return "display/display-sales-page";
    }
	
}
