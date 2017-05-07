// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import com.google.android.gms.internal.zzhu;

class zzq implements Runnable
{
    private boolean mCancelled;
    private zzk zzAy;
    
    zzq(final zzk zzAy) {
        this.mCancelled = false;
        this.zzAy = zzAy;
    }
    
    public void cancel() {
        this.mCancelled = true;
        zzhu.zzHK.removeCallbacks((Runnable)this);
    }
    
    @Override
    public void run() {
        if (!this.mCancelled) {
            this.zzAy.zzeR();
            this.zzfa();
        }
    }
    
    public void zzfa() {
        zzhu.zzHK.postDelayed((Runnable)this, 250L);
    }
}
