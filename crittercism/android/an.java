// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

public final class an implements t
{
    public boolean a;
    
    private an(final boolean a) {
        this.a = a;
    }
    
    public final boolean a(final h h, final String s, final String s2) {
        synchronized (this) {
            h.a(s, s2, false);
            return true;
        }
    }
    
    public final boolean b(final h h, final String s, final String s2) {
        synchronized (this) {
            h.a(s, s2, Boolean.valueOf(this.a));
            return true;
        }
    }
}
