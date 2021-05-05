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
        switch (weatherTower.getWeather(this.coordinates)) {
            case SUN:
                longitude += 2;
                height += 4;
            case RAIN:
                height -= 5;
            case FOG:
                height -= 3;
            case SNOW:
                height -= 15;
        }
        if (height <= 0) {
            LOGGER.log(this + " landing.");
            this.weatherTower.unregister(this);
        } else {
            this.coordinates = new Coordinates(longitude, latitude, height);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
    }
}
