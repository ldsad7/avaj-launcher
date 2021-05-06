package com.tsimonis.avaj_launcher.abstract_classes;

import com.tsimonis.avaj_launcher.exceptions.AircraftException;
import com.tsimonis.avaj_launcher.models.Coordinates;
import com.tsimonis.avaj_launcher.models.Logger;

import java.util.Objects;

public abstract class AirCraft {
    private static long idCounter;
    private final String name;
    private final long id;
    protected Coordinates coordinates;
    protected final static Logger LOGGER = Logger.getInstance();

    public AirCraft(String name, Coordinates coordinates) {
        if (name == null) {
            throw new AircraftException("Received `name` variable is null");
        }
        this.name = name;
        if (coordinates == null) {
            throw new AircraftException("Received `coordinates` variable is null");
        }
        this.coordinates = coordinates;
        this.id = this.nextId();
    }

    private long nextId() {
        return ++idCounter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirCraft airCraft = (AirCraft) o;
        return name.equals(airCraft.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "#" + name + "(" + id + ")";
    }
}
