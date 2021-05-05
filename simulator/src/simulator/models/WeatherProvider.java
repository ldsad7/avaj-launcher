package simulator.models;

import simulator.enums.WeatherEnum;
import simulator.exceptions.WeatherProviderException;

public class WeatherProvider {
    private final static WeatherEnum[] weather = WeatherEnum.values();
    private static WeatherProvider weatherProvider;

    private WeatherProvider() {
    }

    public static synchronized WeatherProvider getProvider() {
        if (weatherProvider == null) {
            weatherProvider = new WeatherProvider();
        }
        return weatherProvider;
    }

    public WeatherEnum getCurrentWeather(Coordinates coordinates) {
        if (coordinates == null) {
            throw new WeatherProviderException("Given coordinates are null");
        }
        return weather[coordinates.getSum() % weather.length];
    }
}
