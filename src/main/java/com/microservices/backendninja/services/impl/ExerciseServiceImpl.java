package com.microservices.backendninja.services.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.microservices.backendninja.services.ExerciseService;


@Service("exerciseServiceImpl")
public class ExerciseServiceImpl implements ExerciseService {

	private static final Log LOG = LogFactory.getLog(ExerciseServiceImpl.class);
	
	@Override
	public void printMessage() {
		LOG.info("ENTRANDO A PINTAR EL LOG");
	}
}
