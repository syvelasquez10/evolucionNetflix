// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import org.apache.http.util.CharArrayBuffer;

public final class aq extends af
{
    private boolean d;
    
    public aq(final af af) {
        super(af);
        this.d = false;
    }
    
    @Override
    public final boolean a(final CharArrayBuffer charArrayBuffer) {
        boolean d = false;
        if (charArrayBuffer.substringTrimmed(0, charArrayBuffer.length()).length() == 0) {
            d = true;
        }
        this.d = d;
        return true;
    }
    
    @Override
    public final af b() {
        if (this.d) {
            super.a.b(this.a());
            return super.a.b();
        }
        super.b.clear();
        return this;
    }
    
    @Override
    public final af c() {
        super.b.clear();
        return new ar(this);
    }
    
    @Override
    protected final int d() {
        return 8;
    }
    
    @Override
    protected final int e() {
        return 128;
    }
}
