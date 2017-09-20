package com.voyage;

import android.os.Looper;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

/**
 * Created by DU on 3/24/2017.
 */

public class EndPoints{
        public static AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        public static AsyncHttpClient syncHttpClient = new SyncHttpClient();

        private static AsyncHttpClient getClient(){
            if (Looper.myLooper()==null)
            {
                return syncHttpClient;
            }
            else
            {
                return asyncHttpClient;
            }
        }
    public static void  getSites (RequestParams requestParams,AsyncHttpResponseHandler asyncHttpResponseHandler){
        getClient().post(Config.URL_GETSITE, requestParams, asyncHttpResponseHandler);
    }
}
