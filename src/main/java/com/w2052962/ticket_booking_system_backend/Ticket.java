package com.w2052962.ticket_booking_system_backend;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Entity
@Component
public class Ticket {
    @Id
    private String ticketID; // Use String to accommodate UUID
    private String ticketNumber;

    public String getTicketID() {
        return ticketID;
    }
    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }
    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }


    public Ticket() {
        this.ticketID = generateTicketID(); // Generate random ticket ID
    }

    public String generateTicketID() {
        return UUID.randomUUID().toString(); // Example: TICKET-123e4567-e89b-12d3-a456-426614174000
    }

    @Override
    public String toString() {
        return "Ticket ID - " + ticketID ;
    }
}