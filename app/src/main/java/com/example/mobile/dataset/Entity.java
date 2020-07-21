package com.example.mobile.dataset;

import com.example.mobile.common.json.Json;

import java.io.Serializable;

public abstract class Entity implements Serializable, Json {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
