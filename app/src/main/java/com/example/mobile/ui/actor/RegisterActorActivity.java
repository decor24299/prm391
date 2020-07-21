package com.example.mobile.ui.actor;

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
import com.example.mobile.dataset.Actor;
import com.google.gson.Gson;

import org.json.JSONObject;

public class RegisterActorActivity extends AppCompatActivity {

    private static final int MODE_CREATE = 1;
    private static final int MODE_EDIT = 2;

    private EditText edtName;
    private EditText edtDescription;
    private EditText edtPhone;
    private EditText edtEmail;

    private Store module;
    private int id;
    private static final String resource = "actors";
    private int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_actor);

        module = new Store(this, resource);

        this.edtName = this.findViewById(R.id.edtName);
        this.edtDescription = this.findViewById(R.id.edtDescription);
        this.edtPhone = this.findViewById(R.id.edtPhone);
        this.edtEmail = this.findViewById(R.id.edtEmail);

        Button btnSave = findViewById(R.id.btnSave);
        Button btnCancel =findViewById(R.id.btnCancel);

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

                    Actor actor = gson.fromJson(response.toString(), Actor.class);
                    edtName.setText(actor.getName());
                    edtDescription.setText(actor.getDescription());
                    edtPhone.setText(actor.getPhone());
                    edtEmail.setText(actor.getEmail());
                }
            }, null);
        }
    }

    public void btnSaveClicked()  {
        try {
            String name = this.edtName.getText().toString();
            String description = this.edtDescription.getText().toString();
            String phone = this.edtPhone.getText().toString();
            String email = this.edtEmail.getText().toString();

            Actor actor = new Actor(name, description, phone, email);
            String msg = actor.validate();

            if (msg != null) {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                return;
            }

            if(mode == MODE_CREATE) {
                module.insert(actor, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        onBackPressed();
                    }
                }, null);
            } else {
                module.update(id, actor, new Response.Listener<String>() {
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