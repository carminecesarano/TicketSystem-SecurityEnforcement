package com.psss.ticketsystem.controllers;


import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;

import com.psss.ticketsystem.entities.Utente;
import com.psss.ticketsystem.services.UtenteService;

@Controller
@Component
@RequestMapping(value = "user")
public class UserController implements ServletContextAware{
		
	@Autowired
	private UtenteService utenteService;
		
	
	@GetMapping(value= "list")
	public String list(ModelMap modelMap, Authentication authentication) {
		Utente account = utenteService.findByUsername(authentication.getName());
		modelMap.put("account", account);
		modelMap.put("users", utenteService.findAllUsers());
		return "user.list";
	}
	
	@GetMapping(value= "profile/")
	public String profile(ModelMap modelMap, Authentication authentication) {
		Utente account = utenteService.findByUsername(authentication.getName());
		modelMap.put("account", account);
		return "user.profile";
	}
	
	@GetMapping(value= "profile/{username}")
	public String details(@PathVariable("username") String username, ModelMap modelMap, Authentication authentication) {
		Utente account = utenteService.findByUsername(username);
		modelMap.put("account", account);
		return "user.profile";
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
	}
}
