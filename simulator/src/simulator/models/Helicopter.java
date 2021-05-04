package simulator.models;

import simulator.abstract_classes.AirCraft;
import simulator.interfaces.Flyable;

public class Helicopter extends AirCraft implements Flyable {
    public Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {

    }

    @Override
    public void registerTower(WeatherTower weatherTower) {

    }
}
