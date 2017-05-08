// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import java.util.ArrayList;
import android.support.v4.util.ArrayMap;

class TransitionManagerPort$MultiListener$1 extends TransitionPort$TransitionListenerAdapter
{
    final /* synthetic */ TransitionManagerPort$MultiListener this$0;
    final /* synthetic */ ArrayMap val$runningTransitions;
    
    TransitionManagerPort$MultiListener$1(final TransitionManagerPort$MultiListener this$0, final ArrayMap val$runningTransitions) {
        this.this$0 = this$0;
        this.val$runningTransitions = val$runningTransitions;
    }
    
    @Override
    public void onTransitionEnd(final TransitionPort transitionPort) {
        ((ArrayList)this.val$runningTransitions.get(this.this$0.mSceneRoot)).remove(transitionPort);
    }
}
