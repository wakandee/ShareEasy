package com.shareeasy.shareeasy;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;


public class register extends AppCompatActivity {
    ProgressBar registerProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);


        EditText emailTxt = findViewById(R.id.email_txt);
        EditText passwordTxt = findViewById(R.id.password_txt);

        Button registerButton = findViewById(R.id.register_button);
        TextView gotoLogin = findViewById(R.id.goto_login);

        registerProgressBar = findViewById(R.id.registerProgressBar);

        gotoLogin.setOnClickListener(v ->{
            gotoLogin();
        });

        //Registration
        registerButton.setOnClickListener(v -> {

            String email = emailTxt.getText().toString().trim();
            String password = passwordTxt.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                emailTxt.setError("Email is Required");
                emailTxt.requestFocus();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

                emailTxt.setError("Email is invalid");
                emailTxt.requestFocus();
                return;
            }

            if (password.length() <4){
                passwordTxt.setError("Password should be at least 4 chars");
                passwordTxt.requestFocus();
                return;
            }


            registerProgressBar.setVisibility(View.VISIBLE);
            try {
                if (isNetworkConnected()) {
                    if (internetIsConnected()) {
            registerUser(email, password);
                    } else {

                        Toast toast = Toast.makeText(getApplicationContext(), " Connected to Wi-Fi , No Internet", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        registerProgressBar.setVisibility(View.INVISIBLE);
                    }
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), " Please Connect to a Network or turn on mobile data ", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    registerProgressBar.setVisibility(View.INVISIBLE);
                }

            }catch (Exception e){

                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                registerProgressBar.setVisibility(View.INVISIBLE);
            }
        });

        }

        private void registerUser(String email, String password) {
            String link_signup = "https://shareeasyapp.000webhostapp.com/signup.php";
            registerProgressBar.setVisibility(View.VISIBLE);
            Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    String[] field = new String[2];
                    field[0] = "email";
                    field[1] = "password";

                    String[] data = new String[2];
                    data[0] = email;
                    data[1] = password;
                    PutData putData = new PutData(link_signup, "POST", field, data);

                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            registerProgressBar.setVisibility(View.INVISIBLE);
                            String result = putData.getResult();
                            String search = "Registration succeeded";
                            String error = "error occurred";
                            String fail = "Failed to register";
                            String exist = "Email already exist";
                            if (result.toLowerCase().contains(exist.toLowerCase())) {
                                Toast.makeText(getApplicationContext(), exist, Toast.LENGTH_SHORT).show();

                            } else if (result.toLowerCase().contains(search.toLowerCase())) {

                                Toast.makeText(getApplicationContext(), search, Toast.LENGTH_SHORT).show();
                                gotoLogin();
                                //sp.edit().putBoolean("logged", true).apply();
                                //sp.edit().putString("email", email).apply();
                                //goToMainActivity();
                            }else  if(result.toLowerCase().contains(fail.toLowerCase())) {
                                Toast.makeText(getApplicationContext(), fail, Toast.LENGTH_LONG).show();

                            }else  if(result.toLowerCase().contains(error.toLowerCase())) {
                                Toast.makeText(getApplicationContext(), "Error Occurred, Try again...", Toast.LENGTH_LONG).show();

                            }else {
                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                }
            });

        }
    private Boolean internetIsConnected() {
        try {

            String command = "ping -c 1 google.com";
            return (Runtime.getRuntime().exec(command).waitFor() == 0);

        } catch (Exception e) {
            return false;
        }
    }

    @TargetApi(Build.VERSION_CODES.M)

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetwork() != null;
    }


    private void gotoLogin() {

//        Intent intent = new Intent(this, login.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();

    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Discard details and go back?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                //finishAffinity();
                //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
