package com.tsimonis.avaj_launcher.models;

import com.tsimonis.avaj_launcher.abstract_classes.AirCraft;
import com.tsimonis.avaj_launcher.exceptions.AircraftException;
import com.tsimonis.avaj_launcher.interfaces.Flyable;

public class Helicopter extends AirCraft implements Flyable {
    private WeatherTower weatherTower;

    public Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        int longitude = this.coordinates.getLongitude();
        int latitude = this.coordinates.getLatitude();
        int height = this.coordinates.getHeight();
        if (weatherTower == null) {
            throw new AircraftException("You need to register a weatherTower before updating conditions...");
        }
        String message = "";
        switch (weatherTower.getWeather(this.coordinates)) {
            case SUN:
                message = "Let's enjoy the good weather and take some pics.";
                longitude += 10;
                height += 2;
                break;
            case RAIN:
                message = "It's raining. Better watch out for lightings.";
                longitude -= 5;
                break;
            case FOG:
                message = "Where are you? I don't see you!";
                longitude += 1;
                break;
            default:
            case SNOW:
                message = "My rotor is going to freeze!";
                height -= 12;
                break;
        }
        LOGGER.log(this + ": " + message);
        if (height <= 0) {
            LOGGER.log(this + " landing.");
            this.weatherTower.unregister(this);
        } else {
            this.coordinates = new Coordinates(longitude, latitude, height);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        if (weatherTower == null) {
            throw new AircraftException("Given weatherTower is null");
        }
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
    }
}
