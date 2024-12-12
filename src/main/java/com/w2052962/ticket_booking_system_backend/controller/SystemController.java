//package com.w2052962.ticket_booking_system_backend;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api")
//public class SystemController {
//
//    @Autowired
//    private SimpMessagingTemplate messagingTemplate;
//
//    @Autowired
//    private Configuration configuration;
//
//    @PostMapping("/config/update")
//    public void updateConfig(@RequestBody Configuration config) {
//        configuration.setTotalTickets(config.getTotalTickets());
//        configuration.setReleaseRate(config.getReleaseRate());
//        configuration.setRetrievalRate(config.getRetrievalRate());
//        configuration.setTicketCapacity(config.getTicketCapacity());
//        configuration.validate();
//        // Save configuration to file if required.
//    }
//
//    @PostMapping("/simulation/start")
//    public void startSimulation() {
//        messagingTemplate.convertAndSend("/topic/logs", "Simulation started with configuration: " + configuration);
//        // Start simulation logic
//    }
//
//    @PostMapping("/simulation/stop")
//    public void stopSimulation() {
//        messagingTemplate.convertAndSend("/topic/logs", "Simulation stopped.");
//        // Stop simulation logic
//    }
//}
