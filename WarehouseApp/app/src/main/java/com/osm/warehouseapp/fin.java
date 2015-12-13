package com.osm.warehouseapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class fin extends ActionBarActivity {

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
        farmername.setText(fl.farmername);
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
                finish();
            }
        });
    }
}
