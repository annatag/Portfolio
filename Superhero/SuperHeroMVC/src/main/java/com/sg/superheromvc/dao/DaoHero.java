/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superheromvc.dao;

import com.sg.superheromvc.model.Hero;
import java.util.List;

/**
 *
 * @author Anna
 */
public interface DaoHero {

    public void addHero(Hero hero);

    public void deleteHero(int idHero);

    public Hero getHero(int idHero);
    
    public Hero getHeroByHeroName( String heroName);

    public List<Hero> getAllHeroes();
    
    public List<Hero> getAllHeroesBySightingId(int idSighting);

    public Hero updateHero(Hero hero);

    public List<Hero> getAllHeroesByLocation(int idLocation);

   
}
