package com.magung.uas_agung;

public class Planets {
    String name, population, terrain, diameter, rotation_period;

    public Planets(String name, String population, String terrain, String diameter, String rotation_period) {
        this.name = name;
        this.population = population;
        this.terrain = terrain;
        this.diameter = diameter;
        this.rotation_period = rotation_period;
    }

    public String getName() {
        return name;
    }

    public String getPopulation() {
        return population;
    }

    public String getTerrain() {
        return terrain;
    }

    public String getDiameter() {
        return diameter;
    }

    public String getRotation_period() {
        return rotation_period;
    }
}
