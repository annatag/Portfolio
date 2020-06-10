/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superheromvc.service;

import com.sg.superheromvc.model.Hero;
import com.sg.superheromvc.model.Hero_has_Organization;
import com.sg.superheromvc.model.Organization;
import com.sg.superheromvc.model.Sighting_has_Hero;
import java.util.List;
import static junit.framework.Assert.assertEquals;
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
public class ServiceHeroOrgTest {
   
 

    private ServiceLayerHero serviceLayerHero;
    private ServiceLayerLocation serviceLoc;
    private ServiceLayerSightingHero serviceSightHero;
    private ServiceLayerSighting serviceSight;
    private ServiceLayerHeroOrg serviceHeroOrg;
    private ServiceLayerOrg serviceOrg;
    
    public ServiceHeroOrgTest() {
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
        
        serviceOrg = ctx.getBean("ServiceOrg", ServiceLayerOrg.class);
       
        serviceSightHero = ctx.getBean("ServiceSightingHero", ServiceLayerSightingHero.class); 
       
        serviceHeroOrg = ctx.getBean("ServiceHeroOrg", ServiceLayerHeroOrg.class);
        
        
       
        List<Hero_has_Organization> heroOrgs = serviceHeroOrg.getAllHeroOrgs();
        for (Hero_has_Organization currentHeroOrg : heroOrgs) {           
            serviceHeroOrg.deleteHeroOrg(currentHeroOrg.getHero_idHero());
        }
        
        
        List<Organization> orgs = serviceOrg.getAllOrgs();
        for (Organization currentOrg: orgs){
        serviceOrg.deleteOrg(currentOrg.getIdOrganization());
        };
        
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
    public void addGetDeleteOrg() {
        Hero hr = new Hero();
        hr.setNameHero("Spider");
        hr.setDescriptionHero("Big Boy");
        hr.setPowerHero("Flying");
        serviceLayerHero.addHero(hr);

        Organization org = new Organization();
        org.setNameOrg("Fireballs");
        org.setDescriptionOrg("Heroes with fire capabilities");
        org.setContactInfoOrg("763-333-9890");
        serviceOrg.addOrg(org);

        Hero_has_Organization HeroOrg = new Hero_has_Organization();
        HeroOrg.setHero_idHero(hr.getIdHero());
        HeroOrg.setOrg(org);

        serviceHeroOrg.addHeroOrg(HeroOrg);
        int idHeroOrg = HeroOrg.getIdHeroOrg();
        Hero_has_Organization fromDb = serviceHeroOrg.getHeroOrg(idHeroOrg);

        assertEquals(fromDb.getIdHeroOrg(), HeroOrg.getIdHeroOrg()); //passes
        serviceHeroOrg.deleteHeroOrg(HeroOrg.getHero_idHero()); //passes
        assertEquals(serviceHeroOrg.getAllHeroOrgs().size(), 0);
    }

    @Test
    public void addUpdateHeroOrg() {
        Hero hr1 = new Hero();
        hr1.setNameHero("Blue Boy");
        hr1.setDescriptionHero("Fat and Ugly");
        hr1.setPowerHero("Fire");
        serviceLayerHero.addHero(hr1);
       
        Organization org = new Organization();
        org.setNameOrg("Fireballs");
        org.setDescriptionOrg("Heroes with fire capabilities");
        org.setContactInfoOrg("763-333-9890");
        serviceOrg.addOrg(org);
        

        Hero_has_Organization HeroOrg = new Hero_has_Organization();
        HeroOrg.setHero_idHero(hr1.getIdHero());
        HeroOrg.setOrg(org);
        serviceHeroOrg.addHeroOrg(HeroOrg);
        
        int idHeroOrg = HeroOrg.getIdHeroOrg();
        Hero_has_Organization fromDb = serviceHeroOrg.getHeroOrg(idHeroOrg);
        
        
        Hero hr2 = new Hero();
        hr2.setNameHero("Spider");
        hr2.setDescriptionHero("Big Boy");
        hr2.setPowerHero("Flying");
        serviceLayerHero.addHero(hr2);

        
        Organization org2 = new Organization();
        org2.setNameOrg("Waterfalls");
        org2.setDescriptionOrg("Heroes that can swim");
        org2.setContactInfoOrg("763-555-4444");
        serviceOrg.addOrg(org2);
        
        fromDb.setHero_idHero(hr2.getIdHero());
        fromDb.setOrg(org2);
        
        serviceHeroOrg.updateHeroOrg(fromDb);

        Hero_has_Organization updatedHeroOrg = serviceHeroOrg.getHeroOrg(fromDb.getIdHeroOrg());
        assertEquals(updatedHeroOrg.getHero_idHero(), hr2.getIdHero());
        assertEquals(updatedHeroOrg.getOrg().getIdOrganization(), org2.getIdOrganization());
    }

    @Test
    public void getAllHeroesByOrg() {
        Hero hr1 = new Hero();
        hr1.setNameHero("Ironman");
        hr1.setDescriptionHero("Fat and Ugly");
        hr1.setPowerHero("Fire");
        serviceLayerHero.addHero(hr1);
             
        Hero hr2 = new Hero();
        hr2.setNameHero("Spider");
        hr2.setDescriptionHero("Big Boy");
        hr2.setPowerHero("Flying");
        serviceLayerHero.addHero(hr2);
        
        
        Hero hr3 = new Hero();
        hr3.setNameHero("Batman");
        hr3.setDescriptionHero("Black wongs");
        hr3.setPowerHero("Claws");
        serviceLayerHero.addHero(hr3);
        
        Organization org1 = new Organization();
        org1.setNameOrg("Fireballs");
        org1.setDescriptionOrg("Heroes with fire capabilities");
        org1.setContactInfoOrg("763-333-9890");
        serviceOrg.addOrg(org1);

        
        Organization org2 = new Organization();
        org2.setNameOrg("Waterfalls");
        org2.setDescriptionOrg("Heroes that can swim");
        org2.setContactInfoOrg("763-555-4444");
        serviceOrg.addOrg(org2);
        
        
        Hero_has_Organization HeroOrg1 = new Hero_has_Organization();
        HeroOrg1.setHero_idHero(hr1.getIdHero());
        HeroOrg1.setOrg(org1);
        serviceHeroOrg.addHeroOrg(HeroOrg1);
        
     //   int idHeroOrg = HeroOrg1.getIdHeroOrg();
       
        
        Hero_has_Organization HeroOrg2 = new Hero_has_Organization();
        HeroOrg2.setHero_idHero(hr2.getIdHero());
        HeroOrg2.setOrg(org1);
        serviceHeroOrg.addHeroOrg(HeroOrg2);
        
        
        Hero_has_Organization HeroOrg3 = new Hero_has_Organization();
        HeroOrg3.setHero_idHero(hr3.getIdHero());
        HeroOrg3.setOrg(org2);
        serviceHeroOrg.addHeroOrg(HeroOrg3);
        
        List<Hero> fromDaoList = serviceHeroOrg.getAllHerosByOrg(org1.getNameOrg());
        assertEquals(fromDaoList.size(), 2);
    }

}


