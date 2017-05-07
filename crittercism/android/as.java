// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import org.apache.http.util.CharArrayBuffer;

public final class as extends af
{
    public static final as d;
    
    static {
        d = new as();
    }
    
    private as() {
        super((al)null);
    }
    
    @Override
    public final boolean a(final int n) {
        ++this.c;
        return false;
    }
    
    @Override
    public final boolean a(final CharArrayBuffer charArrayBuffer) {
        return true;
    }
    
    public final int b(final byte[] array, final int n, final int n2) {
        this.c += n2;
        return -1;
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
