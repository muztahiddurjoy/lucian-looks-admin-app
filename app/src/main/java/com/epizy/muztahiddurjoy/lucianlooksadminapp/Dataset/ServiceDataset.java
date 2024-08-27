package com.epizy.muztahiddurjoy.lucianlooksadminapp.Dataset;

public class ServiceDataset {
    String name;
    String description;
    String id;
    String image;
    String owner;
    String price;

    public ServiceDataset() {
    }

    public ServiceDataset(String name, String description, String id, String image, String owner, String price) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.image = image;
        this.owner = owner;
        this.price = price;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
}
