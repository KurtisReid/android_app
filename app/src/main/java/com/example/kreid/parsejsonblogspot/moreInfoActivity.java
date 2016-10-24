package com.example.kreid.parsejsonblogspot;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class moreInfoActivity extends AppCompatActivity {


    String TAG_type = "type";
    String TAG_effect_on_distance = "effectOnDistance";
    String TAG_title = "title";
    String TAG_when = "when";
    String TAG_institution = "institution";
    String TAG_where = "where";
    String TAG_contributes = "contributes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);


        Intent intent = getIntent();
        Log.i("moreInfoActivity:", "Im here");

        //Log.i("moreInfoActivity:", intent.getExtras().get("type").toString());

       // TextView tv = (TextView) findViewById(R.id.institiution);
       // TextView title = (TextView) findViewById(R.id.title);
        TextView type = (TextView) findViewById(R.id.type);
        /*TextView location = (TextView) findViewById(R.id.location);
        TextView contributes = (TextView) findViewById(R.id.contributes);

        title.setText(intent.getExtras().get("title").toString());
        location.setText(intent.getExtras().get("where").toString());//prints intents
        tv.setText(intent.getExtras().get("when").toString());//prints intents
        contributes.setText(intent.getExtras().get("contributes").toString());*/
        //type.setText(intent.getExtras().get("school.tuition_revenue_per_fte").toString());



        Button button = (Button) findViewById(R.id.button_post);//POST

        button.setOnClickListener(new View.OnClickListener() {//signs up
            @Override
            public void onClick(View v) {
                new putJson().execute();//post method
            }
        });




        //start here



        
    }

}
