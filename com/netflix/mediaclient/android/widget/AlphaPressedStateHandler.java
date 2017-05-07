// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.animation.Animator$AnimatorListener;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.view.View;

public class AlphaPressedStateHandler extends PressedStateHandler
{
    public AlphaPressedStateHandler(final View view) {
        super(view);
    }
    
    @Override
    protected void handlePressCancelled(final View view) {
        view.setAlpha(1.0f);
    }
    
    @Override
    protected void handlePressComplete(final View view) {
        AnimationUtils.startPressedStateCompleteAnimation(view, (Animator$AnimatorListener)new AlphaPressedStateHandler$1(this));
    }
    
    @Override
    protected void handlePressStarted(final View view) {
        view.setAlpha(0.7f);
    }
}
