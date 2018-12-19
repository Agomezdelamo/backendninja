package com.microservices.backendninja.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.servlet.ModelAndView;

public class AccesoDenegadoController implements AccessDeniedHandler {
	public static final Log LOG = LogFactory.getLog(AccesoDenegadoController.class);

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			LOG.warn("User: " + auth.getName() + " attempted to access the protected URL: " + request.getRequestURI()); 
		}
		
		response.sendRedirect("/403");
	}
}