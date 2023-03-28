package com.shareeasy.shareeasy.ui.products;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.shareeasy.shareeasy.R;
import com.shareeasy.shareeasy.data.models.Product;
import com.shareeasy.shareeasy.databinding.FragmentProductsBinding;
import com.shareeasy.shareeasy.utils.UIHelpers;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductsFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private RecyclerView.Adapter mAdapter;
    private List<Product> products;
    ProgressDialog progressDialog ;
    private static  final String BASE_URL = "https://shareeasyapp.000webhostapp.com/get_donated_items.php";


    private FragmentProductsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentProductsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.products_recycler_view);
        manager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(manager);
        products = new ArrayList<>();

        getProducts();

    }

    private void getProducts (){
//        progressBar.setVisibility(View.VISIBLE);
        progressDialog = ProgressDialog.show(getContext(),"Fetching items...","Please Wait",false,false);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

//                        progressBar.setVisibility(View.GONE);
                        progressDialog.dismiss();

                        try {

                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i<array.length(); i++){

                                JSONObject object = array.getJSONObject(i);
                                String item = object.getString("item");

                                String item_type = object.getString("item_type");
                                String item_date = object.getString("item_date");
                                String item_status = object.getString("item_status");

                                String food_description = object.getString("food_description");

                                Product product = new Product(item,item_type,item_date,item_status, food_description);
                                products.add(product);
                            }

                        }catch (Exception e){

                        }

                        mAdapter = new RecyclerAdapter(getContext(),products);
                        recyclerView.setAdapter(mAdapter);
//                        UIHelpers.status_dialog(getContext()," suceess");

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

//                progressBar.setVisibility(View.GONE);
                progressDialog.dismiss();
                Toast.makeText(getContext(), error.toString(),Toast.LENGTH_LONG).show();

            }
        });

        Volley.newRequestQueue(getContext()).add(stringRequest);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}