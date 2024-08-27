package com.epizy.muztahiddurjoy.lucianlooksadminapp.Dataset;

public class ProductDataset {
    String name;
    String description;
    String owner;
    String price;
    String imageurl;
    String id;
    String category;

    public ProductDataset() {
    }

    public ProductDataset(String name, String description, String owner, String price, String imageurl, String id, String category) {
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.price = price;
        this.imageurl = imageurl;
        this.id = id;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
