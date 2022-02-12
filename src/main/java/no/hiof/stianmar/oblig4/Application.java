package no.hiof.stianmar.oblig4;

import io.javalin.Javalin;
import io.javalin.plugin.rendering.vue.VueComponent;
import no.hiof.stianmar.oblig4.controller.MoonController;
import no.hiof.stianmar.oblig4.controller.PlanetController;
import no.hiof.stianmar.oblig4.controller.PlanetSystemController;
import no.hiof.stianmar.oblig4.model.Moon;
import no.hiof.stianmar.oblig4.repository.UniverseJSONRepository;


public class Application {

    // public static final String filkilde = "planets_4000.json";
    public static final String filkilde = "planets_2.json";

    /**
     * Starting "planet-system" program, with logging and with routes and controllers.
     *
     * @param args
     */
    public static void main(String[] args) {

        Javalin app = Javalin.create().start(); // starting the program
        app.config.enableWebjars(); // enabling static files.

        app.get("/", ctx -> ctx.redirect("/planet-systems")); // redirect if the start entrypoint is wrong.

        // controller
        UniverseJSONRepository universeJSONRepository = new UniverseJSONRepository(filkilde);
        PlanetSystemController planetSystemController = new PlanetSystemController(universeJSONRepository);
        PlanetController planetController = new PlanetController(universeJSONRepository);
        MoonController moonController = new MoonController(universeJSONRepository);


        //
        // ROUTING TO Planetsystems
        //

        // create
        app.get("/planet-systems/createPlanetSystem", new VueComponent("planet-systems-create"));
        app.post("/api/planet-systems/createPlanetSystem", planetSystemController::createPlanetSystem);

        // - overview / detail
        app.get("/planet-systems", new VueComponent("planet-systems-overview"));
        app.get("/planet-systems/:planet-system-id", new VueComponent("planet-systems-detail"));

        app.get("/api/planet-systems", planetSystemController::getAllPlanetSystems);
        app.get("/api/planet-systems/:planet-system-id", planetSystemController::getOnePlanetSystem);

        // delete
        app.get("/api/planet-systems/:planet-system-id/delete", planetSystemController::deletePlanetSystem);

        //
        // Routing to Planets
        //

        // planets - overview / detail
        app.get("/planet-systems/:planet-system-id/planets/:planet-id", new VueComponent("planet-detail"));
        app.get("/api/planet-systems/:planet-system-id/planets", planetController::getAllPlanets);
        app.get("/api/planet-systems/:planet-system-id/planets/:planet-id", planetController::getOnePlanet);

        // create planet
        app.get("/planet-systems/:planet-system-id/createplanet", new VueComponent("planet-create"));
        app.post("/api/planet-systems/:planet-system-id/createplanet", planetController::createPlanet);

        // update planet
        app.get("/planet-systems/:planet-system-id/planets/:planet-id/update", new VueComponent("planet-update"));
        app.post("/api/planet-systems/:planet-system-id/planets/:planet-id/update", planetController::updatePlanet);

        // delete planet
        app.get("/api/planet-systems/:planet-system-id/planets/:planet-id/delete", planetController::deletePlanet);


        //
        // Routing to moon.
        //

        // moons - overview:
        app.get("/api/planet-systems/:planet-system-id/planets/:planet-id/moon", moonController::getAllMoons);
        app.get("/api/planet-systems/:planet-system-id/planets/:planet-id/moon/:moon-id", moonController::getOneMoon);

        // create planet
        app.get("/planet-systems/:planet-system-id/planets/:planet-id/createmoon", new VueComponent("moon-create"));
        app.post("/api/planet-systems/:planet-system-id/planets/:planet-id/createmoon", moonController::createMoon);

        app.get("/planet-systems/:planet-system-id/planets/:planet-id/moon/:moon-id", new VueComponent("moon-detail"));


        // TODO:
        // Adding routes to create moon
        // Adding routes to update moon
        // Adding routes to delete moon

    }
}