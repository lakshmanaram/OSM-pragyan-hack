package com.osm.warehouseapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class Checkout extends ActionBarActivity {

    String user = null,pass = null;
    FarmerList fl;
    String URL = "";
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        TextView tv = (TextView) findViewById(R.id.tv);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            user = b.getString("username");
            pass = b.getString("password");
            String t = b.getString("type");
            if(t.equals("Market")){
                tv.setText("Market CheckOut");
            }
            else{
                tv.setText("Home Delivery CheckOut");
            }
        }
        fl = Farmerstat.f;
        ListView list = (ListView) findViewById(R.id.veggielist);
        ArrayList<veggie> f = new ArrayList<>();
        veggie v = new veggie();
        v.veggies = "tomato";
        v.kgs = "3.5";
        f.add(v);
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
