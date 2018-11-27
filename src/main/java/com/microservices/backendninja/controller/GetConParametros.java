package com.microservices.backendninja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/example2")
public class GetConParametros {
	
	private static final String EXAMPLE_VIEW = "example2";
	
	
	/**
	 * PRIMERA FORMA DE HACER GET
	 */
	//localhost:8090/example2/request1?nm=JOHN
	//localhost:8090/example2/request1?nm=MICHAEL
	
	//Recibimos un parametro por la request parametrizado, 
	//nm es el nombre del puntero en la request..., ylo bajamos a la vista
	//required a false indica que el parametro no es obligatorio
	@RequestMapping("/request1")
	public ModelAndView request1(@RequestParam(name="nm",required=false, defaultValue="NULL") String name){
		ModelAndView model = new ModelAndView(EXAMPLE_VIEW);
		model.addObject("nm_in_model", name );
		return model;
	}
	
	/**
	 * SEGUNDA FORMA DE HACER GET
	 * Metemos el parametro en el requestmapping y luego aparte
	 * declaramos en los parametros de entrada que es un variable del path
	 */
	//localhost:8090/example2/request1/JOHN
	@RequestMapping("/request2/{nm}")
	public ModelAndView request2(@PathVariable("nm") String name) {
		ModelAndView model = new ModelAndView(EXAMPLE_VIEW);
		model.addObject("nm_in_model", name );
		return model;
	}

}
