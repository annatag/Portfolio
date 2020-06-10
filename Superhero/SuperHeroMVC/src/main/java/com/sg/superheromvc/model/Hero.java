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
public class Hero {
    private int idHero;
    
    
    @NotEmpty(message = "You must supply a value for name of Hero.")
    @Length(max = 45, message = "Name must be no more than 45 characters in length.")
    private String nameHero;
    
    
    @Length(max = 80, message = "The description must be no more than 80 characters in length.")
    private String descriptionHero;
    
    @NotEmpty(message = "You must supply a value for Power.")
    @Length(max = 45, message = "Power must be no more than 45 characters in length.")
    private String powerHero;

    public int getIdHero() {
        return idHero;
    }

    public void setIdHero(int idHero) {
        this.idHero = idHero;
    }

    public String getNameHero() {
        return nameHero;
    }

    public void setNameHero(String nameHero) {
        this.nameHero = nameHero;
    }

    public String getDescriptionHero() {
        return descriptionHero;
    }

    public void setDescriptionHero(String descriptionHero) {
        this.descriptionHero = descriptionHero;
    }

    public String getPowerHero() {
        return powerHero;
    }

    public void setPowerHero(String powerHero) {
        this.powerHero = powerHero;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (int) (this.idHero ^ (this.idHero >>> 32));
        hash = 41 * hash + Objects.hashCode(this.nameHero);
        hash = 41 * hash + Objects.hashCode(this.descriptionHero);
        hash = 41 * hash + Objects.hashCode(this.powerHero);
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
        final Hero other = (Hero) obj;
        if (this.idHero != other.idHero) {
            return false;
        }
        if (!Objects.equals(this.nameHero, other.nameHero)) {
            return false;
        }
        if (!Objects.equals(this.descriptionHero, other.descriptionHero)) {
            return false;
        }
        if (!Objects.equals(this.powerHero, other.powerHero)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
