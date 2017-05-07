// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.text.TextUtils;
import android.net.Uri$Builder;
import android.os.Bundle;

@ez
public class v
{
    private a lZ;
    private boolean ma;
    private boolean mb;
    
    public v() {
        final boolean b = false;
        final Bundle bd = gb.bD();
        boolean mb = b;
        if (bd != null) {
            mb = b;
            if (bd.getBoolean("gads:block_autoclicks", false)) {
                mb = true;
            }
        }
        this.mb = mb;
    }
    
    public v(final boolean mb) {
        this.mb = mb;
    }
    
    public void a(final a lz) {
        this.lZ = lz;
    }
    
    public void ar() {
        this.ma = true;
    }
    
    public boolean av() {
        return !this.mb || this.ma;
    }
    
    public void d(final String s) {
        gs.S("Action was blocked because no click was detected.");
        if (this.lZ != null) {
            this.lZ.e(s);
        }
    }
    
    public interface a
    {
        void e(final String p0);
    }
    
    @ez
    public static class b implements a
    {
        private final fz.a mc;
        private final gv md;
        
        public b(final fz.a mc, final gv md) {
            this.mc = mc;
            this.md = md;
        }
        
        @Override
        public void e(final String s) {
            gs.S("An auto-clicking creative is blocked");
            final Uri$Builder uri$Builder = new Uri$Builder();
            uri$Builder.scheme("https");
            uri$Builder.path("//pagead2.googlesyndication.com/pagead/gen_204");
            uri$Builder.appendQueryParameter("id", "gmob-apps-blocked-navigation");
            if (!TextUtils.isEmpty((CharSequence)s)) {
                uri$Builder.appendQueryParameter("navigationURL", s);
            }
            if (this.mc != null && this.mc.vw != null && !TextUtils.isEmpty((CharSequence)this.mc.vw.tN)) {
                uri$Builder.appendQueryParameter("debugDialog", this.mc.vw.tN);
            }
            gj.c(this.md.getContext(), this.md.dy().wD, uri$Builder.toString());
        }
    }
}
