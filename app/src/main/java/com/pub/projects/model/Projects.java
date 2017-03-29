package com.pub.projects.model;

import java.util.List;


public class Projects {

    private List<Tasks> tasks;
    private int id;
    private String name;


    public Projects(List<Tasks> tasks, Integer id, String name) {
        this.tasks = tasks;
        this.id = id;
        this.name = name;


    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tasks> getTasks() {
        return tasks;
    }

    public void setTasks(List<Tasks> tasks) {
        this.tasks = tasks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
