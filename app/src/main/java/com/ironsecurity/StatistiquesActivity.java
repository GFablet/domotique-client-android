package com.ironsecurity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class StatistiquesActivity extends AppCompatActivity {

    private ListView stat1;
    private ArrayAdapter aAdapter;
    private String[] catégories = {"Appareils en fonction", "Consommation électrique générale", "Consommation électrique par équipements",
            "Durée de fonctionnement des équipements", "Nombres de pannes générale", "Nombres de pannes par équipements", "Durée depuis la dernière panne",
            "Durée depuis la dernière panne par équipement", "Température ambiante par pièces", "Temps de présence dans les pièces"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistiques);

        stat1 = findViewById(R.id.stats);
        aAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, catégories);
        stat1.setAdapter(aAdapter);
    }
}
