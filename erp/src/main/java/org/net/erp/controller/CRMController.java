package org.net.erp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.net.erp.bo.BaseBO;
import org.net.erp.model.Client;
import org.net.erp.model.Master;
import org.net.erp.model.MessagesSent;
import org.net.erp.repository.MasterRepository;
import org.net.erp.repository.MessagesSentRepository;
import org.net.erp.services.ClientService;
import org.net.erp.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/crm")
public class CRMController {

	@Autowired
	private MessagesSentRepository messagesSentRepository;
	
	@Autowired
	private MasterRepository masterRepo;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private BaseBO baseBO;
	
	@GetMapping("/sms")
	public String showSmsPage(HttpServletRequest request,Model model) {
		try {
			int key = (int)request.getSession().getAttribute("session_organization_key");
			Master master = this.masterRepo.findByMasterId(key);
			List<MessagesSent> messageSent = this.messagesSentRepository.findByMasterId(key);
			int totalMessagesSent = 0;
			int totalMessages = 0;
			double percentUsed = 0;
			for(MessagesSent temp : messageSent) {
				totalMessagesSent += temp.getMessageCount();
			}
			if(master.getOrganizationPlan().equalsIgnoreCase("Basic")){
				totalMessages = 50;
				model.addAttribute("totalMessages",totalMessages);
				if(totalMessagesSent < totalMessages) {
					model.addAttribute("showSendBtn",true);	
				}				
			}else if(master.getOrganizationPlan().equalsIgnoreCase("Standard")) {        	
				totalMessages = 500;
				model.addAttribute("totalMessages",totalMessages);
				if(totalMessagesSent < totalMessages) {
					model.addAttribute("showSendBtn",true);	
				}
			}else {
				totalMessages = 2000;
				model.addAttribute("totalMessages",totalMessages);
			}
			if(totalMessagesSent > 0) {
				double totalMessagesD = totalMessages;
				percentUsed = (totalMessagesSent/totalMessagesD) * 100;
				model.addAttribute("showSendBtn",true);
			}
			model.addAttribute("percentUsed", percentUsed);
			model.addAttribute("totalMessagesSent", totalMessagesSent);
			model.addAttribute("totalMessagesLeft", totalMessages - totalMessagesSent);
			model.addAttribute("organizationName",master.getOrganizationName());	
		}catch(Exception e) {

		}			
		return "crm/sms-page";
	}
	
	@PostMapping("/sms")
	public String sendSms(RedirectAttributes ra,HttpServletRequest request) {
		int messageSentCounter = 0;
		try {
			ArrayList<String> clientNumber = new ArrayList<String>();
			int key = (int)request.getSession().getAttribute("session_organization_key");
			Master master = this.masterRepo.findByMasterId(key);
			String messageContents = request.getParameter("message-to-send");
			String [] clientIds = request.getParameterValues("crm_sms_clients");	
			String messageSentTo = Constants.EMPTY;
			for(String temp : clientIds) {
				messageSentTo += temp + Constants.COMMA;
				Client client = clientService.getClientById(Integer.parseInt(temp));
				clientNumber.add(client.getMobileNumber());
			}
			messageSentTo = messageSentTo.substring(0, messageSentTo.length() - 1);
			if(messageContents.contains("Regards")) {
				messageContents = messageContents.replace("Regards", "\nRegards");
				messageContents = messageContents.replace(master.getOrganizationName(), "\n"+master.getOrganizationName());
			}
			MessagesSent msgSent = new MessagesSent();
			msgSent.setMaster(master);
			msgSent.setMessageContents(messageContents);	
			msgSent.setMessageSentTo(messageSentTo);
			msgSent.setMessageCount(clientIds.length);	
			for(String temp : clientNumber) {
				boolean isSuccess = baseBO.sendMessage(messageContents, temp);
				if(isSuccess) {
					messageSentCounter++;	
				}				
			}		
			if(messageSentCounter == clientIds.length) {
				ra.addFlashAttribute("messageSent","message(s) sent successfully.");
			}else {
				ra.addFlashAttribute("messageNotSent","some problem in sending message(s) kindly try again later.");
			}
			messagesSentRepository.save(msgSent);
		}catch(Exception e) {
			
		}
		return "redirect:/crm/sms";
	}
}
