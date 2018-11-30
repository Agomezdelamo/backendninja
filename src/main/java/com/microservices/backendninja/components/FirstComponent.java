package com.microservices.backendninja.components;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.microservices.backendninja.repository.CourseJpaRepository;

//Componente es un tipado generico de spring, es la clase padre de
//@Repository, @Service, y @Controller
@Component("exampleComponent")
public class FirstComponent {
	
	private static final Log LOG = LogFactory.getLog(FirstComponent.class);
	
	@Autowired
	@Qualifier("courseJpaRepository")
	private CourseJpaRepository repository;
	
	public void sayHello() {	
		LOG.info("HELLO FROM FIRST COMPONENT");
		
	}
}
