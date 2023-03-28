package com.shareeasy.shareeasy.ui.gethelp.products;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shareeasy.shareeasy.R;
import com.shareeasy.shareeasy.info_details;
//import com.shareeasy.shareeasy.data.models.Message;

public class ChooseProductFragment extends Fragment {
    private static final String TAG = "ChooseProductFragment";
    String product_to_donate = "";
    info_details donation_items;// = new info_details();

    private com.shareeasy.shareeasy.ui.gethelp.products.ChooseProductAdapter chooseProductAdapter;
//        private Message message;

    public ChooseProductFragment() {
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        assert getArguments() != null;
//        //The the message argument first
////        message = ChooseProductFragmentArgs.fromBundle(getArguments()).getMessage();
//    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.choose_product_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        assert getContext() != null;

        RecyclerView productsRecyclerView = view.findViewById(R.id.products_recycler_view);
        productsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        productsRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        productsRecyclerView.setHasFixedSize(true);

        //Register the button and it's click listener
        Button nextButton = view.findViewById(R.id.next_button);

        nextButton.setOnClickListener(v -> {
//            assert chooseProductAdapter != null;
           // Toast.makeText(this.getActivity(), message.getDonated_items(), Toast.LENGTH_SHORT).show();
            if (TextUtils.isEmpty(product_to_donate)) {
                Toast.makeText(this.getActivity(), "Product is blank", Toast.LENGTH_SHORT).show();
            }else{

                Toast.makeText(this.getActivity(), product_to_donate, Toast.LENGTH_SHORT).show();
            }


            info_details.product_to_donate = product_to_donate;
//            Navigation.findNavController(view).navigate(R.id.action_chooseProductFragment_to_locationFragment);

//            message.addProducts(chooseProductAdapter.getCheckedProducts());
//            ChooseProductFragmentDirections.ActionChooseProductFragmentToLocationFragment action = ChooseProductFragmentDirections.actionChooseProductFragmentToLocationFragment();
//            action.setMessage(message);
//            Navigation.findNavController(v).navigate(action);

            //Log.d(TAG, "onViewCreated: products count: " + chooseProductAdapter.getCheckedProducts().size());
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        com.project.ShareEasy.ui.fragments.gethelp.products.ChooseProductViewModel mViewModel = new ViewModelProvider(this).get(ChooseProductViewModel.class);
//
//        mViewModel.getProducts().observe(getViewLifecycleOwner(), products -> {
//
//            chooseProductAdapter = new com.project.ShareEasy.ui.fragments.gethelp.products.ChooseProductAdapter(products);
//            productsRecyclerView.setAdapter(chooseProductAdapter);

        //});
    }

}
