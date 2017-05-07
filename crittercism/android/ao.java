// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

public final class ao implements t
{
    public String a;
    
    private ao(final String a) {
        this.a = a;
    }
    
    public final boolean a(final h h, final String s, final String s2) {
        synchronized (this) {
            h.b(s, s2, this.a);
            return true;
        }
    }
}
