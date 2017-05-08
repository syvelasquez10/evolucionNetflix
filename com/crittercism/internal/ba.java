// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import android.location.Location;

public final class ba
{
    private static Location a;
    
    public static Location a() {
        synchronized (ba.class) {
            return ba.a;
        }
    }
    
    public static void a(Location a) {
        // monitorenter(ba.class)
        Label_0015: {
            if (a != null) {
                break Label_0015;
            }
            try {
                while (true) {
                    ba.a = a;
                    return;
                    a = new Location(a);
                    continue;
                }
            }
            finally {
            }
            // monitorexit(ba.class)
        }
    }
    
    public static boolean b() {
        synchronized (ba.class) {
            return ba.a != null;
        }
    }
}
