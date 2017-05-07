// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Result;

public class zzqo implements Result
{
    private final zzqo$zza zzaTI;
    
    public zzqo(final zzqo$zza zzaTI) {
        this.zzaTI = zzaTI;
    }
    
    @Override
    public Status getStatus() {
        return this.zzaTI.getStatus();
    }
    
    public zzqo$zza zzBw() {
        return this.zzaTI;
    }
}
