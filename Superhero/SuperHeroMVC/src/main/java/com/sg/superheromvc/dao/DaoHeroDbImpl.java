/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superheromvc.dao;

import com.sg.superheromvc.model.Hero;
import com.sg.superheromvc.model.Hero_has_Organization;
import com.sg.superheromvc.model.Organization;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Anna
 */
public class DaoHeroDbImpl implements DaoHero {

    private static final String SQL_INSERT_HERO
            = "insert into Hero (nameHero, descriptionHero, powerHero) "
            + " values (?, ?, ?)";

    private static final String SQL_DELETE_HERO
            = "delete from Hero where idHero = ? ";

    private static final String SQL_SELECT_HERO
            = "select * from Hero where idHero = ?";

    private static final String SQL_SELECT_HERO_BY_NAME
            = " select * from Hero where nameHero = ? ";
    private static final String SQL_SELECT_ALL_HEROES
            = "select * from Hero";

    private static final String SQL_UPDATE_HERO
            = "update Hero set nameHero = ?, descriptionHero = ?, powerHero = ? "
            + " where idHero = ?";

    private static final String SQL_SELECT_HEROES_BY_LOCATION_ID
            = "select Hero.idHero, Hero.nameHero, Hero.descriptionHero, Hero.powerHero from Hero "
            + " join Sighting_has_Hero SH on SH.Hero_idHero = Hero.idHero  "
            + " join  Sighting SI on SI.idSighSighting = SH.Sighting_idSighSighting "
            + " join Location LO on LO.idLocation = SI.Location_idLocation where LO.idLocation = ? ";

    private static final String SQL_SELECT_HEROES_BY_SIGHTING_ID
            ="select Hero.idHero, Hero.nameHero, Hero.descriptionHero, Hero.powerHero from Hero " 
            + " join Sighting_has_Hero SH on SH.Hero_idHero = Hero.idHero " 
            + " join Sighting SI on SI.idSighSighting = SH.Sighting_idSighSighting " 
            + " where   SI.idSighSighting = ?" ;

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addHero(Hero hero) {
        jdbcTemplate.update(SQL_INSERT_HERO,
                hero.getNameHero(),
                hero.getDescriptionHero(),
                hero.getPowerHero());

        // query the database for the id that was just assigned to the new
        // row in the database
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class);
        // set the new id value on the contact object and return it
        hero.setIdHero(newId);
       
       

        
    }
    
    


    @Override
    public void deleteHero(int idHero) {
        jdbcTemplate.update(SQL_DELETE_HERO, idHero);
    }

    @Override
    public Hero getHero(int idHero) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_HERO,
                    new HeroMapper(), idHero);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given hero id - we just 
            // want to return null in this case
            return null;
        }
    }

    @Override
    public Hero getHeroByHeroName(String heroName) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_HERO_BY_NAME,
                    new HeroMapper(), heroName);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given hero id - we just 
            // want to return null in this case
            return null;
        } 
    }
    
    
    

    @Override
    public List<Hero> getAllHeroesBySightingId(int idSighting) {
        try {
            return jdbcTemplate.query(SQL_SELECT_HEROES_BY_SIGHTING_ID,
                    new HeroMapper(), idSighting);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given hero id - we just 
            // want to return null in this case
            return null;
        }
    }

    
    
    @Override
    public List<Hero> getAllHeroes() {
        return jdbcTemplate.query(SQL_SELECT_ALL_HEROES,
                new HeroMapper());
    }

    @Override
    public Hero updateHero(Hero hero) {
        jdbcTemplate.update(SQL_UPDATE_HERO,
                hero.getNameHero(),
                hero.getDescriptionHero(),
                hero.getPowerHero(),
                hero.getIdHero());
        return hero;
    }

    @Override
    public List<Hero> getAllHeroesByLocation(int idLocation) {
        try {
            return jdbcTemplate.query(SQL_SELECT_HEROES_BY_LOCATION_ID,
                    new HeroMapper(), idLocation);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given hero id - we just 
            // want to return null in this case
            return null;
        }
    }
    

  

    
     private static final class HeroMapper implements RowMapper<Hero> {

        public Hero mapRow(ResultSet rs, int rowNum) throws SQLException {
            Hero hero = new Hero();
            hero.setIdHero(rs.getInt("idHero"));
            hero.setNameHero(rs.getString("nameHero"));
            hero.setDescriptionHero(rs.getString("descriptionHero"));
            hero.setPowerHero(rs.getString("powerHero"));
            return hero;
        }
    }
}
