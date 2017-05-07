// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.webkit.WebChromeClient;
import android.net.http.SslError;

public class zzie$zzc extends zzie$zzb
{
    @Override
    public String zza(final SslError sslError) {
        return sslError.getUrl();
    }
    
    @Override
    public WebChromeClient zzf(final zziz zziz) {
        return new zzjh(zziz);
    }
}
