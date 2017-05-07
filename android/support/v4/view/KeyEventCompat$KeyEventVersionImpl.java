// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.View;
import android.view.KeyEvent$Callback;
import android.view.KeyEvent;

interface KeyEventCompat$KeyEventVersionImpl
{
    boolean dispatch(final KeyEvent p0, final KeyEvent$Callback p1, final Object p2, final Object p3);
    
    Object getKeyDispatcherState(final View p0);
    
    boolean isTracking(final KeyEvent p0);
    
    boolean metaStateHasModifiers(final int p0, final int p1);
    
    boolean metaStateHasNoModifiers(final int p0);
    
    int normalizeMetaState(final int p0);
    
    void startTracking(final KeyEvent p0);
}
