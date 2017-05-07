// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import android.content.Context;
import android.app.Activity;
import android.widget.ImageButton;
import android.view.View$OnClickListener;
import android.widget.FrameLayout;

public final class bp extends FrameLayout implements View$OnClickListener
{
    private final ImageButton gZ;
    private final Activity gs;
    
    public bp(final Activity gs, int a) {
        super((Context)gs);
        this.gs = gs;
        this.setOnClickListener((View$OnClickListener)this);
        (this.gZ = new ImageButton((Context)gs)).setImageResource(17301527);
        this.gZ.setBackgroundColor(0);
        this.gZ.setOnClickListener((View$OnClickListener)this);
        this.gZ.setPadding(0, 0, 0, 0);
        a = cs.a((Context)gs, a);
        this.addView((View)this.gZ, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(a, a, 17));
    }
    
    public void g(final boolean b) {
        final ImageButton gz = this.gZ;
        int visibility;
        if (b) {
            visibility = 4;
        }
        else {
            visibility = 0;
        }
        gz.setVisibility(visibility);
    }
    
    public void onClick(final View view) {
        this.gs.finish();
    }
}
