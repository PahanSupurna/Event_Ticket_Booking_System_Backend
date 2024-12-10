package com.w2052962.ticket_booking_system_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/config")
public class ConfigurationController {

    @Autowired
    private Configuration configuration;

    @Autowired
    private TicketPool ticketPool;

    @Autowired
    private VendorLogic vendorLogic;

    @Autowired
    private CustomerLogic customerLogic;

    @PostMapping("/set")
    public String setConfiguration(@RequestBody Configuration config) {
        configuration.setTotalTickets(config.getTotalTickets());
        configuration.setReleaseRate(config.getReleaseRate());
        configuration.setRetrievalRate(config.getRetrievalRate());
        configuration.setTicketCapacity(config.getTicketCapacity());

        if (!configuration.validate()) {
            return "Invalid configuration. Please check the input values.";
        }

        // Save to text file
        Logger logger = new Logger(
                configuration.getTotalTickets(),
                configuration.getReleaseRate(),
                configuration.getRetrievalRate(),
                configuration.getTicketCapacity()
        );
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

        return "Configuration set successfully!";
    }
}