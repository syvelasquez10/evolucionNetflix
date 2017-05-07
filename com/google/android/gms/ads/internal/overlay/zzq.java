// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import com.google.android.gms.internal.zzid;

class zzq implements Runnable
{
    private boolean mCancelled;
    private zzk zzCo;
    
    zzq(final zzk zzCo) {
        this.mCancelled = false;
        this.zzCo = zzCo;
    }
    
    public void cancel() {
        this.mCancelled = true;
        zzid.zzIE.removeCallbacks((Runnable)this);
    }
    
    @Override
    public void run() {
        if (!this.mCancelled) {
            this.zzCo.zzeX();
            this.zzfg();
        }
    }
    
    public void zzfg() {
        zzid.zzIE.postDelayed((Runnable)this, 250L);
    }
}
