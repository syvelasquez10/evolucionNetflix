// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import com.netflix.mediaclient.Log;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.ViewFlipper;

public class SafeViewFlipper extends ViewFlipper
{
    private static final String TAG = "nf-ui";
    
    public SafeViewFlipper(final Context context) {
        super(context);
    }
    
    public SafeViewFlipper(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    protected void onDetachedFromWindow() {
        try {
            super.onDetachedFromWindow();
        }
        catch (IllegalArgumentException ex) {
            Log.d("nf-ui", "SafeViewFlipper ignoring IllegalArgumentException");
            this.stopFlipping();
        }
    }
}
