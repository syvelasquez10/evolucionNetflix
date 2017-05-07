// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.gfx;

import android.animation.Animator;
import android.view.View;
import android.animation.Animator$AnimatorListener;

public class AnimationUtils$HideViewOnAnimatorEnd implements Animator$AnimatorListener
{
    private final View view;
    
    public AnimationUtils$HideViewOnAnimatorEnd(final View view) {
        this.view = view;
    }
    
    public void onAnimationCancel(final Animator animator) {
    }
    
    public void onAnimationEnd(final Animator animator) {
        this.view.setVisibility(8);
    }
    
    public void onAnimationRepeat(final Animator animator) {
    }
    
    public void onAnimationStart(final Animator animator) {
    }
}
