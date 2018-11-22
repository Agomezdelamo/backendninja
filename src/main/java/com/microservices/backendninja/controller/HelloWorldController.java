package com.microservices.backendninja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/soy")
public class HelloWorldController {
	
	@GetMapping("/helloworld")
	public String helloWorld() {
		/**
		 * retornamos el nombre de la vista, 
		 * cuando el dispatcher intercepta el get de
		 * localhost:8080/soy/helloworld
		 * 
		 * ejecuta este metodo, y el string devuelto
		 * lo usa para buscar una vista q que se llame
		 * helloworld.html, o la extension tambien podra ser .jsp
		 * eso va por configuración de spring.
		 */ 
		return "helloworld";
	}
}
