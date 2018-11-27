package com.microservices.backendninja.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.microservices.backendninja.model.Person;
import com.microservices.backendninja.services.FirstService;

@Service("firstServiceProduccion")
public class FirstServiceImpl implements FirstService {
	
	private static final Log LOG = LogFactory.getLog(FirstServiceImpl.class);
	
	@Override
	public List<Person> getListPeople() {
		LOG.info("ENTRAMOS AL SERVICIO GETPEOPLE");
		//realmente el servicio obtendria esta info de la capa DAO, aqui lo simulamos
		List<Person> personList = new ArrayList<>();
		personList.add(new Person("juan",22));
		personList.add(new Person("Alberto",32));
		personList.add(new Person("pedro",42));
		personList.add(new Person("luis",12));
		LOG.info("SALIMOS DEL SERVICIO GETPEOPLE");
		return personList;
	}

}
