// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.shekhar.playground.roo.poll.domain;

import com.shekhar.playground.roo.poll.domain.AnswerDataOnDemand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect AnswerIntegrationTest_Roo_IntegrationTest {
    
    declare @type: AnswerIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: AnswerIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml");
    
    declare @type: AnswerIntegrationTest: @Transactional;
    
    @Autowired
    private AnswerDataOnDemand AnswerIntegrationTest.dod;
    
    @Test
    public void AnswerIntegrationTest.testCountAnswers() {
        org.junit.Assert.assertNotNull("Data on demand for 'Answer' failed to initialize correctly", dod.getRandomAnswer());
        long count = com.shekhar.playground.roo.poll.domain.Answer.countAnswers();
        org.junit.Assert.assertTrue("Counter for 'Answer' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void AnswerIntegrationTest.testFindAnswer() {
        com.shekhar.playground.roo.poll.domain.Answer obj = dod.getRandomAnswer();
        org.junit.Assert.assertNotNull("Data on demand for 'Answer' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Answer' failed to provide an identifier", id);
        obj = com.shekhar.playground.roo.poll.domain.Answer.findAnswer(id);
        org.junit.Assert.assertNotNull("Find method for 'Answer' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'Answer' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void AnswerIntegrationTest.testFindAllAnswers() {
        org.junit.Assert.assertNotNull("Data on demand for 'Answer' failed to initialize correctly", dod.getRandomAnswer());
        long count = com.shekhar.playground.roo.poll.domain.Answer.countAnswers();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'Answer', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<com.shekhar.playground.roo.poll.domain.Answer> result = com.shekhar.playground.roo.poll.domain.Answer.findAllAnswers();
        org.junit.Assert.assertNotNull("Find all method for 'Answer' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'Answer' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void AnswerIntegrationTest.testFindAnswerEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'Answer' failed to initialize correctly", dod.getRandomAnswer());
        long count = com.shekhar.playground.roo.poll.domain.Answer.countAnswers();
        if (count > 20) count = 20;
        java.util.List<com.shekhar.playground.roo.poll.domain.Answer> result = com.shekhar.playground.roo.poll.domain.Answer.findAnswerEntries(0, (int) count);
        org.junit.Assert.assertNotNull("Find entries method for 'Answer' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'Answer' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void AnswerIntegrationTest.testFlush() {
        com.shekhar.playground.roo.poll.domain.Answer obj = dod.getRandomAnswer();
        org.junit.Assert.assertNotNull("Data on demand for 'Answer' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Answer' failed to provide an identifier", id);
        obj = com.shekhar.playground.roo.poll.domain.Answer.findAnswer(id);
        org.junit.Assert.assertNotNull("Find method for 'Answer' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyAnswer(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'Answer' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void AnswerIntegrationTest.testMerge() {
        com.shekhar.playground.roo.poll.domain.Answer obj = dod.getRandomAnswer();
        org.junit.Assert.assertNotNull("Data on demand for 'Answer' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Answer' failed to provide an identifier", id);
        obj = com.shekhar.playground.roo.poll.domain.Answer.findAnswer(id);
        boolean modified =  dod.modifyAnswer(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        com.shekhar.playground.roo.poll.domain.Answer merged = (com.shekhar.playground.roo.poll.domain.Answer) obj.merge();
        obj.flush();
        org.junit.Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        org.junit.Assert.assertTrue("Version for 'Answer' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void AnswerIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'Answer' failed to initialize correctly", dod.getRandomAnswer());
        com.shekhar.playground.roo.poll.domain.Answer obj = dod.getNewTransientAnswer(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'Answer' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'Answer' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'Answer' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void AnswerIntegrationTest.testRemove() {
        com.shekhar.playground.roo.poll.domain.Answer obj = dod.getRandomAnswer();
        org.junit.Assert.assertNotNull("Data on demand for 'Answer' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Answer' failed to provide an identifier", id);
        obj = com.shekhar.playground.roo.poll.domain.Answer.findAnswer(id);
        obj.remove();
        obj.flush();
        org.junit.Assert.assertNull("Failed to remove 'Answer' with identifier '" + id + "'", com.shekhar.playground.roo.poll.domain.Answer.findAnswer(id));
    }
    
}
