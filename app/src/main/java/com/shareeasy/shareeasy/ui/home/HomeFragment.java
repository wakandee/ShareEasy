package com.shareeasy.shareeasy.ui.home;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.shareeasy.shareeasy.MainActivity;
import com.shareeasy.shareeasy.MainActivity;
import com.shareeasy.shareeasy.R;

import de.hdodenhof.circleimageview.CircleImageView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import com.shareeasy.shareeasy.ui.gethelp.UserInfo;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class HomeFragment extends Fragment {

    //private FirebaseAuth mAuth;
    public SharedPreferences sp;

//    TextView server;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        HomeViewModel mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        sp = getContext().getSharedPreferences("display_name", Context.MODE_PRIVATE);
        sp = getContext().getSharedPreferences("image_url", Context.MODE_PRIVATE);
        sp = this.getActivity().getSharedPreferences("email", Context.MODE_PRIVATE);
        String name = "Name: "+sp.getString("display_name", "");
        String photoUrl = sp.getString("image_url", "");
        String email = sp.getString("email", "");
        //        String photoUrl = UserInfo.getPhotoUrl();




        Button getStaredButton = view.findViewById(R.id.get_started_button);

        getStaredButton.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_donated_itemsFragment);
        });

        CircleImageView avatarCiv = view.findViewById(R.id.avatar_civ);
        TextView nameTv = view.findViewById(R.id.name_tv);
        TextView emailTv = view.findViewById(R.id.email_tv);

//        if (!TextUtils.isEmpty(currentUser.getDisplayName()))



        //Toast.makeText(getContext(), "DN:"+sp.getString("display_name", "")+"PU:"+sp.getString("image_url", ""), Toast.LENGTH_SHORT).show();
        nameTv.setText(name);
//        Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();
        emailTv.setText("Email: "+email);

        Glide.with(this)
                .load(photoUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_account_circle)
                .into(avatarCiv);


        CardView servicesCardView = view.findViewById(R.id.services_card_view);
        servicesCardView.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.nav_services));

        CardView productsCardView = view.findViewById(R.id.products_card_view);
        productsCardView.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.nav_products));

        CardView notificationsCardView = view.findViewById(R.id.notifications_card_view);
        notificationsCardView.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.nav_notification));

        CardView aboutUsCardView = view.findViewById(R.id.about_us_card_view);
        aboutUsCardView.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.nav_about));

        CardView contactCardView = view.findViewById(R.id.contact_card_view);
        contactCardView.setOnClickListener(v -> initiateCall());

        CardView profileCardView = view.findViewById(R.id.profile_card_view);
        profileCardView.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.nav_profile));

    }

    private void UpdateUi() {
    }

    private void initiateCall() {

        assert getContext() != null;
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:+25470000000000"));
            startActivity(intent);
        } else {
            assert getActivity() != null;
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, MainActivity.CALL_PHONE_RC);
        }
    }
}
