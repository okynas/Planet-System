package no.hiof.stianmar.oblig4.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class PlanetSystem implements Comparable<Planet>{

    private String name;
    private String pictureUrl;
    private Star centerStar;
    private ArrayList<Planet> planets;

    private Planet greatestPlanet;
    private Planet smallestPlanet;

    public PlanetSystem() {

    }

    public PlanetSystem(String name, Star centerStar, ArrayList<Planet> planets, String pictureUrl) {
        this.name = name;
        this.centerStar = centerStar;
        this.planets = planets;
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

    public Star getCenterStar() {
        return centerStar;
    }

    public void setCenterStar(Star centerStar) {
        this.centerStar = centerStar;
    }

    public ArrayList<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(ArrayList<Planet> planets) {
        this.planets = planets;
    }

    /**
     * Hent ut en planet knyttet til et planetsystem.
     */
    public Planet getOnePlanet(String name) {
        ArrayList<Planet> allPlanets = getPlanets();

        for (Planet selectedPlanet : allPlanets) {
            // bruker toLowerCase slik at man kan bruke e.g. Mars og mars til å finne planeten mars.
            if (selectedPlanet.getName().toLowerCase().equals(name.toLowerCase())) {
                return selectedPlanet;
            }
        }

        return null;
    }

    /**
     * En metode for å finne størst planet i planetsystemet / solsystemet.
     * Hvis to planeter har lik radius, vil det regnes ut største planet etter
     * masse.
     */
    @JsonIgnore
    public Planet greatestPlanet() {
        Planet greatestPlanet = getPlanets().get(0);
        double max = greatestPlanet.getRadius();

        for (int i = 1; i < getPlanets().size(); i++) {

            if ( max < getPlanets().get(i).getRadius() ) {
                max = getPlanets().get(i).getRadius();
                greatestPlanet = getPlanets().get(i);
            } else if ( max == planets.get(i).getRadius() ) {
                if (greatestPlanet.getMass() < planets.get(i).getMass()){
                    greatestPlanet = getPlanets().get(i);
                }
            }

        }
        return greatestPlanet;
    }

    /**
     * En metode for å finne minste planet, hvis to planeter har lik radius,
     * vil minste planet regners ut ift masse.
     */
    @JsonIgnore
    public Planet smallestPlanet() {
        Planet smallestPlanet = getPlanets().get(0);
        double min = smallestPlanet.getRadius();

        for (int i = 1; i < getPlanets().size(); i++) {

            if (min > getPlanets().get(i).getRadius()) {
                min = getPlanets().get(i).getRadius();
                smallestPlanet = getPlanets().get(i);
            } else if (min == getPlanets().get(i).getRadius()) {
                if (smallestPlanet().getMass() > getPlanets().get(i).getMass()) {
                    smallestPlanet = getPlanets().get(i);
                }
            }

        }

        return smallestPlanet;
    }

    @Override
    public String toString() {
        String names = "Solar system includes the planets: ";

        for (int i = 0; i < getPlanets().size(); i++) {
            names += planets.get(i).getName() + ", ";
        }
        return names;
    }

    /**
     * Sammenligner to planeters med hverandre. Denne metoden brukes videre i PlanetController->getAllPlanets() for å
     * sortere planetene etter navn, masse, nummer og radius.
     */
    @Override
    public int compareTo(Planet sammenligningsPlanet) {
        return this.name.compareTo(sammenligningsPlanet.getName());
    }
}
