package com.w2052962.ticket_booking_system_backend;

import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Vendor implements Runnable {
    @Id
    private int id;
    private final TicketPool ticketPool;
    private final int releaseRate;

    // Constructor
    @Autowired
    public Vendor(TicketPool ticketPool, Configuration configuration) {
        this.ticketPool = ticketPool;
        this.releaseRate = configuration.getReleaseRate();
    }

    //A method to check the availability of the ticket queue and add tickets from the vendor
    @Override
    public void run() {
        try {
            while (true) {
                synchronized (ticketPool) {
                    if (ticketPool.allTicketsSold()) {
                        break; // Stop if total tickets have been sold
                    }
                    ticketPool.addTicket();
                }
                Thread.sleep(releaseRate*1000L);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}