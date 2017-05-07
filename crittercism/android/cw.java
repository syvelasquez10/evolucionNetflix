// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

public final class cw
{
    private long a;
    private long b;
    
    public cw(final long b) {
        this.a = 0L;
        this.b = b;
    }
    
    public final boolean a() {
        synchronized (this) {
            return System.nanoTime() - this.a > this.b;
        }
    }
    
    public final void b() {
        synchronized (this) {
            this.a = System.nanoTime();
        }
    }
}
