// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.gfx;

import android.animation.AnimatorSet$Builder;
import android.animation.AnimatorSet;
import com.netflix.mediaclient.util.AndroidUtils;
import android.animation.Animator;
import android.transition.TransitionValues;
import android.util.Property;
import com.netflix.mediaclient.util.DeviceUtils;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.annotation.TargetApi;
import android.transition.Visibility;

@TargetApi(21)
public class SlideTransition extends Visibility
{
    private ObjectAnimator getTranslationAnimator(final ViewGroup viewGroup, final View view, final boolean b) {
        final boolean tabletByContext = DeviceUtils.isTabletByContext(view.getContext());
        if (b) {
            final Property translation_X = View.TRANSLATION_X;
            float n;
            if (tabletByContext) {
                n = viewGroup.getWidth() / 2;
            }
            else {
                n = viewGroup.getWidth();
            }
            return ObjectAnimator.ofFloat((Object)view, translation_X, new float[] { n, 0.0f });
        }
        final Property translation_X2 = View.TRANSLATION_X;
        float n2;
        if (tabletByContext) {
            n2 = viewGroup.getWidth() / 2;
        }
        else {
            n2 = viewGroup.getWidth();
        }
        return ObjectAnimator.ofFloat((Object)view, translation_X2, new float[] { n2 });
    }
    
    public Animator onAppear(final ViewGroup viewGroup, final View target, final TransitionValues transitionValues, final TransitionValues transitionValues2) {
        if (target == null) {
            return null;
        }
        final ObjectAnimator translationAnimator = this.getTranslationAnimator(viewGroup, target, true);
        ((Animator)translationAnimator).setDuration((long)AndroidUtils.getIntegerRes(target.getContext(), 2131492870));
        ((Animator)translationAnimator).setTarget((Object)target);
        return (Animator)translationAnimator;
    }
    
    public Animator onDisappear(final ViewGroup viewGroup, final View target, final TransitionValues transitionValues, final TransitionValues transitionValues2) {
        if (target == null) {
            return null;
        }
        final AnimatorSet set = new AnimatorSet();
        final ObjectAnimator translationAnimator = this.getTranslationAnimator(viewGroup, target, false);
        set.setDuration((long)AndroidUtils.getIntegerRes(target.getContext(), 2131492870));
        final AnimatorSet$Builder play = set.play((Animator)translationAnimator);
        if (DeviceUtils.isTabletByContext(target.getContext())) {
            final Property alpha = View.ALPHA;
            float n;
            if (DeviceUtils.isTabletByContext(target.getContext())) {
                n = 0.0f;
            }
            else {
                n = 1.0f;
            }
            play.with((Animator)ObjectAnimator.ofFloat((Object)target, alpha, new float[] { n }));
        }
        set.setTarget((Object)target);
        return (Animator)set;
    }
}
