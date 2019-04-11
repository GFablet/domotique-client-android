package com.ironsecurity.cameras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ironsecurity.R;

public class OperateCamerasActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilotage_cameras);

        Intent intent = getIntent();
        String cameraIP = intent.getStringExtra("cameraIP");

        //camera1.loadUrl(cameraIP + "/video.cgi");
    }

    public class Camera extends AppCompatActivity {
        Button Cam1;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);



        }
    }
}
