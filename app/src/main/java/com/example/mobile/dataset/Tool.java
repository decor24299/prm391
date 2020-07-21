package com.example.mobile.dataset;

import com.example.mobile.common.json.Json;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Tool extends Entity implements Serializable, Json {

    private String name;

    private String description;

    private int quantity;

    private int statusId;

    public Tool() {

    }

    public Tool(String name, String description, int quantity, int statusId) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.statusId = statusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String validate() {
        return null;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String serialize() throws JSONException {
        JSONObject jo = new JSONObject();
        jo.put("name", name);
        jo.put("description", description);
        jo.put("quantity", quantity);
        jo.put("statusId", statusId);

        return jo.toString();
    }
}
