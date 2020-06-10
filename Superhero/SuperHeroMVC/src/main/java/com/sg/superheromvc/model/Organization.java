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
public class Organization {
    private int idOrganization;
    
    
    @NotEmpty(message = "You must supply a value for name of the Organization.")
    @Length(max = 45, message = "Name of Organization must be no more than 45 characters in length.")
    private String nameOrg;
    
    
    @Length(max = 80, message = "The description must be no more than 80 characters in length.")
    private String descriptionOrg;
    
    @NotEmpty(message = "You must supply a value for contactInfo.")
    @Length(max = 80, message = "Power must be no more than 80 characters in length.")
    private String contactInfoOrg;

    public int getIdOrganization() {
        return idOrganization;
    }

    public void setIdOrganization(int idOrganization) {
        this.idOrganization = idOrganization;
    }

    public String getNameOrg() {
        return nameOrg;
    }

    public void setNameOrg(String nameOrg) {
        this.nameOrg = nameOrg;
    }

    public String getDescriptionOrg() {
        return descriptionOrg;
    }

    public void setDescriptionOrg(String descriptionOrg) {
        this.descriptionOrg = descriptionOrg;
    }

    public String getContactInfoOrg() {
        return contactInfoOrg;
    }

    public void setContactInfoOrg(String contactInfoOrg) {
        this.contactInfoOrg = contactInfoOrg;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (int) (this.idOrganization ^ (this.idOrganization >>> 32));
        hash = 17 * hash + Objects.hashCode(this.nameOrg);
        hash = 17 * hash + Objects.hashCode(this.descriptionOrg);
        hash = 17 * hash + Objects.hashCode(this.contactInfoOrg);
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
        final Organization other = (Organization) obj;
        if (this.idOrganization != other.idOrganization) {
            return false;
        }
        if (!Objects.equals(this.nameOrg, other.nameOrg)) {
            return false;
        }
        if (!Objects.equals(this.descriptionOrg, other.descriptionOrg)) {
            return false;
        }
        if (!Objects.equals(this.contactInfoOrg, other.contactInfoOrg)) {
            return false;
        }
        return true;
    }

    
}
