package com.microservices.backendninja.controller;


import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.microservices.backendninja.components.FirstComponent;
import com.microservices.backendninja.model.Person;

@Controller
@RequestMapping("/example3")
public class PostConParametros {
	
	public static final Log LOGGER = LogFactory.getLog(PostConParametros.class);
	public static final String BASEPATH = "example3";
	public static final String FORMVIEW = "_form";
	public static final String RESULTVIEW = "_result";
	
	//INYECTAMOS UN COMPONENTE
	@Autowired
	@Qualifier("exampleComponent")
	private FirstComponent componenteInyectado;
	
	
	//Metodo redirecciona peticiones get
	//#1
	/* lo comentamos porque no puede haber2 mapping al mismo path
	@GetMapping("/")
	public String redirect() {
		return "redirect:/"+ BASEPATH + "/" + FORMVIEW;
	}
	*/
	
	//Metodo redirecciona peticiones get
	//#2
	@GetMapping("/")
	public RedirectView reV() { 
		return new RedirectView("/" + BASEPATH + "/" + FORMVIEW);
	}
	
	
	//Metodo ejemplo de autowired
	@GetMapping("/showcomponent")
	public String mostrarComponente(Model model) { 
		//inyectamose el componente con el valor de @component
		//pero usamos su clase padre component para tiparlo
		//por eso ahora casteamos a firstcomponent para decir hola
		//no falla en compilacion porque hemos hecho downcasting
		componenteInyectado.sayHello();
		model.addAttribute("person", new Person("jose",33));

		
		return FORMVIEW;
	}
	
	//Metodo que nos enseña el formlulario
	@GetMapping("/showForm")
	public String showForm(Model model) {
		LOGGER.info("INFO TRACE");
		LOGGER.warn("WARNING TRACE");
		LOGGER.error("ERROR TRACE");
		LOGGER.debug("DEBUG TRACE"); //solo se ve en debug
		
		/**provocamos un error en ejecución del servidor (500)
		* este error lo va a capturar el controller que maneja los errores
		*lo dejamos comentado, si se descomenta produce un error de java que spring maneja mediante la anotación @ExceptionHandler
		* que es un listener, en este caso de excepciones de tipo Exception, es decir, cualquier excepción nos lleva a la página de error en servidor 500.html
		   
		  int i = 6 / 0;
		 * 
		 */
		model.addAttribute("person", new Person());
		return FORMVIEW;
	}
	
	
	/**
	 * Metodo que escucha llamada post, recibe un atributo del body de la petición post del formulario con el objeto persona
	 * 	
	 * @param per @Valid es necesario para que binding result se ejecute correctamente, es decir, marcamos el objeto que tiene validaciones con @valid y todo lo demas lo hace spring en bidinresult
	 * 
	 * @param @BindingResult BindingResult es un objeto en el que se ejecutan las validaciones que hemos anotado en el model a Person, nos va a informar si se cumplen esas validaciones o no, y cual no se cumplen
	 * @return ModelAndView devuelve el string con la vista mas el objeto modelo, el string de la vista lo busca spring en el directorio donde el sabe que estan las vistas
	 */
	@PostMapping("/addperson")
	public ModelAndView addPerson(@Valid @ModelAttribute("person") Person per, BindingResult bindingResult ) {
		LOGGER.info("INFO TRACE -> Esta es la persona a añadir " + per + " " );
		ModelAndView mav = new ModelAndView();
		if(bindingResult.hasErrors()) {
			//si hay errores al formulario
			mav.setViewName(FORMVIEW);

		} else {
			per.setName(per.getName() + " Persona");
			mav.setViewName(RESULTVIEW);
			mav.addObject("personFromPost", per);			
			
		}
		LOGGER.info("INFO TRACE -> Esta es la persona ya tratada en el metodo a añadir en la vista " + per + " " );

		return mav;
	}
}