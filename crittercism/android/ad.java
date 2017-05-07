// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

public final class ad extends Exception
{
    private static final long serialVersionUID = 4511293437269420307L;
    private a a;
    
    public ad(final String s, final a a) {
        super(s);
        this.a = a;
    }
    
    public enum a
    {
        a("NO_INTERNET", 0), 
        b("CONN_TIMEOUT", 1), 
        c("UNKNOWN_HOST", 2), 
        d("WTF", 3), 
        e("FILE_NOT_FOUND", 4);
        
        private a(final String s, final int n) {
        }
    }
}
