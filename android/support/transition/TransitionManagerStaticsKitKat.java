// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.ViewGroup;
import android.annotation.TargetApi;

@TargetApi(19)
class TransitionManagerStaticsKitKat extends TransitionManagerStaticsImpl
{
    @Override
    public void beginDelayedTransition(final ViewGroup viewGroup, final TransitionImpl transitionImpl) {
        Transition mTransition;
        if (transitionImpl == null) {
            mTransition = null;
        }
        else {
            mTransition = ((TransitionKitKat)transitionImpl).mTransition;
        }
        TransitionManager.beginDelayedTransition(viewGroup, mTransition);
    }
}
