// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import android.location.Location;

public final class bc
{
    private static Location a;
    
    public static Location a() {
        synchronized (bc.class) {
            return bc.a;
        }
    }
    
    public static boolean b() {
        synchronized (bc.class) {
            return bc.a != null;
        }
    }
}
