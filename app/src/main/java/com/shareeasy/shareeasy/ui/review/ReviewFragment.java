package com.shareeasy.shareeasy.ui.review;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.shareeasy.shareeasy.R;
//import com.shareeasy.shareeasy.data.models.Message;
//import com.shareeasy.shareeasy.ui.gethelp.InstitutionFragmentArgs;
import com.shareeasy.shareeasy.utils.UIHelpers;
import com.shareeasy.shareeasy.info_details;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.List;
import java.util.Locale;

public class ReviewFragment extends Fragment {
    private static final String TAG = "ReviewFragment";
    public SharedPreferences sp;
    String user_email = "";

//    private ReviewViewModel mViewModel;
//    private Message message;
    info_details donation_items = new info_details();
    ProgressDialog progressDialog ;

    private NavController navController;

    public ReviewFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get the message
//        message = InstitutionFragmentArgs.fromBundle(requireArguments()).getMessage();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.review_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        sp = this.getActivity().getSharedPreferences("email", Context.MODE_PRIVATE);
        user_email = sp.getString("email", "");

        navController = Navigation.findNavController(view);
        Toast.makeText(getContext(),donation_items.itemTxt, Toast.LENGTH_SHORT).show();

        //Register the views
        TextView donateditemsTv = view.findViewById(R.id.donated_items_tv);
        TextView locationTv = view.findViewById(R.id.location_tv);
        TextView itemtypeTv = view.findViewById(R.id.item_type_tv);
        TextView descriptionTv = view.findViewById(R.id.description_tv);
        TextView institutionTv = view.findViewById(R.id.institution_tv);
        //TextView services_tv = view.findViewById(R.id.services_tv);

//        RecyclerView servicesRecyclerView = view.findViewById(R.id.selected_services_recycler_view);
        //servicesRecyclerView.setAdapter(new com.shareeasy.shareeasy.ui.review.SelectedItemsAdapter(message.getServicesList(), "services"));

//        RecyclerView productsRecyclerView = view.findViewById(R.id.selected_products_recycler_view);
        //productsRecyclerView.setAdapter(new com.shareeasy.shareeasy.ui.review.SelectedItemsAdapter(message.getProductsList(), "products"));


       // assert message != null;

//        donateditemsTv.setText(message.getDonated_items());
//        locationTv.setText(message.getLocationName());
//        itemtypeTv.setText(message.getItem_type());
//        descriptionTv.setText(message.getDescription());
//
//        if (TextUtils.isEmpty(message.getInstitution()))
//            institutionTv.setText(getResources().getString(R.string.no_institution));
//        else
//            institutionTv.setText(message.getInstitution());
//
//        Button sendRequestBtn = view.findViewById(R.id.send_request_btn);
//
//        sendRequestBtn.setOnClickListener(v -> sendMessage());

        String Location_adress = getCompleteAddressString(info_details.mLocationLatLng.latitude,info_details.mLocationLatLng.longitude);

        donateditemsTv.setText(info_details.item_to_donate);
        locationTv.setText(Location_adress);
        itemtypeTv.setText(info_details.itemTxt);
        descriptionTv.setText(info_details.descriptionTxt);

        //services_tv.setText(info_details.servicesss.toString());

        if (TextUtils.isEmpty(info_details.institutions))
            institutionTv.setText(getResources().getString(R.string.no_institution));
        else
            institutionTv.setText(info_details.institutions);

        Button sendRequestBtn = view.findViewById(R.id.send_request_btn);

        sendRequestBtn.setOnClickListener(v ->{


            sendMessage(info_details.item_to_donate,info_details.itemTxt,info_details.descriptionTxt,info_details.institutions,Location_adress);
            Navigation.findNavController(view).navigate(R.id.action_message_send);
        } );


    }

//    private void sendMessage() {
//        progressDialog = ProgressDialog.show(getContext(),"Image is Uploading","Please Wait",false,false);
//        Log.d(TAG, "sendMessage: started sending message");
//        Toast.makeText(getContext(), "Sending message...", Toast.LENGTH_SHORT).show();
//        //UIHelpers.toast("Sending message...");
//
//        //mViewModel.addMessage(message);
//
////        progressDialog.dismiss();
//    }
private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
    String strAdd = "";
    Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
    try {
        List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
        if (addresses != null) {
            Address returnedAddress = addresses.get(0);
            StringBuilder strReturnedAddress = new StringBuilder("");

            for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
            }
            strAdd = strReturnedAddress.toString();
//            Log.w("My Current loction address", strReturnedAddress.toString());
        } else {
//            Log.w("My Current loction address", "No Address returned!");
        }
    } catch (Exception e) {
        e.printStackTrace();
//        Log.w("My Current loction address", "Cannot get Address!");
    }
    return strAdd;
}

    private void sendMessage(String item_to_donate, String item_type, String item_description, String institutions,String location) {
        String Url_login = "https://shareeasyapp.000webhostapp.com/add_donated_items.php";
//        loginProgressBar.setVisibility(View.VISIBLE);
        progressDialog = ProgressDialog.show(getContext(),"Sending message...","Please Wait",false,false);
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[6];
                field[0] = "email";
                field[1] = "item_to_donate";
                field[2] = "item_type";
                field[3] = "item_description";
                field[4] = "institutions";
                field[5] = "location";

                String[] data = new String[6];
                data[0] = user_email;
                data[1] = item_to_donate;
                data[2] = item_type;
                data[3] = item_description;
                data[4] = institutions;
                data[5] = location;

                PutData putData = new PutData(Url_login, "POST", field, data);

                if (putData.startPut()) {
                    if (putData.onComplete()) {
//                        loginProgressBar.setVisibility(View.INVISIBLE);
                        progressDialog.dismiss();
                        String result = putData.getResult();
                        String search = "Success";
                        String search2 = "Failed";
                        String error = "error occurred";
                        if (result.toLowerCase().contains(search.toLowerCase())) {
                            //if (result == ""){

                            UIHelpers.status_dialog(getContext(),"Item details successfully saved");

//                            Toast.makeText(getContext(), search, Toast.LENGTH_SHORT).show();

                            //goToMainActivity();
                        }else  if(result.toLowerCase().contains(search2.toLowerCase())) {
                            //}else  if(result == "2"){
                            UIHelpers.status_dialog(getContext(),search2+" saving info");
//                            Toast.makeText(getContext(), search2, Toast.LENGTH_LONG).show();

                        }else  if(result.toLowerCase().contains(error.toLowerCase())) {
                            //}else  if(result == "3"){
                            UIHelpers.status_dialog(getContext(),"Error Occurred, Try again...");
//                            Toast.makeText(getContext(), "Error Occurred, Try again...", Toast.LENGTH_LONG).show();

                        }else {
                            UIHelpers.status_dialog(getContext()," Contact Customer care for assistance ");
//                            Toast.makeText(getContext(), " Contact Customer care for assistance", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        mViewModel = new ViewModelProvider(this).get(com.shareeasy.shareeasy.ui.review.ReviewViewModel.class);
//
//        mViewModel.getSuccessMessage().observe(getViewLifecycleOwner(), successMessage -> {
//            UIHelpers.toast(successMessage);
//            navController.navigate(R.id.action_message_send);
//        });
//        mViewModel.getErrorMessage().observe(getViewLifecycleOwner(), errorMessage -> {
//            Log.e(TAG, "onActivityCreated: message not added" + errorMessage);
////            UIHelpers.toast(errorMessage);
//        });

    }


}
