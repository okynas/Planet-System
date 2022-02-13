package no.hiof.stianmar.oblig4.controller;

import io.javalin.http.Context;
import no.hiof.stianmar.oblig4.model.Moon;
import no.hiof.stianmar.oblig4.model.Planet;
import no.hiof.stianmar.oblig4.repository.IUniverseRepository;

import java.util.ArrayList;

public class MoonController {
    private IUniverseRepository universeRepository;

    public MoonController(IUniverseRepository universityRepository) {
        this.universeRepository = universityRepository;
    }

    public void getAllMoons(Context context) {
        String planetSystemName = context.pathParam("planet-system-id");
        String planetName = context.pathParam("planet-id");
        ArrayList<Moon> moons = universeRepository.getAllMoons(planetSystemName, planetName);

        context.json(moons);
    }

    public void getOneMoon(Context context) {
        String planetSystemName = context.pathParam("planet-system-id");
        String planetName = context.pathParam("planet-id");
        String moonName =  context.pathParam("moon-id");

        Moon oneMoon = universeRepository.getOneMoon(planetSystemName, planetName, moonName);

        if (oneMoon == null) {
            context.status(404).result("Not found");
        } else {
            context.json(oneMoon);
        }
    }
<<<<<<< Updated upstream
=======

    public void createMoon(Context context) {
        String planetSystemName = context.pathParam("planet-system-id");
        String planetNavn =  context.pathParam("planet-id");

        String moonName = context.formParam("name");
        String planetMasse = context.formParam("mass");
        String planetRadius = context.formParam("radius");
        String planetSemiMajor = context.formParam("semiMajorAxis");
        String planetEccentricity = context.formParam("eccentricity");
        String planetOrbital = context.formParam("orbitalPeriod");
        String planetPictureUrl = context.formParam("pictureUrl");

        universeRepository.createMoon(planetSystemName, planetNavn, moonName, Double.parseDouble(planetMasse), Double.parseDouble(planetRadius), Double.parseDouble(planetSemiMajor), Double.parseDouble(planetEccentricity), Double.parseDouble(planetOrbital), planetPictureUrl, Application.filkilde);

        context.redirect("/planet-systems/" + planetSystemName + "/planets/" + planetNavn);
    }

    public void deleteMoon(Context context) {
        String planetSystemName = context.pathParam("planet-system-id");
        String planetNavn =  context.pathParam("planet-id");
        String moonName = context.pathParam("moon-id");

        universeRepository.deleteMoon(planetSystemName, planetNavn, moonName, Application.filkilde);
        context.redirect("/planet-systems/" + planetSystemName + "/planets/" + planetNavn);
    }
>>>>>>> Stashed changes
}
