// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.webkit.WebView;
import java.util.List;
import com.google.ads.mediation.admob.AdMobAdapter;
import android.os.SystemClock;
import android.content.Context;

class fe$2 implements Runnable
{
    final /* synthetic */ fe tr;
    final /* synthetic */ fz ts;
    
    fe$2(final fe tr, final fz ts) {
        this.tr = tr;
        this.ts = ts;
    }
    
    @Override
    public void run() {
        synchronized (this.tr.mw) {
            this.tr.tm.a(this.ts);
        }
    }
}
