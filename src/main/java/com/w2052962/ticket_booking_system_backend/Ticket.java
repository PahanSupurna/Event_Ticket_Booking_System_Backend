package com.w2052962.ticket_booking_system_backend;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Ticket {
    private Long id;
    //private Vendor vendor

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

//    public Vendor getVendor() {
//        return vendor;
//    }
//
//    public void setVendor(Vendor vendor) {
//        this.vendor = vendor;
//    }

}