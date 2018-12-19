package com.microservices.backendninja.components;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.microservices.backendninja.repository.LogRepository;

//cada petición al servidor pasa por aqui
//para que funcione tenemos que registrar este interceptor en la configuración de spring que se encuentra en:
//com.microservices.backendninja.configuration.WebMvcConfiguration.java que es una clase que implementa el interaz WebMvcConfigurer y con el cual podemos registrar interceptors
@Component("requestTimeInterceptor")
public class RequestTimeInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	@Qualifier("logRepository")
	private LogRepository logRepository;
	
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
		String url = request.getRequestURL().toString();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = "";
		if(null != auth && auth.isAuthenticated()) {
			username = auth.getName();
		}
		//guardamos el log en db cada petición una vez esta completada, auditando así cada petición
		logRepository.save(new com.microservices.backendninja.entity.Log(new Date(),auth.getDetails().toString(),username,url));
		
		LOGGER.info("Url to: " + url  + " in: " + (System.currentTimeMillis() - startTime) + " ms");
	}

}
