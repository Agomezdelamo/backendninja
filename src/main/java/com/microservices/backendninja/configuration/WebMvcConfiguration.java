package com.microservices.backendninja.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.microservices.backendninja.components.RequestTimeInterceptor;

//Creamos una clase de configuración de Spring para añadirle a su configuración nuestro interceptor
//tiene que implementar WebMvcConfigurer
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
	
	//1 nos traemos nuestro interceptor
	@Autowired
	@Qualifier("requestTimeInterceptor")
	private RequestTimeInterceptor interceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//2 lo registramos en la configuración spring
		registry.addInterceptor(interceptor);
	}

}
