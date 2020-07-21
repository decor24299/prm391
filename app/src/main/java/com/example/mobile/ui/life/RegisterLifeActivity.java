package com.example.mobile.ui.life;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.example.mobile.R;
import com.example.mobile.common.network.Store;
import com.example.mobile.dataset.Life;
import com.google.gson.Gson;

import org.json.JSONObject;

public class RegisterLifeActivity extends AppCompatActivity {

    private static final int MODE_CREATE = 1;
    private static final int MODE_EDIT = 2;

    private EditText edtName;
    private EditText edtDescription;
    private EditText edtFilmingLocation;
    private EditText edtTimeStart;
    private EditText edtTimeEnd;
    private EditText edtNumberOfFilming;

    private Store module;
    private int id;
    private static final String resource = "lives";
    private int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_life);

        module = new Store(this, resource);

        this.edtName = this.findViewById(R.id.edtName);
        this.edtDescription = this.findViewById(R.id.edtDescription);
        this.edtFilmingLocation = this.findViewById(R.id.edtFilmingLocation);
        this.edtTimeStart = this.findViewById(R.id.edtTimeStart);
        this.edtTimeEnd = this.findViewById(R.id.edtTimeEnd);
        this.edtNumberOfFilming = this.findViewById(R.id.edtNumberOfFilming);

        Button btnSave = findViewById(R.id.btnSave);
        Button btnCancel = findViewById(R.id.btnCancel);

        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                btnSaveClicked();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                btnCancelClicked();
            }
        });

        Intent intent = this.getIntent();
        id = intent.getIntExtra("id", -1);
        if(id == -1)  {
            this.mode = MODE_CREATE;
        } else  {
            this.mode = MODE_EDIT;
            this.module.findOne(id, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Gson gson = new Gson();

                    Life life = gson.fromJson(response.toString(), Life.class);
                    edtName.setText(life.getName());
                    edtDescription.setText(life.getDescription());
                    edtFilmingLocation.setText(life.getFilmingLocation());
                    edtTimeStart.setText(life.getTimeStart());
                    edtTimeEnd.setText(life.getTimeEnd());
                    edtNumberOfFilming.setText(Integer.toString(life.getNumberOfFilming()));
                }
            }, null);
        }
    }

    public void btnSaveClicked()  {
        try {
            String name = this.edtName.getText().toString();
            String description = this.edtDescription.getText().toString();
            String filmingLocation = this.edtFilmingLocation.getText().toString();
            String timeStart = this.edtTimeStart.getText().toString();
            String timeEnd = this.edtTimeEnd.getText().toString();
            int numberOfFilming = Integer.parseInt(this.edtNumberOfFilming.getText().toString());

            Life life = new Life(name, description, filmingLocation, timeStart, timeEnd, numberOfFilming);
            String msg = life.validate();

            if (msg != null) {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                return;
            }

            if(mode == MODE_CREATE) {
                module.insert(life, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        onBackPressed();
                    }
                }, null);
            } else {
                module.update(id, life, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        onBackPressed();
                    }
                }, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnCancelClicked()  {
        this.onBackPressed();
    }

    @Override
    public void finish() {
        Intent data = new Intent();

        this.setResult(Activity.RESULT_OK, data);
        super.finish();
    }

}