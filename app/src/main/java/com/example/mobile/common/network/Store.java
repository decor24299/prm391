package com.example.mobile.common.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mobile.R;
import com.example.mobile.common.json.Json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class Store {

    private RequestQueue queue;
    private String url;

    public Store(Context context, String resource) {
        queue = Volley.newRequestQueue(context);
        url = context.getResources().getString(R.string.host) + resource;
    }

    public void findAll(final Response.Listener<JSONArray> resolve, final Response.ErrorListener reject) {
        JsonArrayRequest request = new JsonArrayRequest(url, resolve, reject);

        queue.add(request);
    }

    public void findOne(int id, final Response.Listener<JSONObject> resolve, final Response.ErrorListener reject) {
        String url = this.url + "/" + id;
        JsonObjectRequest request = new JsonObjectRequest(url, null, resolve, reject);

        queue.add(request);
    }

    public void insert(final Json json, final Response.Listener<String> resolve, final Response.ErrorListener reject) throws JSONException {
        final String body = json.serialize();
        StringRequest request = new StringRequest(Request.Method.POST, url, resolve, reject) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() {
                return body.getBytes(StandardCharsets.UTF_8);
            }
        };

        queue.add(request);
    }

    public void update(int id, final Json json, final Response.Listener<String> resolve, final Response.ErrorListener reject) throws JSONException {
        String url = this.url + "/" + id;

        final String body = json.serialize();

        StringRequest request = new StringRequest(Request.Method.PUT, url, resolve, reject) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() {
                return body.getBytes(StandardCharsets.UTF_8);
            }
        };

        queue.add(request);
    }

    public void delete(int id, final Response.Listener<String> resolve, final Response.ErrorListener reject) {
        String url = this.url + "/" + id;

        StringRequest request = new StringRequest(Request.Method.DELETE, url, resolve, reject);

        queue.add(request);
    }

}
