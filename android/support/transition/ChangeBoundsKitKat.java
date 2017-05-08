// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import android.transition.ChangeBounds;
import android.annotation.TargetApi;

@TargetApi(19)
class ChangeBoundsKitKat extends TransitionKitKat
{
    public ChangeBoundsKitKat(final TransitionInterface transitionInterface) {
        this.init(transitionInterface, new ChangeBounds());
    }
}
