package com.shekhar.roo.conference.domain;

import org.springframework.roo.addon.test.RooIntegrationTest;
import com.shekhar.roo.conference.domain.Conference;
import org.junit.Test;

@RooIntegrationTest(entity = Conference.class)
public class ConferenceIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }
}
