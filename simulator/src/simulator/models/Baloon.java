package simulator.models;

import simulator.abstract_classes.AirCraft;
import simulator.exceptions.AircraftException;
import simulator.interfaces.Flyable;

public class Baloon extends AirCraft implements Flyable {
    private WeatherTower weatherTower;

    public Baloon(String name, Coordinates coordinates) {
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
                message = "That's pretty sunny outside! Let's go swimming :)";
                longitude += 2;
                height += 4;
                break;
            case RAIN:
                message = "Rain sounds like pain... Let the rain wash away my tears :(";
                height -= 5;
                break;
            case FOG:
                message = "Let's play hide and seek!";
                height -= 3;
                break;
            default:
            case SNOW:
                message = "Feels like magic!";
                height -= 15;
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
