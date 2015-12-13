package com.osm.qualitycheck;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class Requests extends ActionBarActivity {
final String URL = "http://192.168.43.79:3000/api/qc/placesToVisit";
    String user = null, pass = null;
    CustomAdapterFarmer adapt;
    ListView list;
    ArrayList<FarmerList> f = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            user = b.getString("username");
            pass = b.getString("password");
        }
        Log.d("hello",user + "coming" + pass);
        FarmerDetails a = new FarmerDetails();
        a.execute(user, pass);


    }

    class FarmerDetails extends AsyncTask<String, Void, Boolean> {
        final String TAG = "JsonParser.java";

        @Override
        protected Boolean doInBackground(String... params) {
            JSONParser jp = new JSONParser();
            try {
                JSONObject js = new JSONObject();

                js.put("username", params[0]);
                js.put("password", params[1]);
                JSONObject jd = jp.makeHttpRequest(URL, "POST", js);
                Log.i(TAG, js.toString());
                String success = jd.getString("api_call_status");
                if(success.equals("success")){
                    JSONArray jarray = jd.getJSONArray("places");
                    int n = jd.getInt("numberOfPlaces");
                    for(int i=0;i<n;i++){
                        JSONObject j = jarray.getJSONObject(i);
                        FarmerList temp = new FarmerList();
                        temp.farmername = j.getString("farmerName");
                        temp.timestamp = j.getString("orderTime");
                        temp.orderid = j.getString("orderID");
                        temp.address = j.getString("address");
                        temp.farmerid = j.getString("farmerID");
                        JSONArray nj = j.getJSONArray("orderList");
                        ArrayList<veggie> v = new ArrayList<>();
                        for(int k=0;k<nj.length();k++){
                            JSONObject jsobj = nj.getJSONObject(k);
                            veggie e =new veggie();
                            e.veggies = jsobj.getString("itemName");
                            e.kgs = jsobj.getString("itemWeight");
                            v.add(e);
                        }
                        temp.veg = v;
                        Log.d("hello",temp.orderid);
                        f.add(temp);
                    }
                    return true;
                }
                else
                    return false;
            } catch (Exception e) {
                e.printStackTrace();

            }


            return false;
        }

        //  @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (!aBoolean) {
                wrongpassword();
            }
            else{
                list = (ListView) findViewById(R.id.listView);
                adapt = new CustomAdapterFarmer(getApplicationContext(), f,user,pass);
                list.setAdapter(adapt);
            }
        }
    }
    private void wrongpassword() {
        Intent i =new Intent(this,MainActivity.class);
        i.putExtra("wrong",true);
        startActivity(i);

    }
}