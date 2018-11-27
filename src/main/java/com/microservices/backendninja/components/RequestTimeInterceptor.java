package com.microservices.backendninja.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//cada petición al servidor pasa por aqui
//para que funcione tenemos que registrar este interceptor en la configuración de spring que se encuentra en:
//com.microservices.backendninja.configuration.WebMvcConfiguration.java que es una clase que implementa el interaz WebMvcConfigurer y con el cual podemos registrar interceptors
@Component("requestTimeInterceptor")
public class RequestTimeInterceptor extends HandlerInterceptorAdapter {
	
	private static final Log LOGGER = LogFactory.getLog(RequestTimeInterceptor.class);
	
	//antes de que entremos al metodo que mapea un path en el controlador
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		request.setAttribute("startTime", System.currentTimeMillis());
		
		return true;
	}
	
	//antes de renderizar la vista en el navegador
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long startTime = (long)request.getAttribute("startTime");
		LOGGER.info("REQUEST URL: " + request.getRequestURL().toString() + " TOTAL TIME: " + (System.currentTimeMillis() - startTime) + " ms");
	}

}
