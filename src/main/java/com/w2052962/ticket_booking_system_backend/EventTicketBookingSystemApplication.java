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
            Scanner scanner = new Scanner(System.in);

            // Prompt for configuration settings
            System.out.print("Enter total number of tickets: ");
            int totalTickets = scanner.nextInt();

            System.out.print("Enter ticket release rate (seconds): ");
            int releaseRate = scanner.nextInt();

            System.out.print("Enter customer retrieval rate (seconds): ");
            int retrievalRate = scanner.nextInt();

            System.out.print("Enter maximum ticket capacity: ");
            int ticketCapacity = scanner.nextInt();

            // Set configuration values
            configuration.setTotalTickets(totalTickets);
            configuration.setReleaseRate(releaseRate);
            configuration.setRetrievalRate(retrievalRate);
            configuration.setTicketCapacity(ticketCapacity);

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