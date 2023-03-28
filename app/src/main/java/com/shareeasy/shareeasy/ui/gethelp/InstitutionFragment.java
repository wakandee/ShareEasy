package com.shareeasy.shareeasy.ui.gethelp;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.shareeasy.shareeasy.R;
import com.shareeasy.shareeasy.info_details;

//import com.shareeasy.shareeasy.data.models.Message;

public class InstitutionFragment extends Fragment {

    info_details donation_items = new info_details();
    private AutoCompleteTextView institutionAtv;
    private Message message;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_institution, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        message = InstitutionFragmentArgs.fromBundle(requireArguments()).getMessage();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //institution Dropdown
        assert getActivity() != null;

        String[] institution = getActivity().getResources().getStringArray(R.array.institution);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.dropdown_menu_popup_item, institution);
        institutionAtv = view.findViewById(R.id.institution_atv);
        institutionAtv.setAdapter(adapter);

        institutionAtv.setOnItemClickListener((parent, view1, position, id) -> closeKeyboard());
        // Register the rest of the views
        Button skipButton = view.findViewById(R.id.skip_button);
        Button nextButton = view.findViewById(R.id.next_button);

        skipButton.setOnClickListener(v -> {

            Navigation.findNavController(view).navigate(R.id.action_institutionFragment_to_reviewFragment);

//            InstitutionFragmentDirections.ActionInstitutionFragmentToReviewFragment action = InstitutionFragmentDirections.actionInstitutionFragmentToReviewFragment();

//            action.setMessage(message);

//            Navigation.findNavController(v).navigate(action);

        });

        nextButton.setOnClickListener(v -> {
            String institutions = String.valueOf(institutionAtv.getText());

            // assert message != null;

//            message.setInstitution(institutions);


            if (TextUtils.isEmpty(institutions)) {
                institutionAtv.setError("Select which institution you want");
                institutionAtv.requestFocus();
                return;
            }
            info_details.institutions = institutions;
            Navigation.findNavController(view).navigate(R.id.action_institutionFragment_to_reviewFragment);
//            InstitutionFragmentDirections.ActionInstitutionFragmentToReviewFragment action = InstitutionFragmentDirections.actionInstitutionFragmentToReviewFragment();
//            action.setMessage(message);
//            Navigation.findNavController(v).navigate(action);

        });

    }

    private void closeKeyboard() {
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        if (imm != null)
            imm.hideSoftInputFromWindow(institutionAtv.getApplicationWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

}
