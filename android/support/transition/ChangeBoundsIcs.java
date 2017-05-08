// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import android.annotation.TargetApi;

@TargetApi(14)
class ChangeBoundsIcs extends TransitionIcs
{
    public ChangeBoundsIcs(final TransitionInterface transitionInterface) {
        this.init(transitionInterface, new ChangeBoundsPort());
    }
}
