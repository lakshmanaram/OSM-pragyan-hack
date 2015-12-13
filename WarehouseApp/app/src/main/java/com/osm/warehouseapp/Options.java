package com.osm.warehouseapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


public class Options extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Button b1 = (Button) findViewById(R.id.Qc);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Options.this,Qc.class);
                startActivity(i);
            }
        });
        Button b2 = (Button) findViewById(R.id.Mc);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Options.this,Checkout.class);
                i.putExtra("type","Market");
                startActivity(i);
            }
        });
        Button b3 = (Button) findViewById(R.id.Hd);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Options.this,Checkout.class);
                i.putExtra("type","Home");
                startActivity(i);
            }
        });
    }
}
