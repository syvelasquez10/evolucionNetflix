// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.util.List;
import com.crittercism.app.CrittercismConfig;

public final class az extends CrittercismConfig
{
    String b;
    public bl c;
    
    public az(final bl c, final CrittercismConfig crittercismConfig) {
        super(crittercismConfig);
        this.b = "524c99a04002057fcd000001";
        this.c = c;
    }
    
    @Override
    public final List a() {
        final List a = super.a();
        a.add(this.c.a);
        return a;
    }
    
    public final String b() {
        return this.a;
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (!(o instanceof az)) {
            return false;
        }
        final az az = (az)o;
        return super.equals(o) && CrittercismConfig.a(this.c.b, az.c.b) && CrittercismConfig.a(this.c.a, az.c.a) && CrittercismConfig.a(this.c.c, az.c.c) && CrittercismConfig.a(this.c.d, az.c.d) && CrittercismConfig.a(this.b, az.b);
    }
    
    @Override
    public final int hashCode() {
        return ((((super.hashCode() * 31 + this.c.b.hashCode()) * 31 + this.c.a.hashCode()) * 31 + this.c.c.hashCode()) * 31 + this.c.d.hashCode()) * 31 + this.b.hashCode();
    }
}
