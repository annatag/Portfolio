/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superheromvc.dao;

import com.sg.superheromvc.model.Location;
import com.sg.superheromvc.model.Organization;
import java.util.List;

/**
 *
 * @author Anna
 */
public interface DaoOrg {
    public void addOrg(Organization org);

    public void deleteOrg(int idOrganization);

    public Organization getOrg(int idOrganization);

    public List<Organization> getAllOrgs();

    public Organization updateOrg(Organization org);

   public List<Organization> getAllOrgsByHero(int idOrganization);
}
