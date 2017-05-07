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

@ez
public final class dp extends FrameLayout implements View$OnClickListener
{
    private final Activity nr;
    private final ImageButton sg;
    
    public dp(final Activity nr, int a) {
        super((Context)nr);
        this.nr = nr;
        this.setOnClickListener((View$OnClickListener)this);
        (this.sg = new ImageButton((Context)nr)).setImageResource(17301527);
        this.sg.setBackgroundColor(0);
        this.sg.setOnClickListener((View$OnClickListener)this);
        this.sg.setPadding(0, 0, 0, 0);
        this.sg.setContentDescription((CharSequence)"Interstitial close button");
        a = gr.a((Context)nr, a);
        this.addView((View)this.sg, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(a, a, 17));
    }
    
    public void o(final boolean b) {
        final ImageButton sg = this.sg;
        int visibility;
        if (b) {
            visibility = 4;
        }
        else {
            visibility = 0;
        }
        sg.setVisibility(visibility);
    }
    
    public void onClick(final View view) {
        this.nr.finish();
    }
}
