package com.microservices.backendninja.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.microservices.backendninja.entitys.Course;
import com.microservices.backendninja.services.CourseService;
import com.microservices.backendninja.services.impl.CourseServiceImpl;

@Controller
@RequestMapping("/course")
public class CourseController {
	
	private static final String COURSE_VIEW = "courses";
	private static final Log LOG = LogFactory.getLog(CourseController.class);
	
	@Autowired
	@Qualifier("courseServiceImpl")
	private CourseService courseService;
	
	//metodo que lista los cursos disponibles en bbdd
	@GetMapping("/listcourses")
	public ModelAndView listAllCourses(){
		LOG.info("Call : listAllCourses");
		ModelAndView mav = new ModelAndView(COURSE_VIEW);
		mav.addObject("courses", courseService.listAllCourses());
		return mav;
	}
	
	//metodo que inserta un curso en bbdd y redirige a listCourses con el curso ya añadido
	@PostMapping("/addcourse")
	public String addCourse(@ModelAttribute("course") Course c) {
		LOG.info("Call : addCourse --> param = " + c.toString());

		courseService.addCourse(c);
		return "redirect:/course/listCourses";
	}
}
