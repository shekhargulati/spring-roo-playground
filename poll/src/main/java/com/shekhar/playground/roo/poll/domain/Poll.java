package com.shekhar.playground.roo.poll.domain;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import javax.validation.constraints.NotNull;
import java.util.Set;
import com.shekhar.playground.roo.poll.domain.Answer;
import java.util.HashSet;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;

@RooJavaBean
@RooToString
@RooEntity
public class Poll {

    @NotNull
    private String question;

    @NotNull
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Answer> answers = new HashSet<Answer>();
}
