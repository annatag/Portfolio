/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superheromvc.dao;

import com.sg.superheromvc.model.Hero;
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
public class DaoSightingHeroTest {
  
    private DaoSighting daoSight;
    private DaoLocation daoLoc;
    private DaoSightingHero daoSightHero;
    private DaoHero daoHero;
    
    public DaoSightingHeroTest() {
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
        daoSight = ctx.getBean("DaoSighting", DaoSighting.class);
        
        daoLoc = ctx.getBean("DaoLocation", DaoLocation.class); //QUESTION DO I NEED THIS
        
        daoSightHero = ctx.getBean("DaoSightingHero", DaoSightingHero.class); //QUESTION: DP I need it here
       
        daoHero = ctx.getBean("DaoHero", DaoHero.class);

// remove all of the sightings/heros/locations/sights for heroes
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
    public void addGetDeleteSighting_Hero() throws ParseException {

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
        
        
        Hero hr1 = new Hero();
        hr1.setNameHero("Blue Boy");
        hr1.setDescriptionHero("Fat and Ugly");
        hr1.setPowerHero("Fire");
        daoHero.addHero(hr1);
        
        
        Location loc1 = new Location();
        loc1.setNameLocation("Berlin Wall");
        loc1.setDescriptionLocation("East side");
        loc1.setCoordinates("124555-0000");
        loc1.setAddressLocation("12 Shwarz Ave, Berlin, Germany");
        daoLoc.addLocation(loc1);
        int locId = loc1.getIdLocation();
        
        Sighting sight1 = new Sighting();
        Date date1 = sdf.parse("2010-06-01");
        sight1.setDateSighting(date1);
        sight1.setLocation_idLocation(locId);
        daoSight.addSighting(sight1);
       
        
        Sighting_has_Hero SH = new Sighting_has_Hero();
        SH.setHero_idHero(hr1.getIdHero());
        SH.setSighting_idSighSighting(sight1.getIdSighting());
        daoSightHero.addSightingForHero(SH);
        int idSH = SH.getIdSightingHero();
        Sighting_has_Hero fromDb = daoSightHero.getSightingForHero(idSH);
        
        
       
        assertEquals(fromDb.getIdSightingHero(), SH.getIdSightingHero()); //passes
        daoSightHero.deleteSightingHeroWithHeroId(SH.getHero_idHero()); //passes
        assertEquals(daoSightHero.getAllSightingsForHero().size(), 0); //passes
    }
    
    @Test
    public void addUpdateSightingHero() throws ParseException {
        
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
        
        
        Hero hr1 = new Hero();
        hr1.setNameHero("Blue Boy");
        hr1.setDescriptionHero("Fat and Ugly");
        hr1.setPowerHero("Fire");
        daoHero.addHero(hr1);
        
        
        Location loc1 = new Location();
        loc1.setNameLocation("Berlin Wall");
        loc1.setDescriptionLocation("East side");
        loc1.setCoordinates("124555-0000");
        loc1.setAddressLocation("12 Shwarz Ave, Berlin, Germany");
        daoLoc.addLocation(loc1);
        int locId = loc1.getIdLocation();
        
        Sighting sight1 = new Sighting();
        Date date1 = sdf.parse("2010-06-01");
        sight1.setDateSighting(date1);
        sight1.setLocation_idLocation(locId);
        daoSight.addSighting(sight1);
       
        
        Sighting_has_Hero SH = new Sighting_has_Hero();
        SH.setHero_idHero(hr1.getIdHero());
        SH.setSighting_idSighSighting(sight1.getIdSighting());
        daoSightHero.addSightingForHero(SH);
        int idSH = SH.getIdSightingHero();
        Sighting_has_Hero fromDb = daoSightHero.getSightingForHero(idSH);
        
        
        Hero hr2 = new Hero();
        hr2.setNameHero("Spider");
        hr2.setDescriptionHero("Big Boy");
        hr2.setPowerHero("Flying");
        daoHero.addHero(hr2);
        
        Sighting sight2 = new Sighting();
        Date date2 = sdf.parse("2018-10-11");
        sight2.setDateSighting(date2);
        sight2.setLocation_idLocation(locId);
        daoSight.addSighting(sight2);
        
        fromDb.setHero_idHero(hr2.getIdHero());
        fromDb.setSighting_idSighSighting(sight2.getIdSighting());
        
        daoSightHero.updateSightingForHero(fromDb);
        
        Sighting_has_Hero updatedSH = daoSightHero.getSightingForHero(fromDb.getIdSightingHero());
        assertEquals(updatedSH.getHero_idHero(), hr2.getIdHero());
        assertEquals(updatedSH.getSighting_idSighSighting(), sight2.getIdSighting());
    }
    
    
    
     @Test
    public void getAllSightingsForDateTest() throws ParseException {
         DateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
        
        
        Hero hr1 = new Hero();
        hr1.setNameHero("Blue Boy");
        hr1.setDescriptionHero("Fat and Ugly");
        hr1.setPowerHero("Fire");
        daoHero.addHero(hr1);
        
        
        Location loc1 = new Location();
        loc1.setNameLocation("Berlin Wall");
        loc1.setDescriptionLocation("East side");
        loc1.setCoordinates("124555-0000");
        loc1.setAddressLocation("12 Shwarz Ave, Berlin, Germany");
        daoLoc.addLocation(loc1);
        int locId = loc1.getIdLocation();
        
        Sighting sight1 = new Sighting();
        Date date1 = sdf.parse("2018-06-01");
        sight1.setDateSighting(date1);
        sight1.setLocation_idLocation(locId);
        daoSight.addSighting(sight1);
       
        
        Sighting_has_Hero SH = new Sighting_has_Hero();
        SH.setHero_idHero(hr1.getIdHero());
        SH.setSighting_idSighSighting(sight1.getIdSighting());
        daoSightHero.addSightingForHero(SH);
        int idSH = SH.getIdSightingHero();
        Sighting_has_Hero fromDb1 = daoSightHero.getSightingForHero(idSH);
        
        
        Hero hr2 = new Hero();
        hr2.setNameHero("Spider");
        hr2.setDescriptionHero("Big Boy");
        hr2.setPowerHero("Flying");
        daoHero.addHero(hr2);
        
        Location loc2 = new Location();
        loc2.setNameLocation("Empire State Building");
        loc2.setDescriptionLocation("102 floors");
        loc2.setCoordinates("45635-6764");
        loc2.setAddressLocation("5-th Ave, New York, USA");
        daoLoc.addLocation(loc2);
        int locId2 = loc2.getIdLocation();
        
        
        Sighting sight2 = new Sighting();
        Date date2 = sdf.parse("2018-06-01");
        sight2.setDateSighting(date2);
        sight2.setLocation_idLocation(locId2);
        daoSight.addSighting(sight2);
        
        Sighting_has_Hero SH2 = new Sighting_has_Hero();
        SH2.setHero_idHero(hr2.getIdHero());
        SH2.setSighting_idSighSighting(sight2.getIdSighting());
        daoSightHero.addSightingForHero(SH2);
        
        
        
      List<Sighting_has_Hero> sightingsByDateList = daoSightHero.getAllSightingsByDate(date2);
        assertEquals(sightingsByDateList.size(), 2);
}
}