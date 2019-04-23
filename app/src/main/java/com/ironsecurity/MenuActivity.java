package com.ironsecurity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ironsecurity.cameras.CameraActivity;
import com.ironsecurity.cameras.MosaicCamerasActivity;
import com.ironsecurity.operate_house.FloorSelectionActivity;
import com.ironsecurity.scenarios.ScenariosActivity;

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
        buttonScenarios = findViewById(R.id.buttonRdc);
        buttonCameras = findViewById(R.id.buttonPilotageCameras);
        buttonMosaique = findViewById(R.id.buttonMosaiqueCameras);
        buttonStatistiques = findViewById(R.id.buttonStatistiques);

        //On récupère le type de compte utilisateur
        Intent mainIntent = getIntent();
        //Par défaut le compte est réglé sur utilisateur restreint
        int account = mainIntent.getIntExtra("account", 3);
        //On met en place les différents intent de changement d'activité
        Intent intentMaison = new Intent(MenuActivity.this, FloorSelectionActivity.class);
        Intent intentScenarios = new Intent(MenuActivity.this, ScenariosActivity.class);
        Intent intentMosaique = new Intent(MenuActivity.this, MosaicCamerasActivity.class);
        Intent intentCamera = new Intent(MenuActivity.this, CameraActivity.class);
        Intent intentStatistiques = new Intent(MenuActivity.this, StatistiquesActivity.class);

        if(account != 3)
        {
            changeActivityOnClick(buttonMaison, intentMaison);
            changeActivityOnClick(buttonScenarios, intentScenarios);
            changeActivityOnClick(buttonCameras, intentCamera);
            changeActivityOnClick(buttonStatistiques, intentStatistiques);
        }

        changeActivityOnClick(buttonMosaique, intentMosaique);


        /*
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
        });*/
    }

    private void changeActivityOnClick(Button button, final Intent intent)
    {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }

}
