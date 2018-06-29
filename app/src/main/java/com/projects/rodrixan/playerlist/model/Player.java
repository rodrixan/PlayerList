package com.projects.rodrixan.playerlist.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Player implements Serializable {

    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("surname")
    @Expose
    private String surname;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("date")
    @Expose
    private String date;

    /**
     * No args constructor for use in serialization
     */
    public Player() {
    }

    /**
     * @param name
     * @param surname
     * @param image
     * @param date
     */
    public Player(String image, String surname, String name, String date) {
        super();
        this.image = image;
        this.surname = surname;
        this.name = name;
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Player withImage(String image) {
        this.image = image;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Player withSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player withName(String name) {
        this.name = name;
        return this;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Player withDate(String date) {
        this.date = date;
        return this;
    }

}
