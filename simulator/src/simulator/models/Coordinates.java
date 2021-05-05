package simulator.models;

import simulator.exceptions.CoordinatesException;

public class Coordinates {
    private final int longitude;
    private final int latitude;
    private final int height;

    public Coordinates(int longitude, int latitude, int height) {
        if (longitude <= 0 || latitude <= 0) {
            throw new CoordinatesException(
                    "We received negative values for coordinates: (" + longitude + ", " + latitude + ")");
        }
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = min(100, max(0, height));
    }

    private int min(int a, int b) {
        if (a < b) {
            return a;
        }
        return b;
    }

    private int max(int a, int b) {
        if (a > b) {
            return a;
        }
        return b;
    }

    public int getSum() {
        return this.longitude + this.latitude + this.height;
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getHeight() {
        return height;
    }
}
