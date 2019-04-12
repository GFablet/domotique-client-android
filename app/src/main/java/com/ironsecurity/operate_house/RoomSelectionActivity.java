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

    static boolean LIGHTS_ON;
    static boolean SHUTTERS_CLOSED;
    static boolean AC_ON;
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

        LIGHTS_ON = false;
        SHUTTERS_CLOSED = false;
        AC_ON = false;

        final Intent intentToRoom = new Intent(RoomSelectionActivity.this, RoomActivity.class);

        //TODO Adapter cette partie pour tous les layouts
        if(layoutID == R.layout.activity_rdc)
        {
            Button buttonSalon = findViewById(R.id.buttonSalon);
            Button buttonChambre2 = findViewById(R.id.buttonChambre2);

            buttonSalon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intentToRoom.putExtra("idLumieres", lumieresSalonID);
                    intentToRoom.putExtra("idVolets", voletsSalonID);
                    intentToRoom.putExtra("idClim", climSalon);
                    intentToRoom.putExtra("idLayout", R.layout.salon_layout);
                    startActivity(intentToRoom);
                }
            });
            buttonChambre2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intentToRoom.putExtra("idLumieres", lumieresChambre2ID);
                    intentToRoom.putExtra("idVolets", voletsChambre2ID);
                    intentToRoom.putExtra("idClim", climChambre2);
                    intentToRoom.putExtra("idLayout", R.layout.chambre_layout);
                    startActivity(intentToRoom);
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

    /**
     * Actions exécutées lors d'un clique sur un item du menu
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_lights_floor_on:
                new CClient().execute("operateEquipmentsFromFloor/0/1/"+ FloorSelectionActivity.RDC_ID);
                operateAllLightsFromFloor("allumées");
                LIGHTS_ON = true;
                return true;
            case R.id.action_lights_floor_off:
                new CClient().execute("operateEquipmentsFromFloor/0/0/"+ FloorSelectionActivity.RDC_ID);
                operateAllLightsFromFloor("éteintes");
                LIGHTS_ON = false;
                return true;
            case R.id.action_shutters_floor_on:
                new CClient().execute("operateEquipmentsFromFloor/3/0/"+ FloorSelectionActivity.RDC_ID);
                operateAllShuttersFromFloor("ouverts");
                SHUTTERS_CLOSED = false;
                return true;
            case R.id.action_shutters_floor_off:
                new CClient().execute("operateEquipmentsFromFloor/3/1/"+ FloorSelectionActivity.RDC_ID);
                operateAllShuttersFromFloor("fermés");
                SHUTTERS_CLOSED = true;
                return true;
            case R.id.action_ac_floor_on:
                new CClient().execute("operateACFromFloor/1/"+ RoomActivity.AC_MODE + "/" + FloorSelectionActivity.RDC_ID);
                operateAllACFromFloor("allumées");
                AC_ON = true;
                return true;
            case R.id.action_ac_floor_off:
                new CClient().execute("operateACFromFloor/0/"+ RoomActivity.AC_MODE + "/" + FloorSelectionActivity.RDC_ID);
                operateAllACFromFloor("éteintes");
                AC_ON = false;
                return true;
            case R.id.action_ac_floor_winter:
                RoomActivity.AC_MODE = "1";
                if(AC_ON)
                    new CClient().execute("operateACFromFloor/1/"+ RoomActivity.AC_MODE + "/" + FloorSelectionActivity.RDC_ID);
                else
                    new CClient().execute("operateACFromFloor/1/"+ RoomActivity.AC_MODE + "/" + FloorSelectionActivity.RDC_ID);
                operateAllACFromFloor("en mode hiver");
                return true;
            case R.id.action_ac_floor_summer:
                RoomActivity.AC_MODE = "0";
                if(AC_ON)
                    new CClient().execute("operateACFromFloor/1/"+ RoomActivity.AC_MODE + "/" + FloorSelectionActivity.RDC_ID);
                else
                    new CClient().execute("operateACFromFloor/1/"+ RoomActivity.AC_MODE + "/" + FloorSelectionActivity.RDC_ID);
                operateAllACFromFloor("en mode été");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
