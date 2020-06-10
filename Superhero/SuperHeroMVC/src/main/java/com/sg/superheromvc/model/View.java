/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superheromvc.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Anna
 */
public class View {

    private String locationName;
    private int sightingID;
    private Date date;
    private List<Hero> herosList;

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public int getSightingID() {
        return sightingID;
    }

    public void setSightingID(int sightingID) {
        this.sightingID = sightingID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Hero> getHerosList() {
        return herosList;
    }

    public void setHerosList(List<Hero> herosList) {
        this.herosList = herosList;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.locationName);
        hash = 97 * hash + this.sightingID;
        hash = 97 * hash + Objects.hashCode(this.date);
        hash = 97 * hash + Objects.hashCode(this.herosList);
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
        final View other = (View) obj;
        if (this.sightingID != other.sightingID) {
            return false;
        }
        if (!Objects.equals(this.locationName, other.locationName)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.herosList, other.herosList)) {
            return false;
        }
        return true;
    }

    
    
}
