//package com.shareeasy.shareeasy.data.models;
//
////import com.google.firebase.firestore.DocumentId;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Message implements Serializable {
//
////    @DocumentId
//    private String id;
//
//    private String donated_items;
//    private String userId;
//    private String locationName;
//
//    private List<String> productsList;
//    private List<String> servicesList;
//
//    private double lat;
//    private double lng;
//    private String description;
//    private String institution;
//    private boolean read;
//    private String item_type;
//
//    public Message() {
//        productsList = new ArrayList<>();
//        servicesList = new ArrayList<>();
//    }
//
//    public Message(String donated_items, String userId, boolean read) {
//        this.donated_items = donated_items;
//        this.userId = userId;
//        this.read = read;
//
//        productsList = new ArrayList<>();
//        servicesList = new ArrayList<>();
//    }
//
//    //Getters
//    public String getId() {
//        return id;
//    }
//
//    public String getDonated_items() {
//        return donated_items;
//    }
//
//    public String getLocationName() {
//        return locationName;
//    }
//
//    public double getLat() {
//        return lat;
//    }
//
//    public double getLng() {
//        return lng;
//    }
//
//    public String getItem_type() {
//        return item_type;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public String getInstitution() {
//        return institution;
//    }
//
//    public boolean isRead() {
//        return read;
//    }
//
//    public String getUserId() {
//        return userId;
//    }
//
//    public List<String> getProductsList() {
//        return productsList;
//    }
//
//    public List<String> getServicesList() {
//        return servicesList;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public void setDonated_items(String donated_items) {
//        this.donated_items = donated_items;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }
//
//    public void setLocationName(String locationName) {
//        this.locationName = locationName;
//    }
//
//    public void setItem_type(String item_type) {
//        this.item_type = item_type;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public void setInstitution(String institution) {
//        this.institution = institution;
//    }
//
//    public void setRead(boolean read) {
//        this.read = read;
//    }
//
//    public void setLocation(double lat, double lng) {
//        this.lat = lat;
//        this.lng = lng;
//    }
//
//    public void addService(String serviceId) {
//        servicesList.add(serviceId);
//    }
//
//    public void addProduct(String productId) {
//        productsList.add(productId);
//    }
//
//    public void addProducts(List<Product> products) {
//        for (Product product : products) {
//            productsList.add(product.getId());
//        }
//    }
//
//    public void addServices(List<Service> services) {
//        for (Service service : services) {
//            servicesList.add(service.getId());
//        }
//    }
//
//}
