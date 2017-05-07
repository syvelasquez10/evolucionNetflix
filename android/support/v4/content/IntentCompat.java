// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content;

import android.content.Intent;
import android.content.ComponentName;
import android.os.Build$VERSION;

public class IntentCompat
{
    private static final IntentCompat$IntentCompatImpl IMPL;
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 15) {
            IMPL = new IntentCompat$IntentCompatImplIcsMr1();
            return;
        }
        if (sdk_INT >= 11) {
            IMPL = new IntentCompat$IntentCompatImplHC();
            return;
        }
        IMPL = new IntentCompat$IntentCompatImplBase();
    }
    
    public static Intent makeMainActivity(final ComponentName componentName) {
        return IntentCompat.IMPL.makeMainActivity(componentName);
    }
}
