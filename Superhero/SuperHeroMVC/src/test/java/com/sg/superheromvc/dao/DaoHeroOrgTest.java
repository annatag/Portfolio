/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superheromvc.dao;

import com.sg.superheromvc.model.Hero;
import com.sg.superheromvc.model.Hero_has_Organization;
import com.sg.superheromvc.model.Location;
import com.sg.superheromvc.model.Organization;
import com.sg.superheromvc.model.Sighting;
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
public class DaoHeroOrgTest {

    private DaoHero daoHero;
    private DaoOrg daoOrg;
    private DaoHeroOrg daoHeroOrg;
    private DaoSighting daoSight;
    private DaoLocation daoLoc;
    private DaoSightingHero daoSightHero;

    public DaoHeroOrgTest() {
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
        daoHero = ctx.getBean("DaoHero", DaoHero.class);
        daoOrg = ctx.getBean("DaoOrg", DaoOrg.class);
        daoHeroOrg = ctx.getBean("DaoHeroOrg", DaoHeroOrg.class);
        daoSight = ctx.getBean("DaoSighting", DaoSighting.class);

        daoLoc = ctx.getBean("DaoLocation", DaoLocation.class); 

        daoSightHero = ctx.getBean("DaoSightingHero", DaoSightingHero.class); 

        List<Hero_has_Organization> herosOrgs = daoHeroOrg.getAllHeroesOrg();
        for (Hero_has_Organization currentHeroOrg : herosOrgs) {
            daoHeroOrg.deleteHeroOrg(currentHeroOrg.getHero_idHero());
        }

        List<Sighting_has_Hero> sightsHeros = daoSightHero.getAllSightingsForHero();
        for (Sighting_has_Hero currentSH : sightsHeros) {
            daoSightHero.deleteSightingHeroWithHeroId(currentSH.getHero_idHero());
        }

        List<Hero> heroes = daoHero.getAllHeroes();
        for (Hero currentHero : heroes) {
            daoHero.deleteHero(currentHero.getIdHero());
        }

        List<Sighting> sightings = daoSight.getAllSightings();
        for (Sighting currentSighting : sightings) {
            daoSight.deleteSighting(currentSighting.getIdSighting());
        }

        List<Location> locations = daoLoc.getAllLocations();
        for (Location currentLoc : locations) {
            daoLoc.deleteLocation(currentLoc.getIdLocation());
        }

    }

    @After
    public void tearDown() {
    }

    @Test
    public void addGetDeleteHeroOrg() {
        // Create new HeroOrg
        Hero hr = new Hero();
        hr.setNameHero("Spider");
        hr.setDescriptionHero("Big Boy");
        hr.setPowerHero("Flying");
        daoHero.addHero(hr);

        Organization org = new Organization();
        org.setNameOrg("Fireballs");
        org.setDescriptionOrg("Heroes with fire capabilities");
        org.setContactInfoOrg("763-333-9890");
        daoOrg.addOrg(org);

        Hero_has_Organization HeroOrg = new Hero_has_Organization();
        HeroOrg.setHero_idHero(hr.getIdHero());
        HeroOrg.setOrg(org);

        daoHeroOrg.addHeroOrg(HeroOrg);
        int idHeroOrg = HeroOrg.getIdHeroOrg();
        Hero_has_Organization fromDb = daoHeroOrg.getHeroOrg(idHeroOrg);

        assertEquals(fromDb.getIdHeroOrg(), HeroOrg.getIdHeroOrg()); //passes
        daoHeroOrg.deleteHeroOrg(HeroOrg.getHero_idHero()); //passes
        assertEquals(daoHeroOrg.getAllHeroesOrg().size(), 0);
    }

    @Test
    public void addUpdateHeroOrg() {
        Hero hr1 = new Hero();
        hr1.setNameHero("Blue Boy");
        hr1.setDescriptionHero("Fat and Ugly");
        hr1.setPowerHero("Fire");
        daoHero.addHero(hr1);
       
        Organization org = new Organization();
        org.setNameOrg("Fireballs");
        org.setDescriptionOrg("Heroes with fire capabilities");
        org.setContactInfoOrg("763-333-9890");
        daoOrg.addOrg(org);
        

        Hero_has_Organization HeroOrg = new Hero_has_Organization();
        HeroOrg.setHero_idHero(hr1.getIdHero());
        HeroOrg.setOrg(org);
        daoHeroOrg.addHeroOrg(HeroOrg);
        
        int idHeroOrg = HeroOrg.getIdHeroOrg();
        Hero_has_Organization fromDb = daoHeroOrg.getHeroOrg(idHeroOrg);
        
        
        Hero hr2 = new Hero();
        hr2.setNameHero("Spider");
        hr2.setDescriptionHero("Big Boy");
        hr2.setPowerHero("Flying");
        daoHero.addHero(hr2);

        
        Organization org2 = new Organization();
        org2.setNameOrg("Waterfalls");
        org2.setDescriptionOrg("Heroes that can swim");
        org2.setContactInfoOrg("763-555-4444");
        daoOrg.addOrg(org2);
        
        fromDb.setHero_idHero(hr2.getIdHero());
        fromDb.setOrg(org2);
        
        daoHeroOrg.updateHeroOrg(fromDb);

        Hero_has_Organization updatedHeroOrg = daoHeroOrg.getHeroOrg(fromDb.getIdHeroOrg());
        assertEquals(updatedHeroOrg.getHero_idHero(), hr2.getIdHero());
        assertEquals(updatedHeroOrg.getOrg().getIdOrganization(), org2.getIdOrganization());
    }

      @Test
   public void getAllHeroesByOrg() {
        Hero hr1 = new Hero();
        hr1.setNameHero("Ironman");
        hr1.setDescriptionHero("Fat and Ugly");
        hr1.setPowerHero("Fire");
        daoHero.addHero(hr1);
             
        Hero hr2 = new Hero();
        hr2.setNameHero("Spider");
        hr2.setDescriptionHero("Big Boy");
        hr2.setPowerHero("Flying");
        daoHero.addHero(hr2);
        
        
        Hero hr3 = new Hero();
        hr3.setNameHero("Batman");
        hr3.setDescriptionHero("Black wongs");
        hr3.setPowerHero("Claws");
        daoHero.addHero(hr3);
        
        Organization org1 = new Organization();
        org1.setNameOrg("Fireballs");
        org1.setDescriptionOrg("Heroes with fire capabilities");
        org1.setContactInfoOrg("763-333-9890");
        daoOrg.addOrg(org1);

        
        Organization org2 = new Organization();
        org2.setNameOrg("Waterfalls");
        org2.setDescriptionOrg("Heroes that can swim");
        org2.setContactInfoOrg("763-555-4444");
        daoOrg.addOrg(org2);
        
        
        Hero_has_Organization HeroOrg1 = new Hero_has_Organization();
        HeroOrg1.setHero_idHero(hr1.getIdHero());
        HeroOrg1.setOrg(org1);
        daoHeroOrg.addHeroOrg(HeroOrg1);
        
     //   int idHeroOrg = HeroOrg1.getIdHeroOrg();
       
        
        Hero_has_Organization HeroOrg2 = new Hero_has_Organization();
        HeroOrg2.setHero_idHero(hr2.getIdHero());
        HeroOrg2.setOrg(org1);
        daoHeroOrg.addHeroOrg(HeroOrg2);
        
        
        Hero_has_Organization HeroOrg3 = new Hero_has_Organization();
        HeroOrg3.setHero_idHero(hr3.getIdHero());
        HeroOrg3.setOrg(org2);
        daoHeroOrg.addHeroOrg(HeroOrg3);
        
        List<Hero> fromDaoList = daoHeroOrg.getAllHeroesByOrg(org1.getNameOrg());
        assertEquals(fromDaoList.size(), 2);
        
        
    }
    
     
}
