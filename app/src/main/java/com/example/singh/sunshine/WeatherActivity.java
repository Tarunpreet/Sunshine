package com.example.singh.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 * Created by SINGH on 10/10/17.
 */

public class WeatherActivity extends AppCompatActivity {


    TextView tempTextView;
    TextView dateTextView;
    TextView weatherDescTextView;
    TextView cityTextView;
    ImageView weatherImageView;
    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
       Intent i=getIntent();
        String city=i.getStringExtra("city");
        tempTextView = (TextView) findViewById(R.id.tempTextView);
        dateTextView = (TextView) findViewById(R.id.dateTextView);
        weatherDescTextView = (TextView) findViewById(R.id.weatherDesctextView);
        cityTextView = (TextView) findViewById(R.id.cityTextView);
        weatherImageView = (ImageView) findViewById(R.id.weatherImageView);

        dateTextView.setText(getCurrentDate());

        String baseURL = "http://api.openweathermap.org/data/2.5/weather?q=";
        String API = "&appid=763ce3ee3cba8e9c970306e78f128965";
        String url =baseURL+city+API;
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject responseObject) {
                        //tempTextView.setText("Response: " + response.toString());
                        Log.v("WEATHER", "Response: " + responseObject.toString());

                        try
                        {
                            JSONObject mainJSONObject = responseObject.getJSONObject("main");
                            JSONArray weatherArray = responseObject.getJSONArray("weather");
                            JSONObject firstWeatherObject = weatherArray.getJSONObject(0);

                            String temp = Integer.toString((int) Math.round(mainJSONObject.getDouble("temp")));
                            int temp1=Integer.parseInt(temp);
                            float temp11= (float) (temp1-272.15);
                             int temp3=(int)temp11;
                            temp=Integer.toString(temp3);
                            String weatherDescription = firstWeatherObject.getString("description");
                            String city = responseObject.getString("name");

                            tempTextView.setText(temp);
                            weatherDescTextView.setText(weatherDescription);
                            cityTextView.setText(city);

                            int iconResourceId = getResources().getIdentifier("icon_" + weatherDescription.replace(" ", ""), "drawable", getPackageName());
                            weatherImageView.setImageResource(iconResourceId);

                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });

        // Access the RequestQueue through your singleton class.
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsObjRequest);
    }

    private String getCurrentDate ()
    {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMM dd");
        String formattedDate = dateFormat.format(calendar.getTime());

        return formattedDate;
    }

    }
