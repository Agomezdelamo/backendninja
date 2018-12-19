package com.microservices.backendninja.components;

import org.apache.juli.logging.LogFactory;

import java.util.Date;

import org.apache.juli.logging.Log;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//componente para ejecutar tareas programadas
@Component("taskComponent")
public class TaskComponent {
	private static final Log log = LogFactory.getLog(TaskComponent.class);
		
	//anotación para que se ejecute este metodo como si fuese una tarea programada cada x tiempo
	//en este caso 5 segundos
	@Scheduled(fixedDelay=5000)
	public void toTask(){
		log.info("time is: " + new Date());
	} 
}
