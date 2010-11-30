package com.shekhar.roo.conference.domain;

import org.springframework.roo.addon.test.RooIntegrationTest;
import org.springframework.test.context.ContextConfiguration;

import com.shekhar.roo.conference.domain.Conference;
import org.junit.Test;

@RooIntegrationTest(entity = Conference.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
public class ConferenceIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }
}
