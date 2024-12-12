package com.w2052962.ticket_booking_system_backend.idGenerate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Entity
@Component
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String vendorID;
    private String vendorName;
    private String email;

    public String getVendorID() {
        return vendorID;
    }
    public void setVendorID(String vendorID) {
        this.vendorID = vendorID;
    }

    public String getVendorName() {
        return vendorName;
    }
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String generateVendorID() {
        return "Vendor ID - "+ UUID.randomUUID().toString(); // Example: TICKET-123e4567-e89b-12d3-a456-426614174000
    }

    @Override
    public String toString() {
        return "Vendor ID - "+ Integer.toHexString(System.identityHashCode(this));
    }
}