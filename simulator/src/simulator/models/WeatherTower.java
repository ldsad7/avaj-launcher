package simulator.models;

import simulator.enums.WeatherEnum;

public class WeatherTower extends Tower {
    public WeatherEnum getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    public void changeWeather() {
        this.conditionsChanged();
    }
}
