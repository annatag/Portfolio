/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superheromvc.service;

import com.sg.superheromvc.dao.DaoLocation;
import com.sg.superheromvc.model.Location;
import java.util.List;

/**
 *
 * @author Anna
 */
public class ServiceLayerLocationDbImpl implements ServiceLayerLocation{
    public static DaoLocation daoLocation; 

    public ServiceLayerLocationDbImpl(DaoLocation daoLocation) {
        this.daoLocation = daoLocation;
    }

    @Override
    public void addLocation(Location loc) {
        daoLocation.addLocation(loc);
    }

    @Override
    public void deleteLocation(int idLocation) {
        daoLocation.deleteLocation(idLocation);
    }

    @Override
    public Location getLocation(int idLocation) {
        return daoLocation.getLocation(idLocation);
    }

    @Override
    public List<Location> getAllLocations() {
        return daoLocation.getAllLocations();
    }

    @Override
    public Location updateLocation(Location loc) {
        return daoLocation.updateLocation(loc);
    }

    @Override
    public List<Location> getAllLocationByHero(int idHero) {
        return daoLocation.getAllLocationByHero(idHero);
    }

    @Override
    public Location getLocationBySightingId(int idSighting) {
        return daoLocation.getLocationBySightingId(idSighting);
    }

    @Override
    public Location getLocationByName(String nameLocation) {
        return daoLocation.getLocationByName(nameLocation);
    }
    
    
    
    
    
}
