/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superheromvc.service;

import com.sg.superheromvc.dao.DaoSighting;
import com.sg.superheromvc.model.Sighting;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Anna
 */
public class ServiceLayerSightingDbImpl implements ServiceLayerSighting{
    
    public static DaoSighting daoSighting; 
    
    @Inject
    public ServiceLayerSightingDbImpl(DaoSighting daoSighting) {
        this.daoSighting = daoSighting;
    }

    @Override
    public void addSighting(Sighting sight) {
        daoSighting.addSighting(sight);
    }

    @Override
    public void deleteSighting(int idSighting) {
        daoSighting.deleteSighting(idSighting);
    }

    @Override
    public Sighting getSighting(int idSighting) {
       return daoSighting.getSighting(idSighting);
    }

    @Override
    public List<Sighting> getAllSightings() {
       return daoSighting.getAllSightings();
    }

    @Override
    public List<Sighting> getAllSightingsByOrderMAx10() {
        return daoSighting.getAllSightingsByOrderMAx10();
    }

    
    
    @Override
    public List<Sighting> getAllSightingsForLocId(int idLocation) {
        return daoSighting.getAllSightingsForLocId(idLocation);
    }
    
    

    @Override
    public Sighting updateSighting(Sighting sight) {
        return daoSighting.updateSighting(sight);
    }

    @Override
    public int getLastSightingId() {
        return daoSighting.getLastSightingId();
    }

    
    
    
}
