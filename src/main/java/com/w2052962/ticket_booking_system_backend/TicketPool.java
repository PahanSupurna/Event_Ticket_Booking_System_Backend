package com.w2052962.ticket_booking_system_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.LinkedList;
import java.util.Queue;

@Component
public class TicketPool {
    private final Queue<Ticket> ticketQueue;
    private final Configuration configuration;
    private int ticketNumber;
    private int soldTickets;

    @Autowired
    public TicketPool(Configuration configuration) {
        this.ticketQueue = new LinkedList<>();
        this.configuration = configuration;
        this.ticketNumber = 0;
        this.soldTickets = 0;
    }

    public synchronized void addTicket() throws InterruptedException {
        while (soldTickets >= configuration.getTotalTickets()) {
            notifyAll();
            return;
        }

        while (ticketQueue.size() >= configuration.getTicketCapacity()) {
            System.out.println("Ticket system is full, please wait till a customer buys a ticket.");
            wait();
        }

        ticketNumber++;
        Ticket ticket = new Ticket();
        ticketQueue.offer(ticket);
        System.out.println(Thread.currentThread().getName() + " | " + ticket + " Successfully added to the system | Number of tickets in the system = " + ticketQueue.size());
        notifyAll();
    }

    public synchronized void buyTicket() throws InterruptedException {
        while (ticketQueue.isEmpty()) {
            System.out.println("There are no available tickets in the system. Please wait!");
            wait();
        }

        Ticket ticket = ticketQueue.poll();
        soldTickets++;
        System.out.println(Thread.currentThread().getName() + " | " + ticket + " is successfully purchased | Number of tickets remaining in the system = " + ticketQueue.size());
        notifyAll();
    }

    public synchronized boolean soldStatus() {
        boolean sold = soldTickets >= configuration.getTotalTickets();
        if (sold) {
            notifyAll();
        }
        return sold;
    }
}