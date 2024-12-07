package com.w2052962.ticket_booking_system_backend;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class Configuration {
    private int totalTickets;
    private int releaseRate;
    private int retrievalRate;
    private int ticketCapacity;

    public boolean validate() {
        if(totalTickets <= 0 || ticketCapacity <= 0 || retrievalRate <= 0 || releaseRate <= 0){
            return false;
        }

        if (ticketCapacity>totalTickets){
            return false;
        }

        if (retrievalRate>releaseRate){
            return false;
        }
        return true;
    }
}