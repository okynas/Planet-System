package no.hiof.stianmar.oblig4.model;

public class Moon extends NaturalSatellite {

    public Moon(String name, double radius, double mass, double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl) {

        super(name, radius, mass, semiMajorAxis, eccentricity, orbitalPeriod, null, pictureUrl);
    }

    @Override
    public String toString() {
        return "The Moon " + this.getName() + " belongs to ";
    }
}
