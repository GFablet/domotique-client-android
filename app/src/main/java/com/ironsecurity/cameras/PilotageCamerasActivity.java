package com.ironsecurity.cameras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ironsecurity.R;

public class PilotageCamerasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilotage_cameras);
    }

    public class Camera extends AppCompatActivity {
        Button Cam1;
        Button Cam2;
        Button Cam3;
        Button Cam4;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.choixcamera);

            Cam1 = (Button) findViewById(R.id.button);
            Cam2 = (Button) findViewById(R.id.button);
            Cam3 = (Button) findViewById(R.id.button);
            Cam4 = (Button) findViewById(R.id.button);

            Cam1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Intent i = new Intent( Camera.this, Camera.class);
                    startActivity(i);
                }
            });

            Cam2.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Intent i = new Intent( Camera.this, Camera.class);
                    startActivity(i);
                }
            });

            Cam3.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Intent i = new Intent( Camera.this, Camera.class);
                    startActivity(i);
                }
            });

            Cam4.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Intent i = new Intent( Camera.this, Camera.class);
                    startActivity(i);
                }
            });

        }
    }
}
