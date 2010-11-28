// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.springsource.vote.domain;

import com.springsource.vote.domain.ChoiceDataOnDemand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect ChoiceIntegrationTest_Roo_IntegrationTest {
    
    declare @type: ChoiceIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: ChoiceIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml");
    
    declare @type: ChoiceIntegrationTest: @Transactional;
    
    @Autowired
    private ChoiceDataOnDemand ChoiceIntegrationTest.dod;
    
    @Test
    public void ChoiceIntegrationTest.testCountChoices() {
        org.junit.Assert.assertNotNull("Data on demand for 'Choice' failed to initialize correctly", dod.getRandomChoice());
        long count = com.springsource.vote.domain.Choice.countChoices();
        org.junit.Assert.assertTrue("Counter for 'Choice' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void ChoiceIntegrationTest.testFindChoice() {
        com.springsource.vote.domain.Choice obj = dod.getRandomChoice();
        org.junit.Assert.assertNotNull("Data on demand for 'Choice' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Choice' failed to provide an identifier", id);
        obj = com.springsource.vote.domain.Choice.findChoice(id);
        org.junit.Assert.assertNotNull("Find method for 'Choice' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'Choice' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void ChoiceIntegrationTest.testFindAllChoices() {
        org.junit.Assert.assertNotNull("Data on demand for 'Choice' failed to initialize correctly", dod.getRandomChoice());
        long count = com.springsource.vote.domain.Choice.countChoices();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'Choice', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<com.springsource.vote.domain.Choice> result = com.springsource.vote.domain.Choice.findAllChoices();
        org.junit.Assert.assertNotNull("Find all method for 'Choice' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'Choice' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void ChoiceIntegrationTest.testFindChoiceEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'Choice' failed to initialize correctly", dod.getRandomChoice());
        long count = com.springsource.vote.domain.Choice.countChoices();
        if (count > 20) count = 20;
        java.util.List<com.springsource.vote.domain.Choice> result = com.springsource.vote.domain.Choice.findChoiceEntries(0, (int) count);
        org.junit.Assert.assertNotNull("Find entries method for 'Choice' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'Choice' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void ChoiceIntegrationTest.testFlush() {
        com.springsource.vote.domain.Choice obj = dod.getRandomChoice();
        org.junit.Assert.assertNotNull("Data on demand for 'Choice' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Choice' failed to provide an identifier", id);
        obj = com.springsource.vote.domain.Choice.findChoice(id);
        org.junit.Assert.assertNotNull("Find method for 'Choice' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyChoice(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'Choice' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void ChoiceIntegrationTest.testMerge() {
        com.springsource.vote.domain.Choice obj = dod.getRandomChoice();
        org.junit.Assert.assertNotNull("Data on demand for 'Choice' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Choice' failed to provide an identifier", id);
        obj = com.springsource.vote.domain.Choice.findChoice(id);
        boolean modified =  dod.modifyChoice(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        com.springsource.vote.domain.Choice merged = (com.springsource.vote.domain.Choice) obj.merge();
        obj.flush();
        org.junit.Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        org.junit.Assert.assertTrue("Version for 'Choice' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void ChoiceIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'Choice' failed to initialize correctly", dod.getRandomChoice());
        com.springsource.vote.domain.Choice obj = dod.getNewTransientChoice(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'Choice' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'Choice' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'Choice' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void ChoiceIntegrationTest.testRemove() {
        com.springsource.vote.domain.Choice obj = dod.getRandomChoice();
        org.junit.Assert.assertNotNull("Data on demand for 'Choice' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Choice' failed to provide an identifier", id);
        obj = com.springsource.vote.domain.Choice.findChoice(id);
        obj.remove();
        obj.flush();
        org.junit.Assert.assertNull("Failed to remove 'Choice' with identifier '" + id + "'", com.springsource.vote.domain.Choice.findChoice(id));
    }
    
}
