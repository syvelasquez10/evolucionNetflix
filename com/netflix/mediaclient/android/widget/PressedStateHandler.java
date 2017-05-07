// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.animation.Animator$AnimatorListener;
import java.io.Serializable;
import com.netflix.mediaclient.Log;
import android.view.View;

public abstract class PressedStateHandler
{
    private static final String TAG = "PressedStateHandler";
    private boolean enabled;
    private boolean isAnimating;
    private PressedStateHandler$Listener listener;
    private boolean pressed;
    private final View view;
    
    protected PressedStateHandler(final View view) {
        this.enabled = true;
        this.view = view;
    }
    
    private void setCompletionCallback(final PressedStateHandler$Listener listener) {
        this.listener = listener;
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
            this.isAnimating = true;
            this.handlePressStarted(this.view);
        }
        else {
            this.handlePressCancelled(this.view);
        }
        this.pressed = pressed;
    }
    
    public boolean isAnimating() {
        return this.isAnimating;
    }
    
    protected void log(final String s) {
        if (Log.isLoggable()) {
            final StringBuilder sb = new StringBuilder();
            Serializable value;
            if (this.view == null) {
                value = "null";
            }
            else {
                value = this.view.hashCode();
            }
            Log.v("PressedStateHandler", sb.append(value).append(": ").append(s).toString());
        }
    }
    
    protected void notifyParentOfAnimationComplete() {
        this.isAnimating = false;
        this.view.animate().setListener((Animator$AnimatorListener)null);
        if (this.listener != null) {
            this.log("Notifying listener of pressed animation complete");
            this.listener.onPressedAnimationComplete();
        }
    }
    
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }
}
