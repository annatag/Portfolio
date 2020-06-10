/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superheromvc.service;

import com.sg.superheromvc.dao.DaoOrg;
import com.sg.superheromvc.model.Organization;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Anna
 */
public class ServiceOrgTest {
    private ServiceLayerOrg serviceOrg;
    

    public ServiceOrgTest() {
    }
    
    
    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        // ask Spring for our DAO
        ApplicationContext ctx
            = new ClassPathXmlApplicationContext(
                        "test-applicationContext.xml");
        serviceOrg = ctx.getBean("ServiceOrg", ServiceLayerOrg.class);
       
        List<Organization> orgs = serviceOrg.getAllOrgs();
        for (Organization currentOrg : orgs) {
            serviceOrg.deleteOrg(currentOrg.getIdOrganization());
        }
    }
    
    
     @After
    public void tearDown() {
    }

    @Test
    public void addGetDeleteOrg() {
        // Create new Organization
        Organization org = new Organization();
        org.setNameOrg("Fireballs");
        org.setDescriptionOrg("Heroes with fire capabilities");
        org.setContactInfoOrg("763-333-9890");
        serviceOrg.addOrg(org);
        Organization fromDb = serviceOrg.getOrg(org.getIdOrganization());
        assertEquals(fromDb, org);
        serviceOrg.deleteOrg(org.getIdOrganization());
        assertNull(serviceOrg.getOrg(org.getIdOrganization()));
    }

    @Test
    public void addUpdateOrg() {
        Organization org = new Organization();
        org.setNameOrg("Fireballs");
        org.setDescriptionOrg("Heroes with fire capabilities");
        org.setContactInfoOrg("763-333-9890");
        serviceOrg.addOrg(org);
      
        org.setDescriptionOrg("Updated Description");
        serviceOrg.updateOrg(org);
        Organization fromDb = serviceOrg.getOrg(org.getIdOrganization());
        assertEquals(fromDb, org);
    }

    @Test
    public void getAllOrgs() {
        Organization org = new Organization();
        org.setNameOrg("Fireballs");
        org.setDescriptionOrg("Heroes with fire capabilities");
        org.setContactInfoOrg("763-333-9890");
        serviceOrg.addOrg(org);
      
        Organization org1 = new Organization();
        org1.setNameOrg("Waterfalls");
        org1.setDescriptionOrg("Heroes that can swim");
        org1.setContactInfoOrg("763-555-4444");
        serviceOrg.addOrg(org1);
      
        
        List<Organization> oList = serviceOrg.getAllOrgs();
        assertEquals(oList.size(), 2);
    }
}
