package com.ironsecurity.scenarios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ironsecurity.MainActivity;
import com.ironsecurity.R;

import java.util.HashMap;

public class ScenariosActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener, AdapterView.OnItemSelectedListener {

    private static String SCENARIO_ID;

    Spinner spinner;
    HashMap<String, String> listeScenarios;
    Button envoieScenario;
    TextView textViewScenario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenarios);

        //TODO Récupérer les scénarios de la BDD
        listeScenarios = new HashMap<>();
        listeScenarios.put("Scenario1", "allumer lumiere1 de (cuisine;rdc) pendant 10 secondes\n" +
                                        "eteindre lumiere1 de (cuisine;rdc)\n" +
                                        "allumer lumiere1 de (hall_nord;rdc) pendant 2 secondes\n" +
                                        "eteindre lumiere1 de (hall_nord;rdc)\n" +
                                        "allumer lumiere1 de (salon;rdc)\n" +
                                        "allumer lumiere2 de (salon;rdc) ...");

        textViewScenario = findViewById(R.id.textViewScenario);
        spinner = findViewById(R.id.choix);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ChoixScenar, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        envoieScenario = findViewById(R.id.buttonEnvoieScenario);
        envoieScenario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!SCENARIO_ID.equals(null))
                {
                    MainActivity.clientThread.sendMessage("executeScenarioByID/"+SCENARIO_ID);
                }
            }
        });


    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.menu_menu);
        popup.show();
    }

    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Intent i = new Intent(this, CreateScenarioActivity.class);
                startActivity(i);
                return true;
            case R.id.action_delete:

                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        SCENARIO_ID = parent.getItemAtPosition(position).toString().replaceAll("[a-zA-Z]","");
        //Toast.makeText(this, SCENARIO_ID, Toast.LENGTH_SHORT).show();
        textViewScenario.setText(listeScenarios.get(parent.getItemAtPosition(position).toString()));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}