package com.microservices.backendninja.components;

import org.springframework.stereotype.Component;

import com.microservices.backendninja.entity.Contact;
import com.microservices.backendninja.model.ContactModel;

@Component("contactConverter")
public class ContactConverter {
	
	
	public Contact model2Entity(ContactModel model) {
		Contact contact = new Contact();
		contact.setId(model.getId());
		contact.setFirstName(model.getFirstName());
		contact.setLastName(model.getLastName());
		contact.setCity(model.getCity());
		contact.setTelephone(model.getTelephone());
		return contact;
	}

	public ContactModel entity2Model(Contact entity){
		ContactModel contactModel = new ContactModel();
		contactModel.setId(entity.getId());
		contactModel.setFirstName(entity.getFirstName());
		contactModel.setLastName(entity.getLastName());
		contactModel.setCity(entity.getCity());
		contactModel.setTelephone(entity.getTelephone());
		return contactModel;
	}
}
