package com.example.findmovies.retrofit;

import java.util.List;

public class ResultsFilm {
    public List<Searching> getResults() {
        return results;
    }

    public void setResults(List<Searching> results) {
        this.results = results;
    }

    private List<Searching> results;

}
