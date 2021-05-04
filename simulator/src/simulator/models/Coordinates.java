package simulator.models;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    public Coordinates(int longitude, int latitude, int height) {
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
