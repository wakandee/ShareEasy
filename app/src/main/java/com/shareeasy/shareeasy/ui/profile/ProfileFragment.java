package com.shareeasy.shareeasy.ui.profile;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
import com.shareeasy.shareeasy.MainActivity;
import  com.shareeasy.shareeasy.R;
import com.shareeasy.shareeasy.ui.gethelp.UserInfo;

import de.hdodenhof.circleimageview.CircleImageView;
import com.shareeasy.shareeasy.utils.UIHelpers;
public class ProfileFragment extends Fragment {

    private CircleImageView avatarCIV;
    private TextView change_password,displayNameTV, emailTV, phoneTV, emailNotVerifiedTV;
    private MaterialButton setupAccountButton;
    String EmailName,photoUrl,displayName;


    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
    public SharedPreferences sp;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sp = getContext().getSharedPreferences("display_name", Context.MODE_PRIVATE);
        sp = getContext().getSharedPreferences("image_url", Context.MODE_PRIVATE);
        sp = this.getActivity().getSharedPreferences("email", Context.MODE_PRIVATE);

        displayName = sp.getString("display_name", "");
        photoUrl = sp.getString("image_url", "");
        EmailName = sp.getString("email", "");

        //Register the views associated
        displayNameTV = view.findViewById(R.id.display_name_tv);
        emailTV = view.findViewById(R.id.email_tv);
        phoneTV = view.findViewById(R.id.phone_tv);
        emailNotVerifiedTV = view.findViewById(R.id.email_not_verified_tv);
        setupAccountButton = view.findViewById(R.id.setup_account_button);
        avatarCIV = view.findViewById(R.id.avatar_civ);
        change_password = view.findViewById(R.id.change_password_txt);

        phoneTV.setOnClickListener(l ->{
//            Toast.makeText(getContext(), "add phone number", Toast.LENGTH_SHORT).show();
            UIHelpers.dialog(getContext(),"Coming up soon","Dismiss");

        });

        change_password.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_profileFragment_to_change_password);
        });
        setupAccountButton.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_profileFragment_to_setupAccountFragment);
        });

        //Load the image to the image view
        Glide.with(this)
                .load(photoUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_account_circle_white)
                .into(avatarCIV);

        //Set display Name
        if (!TextUtils.isEmpty(displayName)) {
            displayNameTV.setText("Name: "+displayName);
        }

        //Setting the authenticated email address

        emailTV.setText("Email: "+EmailName);

        //Check user phone number
//        if (!TextUtils.isEmpty(currentUser.getPhoneNumber())) {
//            phoneTV.setText(currentUser.getPhoneNumber());
//        }

        //Checking whether whether the authenticated user email is verified and display or hiding the barge if necessary
//        if (currentUser.isEmailVerified()) {
//            emailNotVerifiedTV.setVisibility(View.INVISIBLE);
//        } else {
//            emailNotVerifiedTV.setVisibility(View.VISIBLE);
//        }

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

//        mAuth = FirebaseAuth.getInstance();
//
//        currentUser = mAuth.getCurrentUser();
    }
}
