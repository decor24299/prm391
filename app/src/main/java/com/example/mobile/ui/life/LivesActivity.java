package com.example.mobile.ui.life;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile.R;
import com.example.mobile.dataset.Entity;
import com.example.mobile.dataset.Life;
import com.example.mobile.ui.common.CustomArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class LivesActivity extends AppCompatActivity {

    private ListView listView;
    private static final String resource = "lives";
    private List<Entity> lifeList = new ArrayList<>();
    private CustomArrayAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lives);

        this.listView = findViewById(R.id.listViewLives);

        listViewAdapter = new CustomArrayAdapter(this, lifeList, Life.class, resource, RegisterLifeActivity.class);
        listView.setAdapter(listViewAdapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        listViewAdapter.load();
    }

    public void insert(View view) {
        Intent intent = new Intent(this, RegisterLifeActivity.class);

        this.startActivity(intent);
    }
}