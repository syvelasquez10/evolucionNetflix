// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Map;

@zzgk
public class zzex
{
    private final zzip zzoL;
    private final boolean zzzD;
    private final String zzzE;
    
    public zzex(final zzip zzoL, final Map<String, String> map) {
        this.zzoL = zzoL;
        this.zzzE = map.get("forceOrientation");
        if (map.containsKey("allowOrientationChange")) {
            this.zzzD = Boolean.parseBoolean(map.get("allowOrientationChange"));
            return;
        }
        this.zzzD = true;
    }
    
    public void execute() {
        if (this.zzoL == null) {
            zzb.zzaE("AdWebView is null");
            return;
        }
        int requestedOrientation;
        if ("portrait".equalsIgnoreCase(this.zzzE)) {
            requestedOrientation = zzp.zzbz().zzgw();
        }
        else if ("landscape".equalsIgnoreCase(this.zzzE)) {
            requestedOrientation = zzp.zzbz().zzgv();
        }
        else if (this.zzzD) {
            requestedOrientation = -1;
        }
        else {
            requestedOrientation = zzp.zzbz().zzgx();
        }
        this.zzoL.setRequestedOrientation(requestedOrientation);
    }
}
