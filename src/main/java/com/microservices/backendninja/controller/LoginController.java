package com.microservices.backendninja.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.microservices.backendninja.constants.ViewConstants;
import com.microservices.backendninja.model.UserCredentials;

@Controller
public class LoginController {
	
	private static final Log LOG = LogFactory.getLog(LoginController.class); 
	
	@GetMapping("/")
	public String redirectToLogin(){
		LOG.info("METHOD: redirectToLogin()");
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String showLoginForm(Model model, 
			@RequestParam(name="error", required=false) String error,
			@RequestParam(name="logout", required=false) String logout
			
			){
		
		LOG.info("METHOD: showLoginForm() PARAMS -> error = " + error + " logout = " + logout);

		model.addAttribute("logout", logout);
		model.addAttribute("error", error);
		model.addAttribute("userCredentials", new UserCredentials());
		LOG.info("returning to login view");
		return ViewConstants.LOGIN;
	}
	
	//recibe un atributo del modelo de tipo model attribute
	@PostMapping
	public String loginCheck(@ModelAttribute(name="userCredentials") UserCredentials userPass){
		LOG.info("METHOD: loginCheck() PARAMS -> userCredentials " + userPass.toString());

		if(userPass.getUser().equals("user") && userPass.getPass().equals("pass")) {
			LOG.info("returning to contacts view");
			return ViewConstants.REDIRECT + ViewConstants.CONTACTS + "/" + ViewConstants.SHOW_CONTACTS;			

		} else {
			LOG.info("redirect to login error");
			return "redirect:/login?error";

		}
	}
}
