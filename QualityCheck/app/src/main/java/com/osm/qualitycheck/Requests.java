package com.osm.qualitycheck;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;


public class Requests extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);
        ListView list = (ListView) findViewById(R.id.listView);
        FarmerList temp = new FarmerList();
            temp.farmerid = "affaffaf";
            temp.farmername = "wgwegwg";
            temp.orderid = "56746474";
            temp.timestamp = "73242";
        ArrayList<FarmerList> f = new ArrayList<>();
        f.add(temp);
        temp.farmerid = "sdffafsdgsfaf";
        temp.farmername = "hmfvgmfwegwg";
        temp.orderid = "12446474";
        temp.timestamp = "4242";
        f.add(temp);
        CustomAdapterFarmer adapt = new CustomAdapterFarmer(getApplicationContext(),f);
        list.setAdapter(adapt);
    }

}
