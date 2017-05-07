// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.webkit.WebView;
import android.text.TextUtils;
import android.webkit.WebViewClient;
import android.os.Looper;
import android.os.Handler;

@ez
public class fc implements Runnable
{
    private final int lf;
    private final int lg;
    protected final gv md;
    private final Handler td;
    private final long te;
    private long tf;
    private gw$a tg;
    protected boolean th;
    protected boolean ti;
    
    public fc(final gw$a gw$a, final gv gv, final int n, final int n2) {
        this(gw$a, gv, n, n2, 200L, 50L);
    }
    
    public fc(final gw$a tg, final gv md, final int lf, final int lg, final long te, final long tf) {
        this.te = te;
        this.tf = tf;
        this.td = new Handler(Looper.getMainLooper());
        this.md = md;
        this.tg = tg;
        this.th = false;
        this.ti = false;
        this.lg = lg;
        this.lf = lf;
    }
    
    public void a(final fk fk, final ha webViewClient) {
        this.md.setWebViewClient((WebViewClient)webViewClient);
        final gv md = this.md;
        String l;
        if (TextUtils.isEmpty((CharSequence)fk.rP)) {
            l = null;
        }
        else {
            l = gj.L(fk.rP);
        }
        md.loadDataWithBaseURL(l, fk.tG, "text/html", "UTF-8", (String)null);
    }
    
    public void b(final fk fk) {
        this.a(fk, new ha(this, this.md, fk.tP));
    }
    
    public void cA() {
        synchronized (this) {
            this.th = true;
        }
    }
    
    public boolean cB() {
        synchronized (this) {
            return this.th;
        }
    }
    
    public boolean cC() {
        return this.ti;
    }
    
    public void cz() {
        this.td.postDelayed((Runnable)this, this.te);
    }
    
    @Override
    public void run() {
        if (this.md == null || this.cB()) {
            this.tg.a(this.md);
            return;
        }
        new fc$a(this, this.md).execute((Object[])new Void[0]);
    }
}
