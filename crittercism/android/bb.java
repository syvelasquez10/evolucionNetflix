// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.util.List;
import com.crittercism.app.CrittercismConfig;

public final class bb extends CrittercismConfig
{
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    
    public bb(String a, final CrittercismConfig crittercismConfig) {
        super(crittercismConfig);
        this.b = "https://api.crittercism.com";
        this.c = "https://apm.crittercism.com";
        this.d = "https://txn.ingest.crittercism.com/api/v1/transactions";
        this.e = "https://appload.ingest.crittercism.com";
        this.f = "524c99a04002057fcd000001";
        a = bn$a.a(a).a();
        this.b = "https://api." + a;
        this.c = "https://apm." + a;
        this.e = "https://appload.ingest." + a;
        this.d = "https://txn.ingest." + a + "/api/v1/transactions";
    }
    
    @Override
    public final List a() {
        final List a = super.a();
        a.add(this.c);
        return a;
    }
    
    public final String b() {
        return this.b;
    }
    
    public final String c() {
        return this.c;
    }
    
    public final String d() {
        return this.d;
    }
    
    public final String e() {
        return this.e;
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (!(o instanceof bb)) {
            return false;
        }
        final bb bb = (bb)o;
        return super.equals(o) && CrittercismConfig.a(this.b, bb.b) && CrittercismConfig.a(this.c, bb.c) && CrittercismConfig.a(this.d, bb.d) && CrittercismConfig.a(this.e, bb.e) && CrittercismConfig.a(this.f, bb.f);
    }
    
    public final String f() {
        return this.f;
    }
    
    public final String g() {
        return this.a;
    }
    
    @Override
    public final int hashCode() {
        return ((((super.hashCode() * 31 + this.b.hashCode()) * 31 + this.c.hashCode()) * 31 + this.d.hashCode()) * 31 + this.e.hashCode()) * 31 + this.f.hashCode();
    }
}
