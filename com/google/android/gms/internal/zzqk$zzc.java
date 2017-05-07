// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;

class zzqk$zzc<T>
{
    private T mData;
    private Status zzQA;
    private long zzaTF;
    
    public zzqk$zzc(final Status zzQA, final T mData, final long zzaTF) {
        this.zzQA = zzQA;
        this.mData = mData;
        this.zzaTF = zzaTF;
    }
    
    public long zzBu() {
        return this.zzaTF;
    }
    
    public void zzQ(final T mData) {
        this.mData = mData;
    }
    
    public void zzU(final long zzaTF) {
        this.zzaTF = zzaTF;
    }
    
    public void zzbh(final Status zzQA) {
        this.zzQA = zzQA;
    }
}
