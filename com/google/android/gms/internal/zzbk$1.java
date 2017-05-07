// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.View;

class zzbk$1 implements Runnable
{
    final /* synthetic */ View zzrV;
    final /* synthetic */ zzbk zzrW;
    
    zzbk$1(final zzbk zzrW, final View zzrV) {
        this.zzrW = zzrW;
        this.zzrV = zzrV;
    }
    
    @Override
    public void run() {
        this.zzrW.zzg(this.zzrV);
    }
}
