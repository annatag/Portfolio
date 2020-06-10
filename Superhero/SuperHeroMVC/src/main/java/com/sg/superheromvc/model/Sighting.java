/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superheromvc.model;

import java.util.Date;
import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Anna
 */
public class Sighting {
    private int idSighting;
    
    
    private int location_idLocation;
    
    //@NotNull(message = "You must supply a value for date in the format yyyy-MM-dd).")
    private Date dateSighting;

    public int getIdSighting() {
        return idSighting;
    }

    public void setIdSighting(int idSighting) {
        this.idSighting = idSighting;
    }

    public int getLocation_idLocation() {
        return location_idLocation;
    }

    public void setLocation_idLocation(int location_idLocation) {
        this.location_idLocation = location_idLocation;
    }

    public Date getDateSighting() {
        return dateSighting;
    }

    public void setDateSighting(Date dateSighting) {
        this.dateSighting = dateSighting;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.idSighting;
        hash = 83 * hash + this.location_idLocation;
        hash = 83 * hash + Objects.hashCode(this.dateSighting);
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
        final Sighting other = (Sighting) obj;
        if (this.idSighting != other.idSighting) {
            return false;
        }
        if (this.location_idLocation != other.location_idLocation) {
            return false;
        }
        if (!Objects.equals(this.dateSighting, other.dateSighting)) {
            return false;
        }
        return true;
    }
    
    
    

    
}
