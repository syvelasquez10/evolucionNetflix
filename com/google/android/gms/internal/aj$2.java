// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.webkit.WebView;
import org.json.JSONObject;
import android.content.Context;

class aj$2 implements Runnable
{
    final /* synthetic */ String mY;
    final /* synthetic */ aj nd;
    
    aj$2(final aj nd, final String my) {
        this.nd = nd;
        this.mY = my;
    }
    
    @Override
    public void run() {
        this.nd.md.loadUrl(this.mY);
    }
}
