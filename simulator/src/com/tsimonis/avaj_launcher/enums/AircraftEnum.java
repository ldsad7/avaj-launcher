package com.tsimonis.avaj_launcher.enums;

import com.tsimonis.avaj_launcher.exceptions.AircraftException;
import com.tsimonis.avaj_launcher.models.Baloon;
import com.tsimonis.avaj_launcher.models.Helicopter;
import com.tsimonis.avaj_launcher.models.JetPlane;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public enum AircraftEnum {
    Helicopter(com.tsimonis.avaj_launcher.models.Helicopter.class.getSimpleName(), Helicopter.class),
    JetPlane(com.tsimonis.avaj_launcher.models.JetPlane.class.getSimpleName(), JetPlane.class),
    Baloon(com.tsimonis.avaj_launcher.models.Baloon.class.getSimpleName(), Baloon.class);

    private static final Map<String, AircraftEnum> BY_TYPE = new HashMap<>();
    private static final Map<String, AircraftEnum> BY_MD5_TYPE = new HashMap<>();
    private static MessageDigest md;

    static {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new AircraftException("We weren't able to create MessageDigest");
        }
        for (AircraftEnum aircraftEnum : values()) {
            BY_TYPE.put(aircraftEnum.type, aircraftEnum);
            BY_MD5_TYPE.put(getMD5Checksum(aircraftEnum.type), aircraftEnum);
        }
    }

    private final String type;
    private final Class classObj;

    AircraftEnum(String type, Class classObj) {
        this.type = type;;
        this.classObj = classObj;
    }

    private static String getMD5Checksum(String string) {
        md.update(string.getBytes(), 0, string.length());
        byte[] b = md.digest();

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            result.append(Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }

    public static AircraftEnum valueOfType(String type) {
        AircraftEnum result = BY_TYPE.get(type);
        if (result == null) {
            result = BY_MD5_TYPE.get(type);
        }
        return result;
    }

    public String getType() {
        return type;
    }

    public Class getClassObj() {
        return classObj;
    }
}
