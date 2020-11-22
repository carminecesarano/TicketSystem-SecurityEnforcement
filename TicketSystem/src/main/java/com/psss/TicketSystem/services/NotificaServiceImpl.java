package com.psss.TicketSystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.psss.TicketSystem.entities.Notifica;
import com.psss.TicketSystem.repositories.NotificaRepository;


@Service("notificaService")
public class NotificaServiceImpl implements NotificaService{

	@Autowired
	private NotificaRepository notificaRepository;
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@Override
	public Notifica save(Notifica notifica) {
		return notificaRepository.save(notifica);
	}

	@Override
	public List<Notifica> cercaNotificheByTicketId(int id, boolean flag) {
		return notificaRepository.cercaNotificheByTicketId(id,flag);
	}
	
	@Override
	public void notify(Notifica notifica, String username) {
		messagingTemplate.convertAndSendToUser(username, "/queue/notify", notifica);
		return;
	}

	
}
