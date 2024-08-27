package com.epizy.muztahiddurjoy.lucianlooksadminapp.Dataset;

public class ProReportDataset {
    String name;
    String details;
    String prodes;
    String proimage;
    String proname;
    String proprice;
    String prowner;

    public ProReportDataset() {

    }

    public ProReportDataset(String name, String details, String prodes, String proimage, String proname, String proprice, String prowner) {
        this.name = name;
        this.details = details;
        this.prodes = prodes;
        this.proimage = proimage;
        this.proname = proname;
        this.proprice = proprice;
        this.prowner = prowner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getProdes() {
        return prodes;
    }

    public void setProdes(String prodes) {
        this.prodes = prodes;
    }

    public String getProimage() {
        return proimage;
    }

    public void setProimage(String proimage) {
        this.proimage = proimage;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public String getProprice() {
        return proprice;
    }

    public void setProprice(String proprice) {
        this.proprice = proprice;
    }

    public String getProwner() {
        return prowner;
    }

    public void setProwner(String prowner) {
        this.prowner = prowner;
    }
}
