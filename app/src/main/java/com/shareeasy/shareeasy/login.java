package com.shareeasy.shareeasy;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
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

public class login extends AppCompatActivity {
    private ProgressBar loginProgressBar;
    public SharedPreferences sp;
    String display_name,image_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        sp = getSharedPreferences("logged", MODE_PRIVATE);
        sp = getSharedPreferences("updateUi", MODE_PRIVATE);
        sp = getSharedPreferences("email", MODE_PRIVATE);
        sp = getSharedPreferences("display_name", MODE_PRIVATE);
        sp = getSharedPreferences("image_url", MODE_PRIVATE);
        sp = getSharedPreferences("email", Context.MODE_PRIVATE);
        String Email = sp.getString("email", "");

        if (sp.getBoolean("logged", false) && Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
//            Toast.makeText(this, "True ", Toast.LENGTH_SHORT).show();

            UpdateUserInfo(Email);

//            if (sp.getBoolean("updateUi", false)) {
//            Toast.makeText(this, "boom ", Toast.LENGTH_SHORT).show();
//            new Handler().postDelayed(new Runnable() { // delay for 50 milliseconds before updating user interface
//                @Override
//                public void run() {
//
//
//
//                }
//            }, 3000); // delay in milliseconds
            //}
            //            sp.edit().putBoolean("updateUi", true).apply();
        }

        EditText emailTxt = findViewById(R.id.email_txt);
        EditText passwordTxt = findViewById(R.id.password_txt);

        Button loginButton = findViewById(R.id.login_button);
        TextView gotoRegisterTv = findViewById(R.id.goto_register_tv);

        loginProgressBar = findViewById(R.id.loginProgressBar);

        gotoRegisterTv.setOnClickListener(v -> {
            Intent intent = new Intent(this, register.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();

//            Intent intent = new Intent(this, register.class);
//            startActivity(intent);
            //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        loginButton.setOnClickListener(v -> {

            String email = String.valueOf(emailTxt.getText());
            String password = String.valueOf(passwordTxt.getText());

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

                emailTxt.setError("Email is invalid");
                emailTxt.requestFocus();
                return;
            }

            if (password.length() < 4){
                passwordTxt.setError("Password should be at least 4 chars");
                passwordTxt.requestFocus();
                return;
            }
            //sendHome();
            loginProgressBar.setVisibility(View.VISIBLE);
            try {
                if (isNetworkConnected()) {
                    if (internetIsConnected()) {
                        loginUser(email, password);
                    } else {

                        Toast toast = Toast.makeText(getApplicationContext(), " Connected to Wi-Fi , No Internet", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        loginProgressBar.setVisibility(View.INVISIBLE);
                    }
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), " Please Connect to a Network or turn on mobile data ", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    loginProgressBar.setVisibility(View.INVISIBLE);
                }

            }catch (Exception e){

                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                loginProgressBar.setVisibility(View.INVISIBLE);
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


    private void loginUser(String email, String password) {
        String Url_login = "https://shareeasyapp.000webhostapp.com/login.php";
        loginProgressBar.setVisibility(View.VISIBLE);
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
                PutData putData = new PutData(Url_login, "POST", field, data);

                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        loginProgressBar.setVisibility(View.INVISIBLE);
                        String result = putData.getResult();
                        String search = "Login Success";
                        String search2 = "wrong email or password";
                        String error = "error occurred";
                        String exist = "Email does not exist";
                        if (exist.toLowerCase().contains(search.toLowerCase())) {
                            Toast.makeText(getApplicationContext(), exist, Toast.LENGTH_SHORT).show();
                        } else if (result.toLowerCase().contains(search.toLowerCase())) {
                        //if (result == ""){
                            sp.edit().putBoolean("logged", true).apply();
                            sp.edit().putString("email", email).apply();

                            Toast.makeText(getApplicationContext(), search, Toast.LENGTH_SHORT).show();
                            UpdateUserInfo(email);

                            //goToMainActivity();
                        }else  if(result.toLowerCase().contains(search2.toLowerCase())) {
                        //}else  if(result == "2"){
                            Toast.makeText(getApplicationContext(), search2, Toast.LENGTH_LONG).show();

                        }else  if(result.toLowerCase().contains(error.toLowerCase())) {
                        //}else  if(result == "3"){
                            Toast.makeText(getApplicationContext(), "Error Occurred, Try again...", Toast.LENGTH_LONG).show();

                        }else {
                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//
//
//        //if (currentUser != null) sendHome();
//
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//    }
public void onBackPressed() {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setCancelable(false);
    builder.setMessage("Do you want to exit?");
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

    private void sendHome() {

//        Toast.makeText(getApplicationContext(), "DN:"+sp.getString("display_name", "")+"PU:"+sp.getString("image_url", ""), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();

    }

    private void UpdateUserInfo(String Email) {

            if (isNetworkConnected()) {
                if (internetIsConnected()) {

                    String Url_get_info = "https://shareeasyapp.000webhostapp.com/GetUserInfo.php";
//                    progressDialog1.dismiss();
                    ProgressDialog progressDialog2 = ProgressDialog.show(this, "Loading info ...", "Please Wait", false, false);
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[1];
                            field[0] = "email";

                            String[] data = new String[1];
                            data[0] = Email;
                            PutData putData = new PutData(Url_get_info, "POST", field, data);

                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressDialog2.dismiss();
                                    String result = putData.getResult();
                                    String search = "email is a must";
                                    String search2 = "Failed";
                                    String search3 = "-1";
                                    String error = "error occurred";

                                    String search1 = "Email does not exist";

                                    String search4 = "User Does not exist";

                                    String search5 = "DetailsLoaded";

                                    if (error.toLowerCase().contains(search.toLowerCase())) {
                                        display_name = "N/A"; // this will contain name
                                        image_url = "N/A"; // this will contain image url
                                        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
                                    }else if (result.toLowerCase().contains(search4.toLowerCase())) {
//                                        Toast.makeText(getApplicationContext(), "User Information does not exist", Toast.LENGTH_SHORT).show();
                                        display_name = "N/A"; // this will contain name
                                        image_url = "N/A"; // this will contain image url
                                    }else if (result.toLowerCase().contains(search1.toLowerCase())) {
                                        //String lpo[] = JSON.parse(this.responseText)// this.responseText;

                                        Toast.makeText(getApplicationContext(), search1, Toast.LENGTH_SHORT).show();
                                        //sp.edit().putString("display_name", displayName).apply();
                                        display_name = "N/A"; // this will contain name
                                        image_url = "N/A"; // this will contain image url
                                    }else if (result.toLowerCase().contains(search2.toLowerCase())) {
                                        //}else  if(result == "2"){
                                        Toast.makeText(getApplicationContext(), search2, Toast.LENGTH_SHORT).show();
                                        display_name = "N/A"; // this will contain name
                                        image_url = "N/A"; // this will contain image url

                                    }else if (result.toLowerCase().contains(search.toLowerCase())) {
                                        Toast.makeText(getApplicationContext(), search, Toast.LENGTH_SHORT).show();
                                        display_name = "N/A"; // this will contain name
                                        image_url = "N/A"; // this will contain image url
                                    } else if (result.toLowerCase().contains(search3.toLowerCase())) {
                                        display_name = "N/A"; // this will contain name
                                        image_url = "N/A"; // this will contain image url
                                        Toast.makeText(getApplicationContext(), "Contact Customer care", Toast.LENGTH_SHORT).show();
                                    }else if (result.toLowerCase().contains(search5.toLowerCase())) {

//                                        Toast.makeText(login.this, result, Toast.LENGTH_SHORT).show();
                                        String[] separated = result.split("::");

                                        if (separated[0].equals("null-") && separated[1].equals("null-")){

                                            display_name = "N/A"; // this will contain name
                                            image_url = "N/A"; // this will contain image url
                                        }else if (separated[0].equals("null-")){
                                            display_name = "N/A"; // this will contain name
                                            image_url = separated[1]; // this will contain image url
                                        }else if (separated[1].equals("null-")){
                                            display_name = separated[0]; // this will contain name
                                            image_url = "N/A"; // this will contain image url
                                        }else {
                                            display_name = separated[0]; // this will contain name
                                            image_url = separated[1]; // this will contain image url
                                        }

                                    } else {
                                        Toast.makeText(login.this, result, Toast.LENGTH_SHORT).show();
                                    }

                                    sp.edit().putString("display_name", display_name).apply();
                                    sp.edit().putString("image_url", image_url).apply();

                                    sendHome();
                                }
                            }

                        }
                    });

                } else {
//                    progressDialog1.dismiss();
                    Toast toast = Toast.makeText(getApplicationContext(), " Connected to Wi-Fi , No Internet", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                    sendHome();

                }
            } else {
//                progressDialog1.dismiss();
                Toast toast = Toast.makeText(getApplicationContext(), " Please Connect to a Network or turn on mobile data to load info", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                sendHome();

            }



        sendHome();

    }
}
