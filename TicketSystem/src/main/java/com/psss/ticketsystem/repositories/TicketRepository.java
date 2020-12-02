package com.psss.ticketsystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.psss.ticketsystem.entities.Ticket;

@Repository("ticketRepository")
public interface TicketRepository extends CrudRepository<Ticket, Integer>{

	@Query("from Ticket where cliente_id = :id order by status")
	public List<Ticket> cercaAllTicketCliente(@Param("id") int id);
	
	@Query("from Ticket where cliente_id = :idCliente and id = :idTicket order by status")
	public Ticket cercaTicketCliente(@Param("idCliente") int idCliente, @Param("idTicket") int idTicket);
	
	@Query("from Ticket where operatore_id = :id order by status")
	public List<Ticket> cercaTicketOperatore(@Param("id") int id);
	
	@Query("from Ticket where status = :stato")
	public List<Ticket> cercaTicketStato(@Param("stato") int stato);
	
	@Query("from Ticket")
	public List<Ticket> findAll();

}
