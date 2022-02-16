/**
 * Organiserer java klasser. Gir unikt navnomåråde, klasser i samme pakke, kan få tak i hverandres private og protected medlemmer. 
 */
package no.hiof.stianmar.oblig4.model;

/**
 * Moon class, extender / utvidder NaturalSatellite. (Inheritance / Arv)
 * Moon klassen arver fra natural satellite, det vil si at moon arver metodene og attributtene til natural satellite.
 */
public class Moon extends NaturalSatellite {

    /**
     * Konstruktør(er) for å opprette Måne med ulike parametere som kan sendes med.
     * Bruker tomme konstruktører ettersom plugin "JACKSON" krever det.
     *
     * Generell konstruktører her: method-overloading -> bruker "metode" med samme navn, men ulike parametere som sendes med
     * Ved metoder, så kan også retur typen være forskjellig.
     *
     * Dette går på gjenbruk av kode.
     */
    public Moon() {

    }

    public Moon(String name, double radius, double mass, double semiMajorAxis, double eccentricity, double orbitalPeriod) {
        super(name, radius, mass, semiMajorAxis, eccentricity, orbitalPeriod);
    }

    public Moon(String name, double radius, double mass, double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl) {
        super(name, radius, mass, semiMajorAxis, eccentricity, orbitalPeriod, pictureUrl);
    }

    /**
     * Metode overriding: når man arver fra andre klasser, som f.eks. toString() metoden her, fra tidligere klasser.
     * Da kan det være lurt å endre på denne, slik at den gjeldende klassen får en skreddersydd metode.
     * Her vil det dermed være en toString() som vil si at om vi ikke hadde endret på denne, ville den returnert NaturalSatellite
     * sin toString() metode.
     *
     *
     * @return månens navn.
     */
    @Override
    public String toString() {
        return "The Moon " + this.getName() + " belongs to ";
    }
}
