package simulator.models;

import simulator.abstract_classes.AirCraft;
import simulator.interfaces.Flyable;

public class JetPlane extends AirCraft implements Flyable {
    public JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {

    }

    @Override
    public void registerTower(WeatherTower weatherTower) {

    }
}
