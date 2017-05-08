// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.util.HashMap;
import java.util.Map;

public final class do
{
    private static Map a;
    
    static {
        (do.a = new HashMap()).put("com.amazon.venezia", new dn$a$a());
        do.a.put("com.android.vending", new dn$b$a());
    }
    
    public static dm a(final String s) {
        if (s != null && do.a.containsKey(s)) {
            return do.a.get(s);
        }
        return null;
    }
}
