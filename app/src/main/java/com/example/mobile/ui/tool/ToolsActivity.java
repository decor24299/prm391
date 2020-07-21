package com.example.mobile.ui.tool;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile.R;
import com.example.mobile.dataset.Entity;
import com.example.mobile.dataset.Tool;
import com.example.mobile.ui.common.CustomArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class ToolsActivity extends AppCompatActivity {

    private ListView listView;
    private static final String resource = "tools";
    private List<Entity> toolList = new ArrayList<>();
    private CustomArrayAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);

        this.listView = findViewById(R.id.listViewTools);

        listViewAdapter = new CustomArrayAdapter(this, toolList, Tool.class, resource, RegisterToolActivity.class);
        listView.setAdapter(listViewAdapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        listViewAdapter.load();
    }

    public void insert(View view) {
        Intent intent = new Intent(this, RegisterToolActivity.class);

        this.startActivity(intent);
    }
}