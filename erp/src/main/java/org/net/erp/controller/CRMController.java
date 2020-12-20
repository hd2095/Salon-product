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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(CRMController.class);

	@GetMapping("/sms")
	public String showSmsPage(HttpServletRequest request,Model model) {
		int key = 0;
		try {
			key = (int)request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			Master master = this.masterRepo.findByMasterId(key);
			List<MessagesSent> messageSent = this.messagesSentRepository.findByMasterId(key);
			int totalMessagesSent = 0;
			int totalMessages = 0;
			double percentUsed = 0;
			for(MessagesSent temp : messageSent) {
				totalMessagesSent += temp.getMessageCount();
			}
			if(master.getOrganizationPlan().equalsIgnoreCase(Constants.ORG_PLAN_BASIC)){
				totalMessages = 25;
				model.addAttribute(Constants.TOTAL_MESSAGES,totalMessages);
				if(totalMessagesSent < totalMessages) {
					model.addAttribute(Constants.SHOW_SEND_BUTTON,true);	
				}				
			}else if(master.getOrganizationPlan().equalsIgnoreCase(Constants.ORG_PLAN_STANDARD)) {        	
				totalMessages = 500;
				model.addAttribute(Constants.TOTAL_MESSAGES,totalMessages);
				if(totalMessagesSent < totalMessages) {
					model.addAttribute(Constants.SHOW_SEND_BUTTON,true);	
				}
			}else {
				totalMessages = 2000;
				model.addAttribute(Constants.SHOW_PREMIUM_MSGS, true);
				model.addAttribute(Constants.TOTAL_MESSAGES,totalMessages);
				if(totalMessagesSent < totalMessages) {
					model.addAttribute(Constants.SHOW_SEND_BUTTON,true);	
				}
			}
			if(totalMessagesSent > 0) {
				double totalMessagesD = totalMessages;
				percentUsed = (totalMessagesSent/totalMessagesD) * 100;
			}
			model.addAttribute(Constants.PERCENT_USED, percentUsed);
			model.addAttribute(Constants.TOTAL_MESSAGES_SENT, totalMessagesSent);
			model.addAttribute(Constants.TOTAL_MESSAGES_LEFT, totalMessages - totalMessagesSent);
			model.addAttribute(Constants.ORG_NAME,master.getOrganizationName());	
		}catch(Exception e) {
			LOGGER.error("Exception in showSmsPage for organization id :: "+key+" :: "+e.getMessage());
		}			
		return Constants.VIEW_SMS_PAGE;
	}

	@PostMapping("/sms")
	public String sendSms(RedirectAttributes ra,HttpServletRequest request) {
		@SuppressWarnings("unused")
		int messageSentCounter = 0;
		//int failureMessageCount = 0;
		int key = 0;
		try {
			ArrayList<String> clientNumber = new ArrayList<String>();
			//ArrayList<String> unsentMsg = new ArrayList<String>();
			key = (int)request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			Master master = this.masterRepo.findByMasterId(key);
			String messageContents = request.getParameter(Constants.REQUEST_MSG_TO_SEND);
			String [] clientIds = request.getParameterValues(Constants.REQUEST_CRM_SMS_CLIENTS);	
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
				} /*
					 else { failureMessageCount++; unsentMsg.add(temp); }
				 */				
			}		
			//if(messageSentCounter == clientIds.length) {
			ra.addFlashAttribute("messageSent","message(s) sent successfully.");
			messagesSentRepository.save(msgSent);
			/*			}else {
				if(failureMessageCount > 0) {
					msgSent.setMessageCount(clientIds.length - failureMessageCount);
					String unsentNumbers = "";
					for(String temp : unsentMsg) {
						unsentNumbers += " " +temp;
					}
					ra.addFlashAttribute("messageNotSent","some problem in sending message(s) to "+ unsentNumbers  +" kindly try again later.");
					messagesSentRepository.save(msgSent);
				}else {
					ra.addFlashAttribute("messageNotSent","some problem in sending message(s) kindly try again later.");
				}
			}*/
		}catch(Exception e) {
			LOGGER.error("Exception in sendSms for organization id :: "+key+" :: "+e.getMessage());
		}
		return "redirect:/crm/sms";
	}
}
