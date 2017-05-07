// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Bundle;

@ez
public final class bn
{
    public static iv<String> oX;
    public static iv<String> oY;
    public static iv<Boolean> oZ;
    public static iv<Boolean> pa;
    public static iv<Boolean> pb;
    public static iv<String> pc;
    public static iv<Boolean> pd;
    public static iv<Integer> pe;
    public static iv<Integer> pf;
    public static iv<Integer> pg;
    public static iv<Integer> ph;
    public static iv<Integer> pi;
    private static final Bundle pj;
    private static boolean pk;
    
    static {
        pj = new Bundle();
        bn.pk = false;
        bn.oX = a("gads:sdk_core_location", "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/sdk-core-v40.html");
        bn.oY = a("gads:sdk_core_experiment_id", null);
        bn.oZ = c("gads:sdk_crash_report_enabled", false);
        bn.pa = c("gads:sdk_crash_report_full_stacktrace", false);
        bn.pb = c("gads:block_autoclicks", false);
        bn.pc = a("gads:block_autoclicks_experiment_id", null);
        bn.pk = true;
        bn.pd = c("gads:enable_content_fetching", false);
        bn.pe = a("gads:content_length_weight", 1);
        bn.pf = a("gads:content_age_weight", 1);
        bn.pg = a("gads:min_content_len", 11);
        bn.ph = a("gads:fingerprint_number", 10);
        bn.pi = a("gads:sleep_sec", 10);
    }
    
    private static iv<Integer> a(final String s, final int n) {
        bn.pj.putInt(s, n);
        return iv.a(s, n);
    }
    
    private static iv<String> a(final String s, final String s2) {
        bn.pj.putString(s, s2);
        return iv.m(s, s2);
    }
    
    public static Bundle bs() {
        return bn.pj;
    }
    
    private static iv<Boolean> c(final String s, final boolean b) {
        bn.pj.putBoolean(s, b);
        return iv.g(s, b);
    }
}
