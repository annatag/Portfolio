/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superheromvc.service;

import com.sg.superheromvc.model.Location;
import java.util.List;

/**
 *
 * @author Anna
 */
public interface ServiceLayerLocation {
    public void addLocation(Location loc);

    public void deleteLocation(int idLocation);

    public Location getLocation(int idLocation);
    
    public Location getLocationBySightingId(int idSighting);

    public List<Location> getAllLocations();

    public Location updateLocation(Location loc);

    public List<Location> getAllLocationByHero(int idHero);
    
    public Location getLocationByName(String nameLocation);
}
