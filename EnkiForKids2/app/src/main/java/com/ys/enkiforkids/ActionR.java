package com.ys.enkiforkids;

import android.text.Layout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by acer on 06/02/2018.
 */

public abstract class ActionR
{
    public TextView label;
    public TextView finit;
    public List<ActionR> instruction;
    public LinearLayout l;

    public ActionR(TextView label, TextView finit, List<ActionR> instruction, LinearLayout l) {
        this.label = label;
        this.finit = finit;
        this.instruction = instruction;
        this.l = l;
    }

    abstract void executer(LinearLayout l1,LinearLayout l2,Boolean der,String ip);

    protected void enableDisableView(LinearLayout view, boolean enabled) {
            for ( int idx = 0 ; idx < view.getChildCount() ; idx++ ) {
                view.getChildAt(idx).setEnabled(enabled);
            }
        }
}
