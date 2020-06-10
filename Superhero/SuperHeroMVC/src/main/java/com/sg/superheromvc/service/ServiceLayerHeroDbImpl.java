/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superheromvc.service;

import com.sg.superheromvc.dao.DaoHero;
import com.sg.superheromvc.model.Hero;
import java.util.List;

/**
 *
 * @author Anna
 */
public class ServiceLayerHeroDbImpl implements ServiceLayerHero{
    
    public static DaoHero daoHero; 

    public ServiceLayerHeroDbImpl(DaoHero daoHero) {
        this.daoHero = daoHero;
    }
    
    
    @Override
    public void addHero(Hero hero) {
       daoHero.addHero(hero);
    }

    @Override
    public void deleteHero(int idHero) {
        daoHero.deleteHero(idHero);
    }

    @Override
    public Hero getHero(int idHero) {
        return daoHero.getHero(idHero);
    }

    @Override
    public Hero getHeroByHeroName(String heroName) {
        return daoHero.getHeroByHeroName(heroName);
    }

    
    @Override
    public List<Hero> getAllHeros() {
        return daoHero.getAllHeroes();
    }

    @Override
    public Hero updateHero(Hero hero) {
        return daoHero.updateHero(hero);
    }

    @Override
    public List<Hero> getAllHerosByLocation(int idLocation) {
        return daoHero.getAllHeroesByLocation(idLocation);
    }

    @Override
    public List<Hero> getAllHeroesBySightingId(int idSighting) {
        return daoHero.getAllHeroesBySightingId(idSighting);
    }

 
}
