/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superheromvc.model;

import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Anna
 */
public class Location {
    private int idLocation;
    
    
    @NotEmpty(message = "You must supply a value for name of Location.")
    @Length(max = 45, message = "Name must be no more than 45 characters in length.")
    private String nameLocation;
    
    
    @Length(max = 80, message = "The description must be no more than 80 characters in length.")
    private String descriptionLocation;
    
    @NotEmpty(message = "You must supply a value for address.")
    @Length(max = 80, message = "Address must be no more than 80 characters in length.")
    private String addressLocation;
    
    
    @NotEmpty(message = "You must supply a value for cooridinates.")
    @Length(max = 80, message = "Coordinates must be no more than 80 characters in length.")
    private String coordinates;

    public int getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
    }

    public String getNameLocation() {
        return nameLocation;
    }

    public void setNameLocation(String nameLocation) {
        this.nameLocation = nameLocation;
    }

    public String getDescriptionLocation() {
        return descriptionLocation;
    }

    public void setDescriptionLocation(String descriptionLocation) {
        this.descriptionLocation = descriptionLocation;
    }

    public String getAddressLocation() {
        return addressLocation;
    }

    public void setAddressLocation(String addressLocation) {
        this.addressLocation = addressLocation;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + (int) (this.idLocation ^ (this.idLocation >>> 32));
        hash = 17 * hash + Objects.hashCode(this.nameLocation);
        hash = 17 * hash + Objects.hashCode(this.descriptionLocation);
        hash = 17 * hash + Objects.hashCode(this.addressLocation);
        hash = 17 * hash + Objects.hashCode(this.coordinates);
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
        final Location other = (Location) obj;
        if (this.idLocation != other.idLocation) {
            return false;
        }
        if (!Objects.equals(this.nameLocation, other.nameLocation)) {
            return false;
        }
        if (!Objects.equals(this.descriptionLocation, other.descriptionLocation)) {
            return false;
        }
        if (!Objects.equals(this.addressLocation, other.addressLocation)) {
            return false;
        }
        if (!Objects.equals(this.coordinates, other.coordinates)) {
            return false;
        }
        return true;
    }
    
    
    
}
