package com.ys.enkiforkids;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by acer on 06/02/2018.
 */

public class Trdrt extends ActionR {

    public Trdrt(TextView label, TextView finit, List<ActionR> instruction, LinearLayout l) {
        super(label, finit, instruction, l);
    }

    @Override
    void executer(final LinearLayout l1,final LinearLayout l2,final Boolean b,final String ip) {
        AsyncTask<Void,Void,Void> a =new AsyncTask<Void,Void,Void>()
        {
            @Override
            protected void onPreExecute (){
                enableDisableView(l1,false);
                enableDisableView(l2,false);

            }
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    TimeUnit.SECONDS.sleep(5);
                }
                catch (Exception e){}
                return null;
            }

            @Override
            protected void onPostExecute(Void v) {
                GradientDrawable backgroundGradient = (GradientDrawable)finit.getBackground();
                backgroundGradient.setColor(Color.parseColor("#00FF00"));
                if (b)
                {
                    AsyncTask<Void,Void,Void> a1 =new AsyncTask<Void,Void,Void>()
                    {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            try {

                                String url ="http://"+ip+"/runM1";
                                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                            }
                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //Toast.makeText(this, "That didn't work! No connection", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                TimeUnit.SECONDS.sleep(2);
                            }
                            catch (Exception e){}
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void v) {
                            enableDisableView(l1,true);
                            enableDisableView(l2,true);
                            GradientDrawable backgroundGradient;
                            for(int k=0;k<instruction.size();k++)
                            {
                                backgroundGradient = (GradientDrawable)instruction.get(k).finit.getBackground();
                                backgroundGradient.setColor(Color.parseColor("#D1D1D1"));
                            }
                        }
                    };
                    a1.execute();
                }
            }
        };
        a.execute();
    }
}
