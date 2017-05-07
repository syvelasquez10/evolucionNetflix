// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Map;

@zzgr
public class zzfd
{
    private final boolean zzAq;
    private final String zzAr;
    private final zziz zzoM;
    
    public zzfd(final zziz zzoM, final Map<String, String> map) {
        this.zzoM = zzoM;
        this.zzAr = map.get("forceOrientation");
        if (map.containsKey("allowOrientationChange")) {
            this.zzAq = Boolean.parseBoolean(map.get("allowOrientationChange"));
            return;
        }
        this.zzAq = true;
    }
    
    public void execute() {
        if (this.zzoM == null) {
            zzb.zzaH("AdWebView is null");
            return;
        }
        int requestedOrientation;
        if ("portrait".equalsIgnoreCase(this.zzAr)) {
            requestedOrientation = zzp.zzbx().zzgH();
        }
        else if ("landscape".equalsIgnoreCase(this.zzAr)) {
            requestedOrientation = zzp.zzbx().zzgG();
        }
        else if (this.zzAq) {
            requestedOrientation = -1;
        }
        else {
            requestedOrientation = zzp.zzbx().zzgI();
        }
        this.zzoM.setRequestedOrientation(requestedOrientation);
    }
}
