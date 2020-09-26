package org.net.erp.controller;

import java.security.SecureRandom;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.net.erp.bo.BaseBO;
import org.net.erp.model.LoginMember;
import org.net.erp.model.RegisterMember;
import org.net.erp.model.RegisterOrganization;
import org.net.erp.model.SignUpOtp;
import org.net.erp.repository.RegisterOrganizationRepository;
import org.net.erp.repository.SignUpOtpRepository;
import org.net.erp.services.RegisterMemberService;
import org.net.erp.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private RegisterMemberService registerMemberService;

	@Autowired
	private RegisterOrganizationRepository registerOrganizationRepository;

	@Autowired
	private SignUpOtpRepository signUpOtpRepository;

	@Autowired
	BaseBO baseBO;
	
	@GetMapping("/login")
	public String showErrorLoginPage(@RequestParam(value = "userNotFound", required = false) String userNotFound,@RequestParam(value = "userExpired", required = false) String userExpired,@RequestParam(value = "error", required = false) String error,Model model,HttpServletRequest request) {
		String errorMessge = null;
		if(error != null) {
			errorMessge = "Username or Password is incorrect !!";
		}
		if(null != userExpired) {
			errorMessge = "Membership Expired !!";
		}
		if(null != userNotFound) {
			errorMessge = "User name not found !!";
		}
		model.addAttribute(Constants.LOGIN_MEMBER, new LoginMember());
		model.addAttribute("errorMessge", errorMessge);
		return Constants.LOGIN_JSP;
	}

	@PostMapping("/login")
	public String handleLogin(@Valid @ModelAttribute(Constants.LOGIN_MEMBER) LoginMember loginMember,BindingResult bindingResult,HttpServletRequest request) {
		RegisterMember registerMember = null;
		if(bindingResult.hasErrors()) {
			return Constants.LOGIN_JSP;	
		}else {
			if(loginMember.getUsername().contains(Constants.AT) && loginMember.getUsername().contains(Constants.DOT)) {
				registerMember = registerMemberService.findUserByEmail(loginMember.getUsername());
			}else {
				registerMember = registerMemberService.findUserByMobileNumber(loginMember.getUsername());	
			}	 
			if(registerMember != null) {
				if(bCryptPasswordEncoder.matches(loginMember.getPassword(), registerMember.getMemberPassword())) {
					request.getSession().setAttribute(Constants.SESSION_FIRSTNAME,registerMember.getFirst_name());
					request.getSession().setAttribute(Constants.SESSION_ORGANIZATION_KEY,registerMember.getRegisterOrganization().getMaster_id());
					return Constants.DASHBOARD_JSP;
				}
				else {
					bindingResult.rejectValue(Constants.PASSWORD, null, Constants.INCORRECT_MEMBER_PASSWORD);
					return Constants.LOGIN_JSP;
				}
			}else {
				bindingResult.rejectValue(Constants.USERNAME, null, Constants.MEMBER_NOT_FOUND);
				return Constants.LOGIN_JSP;
			}    		
		}	
	}

	@GetMapping("/invalidate")
	public String destroySession(HttpServletRequest request) {
		request.getSession().invalidate();
		return Constants.REDIRECT;
	}

	@PostMapping("/forgotPassword")
	public String forgotPassword(HttpServletRequest request,Model model,RedirectAttributes ra) {
		try {
			String clientNumber = request.getParameter("mobileNumber");
			RegisterMember member = registerMemberService.findUserByMobileNumber(clientNumber);
			if(null == member) {
				ra.addFlashAttribute("invalidUser", "No user registered with number :: "+clientNumber); 
			}else {			
				int len = 8;
				int randNumOrigin = 48, randNumBound = 122;
				String newPassword = generateRandomPasswordOrOtp(len, randNumOrigin, randNumBound, false);
				System.out.print(newPassword);
				member.setMemberPassword(bCryptPasswordEncoder.encode(newPassword));
				registerMemberService.save(member);
				boolean isSuccess = baseBO.sendMessage("Dear user, Kindly login to Grokar https://www.grokar.in/login using new password "+newPassword,clientNumber);
				if(isSuccess) {
					ra.addFlashAttribute("OtpSentSuccessFully", "New password sent to registered mobile number :: "+clientNumber);
				}else {
					ra.addFlashAttribute("OtpSendFailure","Unable to send message at the moment please try again later");					
				}				
			}	
		}catch(Exception e) {
			System.out.print("exception in  forgotPassword :: "+e.getMessage());
		}	
		model.addAttribute(Constants.LOGIN_MEMBER, new LoginMember());
		return "redirect:/login";
	}

	@PostMapping("/signup")
	public String signUp(HttpServletRequest request,RedirectAttributes ra,Model model) {
		try {
			String fullName = request.getParameter("fullname");
			String firstName = null;
			String lastName = null;
			if(fullName.contains(" ")) {
				firstName = fullName.split(" ")[0];
				lastName = fullName.split(" ")[1];
			}
			String mobileNumber = request.getParameter("mobileNumber");
			if(registerMemberService.findUserByMobileNumber(mobileNumber) == null) {
				String email = request.getParameter("email");
				String password = request.getParameter("password");		
				RegisterMember member = new RegisterMember();
				member.setCreated_on(new Date());
				member.setEmailId(email);
				member.setFirst_name(firstName);
				member.setLast_name(lastName);
				member.setMember_type("user");
				member.setMobileNumber(mobileNumber);
				member.setMemberPassword(bCryptPasswordEncoder.encode(password));
				member.setVerified(false);
				registerMemberService.save(member);
				boolean isSuccess = true;//baseBO.sendMessage("Dear user, Kindly login to Grokar https://www.grokar.in/login using new password "+newPassword,clientNumber);
				if(isSuccess) {
					ra.addFlashAttribute("userCreatedSuccessfully", "Dear User,Your account has been created kindly login using your mail or mobile number");
				}else {
					ra.addFlashAttribute("userCreatedFailure","Dear User, Unable to create your account at the moment please try again later");
				}	
			}else {
				ra.addFlashAttribute("alreadySignedUp","Dear User, You have already signed up with us kindly login to make full use of Grokar");
			}
		}catch(Exception e) {
			System.out.print("exception in  signUp :: "+e.getMessage());
		}
		return "redirect:/login";
	}

	@PostMapping("/verify")
	public String verify(HttpServletRequest request,RedirectAttributes ra,Model model) {
		try {
			int memberId = (Integer) request.getSession().getAttribute(Constants.SESSION_MEMBERID);
			RegisterMember member = registerMemberService.findUserByClientId(memberId);
			String otp = request.getParameter("otp");
			SignUpOtp suo = signUpOtpRepository.findByNumber(member.getMobileNumber());
			if(suo.getOtp() == Integer.parseInt(otp)) {			
				member.setVerified(true);
				registerMemberService.save(member);
				return "complete-organization-registration";
			}else {
				ra.addAttribute("OtpDoesntMatch","Dear User, your OTP doesnt match");
			}
		}catch(Exception e) {
			System.out.print("exception in  verify :: "+e.getMessage());
		}
		return "redirect:/complete-registration";
	}
	
	@GetMapping("/complete-registration")
	public String CompleteRegistration(HttpServletRequest request,Model model) {
		String returnJsp = null;
		try {
			int memberId = (Integer) request.getSession().getAttribute(Constants.SESSION_MEMBERID);
			RegisterMember registerMember = registerMemberService.findUserByClientId(memberId);
			returnJsp = "complete-organization-registration";
			if(!registerMember.isVerified()) {
				String otp = generateRandomPasswordOrOtp(4,48,122,true);
				SignUpOtp suo = signUpOtpRepository.findByNumber(registerMember.getMobileNumber());
				if(null == suo) {
					suo =  new SignUpOtp();	
					suo.setMobileNumber(registerMember.getMobileNumber());
				}	
				suo.setOtp_date(new Date());
				suo.setOtp(Integer.parseInt(otp));
				signUpOtpRepository.save(suo);
				boolean isSuccess = baseBO.sendMessage("Dear user, Use "+  otp +" as your verification code on Grokar",registerMember.getMobileNumber());
				if(isSuccess) {
					model.addAttribute("OtpSendSuccess", "Dear User,OTP has been sent to your registered mobile number");
				}else {
					model.addAttribute("OtpSendFailure","Dear User,We are facing some problems in sending OTP to your registered mobile number. please try again later");
				}
				returnJsp = "complete-registration";
			}	
		}catch(Exception e) {
			System.out.print("exception in  CompleteRegistration :: "+e.getMessage());
		}
		return returnJsp;
	}
	
	@PostMapping("/createOrganization")
	public String createOrganization(HttpServletRequest request,Model model) {
		try {
			int memberId = (Integer) request.getSession().getAttribute(Constants.SESSION_MEMBERID);
			RegisterMember registerMember = registerMemberService.findUserByClientId(memberId);
			String orgName = request.getParameter("orgName");
			String organization_type = request.getParameter("organization_type");	
			String orgAddress = request.getParameter("orgAddress");
			String orgGstnNo = request.getParameter("orgGstnNo");	
			String orgGstnPercent = request.getParameter("orgGstnPercent");	
			RegisterOrganization ro = new RegisterOrganization();		
			ro.setGstn_no(orgGstnNo);
			ro.setInvoiceNo(1);
			ro.setOrganization_name(orgName);			
			ro.setOrgnization_address(orgAddress);
			ro.setPlan("Basic");
			if(null != orgGstnPercent && !"".equalsIgnoreCase(orgGstnPercent)) {
				ro.setGstn_percent(Integer.parseInt(orgGstnPercent));	
			}			
			registerOrganizationRepository.saveAndFlush(ro);
			request.getSession().setAttribute(Constants.SESSION_ORGANIZATION_KEY,ro.getMaster_id());
			registerMember.setRegisterOrganization(ro);
			registerMember.setMember_type(organization_type);
			registerMemberService.save(registerMember);
		}catch(Exception e) {
			System.out.print("exception in  createOrganization :: "+e.getMessage());
		}
		return "redirect:/dashboard";
	}
	
	private String generateRandomPasswordOrOtp(int len, int randNumOrigin, int randNumBound,boolean isOtp) {
		SecureRandom random = new SecureRandom();
		if(!isOtp) {
			return random.ints(randNumOrigin, randNumBound + 1)
					.filter(i -> Character.isAlphabetic(i) || Character.isDigit(i))
					.limit(len)
					.collect(StringBuilder::new, StringBuilder::appendCodePoint,
							StringBuilder::append)
					.toString();	
		}else {
			return random.ints(randNumOrigin, randNumBound + 1)
					.filter(i -> Character.isDigit(i))
					.limit(len)
					.collect(StringBuilder::new, StringBuilder::appendCodePoint,
							StringBuilder::append)
					.toString();
		}		
	}
}
