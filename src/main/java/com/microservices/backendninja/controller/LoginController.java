package com.microservices.backendninja.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.microservices.backendninja.constants.ViewConstants;

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
		LOG.info("returning to login view");
		return ViewConstants.LOGIN;
	}
	
	//loginSucces o la barra / entran por aqui, OJO AQUI MAPEAMOS 2 PATH
	@GetMapping({"/loginSuccess", "/"})
	public String loginCheck(){
		LOG.info("METHOD: loginSuccess");
		LOG.info("returning to contacts view");
		return ViewConstants.REDIRECT + ViewConstants.CONTACTS + "/" + ViewConstants.SHOW_CONTACTS;			

	}
}
