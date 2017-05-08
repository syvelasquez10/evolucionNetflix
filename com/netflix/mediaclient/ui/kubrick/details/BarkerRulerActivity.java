// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import android.os.Bundle;
import android.widget.FrameLayout$LayoutParams;
import android.widget.LinearLayout;
import android.widget.FrameLayout;
import android.content.Intent;
import android.view.ViewGroup$LayoutParams;
import android.content.Context;
import android.view.View;
import android.app.Activity;

public class BarkerRulerActivity extends Activity
{
    public static final boolean SHOW_BARS = false;
    BarkerHelper$BarkerBars barker;
    
    private View createColumn() {
        final View view = new View((Context)this);
        view.setBackgroundColor(this.getResources().getColor(2131623953));
        view.setLayoutParams(new ViewGroup$LayoutParams(this.barker.getColumnWidth(), -1));
        return view;
    }
    
    private View createGutter() {
        final View view = new View((Context)this);
        view.setBackgroundColor(this.getResources().getColor(2131623954));
        view.setLayoutParams(new ViewGroup$LayoutParams(this.barker.getGutterWidth(), -1));
        return view;
    }
    
    public static void showRuler(final Activity activity) {
        activity.startActivity(new Intent((Context)activity, (Class)BarkerRulerActivity.class));
    }
    
    public View createViews() {
        int i = 0;
        final FrameLayout frameLayout = new FrameLayout((Context)this);
        final LinearLayout linearLayout = new LinearLayout((Context)this);
        linearLayout.setOrientation(0);
        final FrameLayout$LayoutParams layoutParams = new FrameLayout$LayoutParams(-2, -1);
        layoutParams.gravity = 1;
        linearLayout.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        while (i < this.barker.getTotalColumnNum()) {
            linearLayout.addView(this.createGutter());
            linearLayout.addView(this.createColumn());
            ++i;
        }
        linearLayout.addView(this.createGutter());
        frameLayout.addView((View)linearLayout);
        return (View)frameLayout;
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.barker = new BarkerHelper$BarkerBars((Context)this);
        this.getWindow().addFlags(16);
        this.setContentView(this.createViews());
    }
}
