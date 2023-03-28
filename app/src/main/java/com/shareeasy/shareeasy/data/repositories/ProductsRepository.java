package com.shareeasy.shareeasy.data.repositories;

import android.util.Log;

//import com.google.firebase.firestore.FirebaseFirestore;
import com.shareeasy.shareeasy.data.models.Product;

import java.util.List;

public class ProductsRepository {
    private static final String TAG = "ProductsRepository";

//    private FirebaseFirestore mDatabase;

    private ProductTaskListener taskListener;

    public ProductsRepository(ProductTaskListener taskListener) {
//        mDatabase = FirebaseFirestore.getInstance();
        this.taskListener = taskListener;
    }

    public void getProducts() {
//        "Product{" +
//                "id='" + id + '\'' +
//                ", isChecked=" + isChecked +
//                ", name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                '}'
        List<Product> products;
        //products = doc(products, "id", "isChecked", "name", "description");
//        mDatabase.collection("products");
//                .addSnapshotListener((queryDocumentSnapshots, e) -> {
//
//                    if (e != null) {
//                        Log.e(TAG, "getProducts: Products", e);
//                        assert taskListener != null;
//                        taskListener.showError(e.getLocalizedMessage());
//                        return;
//                    }

                    //Log.d(TAG, "getProducts: Successful we are good to go => count: " + products.size());

                    //taskListener.showProducts(queryDocumentSnapshots.toObjects(Product.class));

                //});
    }

    private List<Product> doc(List<Product> products, String id, String roomA, String messages, String message1) {
        List<Product> doc = doc(products, "1", "roomA", "messages", "message1 description");
                return doc;
    }

    public interface ProductTaskListener {

        void showProducts(List<Product> products);

        void showError(String message);
    }
}
