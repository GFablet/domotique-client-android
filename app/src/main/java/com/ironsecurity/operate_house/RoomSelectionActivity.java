package com.ironsecurity.operate_house;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ironsecurity.R;
import com.ironsecurity.cameras.OperateCamerasActivity;
import com.ironsecurity.net.CClient;

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

    /**
     * Méthode de pilotage des lumières de l'étage
     * @param string
     */
    private void operateAllLightsFromFloor(String string){
        Toast.makeText(this,"Lumières " + string,Toast.LENGTH_LONG).show();
    }

    /**
     * Méthode de pilotage des volets de l'étage
     * @param string
     */
    private void operateAllShuttersFromFloor(String string){
        Toast.makeText(this,"Volets " + string,Toast.LENGTH_LONG).show();
    }

    /**
     * Méthode de pilotage des climatisations de l'étage
     * @param string
     */
    private void operateAllACFromFloor(String string){
        Toast.makeText(this,"Climatisations " + string,Toast.LENGTH_LONG).show();
    }

    /**
     * Menu pour allumer/éteindre les équipements d'un étage
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menu_floor, menu);
        return true;
    }

    //gère le click sur une action de l'ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_lights_floor_on:
                new CClient().execute("operateEquipmentsFromFloor/0/1/"+ FloorSelectionActivity.RDC_ID);
                operateAllLightsFromFloor("allumées");
                return true;
            case R.id.action_lights_floor_off:
                new CClient().execute("operateEquipmentsFromFloor/0/0/"+ FloorSelectionActivity.RDC_ID);
                operateAllLightsFromFloor("éteintes");
                return true;
            case R.id.action_shutters_floor_on:
                new CClient().execute("operateEquipmentsFromFloor/3/0/"+ FloorSelectionActivity.RDC_ID);
                operateAllShuttersFromFloor("ouverts");
                return true;
            case R.id.action_shutters_floor_off:
                new CClient().execute("operateEquipmentsFromFloor/3/1/"+ FloorSelectionActivity.RDC_ID);
                operateAllShuttersFromFloor("fermés");
                return true;
            case R.id.action_ac_floor_on:
                new CClient().execute("operateACFromFloor/1/"+ RoomActivity.AC_MODE + "/" + FloorSelectionActivity.RDC_ID );
                operateAllACFromFloor("allumées");
                return true;
            case R.id.action_ac_floor_off:
                new CClient().execute("operateACFromFloor/0/"+ RoomActivity.AC_MODE + "/" + FloorSelectionActivity.RDC_ID );
                operateAllACFromFloor("éteintes");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
