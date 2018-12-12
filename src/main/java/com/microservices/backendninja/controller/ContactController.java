package com.microservices.backendninja.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.microservices.backendninja.constants.ViewConstants;
import com.microservices.backendninja.model.ContactModel;
import com.microservices.backendninja.services.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {

	private static final Log LOG = LogFactory.getLog(ContactController.class);

	@Autowired
	@Qualifier("contactServiceImpl")
	private ContactService service;

	@GetMapping("/contactForm")
	public String redirectContactForm(@RequestParam(name="id", required=false) int id , Model model) {
		ContactModel contact = new ContactModel();
		if(id != 0) {
			 contact = service.findContactByIdModel(id);			
		}
		model.addAttribute("contactModel", contact);
		return ViewConstants.CONTACT_FORM;
	}

	@GetMapping("/cancel")
	public String cancel(Model model) {
		return ViewConstants.REDIRECT + ViewConstants.CONTACTS + "/" + ViewConstants.SHOW_CONTACTS;			
	}

	@PostMapping("/addcontact")
	public String addContact(@ModelAttribute(name="contactModel") ContactModel contactModel, Model model) {
		LOG.info("method ADD CONTACT : contact ->" + contactModel.toString());
		if(service.addContact(contactModel)!= null) {
			model.addAttribute("result", 1);			
		} else {
			model.addAttribute("result",0);
		}
		
	
		return ViewConstants.REDIRECT + ViewConstants.CONTACTS + "/" + ViewConstants.SHOW_CONTACTS;			
	}
	
	@GetMapping("/removecontact")
	public ModelAndView removeContact(@RequestParam(name="id", required=true) int id) {
		service.removeContact(id);
		return showContacts();
	}
	
	@GetMapping("/showcontacts") 
	public ModelAndView showContacts(){
		ModelAndView mav = new ModelAndView(ViewConstants.CONTACTS);
		mav.addObject("contactsList", service.listAllContacts());
		
		return mav;
	}

}
