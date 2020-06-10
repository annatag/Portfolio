/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superheromvc.service;

import com.sg.superheromvc.dao.DaoSightingHero;
import com.sg.superheromvc.model.Hero;
import com.sg.superheromvc.model.Sighting_has_Hero;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Anna
 */
public class ServiceLayerSightingHeroDbImpl implements ServiceLayerSightingHero{
    
     public static DaoSightingHero daoSH; 
    

    public ServiceLayerSightingHeroDbImpl(DaoSightingHero daoSH) {
        this.daoSH = daoSH;
        
    }

    @Override
    public void addSightingForHero(Sighting_has_Hero sightHero) {
        daoSH.addSightingForHero(sightHero);
    }

    @Override
    public void deleteSightingForHero(int idSighting) {
        daoSH.deleteSightingForHero(idSighting);
    }

    @Override
    public List<Sighting_has_Hero> getAllSightingsForHeroId(int idHero) {
        return daoSH.getAllSightingsForHeroId(idHero);
    }

    @Override
    public List<Sighting_has_Hero> getAllSightingsForSightingId(int idSighting) {
        return daoSH.getAllSightingsForSightingId(idSighting);
    }
    
    

    @Override
    public void deleteSightingHeroWithHeroId(int idHero) {
        daoSH.deleteSightingHeroWithHeroId(idHero);
    }

    
    
    @Override
    public Sighting_has_Hero updateSightingForHero(Sighting_has_Hero sightHero) {
        return daoSH.updateSightingForHero(sightHero);
    }

    @Override
    public List<Sighting_has_Hero> getAllSightingsByDate(Date date) {
        return daoSH.getAllSightingsByDate(date);
    }

    @Override
    public Sighting_has_Hero getSightingForHero(int idSightHero) {
        return daoSH.getSightingForHero(idSightHero);
    }

    
    
    @Override
    public List<Sighting_has_Hero> getAllSightingsForHero() {
        return daoSH.getAllSightingsForHero();
    }
    
    
}
