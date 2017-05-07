// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.SystemClock;

@ez
final class ga$a
{
    private long vH;
    private long vI;
    
    public ga$a() {
        this.vH = -1L;
        this.vI = -1L;
    }
    
    public long cS() {
        return this.vI;
    }
    
    public void cT() {
        this.vI = SystemClock.elapsedRealtime();
    }
    
    public void cU() {
        this.vH = SystemClock.elapsedRealtime();
    }
    
    public Bundle toBundle() {
        final Bundle bundle = new Bundle();
        bundle.putLong("topen", this.vH);
        bundle.putLong("tclose", this.vI);
        return bundle;
    }
}
