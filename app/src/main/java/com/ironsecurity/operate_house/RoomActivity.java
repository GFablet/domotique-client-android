package com.ironsecurity.operate_house;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import com.ironsecurity.MainActivity;
import com.ironsecurity.R;
import com.ironsecurity.net.CClient;

public class RoomActivity extends AppCompatActivity {

    Button buttonLumiere1;
    Button buttonVolet1;
    Button buttonClim;
    CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salon_layout);



        checkBox = findViewById(R.id.radioButtonLampe1);
        checkBox.setChecked(true);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    Toast.makeText(RoomActivity.this, "Checked", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(RoomActivity.this, "Unchecked", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
