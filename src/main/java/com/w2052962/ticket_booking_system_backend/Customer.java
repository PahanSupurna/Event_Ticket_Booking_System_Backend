package com.w2052962.ticket_booking_system_backend;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Entity
@Component
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String customerID;
    private String name;
    private String email;
    private String phone;

    public Customer() {}

    public String getCustomerID() {
        return customerID;
    }
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String generateCustomerID() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Customer ID - "+ Integer.toHexString(System.identityHashCode(this));
    }
}