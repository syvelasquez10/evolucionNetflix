// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import com.crittercism.app.CrittercismConfig;

public final class m extends CrittercismConfig
{
    private String a;
    private String b;
    
    public m() {
        this.a = "https://api.crittercism.com";
        this.b = "https://apm.crittercism.com";
    }
    
    public m(final CrittercismConfig crittercismConfig) {
        super(crittercismConfig);
        this.a = "https://api.crittercism.com";
        this.b = "https://apm.crittercism.com";
    }
    
    public final String a() {
        return this.a;
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o instanceof m) {
            final m m = (m)o;
            if (CrittercismConfig.a(this.a, m.a) && CrittercismConfig.a(this.b, m.b)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public final int hashCode() {
        return (super.hashCode() * 31 + this.a.hashCode()) * 31 + this.b.hashCode();
    }
}
