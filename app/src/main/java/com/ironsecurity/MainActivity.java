package com.ironsecurity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public class MainActivity extends AppCompatActivity implements Runnable{
        static final String TAG = "LoginActivity";
        EditText strUserName;
        EditText strPassword;
        ImageButton loginButton;
        Switch savepass;

        public static final String PREFS_NAME = "MyPrefsFile";
        private static final String PREF_USERNAME = "savusername";
        private static final String PREF_PASSWORD = "savpassword";


        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);


            setContentView(R.layout.activity_main);


            strUserName = (EditText) findViewById(R.id.editText2);
            strPassword  = (EditText) findViewById(R.id.editText3);
            loginButton = (ImageButton) findViewById(R.id.btSignIn);
            savepass = (Switch) findViewById(R.id.switch2);

            loginButton.setOnClickListener(new View.OnClickListener() {


                public void onClick(View paramView)

                {

                    run();


                }});// onclick



            SharedPreferences pref = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
            strUserName.setText(pref.getString(PREF_USERNAME, null));
            strPassword.setText(pref.getString(PREF_PASSWORD, null));

        }


        @Override
        public void onBackPressed() {
            // TODO Auto-generated method stub
            AlertDialog.Builder confirm = new AlertDialog.Builder(this);
            confirm.setMessage("Exit Application?");
            confirm.setTitle("Confirm");
            confirm.setPositiveButton("Yes", new DialogInterface.OnClickListener(){

                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.this.finish();
                    System.exit(0);
                }

            });
            confirm.setNegativeButton("No", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub

                }
            });

            confirm.show();

        }

        public void run() {
            // TODO Auto-generated method stub

            if(strUserName.getText().length()==0)
            {

                ShowAlert.displayAlert(this,"Please enter valid Username");
                strUserName.requestFocus();
                return;
            }

            if(strPassword.getText().length()==0)
            {
                ShowAlert.displayAlert(this,"Please enter valid Password");
                strPassword.requestFocus();
                return;
            }

            String username=strUserName.getText().toString();
            String password=strPassword.getText().toString();
            final DatabaseHelper dbHelper = new DatabaseHelper(this);
            dbHelper.addUser(new User("Admin", "admin"));


            if(savepass.isChecked())
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
                Intent i = new Intent(this, Menu.class); // here the nextpage to be loaded is specified
                startActivity(i);
            }
            else
            {
                System.out.println("inside the else");
                ShowAlert.displayAlert(this,"Invalid Username/Password");
                return;

            }
        }
        @Override
        public void onStart()
        {
            try
            {
                super.onStart();

            }
            catch(Exception ex)
            {
                Log.d(TAG,ex.toString());
            }

        }
}
