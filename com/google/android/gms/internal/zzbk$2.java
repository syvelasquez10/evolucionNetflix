// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.webkit.WebView;
import android.webkit.ValueCallback;

class zzbk$2 implements Runnable
{
    final /* synthetic */ zzbk zzrW;
    ValueCallback<String> zzrX;
    final /* synthetic */ zzbh zzrY;
    final /* synthetic */ WebView zzrZ;
    
    zzbk$2(final zzbk zzrW, final zzbh zzrY, final WebView zzrZ) {
        this.zzrW = zzrW;
        this.zzrY = zzrY;
        this.zzrZ = zzrZ;
        this.zzrX = (ValueCallback<String>)new zzbk$2$1(this);
    }
    
    @Override
    public void run() {
        if (!this.zzrZ.getSettings().getJavaScriptEnabled()) {
            return;
        }
        try {
            this.zzrZ.evaluateJavascript("(function() { return  {text:document.body.innerText}})();", (ValueCallback)this.zzrX);
        }
        catch (Throwable t) {
            this.zzrX.onReceiveValue((Object)"");
        }
    }
}
