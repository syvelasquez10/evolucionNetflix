// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.util.StateSet;
import java.util.ArrayList;

final class StateListAnimator
{
    private final ValueAnimatorCompat$AnimatorListener mAnimationListener;
    private StateListAnimator$Tuple mLastMatch;
    ValueAnimatorCompat mRunningAnimator;
    private final ArrayList<StateListAnimator$Tuple> mTuples;
    
    StateListAnimator() {
        this.mTuples = new ArrayList<StateListAnimator$Tuple>();
        this.mLastMatch = null;
        this.mRunningAnimator = null;
        this.mAnimationListener = new StateListAnimator$1(this);
    }
    
    private void cancel() {
        if (this.mRunningAnimator != null) {
            this.mRunningAnimator.cancel();
            this.mRunningAnimator = null;
        }
    }
    
    private void start(final StateListAnimator$Tuple stateListAnimator$Tuple) {
        (this.mRunningAnimator = stateListAnimator$Tuple.mAnimator).start();
    }
    
    public void addState(final int[] array, final ValueAnimatorCompat valueAnimatorCompat) {
        final StateListAnimator$Tuple stateListAnimator$Tuple = new StateListAnimator$Tuple(array, valueAnimatorCompat);
        valueAnimatorCompat.addListener(this.mAnimationListener);
        this.mTuples.add(stateListAnimator$Tuple);
    }
    
    public void jumpToCurrentState() {
        if (this.mRunningAnimator != null) {
            this.mRunningAnimator.end();
            this.mRunningAnimator = null;
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
}
