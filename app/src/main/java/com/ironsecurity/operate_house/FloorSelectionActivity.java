package com.ironsecurity.operate_house;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.ironsecurity.R;

public class FloorSelectionActivity extends AppCompatActivity implements View.OnClickListener {

    //TODO A récupérer de la BDD
    static final String EXTERIEUR_ID = "1";
    static final String RDC_ID = "2";
    static final String PREMIER_ID = "3";

    Button buttonExterieur;
    Button buttonRDC;
    Button button1erEtage;
    int[] FloorsID = {R.layout.work_in_progress, R.layout.work_in_progress, R.layout.activity_rdc, R.layout.work_in_progress};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_selection);

        buttonExterieur = findViewById(R.id.buttonExterieur);
        buttonRDC = findViewById(R.id.buttonRdc);
        button1erEtage = findViewById(R.id.button1erEtage);

        buttonExterieur.setOnClickListener(this);
        buttonRDC.setOnClickListener(this);
        button1erEtage.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, RoomSelectionActivity.class);
        System.out.println(v.getTag().toString());
        intent.putExtra("layout", FloorsID[Integer.parseInt(v.getTag().toString())]);
        startActivity(intent);
    }


}
