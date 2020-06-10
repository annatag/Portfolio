/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superheromvc.dao;

import com.sg.superheromvc.model.Sighting;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class DaoSightingDbImpl implements DaoSighting{

    private static final String SQL_INSERT_SIGHTING
            = "insert into Sighting (dateSighting, Location_idLocation) "
            + " values (?, ?)";
    private static final String SQL_DELETE_SIGHTING
            = "delete from Sighting where idSighSighting = ?";
    
    
    private static final String SQL_SELECT_SIGHTING
            = "select * from Sighting where idSighSighting = ?";
    private static final String SQL_SELECT_ALL_SIGHTINGS
            = "select * from Sighting "
            + "ORDER BY dateSighting ";

    private static final String SQL_SELECT_ALL_SIGHTINGS_ORDERED_10
            = "select * from Sighting "
            + "ORDER BY dateSighting DESC LIMIT 10";
    
    private static final String SQL_SELECT_ALL_SIGHTINGS_FOR_LOCATION 
            = " select * from Sighting "
            + " where Location_idLocation = ? ";
            
    private static final String SQL_UPDATE_SIGHTING
            = "update Sighting set dateSighting = ?, Location_idLocation = ? "
            + " where idSighSighting = ?";
     private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
  
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSighting(Sighting sight) {
        jdbcTemplate.update(SQL_INSERT_SIGHTING,
                sight.getDateSighting(), sight.getLocation_idLocation());
                //sight.getLocation_idLocation());
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class);
        // set the new id value on the contact object and return it
        sight.setIdSighting(newId);
    }

    @Override
    public void deleteSighting(int idSighting) {
        jdbcTemplate.update(SQL_DELETE_SIGHTING, idSighting);
    }

    @Override
    public int getLastSightingId() {
         int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class);
         return newId;
    }

    @Override
    public List<Sighting> getAllSightingsByOrderMAx10() {
       return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS_ORDERED_10,
                new SightingMapper());
    }

    
    @Override
    public Sighting getSighting(int idSighting) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING,
                    new SightingMapper(), idSighting);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given sighting id - we just 
            // want to return null in this case
            return null;
        }
    }

    @Override
    public List<Sighting> getAllSightings() {
        return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS,
                new SightingMapper());
    }

    @Override
    public List<Sighting> getAllSightingsForLocId(int idLocation) {
        return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS_FOR_LOCATION,
                new SightingMapper(), idLocation);
    }

    
    
    @Override
    public Sighting updateSighting(Sighting sight) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING,
                sight.getDateSighting(),
                sight.getLocation_idLocation(),
                sight.getIdSighting());
        return sight;
    }

    
    
    
    private static final class SightingMapper implements RowMapper<Sighting> {

        public Sighting mapRow(ResultSet rs, int rowNum) throws SQLException {
            Sighting sh = new Sighting();
            sh.setIdSighting(rs.getInt("idSighSighting"));
            sh.setDateSighting(rs.getDate("dateSighting"));
            sh.setLocation_idLocation(rs.getInt("Location_idLocation"));
            
            return sh;
        }
    } 

    @Override
    public List<Sighting> getAllFakeSightings() {
       Sighting sighting1 = new Sighting();
       Date date1= new Date();
       date1.setDate(2019-01-01);
       sighting1.setDateSighting(date1);
       sighting1.setIdSighting(5);
       sighting1.setLocation_idLocation(5);
       Sighting sighting2 = new Sighting();
       
       Date date2= new Date();
       date2.setDate(2019-01-02);
       sighting1.setDateSighting(date2);
       sighting1.setIdSighting(6);
       sighting1.setLocation_idLocation(6);
       List<Sighting> list = new ArrayList<>();
       list.add(sighting1);
       list.add(sighting2);
       return list;
       
    }
    
    
    
}
