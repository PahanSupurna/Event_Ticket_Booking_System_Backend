package com.w2052962.ticket_booking_system_backend;

public class Configuration {
    private int totalTickets;
    private int releaseRate;
    private int retrievalRate;
    private int ticketCapacity;

    public Configuration(int totalTickets, int releaseRate, int retrievalRate, int ticketCapacity) {
        this.totalTickets = totalTickets;
        this.releaseRate = releaseRate;
        this.retrievalRate = retrievalRate;
        this.ticketCapacity = ticketCapacity;
    }

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