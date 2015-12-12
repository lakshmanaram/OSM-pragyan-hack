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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by lakshmanaram on 12/12/15.
 */
public class CustomAdapterFarmer extends ArrayAdapter<FarmerList> implements View.OnClickListener {

    Context cont;
    String user = null, pass = null;
    ArrayList<FarmerList> fl;
    CustomAdapterFarmer(Context context,ArrayList<FarmerList> f,String username,String password)
    {
        super(context,R.layout.row,R.id.orderid,f);
        this.cont=context;
        this.pass = password;
        this.user = username;
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
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
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
        holder.name.setText(fl.get(position).farmername);
        holder.orderid.setText(fl.get(position).orderid);
        holder.address.setText(fl.get(position).address);
        holder.time.setText(fl.get(position).timestamp);
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
            Intent i = new Intent(cont, FarmerActivity.class);
            i.setFlags(i.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("username", user);
            i.putExtra("password",pass);
            Farmerstat.f = fl.get(mPosition);
            cont.startActivity(i);

//            Log.d("hello",String.valueOf(mPosition)+" is clicked");
        }
    }
}