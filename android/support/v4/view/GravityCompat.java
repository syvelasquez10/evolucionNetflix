// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.graphics.Rect;
import android.os.Build$VERSION;

public class GravityCompat
{
    static final GravityCompat$GravityCompatImpl IMPL;
    
    static {
        if (Build$VERSION.SDK_INT >= 17) {
            IMPL = new GravityCompat$GravityCompatImplJellybeanMr1();
            return;
        }
        IMPL = new GravityCompat$GravityCompatImplBase();
    }
    
    public static void apply(final int n, final int n2, final int n3, final Rect rect, final Rect rect2, final int n4) {
        GravityCompat.IMPL.apply(n, n2, n3, rect, rect2, n4);
    }
    
    public static int getAbsoluteGravity(final int n, final int n2) {
        return GravityCompat.IMPL.getAbsoluteGravity(n, n2);
    }
}
