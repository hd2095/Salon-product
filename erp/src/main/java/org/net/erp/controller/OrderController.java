package org.net.erp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.net.erp.bo.OrderBO;
import org.net.erp.model.Master;
import org.net.erp.model.Order;
import org.net.erp.model.OrderDetails;
import org.net.erp.model.Product;
import org.net.erp.model.SaleDetails;
import org.net.erp.model.Stock;
import org.net.erp.model.Supplier;
import org.net.erp.repository.MasterRepository;
import org.net.erp.repository.OrderDetailsRepository;
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
@RequestMapping("buy")
public class OrderController {

	@Autowired
	private MasterRepository masterRepo;

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private OrderDetailsRepository orderDetailsRepo;

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
	public String showOrderPage() {
		try {

		}catch(Exception e) {

		}
		return Constants.DISPLAY_FOLDER + Constants.FORWARD_SLASH +Constants.ORDER_JSP;
	}

	@GetMapping("/createOrder")
	public String showCreateOrderPage(Model model) {
		try {
			model.addAttribute(Constants.ORDER_FORM, new Order());
		}catch(Exception e) {

		}
		return Constants.FORM_FOLDER + Constants.FORWARD_SLASH +Constants.NEW_ORDER_FORM;
	}

	@PostMapping("/createOrder")
	public String handleOrderForm(@Valid @ModelAttribute(Constants.ORDER_FORM) Order order,BindingResult bindingResult,HttpServletRequest request,Model model) {
		try {
			if(!bindingResult.hasErrors()) {
				int master_id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
				String totalElements = request.getParameter("total_elements");
				int repeaterCount = 0;
				if(null == totalElements || Constants.EMPTY.equalsIgnoreCase(totalElements) || Integer.parseInt(totalElements) == 0) {
					repeaterCount = 0;
				}else {
					repeaterCount = Integer.parseInt(totalElements);
				}
				for(int i  = 0;i <= repeaterCount; i++) {	
					String product = request.getParameter("["+ i +"][order_product]");
					String supplier = request.getParameter("["+ i +"][product_supplier]");		
					String productCost = request.getParameter("["+ i +"][product_cost]");
					String productQuantity = request.getParameter("["+ i +"][product_quantity]");	
					String productBrand = request.getParameter("["+ i +"][product_brand]");
					if(i == 0) {
						Master master = masterRepo.findByMasterId(master_id);
						order.setOrganization(master);
						order.setOrderDeliveryStatus(Constants.ORDER_STATUS_BOOKED);
						order.setOrderStatus(Constants.ACTIVE_STATUS);
						orderRepo.saveAndFlush(order);
					}
					OrderDetails orderDetails = new OrderDetails();
					int product_id = Integer.parseInt(product);
					int supplier_id = Integer.parseInt(supplier);
					Product productObj = productService.getProductById(product_id);
					orderDetails.setProduct(productObj);
					Supplier supplierObj = supplierService.getSupplierById(supplier_id);
					orderDetails.setSupplier(supplierObj);
					orderDetails.setOrder(order);
					orderDetails.setProductBrand(productBrand);
					orderDetails.setProductQuantity(Integer.parseInt(productQuantity));
					orderDetails.setOrderDeleteStatus(Constants.ACTIVE_STATUS);
					orderDetails.setProductCostPrice(Float.parseFloat(productCost));
					orderDetails.setProductDeliveryStatus(Constants.PRODUCT_STATUS_BOOKED);
					orderDetailsRepo.save(orderDetails);
				}	
			}			
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

	@RequestMapping("/getOrderDetails/{id}")
	public ResponseEntity<?> getOrderDetails(@PathVariable(value = "id") int id) {
		String jsonValue = null;
		try {
			List<OrderDetails> orderDetails = orderDetailsRepo.findByOrderId(id);
			jsonValue = orderBO.parseFetchOrderDetails(orderDetails);
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
			if(order.getOrderDeliveryStatus().equalsIgnoreCase(Constants.ORDER_STATUS_RECEIVED)) {
				List<OrderDetails> allOrderDetails =  orderDetailsRepo.findByOrderId(order.getOrderId());
				for(OrderDetails orderDetails : allOrderDetails) {
					orderDetails.setProductDeliveryStatus(Constants.PRODUCT_STATUS_DELETED);
					orderDetails.setOrderDeleteStatus(Constants.INACTIVE_STATUS);
					Stock stock = stockRepo.findByProductId(orderDetails.getProduct().getProductId());
					stock.setStockQuantity(stock.getStockQuantity() - orderDetails.getProductQuantity());
					stock.setLastUpdatedDate(new Date());
					stockRepo.save(stock);
					orderDetailsRepo.save(orderDetails);
				}
			}
			order.setOrderDeliveryStatus(Constants.ORDER_STATUS_DELETED);
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

	@RequestMapping("/deleteProductFromOrder/{id}")
	public ResponseEntity<?> deleteProductFromOrder(@PathVariable(value = "id") int id,HttpServletRequest request) {
		String jsonValue = null;
		try {
			OrderDetails productToDelete = orderDetailsRepo.findByOrderDetailsId(id);
			productToDelete.setOrderDeleteStatus(Constants.INACTIVE_STATUS);
			if(productToDelete.getProductDeliveryStatus().equalsIgnoreCase(Constants.PRODUCT_STATUS_RECEIVED)) {
				Stock stock = stockRepo.findByProductId(productToDelete.getProduct().getProductId());
				stock.setStockQuantity(stock.getStockQuantity() - productToDelete.getProductQuantity());
				stock.setLastUpdatedDate(new Date());
				stockRepo.save(stock);
			}
			productToDelete.setProductDeliveryStatus(Constants.PRODUCT_STATUS_DELETED);
			orderDetailsRepo.save(productToDelete);
			if(Constants.INACTIVE_STATUS == orderDetailsRepo.findByOrderDetailsId(id).getOrderDeleteStatus()) {
				jsonValue = orderBO.setDeleteOperationStatus(true);
			}else {
				jsonValue = orderBO.setDeleteOperationStatus(false);
			}
		}catch(Exception e) {

		}
		return ResponseEntity.ok(jsonValue);
	}

	@RequestMapping("/recieveProductFromOrder/{id}")
	public ResponseEntity<?> recieveProductFromOrder(@PathVariable(value = "id") int id,HttpServletRequest request){
		String jsonValue = null;
		try {
			String isFinalProduct = request.getParameter("isFinalProduct");
			OrderDetails orderDetails = orderDetailsRepo.findByOrderDetailsId(id);
			if(!orderDetails.getProductDeliveryStatus().equalsIgnoreCase(Constants.PRODUCT_STATUS_RECEIVED)) {
				orderDetails.setProductDeliveryStatus(Constants.PRODUCT_STATUS_RECEIVED);
				orderDetailsRepo.save(orderDetails);
				Order order = orderRepo.findByOrderId(orderDetails.getOrder().getOrderId());
				if(null != isFinalProduct && Constants.STRING_TRUE.equalsIgnoreCase(isFinalProduct)) {
					order.setOrderDeliveryStatus(Constants.ORDER_STATUS_RECEIVED);
					order.setOrderReceivedDate(new Date());
				}else {
					order.setOrderDeliveryStatus(Constants.ORDER_STATUS_INTRANSIT);	
				}				
				orderRepo.save(order);
				Stock stock = stockRepo.findByProductId(orderDetails.getProduct().getProductId());
				if(null == stock) {
					stock = new Stock();						
					stock.setStockQuantity(orderDetails.getProductQuantity());
				}else {
					stock.setStockQuantity(stock.getStockQuantity() + orderDetails.getProductQuantity());
				}
				stock.setProduct(orderDetails.getProduct());
				stock.setOrganization(order.getOrganization());
				stock.setLastUpdatedDate(new Date());
				stockRepo.save(stock);
			}
			if(Constants.PRODUCT_STATUS_RECEIVED.equalsIgnoreCase(orderDetailsRepo.findByOrderDetailsId(id).getProductDeliveryStatus())) {
				jsonValue = orderBO.setDeleteOperationStatus(true);
			}else {
				jsonValue = orderBO.setDeleteOperationStatus(false);
			}
		}catch(Exception e) {

		}
		return ResponseEntity.ok(jsonValue);
	}

	@GetMapping("editOrder/{id}")
	public String editOrder(@PathVariable(value = "id") int id,Model model) {
		Order order = null;
		try {			
			order = this.orderService.getOrderById(id);
			List<Order> OrderDetails = new ArrayList<Order>();
			OrderDetails.add(order);
			model.addAttribute(Constants.EDIT_ORDER_FORM, order);	
		}catch(Exception e) {

		}
		if(null != order.getOrderDeliveryStatus() && !Constants.ORDER_STATUS_RECEIVED.equalsIgnoreCase(order.getOrderDeliveryStatus())) {
			return Constants.FORM_FOLDER + Constants.FORWARD_SLASH +Constants.EDIT_ORDER_FORM_JSP;	
		}else {
			return Constants.REDIRECT_ORDER_JSP;
		}		 
	}

	@PostMapping("/editOrder/{id}")
	public String updateOrder(@PathVariable(value = "id") int id,@ModelAttribute(Constants.EDIT_ORDER_FORM) Order order,BindingResult bindingResult,HttpServletRequest request,Model model) {
		boolean isOrderComplete = false;
		boolean isOrderCancelled = false;
		Master master = null;
		try {
			int master_id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			String totalElements = request.getParameter("edit_total_elements");
			int repeaterCount = 0;
			if(null == totalElements || Constants.EMPTY.equalsIgnoreCase(totalElements) || Integer.parseInt(totalElements) == 0) {
				repeaterCount = 0;
			}else {
				repeaterCount = Integer.parseInt(totalElements);
			}
			for(int i  = 0;i <= repeaterCount; i++) {	
				String product = request.getParameter("["+ i +"][edit_order_product]");
				String supplier = request.getParameter("["+ i +"][edit_product_supplier]");		
				String productCost = request.getParameter("["+ i +"][edit_product_cost]");
				String productQuantity = request.getParameter("["+ i +"][edit_product_quantity]");	
				String productBrand = request.getParameter("["+ i +"][edit_product_brand]");
				String recordId = request.getParameter("["+ i +"][edit_order_record_id]");
				if(i == 0) {
					master = masterRepo.findByMasterId(master_id);
					order.setOrganization(master);
					order.setOrderStatus(Constants.ACTIVE_STATUS);
					if(null != order.getOrderDeliveryStatus() && order.getOrderDeliveryStatus().equalsIgnoreCase(Constants.ORDER_STATUS_RECEIVED)) {
						order.setOrderDeliveryStatus(Constants.ORDER_STATUS_RECEIVED);
						isOrderComplete = true;
					}else if(null == order.getOrderDeliveryStatus() || order.getOrderDeliveryStatus().equalsIgnoreCase(Constants.ORDER_STATUS_SELECT)){
						order.setOrderDeliveryStatus(Constants.ORDER_STATUS_BOOKED);
					}else if(Constants.ORDER_STATUS_DELETED.equalsIgnoreCase(order.getOrderDeliveryStatus())) {
						order.setOrderStatus(Constants.INACTIVE_STATUS);
						isOrderCancelled = true;
					}										
					orderRepo.saveAndFlush(order);
				}
				OrderDetails orderDetails = null; 
				if(null != recordId && !Constants.EMPTY.equalsIgnoreCase(recordId)) {
					orderDetails = orderDetailsRepo.findByOrderDetailsId(Integer.parseInt(recordId));
				}						
				int product_id = 0;
				Product productObj = null;
				if(null == orderDetails) {
					orderDetails = new OrderDetails();
					product_id = Integer.parseInt(product);
					int supplier_id = Integer.parseInt(supplier);
					productObj = productService.getProductById(product_id);
					orderDetails.setProduct(productObj);
					Supplier supplierObj = supplierService.getSupplierById(supplier_id);
					orderDetails.setSupplier(supplierObj);
					orderDetails.setOrder(order);
					orderDetails.setProductBrand(productBrand);
					orderDetails.setProductQuantity(Integer.parseInt(productQuantity));
					orderDetails.setOrderDeleteStatus(Constants.ACTIVE_STATUS);
					orderDetails.setProductCostPrice(Float.parseFloat(productCost));
					orderDetails.setProductDeliveryStatus(Constants.PRODUCT_STATUS_BOOKED);
				}
				if(isOrderComplete) {
					if(!orderDetails.getProductDeliveryStatus().equalsIgnoreCase(Constants.PRODUCT_STATUS_RECEIVED)) {
						orderDetails.setProductDeliveryStatus(Constants.PRODUCT_STATUS_RECEIVED);
						Stock stock = stockRepo.findByProductId(product_id);
						if(null == stock) {
							stock = new Stock();						
							stock.setStockQuantity(orderDetails.getProductQuantity());
							stock.setProduct(orderDetails.getProduct());
							stock.setOrganization(master);
						}else {
							stock.setStockQuantity(stock.getStockQuantity() + orderDetails.getProductQuantity());
						}
						stock.setLastUpdatedDate(new Date());
						stockRepo.save(stock);	
					}					
				}else if(isOrderCancelled) {
					orderDetails.setOrderDeleteStatus(Constants.INACTIVE_STATUS);
					orderDetails.setProductDeliveryStatus(Constants.PRODUCT_STATUS_DELETED);
					if(orderDetails.getProductDeliveryStatus().equalsIgnoreCase(Constants.PRODUCT_STATUS_RECEIVED)) {
						Stock stock = stockRepo.findByProductId(product_id);
						stock.setLastUpdatedDate(new Date());
						stock.setStockQuantity(stock.getStockQuantity() - orderDetails.getProductQuantity());
						stockRepo.save(stock);						
					}
				}else {
					if(!orderDetails.getProductDeliveryStatus().equalsIgnoreCase(Constants.PRODUCT_STATUS_RECEIVED)) {
						orderDetails.setProductDeliveryStatus(Constants.PRODUCT_STATUS_BOOKED);
					}
				}				
				orderDetailsRepo.save(orderDetails);
			}
		}catch(Exception e) {

		}
		model.addAttribute(Constants.ORDER_FORM, new Order());
		model.addAttribute(Constants.EDIT_ORDER_FORM, new Order());
		return Constants.REDIRECT+Constants.BUY_FOLDER + Constants.FORWARD_SLASH + Constants.ORDERS_JSP;
	}


}
