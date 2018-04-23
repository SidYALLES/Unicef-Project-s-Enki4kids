package com.ys.enkiforkids;

import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.PixelCopy;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import android.util.TypedValue;

public class main_activity extends AppCompatActivity {

    private Button avantBtn;
    private Button arrierBtn;
    private Button gauBtn;
    private Button drBtn;
    private Button lightBtn;
    private Button turnOfBtn;
    private Button runBtn;
    private Button clrBtn;
    private Button backBtn;
    private Button dltBtn;
    private String ipRobot;
    private LinearLayout actionLayout;
    private LinearLayout barLyout;
    private LinearLayout algoLayout;
    private List<ActionR> instructions;
    private TextView indexDlt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity);

        avantBtn=findViewById(R.id.avantBtn);
        arrierBtn=findViewById(R.id.arrierBtn);
        gauBtn=findViewById(R.id.gauBtn);
        drBtn=findViewById(R.id.drBtn);
        lightBtn=findViewById(R.id.lightBtn);
        turnOfBtn=findViewById(R.id.turnOfBtn);
        algoLayout=findViewById(R.id.algoLayout);
        runBtn=findViewById(R.id.runBtn);
        actionLayout=findViewById(R.id.actionLayout);
        barLyout=findViewById(R.id.barLayout);
        clrBtn=findViewById(R.id.clrBtn);
        backBtn=findViewById(R.id.backBtn);
        dltBtn=findViewById(R.id.dltBtn);

        Bundle b = getIntent().getExtras();
        ipRobot = b.getString("ip");

        instructions=new ArrayList<ActionR>();

        avantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView v=createNewTextView("Pas en avant");
                v.setBackgroundColor(Color.parseColor("#3cb371"));
                LinearLayout l=createNewLLayout();
                TextView indice=createNewTextView("    ");
                indice.setBackgroundResource(R.drawable.circle);
                l.addView(indice);
                l.addView(v);
                instructions.add(new Avant(v,indice,instructions,l));
                algoLayout.addView(l);
            }
        });

        arrierBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView v=createNewTextView("Pas en arriere");
                v.setBackgroundColor(Color.parseColor("#C7CDFA"));
                LinearLayout l=createNewLLayout();
                TextView indice=createNewTextView("    ");
                indice.setBackgroundResource(R.drawable.circle);
                l.addView(indice);
                l.addView(v);
                instructions.add(new Arriere(v,indice,instructions,l));
                algoLayout.addView(l);
            }
        });

        lightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView v=createNewTextView("Allumer la lumiere");
                v.setBackgroundColor(Color.parseColor("#FFF68F"));
                LinearLayout l=createNewLLayout();
                TextView indice=createNewTextView("    ");
                indice.setBackgroundResource(R.drawable.circle);
                l.addView(indice);
                l.addView(v);
                instructions.add(new LightOn(v,indice,instructions,l));
                algoLayout.addView(l);
            }
        });

        turnOfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView v=createNewTextView("Eteindre la lumiere");
                v.setBackgroundColor(Color.parseColor("#81d8d0"));
                LinearLayout l=createNewLLayout();
                TextView indice=createNewTextView("    ");
                indice.setBackgroundResource(R.drawable.circle);
                l.addView(indice);
                l.addView(v);
                instructions.add(new LightOff(v,indice,instructions,l));
                algoLayout.addView(l);
            }
        });

        drBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView v=createNewTextView("Tourner à droite");
                v.setBackgroundColor(Color.parseColor("#f7a974"));
                LinearLayout l=createNewLLayout();
                TextView indice=createNewTextView("    ");
                indice.setBackgroundResource(R.drawable.circle);
                l.addView(indice);
                l.addView(v);
                instructions.add(new Trdrt(v,indice,instructions,l));
                algoLayout.addView(l);
            }
        });

        gauBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView v=createNewTextView("Tourner à gauche");
                v.setBackgroundColor(Color.parseColor("#b8ef5c"));
                LinearLayout l=createNewLLayout();
                TextView indice=createNewTextView("    ");
                indice.setBackgroundResource(R.drawable.circle);
                l.addView(indice);
                l.addView(v);
                instructions.add(new Trgche(v,indice,instructions,l));
                algoLayout.addView(l);
            }
        });

        runBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int der=instructions.size();
                if (der-1==0){
                    instructions.get(0).executer(actionLayout,barLyout,true,ipRobot);
                }
                else {
                    int j;
                    for (j=0;j<instructions.size()-1;j++)
                    {
                        Log.i("d","pas encore");
                        instructions.get(j).executer(actionLayout,barLyout,false,ipRobot);
                    }
                    instructions.get(j).executer(actionLayout,barLyout,true,ipRobot);
                }
            }
        });

        clrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instructions.clear();
                algoLayout.removeAllViews();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dltBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int indx=rechInd(indexDlt);
                Log.i("d","le indx est : "+indx);
                instructions.remove(indx);
                algoLayout.removeView((View) indexDlt.getParent());
                dltBtn.setVisibility(View.INVISIBLE);
            }
        });

    }
    private TextView createNewTextView(String text) {
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        lparams.setMargins((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()),0,0,0);
        final TextView textView = new TextView(this);
        textView.setLayoutParams(lparams);
        textView.setText(text);
        textView.setTextColor(Color.parseColor("#004056"));
        textView.setTextSize((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, getResources().getDisplayMetrics()));
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textView==indexDlt)
                {
                    if(dltBtn.getVisibility()==View.VISIBLE) dltBtn.setVisibility(View.INVISIBLE);
                    else dltBtn.setVisibility(View.VISIBLE);
                }
                else
                {
                    int ind=algoLayout.indexOfChild((LinearLayout)textView.getParent());
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35, getResources().getDisplayMetrics()),
                            (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35, getResources().getDisplayMetrics())
                    );
                    params.setMargins(0,((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35, getResources().getDisplayMetrics()))*(ind),0,0);
                    indexDlt=textView;
                    dltBtn.setLayoutParams(params);
                    dltBtn.setVisibility(View.VISIBLE);
                }
            }
        });
        return textView;
    }

    private LinearLayout createNewLLayout() {
        LinearLayout l=new LinearLayout(this);
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35, getResources().getDisplayMetrics()));
        lparams.setMargins((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()),0,0,0);
        l.setLayoutParams(lparams);
        int i=(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
        l.setPadding(i,i,i,i);
        l.setOrientation(LinearLayout.HORIZONTAL);
        return l;
    }

    private int rechInd(TextView t)
    {
        int i=0,taille=instructions.size();
        boolean b=false;
        while ((!b)&&(i<taille))
        {
            if (instructions.get(i).label==t) b=true;
            else i++;
        }
       return i;
    }

}
