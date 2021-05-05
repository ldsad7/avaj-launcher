package simulator.enums;

import simulator.models.Baloon;
import simulator.models.Helicopter;
import simulator.models.JetPlane;

import java.util.HashMap;
import java.util.Map;

public enum AircraftEnum {
    Helicopter(Helicopter.class.getSimpleName(), Helicopter.class),
    JetPlane(JetPlane.class.getSimpleName(), JetPlane.class),
    Baloon(Baloon.class.getSimpleName(), Baloon.class);

    private static final Map<String, AircraftEnum> BY_TYPE = new HashMap<>();

    static {
        for (AircraftEnum aircraftEnum : values()) {
            BY_TYPE.put(aircraftEnum.type, aircraftEnum);
        }
    }

    private final String type;
    private final Class classObj;

    AircraftEnum(String type, Class classObj) {
        this.type = type;
        this.classObj = classObj;
    }

    public static AircraftEnum valueOfType(String type) {
        return BY_TYPE.get(type);
    }

    public String getType() {
        return type;
    }

    public Class getClassObj() {
        return classObj;
    }
}
