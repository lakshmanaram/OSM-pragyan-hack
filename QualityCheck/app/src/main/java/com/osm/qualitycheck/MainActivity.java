package com.osm.qualitycheck;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import org.json.JSONObject;


public class MainActivity extends Activity {
    public static final String URL = "";
    String user;
    String pass;
    String Qcheck = "Qualitycheckerid";
    static boolean wrong = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        Bundle b = getIntent().getExtras();
//        if (b != null) {
//            wrong = b.getBoolean("wrong");
//        }
        SharedPreferences prefs = getSharedPreferences("user",
                Context.MODE_PRIVATE);
        String id = prefs.getString(Qcheck, "default");
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);
        if(!id.equals("default")){
            username.setText(id);
            password.setText("password");
        }
        if(wrong){
            YoYo.with(Techniques.Tada).duration(700).playOn(username);
            YoYo.with(Techniques.Tada).duration(700).playOn(password);                  //TODO ask rahul on how to make this work
        }
        Button loginbutton = (Button) findViewById(R.id.login);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (username.getText().length() == 0) {
                    Toast.makeText(MainActivity.this, "Enter a valid quality checker number", Toast.LENGTH_SHORT).show();
                } else if (password.getText().length() == 0) {
                    Toast.makeText(MainActivity.this, "Enter valid password", Toast.LENGTH_SHORT).show();
                } else {
                    String user = username.getText().toString();
                    if (checkpref(user)) {
                        startActivity(new Intent(MainActivity.this, Requests.class));
                        finish();
                    } else {
                        user = username.getText().toString();
                        pass = password.getText().toString();
                        Log.d("TAG", user + pass);
                        Authenticate a = new Authenticate();
                        a.execute(user, pass);
                    }
                }
            }
        });
    }
    private boolean checkpref(String user) {
        SharedPreferences share=getSharedPreferences("user",Context.MODE_PRIVATE);
        String rno=share.getString("rno","-1");
        return user.equals(rno);
    }




    class Authenticate extends AsyncTask<String,Void,Boolean> {
        final String TAG = "JsonParser.java";
        @Override
        protected Boolean doInBackground(String... params) {
            JSONParser jp=new JSONParser();

            try {
                JSONObject js=new JSONObject();

                js.put("username",params[0]);
                js.put("password", params[1]);
                JSONObject jd=jp.makeHttpRequest(URL + "/login", "POST", js);
                Log.i(TAG,js.toString());
                int success=jd.getInt("logged_in");
                return true;
//                return success==1;                                                //authentication
            }  catch (Exception e) {
                e.printStackTrace();

            }


            return false;
        }

        //  @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean){/*
                SharedPreferences share1=getSharedPreferences("user",Context.MODE_PRIVATE);
                String qcheck=share1.getString(Qcheck,":)");
                if(qcheck.equals(":)")){
                    SharedPreferences share=getSharedPreferences("user",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=share.edit();
                    editor.putString(Qcheck, user);
                    editor.commit();
                }
                else {*/
                SharedPreferences share=getSharedPreferences("user",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=share.edit();
                editor.putString(Qcheck, user);
                editor.putString("pass", pass);
                editor.commit();
                Intent i = new Intent(MainActivity.this, Requests.class);
                startActivity(i);
                finish();
            }
            else{
                wrongpassword();
            }
        }
    }

    private void wrongpassword() {
        Intent i =new Intent(MainActivity.this,MainActivity.class);
        i.putExtra("wrong",true);
        startActivity(i);  //TODO: enhance with textview "Wrong password"

    }

}