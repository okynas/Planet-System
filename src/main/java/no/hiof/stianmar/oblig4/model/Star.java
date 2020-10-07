package no.hiof.stianmar.oblig4.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Star extends CelestialBody {

    private double effectiveTemperature;
    public static double solarMass = 1.98892E30;
    public static int solarRadius = 695700;

    public Star() {

    }

    public Star(String name, double radius, double mass, double effectiveTemperature, String pictureUrl) {
        super(name, mass, radius, pictureUrl);

        this.effectiveTemperature = effectiveTemperature;
    }

    public Star(String name, double radius, double mass, double effectiveTemp) {
        super(name, mass, radius);

        this.effectiveTemperature = effectiveTemp;
    }

    @JsonIgnore
    public double getEffectiveTemperature() {
        return effectiveTemperature;
    }

    @JsonIgnore
    public void setEffectiveTemperature(double effectiveTemperature) {
        this.effectiveTemperature = effectiveTemperature;
    }

    @JsonIgnore
    public double mSun() {
        return getMass() / solarMass;
    }

    @JsonIgnore
    public double rSun() {
        return getRadius() / solarRadius;
    }

    @Override
    public String toString() {
        return "The star: " + getName() + " has a radius of: " + getRadius() + "km, has a mass of: " + getMass() + "kg, and has en effective temperature of " + getEffectiveTemperature() + ".";
    }
}
