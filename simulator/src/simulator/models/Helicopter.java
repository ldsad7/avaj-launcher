package simulator.models;

import simulator.abstract_classes.AirCraft;
import simulator.exceptions.AircraftException;
import simulator.interfaces.Flyable;

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
        switch (weatherTower.getWeather(this.coordinates)) {
            case SUN:
                longitude += 10;
                height += 2;
            case RAIN:
                longitude -= 5;
            case FOG:
                longitude += 1;
            case SNOW:
                height -= 12;
        }
        this.coordinates = new Coordinates(longitude, latitude, height);
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        if (weatherTower == null) {
            throw new AircraftException("Given weatherTower is null");
        }
        this.weatherTower = weatherTower;
    }
}
