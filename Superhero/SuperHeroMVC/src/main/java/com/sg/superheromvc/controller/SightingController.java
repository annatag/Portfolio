/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superheromvc.controller;

import com.sg.superheromvc.model.Hero;
import com.sg.superheromvc.model.Location;
import com.sg.superheromvc.model.Sighting;
import com.sg.superheromvc.model.Sighting_has_Hero;
import com.sg.superheromvc.model.ViewSighting;
import com.sg.superheromvc.service.ServiceLayerHero;
import com.sg.superheromvc.service.ServiceLayerHeroOrg;
import com.sg.superheromvc.service.ServiceLayerLocation;
import com.sg.superheromvc.service.ServiceLayerOrg;
import com.sg.superheromvc.service.ServiceLayerSighting;
import com.sg.superheromvc.service.ServiceLayerSightingHero;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Anna
 */
@Controller
public class SightingController {

    ServiceLayerHero serviceHero;
    ServiceLayerHeroOrg serviceHeroOrg;
    ServiceLayerSightingHero serviceSightingHero;
    ServiceLayerSighting serviceSighting;
    ServiceLayerLocation serviceLocation;
    ServiceLayerOrg serviceOrg;

    @Inject
    public SightingController(ServiceLayerHero serviceHero, ServiceLayerHeroOrg serviceHeroOrg, ServiceLayerSightingHero serviceSightingHero, ServiceLayerSighting serviceSighting, ServiceLayerLocation serviceLocation, ServiceLayerOrg serviceOrg) {
        this.serviceHero = serviceHero;
        this.serviceHeroOrg = serviceHeroOrg;
        this.serviceSightingHero = serviceSightingHero;
        this.serviceSighting = serviceSighting;
        this.serviceLocation = serviceLocation;
        this.serviceOrg = serviceOrg;
    }

    @RequestMapping(value = "/displaySightingsPage", method = RequestMethod.GET)
    public String displaySightingsPage(HttpServletRequest request, Model model) {
        
        List<ViewSighting> listSightingViews = new ArrayList<>();
        
        
        List<Sighting> sightingList = serviceSighting.getAllSightings();
        
        for (Sighting current: sightingList){
        ViewSighting viewSighting = new ViewSighting();
        List<Hero> heroesList = serviceHero.getAllHeroesBySightingId(current.getIdSighting());
        Location location = serviceLocation.getLocationBySightingId(current.getIdSighting());
       
        
        viewSighting.setListHeroes(heroesList);
        viewSighting.setLocation(location);
        viewSighting.setSighting(current);
        listSightingViews.add(viewSighting);
        }
         
        model.addAttribute("listSightingViews", listSightingViews);
        return "sightings";
    }
    
    
     @RequestMapping(value = "/displayEditSightingForm", method = RequestMethod.GET)
    public String displayEditSightingForm(HttpServletRequest request, Model model) {
        String idParameterString = request.getParameter("idSighting");
        int idSighting = Integer.parseInt(idParameterString);
        Sighting sighting = serviceSighting.getSighting(idSighting);
        model.addAttribute("sighting", sighting);
        return "editSightingForm";
    }

     @RequestMapping(value = "/editSighting", method = RequestMethod.POST)
    public String editSighting(HttpServletRequest request) throws ParseException {

       String idSLocationParameterString = request.getParameter("location_idLocation");
       int idLocation = Integer.parseInt(idSLocationParameterString); 
       
        String idSightingParameterString = request.getParameter("idSighting");
        int idSighting = Integer.parseInt(idSightingParameterString);
       
       
        String parameterString = request.getParameter("dateSighting");
        Date dateSighting = new SimpleDateFormat("yyyy-MM-dd").parse(parameterString);
        
        
        Sighting sighting = serviceSighting.getSighting(idSighting);
        sighting.setDateSighting(dateSighting);
        sighting.setLocation_idLocation(idSighting);
        
        serviceSighting.updateSighting(sighting);

        return "redirect:displaySightingsPage";
    }
    
    @RequestMapping(value = "/createSighting", method = RequestMethod.POST)
    public String createSighting(HttpServletRequest request) throws ParseException {
       
        Sighting sighting = new Sighting();
        Sighting_has_Hero SH = new Sighting_has_Hero();

        String parameterString = request.getParameter("dateSighting");
        Date dateSighting = new SimpleDateFormat("yyyy-MM-dd").parse(parameterString);       
        String nameLocation = request.getParameter("nameLocation");
        String nameHero = request.getParameter("nameHero");
        
        int idLocation =    serviceLocation.getLocationByName(nameLocation).getIdLocation();
       
        int idHero = serviceHero.getHeroByHeroName(nameHero).getIdHero();
        
        sighting.setDateSighting(dateSighting);
        sighting.setLocation_idLocation(idLocation);
        serviceSighting.addSighting(sighting);
        int idSighting = serviceSighting.getLastSightingId();
        SH.setHero_idHero(idHero);
        SH.setSighting_idSighSighting(idSighting);
        
        serviceSightingHero.addSightingForHero(SH);
       

        // we don't want to forward to a View component - we want to
        // redirect to the endpoint that displays the Contacts Page
        // so it can display the new Contact in the table.
        return "redirect:displaySightingsPage";
    }
    
    @RequestMapping(value = "/deleteSighting", method = RequestMethod.GET)
    public String deleteSighting(HttpServletRequest request) {
        
        String idSightingParameterString = request.getParameter("idSighting");
        int idSighting = Integer.parseInt(idSightingParameterString);
        
        List<Sighting_has_Hero> listH_SH = new ArrayList<>();
        
        
        listH_SH = serviceSightingHero.getAllSightingsForSightingId(idSighting);
        
        for (Sighting_has_Hero current : listH_SH) {
            
            serviceSightingHero.deleteSightingForHero(current.getIdSightingHero());
        };
        
        serviceSighting.deleteSighting(idSighting);
        
        return "redirect:displaySightingsPage";
    }
    


}
