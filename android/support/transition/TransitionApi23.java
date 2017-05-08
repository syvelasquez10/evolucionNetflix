// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import android.annotation.TargetApi;

@TargetApi(23)
class TransitionApi23 extends TransitionKitKat
{
    @Override
    public TransitionImpl removeTarget(final int n) {
        this.mTransition.removeTarget(n);
        return this;
    }
}
