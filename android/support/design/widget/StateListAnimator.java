// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.util.StateSet;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import android.view.animation.Animation;
import android.view.animation.Animation$AnimationListener;

final class StateListAnimator
{
    private Animation$AnimationListener mAnimationListener;
    private StateListAnimator$Tuple mLastMatch;
    private Animation mRunningAnimation;
    private final ArrayList<StateListAnimator$Tuple> mTuples;
    private WeakReference<View> mViewRef;
    
    StateListAnimator() {
        this.mTuples = new ArrayList<StateListAnimator$Tuple>();
        this.mLastMatch = null;
        this.mRunningAnimation = null;
        this.mAnimationListener = (Animation$AnimationListener)new StateListAnimator$1(this);
    }
    
    private void cancel() {
        if (this.mRunningAnimation != null) {
            final View target = this.getTarget();
            if (target != null && target.getAnimation() == this.mRunningAnimation) {
                target.clearAnimation();
            }
            this.mRunningAnimation = null;
        }
    }
    
    private void clearTarget() {
        final View target = this.getTarget();
        for (int size = this.mTuples.size(), i = 0; i < size; ++i) {
            if (target.getAnimation() == this.mTuples.get(i).mAnimation) {
                target.clearAnimation();
            }
        }
        this.mViewRef = null;
        this.mLastMatch = null;
        this.mRunningAnimation = null;
    }
    
    private void start(final StateListAnimator$Tuple stateListAnimator$Tuple) {
        this.mRunningAnimation = stateListAnimator$Tuple.mAnimation;
        final View target = this.getTarget();
        if (target != null) {
            target.startAnimation(this.mRunningAnimation);
        }
    }
    
    public void addState(final int[] array, final Animation animation) {
        final StateListAnimator$Tuple stateListAnimator$Tuple = new StateListAnimator$Tuple(array, animation, null);
        animation.setAnimationListener(this.mAnimationListener);
        this.mTuples.add(stateListAnimator$Tuple);
    }
    
    View getTarget() {
        if (this.mViewRef == null) {
            return null;
        }
        return this.mViewRef.get();
    }
    
    public void jumpToCurrentState() {
        if (this.mRunningAnimation != null) {
            final View target = this.getTarget();
            if (target != null && target.getAnimation() == this.mRunningAnimation) {
                target.clearAnimation();
            }
        }
    }
    
    void setState(final int[] array) {
        while (true) {
            for (int size = this.mTuples.size(), i = 0; i < size; ++i) {
                final StateListAnimator$Tuple stateListAnimator$Tuple = this.mTuples.get(i);
                if (StateSet.stateSetMatches(stateListAnimator$Tuple.mSpecs, array)) {
                    final StateListAnimator$Tuple mLastMatch = stateListAnimator$Tuple;
                    if (mLastMatch != this.mLastMatch) {
                        if (this.mLastMatch != null) {
                            this.cancel();
                        }
                        if ((this.mLastMatch = mLastMatch) != null) {
                            this.start(mLastMatch);
                        }
                    }
                    return;
                }
            }
            final StateListAnimator$Tuple mLastMatch = null;
            continue;
        }
    }
    
    void setTarget(final View view) {
        final View target = this.getTarget();
        if (target != view) {
            if (target != null) {
                this.clearTarget();
            }
            if (view != null) {
                this.mViewRef = new WeakReference<View>(view);
            }
        }
    }
}
