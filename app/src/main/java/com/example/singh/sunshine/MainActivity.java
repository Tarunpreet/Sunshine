package com.example.singh.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button check=(Button)findViewById(R.id.id_butt);
        final EditText city=(EditText)findViewById(R.id.editText);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= String.valueOf(city.getText());
                Toast.makeText(MainActivity.this, "LOCATION NOT AVAILABLE", Toast.LENGTH_SHORT).show();
                Intent i= new Intent(MainActivity.this,WeatherActivity.class);
                startActivity(i);
            }
        });

    }
}
