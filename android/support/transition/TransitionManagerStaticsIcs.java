// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import android.view.ViewGroup;
import android.annotation.TargetApi;

@TargetApi(14)
class TransitionManagerStaticsIcs extends TransitionManagerStaticsImpl
{
    @Override
    public void beginDelayedTransition(final ViewGroup viewGroup, final TransitionImpl transitionImpl) {
        TransitionPort mTransition;
        if (transitionImpl == null) {
            mTransition = null;
        }
        else {
            mTransition = ((TransitionIcs)transitionImpl).mTransition;
        }
        TransitionManagerPort.beginDelayedTransition(viewGroup, mTransition);
    }
}
