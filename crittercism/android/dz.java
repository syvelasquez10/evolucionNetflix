// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

public final class dz extends Thread
{
    public dz(final Runnable runnable) {
        super(new df(runnable));
    }
    
    public dz(final Runnable runnable, final String s) {
        super(new df(runnable), s);
    }
}
