package com.ys.enkiforkids;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class first_activity extends AppCompatActivity {
    private Button btnCnx;
    private Button btnQuit;
    private Button strBtn;
    private Button testBtn;
    private TextView t1;
    private WifiConfiguration netConfig;
    private WifiManager wifiManager;
    private String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_activity);

        btnCnx=findViewById(R.id.cnxBtn);
        btnQuit=findViewById(R.id.qBtn);
        t1=findViewById(R.id.t1);
        strBtn=findViewById(R.id.strBtn);
        testBtn=findViewById(R.id.testBtn);


        wifiManager=(WifiManager)this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        netConfig=new WifiConfiguration();

        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Method[] methods=wifiManager.getClass().getDeclaredMethods();
                for (Method method : methods)
                {
                    if(method.getName().equals("setWifiApEnabled"))
                    {
                        try {
                            method.invoke(wifiManager,null,false);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }

                finish();
                System.exit(0);
            }
        });

        btnCnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Method[] methods=wifiManager.getClass().getDeclaredMethods();
                for (Method method : methods)
                {
                    if(method.getName().equals("setWifiApEnabled"))
                    {
                        try {
                            method.invoke(wifiManager,null,false);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }


                //creation du point d'access
                netConfig.SSID="enkiForKidsNet";
                netConfig.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
                if (wifiManager.isWifiEnabled()) wifiManager.setWifiEnabled(false);
                methods=wifiManager.getClass().getDeclaredMethods();


                for (Method method : methods)
                {
                    if(method.getName().equals("setWifiApEnabled"))
                    {
                        try {
                            method.invoke(wifiManager,netConfig,true);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }

                //détection du robot

                t1.setText("Attente d'une connection");
                t1.setTextColor(Color.parseColor("#00FF00"));
                btnCnx.setEnabled(false);
                testBtn.setEnabled(false);
                new Asy().execute();
            }
        });

        strBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(first_activity.this,main_activity.class);
                intent.putExtra("ip", ip);
                startActivity(intent);
            }
        });

        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testBtn.setEnabled(false);
                t1.setText("Attente d'une connection");
                t1.setTextColor(Color.parseColor("#00FF00"));
                testBtn.setEnabled(false);
                new Asy().execute();
            }
        });
    }
    private String testCnx()
    {
        try
        {
            BufferedReader bin = new BufferedReader(new FileReader("/proc/net/arp"));
            String line;

            ip=null;
            line = bin.readLine();
            line = bin.readLine();
            String[] splitted = line.split(" +");
            if (splitted != null)
            {
                ip = splitted[0];
            }
        }
        catch(Exception e) {}
        return ip;
    }
    public class Asy extends AsyncTask<Void,Void,String>
    {

        @Override
        protected String doInBackground(Void... voids) {

            try {
                TimeUnit.SECONDS.sleep(25);
            }
            catch (Exception e){}
            ip=testCnx();
            return ip;
        }

        @Override
        protected void onPostExecute(String ip) {
            super.onPostExecute(ip);
            if(ip!=null)
            {
                t1.setText("Connection établie :)");
                t1.setTextColor(Color.parseColor("#00FF00"));
                strBtn.setVisibility(View.VISIBLE);
            }
            else
            {
                btnCnx.setVisibility(View.INVISIBLE);
                testBtn.setVisibility(View.VISIBLE);
                testBtn.setEnabled(true);
                t1.setTextColor(Color.parseColor("#FF0000"));
                t1.setText("La connection du rebot a échoué veuillez réessayer");
            }
        }
    }

}
