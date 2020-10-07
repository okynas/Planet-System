package no.hiof.stianmar.oblig4.controller;

import io.javalin.http.Context;
import no.hiof.stianmar.oblig4.Application;
import no.hiof.stianmar.oblig4.model.Planet;
import no.hiof.stianmar.oblig4.model.PlanetSystem;
import no.hiof.stianmar.oblig4.repository.IUniverseRepository;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class PlanetSystemController {

    private IUniverseRepository universeRepository;

    public PlanetSystemController(IUniverseRepository iUniverseRepository) {
        this.universeRepository = iUniverseRepository;
    }

    public void getOnePlanetSystem(Context context) {
        String planetSystemName = context.pathParam("planet-system-id");
        PlanetSystem planetSystem = universeRepository.getPlanetSystem(planetSystemName);
        context.json(planetSystem);
    }

    public void getAllPlanetSystems (Context context) {
        ArrayList<PlanetSystem> planetSystems = universeRepository.getAllPlanetSystem();
        context.json(planetSystems);
    }

    public void createPlanetSystem(Context context) {
        // String planetSystemName = context.pathParam("planet-system-id");
        // String filkilde = "planets_100.csv";
        // String filkilde = "planets_100.json";

        String planetSystemNavn = context.formParam("name");
        String starName = context.formParam("star");
        String starMasse = context.formParam("mass");
        String starRadius = context.formParam("radius");
        String starEffectiveTemperature = context.formParam("effectiveTemperature");
        String starPictureUrl = context.formParam("starPictureUrl");
        String planetSystemPictureUrl = context.formParam("planetSystemPictureUrl");

        universeRepository.createPlanetSystem(planetSystemNavn, starName, Double.parseDouble(starMasse), Double.parseDouble(starRadius), Integer.parseInt(starEffectiveTemperature), starPictureUrl, planetSystemPictureUrl, Application.filkilde);

        context.redirect("/planet-systems");
    }

    public void deletePlanetSystem(Context context) {
        String planetSystemName = context.pathParam("planet-system-id");
        universeRepository.deletePlanetSystem(planetSystemName, Application.filkilde);
        context.redirect("/planet-systems");;
    }
}
