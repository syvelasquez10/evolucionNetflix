// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.text.TextUtils;
import android.net.Uri$Builder;

@ez
public class v$b implements v$a
{
    private final fz$a mc;
    private final gv md;
    
    public v$b(final fz$a mc, final gv md) {
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
