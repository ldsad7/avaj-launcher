package com.tsimonis.avaj_launcher.models;

import com.tsimonis.avaj_launcher.enums.AircraftEnum;
import com.tsimonis.avaj_launcher.exceptions.AircraftException;
import com.tsimonis.avaj_launcher.interfaces.Flyable;

import java.lang.reflect.InvocationTargetException;

public class AircraftFactory {
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        AircraftEnum aircraftEnum = AircraftEnum.valueOfType(type);
        if (aircraftEnum == null) {
            throw new AircraftException("There is no such type of aircraft as `" + type + "`");
        }
        Flyable flyable;
        Coordinates coordinates = new Coordinates(longitude, latitude, height);
        try {
            flyable = (Flyable) aircraftEnum.getClassObj().getDeclaredConstructor(
                    String.class, Coordinates.class).newInstance(name, coordinates);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new AircraftException("We weren't able to create an aircraft with given parameters");
        }
        return flyable;
    }
}
