/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superheromvc.dao;

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
public class DaoOrgDbImpl implements DaoOrg{

     private static final String SQL_INSERT_ORGANIZATION
            = "insert into Organization (nameOrg, descriptionOrg, contactInfoOrg) "
            + " values (?, ?, ?)";

    private static final String SQL_DELETE_ORGANIZATION
            = "delete from Organization where idOrganization = ?";

    private static final String SQL_SELECT_ORGANIZATION
            = "select * from Organization where idOrganization = ?";

    private static final String SQL_SELECT_ALL_ORGANIZATIONS
            = "select * from Organization";

    private static final String SQL_UPDATE_ORGANIZATION
            = "update Organization set nameOrg = ?, descriptionOrg = ?, contactInfoOrg = ? "
            + " where idOrganization = ?";

    private static final String SQL_SELECT_ORGANIZATION_BY_HERO_ID
            = "select Organization.idOrganization, Organization.nameOrg, Organization.descriptionOrg, Organization.contactInfoOrg from Organization"
            + "join Hero_has_Organization HerOrg on HerOrg.Organization_idOrganization = Organization.idOrganization  "
            + "join  Hero H on H.idHero = HerOrg.Hero_idHero where H.idHero = ?";

    

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addOrg(Organization org) {
        jdbcTemplate.update(SQL_INSERT_ORGANIZATION,
                org.getNameOrg(),
                org.getDescriptionOrg(),
                org.getContactInfoOrg());

        // query the database for the id that was just assigned to the new
        // row in the database
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class);
        // set the new id value on the Org object and return it
        org.setIdOrganization(newId);
        
    }

    @Override
    public void deleteOrg(int idOrganization) {
     jdbcTemplate.update(SQL_DELETE_ORGANIZATION, idOrganization);   
    }

    @Override
    public Organization getOrg(int idOrganization) {
         try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION,
                    new DaoOrgDbImpl.OrgMapper(), idOrganization);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given org id - we just 
            // want to return null in this case
            return null;
        }
    }

    @Override
    public List<Organization> getAllOrgs() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATIONS,
                new OrgMapper());
    }

    @Override
    public Organization updateOrg(Organization org) {
        jdbcTemplate.update(SQL_UPDATE_ORGANIZATION,
                org.getNameOrg(),
                org.getDescriptionOrg(),
                org.getContactInfoOrg(),
                org.getIdOrganization());
        return org;
    }

    @Override
    public List<Organization> getAllOrgsByHero(int idOrganization) {
       try {
            return jdbcTemplate.query(SQL_SELECT_ORGANIZATION_BY_HERO_ID,
                    new DaoOrgDbImpl.OrgMapper(), idOrganization);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given hero id - we just 
            // want to return null in this case
            return null;
        }
    }
    
    
    private static final class OrgMapper implements RowMapper<Organization> {

        public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {
            Organization org = new Organization();
            org.setIdOrganization(rs.getInt("idOrganization"));
            org.setNameOrg(rs.getString("nameOrg"));
            org.setDescriptionOrg(rs.getString("descriptionOrg"));
            org.setContactInfoOrg(rs.getString("contactInfoOrg"));
            return org;
        }
    } 
}
