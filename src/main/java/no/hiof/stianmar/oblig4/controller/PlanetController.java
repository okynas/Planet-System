package no.hiof.stianmar.oblig4.controller;

import io.javalin.http.Context;
import no.hiof.stianmar.oblig4.Application;
import no.hiof.stianmar.oblig4.model.Moon;
import no.hiof.stianmar.oblig4.model.Planet;
import no.hiof.stianmar.oblig4.repository.IUniverseRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class PlanetController {
    private IUniverseRepository universeRepository;

    public PlanetController(IUniverseRepository universityRepository) {
        this.universeRepository = universityRepository;
    }

    public void getOnePlanet(Context context) {
        String planetSystemName = context.pathParam("planet-system-id");
        String planetName = context.pathParam("planet-id");

        Planet onePlanet = universeRepository.getPlanet(planetSystemName, planetName);

        if (onePlanet == null) {
            context.status(404).result("Not found");
        } else {
            context.json(onePlanet);
        }
    }

    public void getAllPlanets(Context context) {

        String planetSystemName = context.pathParam("planet-system-id");
        String sortBy = context.queryParam("sort_by");

        ArrayList<Planet> planets = universeRepository.getAllPlanets(planetSystemName);

        /**
         * Sorterer planetene ut ifra kriterier: navn, masse, radius og tiden det tar for en runde rundt sola.
         * Returnerer -1 hvis o1 < o2,
         * Returnerer 0 hvis o1 = o2
         * Returnerer 1 hvis o1 > o2.
         *
         * Flere måter å sortere:
         * Collection.sort(ArrayList)
         *
         * arraylist.sort( (element1, element2) -> (int) (element1 - element2);
         *
         * HVIS den ikke implementerer comparator. Denne er også synkende.
         * Collection.sort(Arraylist, new Comparator<CustomData>() {
         *      @Override
         *      public int compare(CustomData lhs, CustomData rhs) {
         *          return lhs.customInt > rhs.customInt ? -1 : (lhs.customInt < rhs.customInt) ? 1 : 0;
         *      }
         * })
         *
         */
        if (sortBy != null) {
            switch (sortBy) {
                case "name":
                    Collections.sort(planets);
                    break;
                case "mass":
                    planets.sort((o1, o2) -> (int) (o1.getMass() - o2.getMass()));
                    break;
                case "radius":
                    planets.sort((o1, o2) -> {
                        if (o1.getRadius() < o2.getRadius())
                            return -1;
                        else if(o1.getRadius() > o2.getRadius())
                            return 1;
                        return 0;
                    });
                    break;
                case "num":
                    planets.sort((planetOne, planetTwo) -> (int)(planetOne.getOrbitalPeriod() - planetTwo.getOrbitalPeriod()));
                    break;
            }
        }


        context.json(planets);

    }

    public void deletePlanet(Context context) {
        String planetSystemName = context.pathParam("planet-system-id");
        String planetName = context.pathParam("planet-id");

        try {
            universeRepository.deletePlanet(planetSystemName, planetName, Application.filkilde);
        } catch (IOException e) {
            e.printStackTrace();
        }


        context.redirect("/planet-systems/" + planetSystemName);

    }

    public void createPlanet(Context context) {
        String planetSystemName = context.pathParam("planet-system-id");

        String planetNavn = context.formParam("name");
        String planetMasse = context.formParam("mass");
        String planetRadius = context.formParam("radius");
        String planetSemiMajor = context.formParam("semiMajorAxis");
        String planetEccentricity = context.formParam("eccentricity");
        String planetOrbital = context.formParam("orbitalPeriod");
        String planetPictureUrl = context.formParam("pictureUrl");

        universeRepository.createPlanet(planetSystemName, planetNavn, Double.parseDouble(planetMasse), Double.parseDouble(planetRadius), Double.parseDouble(planetSemiMajor), Double.parseDouble(planetEccentricity), Double.parseDouble(planetOrbital), planetPictureUrl, Application.filkilde);

        context.redirect("/planet-systems/" + planetSystemName);
    }

    public void updatePlanet(Context context) {

        deletePlanet(context);
        createPlanet(context);

        // ville opprinnelig ha gjort det slik, men det går an å forenkle det mye mer, slik ovenfor. Det eneste forskjellen er at
        // det nå vil slette en planet, og lage en ny planet med nesten de samme verdiene, untatt den eller de du forandret.

        /*
        String planetSystemName = context.pathParam("planet-system-id");
        String planetName = context.pathParam("planet-id");

        String planetNavn = context.formParam("name");
        String planetMasse = context.formParam("mass");
        String planetRadius = context.formParam("radius");
        String planetSemiMajor = context.formParam("semiMajorAxis");
        String planetEccentricity = context.formParam("eccentricity");
        String planetOrbital = context.formParam("orbitalPeriod");
        String planetPictureUrl = context.formParam("pictureUrl");

        universeRepository.oppdaterePlanet(planetSystemName, planetName, planetNavn, Double.parseDouble(planetMasse), Double.parseDouble(planetRadius), Double.parseDouble(planetSemiMajor), Double.parseDouble(planetEccentricity), Double.parseDouble(planetOrbital), planetPictureUrl, filkilde);

        context.redirect("/planet-systems/" + planetSystemName);
        */

    }
}