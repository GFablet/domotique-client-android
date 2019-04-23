package com.ironsecurity.scenarios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.ironsecurity.R;

public class CreateScenarioActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_scenario);

        Spinner spinnerActions = findViewById(R.id.Actions);
        ArrayAdapter<CharSequence> adapterActions = ArrayAdapter.createFromResource(this, R.array.actions, android.R.layout.simple_spinner_item);
        adapterActions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerActions.setAdapter(adapterActions);
        spinnerActions.setOnItemSelectedListener(this);

        Spinner spinnerEtages = findViewById(R.id.Ext_Etages);
        ArrayAdapter<CharSequence> adapterEtages = ArrayAdapter.createFromResource(this, R.array.etages, android.R.layout.simple_spinner_item);
        adapterEtages.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEtages.setAdapter(adapterEtages);
        spinnerEtages.setOnItemSelectedListener(this);

        Spinner spinnerEquipements = findViewById(R.id.Equipements);
        ArrayAdapter<CharSequence> adapterEquipements = ArrayAdapter.createFromResource(this, R.array.equipements, android.R.layout.simple_spinner_item);
        adapterEquipements.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEquipements.setAdapter(adapterEquipements);
        spinnerEquipements.setOnItemSelectedListener(this);

        Spinner spinnerPieces = findViewById(R.id.Pieces);
        ArrayAdapter<CharSequence> adapterPieces = ArrayAdapter.createFromResource(this, R.array.pieces, android.R.layout.simple_spinner_item);
        adapterPieces.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPieces.setAdapter(adapterPieces);
        spinnerPieces.setOnItemSelectedListener(this);

        Spinner spinnerTemps = findViewById(R.id.Temps);
        ArrayAdapter<CharSequence> Temps = ArrayAdapter.createFromResource(this, R.array.temps, android.R.layout.simple_spinner_item);
        Temps.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTemps.setAdapter(Temps);
        spinnerTemps.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
