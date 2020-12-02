package com.psss.ticketsystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.*;

@Controller
@RequestMapping(value= {"", "login-panel"})
public class LoginController {

	@GetMapping(value = {"index"})
	public String index() {
		return "redirect:/login-panel/login";
	}
	
	@GetMapping(value = {""})
	public String indexLogged() {
		return "redirect:/dashboard";
	}

	@GetMapping(value = "login")
	public String login(
		@RequestParam(value="expired", required = false) String expired,
		@RequestParam(value="error", required = false) String error,
		@RequestParam(value="logout", required = false) String logout,
		ModelMap modelMap){
		
		if(logout != null) {
			modelMap.put("success","Logout effettuato correttamente");
		}
		if(expired != null) {
			modelMap.put("msg", "Sessione scaduta. Effettua nuovamente il login.");
		}
		if(error != null) {
			modelMap.put("msg", "Username o Password non valida");
		}
		return "login.index";
	}
	
	@GetMapping(value="accessDenied")
	public String accessDenied(Authentication authentication, ModelMap modelMap) {
		
		if(authentication != null) {
			modelMap.put("msg","Ciao " + authentication.getName() + ", non hai i permessi necessari per accedere");
		} else modelMap.put("msg","Non hai i permessi per accedere a questa pagina!");
		
		return "ticket.denied";
	}
	
	@GetMapping(value = "welcome")
	public String welcome() {
		return "redirect:/dashboard";
	}
	
	
	
}
