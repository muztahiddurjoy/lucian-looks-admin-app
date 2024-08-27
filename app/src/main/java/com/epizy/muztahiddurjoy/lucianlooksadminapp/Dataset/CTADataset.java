package com.epizy.muztahiddurjoy.lucianlooksadminapp.Dataset;

public class CTADataset {
    String btntext;
    String description;
    String link;
    String name;
    String imageurl;

    public CTADataset(String btntext, String description, String link, String name, String imageurl) {
        this.btntext = btntext;
        this.description = description;
        this.link = link;
        this.name = name;
        this.imageurl = imageurl;
    }

    public CTADataset() {
    }

    public String getBtntext() {
        return btntext;
    }

    public void setBtntext(String btntext) {
        this.btntext = btntext;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
