// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import android.content.Context;
import android.widget.ImageButton;
import android.app.Activity;
import android.view.View$OnClickListener;
import android.widget.FrameLayout;

public final class ch extends FrameLayout implements View$OnClickListener
{
    private final Activity nS;
    private final ImageButton oB;
    
    public ch(final Activity ns, int a) {
        super((Context)ns);
        this.nS = ns;
        this.setOnClickListener((View$OnClickListener)this);
        (this.oB = new ImageButton((Context)ns)).setImageResource(17301527);
        this.oB.setBackgroundColor(0);
        this.oB.setOnClickListener((View$OnClickListener)this);
        this.oB.setPadding(0, 0, 0, 0);
        a = dv.a((Context)ns, a);
        this.addView((View)this.oB, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(a, a, 17));
    }
    
    public void i(final boolean b) {
        final ImageButton ob = this.oB;
        int visibility;
        if (b) {
            visibility = 4;
        }
        else {
            visibility = 0;
        }
        ob.setVisibility(visibility);
    }
    
    public void onClick(final View view) {
        this.nS.finish();
    }
}
