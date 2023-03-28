package com.shareeasy.shareeasy.ui.gethelp;


import com.shareeasy.shareeasy.info_details;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.project.ShareEasy.R;
import com.shareeasy.shareeasy.R;
//import com.shareeasy.shareeasy.data.models.Message;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonatedItemsFragment extends Fragment {
    public SharedPreferences sp;

//    private FirebaseAuth mAuth;
    private AutoCompleteTextView donated_itemsATV;

    //info_details donation_items;// = new info_details();

    public DonatedItemsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Initialize fire-base instances
//        mAuth = FirebaseAuth.getInstance();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_donated_items, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //Car Models Dropdown
        assert getActivity() != null;

        String[] donated_items = getActivity().getResources().getStringArray(R.array.donated_items);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.dropdown_menu_popup_item, donated_items);
        donated_itemsATV = view.findViewById(R.id.car_model_tv);
        donated_itemsATV.setAdapter(adapter);

        donated_itemsATV.setOnItemClickListener((parent, view1, position, id) -> closeKeyboard());

        Button nextButton = view.findViewById(R.id.next_button);

        nextButton.setOnClickListener(v -> {

            String model = String.valueOf(donated_itemsATV.getText());


            if (TextUtils.isEmpty(model)) {
                donated_itemsATV.requestFocus();
                donated_itemsATV.setError("Category is required");
                return;
            }else{
//                sp = this.getActivity().getSharedPreferences("email", Context.MODE_PRIVATE);
//                String email = sp.getString("email", "");
//                //Instantiate the message to be sent
//                Message message = new Message(model, email, false);
//                message.setDonated_items(model);

                //donation_items.item_to_donate = model;
                info_details.item_to_donate =model;
                Toast.makeText(this.getActivity(), "item_to_donate: "+ info_details.item_to_donate, Toast.LENGTH_SHORT).show();
                Navigation.findNavController(view).navigate(R.id.action_donated_itemsFragment_to_chooseServicenew);

            }

//            FirebaseUser currentUser = mAuth.getCurrentUser();

//            if (currentUser == null) return;

            sp = this.getActivity().getSharedPreferences("email", Context.MODE_PRIVATE);
            String email = sp.getString("email", "");
            //Instantiate the message to be sent
//            Message message = new Message(model, email, false);

            //Set the message to the action
//            DonatedItemsFragmentDirections.ActionDonatedItemsFragmentToChooseServiceFragment action = DonatedItemsFragmentDirections.actionDonatedItemsFragmentToChooseServiceFragment();
//            action.setMessage(message);
//
//            Navigation.findNavController(v).navigate(action);

        });
    }

    private void closeKeyboard() {
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        if (imm != null)
            imm.hideSoftInputFromWindow(donated_itemsATV.getApplicationWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
    }
}
