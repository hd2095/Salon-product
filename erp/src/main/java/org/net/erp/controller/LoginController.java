package org.net.erp.controller;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
import org.net.erp.services.SecurityService;
import org.net.erp.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	private SecurityService securityService;

	@Autowired
	BaseBO baseBO;

	@GetMapping("/login")
	public String showErrorLoginPage(@RequestParam(value = "userNotFound", required = false) String userNotFound,@RequestParam(value = "userExpired", required = false) String userExpired,@RequestParam(value = "error", required = false) String error,Model model,HttpServletRequest request) {
		String errorMessge = null;
		if(error != null) {
			errorMessge = "Dear User, we received incorrect username or password. kindly enter credentials and try again.";
		}
		if(null != userExpired) {
			errorMessge = "Dear User, your membeship has expired. kindly contact us to renew.";
		}
		if(null != userNotFound) {
			errorMessge = "Dear User, we couldn\\'t find you. kindly signup with us to use OperateIN.";
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
		return Constants.REDIRECT + "login";
	}

	@PostMapping("/forgot-password")
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
				member.setMemberPassword(bCryptPasswordEncoder.encode(newPassword));
				registerMemberService.save(member);
				boolean isSuccess = baseBO.sendMessage("Dear user, Kindly login to OperateIN https://www.OperateIN.in/login using new password "+newPassword,clientNumber);
				if(isSuccess) {
					ra.addFlashAttribute("OtpSentSuccessFully", "New password sent to registered mobile number :: "+clientNumber);
				}else {
					model.addAttribute("OtpSendFailure","Unable to send message at the moment please try again later");
					return "forgotPassword";
				}				
			}	
		}catch(Exception e) {
			System.out.print("exception in  forgotPassword :: "+e.getMessage());
		}	
		model.addAttribute(Constants.LOGIN_MEMBER, new LoginMember());
		return "redirect:/login";
	}

	@GetMapping("/signup")
	public String showSignUpPage() {
		return "signup"; 	
	}

	@GetMapping("/forgot-password")
	public String showForgotPasswordPage() {
		return "forgotPassword"; 	
	}

	@GetMapping("/complete-organization-registration")
	public String showCreateOrgPage() {
		return "complete-organization-registration"; 	
	}

	@GetMapping("/resendOtp")
	public String resendOtp(HttpServletRequest request,Model model) {
		try {
			RegisterMember member = new RegisterMember();
			member = registerMemberService.findUserByClientId((int) request.getSession().getAttribute(Constants.SESSION_MEMBERID));
			String otp = generateRandomPasswordOrOtp(4,48,122,true);
			SignUpOtp suo = signUpOtpRepository.findByNumber(member.getMobileNumber());
			if(null == suo) {
				suo =  new SignUpOtp();	
				suo.setMobileNumber(member.getMobileNumber());
			}	
			suo.setOtp_date(new Date());
			suo.setOtp(Integer.parseInt(otp));
			signUpOtpRepository.save(suo);
			boolean isSuccess = baseBO.sendMessage("Dear user, Use "+  otp +" as your verification code on OperateIN",member.getMobileNumber());
			if(isSuccess) {
				model.addAttribute("OtpSendSuccess", "Dear User,OTP has been sent to your registered mobile number");
				return "complete-registration";
			}else {
				model.addAttribute("OtpSendFailure","Dear User,We are facing some problems in sending OTP to your registered mobile number. please try again later");
			}	
		}catch(Exception e) {
			
		}
		return "complete-registration"; 	
	}
	
	@PostMapping("/signup")
	public String signUp(HttpServletRequest request,RedirectAttributes ra,Model model) {
		try {
			String fullName = request.getParameter("fullname");
			String firstName = null;
			String lastName = null;
			fullName = fullName.trim();
			if(fullName.contains(Constants.SPACE)) {
				firstName = fullName.split(Constants.SPACE)[0];
				lastName = fullName.split(Constants.SPACE)[1];
			}else {
				firstName = fullName;
			}
			String mobileNumber = request.getParameter("mobileNumber");
			String email = request.getParameter("email");
			if(registerMemberService.findUserByMobileNumber(mobileNumber) == null || registerMemberService.findUserByEmail(email) == null) {				
				String password = request.getParameter("password");		
				RegisterMember member = new RegisterMember();
				member.setCreated_on(new Date());
				member.setEmailId(email);
				member.setFirst_name(firstName);
				member.setLast_name(lastName);
				member.setMember_type("user");
				member.setMobileNumber(mobileNumber);
				member.setMemberPassword(bCryptPasswordEncoder.encode(password));
				member.setExpires_on(LocalDate.now().plusMonths(1));
				member.setVerified(false);
				registerMemberService.save(member);			
				member = registerMemberService.findUserByMobileNumber(mobileNumber);
				if(null != member) {					
					request.getSession().setAttribute(Constants.SESSION_MEMBERID,member.getMember_id());
					request.getSession().setAttribute(Constants.SESSION_FIRSTNAME,firstName);
					if(null != lastName) {
						request.getSession().setAttribute(Constants.SESSION_LASTNAME,lastName);	
					}					
					String otp = generateRandomPasswordOrOtp(4,48,122,true);
					SignUpOtp suo = signUpOtpRepository.findByNumber(member.getMobileNumber());
					if(null == suo) {
						suo =  new SignUpOtp();	
						suo.setMobileNumber(member.getMobileNumber());
					}	
					suo.setOtp_date(new Date());
					suo.setOtp(Integer.parseInt(otp));
					signUpOtpRepository.save(suo);
					securityService.autoLogin(mobileNumber,bCryptPasswordEncoder.encode(password));
					boolean isSuccess = baseBO.sendMessage("Dear user, Use "+  otp +" as your verification code on OperateIN",member.getMobileNumber());
					if(isSuccess) {
						model.addAttribute("OtpSendSuccess", "Dear User,OTP has been sent to your registered mobile number");
						return "complete-registration";
					}else {
						model.addAttribute("OtpSendFailure","Dear User,We are facing some problems in sending OTP to your registered mobile number. please try again later");
					}
				}else {
					ra.addFlashAttribute("userCreatedFailure","Dear User, Unable to create your account at the moment please try again later");
				}	
			}else {
				model.addAttribute("alreadySignedUp","Dear User, You have already signed up with us kindly login to make full use of OperateIN");
				return "signup";
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
				return "redirect:/complete-organization-registration";
			}else {
				model.addAttribute("OtpDoesntMatch","Dear User, your OTP doesnt match");
				return "complete-registration";
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
				boolean isSuccess = baseBO.sendMessage("Dear user, Use "+  otp +" as your verification code on OperateIN",registerMember.getMobileNumber());
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
			String organization_type = "Salon/spa";	
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

	@GetMapping("/autoLogin/{user}")
	public String autoLogin(@PathVariable(value = "user") String user,HttpServletRequest request) {
		String flag = "failure";
		RegisterMember member = registerMemberService.findUserByClientId(Integer.parseInt(user));
		if(null != member && null != member.getRegisterOrganization().getOrganization_name()) {
			securityService.autoLogin(user,member.getMemberPassword());
			request.getSession().setAttribute(Constants.SESSION_FIRSTNAME,member.getFirst_name());
			request.getSession().setAttribute(Constants.SESSION_LASTNAME,member.getLast_name());		
			request.getSession().setAttribute(Constants.SESSION_MEMBERID,member.getMember_id());
			if(null != member.getRegisterOrganization()) {
				request.getSession().setAttribute(Constants.SESSION_ORGANIZATION_KEY,member.getRegisterOrganization().getMaster_id());
				flag = "success";	
			}
		}
		return flag;
	}

	@GetMapping("/getMemberExpiry")
	public ResponseEntity<?> getMemberExpiry(HttpServletRequest request) {
		long noOfDaysBetween = 0;
		try {
			int memberId = (Integer) request.getSession().getAttribute(Constants.SESSION_MEMBERID);
			RegisterMember registerMember = registerMemberService.findUserByClientId(memberId);
			LocalDate date = LocalDate.now();
			noOfDaysBetween = ChronoUnit.DAYS.between(date, registerMember.getExpires_on());			
		}catch(Exception e) {

		}		
		return (ResponseEntity<?>)ResponseEntity.ok(noOfDaysBetween);
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
