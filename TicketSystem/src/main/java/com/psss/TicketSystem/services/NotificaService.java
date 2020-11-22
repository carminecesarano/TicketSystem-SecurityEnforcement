package com.psss.TicketSystem.services;

import java.util.List;

import com.psss.TicketSystem.entities.Notifica;

public interface NotificaService {
	
	public Notifica save(Notifica notifica);
		
	public List<Notifica> cercaNotificheByTicketId(int id, boolean flag);

	public void notify(Notifica notifica, String username);

}
