package com.ironsecurity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ironsecurity.net.CClient;
import com.ironsecurity.net.CServer;

public class ConfigurationActivity extends AppCompatActivity {

    EditText editTextIP;
    EditText editTextPort;
    Button buttonValider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        editTextIP = findViewById(R.id.editTextIPServeur);
        editTextPort = findViewById(R.id.editTextPortServeur);
        buttonValider = findViewById(R.id.buttonValider);

        editTextIP.setText(CServer.ADDR_IP);
        String port = "" + CServer.PORT;
        editTextPort.setText(port);

        buttonValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CServer.ADDR_IP = editTextIP.getText().toString();
                CServer.PORT = Integer.parseInt(editTextPort.getText().toString());
                Toast.makeText(ConfigurationActivity.this, "Changements enregistrés", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
