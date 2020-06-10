/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superheromvc.model;

import java.util.Objects;

/**
 *
 * @author Anna
 */
public class Sighting_has_Hero {
    private int idSightingHero;
    
    private int sighting_idSighSighting; 
    
    private int Hero_idHero;

    public int getIdSightingHero() {
        return idSightingHero;
    }

    public void setIdSightingHero(int idSightingHero) {
        this.idSightingHero = idSightingHero;
    }

    public int getSighting_idSighSighting() {
        return sighting_idSighSighting;
    }

    public void setSighting_idSighSighting(int sighting_idSighSighting) {
        this.sighting_idSighSighting = sighting_idSighSighting;
    }

    public int getHero_idHero() {
        return Hero_idHero;
    }

    public void setHero_idHero(int Hero_idHero) {
        this.Hero_idHero = Hero_idHero;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.idSightingHero;
        hash = 79 * hash + this.sighting_idSighSighting;
        hash = 79 * hash + this.Hero_idHero;
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
        final Sighting_has_Hero other = (Sighting_has_Hero) obj;
        if (this.idSightingHero != other.idSightingHero) {
            return false;
        }
        if (this.sighting_idSighSighting != other.sighting_idSighSighting) {
            return false;
        }
        if (this.Hero_idHero != other.Hero_idHero) {
            return false;
        }
        return true;
    }

    
    
    
    
}
