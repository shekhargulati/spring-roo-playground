// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.shekhar.roo.feedback.domain;

import com.shekhar.roo.feedback.domain.FeedbackDataOnDemand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect FeedbackIntegrationTest_Roo_IntegrationTest {
    
    declare @type: FeedbackIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: FeedbackIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml");
    
    declare @type: FeedbackIntegrationTest: @Transactional;
    
    @Autowired
    private FeedbackDataOnDemand FeedbackIntegrationTest.dod;
    
    @Test
    public void FeedbackIntegrationTest.testCountFeedbacks() {
        org.junit.Assert.assertNotNull("Data on demand for 'Feedback' failed to initialize correctly", dod.getRandomFeedback());
        long count = com.shekhar.roo.feedback.domain.Feedback.countFeedbacks();
        org.junit.Assert.assertTrue("Counter for 'Feedback' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void FeedbackIntegrationTest.testFindFeedback() {
        com.shekhar.roo.feedback.domain.Feedback obj = dod.getRandomFeedback();
        org.junit.Assert.assertNotNull("Data on demand for 'Feedback' failed to initialize correctly", obj);
        java.lang.Integer id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Feedback' failed to provide an identifier", id);
        obj = com.shekhar.roo.feedback.domain.Feedback.findFeedback(id);
        org.junit.Assert.assertNotNull("Find method for 'Feedback' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'Feedback' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void FeedbackIntegrationTest.testFindAllFeedbacks() {
        org.junit.Assert.assertNotNull("Data on demand for 'Feedback' failed to initialize correctly", dod.getRandomFeedback());
        long count = com.shekhar.roo.feedback.domain.Feedback.countFeedbacks();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'Feedback', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<com.shekhar.roo.feedback.domain.Feedback> result = com.shekhar.roo.feedback.domain.Feedback.findAllFeedbacks();
        org.junit.Assert.assertNotNull("Find all method for 'Feedback' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'Feedback' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void FeedbackIntegrationTest.testFindFeedbackEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'Feedback' failed to initialize correctly", dod.getRandomFeedback());
        long count = com.shekhar.roo.feedback.domain.Feedback.countFeedbacks();
        if (count > 20) count = 20;
        java.util.List<com.shekhar.roo.feedback.domain.Feedback> result = com.shekhar.roo.feedback.domain.Feedback.findFeedbackEntries(0, (int) count);
        org.junit.Assert.assertNotNull("Find entries method for 'Feedback' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'Feedback' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void FeedbackIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'Feedback' failed to initialize correctly", dod.getRandomFeedback());
        com.shekhar.roo.feedback.domain.Feedback obj = dod.getNewTransientFeedback(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'Feedback' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'Feedback' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'Feedback' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void FeedbackIntegrationTest.testRemove() {
        com.shekhar.roo.feedback.domain.Feedback obj = dod.getRandomFeedback();
        org.junit.Assert.assertNotNull("Data on demand for 'Feedback' failed to initialize correctly", obj);
        java.lang.Integer id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Feedback' failed to provide an identifier", id);
        obj = com.shekhar.roo.feedback.domain.Feedback.findFeedback(id);
        obj.remove();
        obj.flush();
        org.junit.Assert.assertNull("Failed to remove 'Feedback' with identifier '" + id + "'", com.shekhar.roo.feedback.domain.Feedback.findFeedback(id));
    }
    
}
