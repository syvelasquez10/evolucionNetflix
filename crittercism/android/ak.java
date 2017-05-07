// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.message.BasicLineParser;
import org.apache.http.util.CharArrayBuffer;

public abstract class ak extends af
{
    boolean d;
    int e;
    boolean f;
    private boolean g;
    private boolean h;
    
    public ak(final af af) {
        super(af);
        this.d = false;
        this.g = false;
        this.h = false;
        this.f = false;
    }
    
    @Override
    public final boolean a(final CharArrayBuffer charArrayBuffer) {
        final int length = super.b.length();
        int n;
        if (length == 0 || (length == 1 && super.b.charAt(0) == '\r')) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n != 0) {
            this.h = true;
        }
        else {
            try {
                final Header header = BasicLineParser.DEFAULT.parseHeader(charArrayBuffer);
                if (!this.d && header.getName().equalsIgnoreCase("content-length")) {
                    final int int1 = Integer.parseInt(header.getValue());
                    if (int1 < 0) {
                        return false;
                    }
                    this.d = true;
                    this.e = int1;
                    return true;
                }
                else {
                    if (header.getName().equalsIgnoreCase("transfer-encoding")) {
                        this.f = header.getValue().equalsIgnoreCase("chunked");
                        return true;
                    }
                    if (!this.g && header.getName().equalsIgnoreCase("host")) {
                        final String value = header.getValue();
                        if (value != null) {
                            this.g = true;
                            super.a.a(value);
                            return true;
                        }
                    }
                }
            }
            catch (ParseException ex) {
                return false;
            }
            catch (NumberFormatException ex2) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public final af b() {
        if (this.h) {
            return this.g();
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
        return 32;
    }
    
    @Override
    protected final int e() {
        return 128;
    }
    
    protected abstract af g();
}
