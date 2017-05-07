// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

public enum br
{
    a("APP_LOADS", 0, "app_loads_2", 10, Integer.MAX_VALUE, new bs$a(0), (cj)new ca$a(), (String)null), 
    b("HAND_EXCS", 1, "exceptions", 5, 50, new bs$a(0), (cj)new ca$a(), "exceptions"), 
    c("INTERNAL_EXCS", 2, "internal_excs", 3, 3, new bs$a(0), (cj)new ca$a(), "exceptions"), 
    d("NDK_CRASHES", 3, "ndk_crashes", 5, Integer.MAX_VALUE, new bs$a(0), (cj)new ca$a(), "crashes"), 
    e("SDK_CRASHES", 4, "sdk_crashes", 5, Integer.MAX_VALUE, new bs$a(0), (cj)new ca$a(), "crashes"), 
    f("CURR_BCS", 5, "current_bcs", 50, Integer.MAX_VALUE, new bs$a(1), (cj)new bz$a(), (String)null), 
    g("NW_BCS", 6, "network_bcs", 10, Integer.MAX_VALUE, new bs$a(0), (cj)new bz$a(), (String)null), 
    h("PREV_BCS", 7, "previous_bcs", 50, Integer.MAX_VALUE, new bs$a(0), (cj)new bz$a(), (String)null), 
    i("STARTED_TXNS", 8, "started_txns", 50, Integer.MAX_VALUE, new bs$a(0), (cj)new bz$a(), (String)null), 
    j("FINISHED_TXNS", 9, "finished_txns", Integer.MAX_VALUE, Integer.MAX_VALUE, new bs$a(0), (cj)new bz$a(), (String)null), 
    k("SYSTEM_BCS", 10, "system_bcs", 100, Integer.MAX_VALUE, new bs$a(0), (cj)new bz$a(), (String)null);
    
    private String l;
    private int m;
    private int n;
    private bs$a o;
    private cj p;
    private String q;
    
    private br(final String s, final int n, final String l, final int m, final int n2, final bs$a o, final cj p8, final String q) {
        this.l = l;
        this.m = m;
        this.n = n2;
        this.o = o;
        this.p = p8;
        this.q = q;
    }
    
    public final String a() {
        return this.l;
    }
    
    public final int b() {
        return this.m;
    }
    
    public final bs$a c() {
        return this.o;
    }
    
    public final cj d() {
        return this.p;
    }
    
    public final int e() {
        return this.n;
    }
    
    public final String f() {
        return this.q;
    }
}
