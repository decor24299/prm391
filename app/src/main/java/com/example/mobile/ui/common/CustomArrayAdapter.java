package com.example.mobile.ui.common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.mobile.R;
import com.example.mobile.common.json.JsonUtils;
import com.example.mobile.common.network.Store;
import com.example.mobile.dataset.Entity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

public class CustomArrayAdapter extends ArrayAdapter<Entity> {

    private final Context context;
    private List<Entity> entities;
    private final Class entityClass;
    private final Class editScreen;
    private Store store;

    public CustomArrayAdapter(Context context, List<Entity> entities, Class entityClass, String resource, Class editScreen) {
        super(context, R.layout.activity_test2, entities);
        this.entities = entities;
        this.context = context;
        this.entityClass = entityClass;
        this.store = new Store(context, resource);
        this.editScreen = editScreen;
        load();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.activity_test2, parent, false);

        TextView textView = rowView.findViewById(R.id.label);
        ImageView imageView = rowView.findViewById(R.id.logo);
        ImageView trashIcon = rowView.findViewById(R.id.trash_icon);
        ImageView editIcon = rowView.findViewById(R.id.edit_icon);
        ImageView cartIcon = rowView.findViewById(R.id.cart_icon);

        textView.setText(entities.get(position).toString());
        textView.setTextColor(Color.BLACK);

        imageView.setImageResource(R.drawable.img);
        trashIcon.setImageResource(R.drawable.trash);
        editIcon.setImageResource(R.drawable.edit);
        cartIcon.setImageResource(R.drawable.cart);

        trashIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete(entities.get(position));
            }
        });

        editIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit(entities.get(position));
            }
        });

        return rowView;
    }

    public void load() {
        store.findAll(new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    entities.clear();
                    entities.addAll(JsonUtils.convertFromJsonArray(response, entityClass));
                    notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, null);
    }

    private void edit(Entity entity) {
        Intent intent = new Intent(context, editScreen);
        intent.putExtra("id", entity.getId());

        context.startActivity(intent);
    }

    private void delete(final Entity entity) {
        new AlertDialog.Builder(context)
                .setMessage("Are you sure?")
                .setCancelable(false)
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        store.delete(entity.getId(), new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                load();
                            }
                        }, null);
                    }
                })
                .setPositiveButton("No", null)
                .show();
    }

}