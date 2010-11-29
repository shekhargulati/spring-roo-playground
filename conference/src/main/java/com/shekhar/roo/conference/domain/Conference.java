package com.shekhar.roo.conference.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@RooEntity
public class Conference {

    @NotNull
    private String name;

    @Size(max = 4000)
    private String description;
    
    @NotNull
    private String organizerName;

    @NotNull
    private String organizerEmail;

    @NotNull
    @Future
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date startDate;

    @NotNull
    @Future
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date endDate;

    private String website;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Talks> talks = new HashSet<Talks>();

    
    
    @Override
    public String toString() {
        return name;
    }
}
