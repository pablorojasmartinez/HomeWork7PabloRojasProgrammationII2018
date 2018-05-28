/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Pablo Rojas
 */
public class Person {

    private String id;
    private String name;
    private String firstLastname;
    private String secondLastname;
    private String birthdate;
    private String country;
    private String parentId;

    public Person(String id, String name, String firstLastname, String secondLastname, String birthdate, String country, String parentId) {
        this.id = id;
        this.name = name;
        this.firstLastname = firstLastname;
        this.secondLastname = secondLastname;
        this.birthdate = birthdate;
        this.country = country;
        this.parentId = parentId;
    }

    public Person() {
        this.id = "";
        this.name = "";
        this.firstLastname = "";
        this.secondLastname = "";
        this.birthdate = "";
        this.country = "";
        this.parentId = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFistLastname() {
        return firstLastname;
    }

    public void setFirstLastname(String fistLastname) {
        this.firstLastname = fistLastname;
    }

    public String getSecondLastname() {
        return secondLastname;
    }

    public void setSecondLastname(String secondLastname) {
        this.secondLastname = secondLastname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", name=" + name + ", firstLastname=" + firstLastname + ", secondLastname=" + secondLastname + ", birthdate=" + birthdate + ", country=" + country + ", parentId=" + parentId + '}';
    }

}
