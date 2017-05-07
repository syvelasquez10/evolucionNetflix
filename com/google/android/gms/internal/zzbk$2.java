// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.webkit.WebView;
import android.webkit.ValueCallback;

class zzbk$2 implements Runnable
{
    final /* synthetic */ zzbk zzsh;
    ValueCallback<String> zzsi;
    final /* synthetic */ zzbh zzsj;
    final /* synthetic */ WebView zzsk;
    
    zzbk$2(final zzbk zzsh, final zzbh zzsj, final WebView zzsk) {
        this.zzsh = zzsh;
        this.zzsj = zzsj;
        this.zzsk = zzsk;
        this.zzsi = (ValueCallback<String>)new zzbk$2$1(this);
    }
    
    @Override
    public void run() {
        if (!this.zzsk.getSettings().getJavaScriptEnabled()) {
            return;
        }
        try {
            this.zzsk.evaluateJavascript("(function() { return  {text:document.body.innerText}})();", (ValueCallback)this.zzsi);
        }
        catch (Throwable t) {
            this.zzsi.onReceiveValue((Object)"");
        }
    }
}
