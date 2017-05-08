// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.net.URL;

final class dh extends di
{
    private bq a;
    private bq b;
    private ar c;
    private URL d;
    private cy e;
    private cx f;
    
    dh(final bq b, final bq a, final ar c, final URL d, final cy e, final cx f) {
        this.b = b;
        this.a = a;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }
    
    @Override
    public final void a() {
        this.a.a(this.b);
        new dj(this.f.a(this.c).a(this.b), new dc(this.d), true, this.e).run();
    }
}
