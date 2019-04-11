package com.ironsecurity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
        ImageButton buttonOption;
        ImageButton buttonSupport;

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

            buttonOption = findViewById(R.id.Option);
            buttonSupport = findViewById(R.id.buttonSupport);

            loginButton = findViewById(R.id.buttonLogin);
            editTextUsername = findViewById(R.id.editTextUsername);
            editTextPassword = findViewById(R.id.editTextPassword);
            switchRememberMe = findViewById(R.id.switchRemember);

            buttonSupport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            buttonOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, ConfigurationActivity.class);
                    startActivity(intent);
                }
            });

            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    run();
                }
            });
        }

            public void run() {

                if(editTextUsername.getText().length()==0)
                {

                    ShowAlert.displayAlert(this,"Nom d'utilisateur incorrect");
                    editTextUsername.requestFocus();
                    return;
                }

                if(editTextPassword.getText().length()==0)
                {
                    ShowAlert.displayAlert(this,"Mot de passe incorrect");
                    editTextPassword.requestFocus();
                    return;
                }

                String username= editTextUsername.getText().toString();
                String password= editTextPassword.getText().toString();
                final DatabaseHelper dbHelper = new DatabaseHelper(this);
                dbHelper.addUser(new User("Admin", "admin"));
                dbHelper.addUser(new User("Utilisateur", "motdepasse"));
                dbHelper.addUser(new User("Surveillance", "motdepasse"));


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
                    //TODO Remplacer par une variable de compte utilisateur
                    i.putExtra("account", user.getId());
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

    @Override
    protected void onStart() {
        super.onStart();
        //Création du client qui se chargera d'envoyer les messages au serveur java
        if(clientThread == null) {
            clientThread = new CClient();
            thread = new Thread(clientThread);
            thread.start();
        }
    }
}
