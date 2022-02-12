package no.hiof.stianmar.oblig4.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


/**
 * Disse "merknadene" er med for å støtte polymorfisme ved deserialisering, (konstruere java objekt i minet ved input av byte stream)
 * Ved kjøring ved deserialisering vil koden vite hvilken klasse den bør forvente.
 *
 * Bruker også SubTyper til å fortelle koden hva som skal forvente av undergrupper:
 *
 * {
 *     name: stian martinsen
 *     adress: ...
 *     ...
 * }
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Star.class, name = "star"),
        @JsonSubTypes.Type(value = Planet.class, name = "Planet"),
        @JsonSubTypes.Type(value = Moon.class, name = "moon")
})

/**
 * Benytter comparable til å sammenligne planeter. Les mer om i bunn av koden!
 */
public abstract class CelestialBody implements Comparable<Planet>{
    private String name, pictureUrl;
    private double radius;
    private double mass;

    public static final int ASTRONOMICAL_UNITS_IN_KM = 149597871;

    public static final double GRAVITATIONAL_CONSTANT = 6.67408E-11;

    public CelestialBody() {

    }

    public CelestialBody(String name, double mass, double radius) {
        this.name = name;
        this.radius = radius;
        this.mass = mass;
    }

    public CelestialBody(String name, double mass, double radius, String pictureUrl) {
        this.name = name;
        this.radius = radius;
        this.mass = mass;
        this.pictureUrl = pictureUrl;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    @Override
    public String toString() {
        return "The CelestialBody: " + getName() + " is a celestial body";
    }

    /**
     * Sammenligner to planeters med hverandre. Denne metoden brukes videre i PlanetController->getAllPlanets() for å
     * sortere planetene etter navn, masse, nummer og radius.
     */
    @Override
    public int compareTo(Planet thisPlanet) {
        return this.name.compareTo(thisPlanet.getName());
    }

}
