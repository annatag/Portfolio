/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superheromvc.service;

import com.sg.superheromvc.dao.DaoHeroOrg;
import com.sg.superheromvc.model.Hero;
import com.sg.superheromvc.model.Hero_has_Organization;
import java.util.List;

/**
 *
 * @author Anna
 */
public class ServiceLayerHeroOrgDbImpl implements ServiceLayerHeroOrg {
    
    public static DaoHeroOrg daoHeroOrg; 
    

    public ServiceLayerHeroOrgDbImpl(DaoHeroOrg daoHeroOrg) {
        this.daoHeroOrg = daoHeroOrg;
        
    }
    
    
    @Override
    public List<Hero> getAllHerosByOrg(String nameOrg) {
        return daoHeroOrg.getAllHeroesByOrg(nameOrg);
    }

    @Override
    public void addHeroOrg(Hero_has_Organization heroOrg) {
        daoHeroOrg.addHeroOrg(heroOrg);
    }

    @Override
    public void deleteHeroOrg(int idHero) {
       daoHeroOrg.deleteHeroOrg(idHero);
    }

    @Override
    public Hero_has_Organization getHeroOrg(int idHeroOrg) {
        return daoHeroOrg.getHeroOrg(idHeroOrg);
    }

    @Override
    public List<Hero_has_Organization> getAllHeroOrgs() {
        return daoHeroOrg.getAllHeroesOrg();
    }

    @Override
    public List<Hero_has_Organization> getAllHeroOrgsByOrgId(int idOrg) {
        return daoHeroOrg.getAllHeroOrgsByOrgId(idOrg);
    }
    
    
    

    @Override
    public Hero_has_Organization updateHeroOrg(Hero_has_Organization heroOrg) {
      return daoHeroOrg.updateHeroOrg(heroOrg);
    }
    
    
    
}
