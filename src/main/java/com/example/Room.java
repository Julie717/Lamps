package com.example;

import java.io.Serializable;

public class Room implements Serializable {
    private String name;
    private String country;
    private boolean switchOn;

    public Room() {
        name = "";
        country = "";
        switchOn = false;
    }

    public Room(String name, String country, boolean switchOn) {
        this.name = name;
        this.country = country;
        this.switchOn = switchOn;
    }

    public Room(String name, String country) {
        this.name = name;
        this.country = country;
        switchOn = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isSwitchOn() {
        return switchOn;
    }

    public void setSwitchOn(boolean switchOn) {
        this.switchOn = switchOn;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", isSwitchOn=" + switchOn +
                '}';
    }
}
