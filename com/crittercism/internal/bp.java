// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

public enum bp
{
    a("APP_LOADS", 0, "app_loads_2", 10, Integer.MAX_VALUE, new bq$a(0), (ch)new by$a(), (String)null), 
    b("HAND_EXCS", 1, "exceptions", 5, 50, new bq$a(0), (ch)new by$a(), "exceptions"), 
    c("INTERNAL_EXCS", 2, "internal_excs", 3, 3, new bq$a(0), (ch)new by$a(), "exceptions"), 
    d("NDK_CRASHES", 3, "ndk_crashes", 5, Integer.MAX_VALUE, new bq$a(0), (ch)new by$a(), "crashes"), 
    e("SDK_CRASHES", 4, "sdk_crashes", 5, Integer.MAX_VALUE, new bq$a(0), (ch)new by$a(), "crashes"), 
    f("CURR_BCS", 5, "current_bcs", 50, Integer.MAX_VALUE, new bq$a(1), (ch)new bx$a(), (String)null), 
    g("NW_BCS", 6, "network_bcs", 10, Integer.MAX_VALUE, new bq$a(0), (ch)new bx$a(), (String)null), 
    h("PREV_BCS", 7, "previous_bcs", 50, Integer.MAX_VALUE, new bq$a(0), (ch)new bx$a(), (String)null), 
    i("STARTED_TXNS", 8, "started_txns", 50, Integer.MAX_VALUE, new bq$a(0), (ch)new bx$a(), (String)null), 
    j("FINISHED_TXNS", 9, "finished_txns", Integer.MAX_VALUE, Integer.MAX_VALUE, new bq$a(0), (ch)new bx$a(), (String)null), 
    k("SYSTEM_BCS", 10, "system_bcs", 100, Integer.MAX_VALUE, new bq$a(0), (ch)new bx$a(), (String)null);
    
    String l;
    int m;
    int n;
    bq$a o;
    ch p;
    public String q;
    
    private bp(final String s, final int n, final String l, final int m, final int n2, final bq$a o, final ch p8, final String q) {
        this.l = l;
        this.m = m;
        this.n = n2;
        this.o = o;
        this.p = p8;
        this.q = q;
    }
}
