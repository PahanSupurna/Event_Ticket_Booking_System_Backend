package com.w2052962.ticket_booking_system_backend;

import com.w2052962.ticket_booking_system_backend.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,String> {
}
