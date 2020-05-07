package com.example;

import java.util.ArrayList;
import java.util.List;

public enum Countries {
    AUSTRIA, BELARUS, GREECE, ITALY, RUSSIA, FRANCE;

    public String countryNameRus() {
        switch (this) {
            case AUSTRIA:
                return "Австрия";
            case BELARUS:
                return "Беларусь";
            case GREECE:
                return "Греция";
            case ITALY:
                return "Италия";
            case RUSSIA:
                return "Россия";
            case FRANCE:
                return "Франция";
            default:
                return "";
        }
    }

    public static List<String> listOfCountriesRus(){
        List<String> countriesList=new ArrayList<>();
        for (Countries country:Countries.values()){
            countriesList.add(country.countryNameRus());
        }
        return countriesList;
    }
}
