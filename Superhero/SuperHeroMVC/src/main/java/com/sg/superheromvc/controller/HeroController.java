/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superheromvc.controller;

import com.sg.superheromvc.dao.DaoHero;
import com.sg.superheromvc.dao.DaoHeroOrg;
import com.sg.superheromvc.dao.DaoSighting;
import com.sg.superheromvc.dao.DaoSightingHero;
import com.sg.superheromvc.model.Hero;
import com.sg.superheromvc.model.Sighting_has_Hero;
import com.sg.superheromvc.service.ServiceLayerHero;
import static com.sg.superheromvc.service.ServiceLayerHeroDbImpl.daoHero;
import com.sg.superheromvc.service.ServiceLayerHeroOrg;
import static com.sg.superheromvc.service.ServiceLayerHeroOrgDbImpl.daoHeroOrg;
import com.sg.superheromvc.service.ServiceLayerSighting;
import static com.sg.superheromvc.service.ServiceLayerSightingDbImpl.daoSighting;
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
public class HeroController {

    ServiceLayerHero serviceHero;
    ServiceLayerHeroOrg serviceHeroOrg;
    ServiceLayerSightingHero serviceSightingHero;
    ServiceLayerSighting serviceSighting;

    @Inject 
    public HeroController(ServiceLayerHero serviceHero, ServiceLayerHeroOrg serviceHeroOrg, ServiceLayerSightingHero serviceSightingHero, ServiceLayerSighting serviceSighting) {
        this.serviceHero = serviceHero;
        this.serviceHeroOrg = serviceHeroOrg;
        this.serviceSightingHero = serviceSightingHero;
        this.serviceSighting = serviceSighting;
    }


    @RequestMapping(value = "/displayHeroesPage", method = RequestMethod.GET)
    public String displayHeroesPage(Model model) {
        // Get all the Heros from the DAO
        List<Hero> heroesList = serviceHero.getAllHeros();

        // Put the List of Contacts on the Model
        model.addAttribute("heroesList", heroesList);

        // Return the logical name of our View component
        //OR // we don't want to forward to a View component - we want to
        // redirect to the endpoint that displays the Contacts Page
        // so it can display the new Contact in the table.
        return "heroes";

    }

    
    

    @RequestMapping(value = "/createHero", method = RequestMethod.POST)
    public String createHero(HttpServletRequest request) {
        // grab the incoming values from the form and create a new Hero
        // object
        Hero hero = new Hero();
        hero.setNameHero(request.getParameter("nameHero"));
        hero.setDescriptionHero(request.getParameter("descriptionHero"));
        hero.setPowerHero(request.getParameter("powerHero"));

        // persist the new Contact
        serviceHero.addHero(hero);

        // we don't want to forward to a View component - we want to
        // redirect to the endpoint that displays the Contacts Page
        // so it can display the new Contact in the table.
        return "redirect:displayHeroesPage";
    }

    @RequestMapping(value = "/deleteHero", method = RequestMethod.GET)
    public String deleteHero(HttpServletRequest request) {
        String idParameterString = request.getParameter("idHero");
        int idHero = Integer.parseInt(idParameterString);
        
        
        List<Sighting_has_Hero> listSightingHeros = new ArrayList<>();
        listSightingHeros = serviceSightingHero.getAllSightingsForHeroId(idHero);
        
        for   (Sighting_has_Hero currentSH : listSightingHeros) {
        serviceSightingHero.deleteSightingForHero(currentSH.getIdSightingHero());
        };
        
        List<Integer> listSightingIds = new ArrayList<>();
        for (int i=0; i<listSightingHeros.size(); i++){
        int currentId = listSightingHeros.get(i).getSighting_idSighSighting();
        listSightingIds.add(currentId);
        }
        
        
        for (int m=0; m<listSightingIds.size(); m++){
            
            serviceSighting.deleteSighting(listSightingIds.get(m));
            
        }
        
        serviceHeroOrg.deleteHeroOrg(idHero);
        
        serviceHero.deleteHero(idHero);
        
        return "redirect:displayHeroesPage";
    }

    @RequestMapping(value = "/displayEditHeroForm", method = RequestMethod.GET)
    public String displayEditHeroForm(HttpServletRequest request, Model model) {
        String idParameterString = request.getParameter("idHero");
        int idHero = Integer.parseInt(idParameterString);
        Hero hero = serviceHero.getHero(idHero);
        model.addAttribute("hero", hero);
        return "editHeroForm";
    }

    @RequestMapping(value = "/editHero", method = RequestMethod.POST)
    public String editHero(@Valid @ModelAttribute("hero") Hero hero, BindingResult result) {

        if (result.hasErrors()) {
            
            return "editHeroForm";
            
        }
        serviceHero.updateHero(hero);

        return "redirect:displayHeroesPage";
    }

}
