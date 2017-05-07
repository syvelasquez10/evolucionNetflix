// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui;

import com.netflix.mediaclient.Log;
import android.widget.TextView;
import android.view.animation.Animation;
import android.view.animation.AlphaAnimation;
import android.view.View;
import android.app.Activity;

public abstract class Section
{
    protected static final float DISABLED_ALPHA = 0.3f;
    protected Activity activity;
    protected int transpColor;
    
    public Section(final Activity activity) {
        this.transpColor = activity.getResources().getColor(17170445);
        this.activity = activity;
    }
    
    protected void animateView(final View view, final float n, final float n2) {
        final AlphaAnimation alphaAnimation = new AlphaAnimation(n, n2);
        alphaAnimation.setFillAfter(true);
        view.startAnimation((Animation)alphaAnimation);
    }
    
    public abstract void changeActionState(final boolean p0);
    
    public abstract void destroy();
    
    protected void enableButton(final View disableOverlayForImageButton, final boolean enabled) {
        if (disableOverlayForImageButton != null) {
            disableOverlayForImageButton.setEnabled(enabled);
            if (!enabled) {
                this.setDisableOverlayForImageButton(disableOverlayForImageButton);
                return;
            }
            disableOverlayForImageButton.clearAnimation();
        }
    }
    
    public abstract void hide();
    
    protected void setDisableOverlayForImageButton(final View view) {
        this.animateView(view, 0.3f, 0.3f);
    }
    
    public abstract void show();
    
    protected void updateText(final String s, final TextView textView, final String s2, final String text) {
        if (textView != null) {
            if (Log.isLoggable(s, 3)) {
                Log.d(s, "updateText: view id: " + s2 + "txt " + text);
            }
            textView.setText((CharSequence)text);
            return;
        }
        Log.w(s, "View is null!");
    }
}
