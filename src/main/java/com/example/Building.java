package com.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Building implements Serializable {
    private static Building instance;

    private List<Room> building=new ArrayList<>();

    private Building() {
    }

    public static Building getInstance() {
        if (instance == null) {
            instance = new Building();
        }
        return instance;
    }

    public void add(Room room) {
        building.add(room);
    }


    public List<Room> listRoom() {
        return building;
    }

    public List<Room> getBuilding() {
        return building;
    }

    public void setBuilding(List<Room> building) {
        this.building = building;
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        instance = this;
    }

    private Object readResolve() throws ObjectStreamException {
        return instance;
    }

    @Override
    public String toString() {
        String str = "";
        for (Room r : building) {
            str = str + r.getName() + " " + r.getCountry() + "\n";
        }
        return str;
    }
}
