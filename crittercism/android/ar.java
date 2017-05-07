// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import org.apache.http.util.CharArrayBuffer;

public final class ar extends af
{
    private af d;
    
    public ar(final af d) {
        super(d);
        this.d = d;
    }
    
    @Override
    public final boolean a(final int n) {
        if (n == -1) {
            super.a.a(as.d);
            return true;
        }
        ++this.c;
        if ((char)n == '\n') {
            this.d.b(this.a());
            super.a.a(this.d);
            return true;
        }
        return false;
    }
    
    @Override
    public final boolean a(final CharArrayBuffer charArrayBuffer) {
        return true;
    }
    
    @Override
    public final af b() {
        return this;
    }
    
    @Override
    public final af c() {
        return this;
    }
    
    @Override
    protected final int d() {
        return 0;
    }
    
    @Override
    protected final int e() {
        return 0;
    }
}
