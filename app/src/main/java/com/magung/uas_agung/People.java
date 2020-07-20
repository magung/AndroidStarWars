package com.magung.uas_agung;

public class People {
    String name, height, mass, birth_year, gender;

    public People(String name, String height, String mass, String birth_year, String gender) {
        this.name = name;
        this.height = height;
        this.mass = mass;
        this.birth_year = birth_year;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getHeight() {
        return height;
    }

    public String getMass() {
        return mass;
    }

    public String getBirth_year() {
        return birth_year;
    }

    public String getGender() {
        return gender;
    }
}
