// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import org.apache.http.util.CharArrayBuffer;

public final class aj extends af
{
    public aj(final af af) {
        super(af);
    }
    
    @Override
    public final boolean a(final int n) {
        if (n == -1) {
            super.a.b(this.a());
            super.a.a(as.d);
            return true;
        }
        ++this.c;
        return false;
    }
    
    @Override
    public final boolean a(final CharArrayBuffer charArrayBuffer) {
        return true;
    }
    
    public final int b(final byte[] array, final int n, final int n2) {
        if (n2 == -1) {
            super.a.b(this.a());
            super.a.a(as.d);
            return -1;
        }
        this.c += n2;
        return n2;
    }
    
    @Override
    public final af b() {
        return as.d;
    }
    
    @Override
    public final af c() {
        return as.d;
    }
    
    @Override
    protected final int d() {
        return 0;
    }
    
    @Override
    protected final int e() {
        return Integer.MAX_VALUE;
    }
    
    @Override
    public final void f() {
        super.a.b(this.a());
        super.a.a(as.d);
    }
}
