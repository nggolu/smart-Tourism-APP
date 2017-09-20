package com.voyage;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.util.Base64;
import android.util.Log;

import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class SplashScreen extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    private static final int SPLASH_TIMER = 5000;
    private static final int MY_PERMISSIONS_REQUEST = 123;
    protected static final int REQUEST_CHECK_SETTINGS = 0x1;
    private long startTimeMillis = 0;
    boolean settingStatus ;
    Context context;
    String date;
    String permissions[];
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    String bestProvider;
    Location location;
    private String android_id;
    private View mLayout;
    String mLat, mLan;
    GoogleApiClient mGoogleApiClient;
    long delayMillis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mLayout = findViewById(R.id.splash_screen);
        context = SplashScreen.this;
        settingStatus = false;
        android_id = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        date = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        startTimeMillis = System.currentTimeMillis();

        // TimerStart();
        if(!isNetworkOnline(SplashScreen.this) )
        {
            delayMillis = SPLASH_TIMER + startTimeMillis;
            Snackbar.make(mLayout, "Seems no internet Connectivity, please check ur connection  ",
                    Snackbar.LENGTH_INDEFINITE).show();
        }
        else if(settingsRequest()) {
            delayMillis = SPLASH_TIMER * 100;
        }

    }



    private void startNextActivity() {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
            }
        });
         delayMillis = SPLASH_TIMER - (System.currentTimeMillis() - startTimeMillis);
        if (delayMillis < 0) {
            delayMillis = 0;
        }

            new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (new AppPreferences().getLoggedIn(SplashScreen.this)) {
                    Intent intent;

                    intent = new Intent(SplashScreen.this, SignIn.class);

                    AppPreferences onSplash = new AppPreferences();

                    onSplash.setLocation(SplashScreen.this, mLan, mLat);
               /* onSplash.setAndroidId(SplashScreen.this, android_id);
                onSplash.setDate(SplashScreen.this, date);
*/
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(SplashScreen.this, "sorry there is some error !!", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        }, delayMillis);
    }





    @TargetApi(Build.VERSION_CODES.M)
    public void onLocationChanged(Location location) {
        if (location == null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            Location mLastLocation = LocationServices.FusedLocationApi
                    .getLastLocation(mGoogleApiClient);
            mLat = String.valueOf(mLastLocation.getLatitude());
            mLan = String.valueOf(mLastLocation.getLongitude());
       }
        else {
           mLat = String.valueOf(location.getLatitude());
           mLan = String.valueOf(location.getLongitude());

       }
    }
    public static boolean isNetworkOnline(Context con)
    {
        boolean status = false;
        try
        {
            ConnectivityManager cm = (ConnectivityManager) con
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);

            if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                status = true;
            } else {
                netInfo = cm.getNetworkInfo(1);

                if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                    status = true;
                } else {
                    status = false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return status;
    }


   LocationRequest mLocationRequest;

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            return;
        }
        else {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
            if(!settingStatus)
                startNextActivity();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public boolean settingsRequest()
    {

        mGoogleApiClient = new GoogleApiClient.Builder(SplashScreen.this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(SplashScreen.this)
                .addOnConnectionFailedListener(SplashScreen.this)
                .build();
        mGoogleApiClient.connect();

        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(30 * 1000);
        mLocationRequest.setFastestInterval(5 * 1000);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);
        builder.setAlwaysShow(true); //this is the key ingredient

        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                final LocationSettingsStates state = result.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can initialize location
                        // requests here.
                        settingStatus = false;
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied. But could be fixed by showing the user
                        // a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            settingStatus = true;
                            status.startResolutionForResult(SplashScreen.this, REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have no way to fix the
                        // settings so we won't show the dialog.
                        settingStatus = false;
                        break;
                }
            }
        });
        return settingStatus;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
// Check for the integer request code originally supplied to startResolutionForResult().
            case REQUEST_CHECK_SETTINGS:

                switch (resultCode) {
                    case Activity.RESULT_OK:
                        delayMillis = 3000;
                        settingStatus = false;
                        startNextActivity();
                        break;
                    case Activity.RESULT_CANCELED:
                        settingsRequest();//keep asking if imp or do whatever
                        break;
                }
                break;
        }
    }

}
