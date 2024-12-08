package com.w2052962.ticket_booking_system_backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventTicketBookingSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(EventTicketBookingSystemApplication.class, args);
    }
    @Bean
    CommandLineRunner runApp(Configuration configuration, TicketPool ticketPool, VendorLogic vendorLogic, CustomerLogic customerLogic) {
        return args -> {
            // Set initial configuration values
            configuration.setTotalTickets(10);
            configuration.setReleaseRate(4);
            configuration.setRetrievalRate(1);
            configuration.setTicketCapacity(10);

            if (!configuration.validate()) {
                System.out.println("Invalid configuration. Please check the input values.");
                return;
            }

            // Instantiate logger with data from the configuration
            Logger logger = new Logger(
                    configuration.getTotalTickets(),
                    configuration.getReleaseRate(),
                    configuration.getRetrievalRate(),
                    configuration.getTicketCapacity()
            );

            // Save to text file
            logger.saveInTextFile();


            // Start VendorLogic threads
            int numberOfVendors = 6; // Hard-code of the number of vendors
            Thread[] vendorThreads = new Thread[numberOfVendors];
            for (int i = 1; i < numberOfVendors; i++) {
                vendorThreads[i] = new Thread(new VendorLogic(ticketPool, configuration), "Vendor " + i);
                vendorThreads[i].start();
            }

            int numberOfCustomers = 6;
            Thread[] customerThreads = new Thread[numberOfCustomers];
            for (int i = 1; i < customerThreads.length; i++) {
                customerThreads[i] = new Thread(new CustomerLogic(ticketPool, configuration), "Customer " + i);
                customerThreads[i].start();
            }
            
            System.out.println("All tickets processed successfully!");
        };
    }
}