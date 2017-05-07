// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

public final class ck extends RuntimeException
{
    public ck(final String s) {
        this(s, null);
    }
    
    public ck(final String s, final Throwable t) {
        super(s, t);
    }
    
    public ck(final Throwable t) {
        super(t);
    }
}
