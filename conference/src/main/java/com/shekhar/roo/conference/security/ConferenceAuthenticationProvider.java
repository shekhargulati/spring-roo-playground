package com.shekhar.roo.conference.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NonUniqueResultException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import com.shekhar.roo.conference.domain.Speaker;

public class ConferenceAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private String adminUser;
    private String adminPassword;
    private Logger logger = Logger.getLogger(this.getClass());

    @Required
    public void setAdminUser(String adminUser) {
        this.adminUser = adminUser;
    }

    @Required
    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        // TODO Auto-generated method stub

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        String password = (String) authentication.getCredentials();
        if (!StringUtils.hasText(password)) {
            throw new BadCredentialsException("Please enter password");
        }
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        if (adminUser.equals(username)) {
            if (!password.equals(adminPassword)) {
                throw new BadCredentialsException("Invalid password");
            }
            // authorize admin
            logger.info("Valid user " + username);
            authorities.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
        } else {
            try {
                Speaker speaker = Speaker.findSpeakersByEmailAndPasswordEquals(username, password).getSingleResult();
                authorities.add(new GrantedAuthorityImpl("ROLE_SPEAKER"));
            } catch (EntityNotFoundException e) {
                throw new BadCredentialsException("Invalid user");
            } catch (NonUniqueResultException e) {
                throw new BadCredentialsException("Non-unique user, contact administrator");
            }
        }
        return new User(username, password, true, // enabled
                true, // account not expired
                true, // credentials not expired
                true, // account not locked
                authorities);
    }
}
