package simulator.models;

import simulator.enums.AircraftEnum;
import simulator.exceptions.AircraftException;
import simulator.interfaces.Flyable;

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
