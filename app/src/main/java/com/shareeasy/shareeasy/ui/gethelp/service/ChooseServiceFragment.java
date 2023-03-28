package com.shareeasy.shareeasy.ui.gethelp.service;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.shareeasy.shareeasy.R;
import com.shareeasy.shareeasy.info_details;
//import com.shareeasy.shareeasy.data.models.Message;

public class ChooseServiceFragment extends Fragment {
    private static final String TAG = "ChooseServiceFragment";
    info_details donation_items;// = new info_details();


    private RecyclerView chooseServiceRecyclerView;

    private com.shareeasy.shareeasy.ui.gethelp.service.ChooseServiceAdapter chooseServiceAdapter;
    //private Message message;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_choose, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Toast.makeText(this.getActivity(), donation_items.item_to_donate, Toast.LENGTH_SHORT).show();
        //Get and dod presets on the recycler view
        chooseServiceRecyclerView = view.findViewById(R.id.choose_service_recycler_view);
        chooseServiceRecyclerView.setHasFixedSize(true);
        chooseServiceRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        assert getContext() != null;
        chooseServiceRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        //Register the button and it's click listener
        Button nextButton = view.findViewById(R.id.next_button);

//        assert getArguments() != null;

        //Getting the passed variables
//        message = ChooseServiceFragmentArgs.fromBundle(getArguments()).getMessage();
//
//        assert message != null;
//        Log.d(TAG, "onViewCreated: services : " + message.getItem_type());
        String service_to_donate = "";

        nextButton.setOnClickListener(v -> {



            if (TextUtils.isEmpty(service_to_donate)) {
                Toast.makeText(this.getActivity(), "Services is blank", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this.getActivity(), service_to_donate, Toast.LENGTH_SHORT).show();
            }

            info_details.service_to_donate = service_to_donate;
            Navigation.findNavController(view).navigate(R.id.action_chooseServiceFragment_to_chooseProductFragment);



//            message.addServices(chooseServiceAdapter.getCheckedServices());
//            ChooseServiceFragmentDirections.ActionChooseServiceFragmentToChooseProductFragment action = ChooseServiceFragmentDirections.actionChooseServiceFragmentToChooseProductFragment();
//            action.setMessage(message);
//            Navigation.findNavController(v).navigate((NavDirections) action);

            //Log.d(TAG, "onViewCreated: services count: " + chooseServiceAdapter.getCheckedServices().size());
        });


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        com.shareeasy.shareeasy.ui.gethelp.service.ChooseServiceFragmentViewModel viewModel = new ViewModelProvider(this).get(ChooseServiceFragmentViewModel.class);
//
//        viewModel.getServices().observe(getViewLifecycleOwner(), services -> {
//
//            chooseServiceAdapter = new com.project.ShareEasy.ui.fragments.gethelp.service.ChooseServiceAdapter(services);
//            chooseServiceRecyclerView.setAdapter(chooseServiceAdapter);
//
//        });

    }
}
