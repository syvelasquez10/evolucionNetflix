// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

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
    
    public static int getAbsoluteGravity(final int n, final int n2) {
        return GravityCompat.IMPL.getAbsoluteGravity(n, n2);
    }
}
