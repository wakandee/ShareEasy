package com.shareeasy.shareeasy.ui.gethelp;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.shareeasy.shareeasy.R;
//import com.shareeasy.shareeasy.data.models.Message;
import com.shareeasy.shareeasy.info_details;

public class InfoFragment extends Fragment {
    private static final String TAG = "InfoFragment";
    //info_details donation_items = new info_details();

    public InfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Get all the views
        TextInputEditText itemTxt = view.findViewById(R.id.item_txt);
        TextInputEditText descriptionTxt = view.findViewById(R.id.description_txt);

        //Get the message from the location fragment
//        assert getArguments() != null;

//        Message message = InfoFragmentArgs.fromBundle(getArguments()).getMessage();


        MaterialButton nextButton = view.findViewById(R.id.next_button);

        nextButton.setOnClickListener(v -> {

            String item = String.valueOf(itemTxt.getText());
            String description = String.valueOf(descriptionTxt.getText());

            if (TextUtils.isEmpty(item)) {
                itemTxt.setError("Item is Required");
                itemTxt.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(description)) {
                descriptionTxt.setError("Description is Required");
                descriptionTxt.requestFocus();
                return;
            }
            info_details.itemTxt = item;
            info_details.descriptionTxt = description;

            Toast.makeText(getContext(), info_details.itemTxt, Toast.LENGTH_SHORT).show();

            Navigation.findNavController(view).navigate(R.id.action_infoFragment_to_institutionFragment);

//            assert message != null;
//            message.setItem_type(item);
//            message.setDescription(description);
//
//            InfoFragmentDirections.ActionInfoFragmentToInstitutionFragment action = InfoFragmentDirections.actionInfoFragmentToInstitutionFragment();
//            action.setMessage(message);
//            Navigation.findNavController(v).navigate(action);

        });
//        assert message != null;
//        Log.d(TAG, "onViewCreated: location name: " + message.getLocationName());

    }
}
