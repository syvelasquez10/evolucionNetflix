// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism;

import android.view.MotionEvent;
import com.crittercism.app.Crittercism;
import android.widget.TextView;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.content.Context;
import android.widget.LinearLayout;
import crittercism.android.l;
import android.os.Bundle;
import android.view.View;
import android.view.View$OnTouchListener;
import android.view.View$OnClickListener;
import android.app.Activity;

public class NotificationActivity extends Activity implements View$OnClickListener, View$OnTouchListener
{
    public void onClick(final View view) {
        this.finish();
    }
    
    protected void onCreate(Bundle extras) {
        super.onCreate(extras);
        extras = this.getIntent().getExtras();
        if (extras != null && extras.containsKey("com.crittercism.notification")) {
            this.setTheme(16973835);
            this.requestWindowFeature(1);
            final String string = extras.getString("com.crittercism.notification");
            final l i = l.i();
            final LinearLayout contentView = new LinearLayout((Context)this);
            contentView.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, -1));
            contentView.setOrientation(0);
            final int n = i.n();
            contentView.setPadding(n, n, n, n);
            contentView.setId(13);
            contentView.setOnClickListener((View$OnClickListener)this);
            contentView.setOnTouchListener((View$OnTouchListener)this);
            final TextView textView = new TextView((Context)this);
            final LinearLayout$LayoutParams layoutParams = new LinearLayout$LayoutParams(-2, -2);
            layoutParams.setMargins(0, 0, i.n(), 0);
            textView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            textView.setTextSize(16.0f);
            textView.setTextColor(-1);
            textView.setId(51);
            textView.setText((CharSequence)(Crittercism.getNotificationTitle() + ": " + string));
            contentView.addView((View)textView);
            this.setContentView((View)contentView);
        }
    }
    
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        this.finish();
        return true;
    }
}
