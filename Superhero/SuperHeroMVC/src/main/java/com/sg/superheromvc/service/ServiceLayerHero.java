/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superheromvc.service;

import com.sg.superheromvc.model.Hero;
import java.util.List;

/**
 *
 * @author Anna
 */
public interface ServiceLayerHero {

    public void addHero(Hero hero);

    public void deleteHero(int idHero);

    public Hero getHero(int idHero);
    
    public Hero getHeroByHeroName( String heroName);
    

    public List<Hero> getAllHeros();

    public Hero updateHero(Hero hero);

    public List<Hero> getAllHerosByLocation(int idLocation);

    public List<Hero> getAllHeroesBySightingId(int idSighting);

}
