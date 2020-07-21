package com.example.mobile.dataset;

import org.json.JSONException;
import org.json.JSONObject;

public class Actor extends Entity {

    private String name;

    private String description;

    private String phone;

    private String email;

    public Actor() {

    }

    public Actor(String name, String description, String phone, String email) {
        this.name = name;
        this.description = description;
        this.phone = phone;
        this.email = email;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String validate() {
        if (name.isEmpty()) {
            return "Name must be not empty!";
        }

        if (phone.isEmpty()) {
            return "Phone must be not empty!";
        }

        if (email.isEmpty()) {
            return "Email must be not empty!";
        }

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
        jo.put("phone", phone);
        jo.put("email", email);

        return jo.toString();
    }
}
