/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superheromvc.model;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author Anna
 */
public class ViewSighting {
    private Sighting sighting;
    private Location location;
    private List<Hero> listHeroes; 

    public Sighting getSighting() {
        return sighting;
    }

    public void setSighting(Sighting sighting) {
        this.sighting = sighting;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Hero> getListHeroes() {
        return listHeroes;
    }

    public void setListHeroes(List<Hero> listHeroes) {
        this.listHeroes = listHeroes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.sighting);
        hash = 31 * hash + Objects.hashCode(this.location);
        hash = 31 * hash + Objects.hashCode(this.listHeroes);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ViewSighting other = (ViewSighting) obj;
        if (!Objects.equals(this.sighting, other.sighting)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.listHeroes, other.listHeroes)) {
            return false;
        }
        return true;
    }

  
}
