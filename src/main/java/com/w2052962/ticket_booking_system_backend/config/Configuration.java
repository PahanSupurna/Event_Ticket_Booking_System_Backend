package com.w2052962.ticket_booking_system_backend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "ticket")
public class Configuration {
    private int totalTickets;
    private int releaseRate;
    private int retrievalRate;
    private int ticketCapacity;

    // Getters and Setters
    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getReleaseRate() {
        return releaseRate;
    }

    public void setReleaseRate(int releaseRate) {
        this.releaseRate = releaseRate;
    }

    public int getRetrievalRate() {
        return retrievalRate;
    }

    public void setRetrievalRate(int retrievalRate) {
        this.retrievalRate = retrievalRate;
    }

    public int getTicketCapacity() {
        return ticketCapacity;
    }

    public void setTicketCapacity(int ticketCapacity) {
        this.ticketCapacity = ticketCapacity;
    }

    // Validate method
    public boolean validate() {
        if (totalTickets <= 0 || ticketCapacity <= 0 || retrievalRate <= 0 || releaseRate <= 0) {
            return false; // All values must be positive
        }

        if (ticketCapacity > totalTickets) {
            return false; // Capacity cannot exceed total tickets
        }

        return true;
    }
}
