package com.tsimonis.avaj_launcher.interfaces;

import com.tsimonis.avaj_launcher.models.WeatherTower;

public interface Flyable {
    void updateConditions();

    void registerTower(WeatherTower weatherTower);
}
