package com.w2052962.ticket_booking_system_backend;

import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private final int totalTickets;
    private final int releaseRate;
    private final int retrievalRate;
    private final int ticketCapacity;

    public Logger(int totalTickets, int releaseRate, int retrievalRate, int ticketCapacity) {
        this.totalTickets = totalTickets;
        this.releaseRate = releaseRate;
        this.retrievalRate = retrievalRate;
        this.ticketCapacity = ticketCapacity;
    }

    public void saveInTextFile() {
        try (FileWriter fileWriter = new FileWriter("Ticketing_System.txt")) {
            fileWriter.write("Total Tickets: " + totalTickets + "\n");
            fileWriter.write("Release Rate: " + releaseRate + "\n");
            fileWriter.write("Retrieval Rate: " + retrievalRate + "\n");
            fileWriter.write("Ticket Capacity: " + ticketCapacity + "\n");

            System.out.println("Saved ticket information to a text file.");
            System.out.println("---------------------------------------");
        } catch (IOException e) {
            System.out.println("Error occurred during the saving process: " + e.getMessage());
        }
    }
}
