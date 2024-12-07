package com.w2052962.ticket_booking_system_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.LinkedList;
import java.util.Queue;

//Initialized the class
@Component
public class TicketPool {
    private final Queue<Ticket> ticketQueue; //Queue to store tickets in the system
    private final Configuration configuration; // Object of Configuration class
    private int ticketNumber; //To get the ticket number for displaying.
    private int soldTickets; //To keep the track of the number of tickets that were sold.
    private final Customer customer;
    private final Vendor vendor;

    // Constructor
    @Autowired
    public TicketPool(Configuration configuration, Customer customer, Vendor vendor) {
        this.ticketQueue = new LinkedList<>();
        this.configuration = configuration;
        this.ticketNumber = 0;
        this.soldTickets = 0;
        this.customer = customer;
        this.vendor = vendor;
    }

    // Method for adding the tickets to the system by Vendors.
    public synchronized void addTicket() throws InterruptedException {
        while (soldTickets >= configuration.getTotalTickets()) {
            notifyAll(); // Notify all threads to avoid deadlock
            return; // Stop further ticket addition
        }

        //checks if the number of tickets in the system is higher than the maximum capacity.
        while (ticketQueue.size() >= configuration.getTicketCapacity()) {
            System.out.println("Ticket system is full, please wait till a customer buys a ticket.");
            wait(); //waits till a customer buys a ticket
        }

        ticketNumber++;
        Ticket ticket = new Ticket();
        ticketQueue.offer(ticket); //Adds the ticket to the queue
        System.out.println(ticket + " Successfully added to the system by "+vendor.generateVendorID()+" | Number of tickets in the system = " + ticketQueue.size());
        System.out.println(" ");
        notifyAll(); //Notifies the customers about the added tickets.
    }

    // Method for buying tickets in the system by customers.
    public synchronized void buyTicket() throws InterruptedException {
        // Checks if there is no available tickets in the system.
        while (ticketQueue.isEmpty()) {
            System.out.println("There are no available tickets in the system. Please wait!");
            wait(); //waits till a vendor adds tickets to the system
        }

        Ticket ticket = ticketQueue.poll(); //Removes the ticket from the system
        soldTickets++;
        System.out.println(ticket + " is successfully purchased by " + customer.generateCustomerID() +" | Number of tickets remaining in the system = " + ticketQueue.size());
        System.out.println(" ");
        notifyAll(); //Notifies the vendors about the available ticket slot
    }

    public synchronized boolean allTicketsSold() {
        return soldTickets >= configuration.getTotalTickets();
    }
}