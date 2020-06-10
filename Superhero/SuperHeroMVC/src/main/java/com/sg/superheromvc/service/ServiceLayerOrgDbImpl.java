/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superheromvc.service;

import com.sg.superheromvc.dao.DaoHero;
import com.sg.superheromvc.dao.DaoOrg;
import com.sg.superheromvc.model.Organization;
import java.util.List;

/**
 *
 * @author Anna
 */
public class ServiceLayerOrgDbImpl implements ServiceLayerOrg {

    public static DaoOrg daoOrg; 

    public ServiceLayerOrgDbImpl(DaoOrg daoOrg) {
        this.daoOrg = daoOrg;
    }

    

    
    @Override
    public void addOrg(Organization org) {
        daoOrg.addOrg(org);
    }

    @Override
    public void deleteOrg(int idOrganization) {
        daoOrg.deleteOrg(idOrganization);
    }

    @Override
    public Organization getOrg(int idOrganization) {
      return daoOrg.getOrg(idOrganization);
    }

    @Override
    public List<Organization> getAllOrgs() {
        return daoOrg.getAllOrgs();
    }

    @Override
    public Organization updateOrg(Organization org) {
       return daoOrg.updateOrg(org);
    }
    
    /*@Override
    public List<Organization> getAllOrgsByHero(int idOrganization){
    return daoOrg.getAllOrgsByHero(int idOrganization);
    }
    */
    
}
