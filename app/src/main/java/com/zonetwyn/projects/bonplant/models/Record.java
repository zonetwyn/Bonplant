package com.zonetwyn.projects.bonplant.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Record {

    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("quantity")
    @Expose
    private double quantity;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("price")
    @Expose
    private double price;
    @SerializedName("product")
    @Expose
    private Product product;

    public Record() {

    }

    public Record(double quantity, String unit, double price, Product product) {
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
        this.product = product;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
