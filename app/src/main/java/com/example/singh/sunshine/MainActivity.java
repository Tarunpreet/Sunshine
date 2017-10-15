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
        final EditText city1=(EditText)findViewById(R.id.editText);
//        city1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                city1.setText("");
//            }
//        });
       check.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                String city= String.valueOf(city1.getText());
                Toast.makeText(MainActivity.this, "LOADING.....", Toast.LENGTH_SHORT).show();
                Intent i= new Intent(MainActivity.this,WeatherActivity.class);
                i.putExtra("city",city);
                startActivity(i);
         }
        });

    }
}
