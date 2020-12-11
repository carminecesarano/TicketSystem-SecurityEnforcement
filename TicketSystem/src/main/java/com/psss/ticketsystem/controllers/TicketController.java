package com.psss.ticketsystem.controllers;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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

import com.psss.ticketsystem.entities.Notifica;
import com.psss.ticketsystem.entities.StatoTicketAperto;
import com.psss.ticketsystem.entities.StatoTicketChiuso;
import com.psss.ticketsystem.entities.StatoTicketInLavorazione;
import com.psss.ticketsystem.entities.Ticket;
import com.psss.ticketsystem.entities.Utente;
import com.psss.ticketsystem.services.NotificaService;
import com.psss.ticketsystem.services.TicketService;
import com.psss.ticketsystem.services.UtenteService;

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
			
		List<Ticket> tickets = ticketService.cercaAllTicketCliente(account.getUsername());		
		List<Notifica> notifiche = new ArrayList<>();
		
		for (int i = 0; i < tickets.size(); i++) {
			notifiche.addAll(notificaService.cercaNotificheByTicketId(tickets.get(i).getId(), false));
		}
		
		modelMap.put("notifiche", notifiche);
	}
	
	@GetMapping(value= "send")
	public String send(ModelMap modelMap, Authentication authentication) {
		Ticket ticket = new Ticket();
		Utente account = utenteService.findByUsername(authentication.getName());
		
		modelMap.put("ticket", ticket);
		modelMap.put("account", account);
		getNotifiche(modelMap, account);
		return "ticket.send";
	}
	
	@PostMapping(value= "send")
	public String send(@ModelAttribute("ticket") Ticket ticket, Authentication authentication, RedirectAttributes redirectAttributes) {
		try {
			if (!ticket.getTitle().equals("") && !ticket.getDescription().equals("")) {
				Utente account = utenteService.findByUsername(authentication.getName());
				ticket.setCreatedDate(new Date());
				ticket.setCliente(account);
				StatoTicketAperto statoAperto = new StatoTicketAperto();
				ticket.setStatoTicket(statoAperto);
				ticketService.save(ticket);
				redirectAttributes.addFlashAttribute("success", "Ticket inviato correttamente.");
			} else {
				redirectAttributes.addFlashAttribute("err", "Invio del ticket fallito.");
			}
		} catch (ConstraintViolationException ex) {
			redirectAttributes.addFlashAttribute("err", "Input non valido.");
        }
		catch (Exception e) {
			redirectAttributes.addFlashAttribute("err", "Invio del ticket fallito.");
		}
		return "redirect:/ticket/send";
	}	
	
	@GetMapping(value= "history_aperti")
	public String history_aperti(ModelMap modelMap, Authentication authentication) {
		Utente account = utenteService.findByUsername(authentication.getName());
		modelMap.put("account", account);
		modelMap.put("tickets", ticketService.cercaTicketStatoAperto());
		return "ticket.history";
	}
	
	@GetMapping(value= "history_cliente")
	public String history_cliente(Authentication authentication, ModelMap modelMap) {
		Utente cliente = utenteService.findByUsername(authentication.getName());
		List<Ticket> tickets = ticketService.cercaAllTicketCliente(cliente.getUsername());		// Tutti i ticket del cliente
		
		modelMap.put("tickets", tickets);
		modelMap.put("account", cliente);
		getNotifiche(modelMap, cliente);
		return "ticket.history";
	}
	
	@GetMapping(value= "history_operatore")
	public String history_operatore(Authentication authentication, ModelMap modelMap) {
		Utente account = utenteService.findByUsername(authentication.getName());
		modelMap.put("account", account);
		modelMap.put("tickets", ticketService.cercaTicketOperatore(authentication.getName()));
		return "ticket.history";
	}
	
	@GetMapping(value= "details/{id}")
	public String details(@PathVariable("id") int id, ModelMap modelMap, Authentication authentication) {
		
		Utente account = utenteService.findByUsername(authentication.getName());
		GrantedAuthority auth = authentication.getAuthorities().iterator().next();
		
		Ticket ticket = null;
		
		if ("ROLE_CLIENTI".equals(auth.toString())) {
			ticket = ticketService.cercaTicketCliente(account.getUsername(), id);
			getNotifiche(modelMap, account);
		}
		else if (("ROLE_OPERATORI".equals(auth.toString()))) {
			ticket = ticketService.findTicket(id);
		}
		
		if(ticket != null) {
			modelMap.put("ticket", ticket);
			modelMap.put("account", account);
			return "ticket.details";
		}
		else {
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
				default:
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
