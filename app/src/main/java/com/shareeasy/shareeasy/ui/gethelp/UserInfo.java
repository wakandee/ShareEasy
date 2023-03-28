package com.shareeasy.shareeasy.ui.gethelp;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class UserInfo extends AppCompatActivity {
    // String photoUrl;
    private static String displayName= "";


    private static String photoUrl = "https://shareeasyapp.000webhostapp.com/images/ProfilePictures/default_photo.png";

    UserInfo(){

    }

    SharedPreferences sp;
    public static String getPhotoUrl(){

        return  photoUrl;
    }

    public static String getDisplayName() {
        return displayName;
    }

    public void setPhotoUrl(String PhotoUrl){
        photoUrl = PhotoUrl;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sp = this.getSharedPreferences("email", Context.MODE_PRIVATE);
        String Email = sp.getString("email", "");

       // UpdateUi(Email);



        //Toast.makeText(this, Email+"--", Toast.LENGTH_SHORT).show();
    }

    }
