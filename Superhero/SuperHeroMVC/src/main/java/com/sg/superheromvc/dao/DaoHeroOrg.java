/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superheromvc.dao;

import com.sg.superheromvc.model.Hero;
import com.sg.superheromvc.model.Hero_has_Organization;
import java.util.List;

/**
 *
 * @author Anna
 */
public interface DaoHeroOrg {
   
    public void addHeroOrg(Hero_has_Organization heroOrg);

    public void deleteHeroOrg(int Hero_idHero);

    public Hero_has_Organization getHeroOrg(int idHeroOrg);
    
    
    public List<Hero> getAllHeroesByOrg(String nameOrg);
    
    public List<Hero_has_Organization> getAllHeroOrgsByOrgId(int idOrg);
    
    public Hero_has_Organization updateHeroOrg(Hero_has_Organization heroOrg);
    
    public List<Hero_has_Organization> getAllHeroesOrg();
}
