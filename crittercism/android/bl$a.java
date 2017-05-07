// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

public enum bl$a
{
    a("FOREGROUND", 0, "foregrounded"), 
    b("BACKGROUND", 1, "backgrounded");
    
    private String c;
    
    private bl$a(final String s, final int n, final String c) {
        this.c = c;
    }
    
    public final String a() {
        return this.c;
    }
}
