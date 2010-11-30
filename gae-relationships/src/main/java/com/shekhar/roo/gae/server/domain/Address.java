package com.shekhar.roo.gae.server.domain;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import com.shekhar.roo.gae.server.domain.Person;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooEntity(identifierType = String.class)
public class Address {

    private String streetAddress;

    private String city;

    private String state;

    private String zip;

    @ManyToOne
    private Person person;
}
