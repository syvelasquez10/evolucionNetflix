// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.webkit.WebView;
import android.webkit.ValueCallback;

class an$2 implements Runnable
{
    final /* synthetic */ an nB;
    ValueCallback<String> nC;
    final /* synthetic */ ak nD;
    final /* synthetic */ WebView nE;
    
    an$2(final an nb, final ak nd, final WebView ne) {
        this.nB = nb;
        this.nD = nd;
        this.nE = ne;
        this.nC = (ValueCallback<String>)new an$2$1(this);
    }
    
    @Override
    public void run() {
        if (this.nE.getSettings().getJavaScriptEnabled()) {
            this.nE.evaluateJavascript("(function() { return  {text:document.body.innerText}})();", (ValueCallback)this.nC);
        }
    }
}
