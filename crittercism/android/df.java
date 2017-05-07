// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

public final class df extends dj
{
    private Runnable a;
    
    public df(final Runnable a) {
        this.a = a;
    }
    
    @Override
    public final void a() {
        this.a.run();
    }
}
