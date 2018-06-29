package com.projects.rodrixan.playerlist.model;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerResponse implements Serializable {

    @SerializedName("players")
    private List<Player> players = null;
    @SerializedName("type")
    private String type;
    @SerializedName("title")
    private String title;

    /**
     * No args constructor for use in serialization
     */
    public PlayerResponse() {
    }

    /**
     * @param title
     * @param players
     * @param type
     */
    public PlayerResponse(List<Player> players, String type, String title) {
        super();
        this.players = players;
        this.type = type;
        this.title = title;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public PlayerResponse withPlayers(List<Player> players) {
        this.players = players;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PlayerResponse withType(String type) {
        this.type = type;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PlayerResponse withTitle(String title) {
        this.title = title;
        return this;
    }

}
