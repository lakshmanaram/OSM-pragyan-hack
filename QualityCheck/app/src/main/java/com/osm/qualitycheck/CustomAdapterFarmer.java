package com.osm.qualitycheck;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lakshmanaram on 12/12/15.
 */
public class CustomAdapterFarmer extends BaseAdapter implements View.OnClickListener {

    Context cont;
    ArrayList<FarmerList> fl;
    ArrayList<String> namel;
    ArrayList<String> addressl;
    ArrayList<String> timel;
    CustomAdapterFarmer(Context context,ArrayList<FarmerList> f)
    {
//        super(context,R.layout.row,FarmerList);
        this.cont=context;
        this.fl = f;
    }
    @Override
    public void onClick(View view) {

        Intent i = new Intent(cont, MainActivity.class);
//        Log.d("hello",view.)
        cont.startActivity(i);
        ((Activity) cont).finish();
    }
    class ViewHolder
    {
        TextView name,address,orderid,time;
        ViewHolder(View v)
        {
            name = (TextView) v.findViewById(R.id.farmername);
            address = (TextView) v.findViewById(R.id.address);
            orderid = (TextView) v.findViewById(R.id.orderid);
            time = (TextView) v.findViewById(R.id.ordertime);
        }
    }

    @Override
    public int getCount() {
        return fl.size();
    }

    @Override
    public Object getItem(int i) {
        return fl.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) cont.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.row, parent, false);
            holder = new ViewHolder(v);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        v.setOnClickListener(new OnItemClickListener(position));
        return v;
    }
    private class OnItemClickListener implements View.OnClickListener{
        private int mPosition;

        OnItemClickListener(int position){
            mPosition = position;
        }

        @Override
        public void onClick(View arg0) {
            Log.d("hello",String.valueOf(mPosition)+" is clicked");
        }
    }
}