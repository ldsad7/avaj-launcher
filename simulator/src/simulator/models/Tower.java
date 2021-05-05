package simulator.models;

import simulator.exceptions.TowerException;
import simulator.interfaces.Flyable;

import java.util.ArrayList;
import java.util.List;

public class Tower {
    private final static Logger LOGGER = Logger.getInstance();
    private final List<Flyable> observers = new ArrayList<>();
    private final List<Flyable> observersToRemove = new ArrayList<>();

    public void register(Flyable flyable) {
        if (observers.contains(flyable)) {
            throw new TowerException("The list of observers already contains Flyable " + flyable);
        }
        this.observers.add(flyable);
        LOGGER.log(this + " says: " + flyable + " registered to weather tower.");
    }

    public void unregister(Flyable flyable) {
        if (!observers.contains(flyable)) {
            throw new TowerException("The list of observers doesn't contain Flyable " + flyable);
        }
        observersToRemove.add(flyable);
        LOGGER.log(this + " says: " + flyable + " unregistered from weather tower.");
    }

    public void conditionsChanged() {
        observers.removeAll(observersToRemove);
        observersToRemove.clear();
        for (Flyable observer : observers) {
            observer.updateConditions();
        }
    }

    @Override
    public String toString() {
        return "Tower";
    }
}
