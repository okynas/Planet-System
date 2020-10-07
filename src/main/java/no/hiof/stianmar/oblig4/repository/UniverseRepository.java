package no.hiof.stianmar.oblig4.repository;

import no.hiof.stianmar.oblig4.model.Moon;
import no.hiof.stianmar.oblig4.model.Planet;
import no.hiof.stianmar.oblig4.model.PlanetSystem;
import no.hiof.stianmar.oblig4.model.Star;

import java.util.ArrayList;

public class UniverseRepository implements IUniverseRepository {
    private ArrayList<PlanetSystem> allPlanetSystem = new ArrayList<>();
    private ArrayList<Planet> allPlanets = new ArrayList<>();
    private ArrayList<Moon> allMoons = new ArrayList<>();

    public UniverseRepository() {

        // create arrayList of planets object
        ArrayList<Planet> planetsInSolarSystem = new ArrayList<>();

        // create star
        Star sun = new Star("Sun", 695342, 1.9885E30, 5777, "https://upload.wikimedia.org/wikipedia/commons/c/c3/Solar_sys8.jpg");

        Moon earthMoon = new Moon("Moon", 1737.4, 7.342E22, 0.0025, 0.0549, 27.3, "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e1/FullMoon2010.jpg/220px-FullMoon2010.jpg");
        ArrayList<Moon> earthMoons = new ArrayList<>();
        earthMoons.add(earthMoon);

        Moon phobos = new Moon("Phobos", 11, 1.0659E16, 9376, 0.0151, 0.318, "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Phobos_colour_2008.jpg/220px-Phobos_colour_2008.jpg");
        Moon deimos = new Moon("Deimos", 6.2, 1.4762E15, 23462.2, 0.00033, 1.263, "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8d/Deimos-MRO.jpg/220px-Deimos-MRO.jpg");
        ArrayList<Moon> marsMoons = new ArrayList<>();
        marsMoons.add(deimos);
        marsMoons.add(phobos);

        Moon io = new Moon("Io", 1821.6, 8.931E22, 0, 0.0041, 1.769, "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7b/Io_highest_resolution_true_color.jpg/220px-Io_highest_resolution_true_color.jpg");
        Moon europa = new Moon("Europa", 1560.8, 4.799E22, 0, 0.009, 3.551, "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e4/Europa-moon-with-margins.jpg/220px-Europa-moon-with-margins.jpg");
        Moon ganymede = new Moon("Ganymede ", 2634.1, 1.4819E23, 1070400, 0.0013, 7.154, "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f2/Ganymede_g1_true-edit1.jpg/220px-Ganymede_g1_true-edit1.jpg");
        Moon callisto = new Moon("Callisto ", 2410.3,   1.075E23, 1882700, 0.0074, 16.689, "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e9/Callisto.jpg/220px-Callisto.jpg");

        ArrayList<Moon> jupiterMoon = new ArrayList<>();
        jupiterMoon.add(io);
        jupiterMoon.add(europa);
        jupiterMoon.add(ganymede);
        jupiterMoon.add(callisto);

        Moon triton = new Moon("Triton", 1353.4, 2.139E22, 354759, 0.000016, 5.876, "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a6/Triton_moon_mosaic_Voyager_2_%28large%29.jpg/300px-Triton_moon_mosaic_Voyager_2_%28large%29.jpg");
        ArrayList<Moon> neptuneMoons = new ArrayList<>();
        neptuneMoons.add(triton);

        Moon mimas = new Moon("Mimas", 198.2, 3.7493E19, 185539, 0.0196, 0.942, "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Mimas_Cassini.jpg/220px-Mimas_Cassini.jpg");
        Moon titan = new Moon("Titan", 2573.73, 1.3452E23, 1221870, 0.0288, 15.945, "https://upload.wikimedia.org/wikipedia/commons/thumb/4/45/Titan_in_true_color.jpg/220px-Titan_in_true_color.jpg");
        Moon enceladus = new Moon("Enceladus", 252.1, 1.08E20, 237948, 0.0047, 1.37, "https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/PIA17202_-_Approaching_Enceladus.jpg/280px-PIA17202_-_Approaching_Enceladus.jpg");
        ArrayList<Moon> saturnMoons = new ArrayList<>();
        saturnMoons.add(mimas);
        saturnMoons.add(titan);
        saturnMoons.add(enceladus);

        Moon umbriel = new Moon("Umbriel", 584.7, 1.275E21, 266000, 0.0039, 4.144, "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/PIA00040_Umbrielx2.47.jpg/260px-PIA00040_Umbrielx2.47.jpg");
        Moon titania = new Moon("Titania", 788.4, 3.4E21, 435910, 0.0011, 8.706, " https://upload.wikimedia.org/wikipedia/commons/thumb/2/2e/Titania_%28moon%29_color%2C_edited.jpg/260px-Titania_%28moon%29_color%2C_edited.jpg");
        Moon oberon = new Moon("Oberon", 761.4, 3076E21, 583520, 0.0014, 13.463, "https://upload.wikimedia.org/wikipedia/commons/thumb/0/09/Voyager_2_picture_of_Oberon.jpg/220px-Voyager_2_picture_of_Oberon.jpg");
        Moon miranda = new Moon("Miranda", 235.8, 6.4E19, 129390, 0.0013, 1.413, "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e1/PIA18185_Miranda%27s_Icy_Face.jpg/220px-PIA18185_Miranda%27s_Icy_Face.jpg");
        Moon ariel = new Moon("Ariel", 578.9, 1.251E21, 191020, 0.0012, 2.52, "https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/Ariel_%28moon%29.jpg/220px-Ariel_%28moon%29.jpg");

        ArrayList<Moon> uranusMoons = new ArrayList<>();
        uranusMoons.add(umbriel);
        uranusMoons.add(titania);
        uranusMoons.add(oberon);
        uranusMoons.add(miranda);
        uranusMoons.add(ariel);

        allMoons.add(earthMoon);
        allMoons.add(deimos);
        allMoons.add(phobos);
        allMoons.add(io);
        allMoons.add(europa);
        allMoons.add(ganymede);
        allMoons.add(callisto);
        allMoons.add(triton);
        allMoons.add(mimas);
        allMoons.add(titan);
        allMoons.add(enceladus);
        allMoons.add(umbriel);
        allMoons.add(titania);
        allMoons.add(oberon);
        allMoons.add(miranda);
        allMoons.add(ariel);

        // create planets
        Planet mercury = new Planet("Mercury", 3.283E23,2439.7, 0.387, 0.206, 88, sun, "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/Transit_Of_Mercury%2C_May_9th%2C_2016.png/480px-Transit_Of_Mercury%2C_May_9th%2C_2016.png");
        Planet venus = new Planet("Venus",4.867E24,6051.8, 0.723, 0.007, 225, sun, "https://upload.wikimedia.org/wikipedia/commons/e/e5/Venus-real_color.jpg");
        Planet earth = new Planet("Earth",5.972E24,6371, 1, 0.017, 365, sun, "https://upload.wikimedia.org/wikipedia/commons/thumb/a/aa/NASA_Earth_America_2002.jpg/480px-NASA_Earth_America_2002.jpg", earthMoons);
        Planet mars = new Planet("Mars",6.39E23,3389.5, 1.524, 0.093, 687, sun, "https://upload.wikimedia.org/wikipedia/commons/thumb/5/58/Mars_23_aug_2003_hubble.jpg/480px-Mars_23_aug_2003_hubble.jpg", marsMoons);
        Planet jupiter = new Planet("Jupiter",1.898E27,69911, 5.20440, 0.049, 4380, sun, "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2b/Jupiter_and_its_shrunken_Great_Red_Spot.jpg/480px-Jupiter_and_its_shrunken_Great_Red_Spot.jpg", jupiterMoon);
        Planet saturn = new Planet("Saturn",5.683E26,58232, 9.5826, 0.057, 10585, sun, "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1c/Saturn_from_Cassini_Orbiter_-_Square_%282004-10-06%29.jpg/480px-Saturn_from_Cassini_Orbiter_-_Square_%282004-10-06%29.jpg", saturnMoons);
        Planet uranus = new Planet("Uranus",8.681E25,25362, 19.2184, 0.046, 30660, sun, "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3d/Uranus2.jpg/480px-Uranus2.jpg", uranusMoons);
        Planet neptune = new Planet("Neptune",1.024E26,24622, 30.11, 0.010, 60225, sun, "https://upload.wikimedia.org/wikipedia/commons/thumb/4/41/Neptune_Full_%28cropped%29.jpg/480px-Neptune_Full_%28cropped%29.jpg", neptuneMoons);

        // add planets to planet array
        planetsInSolarSystem.add(mercury);
        planetsInSolarSystem.add(venus);
        planetsInSolarSystem.add(earth);
        planetsInSolarSystem.add(mars);
        planetsInSolarSystem.add(jupiter);
        planetsInSolarSystem.add(saturn);
        planetsInSolarSystem.add(uranus);
        planetsInSolarSystem.add(neptune);

        // create solar system
        PlanetSystem milkyWay = new PlanetSystem("Solar System", sun, planetsInSolarSystem, "https://upload.wikimedia.org/wikipedia/commons/c/c3/Solar_sys8.jpg");

        // https://en.wikipedia.org/wiki/MOA-2007-BLG-192Lb
        ArrayList<Planet> planetsInM2B1 = new ArrayList<>();
        Star moa2007Blg192L = new Star("MOA-2007-BLG-192L", 0, 0.06*earth.mEarth(),0, "https://upload.wikimedia.org/wikipedia/en/f/f9/MOA-2007-BLG-192_Planet.png");

        Planet moa2007Blg192Lb = new Planet("MOA-2007-BLG-192Lb", 0, 3.3*earth.mEarth(), 0, 0, 0, moa2007Blg192L);
        planetsInM2B1.add(moa2007Blg192Lb);

        PlanetSystem moa2007Blg192L_PlanetSystem = new PlanetSystem("MOA-2007-BLG-192L", moa2007Blg192L, planetsInM2B1, "https://upload.wikimedia.org/wikipedia/en/f/f9/MOA-2007-BLG-192_Planet.png");

        // https://www.en.wikipedia.org/wiki/Kepler-11#Planetary_system
        // exoplanet.eu/catalog

        ArrayList<Planet> planetsInKepler11 = new ArrayList<>();
        Star kepler11 = new Star("Kepler-11", 1.042 * Star.solarMass, 1.021 * Star.solarRadius,5836, "https://upload.wikimedia.org/wikipedia/commons/6/64/Kepler11.png");


        Planet b = new Planet("Kepler-11b",2.56796E25,12550,1.36134E7,0.045,10, kepler11);
        Planet c = new Planet("Kepler-11c",8.0622E25,20068,1.5857E7,0.026,13,kepler11);
        Planet d = new Planet("Kepler-11d",3.64292E25,21852,2.3786E7,0.004,22,kepler11);
        Planet e = new Planet("Kepler-11e",5.01648E25,28796,2.9021E7,0.012,31,kepler11);
        Planet f = new Planet(" Kepler-11f",1.37356E25,16628,3.7399E7,0.013,36,kepler11);
        Planet g = new Planet("Kepler-11g",1.7916E27,23317,6.9114E7,0.015,118,kepler11);

        planetsInKepler11.add(b);
        planetsInKepler11.add(c);
        planetsInKepler11.add(d);
        planetsInKepler11.add(e);
        planetsInKepler11.add(f);
        planetsInKepler11.add(g);

        PlanetSystem kepler11PlanetSystem = new PlanetSystem("Kepler-11", kepler11, planetsInKepler11, "https://upload.wikimedia.org/wikipedia/commons/6/64/Kepler11.png");

        Star hd74186 = new Star("HD74186", 1.12*sun.getRadius(), 1.05*sun.getMass(),5736, "");

        ArrayList<Planet> planetsInHD7486 = new ArrayList<>();

        Planet hd74186b = new Planet("HD74186b", 0.07*jupiter.getMass(),0, 0.050, 0.038,4.08, hd74186, "");
        Planet hd74186c = new Planet("HD74186c",0.35061*jupiter.getMass(), 0, 2.395, 0.249,1353.6,hd74186, "");

        planetsInHD7486.add(hd74186b);
        planetsInHD7486.add(hd74186c);

        PlanetSystem hd74186PlanetSystem = new PlanetSystem("HD74186", hd74186, planetsInHD7486, "");

        allPlanets.addAll(planetsInSolarSystem);
        allPlanets.addAll(planetsInKepler11);
        allPlanets.addAll(planetsInM2B1);
        allPlanets.addAll(planetsInHD7486);

        allPlanetSystem.add(milkyWay);
        allPlanetSystem.add(kepler11PlanetSystem);
        allPlanetSystem.add(moa2007Blg192L_PlanetSystem);
        allPlanetSystem.add(hd74186PlanetSystem);
    }

    @Override
    public Moon getOneMoon(String moonName) {

        for (Moon oneMoon: allMoons) {
            if (oneMoon.getName().equals(moonName)) {
                return oneMoon;
            }
        }

        return null;
    }

    @Override
    public ArrayList<Moon> getAllMoons(String planetName) {

        /*ArrayList<Moon> allMoons = getPlanet(planetName).getMoon();

        if (allMoons != null) {
            return allMoons;
        }

        return new ArrayList<Moon>();*/
        return null;
    }

    @Override
    public ArrayList<PlanetSystem> getAllPlanetSystem() {
        if (allPlanetSystem != null) {
            return allPlanetSystem;
        }
        return new ArrayList<>();
    }

    // get one planetsytsem
    @Override
    public PlanetSystem getPlanetSystem(String planetSystemName) {

        for (PlanetSystem planetsystem: allPlanetSystem) {
            if (planetsystem.getName().equals(planetSystemName)) {
                return planetsystem;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Planet> getAllPlanets(String planetSystemName) {

        ArrayList<Planet> currentPlanet = getPlanetSystem(planetSystemName).getPlanets();

        if (currentPlanet != null) {
            return currentPlanet;
        }

        return null;
    }

    @Override
    public void createPlanet(String planetSystemName, String planetName, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl, String filkilde) {

    }

    @Override
    public void updatePlanet(String planetSystemNavn, String oldPlanetName, String planetName, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl, String filkilde) {

    }

    @Override
    public void deletePlanet(String planetSystemNavn, String planetNavn, String filkilde) {

    }

    @Override
    public void createPlanetSystem(String planetSystemName, String starName, double starMass, double starRadius, int starEffectiveTemperature, String starPictureUrl, String pictureUrl, String filkilde) {

    }

    @Override
    public Planet getPlanet(String planetSystemNavn, String planetName) {
        return null;
    }

    @Override
    public void deletePlanetSystem(String planetSystemName, String filkilde) {

    }
}