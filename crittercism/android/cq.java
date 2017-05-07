// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

public final class cq extends Throwable
{
    private String a;
    
    public final String a() {
        return this.a;
    }
    
    @Override
    public final String toString() {
        final String localizedMessage = this.getLocalizedMessage();
        final String a = this.a;
        if (localizedMessage == null) {
            return a;
        }
        return a + ": " + localizedMessage;
    }
}
