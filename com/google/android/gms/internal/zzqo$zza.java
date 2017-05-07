// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;

public class zzqo$zza
{
    private final Status zzQA;
    private final zzqo$zza$zza zzaTJ;
    private final byte[] zzaTK;
    private final long zzaTL;
    private final zzqi zzaTM;
    private final zzqp$zzc zzaTN;
    
    public zzqo$zza(final Status status, final zzqi zzqi, final zzqo$zza$zza zzqo$zza$zza) {
        this(status, zzqi, null, null, zzqo$zza$zza, 0L);
    }
    
    public zzqo$zza(final Status zzQA, final zzqi zzaTM, final byte[] zzaTK, final zzqp$zzc zzaTN, final zzqo$zza$zza zzaTJ, final long zzaTL) {
        this.zzQA = zzQA;
        this.zzaTM = zzaTM;
        this.zzaTK = zzaTK;
        this.zzaTN = zzaTN;
        this.zzaTJ = zzaTJ;
        this.zzaTL = zzaTL;
    }
    
    public Status getStatus() {
        return this.zzQA;
    }
    
    public zzqp$zzc zzBA() {
        return this.zzaTN;
    }
    
    public long zzBB() {
        return this.zzaTL;
    }
    
    public zzqo$zza$zza zzBx() {
        return this.zzaTJ;
    }
    
    public byte[] zzBy() {
        return this.zzaTK;
    }
    
    public zzqi zzBz() {
        return this.zzaTM;
    }
}
