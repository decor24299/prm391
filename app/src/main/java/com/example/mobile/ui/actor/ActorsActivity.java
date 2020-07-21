package com.example.mobile.ui.actor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile.R;
import com.example.mobile.dataset.Actor;
import com.example.mobile.dataset.Entity;
import com.example.mobile.ui.common.CustomArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class ActorsActivity extends AppCompatActivity {

    private ListView listView;
    private static final String resource = "actors";
    private List<Entity> actorList = new ArrayList<>();
    private CustomArrayAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actors);

        this.listView = findViewById(R.id.listViewActors);

        listViewAdapter = new CustomArrayAdapter(this, actorList, Actor.class, resource, RegisterActorActivity.class);
        listView.setAdapter(listViewAdapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        listViewAdapter.load();
    }

    public void insert(View view) {
        Intent intent = new Intent(this, RegisterActorActivity.class);

        this.startActivity(intent);
    }
}