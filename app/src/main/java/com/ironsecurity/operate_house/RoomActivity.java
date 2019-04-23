package com.ironsecurity.operate_house;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.ironsecurity.R;
import com.ironsecurity.net.CClient;

public class RoomActivity extends AppCompatActivity {

    //TODO Récupérer l'état de la clim dans la BDD
    //Mode chaud/froid de la clim(chaud = 1, froid = 0)
    static String AC_MODE = "1";

    int[] lumieresID;
    int[] voletsID;
    int clim;
    String roomID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        lumieresID = intent.getIntArrayExtra("idLumieres");
        voletsID = intent.getIntArrayExtra("idVolets");
        clim = intent.getIntExtra("idClim", 0);
        int layout =  intent.getIntExtra("idLayout", R.layout.work_in_progress);
        setContentView(layout);
        //TODO Pour tests, à enlever
        if(layout == R.layout.chambre_layout)
            roomID = "9";
        else if(layout == R.layout.salon_layout)
            roomID = "23";


        if(layout != R.layout.work_in_progress)
        {
            //On lie chaque id de lumière à un listener
            for(int lumiere : lumieresID)
            {
                CheckBox checkBox = findViewById(lumiere);
                checkBox.setChecked(RoomSelectionActivity.LIGHTS_ON);
                operateEquipment(checkBox);
            }

            //On lie chaque id de volet à un listener
            for(int volet : voletsID)
            {
                CheckBox checkBox = findViewById(volet);
                checkBox.setChecked(RoomSelectionActivity.SHUTTERS_CLOSED);
                operateEquipment(checkBox);

            }

            final CheckBox checkBox = findViewById(clim);
            //TODO trouver une autre façon de changer d'icône
            if(AC_MODE.equals("0"))
                checkBox.setButtonDrawable(R.drawable.clim_froid);
            else
                checkBox.setButtonDrawable(R.drawable.clim_chaud);
            checkBox.setChecked(RoomSelectionActivity.AC_ON);

            operateAC(checkBox);
        }


    }

    /**
     * Méthode d'envoie de la commande de l'équipement au serveur
     * @param checkBox
     */
    public void operateEquipment(CheckBox checkBox)
    {

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    new CClient().execute("operateOneEquipment/"+ buttonView.getTag() +"/1");
                    //MainActivity.clientThread.sendMessage("operateOneEquipment/"+ buttonView.getTag() +"/1");
                    //Toast.makeText(RoomActivity.this, buttonView.getTag() + " allumé(e)", Toast.LENGTH_SHORT).show();
                } else
                    new CClient().execute("operateOneEquipment/"+ buttonView.getTag() +"/0");
                    //MainActivity.clientThread.sendMessage("operateOneEquipment/"+ buttonView.getTag() +"/0");
                    //Toast.makeText(RoomActivity.this, buttonView.getTag() + " éteint(e)", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Méthode d'envoie de la commande de la climatisation au serveur
     * @param checkBox
     */
     public void operateAC(final CheckBox checkBox)
    {

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    //TODO trouver une autre façon de changer d'icône
                    if(AC_MODE.equals("0"))
                        checkBox.setButtonDrawable(R.drawable.clim_froid);
                    else
                        checkBox.setButtonDrawable(R.drawable.clim_chaud);
                    new CClient().execute("operateOneAC/"+ buttonView.getTag() +"/1/" + AC_MODE);

                } else
                {
                    checkBox.setButtonDrawable(R.drawable.clim_eteinte);
                    new CClient().execute("operateOneAC/"+ buttonView.getTag() +"/0/" + AC_MODE);
                }
            }
        });
    }


    /**
     * Méthode de pilotage des lumières de la pièce
     * @param string
     */
    private void operateAllLightsFromRoom(String string){
        Toast.makeText(this,"Lumières " + string,Toast.LENGTH_LONG).show();
    }

    /**
     * Méthode de pilotage des volets de la pièce
     * @param string
     */
    private void operateAllShuttersFromRoom(String string){
        Toast.makeText(this,"Volets " + string,Toast.LENGTH_LONG).show();
    }


    /**
     * Menu pour allumer/éteindre les équipements d'une pièce
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menu_room, menu);
        return true;
    }

    //gère le click sur une action de l'ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_lights_floor_on:

                new CClient().execute("operateEquipmentsFromRoom/0/1/"+ roomID);
                operateAllLightsFromRoom("allumées");
                setCheckBoxChecked(lumieresID,true);
                return true;
            case R.id.action_lights_floor_off:
                new CClient().execute("operateEquipmentsFromRoom/0/0/"+ roomID);
                operateAllLightsFromRoom("éteintes");
                setCheckBoxChecked(lumieresID,false);
                return true;
            case R.id.action_shutters_floor_on:
                new CClient().execute("operateEquipmentsFromRoom/3/0/"+ roomID);
                operateAllShuttersFromRoom("ouverts");
                setCheckBoxChecked(voletsID,true);
                return true;
            case R.id.action_shutters_floor_off:
                new CClient().execute("operateEquipmentsFromRoom/3/1/"+ roomID);
                operateAllShuttersFromRoom("fermés");
                setCheckBoxChecked(voletsID,false);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //TODO Pour test, état à récupérer dans la BDD
    public void setCheckBoxChecked(int[] checkBoxID, boolean checked)
    {
        for(int id : checkBoxID)
        {
            CheckBox checkBox = findViewById(id);
            checkBox.setChecked(checked);
        }
    }
}
