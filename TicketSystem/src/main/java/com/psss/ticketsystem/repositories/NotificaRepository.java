package com.psss.ticketsystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.psss.ticketsystem.entities.Notifica;

@Repository("notificaRepository")
public interface NotificaRepository extends CrudRepository<Notifica, Integer>{

	@Query("from Notifica where id_ticket = :id and letta = :flag")
	public List<Notifica> cercaNotificheByTicketId(@Param("id") int id, @Param("flag") boolean flag);
	
}
