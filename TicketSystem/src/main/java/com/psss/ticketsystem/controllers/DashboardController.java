package com.psss.ticketsystem.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.psss.ticketsystem.entities.Notifica;
import com.psss.ticketsystem.entities.Ticket;
import com.psss.ticketsystem.entities.Utente;
import com.psss.ticketsystem.services.NotificaService;
import com.psss.ticketsystem.services.TicketService;
import com.psss.ticketsystem.services.UtenteService;

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
		List<Notifica> notifiche = new ArrayList<>();
		
		for (int i = 0; i < tickets.size(); i++) {
			notifiche.addAll(notificaService.cercaNotificheByTicketId(tickets.get(i).getId(), false));
		}
		modelMap.put("notifiche", notifiche);
	}
	
	@GetMapping(value= {"","index"})
	public String index(Authentication authentication, ModelMap modelMap) {
		
		GrantedAuthority auth = authentication.getAuthorities().iterator().next();
		Utente account = accountService.findByUsername(authentication.getName());
		int sizeCliente = ticketService.cercaAllTicketCliente(account.getUsername()).size();
		int sizeOperatore = ticketService.cercaTicketOperatore(account.getUsername()).size();
		int sizeAperti = ticketService.cercaTicketStatoAperto().size();
		
		modelMap.put("size_cliente", sizeCliente);
		modelMap.put("size_operatore", sizeOperatore);
		modelMap.put("size_aperti", sizeAperti);
		modelMap.put("account", account);
		
		if ("ROLE_CLIENTI".equals(auth.toString())) {
			getNotifiche(modelMap, account);
		}
		return "dashboard.index";
	}
	
}
