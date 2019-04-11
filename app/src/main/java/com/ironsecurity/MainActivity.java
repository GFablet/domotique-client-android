package com.ironsecurity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.ironsecurity.database.DatabaseHelper;
import com.ironsecurity.database.ShowAlert;
import com.ironsecurity.database.User;
import com.ironsecurity.net.CClient;

public class MainActivity extends AppCompatActivity implements Runnable {

        static final String TAG = "LoginActivity";

        Button loginButton;
        EditText editTextUsername;
        EditText editTextPassword;
        Switch switchRememberMe;

        public static final String PREFS_NAME = "MyPrefsFile";
        private static final String PREF_USERNAME = "savusername";
        private static final String PREF_PASSWORD = "savpassword";
        static public CClient clientThread;

        Thread thread;

        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            //Création du client qui se chargera d'envoyer les messages au serveur java
            clientThread = new CClient();
            thread = new Thread(clientThread);
            thread.start();

            loginButton = findViewById(R.id.buttonLogin);
            editTextUsername = findViewById(R.id.editTextUsername);
            editTextPassword = findViewById(R.id.editTextPassword);

            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeActivity();
                }
            });
        }

            public void run() {

                if(editTextUsername.getText().length()==0)
                {

                    ShowAlert.displayAlert(this,"Please enter valid Username");
                    editTextUsername.requestFocus();
                    return;
                }

                if(editTextPassword.getText().length()==0)
                {
                    ShowAlert.displayAlert(this,"Please enter valid Password");
                    editTextPassword.requestFocus();
                    return;
                }

                String username= editTextUsername.getText().toString();
                String password= editTextPassword.getText().toString();
                final DatabaseHelper dbHelper = new DatabaseHelper(this);
                dbHelper.addUser(new User("Admin", "admin"));


                if(switchRememberMe.isChecked())
                {
                    getSharedPreferences(PREFS_NAME,MODE_PRIVATE)
                            .edit()
                            .putString(PREF_USERNAME, username)
                            .putString(PREF_PASSWORD, password)

                            .commit();

                }
                User user = dbHelper.queryUser(username, password);
                if(user != null)
                {
                    Intent i = new Intent(this, MenuActivity.class); // here the nextpage to be loaded is specified
                    startActivity(i);
                }
                else
                {
                    System.out.println("inside the else");
                    ShowAlert.displayAlert(this,"Invalid Username/Password");
                    return;

                }
            }

        private void changeActivity()
        {
                Intent intent = new Intent(this, MenuActivity.class);
                startActivity(intent);
        }
}
