// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.util.HashMap;
import java.util.Map;

public final class am
{
    private static Map a;
    
    static {
        (am.a = new HashMap()).put("com.amazon.venezia", new al$a$a());
        am.a.put("com.android.vending", new al$b$a());
    }
    
    public static ak a(final String s) {
        if (s != null && am.a.containsKey(s)) {
            return am.a.get(s);
        }
        return null;
    }
}
