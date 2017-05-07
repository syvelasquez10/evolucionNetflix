// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.view.MotionEvent;
import com.netflix.mediaclient.Log;
import android.annotation.SuppressLint;
import android.view.View$OnSystemUiVisibilityChangeListener;

@SuppressLint({ "NewApi" })
public class NavigationBarListenerForICS extends NavigationBarListener implements View$OnSystemUiVisibilityChangeListener
{
    public NavigationBarListenerForICS(final TappableSurfaceView tappableSurfaceView) {
        super(tappableSurfaceView);
    }
    
    @Override
    public void onSystemUiVisibilityChange(final int n) {
        final int n2 = n & 0x2;
        if (Log.isLoggable()) {
            Log.d("nf_navbar", "onSystemUiVisibilityChange called: " + n);
            Log.d("nf_navbar", "onSystemUiVisibilityChange visibility mask: " + (n & 0x0));
            Log.d("nf_navbar", "onSystemUiVisibilityChange hide mask: " + n2);
        }
        if (n2 != 0) {
            Log.d("nf_navbar", "Navigation bar is hidden, do nothing");
            return;
        }
        Log.d("nf_navbar", "Navigation bar is visible, execute onTouch event");
        this.owner.processOnTouchEvent(null);
    }
    
    @Override
    public void startListening() {
        Log.d("nf_navbar", "startListening: add itself to listen for navigation bar changes.");
        this.owner.setOnSystemUiVisibilityChangeListener((View$OnSystemUiVisibilityChangeListener)this);
    }
    
    @Override
    public void stopListening() {
        Log.d("nf_navbar", "stopListening: remove listener");
        this.owner.setOnSystemUiVisibilityChangeListener((View$OnSystemUiVisibilityChangeListener)null);
    }
}
