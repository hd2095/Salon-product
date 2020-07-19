package org.net.erp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	public String showClientPage(Model model) {
		model.addAttribute(Constants.CLIENT_FORM, new Client());
		model.addAttribute(Constants.EDIT_CLIENT_FORM, new Client());
		return Constants.CLIENT_JSP;
	}

	@PostMapping(Constants.EMPTY)
	public String createClient(@Valid @ModelAttribute(Constants.CLIENT_FORM) Client client,BindingResult bindingResult,HttpServletRequest request,Model model) {
		try {
			if(!bindingResult.hasErrors()) {			
				int key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
				Master master = masterRepo.findByMasterId(key);
				client.setRegisterOrganization(master);
				if(null != client.getClient_end_date() && null != client.getClient_start_date()) {
					client.setMember(Constants.ACTIVE_STATUS);
				}
				client.setClientStatus(Constants.ACTIVE_STATUS);
				clientRepo.save(client);		
				model.addAttribute(Constants.CLIENT_FORM, new Client());
			}	
		}catch(Exception e) {

		}
		model.addAttribute(Constants.EDIT_CLIENT_FORM, new Client());
		return Constants.CLIENT_JSP;			
	}

	@RequestMapping("/getAllClients")
	public ResponseEntity<?> getAllClients(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String jsonValue = null;
		if(null != request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY)) {
			int id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			jsonValue = clientBO.parseFetchClient(clientRepo.findByMasterId(id));
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

	@PostMapping("/editClient/{id}")
	public String updateClient(@Valid @PathVariable(value = "id") int id,@ModelAttribute(Constants.EDIT_CLIENT_FORM) Client client,BindingResult bindingResult,HttpServletRequest request,Model model) {
		try {
			if(!bindingResult.hasErrors()) {	
				int key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
				Master master = masterRepo.findByMasterId(key);
				client.setRegisterOrganization(master);
				client.setClientId(id);
				if(null != client.getClient_end_date() && null != client.getClient_start_date()) {
					client.setMember(Constants.ACTIVE_STATUS);
				}
				client.setClientStatus(Constants.ACTIVE_STATUS);
				clientRepo.save(client);
				model.addAttribute(Constants.EDIT_CLIENT_FORM, new Client());
			}
		}catch(Exception e) {

		}
		model.addAttribute(Constants.CLIENT_FORM, new Client());		
		return Constants.REDIRECT+Constants.CLIENT_JSP;
	}

}
