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
import com.sg.superheromvc.service.ServiceLayerHero;
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
public class DaoHeroTest {

    private DaoHero daoHero;
    private DaoLocation daoLoc;
    private DaoSightingHero daoSightHero;
    private DaoSighting daoSight;
    private DaoOrg daoOrg;
    private DaoHeroOrg daoHeroOrg;

    public DaoHeroTest() {
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

        daoSightHero = ctx.getBean("DaoSightingHero", DaoSightingHero.class);
        daoOrg = ctx.getBean("DaoOrg", DaoOrg.class);
        daoHeroOrg = ctx.getBean("DaoHeroOrg", DaoHeroOrg.class);

        List<Hero_has_Organization> herosOrgs = daoHeroOrg.getAllHeroesOrg();
        for (Hero_has_Organization currentHeroOrg : herosOrgs) {
            daoHeroOrg.deleteHeroOrg(currentHeroOrg.getHero_idHero());
        }

        List<Organization> orgs = daoOrg.getAllOrgs();
        for (Organization currentOrg : orgs) {
            daoOrg.deleteOrg(currentOrg.getIdOrganization());
        }

       

        List<Hero> heroes = daoHero.getAllHeroes();
        for (Hero currentHero : heroes) {
            daoHero.deleteHero(currentHero.getIdHero());
        }
        
         List<Sighting_has_Hero> sightsHeros = daoSightHero.getAllSightingsForHero();
        for (Sighting_has_Hero currentSH : sightsHeros) {
            daoSightHero.deleteSightingForHero(currentSH.getHero_idHero());
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
        daoHero.addHero(hr);
        Hero fromDb = daoHero.getHero(hr.getIdHero());
        assertEquals(fromDb, hr);
        daoHero.deleteHero(hr.getIdHero());
        assertNull(daoHero.getHero(hr.getIdHero()));
    }

    @Test
    public void addUpdateHero() {
        // Create new contact
        Hero hr = new Hero();
        hr.setNameHero("Spider");
        hr.setDescriptionHero("Big Boy");
        hr.setPowerHero("Flying");
        daoHero.addHero(hr);

        hr.setDescriptionHero("Updated Boy");
        daoHero.updateHero(hr);
        Hero fromDb = daoHero.getHero(hr.getIdHero());
        assertEquals(fromDb, hr);
    }

    @Test
    public void getAllHeroes() {
        // Create new contact
        Hero hr = new Hero();
        hr.setNameHero("Spider");
        hr.setDescriptionHero("Big Boy");
        hr.setPowerHero("Flying");
        daoHero.addHero(hr);
        // Create new contact
        Hero hr1 = new Hero();
        hr1.setNameHero("Blue Boy");
        hr1.setDescriptionHero("Fat and Ugly");
        hr1.setPowerHero("Fire");
        daoHero.addHero(hr1);

        List<Hero> hList = daoHero.getAllHeroes();
        assertEquals(hList.size(), 2);
    }

}
