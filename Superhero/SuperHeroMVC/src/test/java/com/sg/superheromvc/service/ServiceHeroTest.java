/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superheromvc.service;

import com.sg.superheromvc.model.Hero;
import com.sg.superheromvc.model.Hero_has_Organization;
import com.sg.superheromvc.model.Sighting_has_Hero;
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
public class ServiceHeroTest {
    private ServiceLayerHero serviceLayerHero;
    private ServiceLayerLocation serviceLoc;
    private ServiceLayerSightingHero serviceSightHero;
    private ServiceLayerSighting serviceSight;
    private ServiceLayerHeroOrg serviceHeroOrg;
    
    public ServiceHeroTest() {
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
        serviceLayerHero = ctx.getBean("ServiceHero", ServiceLayerHero.class);
        
        serviceSightHero = ctx.getBean("ServiceSightingHero", ServiceLayerSightingHero.class); 
       
        serviceHeroOrg = ctx.getBean("ServiceHeroOrg", ServiceLayerHeroOrg.class);
        
       
        List<Hero_has_Organization> heroOrgs = serviceHeroOrg.getAllHeroOrgs();
        for (Hero_has_Organization currentHeroOrg : heroOrgs) {           
            serviceHeroOrg.deleteHeroOrg(currentHeroOrg.getHero_idHero());
        }
        
        
        List<Hero> heroes = serviceLayerHero.getAllHeros();
        for (Hero currentHero : heroes) {           
            serviceLayerHero.deleteHero(currentHero.getIdHero());
        }
        
       
       List<Sighting_has_Hero> sightsHeros = serviceSightHero.getAllSightingsForHero();
        for (Sighting_has_Hero currentSH : sightsHeros) {
            serviceSightHero.deleteSightingForHero(currentSH.getHero_idHero());
        }
        
        
      
    }
    
    
     @After
    public void tearDown() {
    }

    @Test
    public void addGetDeleteHero() {
        // Create new Hero
        Hero hr = new Hero();
        hr.setNameHero("Spider");
        hr.setDescriptionHero("Big Boy");
        hr.setPowerHero("Flying");
        serviceLayerHero.addHero(hr);
        Hero fromDb = serviceLayerHero.getHero(hr.getIdHero());
        assertEquals(fromDb, hr);
        serviceLayerHero.deleteHero(hr.getIdHero());
        assertNull(serviceLayerHero.getHero(hr.getIdHero()));
    }

    @Test
    public void addUpdateHero() {
        // Create new contact
        Hero hr = new Hero();
        hr.setNameHero("Spider");
        hr.setDescriptionHero("Big Boy");
        hr.setPowerHero("Flying");
        serviceLayerHero.addHero(hr);
      
        hr.setDescriptionHero("Updated Boy");
        serviceLayerHero.updateHero(hr);
        Hero fromDb = serviceLayerHero.getHero(hr.getIdHero());
        assertEquals(fromDb, hr);
    }

    @Test
    public void getAllHeroes() {
        // Create new contact
        Hero hr = new Hero();
        hr.setNameHero("Spider");
        hr.setDescriptionHero("Big Boy");
        hr.setPowerHero("Flying");
        serviceLayerHero.addHero(hr);
        // Create new contact
        Hero hr1 = new Hero();
        hr1.setNameHero("Blue Boy");
        hr1.setDescriptionHero("Fat and Ugly");
        hr1.setPowerHero("Fire");
        serviceLayerHero.addHero(hr1);
        
        List<Hero> hList = serviceLayerHero.getAllHeros();
        assertEquals(hList.size(), 2);
    }

}
