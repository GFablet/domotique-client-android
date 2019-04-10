package com.ironsecurity.operate_house;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ironsecurity.R;

public class FloorSelectionActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonExterieur;
    Button buttonRDC;
    Button button1erEtage;
    int[] FloorsID = {R.layout.work_in_progress, R.layout.work_in_progress, R.layout.activity_rdc, R.layout.work_in_progress};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_selection);

        buttonExterieur = findViewById(R.id.buttonPilotageMaison);
        buttonRDC = findViewById(R.id.buttonScenarioDomotique);
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
