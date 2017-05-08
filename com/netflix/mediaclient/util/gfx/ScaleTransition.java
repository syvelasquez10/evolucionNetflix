// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.gfx;

import com.netflix.mediaclient.util.AndroidUtils;
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
        if (target == null) {
            return null;
        }
        final AnimatorSet set = (AnimatorSet)AnimatorInflater.loadAnimator(target.getContext(), 2131034113);
        set.setDuration((long)AndroidUtils.getIntegerRes(target.getContext(), 2131558406));
        set.setTarget((Object)target);
        return (Animator)set;
    }
    
    public Animator onDisappear(final ViewGroup viewGroup, final View target, final TransitionValues transitionValues, final TransitionValues transitionValues2) {
        if (target == null) {
            return null;
        }
        final AnimatorSet set = (AnimatorSet)AnimatorInflater.loadAnimator(target.getContext(), 2131034112);
        set.setDuration((long)AndroidUtils.getIntegerRes(target.getContext(), 2131558406));
        set.setTarget((Object)target);
        return (Animator)set;
    }
}
