// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.shekhar.roo.gae.server.domain;

import com.shekhar.roo.gae.server.domain.Person;
import java.lang.String;

privileged aspect Address_Roo_JavaBean {
    
    public String Address.getStreetAddress() {
        return this.streetAddress;
    }
    
    public void Address.setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
    
    public String Address.getCity() {
        return this.city;
    }
    
    public void Address.setCity(String city) {
        this.city = city;
    }
    
    public String Address.getState() {
        return this.state;
    }
    
    public void Address.setState(String state) {
        this.state = state;
    }
    
    public String Address.getZip() {
        return this.zip;
    }
    
    public void Address.setZip(String zip) {
        this.zip = zip;
    }
    
    public Person Address.getPerson() {
        return this.person;
    }
    
    public void Address.setPerson(Person person) {
        this.person = person;
    }
    
}
