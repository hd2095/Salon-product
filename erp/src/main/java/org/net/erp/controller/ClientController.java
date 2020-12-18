package org.net.erp.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.net.erp.bo.ClientBO;
import org.net.erp.model.Client;
import org.net.erp.model.Master;
import org.net.erp.repository.ClientRepository;
import org.net.erp.repository.MasterRepository;
import org.net.erp.services.ClientService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("client")
public class ClientController {

	@Autowired
	private ClientRepository clientRepo;

	@Autowired
	private ClientService clientService;

	@Autowired
	private ClientBO clientBO;

	@Autowired
	private MasterRepository masterRepo;

	@GetMapping(Constants.EMPTY)
	public String showClientPage(Model model,HttpServletRequest request) {
		try {
			int id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			if(null != request.getParameter("add")) {
				if(request.getParameter("add").equalsIgnoreCase("")) {
					model.addAttribute(Constants.NEW_CLIENT_FROM_APPOINTMENT,"new");
				}else {
					model.addAttribute(Constants.NEW_CLIENT_FROM_APPOINTMENT,request.getParameter("add"));	
				}	
				if(null != request.getParameter("redirectTo")) {
					model.addAttribute("redirectTo",request.getParameter("redirectTo"));	
				}
			}else if(null != request.getParameter("showDetails")) {
				model.addAttribute("showClientDetails",request.getParameter("showDetails"));
			}
			Master master = masterRepo.findByMasterId(id);
			int entries = clientService.checkClientEntries(id);
			if(master.getOrganizationPlan().equalsIgnoreCase("Basic")) {
				if(entries < 25) {
					model.addAttribute("showAddBtn", true);
				}
			}else if(master.getOrganizationPlan().equalsIgnoreCase("Standard")) {
				if(entries < 500) {
					model.addAttribute("showAddBtn", true);
				}
			}
		}catch(Exception e) {
			System.out.println("Exception in showClientPage :: "+e.getMessage());
		}
		model.addAttribute(Constants.CLIENT_FORM, new Client());
		model.addAttribute(Constants.EDIT_CLIENT_FORM, new Client());
		return Constants.CLIENT_JSP;
	}

	@PostMapping(Constants.EMPTY)
	public String createClient(@Valid @ModelAttribute(Constants.CLIENT_FORM) Client client,RedirectAttributes ra,BindingResult bindingResult,HttpServletRequest request,Model model) {
		try {
			if(!bindingResult.hasErrors()) {			
				int key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
				Client existingClient = clientRepo.checkIfClientExists(key, client.getMobileNumber());
				if(null == existingClient) {
					if(null == client.getClientLoyaltyPoints()) {
						client.setClientLoyaltyPoints(new BigInteger(Constants.INACTIVE_STATUS_STR));
					}
					client.setClientVisits(Constants.INACTIVE_STATUS);
					Master master = masterRepo.findByMasterId(key);
					client.setRegisterOrganization(master);
					client.setClientStatus(Constants.ACTIVE_STATUS);
					clientRepo.saveAndFlush(client);		
					model.addAttribute(Constants.CLIENT_FORM, new Client());
				}else {
					String message = "Client "+existingClient.getFullName() + " has the same phone number "+client.getMobileNumber();
					ra.addFlashAttribute(Constants.EXISTING_CLIENT, message);
					if(null != request.getParameter("redirectTo")) {
						ra.addFlashAttribute("redirectTo",request.getParameter("redirectTo"));	
					}
					return "redirect:/"+Constants.CLIENT_JSP;	
				}
			}	
		}catch(Exception e) {
			System.out.println("Exception in createClient :: "+e.getMessage());
		}
		model.addAttribute(Constants.EDIT_CLIENT_FORM, new Client());
		if(null != request.getParameter("redirectTo")) {
			ra.addFlashAttribute("appointmentClient", client.getClientId());
			return "redirect:/appointment/add";	
		}
		return "redirect:/"+Constants.CLIENT_JSP;			
	}

	@RequestMapping("/getAllClients")
	public ResponseEntity<?> getAllClients(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String jsonValue = null;
		int orderByColumn = 0;
		List<Client> clients = null;
		String order = null;
		String draw = null;
		String searchParam = null;
		int id = 0;
		try {
			if(null != request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY)) {
				id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			}	
			searchParam = request.getParameter("search[value]");
			if(null != searchParam && !Constants.EMPTY.equalsIgnoreCase(searchParam)) {
				clients = clientRepo.findByClientName(id,searchParam);
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
								if(order.equalsIgnoreCase(Constants.SORT_DESC)) { 
									clients = clientRepo.sortByName(id);
								}else {
									clients = clientRepo.sortByNameAsc(id);	
								}
							}
						} else if(orderByColumn == 1) {
							if(null != order) {
								if(order.equalsIgnoreCase(Constants.SORT_DESC)) {
									clients = clientRepo.sortByClientNumber(id);	
								}else {
									clients = clientRepo.sortByClientNumberAsc(id);
								}
							}
						} else if(orderByColumn == 2) {
							if(null != order) {
								if(order.equalsIgnoreCase(Constants.SORT_DESC)) {
									clients = clientRepo.sortByEmail(id);  
								}else {
									clients = clientRepo.sortByEmailAsc(id);
								}
							}
						} else if(orderByColumn == 3) {
							if(null != order) {
								if(order.equalsIgnoreCase(Constants.SORT_DESC)) {
									clients = clientRepo.findByMasterId(id);  
								}else {
									clients = clientRepo.findByMasterIdAsc(id);
								}
							}
						}
					}else {
						clients = clientRepo.findByMasterId(id);
					}	
				}
			}
			jsonValue = clientBO.parseFetchClient(clients);
		}catch(Exception e) {
			System.out.println("Exception in getAllClients :: "+e.getMessage());
		}
		return ResponseEntity.ok(jsonValue);
	}

	@RequestMapping("/getClientsByRevenue")
	public ResponseEntity<?> getClientsByRevenue(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String jsonValue = null;
		if(null != request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY)) {
			int id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			jsonValue = clientBO.parseFetchClient(clientRepo.findByRevenue(id));
		}
		return ResponseEntity.ok(jsonValue);
	}

	@GetMapping("/deleteClient/{id}")
	public ResponseEntity<?> deleteClient(@PathVariable(value = "id") int id) {
		String jsonValue = null;
		try {
			Client client = clientService.getClientById(id);
			client.setClientStatus(Constants.INACTIVE_STATUS);
			clientService.save(client);
			if(Constants.INACTIVE_STATUS == clientService.getClientById(id).getClientStatus()) {
				jsonValue = clientBO.setDeleteOperationStatus(true);
			}else {
				jsonValue = clientBO.setDeleteOperationStatus(false);
			}
		}catch(Exception e) {
			return ResponseEntity.ok(jsonValue);
		}
		return ResponseEntity.ok(jsonValue);
	}

	@GetMapping("/editClient/{id}")
	public ResponseEntity<?> editClient(@PathVariable(value = "id") int id,Model model) {
		String jsonValue = null;
		Client client = null;
		try {
			client = this.clientService.getClientById(id);
			List<Client> clients = new ArrayList<Client>();
			clients.add(client);
			jsonValue = clientBO.parseFetchClient(clients);			
		}catch(Exception e) {

		}
		model.addAttribute(Constants.EDIT_CLIENT_FORM, client);
		return ResponseEntity.ok(jsonValue);
	}

	@GetMapping("/clientDetails/{id}")
	public ResponseEntity<?> clientDetails(@PathVariable(value = "id") int id,Model model) {
		String jsonValue = null;
		Client client = null;
		try {
			client = this.clientService.getClientById(id);
			List<Client> clients = new ArrayList<Client>();
			clients.add(client);
			jsonValue = clientBO.parseFetchClient(clients);			
		}catch(Exception e) {

		}
		model.addAttribute(Constants.EDIT_CLIENT_FORM, client);
		return ResponseEntity.ok(jsonValue);
	}

	@PostMapping("/editClient")
	public String updateClient(RedirectAttributes ra,@ModelAttribute(Constants.EDIT_CLIENT_FORM) Client client,BindingResult bindingResult,HttpServletRequest request,Model model) {
		try {
			if(!bindingResult.hasErrors()) {	
				int key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
				Client existingClient = clientRepo.checkIfClientExists(key, client.getMobileNumber());
				if(null == existingClient) {
					Master master = masterRepo.findByMasterId(key);
					Optional<Client> fetchedClient = clientRepo.findById(client.getClientId());
					if(fetchedClient.get().getRevenue_generated() > 0) {
						client.setRevenue_generated(fetchedClient.get().getRevenue_generated());
					}
					client.setRegisterOrganization(master);				
					client.setClientStatus(Constants.ACTIVE_STATUS);
					clientRepo.save(client);
					model.addAttribute(Constants.EDIT_CLIENT_FORM, new Client());
				}else {
					if(existingClient.getClientId() == client.getClientId()) {
						if(existingClient.getRevenue_generated() > 0) {
							client.setRevenue_generated(existingClient.getRevenue_generated());
						}
						if(existingClient.getClientVisits() > 0) {
							client.setClientVisits(existingClient.getClientVisits());
						}
						if(null != existingClient.getClientLastVisitedDate()) {
							client.setClientLastVisitedDate(existingClient.getClientLastVisitedDate());
						}
						client.setOrganization(existingClient.getOrganization());
						client.setClientStatus(Constants.ACTIVE_STATUS);
						clientRepo.save(client);
					}else {
						ra.addFlashAttribute("editClientExists", "Client "+ existingClient.getFullName() +" has the same phone number");
						ra.addFlashAttribute("editClientId", client.getClientId());
					}	
				}
			}
		}catch(Exception e) {

		}
		model.addAttribute(Constants.CLIENT_FORM, new Client());		
		return Constants.REDIRECT+Constants.CLIENT_JSP;
	}

	@RequestMapping("/filterClients/{param}")
	public ResponseEntity<?> filterClients(@PathVariable("param") String param,HttpServletRequest request,HttpServletResponse response) throws IOException {
		String jsonValue = null;
		if (null != request.getSession().getAttribute("session_organization_key")) {
			final int id = (int)request.getSession().getAttribute("session_organization_key");
			if(param.equalsIgnoreCase("birthday")) {
				jsonValue = this.clientBO.parseFetchClient(this.clientRepo.findByBirthday(id));	
			}else {
				jsonValue = this.clientBO.parseFetchClient(this.clientRepo.findByRevenue(id));
			}
		}
		return (ResponseEntity<?>)ResponseEntity.ok(jsonValue);
	}
}
