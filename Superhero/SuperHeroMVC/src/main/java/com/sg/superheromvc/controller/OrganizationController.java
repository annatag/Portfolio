/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superheromvc.controller;

import com.sg.superheromvc.model.Hero_has_Organization;
import com.sg.superheromvc.model.Organization;
import com.sg.superheromvc.model.Sighting_has_Hero;
import com.sg.superheromvc.service.ServiceLayerHero;
import com.sg.superheromvc.service.ServiceLayerHeroOrg;
import com.sg.superheromvc.service.ServiceLayerLocation;
import com.sg.superheromvc.service.ServiceLayerOrg;
import com.sg.superheromvc.service.ServiceLayerSighting;
import com.sg.superheromvc.service.ServiceLayerSightingHero;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Anna
 */
@Controller
public class OrganizationController {
    
    ServiceLayerHero serviceHero;
    ServiceLayerHeroOrg serviceHeroOrg;
    ServiceLayerSightingHero serviceSightingHero;
    ServiceLayerSighting serviceSighting;
    ServiceLayerLocation serviceLocation;
    ServiceLayerOrg serviceOrg;

    @Inject
    public OrganizationController(ServiceLayerHero serviceHero, ServiceLayerHeroOrg serviceHeroOrg, ServiceLayerSightingHero serviceSightingHero, ServiceLayerSighting serviceSighting, ServiceLayerLocation serviceLocation, ServiceLayerOrg serviceOrg) {
        this.serviceHero = serviceHero;
        this.serviceHeroOrg = serviceHeroOrg;
        this.serviceSightingHero = serviceSightingHero;
        this.serviceSighting = serviceSighting;
        this.serviceLocation = serviceLocation;
        this.serviceOrg = serviceOrg;
    }
    
    @RequestMapping(value="/displayOrganizationPage", method=RequestMethod.GET)
    public String displayOrganizationPage(Model model) {
        
        List<Organization> orgList = serviceOrg.getAllOrgs();

       
        model.addAttribute("orgList", orgList);
        return "organizations";
    }
    
    @RequestMapping(value = "/createOrganization", method = RequestMethod.POST)
      public String createOrganization(HttpServletRequest request) {
        // grab the incoming values from the form and create a new 
        // object
        Organization org = new Organization();
        org.setNameOrg(request.getParameter("nameOrg"));
        org.setDescriptionOrg(request.getParameter("descriptionOrg"));
        org.setContactInfoOrg(request.getParameter("contactInfoOrg"));
        

        // persist the new object
        serviceOrg.addOrg(org);

        // we don't want to forward to a View component - we want to
        // redirect to the endpoint that displays the org Page
        // so it can display the new org in the table.
        return "redirect:displayOrganizationPage";
    }
      
      @RequestMapping(value = "/displayEditOrganizationForm", method = RequestMethod.GET)
    public String displayEditOrganizationForm(HttpServletRequest request, Model model) {
        String idParameterString = request.getParameter("idOrganization");
        int idOrganization = Integer.parseInt(idParameterString);
        Organization org = serviceOrg.getOrg(idOrganization);
        model.addAttribute("org", org);
        return "editOrganizationForm";
    }

     @RequestMapping(value = "/editOrganization", method = RequestMethod.POST)
    public String editOrganization(@Valid @ModelAttribute("org") Organization org, BindingResult result) {

        if (result.hasErrors()) {
            
            return "editOrganizationForm";
            
        }
        serviceOrg.updateOrg(org);

        return "redirect:displayOrganizationPage";
    }


    @RequestMapping(value = "/deleteOrganization", method = RequestMethod.GET)
    public String deleteOrganization(HttpServletRequest request) {
        String idParameterString = request.getParameter("idOrganization");
        int idOrganization = Integer.parseInt(idParameterString);
        
        List<Hero_has_Organization> listH_Org = new ArrayList<>();
        
        
        listH_Org = serviceHeroOrg.getAllHeroOrgsByOrgId(idOrganization);
        
        for (Hero_has_Organization current : listH_Org) {
            
            serviceHeroOrg.deleteHeroOrg(current.getHero_idHero());
        };
        
        serviceOrg.deleteOrg(idOrganization);
        
        return "redirect:displayOrganizationPage";
    }
    
    
}
