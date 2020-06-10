/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superheromvc.service;

import com.sg.superheromvc.model.Hero;
import com.sg.superheromvc.model.Hero_has_Organization;
import com.sg.superheromvc.model.Organization;
import java.util.List;

/**
 *
 * @author Anna
 */
public interface ServiceLayerHeroOrg {
    public List<Hero> getAllHerosByOrg(String nameOrg);
    
    public void addHeroOrg(Hero_has_Organization heroOrg);

    public void deleteHeroOrg(int idHero);

    public Hero_has_Organization getHeroOrg(int idHeroOrg);
    
    public List<Hero_has_Organization> getAllHeroOrgsByOrgId(int idOrg);

    public List<Hero_has_Organization> getAllHeroOrgs();

    public Hero_has_Organization updateHeroOrg(Hero_has_Organization heroOrg);
}
