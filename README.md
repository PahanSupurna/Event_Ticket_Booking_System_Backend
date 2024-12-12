--Event Ticket Booking System Backend-- 
  Introduction 
    • The Event Ticket Booking System Backend is a Spring Boot application designed to 
    simulate a real-time ticket booking process. The system involves vendors adding tickets 
    to a pool and customers purchasing tickets from the pool. The application ensures that the 
    number of tickets in the system does not exceed a specified capacity and that all tickets 
    are sold in a controlled manner.  
    
  Setup Instructions 
    Prerequisites 
      • Java: Ensure you have Java Development Kit (JDK) 11 or higher installed. 
      • Spring Boot: The application is built using Spring Boot. 
      • IDE: An Integrated Development Environment (IDE) like IntelliJ IDEA or Eclipse is 
  
  recommended for development and debugging. 
    • Build Tool: Maven is used to manage dependencies and build the project. -How to Configure and Start the System 
  
  Application Properties: 
    The application reads configuration properties from application.properties. You can configure the following properties: 
      • ticket.totalTickets=6 
      • ticket.releaseRate=3 
      • ticket.retrievalRate=4 
      • ticket.ticketCapacity=5 
      
  Start the Application: 
    • Run the EventTicketBookingSystemApplication class from the com.w2052962.ticket_booking_system_backend package. 
  
  Application Behavior: 
    • The system will validate the configuration properties. 
    • If the configuration is valid, the system will start vendor and customer threads to simulate the ticket booking process. 
    • Vendors will add tickets to the system, and customers will purchase tickets. 
    • The system will log the addition and purchase of tickets to a text file named configuration.txt. 
 
  Console Output: 
    • Vendor Activity: The console will display messages indicating when vendors add tickets to the system. 
    • Customer Activity: The console will display messages indicating when customers purchase tickets from the system. 
    • System State: The console will display the current state of the ticket pool, including the  number of tickets remaining.
