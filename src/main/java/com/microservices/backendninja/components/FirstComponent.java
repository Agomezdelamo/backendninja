package com.microservices.backendninja.components;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

//Componente es un tipado generico de spring, es la clase padre de
//@Repository, @Service, y @Controller
@Component("exampleComponent")
public class FirstComponent {
	
	private static final Log LOG = LogFactory.getLog(FirstComponent.class);
	
	public void sayHello() {	
		LOG.info("HELLO FROM FIRST COMPONENT");
		
	}
}
