// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kingkey.qn.domain;

import com.kingkey.qn.domain.PartDataOnDemand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect PartIntegrationTest_Roo_IntegrationTest {
    
    declare @type: PartIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: PartIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml");
    
    declare @type: PartIntegrationTest: @Transactional;
    
    @Autowired
    private PartDataOnDemand PartIntegrationTest.dod;
    
    @Test
    public void PartIntegrationTest.testCountParts() {
        org.junit.Assert.assertNotNull("Data on demand for 'Part' failed to initialize correctly", dod.getRandomPart());
        long count = com.kingkey.qn.domain.Part.countParts();
        org.junit.Assert.assertTrue("Counter for 'Part' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void PartIntegrationTest.testFindPart() {
        com.kingkey.qn.domain.Part obj = dod.getRandomPart();
        org.junit.Assert.assertNotNull("Data on demand for 'Part' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Part' failed to provide an identifier", id);
        obj = com.kingkey.qn.domain.Part.findPart(id);
        org.junit.Assert.assertNotNull("Find method for 'Part' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'Part' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void PartIntegrationTest.testFindAllParts() {
        org.junit.Assert.assertNotNull("Data on demand for 'Part' failed to initialize correctly", dod.getRandomPart());
        long count = com.kingkey.qn.domain.Part.countParts();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'Part', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<com.kingkey.qn.domain.Part> result = com.kingkey.qn.domain.Part.findAllParts();
        org.junit.Assert.assertNotNull("Find all method for 'Part' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'Part' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void PartIntegrationTest.testFindPartEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'Part' failed to initialize correctly", dod.getRandomPart());
        long count = com.kingkey.qn.domain.Part.countParts();
        if (count > 20) count = 20;
        java.util.List<com.kingkey.qn.domain.Part> result = com.kingkey.qn.domain.Part.findPartEntries(0, (int) count);
        org.junit.Assert.assertNotNull("Find entries method for 'Part' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'Part' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void PartIntegrationTest.testFlush() {
        com.kingkey.qn.domain.Part obj = dod.getRandomPart();
        org.junit.Assert.assertNotNull("Data on demand for 'Part' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Part' failed to provide an identifier", id);
        obj = com.kingkey.qn.domain.Part.findPart(id);
        org.junit.Assert.assertNotNull("Find method for 'Part' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyPart(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'Part' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void PartIntegrationTest.testMerge() {
        com.kingkey.qn.domain.Part obj = dod.getRandomPart();
        org.junit.Assert.assertNotNull("Data on demand for 'Part' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Part' failed to provide an identifier", id);
        obj = com.kingkey.qn.domain.Part.findPart(id);
        boolean modified =  dod.modifyPart(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        com.kingkey.qn.domain.Part merged = (com.kingkey.qn.domain.Part) obj.merge();
        obj.flush();
        org.junit.Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        org.junit.Assert.assertTrue("Version for 'Part' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void PartIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'Part' failed to initialize correctly", dod.getRandomPart());
        com.kingkey.qn.domain.Part obj = dod.getNewTransientPart(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'Part' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'Part' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'Part' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void PartIntegrationTest.testRemove() {
        com.kingkey.qn.domain.Part obj = dod.getRandomPart();
        org.junit.Assert.assertNotNull("Data on demand for 'Part' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Part' failed to provide an identifier", id);
        obj = com.kingkey.qn.domain.Part.findPart(id);
        obj.remove();
        obj.flush();
        org.junit.Assert.assertNull("Failed to remove 'Part' with identifier '" + id + "'", com.kingkey.qn.domain.Part.findPart(id));
    }
    
}