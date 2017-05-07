// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.view.MotionEvent;
import com.netflix.mediaclient.Log;
import android.annotation.SuppressLint;
import android.view.View$OnSystemUiVisibilityChangeListener;

@SuppressLint({ "NewApi" })
public class NavigationBarListenerForJB extends NavigationBarListener implements View$OnSystemUiVisibilityChangeListener
{
    private boolean isLowPeofile;
    
    public NavigationBarListenerForJB(final TappableSurfaceView tappableSurfaceView) {
        super(tappableSurfaceView);
        this.isLowPeofile = false;
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
            Log.d("nf_navbar", "onSystemUiVisibilityChange Navigation bar is hidden, do nothing");
            this.isLowPeofile = false;
            return;
        }
        if ((n & 0x1) != 0x0) {
            Log.d("nf_navbar", "onSystemUiVisibilityChange Navigation bar is low profile");
            this.isLowPeofile = true;
            return;
        }
        if ((n & 0x4) != 0x0) {
            Log.d("nf_navbar", "onSystemUiVisibilityChange SystemUI is full screen, do nothing");
            return;
        }
        if (!this.isLowPeofile) {
            Log.d("nf_navbar", "onSystemUiVisibilityChange Navigation bar is visible, execute onTouch event");
            this.owner.processOnTouchEvent(null);
        }
        this.isLowPeofile = false;
    }
    
    @Override
    public void startListening() {
        Log.d("nf_navbar", "startListening: add itself to listen for navigation bar changes.");
        this.owner.setOnSystemUiVisibilityChangeListener((View$OnSystemUiVisibilityChangeListener)this);
        this.isLowPeofile = false;
    }
    
    @Override
    public void stopListening() {
        Log.d("nf_navbar", "stopListening: remove listener");
        this.owner.setOnSystemUiVisibilityChangeListener((View$OnSystemUiVisibilityChangeListener)null);
    }
}
