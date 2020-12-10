package com.psss.ticketsystem.controllers;


import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.psss.ticketsystem.entities.Utente;
import com.psss.ticketsystem.services.UtenteService;

@Controller
@Component
@RequestMapping(value = "user")
public class UserController implements ServletContextAware{
		
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping(value= "list")
	public String list(ModelMap modelMap, Authentication authentication) {
		Utente account = utenteService.findByUsername(authentication.getName());
		modelMap.put("account", account);
		modelMap.put("users", utenteService.findAllUsers());
		return "user.list";
	}
		
	@GetMapping(value= "profile/{username}")
	public String details(@PathVariable("username") String username, ModelMap modelMap, Authentication authentication) {
		Utente account = utenteService.findByUsername(username);
		modelMap.put("account", account);
		return "user.profile";
	}
	
	@GetMapping(value= "add")
	public String add(ModelMap modelMap, Authentication authentication) {
		Utente authAccount = utenteService.findByUsername(authentication.getName());
		Utente user = new Utente();
		modelMap.put("account", authAccount);
		modelMap.put("user", user);
		return "user.add";
	}
		
	@PostMapping(value= "add")
	public String add(@ModelAttribute("user") Utente user, Authentication authentication, RedirectAttributes redirectAttributes) {
		try {
			if (!user.getUsername().equals("") && 
				!user.getFullName().equals("") &&
				!user.getEmail().equals("") &&
				!user.getPhone().equals("")
				) {
				
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				utenteService.saveLDAP(user);
				user.setPassword("");
				utenteService.save(user);		
				
				redirectAttributes.addFlashAttribute("success", "Utente aggiunto correttamente.");
			}
			else {
				redirectAttributes.addFlashAttribute("err", "Aggiunta dell'utente fallita.");
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("err", "Invio del ticket fallito.");
		}
		return "redirect:/user/add";
	}	
	
	@Override
	public void setServletContext(ServletContext servletContext) {
	}
}
