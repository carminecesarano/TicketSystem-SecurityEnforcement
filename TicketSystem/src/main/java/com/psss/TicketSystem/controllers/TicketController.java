package com.psss.TicketSystem.controllers;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.psss.TicketSystem.entities.Utente;
import com.psss.TicketSystem.entities.Notifica;
import com.psss.TicketSystem.entities.StatoTicketAperto;
import com.psss.TicketSystem.entities.StatoTicketChiuso;
import com.psss.TicketSystem.entities.StatoTicketInLavorazione;
import com.psss.TicketSystem.entities.Ticket;
import com.psss.TicketSystem.services.UtenteService;
import com.psss.TicketSystem.services.NotificaService;
import com.psss.TicketSystem.services.TicketService;

@Controller
@Component
@RequestMapping(value = "ticket")
public class TicketController implements ServletContextAware{

	@Autowired
	private TicketService ticketService;
		
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private NotificaService notificaService;
	
	public void getNotifiche(ModelMap modelMap, Utente account) {
		if (account.getRuolo().getName().equals("ROLE_CLIENTE")) {
			List<Ticket> tickets = ticketService.cercaAllTicketCliente(account.getUsername());		
			List<Notifica> notifiche = new ArrayList<Notifica>();
			
			for (int i = 0; i < tickets.size(); i++) {
				notifiche.addAll(notificaService.cercaNotificheByTicketId(tickets.get(i).getId(), false));
			}
			
			modelMap.put("notifiche", notifiche);
		} 
	}
	
	@RequestMapping(value= "send", method=RequestMethod.GET)
	public String send(ModelMap modelMap, Authentication authentication) {
		Ticket ticket = new Ticket();
		Utente account = utenteService.findByUsername(authentication.getName());
		
		modelMap.put("ticket", ticket);
		modelMap.put("account", account);
		getNotifiche(modelMap, account);
		return "ticket.send";
	}
	
	@RequestMapping(value= "send", method=RequestMethod.POST)
	public String send(@ModelAttribute("ticket") Ticket ticket, Authentication authentication, RedirectAttributes redirectAttributes) {
		try {
			if (!ticket.getTitle().equals("") && !ticket.getDescription().equals("")) {
				Utente account = utenteService.findByUsername(authentication.getName());
				ticket.setCreatedDate(new Date());
				ticket.setCliente(account);
				StatoTicketAperto statoAperto = new StatoTicketAperto();
				ticket.setStatoTicket(statoAperto);
				ticket = ticketService.save(ticket);
				
				redirectAttributes.addFlashAttribute("success", "Ticket inviato correttamente.");
			} else {
				redirectAttributes.addFlashAttribute("err", "Invio del ticket fallito.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("err", "Invio del ticket fallito.");
		}
		return "redirect:/ticket/send";
	}	
	
	@RequestMapping(value= "history_aperti", method=RequestMethod.GET)
	public String history_aperti(ModelMap modelMap, Authentication authentication) {
		Utente account = utenteService.findByUsername(authentication.getName());
		modelMap.put("account", account);
		modelMap.put("tickets", ticketService.cercaTicketStatoAperto());
		return "ticket.history";
	}
	
	@RequestMapping(value= "history_cliente", method=RequestMethod.GET)
	public String history_cliente(Authentication authentication, ModelMap modelMap) {
		Utente cliente = utenteService.findByUsername(authentication.getName());
		List<Ticket> tickets = ticketService.cercaAllTicketCliente(cliente.getUsername());		// Tutti i ticket del cliente
		
		modelMap.put("tickets", tickets);
		modelMap.put("account", cliente);
		getNotifiche(modelMap, cliente);
		return "ticket.history";
	}
	
	@RequestMapping(value= "history_operatore", method=RequestMethod.GET)
	public String history_operatore(Authentication authentication, ModelMap modelMap) {
		Utente account = utenteService.findByUsername(authentication.getName());
		modelMap.put("account", account);
		modelMap.put("tickets", ticketService.cercaTicketOperatore(authentication.getName()));
		return "ticket.history";
	}
	
	@RequestMapping(value= "details/{id}", method=RequestMethod.GET)
	public String details(@PathVariable("id") int id, ModelMap modelMap, Authentication authentication) {
		
		Utente account = utenteService.findByUsername(authentication.getName());
		Ticket ticket = ticketService.cercaTicketCliente(account.getUsername(), id);				
		
		if(ticket != null) {
			modelMap.put("ticket", ticket);
			modelMap.put("account", account);
			getNotifiche(modelMap, account);
			return "ticket.details";
		}
		else {
		//	return "redirect:/login-panel/accessDenied";
			return "ticket.denied";
		}
	}
	
	@RequestMapping(value= "aggiorna_stato/{id}")
	public String aggiorna_stato(Authentication authentication, @PathVariable("id") int id, ModelMap modelMap, RedirectAttributes redirectAttributes) {
		try {
			Utente operatore = utenteService.findByUsername(authentication.getName());
			Ticket ticket = ticketService.findTicket(id);
			
			switch (ticket.getStatoTicket().getId()) {
				case 1 :
					ticket.setStatoTicket(new StatoTicketAperto());
					break;
				case 2 :
					ticket.setStatoTicket(new StatoTicketInLavorazione());
					break;
				case 3:
					ticket.setStatoTicket(new StatoTicketChiuso());
					break;
			}
			
			if (ticket.getOperatore() == null || ticket.getOperatore().getId() == operatore.getId()) {
				
				ticket.aggiornaStatoTicket(ticket,operatore);
				Notifica notifica = ticket.creaNotifica(ticket);
				ticketService.save(ticket);
				notificaService.save(notifica);
				
				notifica.setTicket(null);
				notificaService.notify(notifica, ticket.getCliente().getUsername());
				
				redirectAttributes.addFlashAttribute("success", "Stato ticket aggiornato a: " + ticket.getStatoTicket().getName());
				return "redirect:/ticket/details/{id}";
			} else {
				redirectAttributes.addFlashAttribute("err_ticket", "Il ticket è stato preso in carico da un altro operatore.");
				return "redirect:/ticket/history_operatore";
			}
		
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("err", "Errore presa in carico del ticket.");
			return "redirect:/ticket/details/{id}";
		}	
	}
			
	@Override
	public void setServletContext(ServletContext servletContext) {
	}
}
