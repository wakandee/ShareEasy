package com.shareeasy.shareeasy.data.models;

public class Product {
    private String item,item_type,item_date, item_status,food_description;

    public Product (String item,String item_type, String item_date, String item_status, String food_description){
        this.item = item;
        this.item_type = item_type;
        this.item_date = item_date;
        this.item_status= item_status;
        this.food_description = food_description;

    }

    public void setitem(String item) {
        this.item = item;
    }
    public String getitem() {
        return item;
    }
    public void setitem_type(String item_type) {
        this.item_type = item_type;
    }
    public String getitem_type() {
        return item_type;
    }


    public void setitem_date(String item_date) {
        this.item_date = item_date;
    }
    public String getitem_date(){
        return item_date;
    }

    public void setitem_status(String item_status) { this.item_status = item_status; }
    public String getitem_status(){
        return item_status;
    }

    public String getfood_description() {
        return food_description;
    }

    public void setfood_description(String food_description) {
        this.food_description = food_description;
    }
}
