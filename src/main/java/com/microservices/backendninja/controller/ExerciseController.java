package com.microservices.backendninja.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.microservices.backendninja.services.ExerciseService;

@Controller
@RequestMapping("ejercicio/")
public class ExerciseController {
	
	private static final Log LOG = LogFactory.getLog(ExerciseController.class);

	private static final String BASEPATH = "http://localhost:8030/ejercicio/";
	
	@Autowired
	@Qualifier("exerciseServiceImpl")
	private ExerciseService service;
	
	@GetMapping("uno")
	public RedirectView printLog() {
		return new RedirectView(BASEPATH + "dos");
	}
	@GetMapping("dos")
	public ModelAndView printLog(Model model) {
		service.printMessage();
		model.addAttribute("mensaje", "Ejercicio Resuelto");
		return new ModelAndView("exerciseView");
	}

}
