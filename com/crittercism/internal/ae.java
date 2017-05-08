// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

public final class ae extends aa
{
    private int d;
    
    public ae(final aa aa) {
        super(aa);
        this.d = -1;
    }
    
    @Override
    public final boolean a(final ab ab) {
        int n = ab.b;
        if (n > ab.b) {
            n = ab.b;
        }
        while (true) {
            int b = 0;
            Label_0072: {
                if (n < 0) {
                    break Label_0072;
                }
                int n2 = 0;
            Label_0040_Outer:
                while (true) {
                    if (n2 >= n) {
                        break Label_0072;
                    }
                    Label_0065: {
                        if (ab.a[n2] != ';') {
                            break Label_0065;
                        }
                        while (true) {
                            b = ab.b;
                            if (n2 <= 0) {
                                break Label_0072;
                            }
                            try {
                                this.d = Integer.parseInt(ab.a(n2), 16);
                                return true;
                                ++n2;
                                continue Label_0040_Outer;
                                n2 = -1;
                                continue;
                            }
                            catch (NumberFormatException ex) {
                                return false;
                            }
                            break;
                        }
                    }
                    break;
                }
            }
            int n2 = b;
            continue;
        }
    }
    
    @Override
    public final aa b() {
        if (this.d == 0) {
            return new am(this);
        }
        super.b.b = 0;
        return new ad(this, this.d);
    }
    
    @Override
    public final aa c() {
        return ao.d;
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
        super.a.a(ao.d);
    }
}
