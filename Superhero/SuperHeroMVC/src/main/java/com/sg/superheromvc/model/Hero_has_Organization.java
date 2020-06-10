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
public class Hero_has_Organization {
    private int idHeroOrg;
    private int hero_idHero;
    private Organization org;

    public int getIdHeroOrg() {
        return idHeroOrg;
    }

    public void setIdHeroOrg(int idHeroOrg) {
        this.idHeroOrg = idHeroOrg;
    }

    public int getHero_idHero() {
        return hero_idHero;
    }

    public void setHero_idHero(int hero_idHero) {
        this.hero_idHero = hero_idHero;
    }

    public Organization getOrg() {
        return org;
    }

    public void setOrg(Organization org) {
        this.org = org;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.idHeroOrg;
        hash = 17 * hash + this.hero_idHero;
        hash = 17 * hash + Objects.hashCode(this.org);
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
        final Hero_has_Organization other = (Hero_has_Organization) obj;
        if (this.idHeroOrg != other.idHeroOrg) {
            return false;
        }
        if (this.hero_idHero != other.hero_idHero) {
            return false;
        }
        if (!Objects.equals(this.org, other.org)) {
            return false;
        }
        return true;
    }

    
}
