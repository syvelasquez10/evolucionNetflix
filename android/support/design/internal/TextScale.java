// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.internal;

import java.util.Map;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.animation.Animator;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.transition.TransitionValues;
import android.annotation.TargetApi;
import android.support.transition.Transition;

@TargetApi(14)
public class TextScale extends Transition
{
    private static final String PROPNAME_SCALE = "android:textscale:scale";
    
    private void captureValues(final TransitionValues transitionValues) {
        if (transitionValues.view instanceof TextView) {
            transitionValues.values.put("android:textscale:scale", ((TextView)transitionValues.view).getScaleX());
        }
    }
    
    @Override
    public void captureEndValues(final TransitionValues transitionValues) {
        this.captureValues(transitionValues);
    }
    
    @Override
    public void captureStartValues(final TransitionValues transitionValues) {
        this.captureValues(transitionValues);
    }
    
    @Override
    public Animator createAnimator(final ViewGroup viewGroup, final TransitionValues transitionValues, final TransitionValues transitionValues2) {
        float floatValue = 1.0f;
        if (transitionValues == null || transitionValues2 == null || !(transitionValues.view instanceof TextView) || !(transitionValues2.view instanceof TextView)) {
            return null;
        }
        final TextView textView = (TextView)transitionValues2.view;
        final Map<String, Object> values = transitionValues.values;
        final Map<String, Object> values2 = transitionValues2.values;
        float floatValue2;
        if (values.get("android:textscale:scale") != null) {
            floatValue2 = values.get("android:textscale:scale");
        }
        else {
            floatValue2 = 1.0f;
        }
        if (values2.get("android:textscale:scale") != null) {
            floatValue = (float)values2.get("android:textscale:scale");
        }
        if (floatValue2 == floatValue) {
            return null;
        }
        final ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[] { floatValue2, floatValue });
        ofFloat.addUpdateListener((ValueAnimator$AnimatorUpdateListener)new TextScale$1(this, textView));
        return (Animator)ofFloat;
    }
}
