// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.animation.Animator$AnimatorListener;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.graphics.drawable.Drawable;
import android.view.View;

public class RipplePressedStateHandler extends PressedStateHandler
{
    public RipplePressedStateHandler(final AdvancedImageView advancedImageView) {
        super((View)advancedImageView);
        assert advancedImageView instanceof AdvancedImageView;
    }
    
    @Override
    protected void handlePressCancelled(final View view) {
        if (view instanceof AdvancedImageView) {
            final Drawable foregroundDrawable = ((AdvancedImageView)view).getForegroundDrawable();
            if (foregroundDrawable != null) {
                foregroundDrawable.setState(new int[0]);
            }
        }
    }
    
    @Override
    protected void handlePressComplete(final View view) {
        AnimationUtils.startPressedStateCompleteAnimation(view, (Animator$AnimatorListener)new RipplePressedStateHandler$1(this));
    }
    
    @Override
    protected void handlePressStarted(final View view) {
        if (view instanceof AdvancedImageView) {
            final Drawable foregroundDrawable = ((AdvancedImageView)view).getForegroundDrawable();
            if (foregroundDrawable != null) {
                foregroundDrawable.setState(new int[] { 16842919, 16842910 });
            }
        }
    }
}
