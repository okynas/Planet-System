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
}
