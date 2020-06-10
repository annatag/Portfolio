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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class DaoLocationTest {

    private DaoLocation daoLoc;
    private DaoHero daoHero;
    private DaoSighting daoSight;
    private DaoSightingHero daoSightHero;
    private DaoOrg daoOrg;
    private DaoHeroOrg daoHeroOrg;

    public DaoLocationTest() {
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
        daoLoc = ctx.getBean("DaoLocation", DaoLocation.class);
        daoSight = ctx.getBean("DaoSighting", DaoSighting.class);
        daoHero = ctx.getBean("DaoHero", DaoHero.class);
        daoSightHero = ctx.getBean("DaoSightingHero", DaoSightingHero.class);
        daoOrg = ctx.getBean("DaoOrg", DaoOrg.class);
        daoHeroOrg = ctx.getBean("DaoHeroOrg", DaoHeroOrg.class);

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
    public void addGetDeleteLocation() {
        // Create new Location
        Location loc = new Location();
        loc.setNameLocation("Eifel Tower");
        loc.setDescriptionLocation("2nd floor");
        loc.setCoordinates("10002-2345");
        loc.setAddressLocation("122 Fifth Ave, Paris, France");
        daoLoc.addLocation(loc);
        Location fromDb = daoLoc.getLocation(loc.getIdLocation());
        assertEquals(fromDb, loc);
        daoLoc.deleteLocation(loc.getIdLocation());
        assertNull(daoLoc.getLocation(loc.getIdLocation()));
    }

    @Test
    public void addUpdateLocation() {
        // Create new Location
        Location loc = new Location();
        loc.setNameLocation("Eifel Tower");
        loc.setDescriptionLocation("2nd floor");
        loc.setCoordinates("10002-2345");
        loc.setAddressLocation("122 Fifth Ave, Paris, France");
        daoLoc.addLocation(loc);

        loc.setNameLocation("Updated Eifel Tower");
        daoLoc.updateLocation(loc);
        Location fromDb = daoLoc.getLocation(loc.getIdLocation());
        assertEquals(fromDb, loc);

    }

    @Test
    public void getAllLocations() {

        Location loc = new Location();
        loc.setNameLocation("Eifel Tower");
        loc.setDescriptionLocation("2nd floor");
        loc.setCoordinates("10002-2345");
        loc.setAddressLocation("122 Fifth Ave, Paris, France");
        daoLoc.addLocation(loc);

        Location loc1 = new Location();
        loc1.setNameLocation("Berlin Wall");
        loc1.setDescriptionLocation("East side");
        loc1.setCoordinates("124555-0000");
        loc1.setAddressLocation("12 Shwarz Ave, Berlin, Germany");
        daoLoc.addLocation(loc1);

        List<Location> lList = daoLoc.getAllLocations();
        assertEquals(lList.size(), 2);

    }

    @Test
    public void getAllLocationByHero() throws ParseException {
        Hero hr1 = new Hero();
        hr1.setNameHero("Spider");
        hr1.setDescriptionHero("Big Boy");
        hr1.setPowerHero("Flying");
        daoHero.addHero(hr1);
        

        Location loc1 = new Location();
        loc1.setNameLocation("Berlin Wall");
        loc1.setDescriptionLocation("East side");
        loc1.setCoordinates("124555-0000");
        loc1.setAddressLocation("12 Shwarz Ave, Berlin, Germany");
        daoLoc.addLocation(loc1);

        Location loc2 = new Location();
        loc2.setNameLocation("Empire State Building");
        loc2.setDescriptionLocation("102 floors");
        loc2.setCoordinates("45635-6764");
        loc2.setAddressLocation("5-th Ave, New York, USA");
        daoLoc.addLocation(loc2);

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");

        Sighting sight1 = new Sighting();
        Date date1 = sdf.parse("2010-06-01");
        sight1.setDateSighting(date1);
        sight1.setLocation_idLocation(loc1.getIdLocation());
        daoSight.addSighting(sight1);

        Sighting sight2 = new Sighting();

        Date date2 = sdf.parse("2010-10-10");
        sight2.setDateSighting(date2);
        sight2.setLocation_idLocation(loc2.getIdLocation());
        daoSight.addSighting(sight2);

        
        Sighting_has_Hero sh1 = new Sighting_has_Hero();
        sh1.setHero_idHero(hr1.getIdHero());
        sh1.setSighting_idSighSighting(sight1.getIdSighting());
        daoSightHero.addSightingForHero(sh1);
        
        Sighting_has_Hero sh2 = new Sighting_has_Hero();
        sh2.setHero_idHero(hr1.getIdHero());
        sh2.setSighting_idSighSighting(sight2.getIdSighting());
        daoSightHero.addSightingForHero(sh2);
        
        int idHeroFromDb = daoHero.getHero(hr1.getIdHero()).getIdHero();

        List<Location> locationsByHero = daoLoc.getAllLocationByHero(idHeroFromDb);
        assertEquals(locationsByHero.size(), 2);

    }
}
