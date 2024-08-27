package com.epizy.muztahiddurjoy.lucianlooksadminapp.Dataset;

public class ServiceReportDataset {
    String name;
    String description;
    String serdes;
    String serid;
    String serimage;
    String sername;
    String serowner;
    String serprice;

    public ServiceReportDataset() {
    }

    public ServiceReportDataset(String name, String description, String serdes, String serid, String serimage, String sername, String serowner, String serprice) {
        this.name = name;
        this.description = description;
        this.serdes = serdes;
        this.serid = serid;
        this.serimage = serimage;
        this.sername = sername;
        this.serowner = serowner;
        this.serprice = serprice;
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

    public String getSerdes() {
        return serdes;
    }

    public void setSerdes(String serdes) {
        this.serdes = serdes;
    }

    public String getSerid() {
        return serid;
    }

    public void setSerid(String serid) {
        this.serid = serid;
    }

    public String getSerimage() {
        return serimage;
    }

    public void setSerimage(String serimage) {
        this.serimage = serimage;
    }

    public String getSername() {
        return sername;
    }

    public void setSername(String sername) {
        this.sername = sername;
    }

    public String getSerowner() {
        return serowner;
    }

    public void setSerowner(String serowner) {
        this.serowner = serowner;
    }

    public String getSerprice() {
        return serprice;
    }

    public void setSerprice(String serprice) {
        this.serprice = serprice;
    }
}
