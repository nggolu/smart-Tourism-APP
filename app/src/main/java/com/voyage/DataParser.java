package com.voyage;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by DU on 3/26/2017.
 */
public class DataParser {

    public int praseLogin(Context context, JSONObject jsonObject) {
        try {
            {

                JSONArray message = jsonObject.getJSONArray("data");

                for (int i = 0; i < message.length(); i++) {

                    JSONObject temp = message.getJSONObject(i);
                    if (temp.getString("success").equals("1")) {
                       /* new AppPreferences().setHotCode(context, temp.getString("hotcode"));
                        return Integer.parseInt(temp.getString("hotcode"));*/
                        return 0;
                    } else if (temp.getString("success").equals("0")) {
                        return -3698;
                    } else {
                        return -3698;
                    }
                }

            }

            return -3698;


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
