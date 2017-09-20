package com.voyage;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.voyage.Recommender.User;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by DU on 1/14/2017.
 */
public class AppPreferences {
    public static final String PREF_NAME = "Voyage";

    public static final int MODE = Context.MODE_APPEND;

    public String KEY_APP_VERSION_CODE = "versionCode";

    private String KEY_USERNAMR = "userName";
    private String KEY_LOCATION="location";
    private String KEY_PASSWORD ="password";
    private String KEY_LOGGED_IN = "loggedIn";
    private String KEY_LATITUDE= "latitude";
    private String KEY_LONGITUDE= "longitude";
    private String KEY_ANDROIDID = "android_id";
    private String KEY_DATE = "date";
    private String KEY_USER = "user";
    private String KEY_INTEREST_PLACES = "interest_places";
    Set<String> key_intstPlaces = new HashSet<String>();

    private String KEY_OTP ="otp";
    private String KEY_LOAD_FRAGMENT = "keyloadfragmentRegister";
    private String KEY_TOKEN = "token";

    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, MODE);
    }
    public static SharedPreferences.Editor getEditor(Context context) {
        return getPreferences(context).edit();
    }
    public Double getLatitude(Context context) throws ParseException {
        NumberFormat nf = NumberFormat.getInstance();
        double number = nf.parse(getPreferences(context).getString(KEY_LATITUDE,"latitude")).doubleValue();
        return number;
    }
    public Double getLongitude(Context context) throws ParseException {
        NumberFormat nf = NumberFormat.getInstance();
        double number =nf.parse(getPreferences(context).getString(KEY_LONGITUDE, "longitude")).doubleValue();
        return number;
    }

    public void setLocation(Context context, String mLan,String mLat){
        getEditor(context).putString(KEY_LATITUDE,mLat).commit();
        getEditor(context).putString(KEY_LONGITUDE,mLan).commit();
    }

    public boolean getLoggedIn(Context context) {
        return getPreferences(context).getBoolean(KEY_LOGGED_IN, true);
    }

    public void setAndroidId(Context context,String id){
        getEditor(context).putString(KEY_ANDROIDID,id).commit();
    }
    public void setDate(Context context,String time){
        getEditor(context).putString(KEY_DATE,time).commit();
    }
    public void setInterestPlaces(Context context, String list)
    {
        getEditor(context).putString(KEY_INTEREST_PLACES, list).commit();

    }

    public String getInterestPlaces(Context context)
    {
        return getPreferences(context).getString(KEY_INTEREST_PLACES,"interest_places");
    }

   /* public void setUser(Context context,User user )
    {
        getEditor(context).put(KEY_USER, user).commit();

    }*/
}
