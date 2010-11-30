// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.shekhar.roo.conference.domain;

import com.shekhar.roo.conference.domain.TalksDataOnDemand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect TalksIntegrationTest_Roo_IntegrationTest {
    
    declare @type: TalksIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: TalksIntegrationTest: @Transactional;
    
    @Autowired
    private TalksDataOnDemand TalksIntegrationTest.dod;
    
    @Test
    public void TalksIntegrationTest.testCountTalkses() {
        org.junit.Assert.assertNotNull("Data on demand for 'Talks' failed to initialize correctly", dod.getRandomTalks());
        long count = com.shekhar.roo.conference.domain.Talks.countTalkses();
        org.junit.Assert.assertTrue("Counter for 'Talks' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void TalksIntegrationTest.testFindTalks() {
        com.shekhar.roo.conference.domain.Talks obj = dod.getRandomTalks();
        org.junit.Assert.assertNotNull("Data on demand for 'Talks' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Talks' failed to provide an identifier", id);
        obj = com.shekhar.roo.conference.domain.Talks.findTalks(id);
        org.junit.Assert.assertNotNull("Find method for 'Talks' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'Talks' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void TalksIntegrationTest.testFindAllTalkses() {
        org.junit.Assert.assertNotNull("Data on demand for 'Talks' failed to initialize correctly", dod.getRandomTalks());
        long count = com.shekhar.roo.conference.domain.Talks.countTalkses();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'Talks', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<com.shekhar.roo.conference.domain.Talks> result = com.shekhar.roo.conference.domain.Talks.findAllTalkses();
        org.junit.Assert.assertNotNull("Find all method for 'Talks' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'Talks' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void TalksIntegrationTest.testFindTalksEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'Talks' failed to initialize correctly", dod.getRandomTalks());
        long count = com.shekhar.roo.conference.domain.Talks.countTalkses();
        if (count > 20) count = 20;
        java.util.List<com.shekhar.roo.conference.domain.Talks> result = com.shekhar.roo.conference.domain.Talks.findTalksEntries(0, (int) count);
        org.junit.Assert.assertNotNull("Find entries method for 'Talks' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'Talks' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void TalksIntegrationTest.testFlush() {
        com.shekhar.roo.conference.domain.Talks obj = dod.getRandomTalks();
        org.junit.Assert.assertNotNull("Data on demand for 'Talks' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Talks' failed to provide an identifier", id);
        obj = com.shekhar.roo.conference.domain.Talks.findTalks(id);
        org.junit.Assert.assertNotNull("Find method for 'Talks' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyTalks(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'Talks' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void TalksIntegrationTest.testMerge() {
        com.shekhar.roo.conference.domain.Talks obj = dod.getRandomTalks();
        org.junit.Assert.assertNotNull("Data on demand for 'Talks' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Talks' failed to provide an identifier", id);
        obj = com.shekhar.roo.conference.domain.Talks.findTalks(id);
        boolean modified =  dod.modifyTalks(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        com.shekhar.roo.conference.domain.Talks merged = (com.shekhar.roo.conference.domain.Talks) obj.merge();
        obj.flush();
        org.junit.Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        org.junit.Assert.assertTrue("Version for 'Talks' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void TalksIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'Talks' failed to initialize correctly", dod.getRandomTalks());
        com.shekhar.roo.conference.domain.Talks obj = dod.getNewTransientTalks(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'Talks' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'Talks' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'Talks' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void TalksIntegrationTest.testRemove() {
        com.shekhar.roo.conference.domain.Talks obj = dod.getRandomTalks();
        org.junit.Assert.assertNotNull("Data on demand for 'Talks' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Talks' failed to provide an identifier", id);
        obj = com.shekhar.roo.conference.domain.Talks.findTalks(id);
        obj.remove();
        obj.flush();
        org.junit.Assert.assertNull("Failed to remove 'Talks' with identifier '" + id + "'", com.shekhar.roo.conference.domain.Talks.findTalks(id));
    }
    
}
