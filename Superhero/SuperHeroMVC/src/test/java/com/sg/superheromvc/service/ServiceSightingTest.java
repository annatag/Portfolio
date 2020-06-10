/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superheromvc.service;

import com.sg.superheromvc.dao.DaoHero;
import com.sg.superheromvc.dao.DaoLocation;
import com.sg.superheromvc.dao.DaoSighting;
import com.sg.superheromvc.dao.DaoSightingHero;
import com.sg.superheromvc.model.Hero;
import com.sg.superheromvc.model.Hero_has_Organization;
import com.sg.superheromvc.model.Location;
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
public class ServiceSightingTest {
    private ServiceLayerHero serviceHero;
    private ServiceLayerLocation serviceLoc;
    private ServiceLayerSightingHero serviceSightHero;
    private ServiceLayerSighting serviceSight;
    private ServiceLayerHeroOrg serviceHeroOrg;
    
     public ServiceSightingTest() {
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
        serviceLoc = ctx.getBean("ServiceLocation", ServiceLayerLocation.class);
        serviceSight = ctx.getBean("ServiceSighting", ServiceLayerSighting.class);
        serviceHero = ctx.getBean("ServiceHero", ServiceLayerHero.class);
        serviceSightHero = ctx.getBean("ServiceSightingHero", ServiceLayerSightingHero.class);
        
        List<Sighting_has_Hero> sightsHeros = serviceSightHero.getAllSightingsForHero();
        for (Sighting_has_Hero currentSH : sightsHeros) {
            serviceSightHero.deleteSightingHeroWithHeroId(currentSH.getHero_idHero());
        }

        List<Hero> heroes = serviceHero.getAllHeros();
        for (Hero currentHero : heroes) {
            serviceHero.deleteHero(currentHero.getIdHero());
        }

        List<Sighting> sightings = serviceSight.getAllSightings();
        for (Sighting currentSighting : sightings) {
            serviceSight.deleteSighting(currentSighting.getIdSighting());
        }

        List<Location> locations = serviceLoc.getAllLocations();
        for (Location currentLoc : locations) {
            serviceLoc.deleteLocation(currentLoc.getIdLocation());
        }
        
        
        
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addGetDeleteSighting() throws ParseException {

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
        
        Location loc1 = new Location();
        loc1.setNameLocation("Berlin Wall");
        loc1.setDescriptionLocation("East side");
        loc1.setCoordinates("124555-0000");
        loc1.setAddressLocation("12 Shwarz Ave, Berlin, Germany");
        serviceLoc.addLocation(loc1);
        int locId = loc1.getIdLocation();
        
        Sighting sight1 = new Sighting();
        Date date1 = sdf.parse("2010-06-01");
        sight1.setDateSighting(date1);
        sight1.setLocation_idLocation(locId);
        serviceSight.addSighting(sight1);
        Sighting fromDb = serviceSight.getSighting(sight1.getIdSighting());
        assertEquals(fromDb, sight1);
        serviceSight.deleteSighting(sight1.getIdSighting());
        assertNull(serviceSight.getSighting(sight1.getIdSighting()));
    }
    
    @Test
    public void addUpdateSighting() throws ParseException {
        
        
        Location loc1 = new Location();
        loc1.setNameLocation("Berlin Wall");
        loc1.setDescriptionLocation("East side");
        loc1.setCoordinates("124555-0000");
        loc1.setAddressLocation("12 Shwarz Ave, Berlin, Germany");
        serviceLoc.addLocation(loc1);
        int locId = loc1.getIdLocation();
        
        
        // Create new sighting
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
        Sighting sight2 = new Sighting();
        Date date2 = sdf.parse("2010-10-10");
        Date date3 = sdf.parse("2010-06-01");
        sight2.setDateSighting(date2);
        sight2.setLocation_idLocation(locId);
        serviceSight.addSighting(sight2);
       
      
        sight2.setDateSighting(date3);
        serviceSight.updateSighting(sight2);
        Sighting fromDb = serviceSight.getSighting(sight2.getIdSighting());
        assertEquals(fromDb, sight2);
    }


        @Test
    public void getAllSightings() throws ParseException {
        
        Location loc1 = new Location();
        loc1.setNameLocation("Berlin Wall");
        loc1.setDescriptionLocation("East side");
        loc1.setCoordinates("124555-0000");
        loc1.setAddressLocation("12 Shwarz Ave, Berlin, Germany");
        serviceLoc.addLocation(loc1);
        int locId = loc1.getIdLocation();
        
        
        Location loc2 = new Location();
        loc2.setNameLocation("Eifel Tower");
        loc2.setDescriptionLocation("2nd floor");
        loc2.setCoordinates("10002-2345");
        loc2.setAddressLocation("122 Fifth Ave, Paris, France");
        serviceLoc.addLocation(loc2);
        int locId2 = loc2.getIdLocation();
        
        // Create new sightings
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");

        Sighting sight1 = new Sighting();
        Date date1 = sdf.parse("2010-06-01");
        sight1.setDateSighting(date1);
        sight1.setLocation_idLocation(locId);
        serviceSight.addSighting(sight1);

        Sighting sight2 = new Sighting();
        Date date2 = sdf.parse("2010-10-10");
        sight2.setDateSighting(date2);
        sight2.setLocation_idLocation(locId2);
        serviceSight.addSighting(sight2);
        
        
        List<Sighting> sightingList = serviceSight.getAllSightings();
        assertEquals(sightingList.size(), 2);
    }
}