// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import org.apache.http.util.CharArrayBuffer;

public final class ai extends af
{
    private int d;
    
    public ai(final af af) {
        super(af);
        this.d = -1;
    }
    
    @Override
    public final boolean a(final CharArrayBuffer charArrayBuffer) {
        int index = charArrayBuffer.indexOf(59);
        final int length = charArrayBuffer.length();
        while (true) {
            Label_0036: {
                if (index <= 0) {
                    break Label_0036;
                }
                try {
                    this.d = Integer.parseInt(charArrayBuffer.substringTrimmed(0, index), 16);
                    return true;
                }
                catch (NumberFormatException ex) {
                    return false;
                }
            }
            index = length;
            continue;
        }
    }
    
    @Override
    public final af b() {
        final int d = this.d;
        if (this.d == 0) {
            return new aq(this);
        }
        super.b.clear();
        return new ah(this, this.d);
    }
    
    @Override
    public final af c() {
        return as.d;
    }
    
    @Override
    protected final int d() {
        return 16;
    }
    
    @Override
    protected final int e() {
        return 256;
    }
    
    @Override
    public final void f() {
        super.a.b(this.a());
        super.a.a(as.d);
    }
}
