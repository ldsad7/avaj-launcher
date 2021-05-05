package simulator;

import simulator.exceptions.SimulatorException;
import simulator.interfaces.Flyable;
import simulator.models.AircraftFactory;
import simulator.models.Logger;
import simulator.models.WeatherTower;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Simulator {
    private static final int NUM_OF_VALUES_IN_LINE = 5;

    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                throw new SimulatorException("Simulator should have exactly 1 argument, " + args.length + " were given");
            }
            String fileName = args[0];
            int numOfTimesToRun;
            List<Flyable> flyables = new ArrayList<>();
            try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
                if (!scanner.hasNextLine()) {
                    throw new SimulatorException("The file `" + fileName + "` doesn't contain data");
                }
                String line = scanner.nextLine();
                try {
                    numOfTimesToRun = Integer.parseInt(line);
                } catch (NumberFormatException e) {
                    throw new SimulatorException("The file `" + fileName + "` contains incorrect number on the first line");
                }
                if (numOfTimesToRun <= 0) {
                    throw new SimulatorException("The number on the first line of the file `" + fileName +
                            "` should be a positive integer number");
                }
                while (scanner.hasNextLine()) {
                    line = scanner.nextLine();
                    String[] parts = line.split("\\s+");
                    if (parts.length != NUM_OF_VALUES_IN_LINE) {
                        throw new SimulatorException("The line '" + line + "' in the `" + fileName +
                                "` contains incorrect number of values, should be " + NUM_OF_VALUES_IN_LINE +
                                " but " + parts.length + " was given");
                    }
                    String type = parts[0];
                    String name = parts[1];
                    int longitude, latitude, height;
                    try {
                        longitude = Integer.parseInt(parts[2]);
                        latitude = Integer.parseInt(parts[3]);
                        height = Integer.parseInt(parts[4]);
                    } catch (NumberFormatException e) {
                        throw new SimulatorException("The line '" + line + "' in the `" + fileName +
                                "` contains incorrect values");
                    }
                    flyables.add(AircraftFactory.newAircraft(type, name, longitude, latitude, height));
                }
            } catch (FileNotFoundException e) {
                throw new SimulatorException("We weren't able to open and read from the file `" + fileName + "`");
            }
            WeatherTower weatherTower = new WeatherTower();
            for (Flyable flyable : flyables) {
                flyable.registerTower(weatherTower);
            }
            for (int i = 0; i < numOfTimesToRun; i++) {
                weatherTower.changeWeather();
            }
            try {
                Logger.getInstance().close();
            } catch (Exception e) {
                throw new SimulatorException("We weren't able to close writer in logger");
            }
        } catch (RuntimeException e) {
            System.out.println(e.toString());
        }
    }
}
