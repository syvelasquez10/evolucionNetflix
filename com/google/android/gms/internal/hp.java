// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;

public class hp
{
    private static final String[] Cm;
    private static final Map<String, Integer> Cn;
    
    static {
        int i = 0;
        Cm = new String[] { "text1", "text2", "icon", "intent_action", "intent_data", "intent_data_id", "intent_extra_data", "suggest_large_icon", "intent_activity" };
        Cn = new HashMap<String, Integer>(hp.Cm.length);
        while (i < hp.Cm.length) {
            hp.Cn.put(hp.Cm[i], i);
            ++i;
        }
    }
    
    public static String O(final int n) {
        if (n < 0 || n >= hp.Cm.length) {
            return null;
        }
        return hp.Cm[n];
    }
    
    public static int as(final String s) {
        final Integer n = hp.Cn.get(s);
        if (n == null) {
            throw new IllegalArgumentException("[" + s + "] is not a valid global search section name");
        }
        return n;
    }
    
    public static int fm() {
        return hp.Cm.length;
    }
}
