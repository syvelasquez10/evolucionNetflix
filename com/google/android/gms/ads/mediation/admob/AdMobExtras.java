// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.mediation.admob;

import android.os.Bundle;
import com.google.ads.mediation.NetworkExtras;

public final class AdMobExtras implements NetworkExtras
{
    private final Bundle jh;
    
    public AdMobExtras(Bundle jh) {
        if (jh != null) {
            jh = new Bundle(jh);
        }
        else {
            jh = null;
        }
        this.jh = jh;
    }
    
    public Bundle getExtras() {
        return this.jh;
    }
}
