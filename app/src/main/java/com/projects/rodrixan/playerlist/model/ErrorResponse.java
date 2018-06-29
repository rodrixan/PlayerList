package com.projects.rodrixan.playerlist.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ErrorResponse implements Serializable {

    @SerializedName("error")
    private String error;

    /**
     * No args constructor for use in serialization
     */
    public ErrorResponse() {
    }

    /**
     * @param error
     */
    public ErrorResponse(String error) {
        super();
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ErrorResponse withError(String error) {
        this.error = error;
        return this;
    }

}
