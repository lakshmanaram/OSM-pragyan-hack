package com.osm.qualitycheck;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;


public class FarmerActivity extends ActionBarActivity {
String user = null,pass = null;
    FarmerList fl;
    String URL = "";
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            user = b.getString("username");
            pass = b.getString("password");
        }
        fl = Farmerstat.f;
        TextView orderno = (TextView) findViewById(R.id.orderid);
        orderno.setText(fl.orderid);
        TextView farmername = (TextView) findViewById(R.id.farmername);
        farmername.setText(fl.farmerid);
        TextView farmerid = (TextView) findViewById(R.id.aadharid);
        farmerid.setText(fl.farmerid);
        ListView list = (ListView) findViewById(R.id.veggielist);
        ArrayList<veggie> f = fl.veg;
        CustomAdapter adapt = new CustomAdapter(getApplicationContext(),f);
        et = (EditText) findViewById(R.id.password);
        list.setAdapter(adapt);
        Button submit = (Button) findViewById(R.id.login);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FarmerDetailspost a = new FarmerDetailspost();
                a.execute(user, pass);
            }
        });
    }
    class FarmerDetailspost extends AsyncTask<String, Void, Boolean> {
        final String TAG = "JsonParser.java";

        @Override
        protected Boolean doInBackground(String... params) {
            JSONParser jp = new JSONParser();
            try {
                JSONObject js = new JSONObject();

                js.put("username", params[0]);
                js.put("password", params[1]);
                js.put("orderId",fl.orderid);
                JSONArray jarray = new JSONArray();
                for(int i=0;i<fl.veg.size();i++) {
                    JSONObject jo = new JSONObject();
                    jo.put("itemName",fl.veg.get(i).veggies);
                    jo.put("itemWeight",Float.valueOf(fl.veg.get(i).kgs));
                    jarray.put(jo);
                }
                js.put("orderList",jarray);
                js.put("farmerUsername",fl.farmerid);
                js.put("farmerPassword",et.getText().toString());
                JSONObject jd = jp.makeHttpRequest(URL, "POST", js);
                Log.i(TAG, js.toString());
                String success = jd.getString("api_call_status");
                return success.equals("success");
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
        }
    }
    private void wrongpassword() {
        Intent i =new Intent(this,FarmerActivity.class);
        startActivity(i);
        finish();

    }

}
