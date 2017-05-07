// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.ViewGroup;
import android.os.Build$VERSION;

public class ViewGroupCompat
{
    static final ViewGroupCompat$ViewGroupCompatImpl IMPL;
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 21) {
            IMPL = new ViewGroupCompat$ViewGroupCompatLollipopImpl();
            return;
        }
        if (sdk_INT >= 18) {
            IMPL = new ViewGroupCompat$ViewGroupCompatJellybeanMR2Impl();
            return;
        }
        if (sdk_INT >= 14) {
            IMPL = new ViewGroupCompat$ViewGroupCompatIcsImpl();
            return;
        }
        if (sdk_INT >= 11) {
            IMPL = new ViewGroupCompat$ViewGroupCompatHCImpl();
            return;
        }
        IMPL = new ViewGroupCompat$ViewGroupCompatStubImpl();
    }
    
    public static void setMotionEventSplittingEnabled(final ViewGroup viewGroup, final boolean b) {
        ViewGroupCompat.IMPL.setMotionEventSplittingEnabled(viewGroup, b);
    }
}
