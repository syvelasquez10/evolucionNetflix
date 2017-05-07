// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.webkit.WebView;
import android.content.Context;
import org.json.JSONObject;

class aj$1 implements Runnable
{
    final /* synthetic */ String nb;
    final /* synthetic */ JSONObject nc;
    final /* synthetic */ aj nd;
    
    aj$1(final aj nd, final String nb, final JSONObject nc) {
        this.nd = nd;
        this.nb = nb;
        this.nc = nc;
    }
    
    @Override
    public void run() {
        this.nd.md.a(this.nb, this.nc);
    }
}
