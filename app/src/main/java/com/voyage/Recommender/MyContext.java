package com.voyage.Recommender;

import java.util.*;
public class MyContext
{
	private User user;
	private double latitude;
	private double longitude;
	private ArrayList<String> chose_category;
	public MyContext()
	{
		this.chose_category=new ArrayList<String>();
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	public ArrayList<String> getChose_category() {
		return chose_category;
	}
	public void setChose_category(ArrayList<String> chose_category) {
		this.chose_category = chose_category;
	}
}
