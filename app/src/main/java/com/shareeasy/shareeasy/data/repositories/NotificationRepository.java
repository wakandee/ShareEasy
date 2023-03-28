package com.shareeasy.shareeasy.data.repositories;

//import com.google.firebase.firestore.FirebaseFirestore;
//import com.project.ShareEasy.data.models.Notification;

import java.util.List;

public class NotificationRepository {

    private NotificationTaskListener listener;
//    private FirebaseFirestore mDb = FirebaseFirestore.getInstance();

    public NotificationRepository(NotificationTaskListener listener) {
        this.listener = listener;
    }

    public void getByUserId(String userId) {
//        mDb.collection("users").document(userId).collection("notifications")
//                .addSnapshotListener((queryDocumentSnapshots, e) -> {
//                    if (e != null) {
//
//                        if (listener != null) listener.onError(e);
//                    }
//
//                    assert queryDocumentSnapshots != null;
//
//                    if (listener != null)
//                        listener.onGetAll(queryDocumentSnapshots.toObjects(Notification.class));
//                });
    }

    public void deregisterListener() {
        listener = null;
    }


    public interface NotificationTaskListener {
//        void onGetAll(List<Notification> notifications);

        void onError(Exception e);
    }
}
