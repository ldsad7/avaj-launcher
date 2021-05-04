package simulator.models;

import simulator.abstract_classes.AirCraft;
import simulator.interfaces.Flyable;

public class Baloon extends AirCraft implements Flyable {
    private WeatherTower weatherTower;

    public Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {

    }

    @Override
    public void registerTower(WeatherTower weatherTower) {

    }
}
