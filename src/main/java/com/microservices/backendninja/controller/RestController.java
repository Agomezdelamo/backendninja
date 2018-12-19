package com.microservices.backendninja.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.microservices.backendninja.model.ContactModel;

//controlador de rest con esta etiqueta
//expone rest services a una aplicación cliente tipo angular para que consulte datos
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {
	
	@GetMapping("/checkrest")
	public ResponseEntity<ContactModel> checkRest() {
		ContactModel cm = new ContactModel(2, "miguel", "gonzalez","4532452345","madrid");
		//es un objeto que modela la respuesta de un rest
		return new ResponseEntity<ContactModel>(cm, HttpStatus.OK);
	}

}
