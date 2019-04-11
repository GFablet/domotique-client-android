package com.ironsecurity.cameras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ironsecurity.R;

public class CameraActivity extends AppCompatActivity {

    Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CameraActivity.this, PilotageCamerasActivity.class);
                intent.putExtra("cameraIP", "http://admin:@172.16.3.14");
                startActivity(intent);
            }
        });

    }
}
