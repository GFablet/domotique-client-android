package com.ironsecurity.cameras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ironsecurity.R;

public class CameraActivity extends AppCompatActivity {

    static final String IP_CAMERA1 = "http://admin:@172.16.3.14";
    static final String IP_CAMERA2 = "http://admin:@172.16.3.14";
    static final String IP_CAMERA3 = "http://admin:@172.16.3.14";
    static final String IP_CAMERA4 = "http://admin:@172.16.3.14";

    Button button1;
    Button button2;
    Button button3;
    Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        button1 = findViewById(R.id.buttonCamera1);
        button2 = findViewById(R.id.buttonCamera2);
        button3 = findViewById(R.id.buttonCamera3);
        button4 = findViewById(R.id.buttonCamera4);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CameraActivity.this, OperateCamerasActivity.class);
                intent.putExtra("cameraIP", IP_CAMERA1);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CameraActivity.this, OperateCamerasActivity.class);
                intent.putExtra("cameraIP", IP_CAMERA2);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CameraActivity.this, OperateCamerasActivity.class);
                intent.putExtra("cameraIP", IP_CAMERA3);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CameraActivity.this, OperateCamerasActivity.class);
                intent.putExtra("cameraIP", IP_CAMERA4);
                startActivity(intent);
            }
        });

    }
}
