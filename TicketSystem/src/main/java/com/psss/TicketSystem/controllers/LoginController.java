package com.psss.TicketSystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.*;

@Controller
@RequestMapping(value= {"", "login-panel"})
public class LoginController {

	@RequestMapping(value = {"", "index"}, method = RequestMethod.GET)
	public String index() {
		return "redirect:/login-panel/login";
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(
		@RequestParam(value="expired", required = false) String expired,
		@RequestParam(value="error", required = false) String error,
		@RequestParam(value="logout", required = false) String logout,
		ModelMap modelMap){
		
		if(expired != null) {
			modelMap.put("msg", "Sessione scaduta. Effettua nuovamente il login.");
		}
		if(error != null) {
			modelMap.put("msg", "Username o Password non valida");
		}
		if(logout != null) {
			modelMap.put("success","Logout effettuato correttamente");
		}
		return "login.index";
	}
	
	@RequestMapping(value="accessDenied", method = RequestMethod.GET)
	public String accessDenied(Authentication authentication, ModelMap modelMap) {
		
		if(authentication != null) {
			modelMap.put("msg","Ciao " + authentication.getName() + ", non hai i permessi necessari per accedere");
		} else modelMap.put("msg","Non hai i permessi per accedere a questa pagina!");
		
		return "login.accessDenied";
	}
	
	@RequestMapping(value = "welcome", method = RequestMethod.GET)
	public String welcome() {
		return "redirect:/dashboard";
	}
	
	
	
}
