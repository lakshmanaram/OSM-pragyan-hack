package com.osm.qualitycheck;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.osm.qualitycheck.R;
import com.osm.qualitycheck.RequestsSliderFragment;


public class Requests extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);
//
//        if (savedInstanceState == null) {
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            RequestsSliderFragment fragment = new RequestsSliderFragment();
//            transaction.replace(R.id.fragment_content3, fragment);
//            transaction.commit();
//        }
    }

}
