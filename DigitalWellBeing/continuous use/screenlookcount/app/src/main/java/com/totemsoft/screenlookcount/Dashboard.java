package com.totemsoft.screenlookcount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Dashboard extends AppCompatActivity {

    CardView continuoususe,pickups,averageuse,longestoffscreen,lastdropoff,firstpickup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        continuoususe=findViewById(R.id.continuoususe);
        pickups=findViewById(R.id.pickups);
        averageuse=findViewById(R.id.averageuse);
        longestoffscreen=findViewById(R.id.Longestoffscreen);
        lastdropoff=findViewById(R.id.lastdropoff);
        firstpickup=findViewById(R.id.firstpickup);


        continuoususe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(),ActivityMain.class);
                startActivity(intent);

            }
        });

        pickups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(),Pickups.class);
                startActivity(intent);

            }
        });

        averageuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(),AverageUse.class);
                startActivity(intent);

            }
        });

        longestoffscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(),Longestoffscreen.class);
                startActivity(intent);

            }
        });

        lastdropoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(),LastDropOff.class);
                startActivity(intent);

            }
        });

        firstpickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(),FirstPickupActivity.class);
                startActivity(intent);

            }
        });

    }
}