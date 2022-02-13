package no.hiof.stianmar.oblig4.repository;

import no.hiof.stianmar.oblig4.model.Moon;
import no.hiof.stianmar.oblig4.model.Planet;
import no.hiof.stianmar.oblig4.model.PlanetSystem;

import java.io.IOException;
import java.util.ArrayList;

public interface IUniverseRepository {
    ArrayList<PlanetSystem> getAllPlanetSystem();
    PlanetSystem getPlanetSystem(String planetSystemName);

    ArrayList<Planet> getAllPlanets(String planetSystemName);
    Planet getPlanet(String planetSystemNavn, String planetName);

    // create planet systems
    void createPlanetSystem(String planetSystemName, String starName, double starMass, double starRadius, int starEffectiveTemperature, String starPictureUrl, String pictureUrl, String filkilde);
    void deletePlanetSystem(String planetSystemName, String filkilde);

    void createPlanet(String planetSystemName, String planetName, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl, String filkilde);
    void updatePlanet(String planetSystemNavn, String oldPlanetName, String planetName, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl, String filkilde);
    void deletePlanet(String planetSystemNavn, String planetNavn, String filkilde) throws IOException;

    Moon getOneMoon(String planetSystemName, String planetName, String moonName);
    ArrayList<Moon> getAllMoons(String planetSystemName, String planetName);
<<<<<<< Updated upstream
=======
    void createMoon(String planetSystemName, String planetName, String moonName, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl, String filkilde);
    void deleteMoon(String planetSystemName, String planetName, String moonName, String filkilde);
>>>>>>> Stashed changes
}

