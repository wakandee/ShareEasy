package com.shareeasy.shareeasy.ui.logout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.shareeasy.shareeasy.MainActivity;
import com.shareeasy.shareeasy.R;
import com.shareeasy.shareeasy.databinding.FragmentProductsBinding;
import com.shareeasy.shareeasy.login;

import java.util.Objects;

public class logout extends Fragment {
    private FragmentProductsBinding binding;
    SharedPreferences sp;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentProductsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setCancelable(false);
            builder.setMessage("Do you want to Log Out?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //if user pressed "yes", then he is allowed to exit from application
                    sp = getContext().getSharedPreferences("logged", Context.MODE_PRIVATE);
                    sp = getContext().getSharedPreferences("updateUi",Context.MODE_PRIVATE);
                    sp = getContext().getSharedPreferences("email", Context.MODE_PRIVATE);
                    sp.edit().putBoolean("logged", false).apply();
                    sp.edit().putBoolean("updateUi", false).apply();
                    sp.edit().putString("email", "").apply();
                    Toast.makeText(getContext(),"successfully logged out",Toast.LENGTH_SHORT).show();
                  sendToLogin();

                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //if user select "No", just cancel this dialog and continue with app
                    dialog.cancel();
                    onDestroyView();

                    gotToMain();
//                    finish();
                    //Navigation.findNavController().navigate(R.id.action_profileFragment_to_setupAccountFragment);
                }
            });
            AlertDialog alert = builder.create();
            alert.show();

        return root;
    }

    private void sendToLogin() {
        Intent intent = new Intent(getContext(), login.class);
        startActivity(intent);
        requireActivity().getFragmentManager().popBackStack();
    }

    private void gotToMain() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        requireActivity().getFragmentManager().popBackStack();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
