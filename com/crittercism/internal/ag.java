// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

public abstract class ag extends aa
{
    boolean d;
    int e;
    boolean f;
    private boolean g;
    private boolean h;
    
    public ag(final aa aa) {
        super(aa);
        this.d = false;
        this.g = false;
        this.h = false;
        this.f = false;
    }
    
    @Override
    public final boolean a(final ab ab) {
        final int b = super.b.b;
        int n;
        if (b == 0 || (b == 1 && super.b.a[0] == '\r')) {
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
                final String[] split = ab.toString().split(":", 2);
                if (split.length != 2) {
                    return false;
                }
                final String trim = split[0].trim();
                final String trim2 = split[1].trim();
                if (!this.d && trim.equalsIgnoreCase("content-length")) {
                    final int int1 = Integer.parseInt(trim2);
                    if (int1 < 0) {
                        return false;
                    }
                    this.d = true;
                    this.e = int1;
                    return true;
                }
                else {
                    if (trim.equalsIgnoreCase("transfer-encoding")) {
                        this.f = trim2.equalsIgnoreCase("chunked");
                        return true;
                    }
                    if (!this.g && trim.equalsIgnoreCase("host") && trim2 != null) {
                        this.g = true;
                        super.a.a(trim2);
                        return true;
                    }
                }
            }
            catch (NumberFormatException ex) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public final aa b() {
        if (this.h) {
            return this.g();
        }
        super.b.b = 0;
        return this;
    }
    
    @Override
    public final aa c() {
        super.b.b = 0;
        return new an(this);
    }
    
    @Override
    protected final int d() {
        return 32;
    }
    
    @Override
    protected final int e() {
        return 128;
    }
    
    protected abstract aa g();
}
