// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.webkit.WebView;
import java.util.List;
import com.google.ads.mediation.admob.AdMobAdapter;
import android.os.SystemClock;
import android.content.Context;

class fe$3 implements Runnable
{
    final /* synthetic */ fe tr;
    
    fe$3(final fe tr) {
        this.tr = tr;
    }
    
    @Override
    public void run() {
        while (true) {
            synchronized (this.tr.mw) {
                if (this.tr.sZ.errorCode != -2) {
                    return;
                }
                this.tr.md.dv().a(this.tr);
                if (this.tr.sZ.errorCode == -3) {
                    gs.V("Loading URL in WebView: " + this.tr.sZ.rP);
                    this.tr.md.loadUrl(this.tr.sZ.rP);
                    return;
                }
            }
            gs.V("Loading HTML in WebView.");
            this.tr.md.loadDataWithBaseURL(gj.L(this.tr.sZ.rP), this.tr.sZ.tG, "text/html", "UTF-8", (String)null);
        }
    }
}
