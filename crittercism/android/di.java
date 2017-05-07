// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.net.URL;

final class di extends dj
{
    private bs a;
    private bs b;
    private au c;
    private URL d;
    private cz e;
    private cy f;
    
    di(final bs b, final bs a, final au c, final URL d, final cz e, final cy f) {
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
        new dk(this.f.a(this.c).a(this.b), new dd(this.d), true, this.e).run();
    }
}
