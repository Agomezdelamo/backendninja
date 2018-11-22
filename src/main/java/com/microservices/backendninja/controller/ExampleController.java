package com.microservices.backendninja.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.microservices.backendninja.model.Person;

@Controller
@RequestMapping("/example")
public class ExampleController {
	
	public static final String EXAMPLE_VIEW = "example";
	
	/**
	 * Primera forma
	 * Metodo que retorna el nombre de la vista a pintar,
	 * responde a un get del navegador con el path indicado
	 * @return
	 */
	@RequestMapping(value="/exampleString", method=RequestMethod.GET)
	public String exampleString() {
		return EXAMPLE_VIEW;
	}
	
	/**
	 * Segunda forma
	 * Metodo que retorna un objeto model and view con el nombre de la vista
	 */
	@RequestMapping(value="/exampleMAV", method=RequestMethod.GET)
	public ModelAndView exampleMAV() {
		ModelAndView mav = new ModelAndView(EXAMPLE_VIEW);
		//añadimos una variable, que se llama igual que la de la vista
		// y le damos valor, de esta forma se pinta en la vista.
		mav.addObject("listaPersonas", getPeople());
		return mav;
	}
	
	/**
	 * Tercera forma
	 * Forma de spring 4
	 * 
	 * @param model Usando esta clase spring sabe
	 * Que cuando llamemos a esta vista tiene que inyectarnos un model,
	 * si esta vacio le hace un new.
	 */
	@GetMapping("/exampleSpring4")
	public String exampleSpring4(Model model ) {
		//damos valor a la variable name para pintarla en la vista
		//en el primer parametro decimos como se llama la variable en la vista
		//en el segundo el valor que tiene
		model.addAttribute("listaPersonas", getPeople());
		return EXAMPLE_VIEW;
	}
	
	//metodo que emula la recuperación de lista de bbdd
	public List<Person> getPeople() {
		List<Person> personList = new ArrayList<>();
		personList.add(new Person("juan",22));
		personList.add(new Person("Alberto",32));
		personList.add(new Person("pedro",42));
		personList.add(new Person("luis",12));
		
		return personList;
	}

}
