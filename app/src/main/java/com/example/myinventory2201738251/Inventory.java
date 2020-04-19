package com.example.myinventory2201738251;

public class Inventory {
    String id;


    String name;
    String desc;
    String quantity;

    public Inventory (String name, String desc, String qty, String id){
        this.name = name;
        this.desc = desc;
        this.quantity = qty;
        this.id= id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
