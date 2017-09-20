package com.voyage.Recommender;

import java.io.Serializable;

public class Sites implements Serializable
{
	private String address;
	private String hours;
	private String long_des;
	private String name;
	private String phone;
	private Double rating;
	private int reivewers;
	private String short_des;
	private String category;
	private double latitude;
	private double longitude;
	private int site_id;
	private String imageLink;
	public Sites(){

}

	public Sites(String hours, String address, String long_des, String name, String phone,
				 Double rating, int reivewers, String short_des, String category, double latitude, double longitude, int site_id) {
		this.hours = hours;
		this.address = address;
		this.long_des = long_des;
		this.name = name;
		this.phone = phone;
		this.rating = rating;
		this.reivewers = reivewers;
		this.short_des = short_des;
		this.category = category;
		this.latitude = latitude;
		this.longitude = longitude;
		this.site_id = site_id;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	public String getLong_des() {
		return long_des;
	}
	public void setLong_des(String long_des) {
		this.long_des = long_des;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public int getReivewers() {
		return reivewers;
	}
	public void setReivewers(int reivewers) {
		this.reivewers = reivewers;
	}
	public String getShort_des() {
		return short_des;
	}
	public void setShort_des(String short_des) {
		this.short_des = short_des;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public int getSite_id() {
		return site_id;
	}
	public void setSite_id(int site_id) {
		this.site_id = site_id;
	}
	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

}
