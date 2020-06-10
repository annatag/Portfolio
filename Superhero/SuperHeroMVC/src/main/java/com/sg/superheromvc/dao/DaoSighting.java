/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superheromvc.dao;

import com.sg.superheromvc.model.Sighting;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Anna
 */
public interface DaoSighting {
    
    public void addSighting(Sighting sight);

    public void deleteSighting(int idSighting);
    
    public int getLastSightingId();

    public Sighting getSighting(int idSighting);

    public List<Sighting> getAllSightings();
    
    public List<Sighting> getAllSightingsForLocId(int idLocation);

    public Sighting updateSighting(Sighting sight);
    
    public List<Sighting> getAllFakeSightings();
    
    public List<Sighting> getAllSightingsByOrderMAx10();

    
}
