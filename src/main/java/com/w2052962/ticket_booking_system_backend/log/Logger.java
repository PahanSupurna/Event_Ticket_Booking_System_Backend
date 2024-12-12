package com.w2052962.ticket_booking_system_backend.log;

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
        try (FileWriter writer = new FileWriter("configuration.txt")) {
            writer.write("Total Tickets: " + totalTickets + "\n");
            writer.write("Release Rate: " + releaseRate + "\n");
            writer.write("Retrieval Rate: " + retrievalRate + "\n");
            writer.write("Ticket Capacity: " + ticketCapacity + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}