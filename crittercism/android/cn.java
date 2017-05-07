// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

public final class cn
{
    public int a;
    public int b;
    
    public cn(final Throwable t) {
        this.a = co.d.ordinal();
        this.b = cm.a.ordinal();
        if (t != null) {
            this.a = co.a(t);
            if (this.a != co.d.ordinal()) {
                this.b = Integer.parseInt(t.getMessage());
                return;
            }
            this.b = cm.a(t).a();
        }
    }
}
