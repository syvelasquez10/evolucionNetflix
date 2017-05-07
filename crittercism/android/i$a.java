// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

final class i$a implements Runnable
{
    private boolean a;
    private boolean b;
    private i c;
    
    public i$a(final i c) {
        this.b = false;
        this.c = c;
        this.a = true;
    }
    
    public final boolean a() {
        return this.b;
    }
    
    @Override
    public final void run() {
        if (this.a) {
            this.b = this.c.c();
            return;
        }
        this.c.b();
    }
}
