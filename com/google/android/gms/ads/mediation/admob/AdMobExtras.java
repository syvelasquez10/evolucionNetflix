// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.mediation.admob;

import android.os.Bundle;
import com.google.ads.mediation.NetworkExtras;

@Deprecated
public final class AdMobExtras implements NetworkExtras
{
    private final Bundle rP;
    
    public AdMobExtras(Bundle rp) {
        if (rp != null) {
            rp = new Bundle(rp);
        }
        else {
            rp = null;
        }
        this.rP = rp;
    }
    
    public Bundle getExtras() {
        return this.rP;
    }
}
