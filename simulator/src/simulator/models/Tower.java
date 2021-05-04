package simulator.models;

import simulator.exceptions.TowerException;
import simulator.interfaces.Flyable;

import java.util.ArrayList;
import java.util.List;

public class Tower {
    private final List<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) {
        if (observers.contains(flyable)) {
            throw new TowerException("The list of observers already contains Flyable " + flyable);
        }
        this.observers.add(flyable);
    }

    public void unregister(Flyable flyable) {
        if (!observers.contains(flyable)) {
            throw new TowerException("The list of observers doesn't contain Flyable " + flyable);
        }
        this.observers.remove(flyable);
    }

    public void conditionsChanged() {
        for (Flyable flyable : this.observers) {
            flyable.updateConditions();
        }
    }
}