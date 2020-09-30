package com.haksoy.mvvmdemo.model;

public class Name {
    private String first;
    private String last;

    @Override
    public String toString() {
        return first + " " + last;
    }
}
