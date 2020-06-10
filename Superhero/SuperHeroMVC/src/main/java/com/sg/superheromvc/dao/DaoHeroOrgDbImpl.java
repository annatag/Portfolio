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
public class DaoHeroOrgDbImpl implements DaoHeroOrg{

    
    private static final String SQL_INSERT_HERO_ORG
            = "insert into Hero_has_Organization ( Hero_idHero, Organization_idOrganization) "
            + " values (?, ?)";

    private static final String SQL_DELETE_HERO_ORG
            = "delete from Hero_has_Organization where Hero_idHero = ?";

    private static final String SQL_SELECT_HERO_ORG
            = "select * from Hero_has_Organization where idHeroOrg = ?";
    
    private static final String SQL_SELECT_HERO_ORGS_BY_ORG_ID
            = " select * from Hero_has_Organization where Organization_idOrganization = ? ";
    
    private static final String SQL_UPDATE_HERO_ORG
            = "update Hero_has_Organization set Hero_idHero = ?, Organization_idOrganization = ?"
            + " where idHeroOrg = ?";
    
    private static final String SQL_SELECT_HEROES_BY_ORGANIZATION
            = "select Hero.idHero, Hero.nameHero, Hero.descriptionHero, Hero.powerHero from Hero "
            + "join Hero_has_Organization HO on HO.Hero_idHero = Hero.idHero  "
            + "join Organization Org on HO.Organization_idOrganization= Org.idOrganization "
            + "where nameOrg =?";
    
    
    private static final String SQL_SELECT_ALL_HERO_ORGANIZATION_PAIRS
            = "select * from Hero_has_Organization";
    
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addHeroOrg(Hero_has_Organization heroOrg) {
        int idHero = 0;
        int idOrg = 0;
        if (heroOrg.getHero_idHero() != 0) {
            idHero = heroOrg.getHero_idHero();
        }

        if (heroOrg.getOrg() != null) {
            idOrg = heroOrg.getOrg().getIdOrganization();
        }

        jdbcTemplate.update(SQL_INSERT_HERO_ORG,
                 idHero, idOrg );

       
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class);
        // set the new id value on the contact object and return it
        heroOrg.setIdHeroOrg(newId);
       
       
    }

    @Override
    public void deleteHeroOrg(int Hero_idHero) {
        jdbcTemplate.update(SQL_DELETE_HERO_ORG, Hero_idHero);
    }

    @Override
    public Hero_has_Organization getHeroOrg(int idHeroOrg) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_HERO_ORG,
                    new HeroOrgMapper(), idHeroOrg);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given  id - we just 
            // want to return null in this case
            return null;
        }
    }

    @Override
    public List<Hero_has_Organization> getAllHeroOrgsByOrgId(int idOrg) {
        try {
            return jdbcTemplate.query(SQL_SELECT_HERO_ORGS_BY_ORG_ID,
                    new DaoHeroOrgDbImpl.HeroOrgMapper(), idOrg);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given loc id - we just 
            // want to return null in this case
            return null;
        }
    }
    
    

    @Override
    public Hero_has_Organization updateHeroOrg(Hero_has_Organization heroOrg) {
        jdbcTemplate.update(SQL_UPDATE_HERO_ORG,
                heroOrg.getHero_idHero(),
                heroOrg.getOrg().getIdOrganization(),
                heroOrg.getIdHeroOrg());
        return heroOrg;
    }
    
    
    
    
    
    @Override
    public List<Hero> getAllHeroesByOrg(String nameOrg) {
        try {
            return jdbcTemplate.query(SQL_SELECT_HEROES_BY_ORGANIZATION,
                    new HeroMapper(), nameOrg);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given hero id - we just 
            // want to return null in this case
            return null;
        }
    }

    @Override
    public List<Hero_has_Organization> getAllHeroesOrg() {
         try {
            return jdbcTemplate.query(SQL_SELECT_ALL_HERO_ORGANIZATION_PAIRS,
                    new HeroOrgMapper());
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
    
    
    private static final class HeroOrgMapper implements RowMapper<Hero_has_Organization> {

        public Hero_has_Organization mapRow(ResultSet rs, int rowNum) throws SQLException {
            Hero_has_Organization heroOrg = new Hero_has_Organization();
            heroOrg.setIdHeroOrg(rs.getInt("idHeroOrg"));
            int idHero = rs.getInt("Hero_idHero");
            int idOrg = rs.getInt("Organization_idOrganization");
            
            if (idHero != 0) {
                Hero hero = new Hero();
                hero.setIdHero(idHero);
                heroOrg.setHero_idHero(hero.getIdHero());
            }
            
            
            if (idOrg != 0) {
                Organization org = new Organization();
                org.setIdOrganization(idOrg);
                heroOrg.setOrg(org);
            }
            return heroOrg;
            
        }
}
}