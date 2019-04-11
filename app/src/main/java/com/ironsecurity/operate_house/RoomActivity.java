package com.ironsecurity.operate_house;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import com.ironsecurity.MainActivity;
import com.ironsecurity.R;
import com.ironsecurity.net.CClient;

public class RoomActivity extends AppCompatActivity {

    //TODO Récupérer l'état de la clim dans la BDD
    //Mode chaud/froid de la clim(chaud = 1, froid = 0)
    static String AC_MODE = "1";

    int[] lumieresID;
    int[] voletsID;
    int clim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        lumieresID = intent.getIntArrayExtra("idLumieres");
        voletsID = intent.getIntArrayExtra("idVolets");
        clim = intent.getIntExtra("idClim", 0);
        int layout =  intent.getIntExtra("idLayout", R.layout.work_in_progress);
        setContentView(layout);

        if(layout != R.layout.work_in_progress)
        {
            //On lie chaque id de lumière à un listener
            for(int lumiere : lumieresID)
            {

                //if(MainActivity.clientThread)
                operateEquipment(lumiere);
            }

            //On lie chaque id de volet à un listener
            for(int volet : voletsID)
            {
                //if(MainActivity.clientThread)
                operateEquipment(volet);

            }

            operateAC(clim);
        }


    }

    /**
     * Méthode d'envoie de la commande de l'équipement au serveur
     * @param checkBoxID
     */
    public void operateEquipment(int checkBoxID)
    {
        CheckBox checkBox = findViewById(checkBoxID);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    MainActivity.clientThread.sendMessage("operateOneEquipment/"+ buttonView.getTag() +"/1");
                    //Toast.makeText(RoomActivity.this, buttonView.getTag() + " allumé(e)", Toast.LENGTH_SHORT).show();
                } else
                    MainActivity.clientThread.sendMessage("operateOneEquipment/"+ buttonView.getTag() +"/0");
                    //Toast.makeText(RoomActivity.this, buttonView.getTag() + " éteint(e)", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Méthode d'envoie de la commande de la climatisation au serveur
     * @param checkBoxID
     */
     public void operateAC(int checkBoxID)
    {
        CheckBox checkBox = findViewById(checkBoxID);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    MainActivity.clientThread.sendMessage("operateOneAC/"+ buttonView.getTag() +"/1/" + AC_MODE);
                    //Toast.makeText(RoomActivity.this, buttonView.getTag() + " allumé(e)", Toast.LENGTH_SHORT).show();
                } else
                    MainActivity.clientThread.sendMessage("operateOneAC/"+ buttonView.getTag() +"/0/" + AC_MODE);
                    //Toast.makeText(RoomActivity.this, buttonView.getTag() + " éteint(e)", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
