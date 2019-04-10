package com.ironsecurity.operate_house;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ironsecurity.R;

public class RoomSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int layoutID = intent.getIntExtra("layout", R.layout.activity_room_selection);
        setContentView(layoutID);

        Button buttonSalon = findViewById(R.id.buttonSalon);

        buttonSalon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoomSelectionActivity.this, RoomActivity.class);
                startActivity(intent);
            }
        });
    }
}
