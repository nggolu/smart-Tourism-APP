package com.voyage;

/**
 * Created by DU on 2/24/2017.
 */

public class PlaceDetail {
    private String name;
    private String address;
    private String categ;
    private String imageLink;
    private String rating;
    private String short_des;
    private String long_des;


    public PlaceDetail() {

    }

    public PlaceDetail(String name, String address, String categ, String rating,
                       String short_des, String long_des, String lan, String lat) {
        this.name = name;
        this.address = address;
        this.categ = categ;
        this.imageLink = imageLink;
        this.rating = rating;
        this.short_des = short_des;
        this.long_des = long_des;
    }

    public String getCateg() {
        return categ;
    }

    public void setCateg(String categ) {
        this.categ = categ;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void getShortDes(String short_des){
        this.short_des = short_des;
    }

    public void getLongDes(String long_des) {
        this.long_des = long_des ;
    }
}
