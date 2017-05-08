// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import android.view.ViewGroup;
import android.os.Build$VERSION;

public class TransitionManager
{
    private static TransitionManagerStaticsImpl sImpl;
    
    static {
        if (Build$VERSION.SDK_INT < 19) {
            TransitionManager.sImpl = new TransitionManagerStaticsIcs();
            return;
        }
        TransitionManager.sImpl = new TransitionManagerStaticsKitKat();
    }
    
    public static void beginDelayedTransition(final ViewGroup viewGroup, final Transition transition) {
        final TransitionManagerStaticsImpl sImpl = TransitionManager.sImpl;
        TransitionImpl mImpl;
        if (transition == null) {
            mImpl = null;
        }
        else {
            mImpl = transition.mImpl;
        }
        sImpl.beginDelayedTransition(viewGroup, mImpl);
    }
}
