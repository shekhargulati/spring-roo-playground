package com.shekhar.roo.conference.domain;

import org.springframework.roo.addon.test.RooIntegrationTest;
import org.springframework.test.context.ContextConfiguration;

import com.shekhar.roo.conference.domain.Speaker;
import org.junit.Test;

@RooIntegrationTest(entity = Speaker.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
public class SpeakerIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }
}
