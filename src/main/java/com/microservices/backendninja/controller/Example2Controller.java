package com.microservices.backendninja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/example2")
public class Example2Controller {
	
	private static final String EXAMPLE_VIEW = "example2";
	
	//localhost:8090/example2/request1?nm=JONH
	//localhost:8090/example2/request1?nm=MICHAEL
	
	@RequestMapping("/request1")
	public ModelAndView request1(@RequestParam(name="nm")){
		ModelAndView model = new ModelAndView(EXAMPLE_VIEW);
		model.addObject("nm_in_model", name );
		return model;
	}
}
