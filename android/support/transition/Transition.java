// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import java.util.List;
import android.animation.TimeInterpolator;
import android.animation.Animator;
import android.view.ViewGroup;
import android.view.View;
import android.os.Build$VERSION;

public abstract class Transition implements TransitionInterface
{
    TransitionImpl mImpl;
    
    public Transition() {
        this(false);
    }
    
    Transition(final boolean b) {
        if (!b) {
            if (Build$VERSION.SDK_INT >= 23) {
                this.mImpl = new TransitionApi23();
            }
            else if (Build$VERSION.SDK_INT >= 19) {
                this.mImpl = new TransitionKitKat();
            }
            else {
                this.mImpl = new TransitionIcs();
            }
            this.mImpl.init(this);
        }
    }
    
    public Transition addListener(final Transition$TransitionListener transition$TransitionListener) {
        this.mImpl.addListener(transition$TransitionListener);
        return this;
    }
    
    public Transition addTarget(final int n) {
        this.mImpl.addTarget(n);
        return this;
    }
    
    public Transition addTarget(final View view) {
        this.mImpl.addTarget(view);
        return this;
    }
    
    @Override
    public abstract void captureEndValues(final TransitionValues p0);
    
    @Override
    public abstract void captureStartValues(final TransitionValues p0);
    
    @Override
    public Animator createAnimator(final ViewGroup viewGroup, final TransitionValues transitionValues, final TransitionValues transitionValues2) {
        return null;
    }
    
    public Transition excludeChildren(final int n, final boolean b) {
        this.mImpl.excludeChildren(n, b);
        return this;
    }
    
    public Transition excludeChildren(final View view, final boolean b) {
        this.mImpl.excludeChildren(view, b);
        return this;
    }
    
    public Transition excludeChildren(final Class clazz, final boolean b) {
        this.mImpl.excludeChildren(clazz, b);
        return this;
    }
    
    public Transition excludeTarget(final int n, final boolean b) {
        this.mImpl.excludeTarget(n, b);
        return this;
    }
    
    public Transition excludeTarget(final View view, final boolean b) {
        this.mImpl.excludeTarget(view, b);
        return this;
    }
    
    public Transition excludeTarget(final Class clazz, final boolean b) {
        this.mImpl.excludeTarget(clazz, b);
        return this;
    }
    
    public long getDuration() {
        return this.mImpl.getDuration();
    }
    
    public TimeInterpolator getInterpolator() {
        return this.mImpl.getInterpolator();
    }
    
    public String getName() {
        return this.mImpl.getName();
    }
    
    public long getStartDelay() {
        return this.mImpl.getStartDelay();
    }
    
    public List<Integer> getTargetIds() {
        return this.mImpl.getTargetIds();
    }
    
    public List<View> getTargets() {
        return this.mImpl.getTargets();
    }
    
    public String[] getTransitionProperties() {
        return this.mImpl.getTransitionProperties();
    }
    
    public TransitionValues getTransitionValues(final View view, final boolean b) {
        return this.mImpl.getTransitionValues(view, b);
    }
    
    public Transition removeListener(final Transition$TransitionListener transition$TransitionListener) {
        this.mImpl.removeListener(transition$TransitionListener);
        return this;
    }
    
    public Transition removeTarget(final int n) {
        this.mImpl.removeTarget(n);
        return this;
    }
    
    public Transition removeTarget(final View view) {
        this.mImpl.removeTarget(view);
        return this;
    }
    
    public Transition setDuration(final long duration) {
        this.mImpl.setDuration(duration);
        return this;
    }
    
    public Transition setInterpolator(final TimeInterpolator interpolator) {
        this.mImpl.setInterpolator(interpolator);
        return this;
    }
    
    public Transition setStartDelay(final long startDelay) {
        this.mImpl.setStartDelay(startDelay);
        return this;
    }
    
    @Override
    public String toString() {
        return this.mImpl.toString();
    }
}
