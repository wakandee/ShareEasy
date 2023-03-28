package com.shareeasy.shareeasy.ui.products;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.shareeasy.shareeasy.R;
public class DetailedProductsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_products);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        ImageView mImage = findViewById(R.id.image_view);
        TextView mDescription = (TextView) findViewById(R.id.product_description);
        TextView mTitle = (TextView) findViewById(R.id.product_title);

        // Setting up action bar
        //setSupportActionBar(mToolbar);
        ActionBar mActionBar = getSupportActionBar();
        assert mActionBar != null;
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_chevron_left_black_24dp));

        // Catching incoming intent
        Intent intent = getIntent();
        String title = intent.getStringExtra("item");
        String item_type = intent.getStringExtra("item_type");
        String item_date = intent.getStringExtra("item_date");
        String item_status = intent.getStringExtra("item_status");
        String description = intent.getStringExtra("food_description");


        //Toast.makeText(getApplicationContext(), title, Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(), description, Toast.LENGTH_LONG).show();


        mActionBar.setTitle(title);
        mTitle.setText(title);
        mDescription.setText(description);


    }
}
