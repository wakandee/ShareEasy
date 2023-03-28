//package com.shareeasy.shareeasy.data.repositories;
//
//import android.util.Log;
//
//import com.shareeasy.shareeasy.data.models.Message;
//
////import com.google.firebase.firestore.FirebaseFirestore;
////import com.shareeasy.shareeasy.data.models.Message;
//
//public class MessageRepository {
//
////    private FirebaseFirestore mDatabase;
//    private MessageTaskListener listener;
//    private static final String TAG = "MessageRepository";
//
//    public MessageRepository(MessageTaskListener listener) {
//        this.listener = listener;
////        mDatabase = FirebaseFirestore.getInstance();
//    }
//
//    public void add(Message message) {
////        mDatabase.collection("messages")
////                .add(message)
////                .addOnCompleteListener(task -> {
////
////                    if (task.isSuccessful()) {
////                        Log.d(TAG, "add: message added in repository");
////                        listener.onComplete("Request Sent Successfully");
////
////                    } else {
////                        Log.e(TAG, "add: operation failed", task.getException());
////                        listener.onError(task.getException());
////
////                    }
////
////                });
//    }
//
//
//    public interface MessageTaskListener {
//
//        void onComplete(String message);
//
//        void onError(Exception exception);
//    }
//}
