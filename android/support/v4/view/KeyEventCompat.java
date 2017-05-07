// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.KeyEvent;
import android.os.Build$VERSION;

public class KeyEventCompat
{
    static final KeyEventCompat$KeyEventVersionImpl IMPL;
    
    static {
        if (Build$VERSION.SDK_INT >= 11) {
            IMPL = new KeyEventCompat$HoneycombKeyEventVersionImpl();
            return;
        }
        IMPL = new KeyEventCompat$BaseKeyEventVersionImpl();
    }
    
    public static boolean hasModifiers(final KeyEvent keyEvent, final int n) {
        return KeyEventCompat.IMPL.metaStateHasModifiers(keyEvent.getMetaState(), n);
    }
    
    public static boolean hasNoModifiers(final KeyEvent keyEvent) {
        return KeyEventCompat.IMPL.metaStateHasNoModifiers(keyEvent.getMetaState());
    }
    
    public static void startTracking(final KeyEvent keyEvent) {
        KeyEventCompat.IMPL.startTracking(keyEvent);
    }
}
