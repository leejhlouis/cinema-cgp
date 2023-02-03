package com.cinemacgp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Response {
    private ArrayList<Movie> results;

    public ArrayList<Movie> getMovies() {
        return results;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.results = movies;
    }
}
