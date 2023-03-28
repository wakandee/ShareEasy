package com.shareeasy.shareeasy.ui.services;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.shareeasy.shareeasy.CustomAdapter;
//import com.shareeasy.shareeasy.databinding.FragmentServicesBinding;
import com.shareeasy.shareeasy.R;

import java.util.ArrayList;
import java.util.List;

public class ServicesFragment extends Fragment {

//    private FragmentServicesBinding binding;
    String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry", "WebOS","Ubuntu","Windows7","Max OS X"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

//        binding = FragmentServicesBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
        View contentView = inflater.inflate(R.layout.fragment_services, container, false);



        //final TextView textView = binding.textSlideshow;

//        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(), R.layout.activity_list_services, mobileArray);
//
//        ListView listView = (ListView) root.findViewById(R.id.mobile_list);
//        listView.setAdapter(adapter);

        ListView listView = contentView.findViewById(R.id.listview);
        // sample data
        List<String> list = new ArrayList<>();

        list.add("Trasportation");
        list.add("Medical");
        list.add("Career Talks");
        list.add("Mentorship and Motivation");
        list.add("Spiritual Services");

        CustomAdapter listAdapter = new CustomAdapter(list);
        listView.setAdapter(listAdapter);
//        return contentView;

        return contentView;
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
}