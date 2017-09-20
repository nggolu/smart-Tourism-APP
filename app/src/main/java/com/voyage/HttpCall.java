package com.voyage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import static com.facebook.internal.CallbackManagerImpl.RequestCodeOffset.Login;


/**
 * Created by DU on 3/26/2017.
 */

public class HttpCall {
    int result;
    ShowProgressDialog showProgressDialog;

    private void defineDialog(Context context) {
        showProgressDialog = new ShowProgressDialog(context);
    }
   /* public void login(final MyContext context, String username, String password, final String activity) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("username", username);
        requestParams.put("password", password);

        defineDialog(context);


        EndPoints.login(requestParams, new JsonHttpResponseHandler() {

                    @Override
                    public void onStart() {
                        showProgressDialog.show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                        showProgressDialog.dismiss();

                        result = new DataParser().praseLogin(context,
                                response);
                        if (result != -3698) {
                            Toast.makeText(context, "Successfully Logged In", Toast.LENGTH_SHORT);
                            new AppPreferences().setLoggedIn(context, true);

                            Intent intent = new Intent(context, HomeActivity.class);
                            new HttpCall().addNotificationToken(context, new AppPreferences().getToken(context));
                            new HttpCall().getToolDetails(context, intent, activity,
                                    new AppPreferences().getloadFragRegister(context));
                            *//**//* Intent intent = new Intent(context, HomeActivity.class);
                            context.startActivity(intent);
                            ((SpPRe) context).finish();*//**//*

                        } else {
                            Toast.makeText(context, "Login failed Please try again later", Toast.LENGTH_SHORT).show();

                        }


                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers,
                                          Throwable throwable, JSONObject errorResponse) {
                        //Log.e("error", errorResponse.toString() + " vishwesh");
                        showProgressDialog.dismiss();
                    }

                }
        );

        return;
    }*/
/*

    EndPoints.sentReviews(requestParams, new JsonHttpResponseHandler() {

        @Override
        public void onStart() {
            showProgressDialog.show();
            //progressDialog.show();
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

            // progressDialog.dismiss();

            showProgressDialog.dismiss();
            result = new DataParser().praseAddAddress(context,
                    response);
            if (result == 1) {
                Toast.makeText(context, "Successfully added new address", Toast.LENGTH_SHORT);


            } else {
                Toast.makeText(context, "Can't add your new address. Please try again later", Toast.LENGTH_SHORT).show();

            }

        }

        @Override
        public void onFailure(int statusCode, Header[] headers,
                Throwable throwable, JSONObject errorResponse) {
            //Log.e("error", errorResponse.toString() + " vishwesh");
            // progressDialog.dismiss();
            showProgressDialog.dismiss();
        }

    }
    );


    public void sentReview(final MyContext context, ModelAddress modelAddress, String addressId) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("HotCode", new AppPreferences().getHotCode(context));
        requestParams.put("area", modelAddress.cityArea);
        requestParams.put("roomNo", modelAddress.roomNo);
        requestParams.put("address", modelAddress.address);
        requestParams.put("addressId", addressId);


        defineDialog(context);


        EndPoints.editAddress(requestParams, new JsonHttpResponseHandler() {

                    @Override
                    public void onStart() {
                        showProgressDialog.show();
                        //progressDialog.show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                        // progressDialog.dismiss();

                        showProgressDialog.dismiss();
                        result = new DataParser().praseAddAddress(context,
                                response);
                        if (result == 1) {
                            Toast.makeText(context, "Successfully edited address", Toast.LENGTH_SHORT);


                        } else {
                            Toast.makeText(context, "Can't edit your new address. Please try again later", Toast.LENGTH_SHORT).show();

                        }


                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers,
                                          Throwable throwable, JSONObject errorResponse) {
                        //Log.e("error", errorResponse.toString() + " vishwesh");
                        // progressDialog.dismiss();
                        showProgressDialog.dismiss();
                    }

                }
        );

        return;
    }



    public void register(final MyContext context, String username, String password,
                         String name, String phone) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("username", username);
        requestParams.put("password", password);
        requestParams.put("name", name);
        requestParams.put("phone", phone);


        defineDialog(context);

        EndPoints.register(requestParams, new JsonHttpResponseHandler() {

                    @Override
                    public void onStart() {
                        showProgressDialog.show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                        showProgressDialog.dismiss();

                        result = new DataParser().praseRegister(context,
                                response, false);
                        if (result == 1) {
                            // Toast.makeText(context, "Successfully registerd", Toast.LENGTH_SHORT);
                           *//* *//*

                            new HttpCall().addNotificationToken(context, new AppPreferences().getToken(context));
                            new HttpCall().getToolDetails(context, new Intent(context, HomeActivity.class), "sppre", false);


                        } else {
                            new AppPreferences().setHotCode(context, "hotcode");
                            new AppPreferences().setToken(context, "token");
                            Toast.makeText(context, "Registration failed Please try again later",
                                    Toast.LENGTH_SHORT).show();

                        }


                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers,
                                          Throwable throwable, JSONObject errorResponse) {
                        //Log.e("error", errorResponse.toString() + " vishwesh");
                        new AppPreferences().setHotCode(context, "hotcode");
                        new AppPreferences().setToken(context, "token");
                        showProgressDialog.dismiss();
                    }

                }
        );

        return;
    }
    public void downloadMenuSorting(final MyContext context) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("city", new AppPreferences().getCity(context));

        // defineDialog(context);


        EndPoints.downloadSitesList(requestParams, new JsonHttpResponseHandler() {

                    @Override
                    public void onStart() {
                        //showProgressDialog.show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                        // showProgressDialog.dismiss();

                        new DataParser().praseDownloadSitesList(context,
                                response);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers,
                                          Throwable throwable, JSONObject errorResponse) {

                        Toast.makeText(context, "Please try again later ", Toast.LENGTH_SHORT).show();
                    }

                }
        );


    }
    public void checkUserName(final MyContext context, String username,
                              final EditText userName, final Button next) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("username", username);

        EndPoints.checkUserName(requestParams, new JsonHttpResponseHandler() {

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {



                        result = new DataParser().praseCheckUserName(context, response);
                        if (result == 0 && !userName.getText().toString().contains(" ")) {
                            *//*Toast.makeText(context, "Data Updated", Toast.LENGTH_SHORT).show();*//*
                            next.setVisibility(View.VISIBLE);
                            userName.setError(null);

                        } else if (result == 1) {
                            *//*Toast.makeText(context, "No updates", Toast.LENGTH_SHORT).show();*//*
                            next.setVisibility(View.GONE);
                            userName.setError("Username Already Registered");
                        } else if (userName.getText().toString().contains(" ")) {
                            userName.setError("Username should not contain spaces");
                            next.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        //Log.e("error", errorResponse.toString() + " vishwesh");
                    }
                }
        );
    }
    public void forgetPassword(final MyContext context, String s, final boolean b) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("username", s);

        defineDialog(context);

        EndPoints.forgotPassword(requestParams, new JsonHttpResponseHandler() {

                    @Override
                    public void onStart() {
                        showProgressDialog.show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                        showProgressDialog.dismiss();


                        result = new DataParser().praseForgotPassword(context, response);
                        if (result == 0) {
                            Toast.makeText(context, "Please try again later", Toast.LENGTH_SHORT).show();

                            Class fragmentClass = Login.class;
                            Fragment fragment = null;
                            try {
                                fragment = (Fragment) fragmentClass.newInstance();
                                //   new AppPreferences().setOtp(context, String.valueOf(result));
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            ((SpPRe) context).getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.content_fragment, fragment).commit();
                        } else {
                            //  new AppPreferences().setOtp(context, String.valueOf(result));
                            Class fragmentClass = Otp.class;
                            Fragment fragment = null;
                            try {
                                fragment = (Fragment) fragmentClass.newInstance();
                                Bundle bundle = new Bundle();
                                bundle.putString("forward", "toResetPassword");
                                bundle.putBoolean("permission", b);
                                bundle.putString("otp", String.valueOf(result));
                                fragment.setArguments(bundle);
                            } catch (java.lang.InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            ((SpPRe) context).getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.content_fragment, fragment).commit();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                                          JSONObject errorResponse) {
                        //Log.e("error", errorResponse.toString() + " vishwesh");
                        showProgressDialog.dismiss();
                        Toast.makeText(context, "Please try again later", Toast.LENGTH_SHORT).show();

                        Class fragmentClass = SignIn.class;
                        Fragment fragment = null;
                        try {
                            fragment = (Fragment) fragmentClass.newInstance();
                            // new AppPreferences().setOtp(context, String.valueOf(result));
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        ((SpPRe) context).getSupportFragmentManager().beginTransaction()
                                .replace(R.id.content_fragment, fragment).commit();

                    }
                }
        );
    }*/

}
