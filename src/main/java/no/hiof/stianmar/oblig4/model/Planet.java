package no.hiof.stianmar.oblig4.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Oppretter klassen Planet, som arver fra NaturalSatellite.
 */
public class Planet extends NaturalSatellite {


    /**
     * Ser her at det er enkapsulering. Dvs at attributtene er private, men at de trenger getters- og setters, for å få tilgang
     * til disse attributtene.
     */
    private static final double earthMass = 5.972E24;
    private static final int earthRadius = 6371;
    private static final double jupiterMass = 1.898E27;
    private static final int jupiterRadius = 71492;

    private ArrayList<Moon> moon;

    /**
     * Konstruktør(er) til Planet med ulike parametere.
     */
    public Planet() {

    }

    public Planet(String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, CelestialBody celestialBody, ArrayList<Moon> moon) {
        super(name, mass,radius, semiMajorAxis, eccentricity, orbitalPeriod, celestialBody);
        this.moon = moon;
    }

    public Planet(String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, CelestialBody celestialBody, String pictureUrl, ArrayList<Moon> moon) {
        super(name, mass,radius, semiMajorAxis, eccentricity, orbitalPeriod, celestialBody, pictureUrl);
        this.moon = moon;
    }


    /**
     * Getters og setters for månene til gjeldende planet. Brukes iht enkapsulering, for å hente og endre verdier på private verdier.
     */
    public ArrayList<Moon> getMoon() {
        return moon;
    }

    public void setMoon(ArrayList<Moon> moon) {
        this.moon = moon;
    }


    /**
     * Metoder som ikke blir sendt med til api-et. Det er PGA @JsonIgnore (som er plugin) fra:
     * com.fasterxml.jackson.module:jackson-module-kotlin:2.9.9 -> sjekk build.gradle
     */

    /**
     * JupiterMasse, er masseenhet tilsvarende den totale massen til Jupiter
     * De andre nedenfor er radius i forhold til jupiter, og masse og radiuis ift jorda.
     */
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

    /**
     * Overflate Gravitasjon. Gitt av newtons law of universal gravitation:
     * https://en.wikipedia.org/wiki/Newton%27s_law_of_universal_gravitation
     *
     * g = GM/r^2
     */
    @JsonIgnore
    public double surfaceGravity() {
        return ( GRAVITATIONAL_CONSTANT * getMass() ) / (Math.pow( (getRadius() * 1000), 2) );
    }

    /**
     * En toString metode som kalles når man printer objektet "planet".
     * Overrider, fordi den vanlige toString metoden til klasser blir endret.
     */
    @Override
    public String toString() {
        return getName();
        //return "The planet: " + getName() + " has a radius of: " + getRadius() + "km and has a mass of: " + getMass() + " kg.";
    }
}
