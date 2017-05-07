// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import java.util.Random;

public class zzl extends zzu$zza
{
    private Object zzpc;
    private final Random zzsT;
    private long zzsU;
    
    public zzl() {
        this.zzpc = new Object();
        this.zzsT = new Random();
        this.zzcK();
    }
    
    public long getValue() {
        return this.zzsU;
    }
    
    public void zzcK() {
        final Object zzpc = this.zzpc;
        // monitorenter(zzpc)
        int n = 3;
        long zzsU = 0L;
        while (true) {
            final int n2 = n - 1;
            Label_0065: {
                if (n2 <= 0) {
                    break Label_0065;
                }
                try {
                    final long n3 = zzsU = this.zzsT.nextInt() + 2147483648L;
                    n = n2;
                    if (n3 == this.zzsU) {
                        continue;
                    }
                    zzsU = n3;
                    n = n2;
                    if (n3 != 0L) {
                        zzsU = n3;
                        this.zzsU = zzsU;
                        return;
                    }
                    continue;
                }
                finally {
                }
                // monitorexit(zzpc)
            }
        }
    }
}
