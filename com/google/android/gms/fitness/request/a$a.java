// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import java.util.HashMap;
import java.util.Map;

public class a$a
{
    private static final a$a TV;
    private final Map<BleScanCallback, a> TW;
    
    static {
        TV = new a$a();
    }
    
    private a$a() {
        this.TW = new HashMap<BleScanCallback, a>();
    }
    
    public static a$a iV() {
        return a$a.TV;
    }
    
    public a a(final BleScanCallback bleScanCallback) {
        synchronized (this.TW) {
            a a;
            if ((a = this.TW.get(bleScanCallback)) == null) {
                a = new a(bleScanCallback, null);
                this.TW.put(bleScanCallback, a);
            }
            return a;
        }
    }
    
    public a b(final BleScanCallback bleScanCallback) {
        synchronized (this.TW) {
            final a a = this.TW.get(bleScanCallback);
            if (a != null) {
                return a;
            }
            return new a(bleScanCallback, null);
        }
    }
}
