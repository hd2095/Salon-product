package org.net.erp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.net.erp.bo.OrderBO;
import org.net.erp.model.Master;
import org.net.erp.model.Order;
import org.net.erp.model.Product;
import org.net.erp.model.Stock;
import org.net.erp.model.Supplier;
import org.net.erp.repository.MasterRepository;
import org.net.erp.repository.OrderRepository;
import org.net.erp.repository.StockRepository;
import org.net.erp.services.OrderService;
import org.net.erp.services.ProductService;
import org.net.erp.services.SupplierService;
import org.net.erp.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("inventory")
public class OrderController {

	@Autowired
	private MasterRepository masterRepo;

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	StockRepository stockRepo;

	@Autowired
	private SupplierService supplierService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderBO orderBO;

	@GetMapping("/newOrder")
	public String showOrderPage(Model model) {
		try {
			model.addAttribute(Constants.ORDER_FORM, new Order());
			model.addAttribute(Constants.EDIT_ORDER_FORM, new Order());
		}catch(Exception e) {

		}
		return Constants.DISPLAY_FOLDER + Constants.FORWARD_SLASH +Constants.ORDER_JSP;
	}

	@PostMapping("/newOrder")
	public String handleOrderForm(@Valid @ModelAttribute(Constants.ORDER_FORM) Order order,BindingResult bindingResult,HttpServletRequest request,Model model) {
		try {
			if(!bindingResult.hasErrors()) {
				int key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
				Master master = masterRepo.findByMasterId(key);
				float costPrice = Float.parseFloat(order.getCostPrice());
				float orderTotal = costPrice * order.getQuantity();
				order.setOrganization(master);
				order.setOrderTotal(orderTotal);
				int productId = Integer.parseInt(order.getProduct().getProductName().split(Constants.COMMA)[0]);
				Product product = productService.getProductById(productId);
				order.setProduct(product);
				Supplier supplier = supplierService.getSupplierById(order.getSupplier().getSupplierId());
				order.setSupplier(supplier);
				order.setOrderDeliveryStatus(Constants.ORDER_STATUS_BOOKED);
				order.setOrderStatus(Constants.ACTIVE_STATUS);
				orderRepo.save(order);				
			}
			model.addAttribute(Constants.ORDER_FORM, new Order());
			model.addAttribute(Constants.EDIT_ORDER_FORM, new Order());
		}catch(Exception e) {

		}
		return Constants.DISPLAY_FOLDER + Constants.FORWARD_SLASH +Constants.ORDER_JSP;
	}

	@RequestMapping("/getAllOrders")
	public ResponseEntity<?> getAllOrders(HttpServletRequest request) {
		String jsonValue = null;
		try {
			int id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			jsonValue = orderBO.parseFetchOrder(orderRepo.findByMasterId(id));
		}catch(Exception e) {

		}
		return ResponseEntity.ok(jsonValue);
	}

	@GetMapping("order/deleteOrder/{id}")
	public ResponseEntity<?> deleteOrder(@PathVariable(value = "id") int id) {
		String jsonValue = null;
		try {
			Order order = orderService.getOrderById(id);
			order.setOrderStatus(Constants.INACTIVE_STATUS);
			orderRepo.save(order);
			if(Constants.INACTIVE_STATUS == orderService.getOrderById(id).getOrderStatus()) {
				jsonValue = orderBO.setDeleteOperationStatus(true);
			}else {
				jsonValue = orderBO.setDeleteOperationStatus(false);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.ok(jsonValue);
		}
		return ResponseEntity.ok(jsonValue);
	}

	@GetMapping("order/editOrder/{id}")
	public ResponseEntity<?> editOrder(@PathVariable(value = "id") int id,Model model) {
		String jsonValue = null;
		try {			
			Order order = this.orderService.getOrderById(id);
			List<Order> OrderDetails = new ArrayList<Order>();
			OrderDetails.add(order);
			jsonValue = orderBO.parseFetchOrder(OrderDetails);
			model.addAttribute(Constants.EDIT_ORDER_FORM, order);	
		}catch(Exception e) {

		}
		return ResponseEntity.ok(jsonValue);
	}

	@PostMapping("order/editOrder/{id}")
	public String updateOrder(@PathVariable(value = "id") int id,@ModelAttribute(Constants.EDIT_ORDER_FORM) Order order,BindingResult bindingResult,HttpServletRequest request,Model model) {
		try {
			int key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			Master master = masterRepo.findByMasterId(key);
			order.setOrganization(master);
			int productId = Integer.parseInt(order.getProduct().getProductName().split(Constants.COMMA)[0]);
			Product product = productService.getProductById(productId);
			order.setProduct(product);
			Supplier supplier = supplierService.getSupplierById(order.getSupplier().getSupplierId());
			order.setSupplier(supplier);
			order.setOrderId(id);		
			float costPrice = Float.parseFloat(order.getCostPrice());
			float orderTotal = costPrice * order.getQuantity();
			order.setOrderTotal(orderTotal);
			order.setOrderStatus(Constants.ACTIVE_STATUS);		
			if(order.getOrderDeliveryStatus().equalsIgnoreCase(Constants.ORDER_STATUS_SELECT)) {
				order.setOrderDeliveryStatus(Constants.ORDER_STATUS_BOOKED);
			}
			orderRepo.save(order);
			if(order.getOrderDeliveryStatus().equalsIgnoreCase(Constants.ORDER_STATUS_RECEIVED)) {
				Stock stock = new Stock();
				stock.setOrder(order);
				stock.setOrganization(master);
				stock.setProduct(product);
				stock.setSupplier(supplier);
				stock.setStockQuantity(order.getQuantity());
				stockRepo.save(stock);
			}
		}catch(Exception e) {

		}
		model.addAttribute(Constants.ORDER_FORM, new Order());
		model.addAttribute(Constants.EDIT_ORDER_FORM, new Order());
		return Constants.REDIRECT+Constants.INVENTORY_FOLDER + Constants.FORWARD_SLASH +Constants.ORDERS_JSP;
	}


}
