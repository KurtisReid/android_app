package com.example.kreid.parsejsonblogspot;

import android.content.Intent;
import android.os.Bundle;
import android.os. AsyncTask;
import com.example.kreid.parsejsonblogspot.moreInfoActivity;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/*
Get and post methods
*todo to get ready for mobile:
* change string tags to match reccomendation parameters
 */

/*


 */
public class MainActivity extends Activity {
    private static String urlString;
    private static String nameString;
    String TAG_type = "school.name";
    String TAG_effect_on_distance = "effectOnDistance";
    String TAG_title = "title";
    String TAG_when = "when";
    String TAG_institution = "institution";
    String TAG_where = "where";
    String TAG_contributes = "contributes";
    boolean test = false;


    ArrayList<HashMap<String, String>> contactList; // hashmap where we will store the incoming data



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HashMap<String, String> contact = new HashMap<String, String>();
        contactList = new ArrayList<HashMap<String, String>>();




        //final TextView tv = (TextView) findViewById(R.id.tv);
        //final TextView tv2 = (TextView) findViewById(R.id.tv2);
        Button btn = (Button) findViewById(R.id.btn);//GET
        //Button loc_cal = (Button) findViewById(R.id.loc_cal);//POST



        urlString = "http://10.0.2.2:8081/getSchoolPrice";
        nameString = "http://10.0.2.2:8081/getSchoolName";
        Log.i("mainActivity", "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        contact.put(TAG_title, "this is the title");// adds information to contact
        contactList.add(contact);//puts contact into hashmap

        //Log.i("MainActivity kkkkkkkk", contactList.toString());

        ListAdapter adapter = new SimpleAdapter(
                MainActivity.this, contactList,
                R.layout.list_item, new String[] { TAG_title, TAG_type,
                TAG_where }, new int[] { R.id.name,
                R.id.type, R.id.location });

        ListView myList=(ListView)findViewById(android.R.id.list);

        myList.setAdapter(adapter);



        //listens for list object to be clicked
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Log.i("mainActivity:", "good button clicked");
                Intent intent = new Intent(MainActivity.this, moreInfoActivity.class);
                new ProcessJSON().execute(urlString); //get method

                intent.putExtra(TAG_type, contactList.get(0).get(TAG_type));


                startActivity(intent);
            }
        });


       /* btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       new ProcessJSON().execute(urlString); //get method
                                       //post info to list

                                       //Log.i("MainActivity HHHHHHHHHH", contactList.toString());


                                   }
                               });*/

        //new ProcessJSON().execute(nameString);





        /*btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //starts another activity
                Log.i("mainActivity:", "button clicked");
                Log.i("mainActivity:", "good button clicked");
                Intent intent = new Intent(MainActivity.this, moreInfoActivity.class);


                intent.putExtra(TAG_type, contactList.get(0).get(TAG_type));


                startActivity(intent);




                //Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                //startActivity(intent);

                //new putJson().execute();//post method
            }
        });*/
/*
        loc_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //starts another activity
    //            Log.i("mainActivity:", "loc_cal button clicked");
  //              Intent intent = new Intent(MainActivity.this, calender_and_location.class);
//                startActivity(intent);
                Log.i("mainActivity:", "good button clicked");
                Intent intent = new Intent(MainActivity.this, moreInfoActivity.class);


                intent.putExtra(TAG_type, contactList.get(0).get(TAG_type));


                startActivity(intent);

                //new putJson().execute();//post method

            }
        });
*/







        //Log.i("mainActivity:", "LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
        //Log.i("mainActivity:", contactList.get(0).get(TAG_title));


        /*myList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //starts another activity

                Intent intent = new Intent(this, moreInfoActivity.class);
                intent.putExtra(TAG_title, contactList.get(0).get(TAG_title));
                intent.putExtra(TAG_where, contactList.get(0).get(TAG_where));
                intent.putExtra(TAG_type, contactList.get(0).get(TAG_type));


                //new putJson().execute();//post method
            }
        });*/











    }



    private class ProcessJSON extends AsyncTask<String, Void, String>{
        protected String doInBackground(String... strings){
            String stream = null;
            String urlString = strings[0];
            Log.i("MainActivity", "ProcessJSON!!!!!!!!!!!!!!!!!!!!!!!!");
            HTTPDataHandler hh = new HTTPDataHandler();
            stream = hh.GetHTTPData(urlString);

            //Log.i("MainActivity", stream);


            // Return the data from specified url
            return stream;
        }

        protected void onPostExecute(String stream){
            TextView tv = (TextView) findViewById(R.id.tv);


                tv.setText(stream);

            HashMap<String, String> contact = new HashMap<String, String>();



            //..........Process JSON DATA................
            if(stream != null){
                try {
                    // Get the full HTTP Data as JSONObject

                    JSONArray reader = new JSONArray(stream);
                    Log.i("mainActivity", String.valueOf(reader.length()));
                    String arr[] = new String[17];
                    int j = 0;
                    for (int i = 0; i < 2; i++) {

                    Log.i("mainActivity", reader.toString());
                    //Log.i("mainActivity", String.valueOf(i));
                    JSONObject object = reader.getJSONObject(i);
                    JSONArray object1;
                        Log.i("mainActivity", "HELLO WHEREEVER YOU ARE");
                        Log.i("mainActivity", object.toString());


                    //Log.i("mainActivity", "jsonObject waetherArray passed !!!!!!!!!!!!!!!!!!");
                    //JSONObject weather_object_0 = weatherArray.getJSONObject(1);
                        tv.setText(object.getString("name"));
                        Log.i("mainActivity", "HELLO WHEREEVER YOU ARE");
                        Log.i("mainActivity",object.getString("school.name"));

                    arr[j] = object.getString("school.name");//finds the string
                        contact.put(TAG_effect_on_distance, arr[j]);
                    Log.i("mainActivity", "effect_onDistance"+arr[j]);

                    arr[j] = object.getString(TAG_title);
                        contact.put(TAG_title, arr[j]);
                    Log.i("mainActivity", "title"+arr[j]);
                        j++;
                    arr[j] = object.getString(TAG_type);
                        contact.put(TAG_type, arr[j]);
                    Log.i("mainActivity", "type"+arr[j]);
                        j++;
                    arr[j] = object.getString(TAG_when);
                        contact.put(TAG_when, arr[j]);
                    Log.i("mainActivity", arr[j]);
                        j++;
                    //arr[j] = object.getString(TAG_institution);
                    //Log.i("mainActivity", arr[j]);
                    //    j++;
                    arr[j] = object.getString(TAG_where);
                        contact.put(TAG_where, arr[j]);
                    Log.i("mainActivity", arr[j]);
                        j++;
                    arr[j] = object.getString(TAG_contributes);
                        contact.put(TAG_contributes, arr[j]);
                    Log.i("mainActivity", arr[j]);
                        j++;


                        contactList.add(contact);//adds to arraylist
                        Log.i("MainActivity jjjj", contact.toString());
                        //tv.setText((CharSequence) contact.toString());
                        //Log.i("mainActivity:", "LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
                        //Log.i("mainActivity:", contactList.get(0).get(TAG_title));


                    /*
                        String effectOnDistance = object.getString(TAG_effect_on_distance);//finds the string
                        String title = object.getString(TAG_title);
                        String type = object.getString(TAG_type);
                        String when = object.getString(TAG_when);
                        String institution = object.getString(TAG_institution);
                        String where = object.getString(TAG_where);
                        String contributes = object.getString(TAG_contributes);*/
                }
                    //tv.setText(tv.getText());
                    /*
                    String label[] = {"title: ", "type: ", "when", "where...", "contributes: "};
                    tv.append("\t\t\teffectOnDistance..." + arr[0] + "\n");

                    for (int k = 0; k < 5; k++){
                        tv.append("\t\t\t" + label[k] + arr[k] + "\n");
                        Log.i("mainActivity",label[k] + arr[k]);


                    }
                    for (int k = 0; k < 5; k++){
                        tv2.append("\t\t\t" + label[k] + arr[k+5] + "\n");
                        Log.i("mainActivity","tv2  " + label[k] + arr[k+5]);


                    }
*/
                    //tv.setText(tv.getText() + "\t\t\t" + stream);

                        // process other data as this way..............
                    //}




                }catch(JSONException e){
                    e.printStackTrace();
                }

            } // if statement end
            else
            {
                Log.i("MainActivity", "Stream = null");
            }
        }



        // onPostExecute() end
    } // ProcessJSON class end



} // Activity end
