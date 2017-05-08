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
    
    public static boolean b() {
        synchronized (ba.class) {
            return ba.a != null;
        }
    }
}
