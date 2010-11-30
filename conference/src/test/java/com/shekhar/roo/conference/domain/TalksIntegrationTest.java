package com.shekhar.roo.conference.domain;

import org.springframework.roo.addon.test.RooIntegrationTest;
import org.springframework.test.context.ContextConfiguration;

import com.shekhar.roo.conference.domain.Talks;
import org.junit.Test;

@RooIntegrationTest(entity = Talks.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
public class TalksIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }
}
