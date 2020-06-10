/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superheromvc.dao;

import com.sg.superheromvc.model.Hero;
import com.sg.superheromvc.model.Sighting;
import com.sg.superheromvc.model.Sighting_has_Hero;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
public class DaoSightingHeroDbImpl implements DaoSightingHero {

    private static final String SQL_INSERT_SIGHTING_FOR_HERO
            = "insert into Sighting_has_Hero (Sighting_idSighSighting, Hero_idHero) "
            + " values (?, ?)";

    private static final String SQL_SELECT_SIGHTING_FOR_HERO
            = "select * from Sighting_has_Hero where idSightingHero = ?";
    
    private static final String  SQL_SELECT_SIGHTING_FOR_SIGHTING_ID 
            =" select * from Sighting_has_Hero where Sighting_idSighSighting = ?";
            
    
    private static final String SQL_SELECT_HERO_FOR_SIGHTING_HERO =
            "select nameHero" +
            "from Hero H" +
            "join Sighting_has_Hero SH on SH.Hero_idHero=H.idHero" +
            "where idSightingHero=?";

    private static final String SQL_DELETE_SIGHTING_FOR_HERO
            = "delete from Sighting_has_Hero where idSightingHero = ?";
    
    
    private static final String SQL_DELETE_SIGHTING_FOR_HERO_BY_HERO_ID
            ="delete from Sighting_has_Hero where Hero_idHero = ? ";

    private static final String SQL_UPDATE_SIGHTING_FOR_HERO
            = "update Sighting_has_Hero set Sighting_idSighSighting = ?, Hero_idHero = ?"
            + " where idSightingHero = ?";

    private static final String SQL_SELECT_ALL_SIGHTINGS_FOR_HERO
            = "select * from Sighting_has_Hero";

    private static final String SQL_SELECT_SIGHTINGS_BY_DATE
            = " select SH.idSightingHero, SH.Hero_idHero, SH.Sighting_idSighSighting,  H.nameHero, L.nameLocation, L.addressLocation, S.dateSighting"
            + " from Hero H "
            + " join Sighting_has_Hero SH on SH.Hero_idHero = H.idHero " 
            + " join  Sighting S on S.idSighSighting = SH.Sighting_idSighSighting" 
            + " join Location L on L.idLocation = S.Location_idLocation"
            + "  where S.dateSighting = ?";
    
    private static final String SQL_SELECT_ALL_SIGHTINGS_FOR_ONE_HERO
            = "select * from Sighting_has_Hero " 
            + " where Hero_idHero = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSightingForHero(Sighting_has_Hero sightHero) {

        int idHero = 0;
        int idSighting = 0;
        if (sightHero.getHero_idHero() != 0) {
            idHero = sightHero.getHero_idHero();
        }

        if (sightHero.getSighting_idSighSighting() != 0) {
            idSighting = sightHero.getSighting_idSighSighting();
        }

        jdbcTemplate.update(SQL_INSERT_SIGHTING_FOR_HERO,
                idSighting, idHero);

        //QUESTION: MAYBE NO NEED TO RETRIEVE ID
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class);
        // set the new id value on the contact object and return it
        sightHero.setIdSightingHero(newId);
    }

    @Override
    public void deleteSightingForHero(int idSightingHero) {
        jdbcTemplate.update(SQL_DELETE_SIGHTING_FOR_HERO, idSightingHero);
    }
    
    
   @Override
    public void deleteSightingHeroWithHeroId(int idHero) {
       jdbcTemplate.update(SQL_DELETE_SIGHTING_FOR_HERO_BY_HERO_ID, idHero); 
    }
    
    
    @Override
    public Sighting_has_Hero updateSightingForHero(Sighting_has_Hero sightHero) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING_FOR_HERO,
                sightHero.getSighting_idSighSighting(),
                sightHero.getHero_idHero(),
                sightHero.getIdSightingHero());
        return sightHero;
    }

    @Override
    public Sighting_has_Hero getSightingForHero(int idSightingHero) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING_FOR_HERO,
                    new DaoSightingHeroDbImpl.SightHeroMapper(), idSightingHero);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given sighting id - we just 
            // want to return null in this case
            return null;
        }
    }

    @Override
    public List<Sighting_has_Hero> getAllSightingsForSightingId(int idSighting) {
        try {
            return jdbcTemplate.query(SQL_SELECT_SIGHTING_FOR_SIGHTING_ID,
                    new DaoSightingHeroDbImpl.SightHeroMapper(), idSighting);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given sighting id - we just 
            // want to return null in this case
            return null;
        }
    }
    
    
    

    @Override
    public List<Sighting_has_Hero> getAllSightingsForHero() {
        return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS_FOR_HERO,
                new DaoSightingHeroDbImpl.SightHeroMapper());
    }

    @Override
    public List<Sighting_has_Hero> getAllSightingsForHeroId(int idHero) {
         try {
            return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS_FOR_ONE_HERO,
                    new DaoSightingHeroDbImpl.SightHeroMapper(), idHero);
        } catch (EmptyResultDataAccessException ex) {        
            return null;
        }
    }
    
    
    

    @Override
    public List<Sighting_has_Hero> getAllSightingsByDate(Date date) {
        try {
            return jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_DATE,
                    new DaoSightingHeroDbImpl.SightHeroMapper(), date);
        } catch (EmptyResultDataAccessException ex) {        
            return null;
        }
    }

  
    
    
    
    
    
    private static final class SightHeroMapper implements RowMapper<Sighting_has_Hero> {

        public Sighting_has_Hero mapRow(ResultSet rs, int rowNum) throws SQLException {
            Sighting_has_Hero sightHero = new Sighting_has_Hero();
            sightHero.setIdSightingHero(rs.getInt("idSightingHero"));
            int idHero = rs.getInt("Hero_idHero");
            int idSighting = rs.getInt("Sighting_idSighSighting");
            if (idHero != 0) {
                Hero hero = new Hero();
                hero.setIdHero(idHero);
                sightHero.setHero_idHero(idHero);
            }
            if (idSighting != 0) {
                Sighting sight = new Sighting();
                sight.setIdSighting(idSighting);
                sightHero.setSighting_idSighSighting(idSighting);
            }
            return sightHero;
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
