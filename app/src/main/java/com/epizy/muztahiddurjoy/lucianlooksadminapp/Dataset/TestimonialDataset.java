package com.epizy.muztahiddurjoy.lucianlooksadminapp.Dataset;

public class TestimonialDataset {
    String name;
    String desc;
    String date;

    public TestimonialDataset(String name, String desc, String date) {
        this.name = name;
        this.desc = desc;
        this.date = date;
    }

    public TestimonialDataset() {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
