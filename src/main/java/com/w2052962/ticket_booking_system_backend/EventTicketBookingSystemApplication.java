package com.w2052962.ticket_booking_system_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class EventTicketBookingSystemApplication {

    @Autowired
    private Configuration configuration;

    @Autowired
    private TicketPool ticketPool;

    @Autowired
    private VendorLogic vendorLogic;

    @Autowired
    private CustomerLogic customerLogic;

    public static void main(String[] args) {
        SpringApplication.run(EventTicketBookingSystemApplication.class, args);
    }

    @Bean
    CommandLineRunner runApp() {
        return args -> {
            // Validate configuration
            if (!configuration.validate()) {
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
            int numberOfVendors = 6;
            Thread[] vendorThreads = new Thread[numberOfVendors];
            for (int i = 0; i < numberOfVendors; i++) {
                vendorThreads[i] = new Thread(new VendorLogic(ticketPool, configuration), "Vendor " + (i + 1));
                vendorThreads[i].start();
            }

            // Start CustomerLogic threads
            int numberOfCustomers = 6;
            Thread[] customerThreads = new Thread[numberOfCustomers];
            for (int i = 0; i < customerThreads.length; i++) {
                customerThreads[i] = new Thread(new CustomerLogic(ticketPool, configuration), "Customer " + (i + 1));
                customerThreads[i].start();
            }
        };
    }
}
