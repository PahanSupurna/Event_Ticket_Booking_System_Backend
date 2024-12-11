package com.w2052962.ticket_booking_system_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendorLogic implements Runnable {
    private final TicketPool ticketPool;
    private final int releaseRate;

    // Constructor
    @Autowired
    public VendorLogic(TicketPool ticketPool, Configuration configuration) {
        this.ticketPool = ticketPool;
        this.releaseRate = configuration.getReleaseRate();
    }

    //A method to check the availability of the ticket queue and add tickets from the vendor
    @Override
    public void run() {
        try {
            while (true) {
                synchronized (ticketPool) {
                    if (ticketPool.soldStatus()) {
                        System.out.println(Thread.currentThread().getName() + " stopped.");
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