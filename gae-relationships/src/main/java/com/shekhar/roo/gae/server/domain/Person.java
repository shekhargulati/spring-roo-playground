package com.shekhar.roo.gae.server.domain;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import java.util.Set;
import com.shekhar.roo.gae.server.domain.Address;
import java.util.HashSet;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

@RooJavaBean
@RooToString
@RooEntity(identifierType = String.class)
public class Person {

    private String firstName;

    private String lastName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private Set<Address> addresses = new HashSet<Address>();
}
