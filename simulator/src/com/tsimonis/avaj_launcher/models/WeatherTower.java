package com.tsimonis.avaj_launcher.models;

import com.tsimonis.avaj_launcher.enums.WeatherEnum;

public class WeatherTower extends Tower {
    public WeatherEnum getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    public void changeWeather() {
        this.conditionsChanged();
    }
}
