// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

public final class bx$m implements bw
{
    private Long a;
    
    public bx$m() {
        this.a = null;
        this.a = Runtime.getRuntime().maxMemory();
    }
    
    @Override
    public final String a() {
        return "memory_total";
    }
}
