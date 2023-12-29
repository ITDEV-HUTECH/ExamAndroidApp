package com.example.libs.Model;

import java.util.UUID;

public class Product {
    private UUID id;
    private String name;
    private double price;

    private String groupName;

    public UUID getID() {
        return id;
    }

    public void setID(UUID value) {
        this.id = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double value) {
        this.price = value;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String value) {
        this.groupName = value;
    }
}