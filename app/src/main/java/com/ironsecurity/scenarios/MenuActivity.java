package com.ironsecurity.scenarios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ironsecurity.R;
import com.ironsecurity.cameras.CameraActivity;
import com.ironsecurity.cameras.MosaicCamerasActivity;
import com.ironsecurity.cameras.PilotageCamerasActivity;
import com.ironsecurity.operate_house.FloorSelectionActivity;

public class MenuActivity extends AppCompatActivity {

    Button buttonMaison;
    Button buttonScenarios;
    Button buttonCameras;
    Button buttonMosaique;
    Button buttonStatistiques;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_menu);

        buttonMaison = findViewById(R.id.buttonPilotageMaison);
        buttonScenarios = findViewById(R.id.buttonScenarioDomotique);
        buttonCameras = findViewById(R.id.buttonPilotageCameras);
        buttonMosaique = findViewById(R.id.buttonMosaiqueCameras);
        buttonStatistiques = findViewById(R.id.buttonStatistiques);

        buttonMaison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, FloorSelectionActivity.class);
                startActivity(intent);
            }
        });

        buttonScenarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ScenariosActivity.class);
                startActivity(intent);
            }
        });

        buttonMosaique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MosaicCamerasActivity.class);
                startActivity(intent);
            }
        });

        buttonCameras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, CameraActivity.class);
                startActivity(intent);
            }
        });

        buttonStatistiques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ScenariosActivity.class);
                startActivity(intent);
            }
        });
    }

}
