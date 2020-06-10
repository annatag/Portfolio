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
public class DaoLocationDbImpl implements DaoLocation {

    private static final String SQL_INSERT_LOCATION
            = "insert into Location (nameLocation, descriptionLocation, addressLocation, coordinates) "
            + " values (?, ?, ?, ?)";

    private static final String SQL_DELETE_LOCATION
            = "delete from Location where idLocation = ?";

    private static final String SQL_SELECT_LOCATION
            = "select * from Location where idLocation = ?";

    private static final String SQL_SELECT_ALL_LOCATIONS
            = "select * from Location";

    private static final String SQL_UPDATE_LOCATION
            = " update Location set nameLocation = ?, descriptionLocation = ?, addressLocation = ?, coordinates = ? "
            + " where idLocation = ?";

    private static final String SQL_SELECT_LOCATION_BY_LOCATION_NAME 
            = " select * from Location where nameLocation = ? ";
    private static final String SQL_SELECT_LOCATIONS_BY_HERO_ID
            = " select  idLocation, nameLocation, descriptionLocation, addressLocation, coordinates FROM Location LOC "
            + " join  Sighting SI on SI.Location_idLocation = LOC.idLocation "
            + " join Sighting_has_Hero SH on SH.Sighting_idSighSighting = SI.idSighSighting "
            + " join Hero H on H.idHero = SH.Hero_idHero where H.idHero = ? ";

    private static final String SQL_SELECT_LOCATION_BY_SIGHTING_ID
            = "select  idLocation, nameLocation, descriptionLocation, addressLocation, coordinates FROM Location LOC " 
            + " join   Sighting SI on SI.Location_idLocation = LOC.idLocation " 
            + " where SI.idSighSighting = ? ";
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addLocation(Location loc) {
        jdbcTemplate.update(SQL_INSERT_LOCATION,
                loc.getNameLocation(),
                loc.getDescriptionLocation(),
                loc.getAddressLocation(),
                loc.getCoordinates());

        // query the database for the id that was just assigned to the new
        // row in the database
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class);
        // set the new id value on the location object and return it
        loc.setIdLocation(newId);

    }

    @Override
    public void deleteLocation(int idLocation) {
        jdbcTemplate.update(SQL_DELETE_LOCATION, idLocation);
    }

    @Override
    public Location getLocation(int idLocation) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION,
                    new DaoLocationDbImpl.LocationMapper(), idLocation);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given location id - we just 
            // want to return null in this case
            return null;
        }
    }

    @Override
    public Location getLocationByName(String nameLocation) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_LOCATION_NAME,
                    new DaoLocationDbImpl.LocationMapper(), nameLocation);
        } catch (EmptyResultDataAccessException ex) {
            
            return null;
        }
 
    }
    
    
    

    @Override
    public Location getLocationBySightingId(int idSighting) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_SIGHTING_ID,
                    new DaoLocationDbImpl.LocationMapper(), idSighting);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given location id - we just 
            // want to return null in this case
            return null;
        }
    }
    
    

    @Override
    public List<Location> getAllLocations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS,
                new DaoLocationDbImpl.LocationMapper());
    }

    @Override
    public Location updateLocation(Location loc) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                loc.getNameLocation(),
                loc.getDescriptionLocation(),
                loc.getAddressLocation(),
                loc.getCoordinates(),
                loc.getIdLocation());

        return loc;
    }

    @Override
    public List<Location> getAllLocationByHero(int idHero) {
        try {
            return jdbcTemplate.query(SQL_SELECT_LOCATIONS_BY_HERO_ID,
                    new DaoLocationDbImpl.LocationMapper(), idHero);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given loc id - we just 
            // want to return null in this case
            return null;
        }
    }


    private static final class LocationMapper implements RowMapper<Location> {

        public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
            Location loc = new Location();
            loc.setIdLocation(rs.getInt("idLocation"));
            loc.setNameLocation(rs.getString("nameLocation"));
            loc.setDescriptionLocation(rs.getString("descriptionLocation"));
            loc.setAddressLocation(rs.getString("addressLocation"));
            loc.setCoordinates(rs.getString("coordinates"));
            return loc;
        }
    }
}
