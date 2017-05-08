// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.gfx;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.Animator;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;
import android.annotation.TargetApi;
import android.transition.Visibility;

@TargetApi(21)
public class ScaleTransition extends Visibility
{
    public Animator onAppear(final ViewGroup viewGroup, final View target, final TransitionValues transitionValues, final TransitionValues transitionValues2) {
        final AnimatorSet set = (AnimatorSet)AnimatorInflater.loadAnimator(target.getContext(), 2131034113);
        set.setTarget((Object)target);
        return (Animator)set;
    }
    
    public Animator onDisappear(final ViewGroup viewGroup, final View target, final TransitionValues transitionValues, final TransitionValues transitionValues2) {
        final AnimatorSet set = (AnimatorSet)AnimatorInflater.loadAnimator(target.getContext(), 2131034112);
        set.setTarget((Object)target);
        return (Animator)set;
    }
}
