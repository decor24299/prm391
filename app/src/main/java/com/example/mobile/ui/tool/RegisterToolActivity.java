package com.example.mobile.ui.tool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.example.mobile.R;
import com.example.mobile.common.json.JsonUtils;
import com.example.mobile.common.network.Store;
import com.example.mobile.dataset.Tool;
import com.example.mobile.dataset.ToolStatus;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class RegisterToolActivity extends AppCompatActivity {

    private static final int MODE_CREATE = 1;
    private static final int MODE_EDIT = 2;

    private Spinner dropDownStatus;
    private EditText edtName;
    private EditText edtDescription;
    private EditText edtQuantity;

    private Store toolStore;
    private Store toolStatusStore;
    private int id;
    private List<ToolStatus> toolStatusList;
    private static final String toolStoreResource = "tools";
    private static final String toolStatusStoreResource = "toolstatus";
    private int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_tool);

        toolStore = new Store(this, toolStoreResource);
        toolStatusStore = new Store(this, toolStatusStoreResource);

        dropDownStatus = findViewById(R.id.dropdownStatus);
        this.edtName = this.findViewById(R.id.edtName);
        this.edtDescription = this.findViewById(R.id.edtDescription);
        this.edtQuantity = this.findViewById(R.id.edtQuantity);

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

        toolStatusStore.findAll(new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    toolStatusList = JsonUtils.convertFromJsonArray(response, ToolStatus.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ArrayAdapter<ToolStatus> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, toolStatusList);
                dropDownStatus.setAdapter(adapter);

                Intent intent = getIntent();
                id = intent.getIntExtra("id", -1);
                if(id == -1)  {
                    mode = MODE_CREATE;
                } else  {
                    mode = MODE_EDIT;
                   toolStore.findOne(id, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Gson gson = new Gson();

                            Tool tool = gson.fromJson(response.toString(), Tool.class);
                            edtName.setText(tool.getName());
                            edtDescription.setText(tool.getDescription());
                            edtQuantity.setText(Integer.toString(tool.getQuantity()));

                            for (int i = 0; i < toolStatusList.size(); i++) {
                                if (toolStatusList.get(i).getId() == tool.getStatusId()) {
                                    dropDownStatus.setSelection(i);
                                    break;
                                }
                            }
                        }
                    }, null);
                }
            }
        }, null);
    }

    public void btnSaveClicked()  {
        try {
            String name = this.edtName.getText().toString();
            String description = this.edtDescription.getText().toString();
            int quantity = Integer.parseInt(this.edtQuantity.getText().toString());
            int pos = dropDownStatus.getSelectedItemPosition();
            int statusId = toolStatusList.get(pos).getId();

            Tool tool = new Tool(name, description, quantity, statusId);
            String msg = tool.validate();

            if (msg != null) {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                return;
            }

            if(mode == MODE_CREATE) {
                toolStore.insert(tool, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        onBackPressed();
                    }
                }, null);
            } else {
                toolStore.update(id, tool, new Response.Listener<String>() {
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