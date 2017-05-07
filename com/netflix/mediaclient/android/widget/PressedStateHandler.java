// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.view.View;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.Log;
import android.widget.ImageView;

public class PressedStateHandler
{
    private static final String TAG = "PressedStateHandler";
    private boolean enabled;
    private boolean pressed;
    private final ImageView view;
    
    public PressedStateHandler(final ImageView view) {
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
            AnimationUtils.startAlphaFadeInAnimation((View)this.view);
        }
        else {
            final ImageView view = this.view;
            float alpha;
            if (pressed) {
                alpha = 0.65f;
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
