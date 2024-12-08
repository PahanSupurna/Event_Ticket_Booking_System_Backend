package com.w2052962.ticket_booking_system_backend;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String ticketID; // Use String to accommodate UUID
    //private String ticketNumber;

    public String getTicketID() {
        return ticketID;
    }
    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

//    public String getTicketNumber() {
//        return ticketNumber;
//    }
//    public void setTicketNumber(String ticketNumber) {
//        this.ticketNumber = ticketNumber;
//    }

    public String generateTicketID() {
        return UUID.randomUUID().toString(); // Example: TICKET-123e4567-e89b-12d3-a456-426614174000
    }

    @Override
    public String toString() {
        return "Ticket ID - " + Integer.toHexString(System.identityHashCode(this));
    }

}