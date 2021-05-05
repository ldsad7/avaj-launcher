package simulator.models;

import simulator.abstract_classes.AirCraft;
import simulator.exceptions.AircraftException;
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
        if (weatherTower == null) {
            throw new AircraftException("You need to register a weatherTower before updating conditions...");
        }
        String message = "";
        switch (weatherTower.getWeather(this.coordinates)) {
            case SUN:
                message = "This is hot.";
                latitude += 10;
                height += 2;
                break;
            case RAIN:
                message = "Damn you rain! You messed up my JetPlane.";
                latitude -= 5;
                break;
            case FOG:
                message = "Foggy-moggy, everything is wrong with this weather!";
                latitude += 1;
                break;
            default:
            case SNOW:
                message = "OMG! Winter is coming!";
                height -= 7;
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
