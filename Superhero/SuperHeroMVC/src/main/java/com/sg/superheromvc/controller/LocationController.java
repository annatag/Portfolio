/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superheromvc.controller;

import com.sg.superheromvc.model.Location;
import com.sg.superheromvc.model.Sighting;
import com.sg.superheromvc.model.Sighting_has_Hero;
import com.sg.superheromvc.service.ServiceLayerHero;
import com.sg.superheromvc.service.ServiceLayerHeroOrg;
import com.sg.superheromvc.service.ServiceLayerLocation;
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
public class LocationController {
    
    ServiceLayerHero serviceHero;
    ServiceLayerHeroOrg serviceHeroOrg;
    ServiceLayerSightingHero serviceSightingHero;
    ServiceLayerSighting serviceSighting;
    ServiceLayerLocation serviceLocation;
    
    @Inject    
    public LocationController(ServiceLayerHero serviceHero, ServiceLayerHeroOrg serviceHeroOrg, ServiceLayerSightingHero serviceSightingHero, ServiceLayerSighting serviceSighting, ServiceLayerLocation serviceLocation) {
        this.serviceHero = serviceHero;
        this.serviceHeroOrg = serviceHeroOrg;
        this.serviceSightingHero = serviceSightingHero;
        this.serviceSighting = serviceSighting;
        this.serviceLocation = serviceLocation;
    }
    
    @RequestMapping(value = "/displayLocationsPage", method = RequestMethod.GET)
    public String displayLocationsPage(Model model) {
        // Get all the Heros from the DAO
        List<Location> locationList = serviceLocation.getAllLocations();

        // Put the List of Contacts on the Model
        model.addAttribute("locationList", locationList);

        // Return the logical name of our View component
        //OR // we don't want to forward to a View component - we want to
        // redirect to the endpoint that displays the Contacts Page
        // so it can display the new Contact in the table.
        return "locations";
        
    }
    
    @RequestMapping(value = "/createLocation", method = RequestMethod.POST)
    public String createLocation(HttpServletRequest request) {
        // grab the incoming values from the form and create a new Hero
        // object
        Location location = new Location();
        location.setNameLocation(request.getParameter("nameLocation"));
        location.setDescriptionLocation(request.getParameter("descriptionLocation"));
        location.setAddressLocation(request.getParameter("addressLocation"));
        location.setCoordinates(request.getParameter("coordinates"));

        // persist the new Contact
        serviceLocation.addLocation(location);

        // we don't want to forward to a View component - we want to
        // redirect to the endpoint that displays the Contacts Page
        // so it can display the new Contact in the table.
        return "redirect:displayLocationsPage";
    }
    
    @RequestMapping(value = "/displayEditLocationForm", method = RequestMethod.GET)
    public String displayEditLocationForm(HttpServletRequest request, Model model) {
        String idParameterString = request.getParameter("idLocation");
        int idLocation = Integer.parseInt(idParameterString);
        Location location = serviceLocation.getLocation(idLocation);
        model.addAttribute("location", location);
        return "editLocationForm";
    }
    
    @RequestMapping(value = "/editLocation", method = RequestMethod.POST)
    public String editLocation(@Valid @ModelAttribute("location") Location location, BindingResult result) {
        
        if (result.hasErrors()) {
            
            return "editLocationForm";
            
        }
        serviceLocation.updateLocation(location);
        
        return "redirect:displayLocationsPage";
    }

    @RequestMapping(value = "/deleteLocation", method = RequestMethod.GET)
    public String deleteLocation(HttpServletRequest request) {
        String idParameterString = request.getParameter("idLocation");
        int idLocation = Integer.parseInt(idParameterString);
        
        List<Sighting_has_Hero> listSH = new ArrayList<>();
        
        List<Sighting> listSightings = new ArrayList<>();
        listSightings = serviceSighting.getAllSightingsForLocId(idLocation);
        
        for (Sighting currentS : listSightings) {
            listSH = serviceSightingHero.getAllSightingsForSightingId(currentS.getIdSighting());
            for (Sighting_has_Hero currentSH : listSH) {
                serviceSightingHero.deleteSightingForHero(currentSH.getIdSightingHero());
            }
            serviceSighting.deleteSighting(currentS.getIdSighting());
        };
        
        serviceLocation.deleteLocation(idLocation);
        
        return "redirect:displayLocationsPage";
    }
    
}
