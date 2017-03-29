package com.pub.projects.model;

import java.util.List;


public class Tasks {

    private List<Details> details;
    private int id;
    private String name;


    public Tasks(List<Details> details, Integer id, String name) {
        this.details = details;
        this.id = id;
        this.name = name;

    }

    public List<Details> getDetails() {
        return details;
    }

    public void setDetails(List<Details> details) {
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
