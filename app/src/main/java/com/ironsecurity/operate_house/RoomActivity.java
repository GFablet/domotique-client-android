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

    int[] lumieresID = {R.id.checkBoxLampe1,R.id.checkBoxLampe2,R.id.checkBoxLampe3,
                        R.id.checkBoxLampe4,R.id.checkBoxLampe5,R.id.checkBoxLampe6,
                        R.id.checkBoxLampe7, R.id.checkBoxLampe8};
    int[] voletsID = {R.id.checkBoxVolet1,R.id.checkBoxVolet2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salon_layout);


        for(int lumiere : lumieresID)
        {
            CheckBox checkBox = findViewById(lumiere);
            //if(MainActivity.clientThread)

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked)
                    {
                        MainActivity.clientThread.sendMessage("operateOneEquipment/"+ buttonView.getTag() +"/1");
                        Toast.makeText(RoomActivity.this, buttonView.getTag() + " allumée", Toast.LENGTH_SHORT).show();
                    } else
                        MainActivity.clientThread.sendMessage("operateOneEquipment/"+ buttonView.getTag() +"/0");
                        Toast.makeText(RoomActivity.this, buttonView.getTag() + " éteinte", Toast.LENGTH_SHORT).show();
                }
            });
        }

        /*
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
        });*/

    }
}
