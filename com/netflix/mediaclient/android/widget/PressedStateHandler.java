// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.view.View;

public abstract class PressedStateHandler
{
    protected static final String TAG = "PressedStateHandler";
    private boolean enabled;
    private boolean pressed;
    private final View view;
    
    protected PressedStateHandler(final View view) {
        this.enabled = true;
        this.view = view;
    }
    
    protected abstract void handlePressCancelled(final View p0);
    
    protected abstract void handlePressComplete(final View p0);
    
    protected abstract void handlePressStarted(final View p0);
    
    public void handleSetPressed(final boolean pressed) {
        if (!this.enabled) {
            return;
        }
        if (this.pressed && !pressed) {
            this.handlePressComplete(this.view);
        }
        else if (pressed) {
            this.handlePressStarted(this.view);
        }
        else {
            this.handlePressCancelled(this.view);
        }
        this.pressed = pressed;
    }
    
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }
}
