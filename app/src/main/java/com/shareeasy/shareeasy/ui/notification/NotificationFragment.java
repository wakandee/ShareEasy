package com.shareeasy.shareeasy.ui.notification;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.shareeasy.shareeasy.databinding.FragmentNotificationBinding;

public class NotificationFragment extends Fragment {

    //private NotificationViewModel notificationViewModel;
    private FragmentNotificationBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //notificationViewModel = new ViewModelProvider(this).get(NotificationViewModel.class);

        binding = FragmentNotificationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}