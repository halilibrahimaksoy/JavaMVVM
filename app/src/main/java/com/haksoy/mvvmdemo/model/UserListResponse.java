package com.haksoy.mvvmdemo.model;

import java.util.List;

public class UserListResponse {
    private List<User> results;

    public List<User> getResults() {
        return results;
    }

    public void setResults(List<User> results) {
        this.results = results;
    }
}