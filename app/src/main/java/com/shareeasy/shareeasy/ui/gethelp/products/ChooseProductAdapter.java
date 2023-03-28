package com.shareeasy.shareeasy.ui.gethelp.products;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.shareeasy.shareeasy.R;
//import com.shareeasy.shareeasy.data.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ChooseProductAdapter<Product> extends RecyclerView.Adapter<ChooseProductAdapter.ProductViewHolder> {
    private static final String TAG = "ChooseProductAdapter";
    private final List<Product> products;
//    private List<Product> products;

    public ChooseProductAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_select_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        //Set checkbox name to the product name
//        holder.checkBox.setText(products.get(position).getName());

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {

//            if (products.get(position).isChecked())
//                products.get(position).setChecked(false);
//            else products.get(position).setChecked(true);

            Log.d(TAG, "onBindViewHolder: product: " + products.get(position));
        });

    }

    public List<Product> getCheckedProducts() {
        List<Product> selectedProducts = new ArrayList<>();

        for (Product product : products)
//            if (product.isChecked())
                selectedProducts.add(product);

        return selectedProducts;

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;

        ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }
}
