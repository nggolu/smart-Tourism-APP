package com.voyage.Recommender;

import java.util.*;
public class RecommenderSystem
{
	private HashMap<String,User> UserList;
	private HashMap<Integer,Sites> SiteList;
	private MyContext myContext;
	public RecommenderSystem()
	{
		UserList=new HashMap<String,User>();
		SiteList=new HashMap<Integer,Sites>();
	}
	public HashMap<String, User> getUserList() {
		return UserList;
	}
	public void setUserList(HashMap<String, User> userList) {
		UserList = userList;
	}
	public HashMap<Integer, Sites> getSiteList() {
		return SiteList;
	}
	public void setSiteList(HashMap<Integer, Sites> siteList) {
		SiteList = siteList;
	}
	public MyContext getContext() {
		return myContext;
	}
	public void setContext(MyContext context) {
		this.myContext = context;
	}
	public double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}
	public double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
	public double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == "K") {
			dist = dist * 1.609344;
		} else if (unit == "N") {
			dist = dist * 0.8684;
		}

		return (dist);
	}
	public ArrayList<Sites> newUserRecommendation()
	{
		ArrayList<Pair> sort_list=new ArrayList<Pair>();
		for(Integer i:this.getSiteList().keySet())
		{
			Pair p=new Pair();
			p.setSite(this.SiteList.get(i));
			p.setDiff(this.distance(this.SiteList.get(i).getLatitude(), this.SiteList.get(i).getLongitude(), this.myContext.getLatitude(), this.myContext.getLongitude(), "K"));
			//System.out.println(p.getDiff());
			sort_list.add(p);
		}
		Collections.sort(sort_list,new Comparator<Pair>(){
			
			public int compare(Pair a,Pair b)
			{
				if(a.getDiff()<b.getDiff())
					return -1;
				else
					return 1;
			}
			
		});
		ArrayList<Sites> recommendedList=new ArrayList<Sites>();
		for(Pair i:sort_list)
		{
			for(String j:this.myContext.getChose_category())
			{
				if(j.equals(i.getSite().getCategory()))
				{
					recommendedList.add(i.getSite());
					break;
				}
			}
		}
		for(Pair i:sort_list)
		{
			if(recommendedList.contains(i.getSite())==false)
			{
				recommendedList.add(i.getSite());
			}
		}
		/*for(Sites i:recommendedList)
		{
			//System.out.println(i.getName());
		}*/
		return recommendedList;
		
	}
	
}
