// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.webkit.WebView;
import org.json.JSONObject;
import android.content.Context;

@ez
public class aj implements ah
{
    private final gv md;
    
    public aj(final Context context, final gt gt) {
        this.md = gv.a(context, new ay(), false, false, null, gt);
    }
    
    private void runOnUiThread(final Runnable runnable) {
        if (gr.dt()) {
            runnable.run();
            return;
        }
        gr.wC.post(runnable);
    }
    
    @Override
    public void a(final a a) {
        this.md.dv().a((gw.a)new gw.a() {
            @Override
            public void a(final gv gv) {
                a.aM();
            }
        });
    }
    
    @Override
    public void a(final t t, final dn dn, final bw bw, final dq dq, final boolean b, final bz bz) {
        this.md.dv().a(t, dn, bw, dq, b, bz, new v(false));
    }
    
    @Override
    public void a(final String s, final by by) {
        this.md.dv().a(s, by);
    }
    
    @Override
    public void a(final String s, final JSONObject jsonObject) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                aj.this.md.a(s, jsonObject);
            }
        });
    }
    
    @Override
    public void destroy() {
        this.md.destroy();
    }
    
    @Override
    public void f(final String s) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                aj.this.md.loadUrl(s);
            }
        });
    }
    
    @Override
    public void g(final String s) {
        this.md.dv().a(s, null);
    }
    
    @Override
    public void pause() {
        gj.a(this.md);
    }
    
    @Override
    public void resume() {
        gj.b(this.md);
    }
}
