// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

public final class dv implements bv
{
    public int a;
    
    public dv(final int a) {
        this.a = a;
    }
    
    public final void a(final ax ax, final String s, final String s2) {
        synchronized (this) {
            ax.a(s, s2, Integer.valueOf(this.a));
        }
    }
}
