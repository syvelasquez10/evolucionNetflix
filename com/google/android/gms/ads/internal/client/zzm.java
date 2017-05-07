// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import java.util.Random;

public class zzm extends zzv$zza
{
    private Object zzpd;
    private final Random zzts;
    private long zztt;
    
    public zzm() {
        this.zzpd = new Object();
        this.zzts = new Random();
        this.zzcL();
    }
    
    public long getValue() {
        return this.zztt;
    }
    
    public void zzcL() {
        final Object zzpd = this.zzpd;
        // monitorenter(zzpd)
        int n = 3;
        long zztt = 0L;
        while (true) {
            final int n2 = n - 1;
            Label_0065: {
                if (n2 <= 0) {
                    break Label_0065;
                }
                try {
                    final long n3 = zztt = this.zzts.nextInt() + 2147483648L;
                    n = n2;
                    if (n3 == this.zztt) {
                        continue;
                    }
                    zztt = n3;
                    n = n2;
                    if (n3 != 0L) {
                        zztt = n3;
                        this.zztt = zztt;
                        return;
                    }
                    continue;
                }
                finally {
                }
                // monitorexit(zzpd)
            }
        }
    }
}
