package com.shareeasy.shareeasy.ui.gethelp.service;


import java.io.Serializable;

public class ServicesAccount implements Serializable {

    private String tittle;
    private String description;

    private boolean active;

    public ServicesAccount(String tittle, String description)  {
        this.tittle = tittle;
        this.description = description;
        this.active= true;
    }

    public ServicesAccount(String tittle, String description, boolean active)  {
        this.tittle = tittle;
        this.description = description;
        this.active= active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return this.tittle +" ("+ this.description +")";
    }

}

