package com.voyage;

import android.content.Context;
import android.content.Intent;

import android.os.AsyncTask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.widget.TextView;
import android.widget.Toast;

import com.voyage.Recommender.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class SignIn  extends AppCompatActivity {

    Button submit;
    EditText email, pass;
    TextView tvregister;
    String date;
    User user = new User();
    HashMap<String,String> hashMapUser = new HashMap<String, String>();;
    private String android_id;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_in);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);
        submit = (Button) findViewById(R.id.email_sign_in_button);
        tvregister = (TextView) findViewById(R.id.register_now);
        context = SignIn.this;
        new JSONTask().execute("http://mybucketlist.me:8000/getUsers");

        tvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(SignIn.this ,Register.class);
                startActivity(in);
                finish();
            }
        });

        //FacebookLogin();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().equals("")) {
                    Toast.makeText(SignIn.this, "enter email id...", Toast.LENGTH_SHORT).show();
                } else if (pass.getText().toString().equals("")) {
                    Toast.makeText(SignIn.this, "enter password...", Toast.LENGTH_SHORT).show();
                } else {

                    //  System.out.print("hashmap "+ hashMapUser);
                    String data = hashMapUser.get(email.getText().toString());
                    System.out.println("data is " + data);
                    if (data == null) {
                        Toast.makeText(context, "please enter email id ..", Toast.LENGTH_SHORT).show();
                    }
                    else if  (data.indexOf("?") != 0) {
                        String password = data.substring(0, data.indexOf("?"));

                        //System.out.println(password);
                        if (pass.getText().toString().equals(password)) {
                            Intent intent = new Intent(context, UserInterest.class);
                            startActivity(intent);
                        } else
                            Toast.makeText(context, "please enter correct password", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(context, "please try again ... ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public class JSONTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                BufferedReader reader = null;
                HttpURLConnection connection = null;

                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String finaljson = buffer.toString();
                JSONArray jarray = new JSONArray(finaljson);
                //for(int i =0;)
                 int arraylength =jarray.length();
                //System.out.println(arraylength);
                for(int i=0; i<arraylength;i++)
                {
                    JSONObject jobjects = jarray.getJSONObject(i);
                    //JSONObject jobjects = jarray.getJSONObject(0);
                    String username=jobjects.getString("username");
                    String password=jobjects.getString("password");
                    String email_id=jobjects.getString("email_id");
                    String _id=jobjects.getString("_id");
                   // System.out.println(password+email_id+username);

                    user.setEmail(email_id);
                    user.setUsername(username);
                    user.setPassword(password);
                  /*  new AppPreferences().set*/
                    hashMapUser.put(email_id,password+"?"+username+"?"+_id);
                  //  System.out.println(hashMapUser.get(email_id));

                }

                return null;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
           // jsdata.setText(s);
        }
    }

    public User User(){
        return user;
    }
}

