// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.ViewConfiguration;
import android.os.Build$VERSION;

public class ViewConfigurationCompat
{
    static final ViewConfigurationCompat$ViewConfigurationVersionImpl IMPL;
    
    static {
        if (Build$VERSION.SDK_INT >= 14) {
            IMPL = new ViewConfigurationCompat$IcsViewConfigurationVersionImpl();
            return;
        }
        if (Build$VERSION.SDK_INT >= 11) {
            IMPL = new ViewConfigurationCompat$HoneycombViewConfigurationVersionImpl();
            return;
        }
        if (Build$VERSION.SDK_INT >= 8) {
            IMPL = new ViewConfigurationCompat$FroyoViewConfigurationVersionImpl();
            return;
        }
        IMPL = new ViewConfigurationCompat$BaseViewConfigurationVersionImpl();
    }
    
    public static int getScaledPagingTouchSlop(final ViewConfiguration viewConfiguration) {
        return ViewConfigurationCompat.IMPL.getScaledPagingTouchSlop(viewConfiguration);
    }
    
    public static boolean hasPermanentMenuKey(final ViewConfiguration viewConfiguration) {
        return ViewConfigurationCompat.IMPL.hasPermanentMenuKey(viewConfiguration);
    }
}
