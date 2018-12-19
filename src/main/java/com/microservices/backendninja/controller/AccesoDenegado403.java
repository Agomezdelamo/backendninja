package com.microservices.backendninja.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.microservices.backendninja.services.impl.UserServiceImpl;

@Controller
public class AccesoDenegado403 {
	
	@Autowired
	@Qualifier("userService")
	private UserServiceImpl userService;

	@GetMapping("/403")
	public ModelAndView redirectToAccessDeniedPage(Principal user) {
		
		ModelAndView model = new ModelAndView();
		model.addObject("rol", userService.loadUserByUsername(user.getName()).getAuthorities().toString()+ "y solo se permite al rol ADMIN acceder a esta página");
		model.setViewName("error/403");
		return model;
	}
}
