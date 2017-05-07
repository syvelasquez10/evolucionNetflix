// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.Log;
import android.view.View;

public class PressedStateHandler
{
    private static final String TAG = "PressedStateHandler";
    private boolean enabled;
    private boolean pressed;
    private final View view;
    
    public PressedStateHandler(final View view) {
        this.enabled = true;
        this.view = view;
    }
    
    public void handleSetPressed(final boolean pressed) {
        if (!this.enabled) {
            return;
        }
        if (Log.isLoggable("PressedStateHandler", 2)) {
            Log.v("PressedStateHandler", "Prev pressed state: " + this.pressed + ", new pressed state: " + pressed);
        }
        if (this.pressed && !pressed) {
            AnimationUtils.startPressedStateCompleteAnimation(this.view);
        }
        else {
            final View view = this.view;
            float alpha;
            if (pressed) {
                alpha = 0.7f;
            }
            else {
                alpha = 1.0f;
            }
            view.setAlpha(alpha);
        }
        this.pressed = pressed;
    }
    
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }
}
