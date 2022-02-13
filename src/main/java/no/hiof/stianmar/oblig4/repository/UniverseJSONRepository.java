package no.hiof.stianmar.oblig4.repository;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.hiof.stianmar.oblig4.model.Moon;
import no.hiof.stianmar.oblig4.model.Planet;
import no.hiof.stianmar.oblig4.model.PlanetSystem;
import no.hiof.stianmar.oblig4.model.Star;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class UniverseJSONRepository implements IUniverseRepository{

    private ArrayList<PlanetSystem> planetSystems = new ArrayList<>();

    public UniverseJSONRepository(String filnavn) {

        ArrayList<PlanetSystem> planetSystemerFraJSONFil = readFromJSONFile(filnavn);

        planetSystems.addAll(planetSystemerFraJSONFil);

        // deletePlanet("Solar System", "Mars", filnavn);
        /* createPlanet("Solar System",
         "Mars",
         6.39E23,
         3389.5,
         1.524,
         0.093,
         687,
         "https://upload.wikimedia.org/wikipedia/commons/thumb/5/58/Mars_23_aug_2003_hubble.jpg/480px-Mars_23_aug_2003_hubble.jpg",
         filnavn);
         */

    }

    /*public UniverseJSONRepository(File file) {

    }*/

    private ArrayList<PlanetSystem> readFromJSONFile(String filnavn) {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayList<PlanetSystem> planetSystemsReadFromFile = new ArrayList<>();

        try {
            PlanetSystem[] planetSystemArray = objectMapper.readValue(new File(filnavn), PlanetSystem[].class);

            planetSystemsReadFromFile.addAll(Arrays.asList(planetSystemArray));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return planetSystemsReadFromFile;
    }

    public static void writeToJSONFile(String filnavn, ArrayList<PlanetSystem> planetSystems) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filnavn), planetSystems);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ArrayList<PlanetSystem> getAllPlanetSystem() {
        if (planetSystems != null) {
            return planetSystems;
        }
        return new ArrayList<>();
    }

    @Override
    public PlanetSystem getPlanetSystem(String planetSystemName) {

        for (PlanetSystem planetsystem: planetSystems) {
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

        return new ArrayList<>();
    }

    @Override
    public Planet getPlanet(String planetSystemName, String planetName) {
        return getPlanetSystem(planetSystemName).getOnePlanet(planetName);
    }

    @Override
    public void createPlanet(String planetSystemName, String planetName, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl, String filkilde) {

        for (PlanetSystem planetSystem : planetSystems) {
            for (int i = 0; i < planetSystem.getPlanets().size(); i++) {
                if (planetSystem.getName().equals(planetSystemName) && !planetSystem.getPlanets().get(i).getName().equals(planetName)) {

                    Star centralStar = planetSystem.getCenterStar();
                    Moon moon = new Moon();
                    ArrayList<Moon> moons = new ArrayList<>();
                    moons.add(moon);

                    Planet newPlanet = new Planet(planetName, mass, radius, semiMajorAxis, eccentricity, orbitalPeriod, centralStar, pictureUrl, null);
                    ArrayList<Planet> planets = planetSystem.getPlanets();
                    planets.add(newPlanet);

                    writeToJSONFile(filkilde, planetSystems);
                    break;

                } else if (planetSystem.getPlanets().get(i).getName().equals(planetName)) {
                    System.out.println("Planet does exist");
                }
            }
        }

    }

    @Override
    public void updatePlanet(String planetSystemNavn, String oldPlanetName, String planetName, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl, String filkilde) {

        deletePlanet(planetSystemNavn, planetName, filkilde);
        createPlanet(planetSystemNavn, planetName, mass, radius, semiMajorAxis, eccentricity, orbitalPeriod, pictureUrl, filkilde);

        // dette fungerer også, og er mye mer kode.
        /*for (PlanetSystem planetSystem : planetSystems) {
            for (int i = 0; i < planetSystem.getPlanets().size(); i++) {
                if (!planetSystem.getName().equals(planetSystemNavn) && planetSystem.getPlanets().get(i) == null) {
                    System.out.println("Planet does not exist");
                } else if (planetSystem.getPlanets().get(i).getName().equals(oldPlanetName)) {

                    Planet planetToUpdate = planetSystem.getPlanets().get(i);

                    planetToUpdate.setName(planetName);
                    planetToUpdate.setMass(mass);
                    planetToUpdate.setRadius(radius);
                    planetToUpdate.setSemiMajorAxis(semiMajorAxis);
                    planetToUpdate.setEccentricity(eccentricity);
                    planetToUpdate.setOrbitalPeriod(orbitalPeriod);
                    planetToUpdate.setPictureUrl(pictureUrl);

                    writeToJSONFile(filkilde, planetSystems);
                    break;

                }
            }
        }*/

    }

    @Override
    public void deletePlanet(String planetSystemNavn, String planetNavn, String filkilde) {

        for (PlanetSystem planetSystem : planetSystems) {
            for (int i = 0; i < planetSystem.getPlanets().size(); i++) {
                if (!planetSystem.getName().equals(planetSystemNavn) && planetSystem.getPlanets().get(i) == null) {
                    System.out.println("Planet does not exist");
                } else if (planetSystem.getPlanets().get(i).getName().equals(planetNavn)) {

                    ArrayList<Planet> planets = planetSystem.getPlanets();
                    planets.remove(planetSystem.getPlanets().get(i));

                    writeToJSONFile(filkilde, planetSystems);
                    break;

                }
            }
        }

    }

    @Override
    public void createPlanetSystem(String planetSystemName, String starName, double starMass, double starRadius, int starEffectiveTemperature, String starPictureUrl, String pictureUrl, String filkilde) {

        for (int i = 0; i < planetSystems.size(); i++) {
            if (planetSystems.get(i).getName().equals(planetSystemName)) {
                System.out.println("Planet system already exist");
            } else {
                System.out.println("Trying to create");

                Star centerStar = new Star(starName, starRadius, starMass, starEffectiveTemperature, starPictureUrl);
                PlanetSystem newlyCreatedPlanetSystem = new PlanetSystem(planetSystemName, centerStar, new ArrayList<>(), pictureUrl);
                planetSystems.add(newlyCreatedPlanetSystem);
                writeToJSONFile(filkilde, planetSystems);
                break;
            }
        }
    }

    @Override
    public void deletePlanetSystem(String planetSystemName, String filkilde) {

        for (int i = 0; i < planetSystems.size(); i++) {
            if (planetSystems.get(i).getName().equals(planetSystemName)) {
                planetSystems.remove(i);
                writeToJSONFile(filkilde, planetSystems);
                break;
            } else {
                System.out.println("Can't delete a planetsystem that does not exist");
            }
        }

    }

    @Override
    public Moon getOneMoon(String planetSystemName, String planetName, String moonName) {
        ArrayList<Moon> allMoons = getPlanet(planetSystemName, planetName).getMoon();
        for (int i = 0; i < allMoons.size(); i++) {
            if (allMoons.get(i).getName().equals(moonName)) {
                return allMoons.get(i);
            }
        }
        return null;
    }

    @Override
    public ArrayList<Moon> getAllMoons(String planetSystemName, String planetName) {
        ArrayList<Moon> allMoons = getPlanet(planetSystemName, planetName).getMoon();

        if (allMoons != null) {
            return allMoons;
        }

        return new ArrayList<Moon>();
    }
<<<<<<< Updated upstream
=======

    @Override
    public void createMoon(String planetSystemName, String planetName, String moonName, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl, String filkilde) {

        for (PlanetSystem planetSystem : planetSystems) {

            for (int i = 0; i < planetSystem.getPlanets().size(); i++) {

                if (planetSystem.getName().equals(planetSystemName) && planetSystem.getPlanets().get(i).getName().equals(planetName)) {

                    if (planetSystem.getPlanets().get(i).getMoon() == null) {
                        Moon moon = new Moon(moonName, radius, mass, semiMajorAxis, eccentricity, orbitalPeriod);
                        ArrayList<Moon> moons = new ArrayList<>(); // planetSystem.getPlanets().get(i).getMoon();
                        moons.add(moon);
                        planetSystem.getPlanets().get(i).setMoon(moons);
                        writeToJSONFile(filkilde, planetSystems);
                        break;
                    } else {
                        for (int j = 0; j < planetSystem.getPlanets().get(i).getMoon().size(); j++) {

                            if ( !planetSystem.getPlanets().get(i).getMoon().get(j).equals(moonName)) {
                                Moon moon = new Moon(moonName, radius, mass, semiMajorAxis, eccentricity, orbitalPeriod);
                                ArrayList<Moon> moons = planetSystem.getPlanets().get(i).getMoon();
                                moons.add(moon);
                                writeToJSONFile(filkilde, planetSystems);
                                break;
                            } else {
                                System.out.println("Moon exists");
                            }
                        }
                    }

                }
            }
        }
    }

    @Override
    public void deleteMoon(String planetSystemName, String planetName, String moonName, String filkilde) {

        for (PlanetSystem planetSystem : planetSystems) {
            if (planetSystem.getName().equals(planetSystemName)) {
                System.out.println("PLANETNAME");
                Planet planet = planetSystem.getOnePlanet(planetName);
                ArrayList<Moon> moons = planet.getMoon();

                for (int i = 0; i < moons.size(); i++) {
                    if (moons.get(i).getName().equals(moonName)) {
                        moons.remove(i);
                        writeToJSONFile(filkilde, planetSystems);
                        break;
                    }
                }

            }
        }

    }
>>>>>>> Stashed changes
}
