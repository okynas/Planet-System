package no.hiof.stianmar.oblig4.model;

/**
 * Moon class, extender / utvidder NaturalSatellite. (Inheritance / Arv)
 * Moon klassen arver fra natural satellite, det vil si at moon arver metodene og attributtene til natural satellite.
 */
public class Moon extends NaturalSatellite {

    /**
     * Konstruktør(er) for å opprette Måne med ulike parametere som kan sendes med.
     * Bruker tomme konstruktører ettersom plugin "JACKSON" krever det.
     */
    public Moon() {

    }

    public Moon(String name, double radius, double mass, double semiMajorAxis, double eccentricity, double orbitalPeriod) {
        super(name, radius, mass, semiMajorAxis, eccentricity, orbitalPeriod);
    }

    public Moon(String name, double radius, double mass, double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl) {
        super(name, radius, mass, semiMajorAxis, eccentricity, orbitalPeriod, pictureUrl);
    }

    @Override
    public String toString() {
        return "The Moon " + this.getName() + " belongs to ";
    }
}
