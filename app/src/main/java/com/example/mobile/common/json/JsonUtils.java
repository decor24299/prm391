package com.example.mobile.common.json;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static List convertFromJsonArray(JSONArray jsonArray, Class type) throws JSONException {
        List actorList = new ArrayList();
        Gson gson = new Gson();

        for (int i = 0; i < jsonArray.length(); i++) {
            Object actor = gson.fromJson(jsonArray.getJSONObject(i).toString(), type);
            actorList.add(actor);
        }

        return actorList;
    }
}
