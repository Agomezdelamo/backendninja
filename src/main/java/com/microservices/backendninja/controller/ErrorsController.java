package com.microservices.backendninja.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//controlador que maneja los errores en spring
//Captura las excepciones en los controladores
@ControllerAdvice
public class ErrorsController {
	
	public static final String INTERNALSERVERERROR = "error/500";
	
	//indicamos que excepciones captura este metodo
	@ExceptionHandler(Exception.class)
	public String showInternalServerError(Exception ex, Model model) {
		
		model.addAttribute("errormsg", ex.getMessage());
		//indicamos el path al que se redirige si se produce esta excepcion
		return INTERNALSERVERERROR;
	}

}
