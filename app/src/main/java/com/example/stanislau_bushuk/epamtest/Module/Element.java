package com.example.stanislau_bushuk.epamtest.Module;

/**
 * Created by Stanislau_Bushuk on 3/6/2018.
 */

public class Element {

    private String id;
    private String name;
    private String description;

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Element(String name, String id, String description){
        this.id=id;
        this.name=name;
        this.description=description;
    }
}

