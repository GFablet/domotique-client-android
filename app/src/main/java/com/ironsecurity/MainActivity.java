package com.ironsecurity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;

import com.ironsecurity.operate_house.FloorSelectionActivity;

public class MainActivity extends AppCompatActivity{
        static final String TAG = "LoginActivity";
        EditText strUserName;
        EditText strPassword;
        Button loginButton;
        Switch savepass;

        public static final String PREFS_NAME = "MyPrefsFile";
        private static final String PREF_USERNAME = "savusername";
        private static final String PREF_PASSWORD = "savpassword";


        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            loginButton = findViewById(R.id.buttonLogin);

            loginButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            changeActivity();
                    }
            });

        }
        private void changeActivity()
        {
                Intent intent = new Intent(this, FloorSelectionActivity.class);
                startActivity(intent);
        }
}
