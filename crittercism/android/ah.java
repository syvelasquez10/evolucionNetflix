// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import org.apache.http.util.CharArrayBuffer;

public final class ah extends af
{
    private ai d;
    private int e;
    private int f;
    
    public ah(final ai d, final int e) {
        super(d);
        this.f = 0;
        this.d = d;
        this.e = e;
    }
    
    @Override
    public final boolean a(int n) {
        if (this.f < this.e + 2) {
            if (n == -1) {
                super.a.b(this.a());
                super.a.a(as.d);
                return true;
            }
            ++this.c;
            n = (char)n;
            ++this.f;
            if (this.f > this.e) {
                if (n == 10) {
                    this.d.b(this.a());
                    super.a.a(this.d);
                    return true;
                }
                if (this.f == this.e + 2 && n != 10) {
                    super.a.a(as.d);
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public final boolean a(final CharArrayBuffer charArrayBuffer) {
        return true;
    }
    
    @Override
    public final af b() {
        return this.d;
    }
    
    @Override
    public final af c() {
        return null;
    }
    
    @Override
    protected final int d() {
        return 0;
    }
    
    @Override
    protected final int e() {
        return 0;
    }
    
    @Override
    public final void f() {
        super.a.b(this.a());
        super.a.a(as.d);
    }
}
