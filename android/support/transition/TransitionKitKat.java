// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import java.util.List;
import android.animation.TimeInterpolator;
import android.animation.Animator;
import android.view.ViewGroup;
import android.view.View;
import android.transition.Transition$TransitionListener;
import java.util.Map;
import android.transition.Transition;
import android.annotation.TargetApi;

@TargetApi(19)
class TransitionKitKat extends TransitionImpl
{
    private TransitionKitKat$CompatListener mCompatListener;
    TransitionInterface mExternalTransition;
    Transition mTransition;
    
    static android.transition.TransitionValues convertToPlatform(final TransitionValues transitionValues) {
        if (transitionValues == null) {
            return null;
        }
        final android.transition.TransitionValues transitionValues2 = new android.transition.TransitionValues();
        copyValues(transitionValues, transitionValues2);
        return transitionValues2;
    }
    
    static TransitionValues convertToSupport(final android.transition.TransitionValues transitionValues) {
        if (transitionValues == null) {
            return null;
        }
        final TransitionValues transitionValues2 = new TransitionValues();
        copyValues(transitionValues, transitionValues2);
        return transitionValues2;
    }
    
    static void copyValues(final TransitionValues transitionValues, final android.transition.TransitionValues transitionValues2) {
        if (transitionValues != null) {
            transitionValues2.view = transitionValues.view;
            if (transitionValues.values.size() > 0) {
                transitionValues2.values.putAll(transitionValues.values);
            }
        }
    }
    
    static void copyValues(final android.transition.TransitionValues transitionValues, final TransitionValues transitionValues2) {
        if (transitionValues != null) {
            transitionValues2.view = transitionValues.view;
            if (transitionValues.values.size() > 0) {
                transitionValues2.values.putAll(transitionValues.values);
            }
        }
    }
    
    static void wrapCaptureEndValues(final TransitionInterface transitionInterface, final android.transition.TransitionValues transitionValues) {
        final TransitionValues transitionValues2 = new TransitionValues();
        copyValues(transitionValues, transitionValues2);
        transitionInterface.captureEndValues(transitionValues2);
        copyValues(transitionValues2, transitionValues);
    }
    
    static void wrapCaptureStartValues(final TransitionInterface transitionInterface, final android.transition.TransitionValues transitionValues) {
        final TransitionValues transitionValues2 = new TransitionValues();
        copyValues(transitionValues, transitionValues2);
        transitionInterface.captureStartValues(transitionValues2);
        copyValues(transitionValues2, transitionValues);
    }
    
    @Override
    public TransitionImpl addListener(final TransitionInterfaceListener transitionInterfaceListener) {
        if (this.mCompatListener == null) {
            this.mCompatListener = new TransitionKitKat$CompatListener(this);
            this.mTransition.addListener((Transition$TransitionListener)this.mCompatListener);
        }
        this.mCompatListener.addListener(transitionInterfaceListener);
        return this;
    }
    
    @Override
    public TransitionImpl addTarget(final int n) {
        this.mTransition.addTarget(n);
        return this;
    }
    
    @Override
    public TransitionImpl addTarget(final View view) {
        this.mTransition.addTarget(view);
        return this;
    }
    
    @Override
    public void captureEndValues(final TransitionValues transitionValues) {
        final android.transition.TransitionValues transitionValues2 = new android.transition.TransitionValues();
        copyValues(transitionValues, transitionValues2);
        this.mTransition.captureEndValues(transitionValues2);
        copyValues(transitionValues2, transitionValues);
    }
    
    @Override
    public void captureStartValues(final TransitionValues transitionValues) {
        final android.transition.TransitionValues transitionValues2 = new android.transition.TransitionValues();
        copyValues(transitionValues, transitionValues2);
        this.mTransition.captureStartValues(transitionValues2);
        copyValues(transitionValues2, transitionValues);
    }
    
    @Override
    public Animator createAnimator(final ViewGroup viewGroup, final TransitionValues transitionValues, final TransitionValues transitionValues2) {
        android.transition.TransitionValues transitionValues3 = null;
        android.transition.TransitionValues transitionValues5;
        if (transitionValues != null) {
            final android.transition.TransitionValues transitionValues4 = new android.transition.TransitionValues();
            copyValues(transitionValues, transitionValues4);
            transitionValues5 = transitionValues4;
        }
        else {
            transitionValues5 = null;
        }
        if (transitionValues2 != null) {
            transitionValues3 = new android.transition.TransitionValues();
            copyValues(transitionValues2, transitionValues3);
        }
        return this.mTransition.createAnimator(viewGroup, transitionValues5, transitionValues3);
    }
    
    @Override
    public TransitionImpl excludeChildren(final int n, final boolean b) {
        this.mTransition.excludeChildren(n, b);
        return this;
    }
    
    @Override
    public TransitionImpl excludeChildren(final View view, final boolean b) {
        this.mTransition.excludeChildren(view, b);
        return this;
    }
    
    @Override
    public TransitionImpl excludeChildren(final Class clazz, final boolean b) {
        this.mTransition.excludeChildren(clazz, b);
        return this;
    }
    
    @Override
    public TransitionImpl excludeTarget(final int n, final boolean b) {
        this.mTransition.excludeTarget(n, b);
        return this;
    }
    
    @Override
    public TransitionImpl excludeTarget(final View view, final boolean b) {
        this.mTransition.excludeTarget(view, b);
        return this;
    }
    
    @Override
    public TransitionImpl excludeTarget(final Class clazz, final boolean b) {
        this.mTransition.excludeTarget(clazz, b);
        return this;
    }
    
    @Override
    public long getDuration() {
        return this.mTransition.getDuration();
    }
    
    @Override
    public TimeInterpolator getInterpolator() {
        return this.mTransition.getInterpolator();
    }
    
    @Override
    public String getName() {
        return this.mTransition.getName();
    }
    
    @Override
    public long getStartDelay() {
        return this.mTransition.getStartDelay();
    }
    
    @Override
    public List<Integer> getTargetIds() {
        return (List<Integer>)this.mTransition.getTargetIds();
    }
    
    @Override
    public List<View> getTargets() {
        return (List<View>)this.mTransition.getTargets();
    }
    
    @Override
    public String[] getTransitionProperties() {
        return this.mTransition.getTransitionProperties();
    }
    
    @Override
    public TransitionValues getTransitionValues(final View view, final boolean b) {
        final TransitionValues transitionValues = new TransitionValues();
        copyValues(this.mTransition.getTransitionValues(view, b), transitionValues);
        return transitionValues;
    }
    
    @Override
    public void init(final TransitionInterface mExternalTransition, final Object o) {
        this.mExternalTransition = mExternalTransition;
        if (o == null) {
            this.mTransition = new TransitionKitKat$TransitionWrapper(mExternalTransition);
            return;
        }
        this.mTransition = (Transition)o;
    }
    
    @Override
    public TransitionImpl removeListener(final TransitionInterfaceListener transitionInterfaceListener) {
        if (this.mCompatListener != null) {
            this.mCompatListener.removeListener(transitionInterfaceListener);
            if (this.mCompatListener.isEmpty()) {
                this.mTransition.removeListener((Transition$TransitionListener)this.mCompatListener);
                this.mCompatListener = null;
                return this;
            }
        }
        return this;
    }
    
    @Override
    public TransitionImpl removeTarget(final int n) {
        if (n > 0) {
            this.getTargetIds().remove((Object)n);
        }
        return this;
    }
    
    @Override
    public TransitionImpl removeTarget(final View view) {
        this.mTransition.removeTarget(view);
        return this;
    }
    
    @Override
    public TransitionImpl setDuration(final long duration) {
        this.mTransition.setDuration(duration);
        return this;
    }
    
    @Override
    public TransitionImpl setInterpolator(final TimeInterpolator interpolator) {
        this.mTransition.setInterpolator(interpolator);
        return this;
    }
    
    @Override
    public TransitionImpl setStartDelay(final long startDelay) {
        this.mTransition.setStartDelay(startDelay);
        return this;
    }
    
    @Override
    public String toString() {
        return this.mTransition.toString();
    }
}
