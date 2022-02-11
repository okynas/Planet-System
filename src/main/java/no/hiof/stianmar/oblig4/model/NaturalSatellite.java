package no.hiof.stianmar.oblig4.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class NaturalSatellite extends CelestialBody{

    private double semiMajorAxis;
    private double eccentricity;
    private double orbitalPeriod;
    private CelestialBody centralCelestialBody;
    private String pictureUrl;

    public NaturalSatellite() {

    }

    public NaturalSatellite(String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, CelestialBody celestialBody, String pictureUrl) {
        super(name, mass, radius,  pictureUrl);
        this.semiMajorAxis = semiMajorAxis;
        this.eccentricity = eccentricity;
        this.orbitalPeriod = orbitalPeriod;
        this.centralCelestialBody = celestialBody;
        this.pictureUrl = pictureUrl;
    }

    public NaturalSatellite(String name, double mass, double radius,  double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl) {
        super(name, mass, radius, pictureUrl);
        this.semiMajorAxis = semiMajorAxis;
        this.eccentricity = eccentricity;
        this.orbitalPeriod = orbitalPeriod;
    }

    public NaturalSatellite(String name, double mass, double radius,  double semiMajorAxis, double eccentricity, double orbitalPeriod) {
        super(name, mass, radius);
        this.semiMajorAxis = semiMajorAxis;
        this.eccentricity = eccentricity;
        this.orbitalPeriod = orbitalPeriod;
    }

    public NaturalSatellite(String name, double mass, double radius,  double semiMajorAxis, double eccentricity, double orbitalPeriod, CelestialBody celestialBody) {
        super(name, mass, radius);
        this.semiMajorAxis = semiMajorAxis;
        this.eccentricity = eccentricity;
        this.orbitalPeriod = orbitalPeriod;
        this.centralCelestialBody = celestialBody;
    }

    public double getSemiMajorAxis() {
        return semiMajorAxis;
    }

    public void setSemiMajorAxis(double semiMajorAxis) {
        this.semiMajorAxis = semiMajorAxis;
    }

    public double getEccentricity() {
        return eccentricity;
    }

    public void setEccentricity(double eccentricity) {
        this.eccentricity = eccentricity;
    }

    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public void setOrbitalPeriod(double orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }

    public CelestialBody getCentralCelestialBody() {
        return centralCelestialBody;
    }

    public void setCentralCelestialBody(CelestialBody centralCelestialBody) {
        this.centralCelestialBody = centralCelestialBody;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public int distanceToCentralBody(double degrees) {
        double a = getSemiMajorAxis();
        double e = getEccentricity();

        return (int) ( AU *(a * ( 1 - Math.pow(e, 2) ) ) / (1 + e * Math.cos(Math.toRadians(degrees))));
    }

    public int distanceToCentralBodyRadian(double radians) {
        return distanceToCentralBody(Math.toDegrees(radians));
    }

    // speed of orbiting satelite
    public double orbitingVelocity(double distance) {
        double G = GRAVITATIONAL_CONSTANT;
        double M = getCentralCelestialBody().getMass();
        double r = distance * 1000;

        return Math.round((Math.sqrt( (G*M) / r) / 1000) * 100.00) / 100.00; // km/s
    }

    public double orbitingVelocityMeterPerSecond(double distance) {
        return orbitingVelocity(distance) * 1000.00;
    }

    public int maxDistanceToCentralBody() {

        int maxDistance = distanceToCentralBody(0);

        for (int i = 1; i < 360; i++) {
            if (maxDistance < distanceToCentralBody(i)) {
                maxDistance = distanceToCentralBody(i);
            }
        }
        return maxDistance;
    }

    public int minDistanceToCentralBody() {

        int minDistance = distanceToCentralBody(0);

        for (int i = 1; i < 360; i++) {
            if (minDistance > distanceToCentralBody(i)) {
                minDistance = distanceToCentralBody(i);
            }
        }
        return minDistance;
    }

    @Override
    public String toString() {
        return "The Natural Satellite: " + getName() + " is inherited from Celestial Body";
    }
}
