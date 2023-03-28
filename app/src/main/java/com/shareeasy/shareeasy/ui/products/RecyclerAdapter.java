package com.shareeasy.shareeasy.ui.products;

import androidx.recyclerview.widget.RecyclerView;

import com.shareeasy.shareeasy.R;
import com.shareeasy.shareeasy.data.models.Product;

import java.util.List;



import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private final Context mContext;
    private List<Product> products = new ArrayList<>();


    public RecyclerAdapter (Context context,List<Product> products){
        this.mContext = context;
        this.products = products;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView mfood_description;
        private final TextView item,item_type,item_date,item_status;
        private final LinearLayout mContainer;


        public MyViewHolder(View view){
            super(view);

            item = view.findViewById(R.id.product_title);
            item_type = view.findViewById(R.id.product_type);
            item_date = view.findViewById(R.id.item_date);
            item_status = view.findViewById(R.id.item_status);
            mfood_description= view.findViewById(R.id.product_description);
            mContainer = view.findViewById(R.id.product_container);

        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.products_list_item_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Product product = products.get(position);


        holder.item.setText(product.getitem());
        holder.item_type.setText(product.getitem_type());
        holder.item_date.setText(product.getitem_date());
        holder.item_status.setText(product.getitem_status());
        holder.mfood_description.setText(product.getfood_description());


//        holder.mContainer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(mContext,DetailedProductsActivity.class);
//
//                intent.putExtra("item",product.getitem());
//                intent.putExtra("item_type",product.getitem_type());
//                intent.putExtra("item_date",product.getitem_date());
//                intent.putExtra("item_status",product.getitem_status());
//                intent.putExtra("food_description",product.getfood_description());
//
//                mContext.startActivity(intent);
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}

