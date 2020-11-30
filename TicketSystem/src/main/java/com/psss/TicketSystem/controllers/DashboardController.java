package com.psss.TicketSystem.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.psss.TicketSystem.entities.Utente;
import com.psss.TicketSystem.entities.Notifica;
import com.psss.TicketSystem.entities.Ticket;
import com.psss.TicketSystem.services.UtenteService;
import com.psss.TicketSystem.services.NotificaService;
import com.psss.TicketSystem.services.TicketService;

@Controller
@RequestMapping(value = "dashboard")
public class DashboardController {

	@Autowired
	private UtenteService accountService;
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private NotificaService notificaService;
	
	public void getNotifiche(ModelMap modelMap, Utente account) {
		List<Ticket> tickets = ticketService.cercaAllTicketCliente(account.getUsername());		
		List<Notifica> notifiche = new ArrayList<Notifica>();
		
		for (int i = 0; i < tickets.size(); i++) {
			notifiche.addAll(notificaService.cercaNotificheByTicketId(tickets.get(i).getId(), false));
		}
		modelMap.put("notifiche", notifiche);
	}
	
	@RequestMapping(value= {"","index"}, method=RequestMethod.GET)
	public String index(Authentication authentication, ModelMap modelMap) {
		
		GrantedAuthority auth = authentication.getAuthorities().iterator().next();
		Utente account = accountService.findByUsername(authentication.getName());
		System.out.println(authentication.getName());
		int size_cliente = ticketService.cercaAllTicketCliente(account.getUsername()).size();
		int size_operatore = ticketService.cercaTicketOperatore(account.getUsername()).size();
		int size_aperti = ticketService.cercaTicketStatoAperto().size();
		
		modelMap.put("size_cliente", size_cliente);
		modelMap.put("size_operatore", size_operatore);
		modelMap.put("size_aperti", size_aperti);
		modelMap.put("account", account);
		
		if ("ROLE_CLIENTI".equals(auth.toString())) {
			getNotifiche(modelMap, account);
		}
		return "dashboard.index";
	}
	
}
