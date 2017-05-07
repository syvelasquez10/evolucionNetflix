// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.BlockingQueue;

public class zzca
{
    BlockingQueue<zzcd> zzvi;
    
    public boolean zza(final zzcd zzcd) {
        return this.zzvi.offer(zzcd);
    }
}
