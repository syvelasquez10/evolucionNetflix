// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;

class NavigationBarListener
{
    protected static final String TAG = "nf_navbar";
    protected TappableSurfaceView owner;
    
    NavigationBarListener(final TappableSurfaceView owner) {
        if (owner == null) {
            throw new IllegalArgumentException("View is null!");
        }
        this.owner = owner;
    }
    
    static NavigationBarListener getInstance(final Context context, final TappableSurfaceView tappableSurfaceView) {
        final boolean tabletByContext = DeviceUtils.isTabletByContext(context);
        if (Log.isLoggable()) {
            Log.d("nf_navbar", "Device is tablet: " + tabletByContext);
        }
        int n;
        if (AndroidUtils.getAndroidVersion() >= 16) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n != 0) {
            Log.d("nf_navbar", "Real listener for JB+");
            return new NavigationBarListenerForJB(tappableSurfaceView);
        }
        Log.d("nf_navbar", "Dummy listener");
        return new NavigationBarListener(tappableSurfaceView);
    }
    
    public void onSystemUiVisibilityChange(final int n) {
    }
    
    public void startListening() {
        Log.d("nf_navbar", "startListening: noop");
    }
    
    public void stopListening() {
        Log.d("nf_navbar", "stopListening: noop");
    }
}
