package com.osm.warehouseapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lakshmanaram on 13/12/15.
 */
public class CustomAdapter extends ArrayAdapter<veggie> implements View.OnClickListener {

    Context cont;
    ArrayList<veggie> veg;
    CustomAdapter(Context context,ArrayList<veggie> r)
    {
        super(context,R.layout.veggierow,R.id.vegname,r);
        this.cont=context;
        this.veg = r;
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
        TextView name;
        EditText quantity;
        ImageButton img;
        ViewHolder(View v)
        {
            name = (TextView) v.findViewById(R.id.vegname);
            quantity = (EditText) v.findViewById(R.id.veggieQuantity);
            img = (ImageButton) v.findViewById(R.id.imagebutton);
        }
    }
    public ArrayList<veggie> getVeg(){
        return veg;
    }
    @Override
    public int getCount() {
        return veg.size();
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
            v = inflater.inflate(R.layout.veggierow, parent, false);
            holder = new ViewHolder(v);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.name.setText(veg.get(position).veggies);
        holder.quantity.setText(veg.get(position).kgs);
        final ViewHolder h1 = holder;
        final int p = position;
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                veggie temp = new veggie();
                temp.veggies = h1.name.getText().toString();
                temp.kgs = h1.quantity.getText().toString();
                veg.set(p,temp);
            }
        });

        return v;
    }

}
