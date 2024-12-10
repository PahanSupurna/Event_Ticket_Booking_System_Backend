package com.w2052962.ticket_booking_system_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerLogic implements Runnable {
    private final TicketPool ticketPool;
    private final int retrievalRate;

    @Autowired
    public CustomerLogic(TicketPool ticketPool, Configuration configuration) {
        this.ticketPool = ticketPool;
        this.retrievalRate = configuration.getRetrievalRate();
    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized (ticketPool) {
                    if (ticketPool.soldStatus()) {
                        break; // Stop if all tickets are sold
                    }
                    ticketPool.buyTicket();
                }
                Thread.sleep(retrievalRate * 1000L);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();  // Handle interrupt properly
        }
    }
}
