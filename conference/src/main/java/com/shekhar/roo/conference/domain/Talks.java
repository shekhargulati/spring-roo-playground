package com.shekhar.roo.conference.domain;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import javax.validation.constraints.NotNull;
import com.shekhar.roo.conference.domain.Conference;
import javax.persistence.ManyToOne;
import com.shekhar.roo.conference.domain.Speaker;

@RooJavaBean
@RooToString
@RooEntity
public class Talks {

    @NotNull
    private String title;

    @NotNull
    private String description;

    @ManyToOne
    private Conference conference;

    @ManyToOne
    private Speaker speaker;
    
    public String toString() {
        return title;
    }
}
