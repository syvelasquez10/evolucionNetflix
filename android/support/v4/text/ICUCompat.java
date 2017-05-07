// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.text;

import android.os.Build$VERSION;

public class ICUCompat
{
    private static final ICUCompat$ICUCompatImpl IMPL;
    
    static {
        if (Build$VERSION.SDK_INT >= 14) {
            IMPL = new ICUCompat$ICUCompatImplIcs();
            return;
        }
        IMPL = new ICUCompat$ICUCompatImplBase();
    }
    
    public static String addLikelySubtags(final String s) {
        return ICUCompat.IMPL.addLikelySubtags(s);
    }
    
    public static String getScript(final String s) {
        return ICUCompat.IMPL.getScript(s);
    }
}
