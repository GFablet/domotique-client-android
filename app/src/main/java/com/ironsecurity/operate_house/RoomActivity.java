package com.ironsecurity.operate_house;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ironsecurity.MainActivity;
import com.ironsecurity.R;
import com.ironsecurity.net.CClient;

public class RoomActivity extends AppCompatActivity {

    Button buttonLumiere1;
    Button buttonVolet1;
    Button buttonClim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salon_layout);

        buttonLumiere1 = findViewById(R.id.buttonLumiere1);
        buttonVolet1 = findViewById(R.id.buttonVolet1);

        buttonLumiere1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.clientThread.sendMessage("operateOneEquipment/"+v.getTag().toString()+"/0");
            }
        });
    }
}
