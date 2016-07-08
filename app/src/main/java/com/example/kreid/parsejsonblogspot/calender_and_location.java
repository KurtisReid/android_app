package com.example.kreid.parsejsonblogspot;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class calender_and_location extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_and_location);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button cal_button = (Button) findViewById(R.id.button_post);
        Button loc_button = (Button) findViewById(R.id.cal_btn);

        loc_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //activate location activity
                Intent intent = new Intent(calender_and_location.this, MapsActivity.class);
                startActivity(intent);
            }
        });


/*        cal_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //activate calendar activity
                Log.i("Cal_and_loc", "cal bttn clk");
            }
        });*/

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
