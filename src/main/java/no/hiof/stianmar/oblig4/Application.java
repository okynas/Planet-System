package no.hiof.stianmar.oblig4;


import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.plugin.rendering.vue.VueComponent;
import no.hiof.stianmar.oblig4.controller.PlanetController;
import no.hiof.stianmar.oblig4.controller.PlanetSystemController;
import no.hiof.stianmar.oblig4.repository.UniverseCSVRepository;
import no.hiof.stianmar.oblig4.repository.UniverseJSONRepository;
//import no.hiof.stianmar.oblig4.repository.UniverseRepository;
import org.jetbrains.annotations.NotNull;

public class Application {

    public static final String filkilde = "planets_100.csv";
    // public static final String filkilde = "planets_4000.json";

    public static void main(String[] args) {
        Javalin app = Javalin.create().start();
        app.config.enableWebjars();

        app.get("/", ctx -> ctx.redirect("/planet-systems"));

        // controller
        //UniverseRepository universeRepositoryRepository = new UniverseRepository();
        //PlanetSystemController planetSystemController = new PlanetSystemController(universeRepositoryRepository);
        //PlanetController planetController = new PlanetController(universeRepositoryRepository);

        // csv
        UniverseCSVRepository universeCSVRepository = new UniverseCSVRepository(filkilde);
        PlanetSystemController planetSystemController = new PlanetSystemController(universeCSVRepository);
        PlanetController  planetController = new PlanetController(universeCSVRepository);


        // json
        /*UniverseJSONRepository universeJSONRepository = new UniverseJSONRepository(filkilde);
        PlanetSystemController planetSystemController = new PlanetSystemController(universeJSONRepository);
        PlanetController planetController = new PlanetController(universeJSONRepository);*/




        app.get("/planet-systems/:planet-system-id/createplanet", new VueComponent("planet-create"));

        // create
        app.get("/planet-systems", new VueComponent("planet-systems-overview"));
        app.post("/api/planet-systems/:planet-system-id/createplanet", planetController::createPlanet);

        // update
        app.get("/planet-systems/:planet-system-id/planets/:planet-id/update", new VueComponent("planet-update"));
        app.post("/api/planet-systems/:planet-system-id/planets/:planet-id/update", planetController::updatePlanet);

        // delete
        app.get("/api/planet-systems/:planet-system-id/planets/:planet-id/delete", planetController::deletePlanet);

        // planetsystems - create / delete
        app.get("/planet-systems/createPlanetSystem", new VueComponent("planet-systems-create"));
        app.post("/api/planet-systems/createPlanetSystem", planetSystemController::createPlanetSystem);
        app.get("/api/planet-systems/:planet-system-id/delete", planetSystemController::deletePlanetSystem);

        // - overview / detail
        app.get("/planet-systems/:planet-system-id", new VueComponent("planet-systems-detail"));
        app.get("/api/planet-systems", planetSystemController::getAllPlanetSystems);
        app.get("/api/planet-systems/:planet-system-id", planetSystemController::getOnePlanetSystem);

        // planets - overview / detail
        app.get("/planet-systems/:planet-system-id/planets/:planet-id", new VueComponent("planet-detail"));
        app.get("/api/planet-systems/:planet-system-id/planets", planetController::getAllPlanets);
        app.get("/api/planet-systems/:planet-system-id/planets/:planet-id", planetController::getOnePlanet);

    }
}