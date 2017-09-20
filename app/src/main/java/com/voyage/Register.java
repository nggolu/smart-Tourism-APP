package com.voyage;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.voyage.Recommender.User;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class Register extends AppCompatActivity {

    EditText username, password, email;
    Button register;
    String input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_register);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);

        register = (Button) findViewById(R.id.email_sign_in_button);
        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        final User user = new User();
     //   final Firebase fire = new Firebase(db_url);
        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (username.getText().toString().equals("")) {
                    Toast.makeText(Register.this, "enter name ..", Toast.LENGTH_SHORT).show();
                } else if (password.getText().toString().equals("")) {
                    Toast.makeText(Register.this, "enter password...", Toast.LENGTH_SHORT).show();
                } else if (email.getText().toString().equals("")) {
                    Toast.makeText(Register.this, "enter email id...", Toast.LENGTH_SHORT).show();
                } else if (email.getText().toString().matches(emailPattern)) {
                    user.setEmail(email.getText().toString());
                    user.setPassword(password.getText().toString());
                    user.setUsername(username.getText().toString());
                    //sername = email.getText().toString().substring(0, email.getText().toString().indexOf('@'));

                    System.out.println(email.getText().toString());
                  //  fire.child(username).setValue(user);
                    input = "{\"username\":\""+username.getText().toString()+"\", \"password\":\"" + password.getText().toString() +"\", \"email_id\": \"" +email.getText().toString()+"\"}";
                    new JSONTask().execute("http://mybucketlist.me:8000/InsertUser");

                    Toast.makeText(Register.this, "registeration compelete ", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(Register.this , UserInterest.class);
                    startActivity(in);
                    finish();

                }
                else
                {
                    Toast.makeText(Register.this, "enter correct email id...", Toast.LENGTH_SHORT).show();
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
                // connection.setDoOutput(true);
                //  connection.setRequestMethod("POST");
                System.out.println(url);
                ///  connection.setRequestProperty("content-type","text/json");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                //    String input = "username"+username.getText().toString() ;

                JSONObject json = new JSONObject(input);
                OutputStreamWriter streamWriter = new OutputStreamWriter(connection.getOutputStream());
                streamWriter.write(json.toString());
                streamWriter.flush();
                StringBuilder stringBuilder = new StringBuilder();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                    InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(streamReader);
                    String response = null;
                    while ((response = bufferedReader.readLine()) != null) {
                        stringBuilder.append(response + "\n");
                    }
                    bufferedReader.close();

                    Log.d("test", stringBuilder.toString());
                    return stringBuilder.toString();
                } else {
                    Log.e("test", connection.getResponseMessage());
                    return null;
                }
            } catch (Exception exception){
                Log.e("test", exception.toString());
                return null;
            }


        }


    }


}
