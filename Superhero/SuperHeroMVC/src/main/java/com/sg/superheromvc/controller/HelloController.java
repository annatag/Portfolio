package com.sg.superheromvc.controller;

import com.sg.superheromvc.model.Hero;
import com.sg.superheromvc.model.View;
import com.sg.superheromvc.model.Hero_has_Organization;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

    ServiceLayerSighting serviceSighting;
    ServiceLayerSightingHero serviceSightingHero;
    ServiceLayerHero serviceHero;
    ServiceLayerLocation serviceLocation;
    ServiceLayerOrg serviceOrg;
    ServiceLayerHeroOrg serviceHeroOrg;
    Map<Sighting, Location> mapSightingLocation = new HashMap<Sighting, Location>();
    Map<Sighting, Sighting_has_Hero> mapSightingHero = new HashMap<Sighting, Sighting_has_Hero>();
    Map<Sighting, View> mapView = new HashMap<Sighting, View>();

    @Inject
    public HelloController(ServiceLayerSighting serviceSighting, ServiceLayerSightingHero serviceSightingHero, ServiceLayerHero serviceHero, ServiceLayerLocation serviceLocation,  ServiceLayerOrg serviceOrg,
    ServiceLayerHeroOrg serviceHeroOrg) {
        this.serviceSighting = serviceSighting;
        this.serviceSightingHero = serviceSightingHero;
        this.serviceHero = serviceHero;
        this.serviceLocation = serviceLocation;
        this.serviceOrg = serviceOrg;
        this.serviceHeroOrg = serviceHeroOrg;
    }

    @RequestMapping(value = {"/", "/displayHomePage", "/hello"}, method = RequestMethod.GET)
    public String displayHomePage(HttpServletRequest request, Model model) {
        

       List<ViewSighting> listSightingViews = new ArrayList<>();
        
        
        List<Sighting> sightingList = serviceSighting.getAllSightingsByOrderMAx10();
        
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
        
        return "hello";

    }

}
