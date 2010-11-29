package com.shekhar.roo.conference.domain;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import com.shekhar.roo.conference.domain.Gender;
import javax.validation.constraints.NotNull;
import javax.persistence.Enumerated;
import java.util.Set;
import com.shekhar.roo.conference.domain.Talks;
import java.util.HashSet;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

@RooJavaBean
@RooToString
@RooEntity(finders = { "findSpeakersByEmailAndPasswordEquals" })
public class Speaker {

    @NotNull
    @Enumerated
    private Gender gender;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    private String organization;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Talks> talks = new HashSet<Talks>();

    @NotNull
    private String password;

    public String toString() {
        return firstName + " " + lastName;
    }
}
