/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superheromvc.service;

import com.sg.superheromvc.model.Hero;
import com.sg.superheromvc.model.Sighting_has_Hero;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Anna
 */
public interface ServiceLayerSightingHero {
    public void addSightingForHero(Sighting_has_Hero sightHero);

    public void deleteSightingForHero(int idSighting);
    
    public void deleteSightingHeroWithHeroId(int idHero);

    public Sighting_has_Hero updateSightingForHero(Sighting_has_Hero sightHero);

    public List<Sighting_has_Hero> getAllSightingsByDate(Date date); 
    
    public List<Sighting_has_Hero> getAllSightingsForHero();
    
    public List<Sighting_has_Hero> getAllSightingsForHeroId(int idHero); 
    
    public List<Sighting_has_Hero> getAllSightingsForSightingId(int idSighting); 
    
    public Sighting_has_Hero getSightingForHero(int idSightHero);
    
}
