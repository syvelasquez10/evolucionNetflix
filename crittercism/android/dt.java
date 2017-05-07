// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

public final class dt
{
    private boolean a;
    private boolean b;
    
    public dt(final boolean a) {
        this.a = a;
        this.b = true;
    }
    
    public final boolean a() {
        synchronized (this) {
            return this.a;
        }
    }
}
