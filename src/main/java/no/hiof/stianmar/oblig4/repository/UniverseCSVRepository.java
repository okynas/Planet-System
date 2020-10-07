package no.hiof.stianmar.oblig4.repository;

import no.hiof.stianmar.oblig4.model.*;

import javax.xml.stream.events.StartDocument;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UniverseCSVRepository implements IUniverseRepository {

    private HashMap<String, PlanetSystem> planetSystemHashMap = new HashMap<>();

    public UniverseCSVRepository(String filnavn) {

        HashMap<String, PlanetSystem> lestePlanetSystemer = readFromCSVFile(new File(filnavn));

        planetSystemHashMap.putAll(lestePlanetSystemer);

        // deletePlanet("Solar System", "Mars", filnavn);
        // createPlanet("Solar System", "Mars",6.39E23,3389.5, 1.524, 0.093, 687, "https://upload.wikimedia.org/wikipedia/commons/thumb/5/58/Mars_23_aug_2003_hubble.jpg/480px-Mars_23_aug_2003_hubble.jpg", filnavn);
    }

    public static HashMap<String, PlanetSystem> readFromCSVFile(File filkilde) {
        HashMap<String, PlanetSystem> planetSystemHashMapFromFile = new HashMap<>();
        Star centralStar = null;
        PlanetSystem planetSystem = null;
        ArrayList<Planet> planets = new ArrayList<>();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filkilde))) {

            String linje = "";

            while ((linje = bufferedReader.readLine()) != null) {
                String[] deler = linje.split(",");

                if (planetSystemHashMapFromFile.containsKey(deler[0])) {
                    Planet planetOne = new Planet(deler[7], Double.parseDouble(deler[8]), Double.parseDouble(deler[9]), Double.parseDouble(deler[10]),Double.parseDouble(deler[11]),Double.parseDouble(deler[12]), centralStar,deler[13]);
                    planets.add(planetOne);
                } else {
                    planets = new ArrayList<>();
                    centralStar = new Star(deler[2], Double.parseDouble(deler[4]), Double.parseDouble(deler[3]), Double.parseDouble(deler[5]), deler[6]);
                    planetSystem = new PlanetSystem(deler[0], centralStar, planets, deler[1]);
                    Planet planetOne = new Planet(deler[7], Double.parseDouble(deler[8]), Double.parseDouble(deler[9]), Double.parseDouble(deler[10]), Double.parseDouble(deler[11]), Double.parseDouble(deler[12]), centralStar, deler[13]);
                    planets.add(planetOne);
                }

                planetSystem.setPlanets(planets);
                planetSystemHashMapFromFile.put(deler[0], planetSystem);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(planetSystemHashMapFromFile);

        return planetSystemHashMapFromFile;
    }

    public static void writeToCSVFile(HashMap<String, PlanetSystem> planetSystemHashMap, File file) {

        String output = "";

        for (PlanetSystem planetSystem : planetSystemHashMap.values()) {
            for (Planet planet : planetSystem.getPlanets()) {

                output += planetSystem.getName() + ","
                        + planetSystem.getPictureUrl() + ","
                        + planetSystem.getCenterStar().getName() + ","
                        + planetSystem.getCenterStar().getMass() + ","
                        + planetSystem.getCenterStar().getRadius() + ","
                        + planetSystem.getCenterStar().getEffectiveTemperature() + ","
                        + planetSystem.getCenterStar().getPictureUrl() +","
                        + planet.getName() + ","
                        + planet.getRadius() + ","
                        + planet.getMass() + ","
                        + planet.getSemiMajorAxis() + ","
                        + planet.getEccentricity() + ","
                        + planet.getOrbitalPeriod() + ","
                        + planet.getPictureUrl() + "\n";

            }
        }

        String finalOutput = output;
        Thread thread = new Thread(() -> {
            try (FileWriter fileWriter = new FileWriter(file, false)) {
                fileWriter.write(finalOutput);

                System.out.println(Thread.currentThread().getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        thread.start();




    }

    @Override
    public ArrayList<PlanetSystem> getAllPlanetSystem() {
        ArrayList<PlanetSystem> planetSystems = new ArrayList<PlanetSystem>(planetSystemHashMap.values());
        return planetSystems;
    }

    @Override
    public PlanetSystem getPlanetSystem(String planetSystemName) {
        PlanetSystem planetSystem = planetSystemHashMap.get(planetSystemName);
        return planetSystem;
    }

    @Override
    public ArrayList<Planet> getAllPlanets(String planetSystemName) {
        PlanetSystem planetSystem = planetSystemHashMap.get(planetSystemName);
        ArrayList<Planet> planets = planetSystem.getPlanets();
        return planets;
    }

    @Override
    public Planet getPlanet(String planetSystemNavn, String planetName) {
        PlanetSystem planetSystem = planetSystemHashMap.get(planetSystemNavn);
        Planet planet = planetSystem.getOnePlanet(planetName);
        return planet;
    }

    @Override
    public void createPlanet(String planetSystemName, String planetName, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl, String filkilde) {
        Planet planet = planetSystemHashMap.get(planetSystemName).getOnePlanet(planetName);

        if(planet == null) {
            // Sjekker om det finnes en planet som heter "Init Planet", den skal eksistere om det nylig er oppretter et
            // planetsystem. Og hvis den finnes skal den slettes når man legger til en planet.
            // Må lages en init planet når man lager et planetsystem fordi ellers vil det ikke skrives til .csv filen.
            // Må ha en verdi på alle plasser i .csv filen.
            Planet planetToDelete = planetSystemHashMap.get(planetSystemName).getOnePlanet("Init Planet");

            if (planetToDelete != null) {
                deletePlanet(planetSystemName, planetToDelete.getName(), filkilde);
            }

            Star centralStar = planetSystemHashMap.get(planetSystemName).getCenterStar();
            Planet newPlanet = new Planet(planetName, mass, radius, semiMajorAxis, eccentricity, orbitalPeriod, centralStar, pictureUrl);
            ArrayList<Planet> planets = planetSystemHashMap.get(planetSystemName).getPlanets();
            planets.add(newPlanet);

            writeToCSVFile(planetSystemHashMap, new File(filkilde));
        } else {
            System.out.println("Planet already excist!");
        }


    }

    @Override
    public void updatePlanet(String planetSystemNavn, String oldPlanetName, String planetName, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl, String filkilde) {
        deletePlanet(planetSystemNavn, oldPlanetName, filkilde);
        createPlanet(planetSystemNavn, planetName, mass, radius, semiMajorAxis, eccentricity, orbitalPeriod, pictureUrl, filkilde);

        // dette fungerer også, men mye mer kode.
        /*Planet planetToUpdate = planetSystemHashMap.get(planetSystemNavn).getOnePlanet(oldPlanetName);
        if (planetToUpdate == null) {
            System.out.println("Planet does not excist");
        } else {
            // ArrayList<Planet> planets = planetSystemHashMap.get(planetSystemNavn).getPlanets();
            // planets.remove(planetToUpdate);

            planetToUpdate.setName(planetName);
            planetToUpdate.setMass(mass);
            planetToUpdate.setRadius(radius);
            planetToUpdate.setSemiMajorAxis(semiMajorAxis);
            planetToUpdate.setEccentricity(eccentricity);
            planetToUpdate.setOrbitalPeriod(orbitalPeriod);
            planetToUpdate.setPictureUrl(pictureUrl);

            writeToCSVFile(planetSystemHashMap, new File(filkilde));
        }*/
    }

    @Override
    public void deletePlanet(String planetSystemNavn, String planetNavn, String filkilde) {
        Planet planetToDelete = planetSystemHashMap.get(planetSystemNavn).getOnePlanet(planetNavn);
        if (planetToDelete == null) {
            System.out.println("Planet does not excist");
        } else {
            ArrayList<Planet> planets = planetSystemHashMap.get(planetSystemNavn).getPlanets();
            planets.remove(planetToDelete);

            writeToCSVFile(planetSystemHashMap, new File(filkilde));
        }

    }

    // legger til mulighet for å lage et planetsystem
    @Override
    public void createPlanetSystem(String planetSystemName, String starName, double starMass, double starRadius, int starEffectiveTemperature, String starPictureUrl, String pictureUrl, String filkilde) {

        PlanetSystem planetSystem = planetSystemHashMap.get(planetSystemName);
        if(planetSystem == null) {

            System.out.println("Trying to create");

            Star centerStar = new Star(starName, starRadius, starMass, starEffectiveTemperature, starPictureUrl);

            Planet newPlanet = new Planet("Init Planet", 0, 0, 0, 0, 0, centerStar, "http://bit.ly/2Q2PngD");
            ArrayList<Planet> planets = new ArrayList<>();
            planets.add(newPlanet);

            PlanetSystem newlyCreatedPlanetSystem = new PlanetSystem(planetSystemName, centerStar, planets, pictureUrl);
            planetSystemHashMap.put(planetSystemName, newlyCreatedPlanetSystem);
            writeToCSVFile(planetSystemHashMap, new File(filkilde));
        } else {
            System.out.println("Planet already excist!");
        }

    }

    @Override
    public void deletePlanetSystem(String planetSystemName, String filkilde) {
        PlanetSystem planetSystem = planetSystemHashMap.get(planetSystemName);
        if (planetSystem != null) {
            System.out.println("Trying to delete");
            planetSystemHashMap.remove(planetSystem.getName());
            writeToCSVFile(planetSystemHashMap, new File(filkilde));
        }
    }

    @Override
    public Moon getOneMoon(String moonName) {
        return null;
    }

    @Override
    public ArrayList<Moon> getAllMoons(String planetSystemName) {
        return null;
    }
}
