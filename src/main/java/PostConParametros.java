package com.microservices.backendninja.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.microservices.backendninja.model.Person;

@Controller
@RequestMapping("/example3")
public class PostConParametros {
	
	public static final String FORMVIEW = "form";
	
	//Metodo que nos enseña el formlulario
	@GetMapping("/showForm")
	public String showForm(Model model) {
		model.addAttribute("person", new Person());
		return FORMVIEW;
	}
}
