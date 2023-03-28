package com.shareeasy.shareeasy.ui.profile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputEditText;
import com.shareeasy.shareeasy.MainActivity;
import com.shareeasy.shareeasy.R;
import com.shareeasy.shareeasy.login;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.Objects;

public class change_password extends Fragment {
    TextInputEditText initial_passwordd, confirm_password, new_passwordd;
    Button btnchange_password, back_button;
    TextView forgot_password;
    ProgressBar progressBar;
    public SharedPreferences sp;


    String Url_chage_password = "https://shareeasyapp.000webhostapp.com/change_password.php";

    public static change_password newInstance() {
        return new change_password();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_change_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        new_passwordd = view.findViewById(R.id.new_password);
        initial_passwordd = view.findViewById(R.id.initial_password);
        confirm_password = view.findViewById(R.id.confirm_password);
        btnchange_password = view.findViewById(R.id.btnchangepassword);
        forgot_password = view.findViewById(R.id.reset_password);
        back_button = view.findViewById(R.id.back_button);

        progressBar = view.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        sp = this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        sp = this.getActivity().getSharedPreferences("email", Context.MODE_PRIVATE);

        if (!sp.getBoolean("logged", true)) {
            Toast toast = Toast.makeText(this.getActivity(), " First Login to change password", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
//            Navigation.findNavController(v).navigate(R.id.action_change_profile_to_nav_profile);
        } else {
            back_button.setOnClickListener(v -> {
                Navigation.findNavController(v).navigate(R.id.action_change_profile_to_nav_profile);
                super.onDestroyView();
//                Object binding = null;
            });


            forgot_password.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Contact customer care for assitance.", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(), reset_password.class);
//                    startActivity(intent);
//                    finish();

                }
            });

            btnchange_password.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                            //(isset($_POST["id"]) AND isset($_POST["initial_password"]) AND isset($_POST["new_password"]) AND isset($_POST["cpassword"])

                            //String id;
                            String initial_password;
                            String new_password;
                            String cpassword;

                            new_password = Objects.requireNonNull(new_passwordd.getText()).toString();
                            initial_password = Objects.requireNonNull(initial_passwordd.getText()).toString();
                            cpassword = Objects.requireNonNull(confirm_password.getText()).toString();

                            String email = sp.getString("email", "");

                            if (!email.equals("")) {

                                if (!cpassword.equals("") && !new_password.equals("") && !initial_password.equals("")) {
                                    if (!initial_password.equals(new_password)) {
                                        if (new_password.equals(cpassword)) {

                                            if (isNetworkConnected()) {
                                                if (internetIsConnected()) {


                                            progressBar.setVisibility(View.VISIBLE);
                                            Handler handler = new Handler();
                                            handler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    String[] field = new String[3];
                                                    field[0] = "email";
                                                    field[1] = "initial_password";
                                                    field[2] = "new_password";


                                                    String[] data = new String[3];
                                                    data[0] = email;
                                                    data[1] = initial_password;
                                                    data[2] = new_password;


                                                    PutData putData = new PutData(Url_chage_password, "POST", field, data);

                                                    if (putData.startPut()) {
                                                        if (putData.onComplete()) {
                                                            progressBar.setVisibility(View.GONE);
                                                            String result = putData.getResult();
                                                            String search = "Update Success";

                                                            if (result.toLowerCase().contains(search.toLowerCase())) {

                                                                Toast.makeText(getContext(), "Password Update Success", Toast.LENGTH_SHORT).show();
                                                                //Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                                                                //sp.edit().putString("user_key", ).apply();
                                                                //goToMainActivity();
                                                            } else {
                                                                Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    }

                                                }
                                            });

                                                } else {
                                                    Toast toast = Toast.makeText(change_password.newInstance().getContext(), " Connected to Wi-Fi , No Internet", Toast.LENGTH_SHORT);
                                                    toast.setGravity(Gravity.CENTER, 0, 0);
                                                    toast.show();
                                                }
                                            } else {
                                                Toast toast = Toast.makeText(change_password.newInstance().getContext(), " Please Connect to a Network or turn on mobile data ", Toast.LENGTH_SHORT);
                                                toast.setGravity(Gravity.CENTER, 0, 0);
                                                toast.show();
                                            }
                                        } else {
                                            Toast.makeText(getContext(), "new password does not match confirm ", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(getContext(), "password match the original password", Toast.LENGTH_SHORT).show();
                                    }


                                } else {
                                    Toast.makeText(getContext(), " All Fields are required", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(getContext(), " Email not visible", Toast.LENGTH_SHORT).show();
                            }


                }
            });
            // }
        }
    }

    private Boolean internetIsConnected() {
        try {
            String command = "ping -c 1 google.com";
            return (Runtime.getRuntime().exec(command).waitFor() == 0);
        } catch (Exception e) {
            return false;
        }
    }

    @SuppressLint("MissingPermission")
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }


}
