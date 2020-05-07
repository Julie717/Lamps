package com.controller;

import com.example.Building;
import com.example.Countries;
import com.example.Room;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class HomeServlet extends HttpServlet {
    public static final String DATABASE_COUNTRY_PATH = "D:/dataBaseCountry/GeoLite2-Country.mmdb";
    private static String fileName = "building.out";
    private Building building = Building.getInstance();

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    @Override
    public void init() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(
                    new FileInputStream(fileName));
            building = (Building) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(fileName));
            objectOutputStream.writeObject(building);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getClientIp(HttpServletRequest request) {

        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }

    public String clientCountry(String clientIpAddress) {
        try {
            File dbFile = new File(DATABASE_COUNTRY_PATH);
            DatabaseReader reader = new DatabaseReader.Builder(dbFile).build();
            InetAddress ipAddress = InetAddress.getByName(clientIpAddress);
            CountryResponse country = reader.country(ipAddress);
            String countryIp = country.getCountry().getName().toUpperCase();
            return countryIp;
        } catch (GeoIp2Exception |
                IOException e) {
            System.out.println("This country is absent in Database");
        }
        return "";
    }

    public boolean isAvailable(String clientCountry, String roomCountry) {
        boolean available = false;
        if (clientCountry!=""){
            String countryRus = Countries.valueOf(clientCountry.toUpperCase()).countryNameRus();
            if (countryRus.equals(roomCountry)) {
                available = true;
            }
        }
        return available;
    }

    public Room findRoom(String nameRoom, String countryRoom) {
        for (Room r : building.listRoom()) {
            if (r.getName().equals(nameRoom) && r.getCountry().equals(countryRoom)) {
                return r;
            }
        }
        return null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        String path1 = "/WEB-INF/view/";
        Room room;
        String name, country;
        switch (path) {
            case "/add":
                req.setAttribute("listCountries", Countries.listOfCountriesRus());
                path1 = path1 + "addRoom.jsp";
                break;
            case "/show":
                List<Boolean> isAvailable = new ArrayList<>();
                for (Room r : building.listRoom()) {
                    isAvailable.add(isAvailable(clientCountry(getClientIp(req)), r.getCountry()));
                }
                req.setAttribute("isAvailable", isAvailable);
                req.setAttribute("building", building.listRoom());
                path1 = path1 + "showAllRooms.jsp";
                break;
            case "/room":
                name = new String(req.getParameter("roomName").getBytes("ISO-8859-1"), "UTF8");
                country = new String(req.getParameter("roomCountry").getBytes("ISO-8859-1"), "UTF8");
                room = findRoom(name, country);
                req.setAttribute("room", room);
                path1 = path1 + "room.jsp";
                break;
            case "/lamp":
                name = new String(req.getParameter("name").getBytes("ISO-8859-1"), "UTF8");
                String switchOn = new String(req.getParameter("switchOn").getBytes("ISO-8859-1"), "UTF8");
                country = new String(req.getParameter("country").getBytes("ISO-8859-1"), "UTF8");
                room = findRoom(name, country);
                room.setSwitchOn(Boolean.valueOf(switchOn));
                writeToFile();
                break;
            default:
                path1 = "/index.jsp";
        }
        req.getRequestDispatcher(path1).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = new String(req.getParameter("nameRoom").getBytes("ISO-8859-1"), "UTF8");
        String country = new String(req.getParameter("country").getBytes("ISO-8859-1"), "UTF8");
        Room room = findRoom(name, country);
        if (room == null) {
            room = new Room(name, country);
            building.add(room);
        }
        req.setAttribute("room", room);
        writeToFile();
        req.getRequestDispatcher("/WEB-INF/view/room.jsp").forward(req, resp);
    }
}
