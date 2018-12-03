package com.microservices.backendninja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.microservices.backendninja.model.UserCredentials;

@Controller
public class LoginController {
	
	@GetMapping("/")
	public String redirectToLogin(){
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String showLoginForm(Model model, 
			@RequestParam(name="error", defaultValue="", required=false) String error,
			@RequestParam(name="logout", required=false) String logout
			
			){
		model.addAttribute("logout", logout);
		model.addAttribute("error", error);
		model.addAttribute("userCredentials", new UserCredentials());
		return "login";
	}
	
	//recibe un atributo del modelo de tipo model attribute
	@PostMapping
	public String loginCheck(@ModelAttribute(name="userCredentials") UserCredentials userPass){
		
		if(userPass.getUser().equals("user") && userPass.getPass().equals("pass")) {
			return "contacts";			
		} else {
			return "redirect:/login?error";
		}
	}
}
