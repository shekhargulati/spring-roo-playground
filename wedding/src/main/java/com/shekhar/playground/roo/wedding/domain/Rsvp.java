package com.shekhar.playground.roo.wedding.domain;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.solr.RooSolrSearchable;

@RooJavaBean
@RooToString
@RooEntity(finders = { "findRsvpsByCodeEquals" })
@RooSolrSearchable
public class Rsvp {

    @NotNull
    @Size(min = 1, max = 30)
    private String code;

    @NotNull
    private String email;

    private Integer attending;

    @Size(max = 100)
    private String specialRequests;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date confirmed;
}
