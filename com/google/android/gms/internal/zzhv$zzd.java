// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.webkit.WebSettings;
import android.content.Context;

class zzhv$zzd extends zzhv$zzf
{
    @Override
    public String getDefaultUserAgent(final Context context) {
        return WebSettings.getDefaultUserAgent(context);
    }
    
    @Override
    public boolean zza(final Context context, final WebSettings webSettings) {
        super.zza(context, webSettings);
        webSettings.setMediaPlaybackRequiresUserGesture(false);
        return true;
    }
}
