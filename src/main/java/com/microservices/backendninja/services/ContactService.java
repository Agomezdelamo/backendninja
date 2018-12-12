package com.microservices.backendninja.services;

import java.util.List;

import com.microservices.backendninja.entity.Contact;
import com.microservices.backendninja.model.ContactModel;

public interface ContactService {

	ContactModel addContact(ContactModel model);
	
	List<ContactModel> listAllContacts();
	
	Contact findContactById(int id);
	
	ContactModel findContactByIdModel(int id);
	
	void removeContact(int id);
	
}
