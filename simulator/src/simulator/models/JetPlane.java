package simulator.models;

import simulator.abstract_classes.AirCraft;
import simulator.interfaces.Flyable;

public class JetPlane extends AirCraft implements Flyable {
    private WeatherTower weatherTower;

    public JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        int longitude = this.coordinates.getLongitude();
        int latitude = this.coordinates.getLatitude();
        int height = this.coordinates.getHeight();
        switch (weatherTower.getWeather(this.coordinates)) {
            case SUN:
                latitude += 10;
                height += 2;
            case RAIN:
                latitude -= 5;
            case FOG:
                latitude += 1;
            case SNOW:
                height -= 7;
        }
        this.coordinates = new Coordinates(longitude, latitude, height);
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {

    }
}
