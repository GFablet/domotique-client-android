package com.ironsecurity.operate_house;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ironsecurity.R;

public class RoomSelectionActivity extends AppCompatActivity {

    //TODO A remplacer
    int[] lumieresSalonID =  {R.id.checkBoxSalonLampe1,R.id.checkBoxSalonLampe2,R.id.checkBoxSalonLampe3,
            R.id.checkBoxSalonLampe4,R.id.checkBoxSalonLampe5,R.id.checkBoxSalonLampe6,
            R.id.checkBoxSalonLampe7, R.id.checkBoxSalonLampe8};
    int[] voletsSalonID = {R.id.checkBoxSalonVolet1,R.id.checkBoxSalonVolet2};
    int climSalon = R.id.checkBoxSalonClim;
    int[] lumieresChambre2ID = {R.id.checkBoxChambreLampe1,R.id.checkBoxChambreLampe2,R.id.checkBoxChambreLampe3,
            R.id.checkBoxChambreLampe4};
    int[] voletsChambre2ID = {R.id.checkBoxChambreVolet1,R.id.checkBoxChambreVolet2, R.id.checkBoxChambreVolet3};
    int climChambre2 = R.id.checkBoxChambreClim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int layoutID = intent.getIntExtra("layout", R.layout.work_in_progress);
        setContentView(layoutID);
        //TODO Adapter cette partie pour tous les layouts
        if(layoutID == R.layout.activity_rdc)
        {
            Button buttonSalon = findViewById(R.id.buttonSalon);
            Button buttonChambre2 = findViewById(R.id.buttonChambre2);

            buttonSalon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(RoomSelectionActivity.this, RoomActivity.class);
                    intent.putExtra("idLumieres", lumieresSalonID);
                    intent.putExtra("idVolets", voletsSalonID);
                    intent.putExtra("idClim", climSalon);
                    intent.putExtra("idLayout", R.layout.salon_layout);
                    startActivity(intent);
                }
            });
            buttonChambre2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(RoomSelectionActivity.this, RoomActivity.class);
                    intent.putExtra("idLumieres", lumieresChambre2ID);
                    intent.putExtra("idVolets", voletsChambre2ID);
                    intent.putExtra("idClim", climChambre2);
                    intent.putExtra("idLayout", R.layout.chambre_layout);
                    startActivity(intent);
                }
            });
        }

    }
}
