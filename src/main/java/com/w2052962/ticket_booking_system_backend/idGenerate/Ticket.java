package com.w2052962.ticket_booking_system_backend.idGenerate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String ticketID; // Use String to accommodate UUID

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public String generateTicketID() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Ticket ID - " + Integer.toHexString(System.identityHashCode(this));
    }
}