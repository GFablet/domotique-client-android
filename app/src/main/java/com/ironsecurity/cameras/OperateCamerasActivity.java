package com.ironsecurity.cameras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;

import com.ironsecurity.R;

public class OperateCamerasActivity extends AppCompatActivity {


    WebView webViewCamera;
    ImageButton btnUp;
    ImageButton btnDown;
    ImageButton btnLeft;
    ImageButton btnRight;
    String urlUp;
    String urlDown;
    String urlLeft;
    String urlRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilotage_cameras);

        Intent intent = getIntent();
        String cameraIP = intent.getStringExtra("cameraIP");

        webViewCamera = findViewById(R.id.webView);
        webViewCamera.loadUrl(cameraIP + "/video.cgi");

        btnUp = findViewById(R.id.btnUp);
        btnDown = findViewById(R.id.btnDown);
        btnLeft = findViewById(R.id.btnLeft);
        btnRight = findViewById(R.id.btnRight);

        urlUp = cameraIP + "/pantiltcontrol.cgi?PanSingleMoveDegree=5&TiltSingleMoveDegree=5&PanTiltSingleMove=1";
        urlDown = cameraIP + "/pantiltcontrol.cgi?PanSingleMoveDegree=5&TiltSingleMoveDegree=5&PanTiltSingleMove=7";
        urlLeft = cameraIP + "/pantiltcontrol.cgi?PanSingleMoveDegree=5&TiltSingleMoveDegree=5&PanTiltSingleMove=3";
        urlRight = cameraIP + "/pantiltcontrol.cgi?PanSingleMoveDegree=5&TiltSingleMoveDegree=5&PanTiltSingleMove=5";

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webViewCamera.loadUrl(urlUp);
            }
        });

        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //requestQueue.add(requestDown);
                webViewCamera.loadUrl(urlDown);
            }
        });

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webViewCamera.loadUrl(urlLeft);
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webViewCamera.loadUrl(urlRight);
            }
        });
    }

}