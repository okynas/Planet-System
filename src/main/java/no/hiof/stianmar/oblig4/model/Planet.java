package no.hiof.stianmar.oblig4.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Comparator;

public class Planet extends NaturalSatellite {

    private static final double earthMass = 5.972E24;
    private static final int earthRadius = 6371;
    private static final double jupiterMass = 1.898E27;
    private static final int jupiterRadius = 71492;

    private ArrayList<Moon> moon;

    public Planet() {

    }

    public Planet(String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, CelestialBody celestialBody, String pictureUrl) {
        super(name, mass, radius, semiMajorAxis, eccentricity, orbitalPeriod, celestialBody, pictureUrl);
    }

    public Planet(String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, CelestialBody celestialBody) {
        super(name, mass,radius, semiMajorAxis, eccentricity, orbitalPeriod, celestialBody);
    }

    public Planet(String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, CelestialBody celestialBody, String pictureUrl, ArrayList<Moon> moon) {
        super(name, mass,radius, semiMajorAxis, eccentricity, orbitalPeriod, celestialBody, pictureUrl);
        this.moon = moon;
    }

    @JsonIgnore
    public ArrayList<Moon> getMoon() {
        return moon;
    }

    @JsonIgnore
    public void setMoon(ArrayList<Moon> moon) {
        this.moon = moon;
    }

    @JsonIgnore
    public double mJup() {
        return getMass() / jupiterMass ;
    }

    @JsonIgnore
    public double rJup() {
        return getRadius() / jupiterRadius;
    }

    @JsonIgnore
    public double mEarth() {
        return getMass() / earthMass;
    }

    @JsonIgnore
    public double rEarth() {
        return getRadius() / earthRadius;
    }

    @JsonIgnore
    public double surfaceGravity() {
        return ( GRAVITATIONAL_CONSTANT * getMass() ) / (Math.pow( (getRadius() * 1000), 2) );
    }

    @Override
    public String toString() {
        return "The planet: " + getName() + " has a radius of: " + getRadius() + "km and has a mass of: " + getMass() + " kg.";
    }
}
