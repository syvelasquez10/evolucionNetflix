// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.animation.Animator$AnimatorListener;
import java.io.Serializable;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View;
import java.security.InvalidParameterException;
import android.view.View$OnClickListener;

public class PressedStateHandler$DelayedOnClickListener implements View$OnClickListener
{
    private final View$OnClickListener onClickListener;
    private final PressedStateHandler pressedStateHandler;
    
    public PressedStateHandler$DelayedOnClickListener(final PressedStateHandler pressedStateHandler, final View$OnClickListener onClickListener) {
        this.pressedStateHandler = pressedStateHandler;
        this.onClickListener = onClickListener;
        if (onClickListener == null) {
            throw new InvalidParameterException("onClickListener must not be null");
        }
    }
    
    public void onClick(final View view) {
        final NetflixActivity netflixActivity = (NetflixActivity)view.getContext();
        if (this.pressedStateHandler != null && this.pressedStateHandler.isAnimating()) {
            if (Log.isLoggable()) {
                Log.v("PressedStateHandler", "Pressed handler is busy - waiting to launch details");
            }
            this.pressedStateHandler.setCompletionCallback(new PressedStateHandler$DelayedOnClickListener$1(this, netflixActivity, view));
            return;
        }
        if (Log.isLoggable()) {
            Log.v("PressedStateHandler", "Pressed handler is not available or busy - calling onClick callback directly");
        }
        this.onClickListener.onClick(view);
    }
}
