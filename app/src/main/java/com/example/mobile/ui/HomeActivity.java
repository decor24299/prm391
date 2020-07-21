package com.example.mobile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile.R;
import com.example.mobile.ui.actor.ActorsActivity;
import com.example.mobile.ui.life.LivesActivity;
import com.example.mobile.ui.tool.ToolsActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void goToActors(View view) {
        Intent intent = new Intent(this, ActorsActivity.class);

        startActivity(intent);
    }

    public void goToLives(View view) {
        Intent intent = new Intent(this, LivesActivity.class);

        startActivity(intent);
    }

    public void goToTools(View view) {
        Intent intent = new Intent(this, ToolsActivity.class);

        startActivity(intent);
    }
}