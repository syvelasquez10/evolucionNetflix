// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

public final class ao extends ak
{
    private int g;
    
    public ao(final af af, final int g) {
        super(af);
        this.g = g;
    }
    
    @Override
    protected final af g() {
        int n;
        if (super.a.c().equals("HEAD") || (this.g >= 100 && this.g <= 199) || this.g == 204 || this.g == 304) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n != 0) {
            super.a.b(this.a());
            return super.a.b();
        }
        if (super.f) {
            return new ai(this);
        }
        if (super.d) {
            if (super.e > 0) {
                return new ag(this, super.e);
            }
            super.a.b(this.a());
            return super.a.b();
        }
        else {
            if (super.a.c().equals("CONNECT")) {
                super.a.b(this.a());
                return super.a.b();
            }
            return new aj(this);
        }
    }
}
