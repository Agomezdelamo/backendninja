package com.microservices.backendninja.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.microservices.backendninja.components.ContactConverter;
import com.microservices.backendninja.entity.Contact;
import com.microservices.backendninja.model.ContactModel;
import com.microservices.backendninja.repository.ContactRepository;
import com.microservices.backendninja.services.ContactService;

@Service("contactServiceImpl")
public class ContactServiceImpl implements ContactService {

	@Autowired
	@Qualifier("contactRepository")
	private ContactRepository repository;

	@Autowired
	@Qualifier("contactConverter")
	private ContactConverter converter;

	@Override
	public ContactModel addContact(ContactModel model) {
		Contact entityFromRepo = repository.save(converter.model2Entity(model));

		return converter.entity2Model(entityFromRepo);
	}

	@Override
	public List<ContactModel> listAllContacts() {
		return repository.findAll().stream().map(item -> converter.entity2Model(item)).collect(Collectors.toList());
	}

}
