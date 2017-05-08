// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import android.annotation.TargetApi;

@TargetApi(14)
class AutoTransitionPort extends TransitionSetPort
{
    public AutoTransitionPort() {
        this.setOrdering(1);
        this.addTransition(new FadePort(2)).addTransition(new ChangeBoundsPort()).addTransition(new FadePort(1));
    }
}
