// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.util.List;
import java.util.Map;

public abstract class n
{
    Map a;
    
    public n(final Map a) {
        this.a = a;
    }
    
    private String c(final String s) {
        final List<String> list = this.a.get(s);
        if (list != null) {
            return list.get(list.size() - 1);
        }
        return null;
    }
    
    public final long a(String c) {
        long long1 = Long.MAX_VALUE;
        c = this.c(c);
        if (c == null) {
            return long1;
        }
        try {
            long1 = Long.parseLong(c);
            return long1;
        }
        catch (NumberFormatException ex) {
            return Long.MAX_VALUE;
        }
    }
    
    public final int b(String c) {
        int int1 = -1;
        c = this.c(c);
        if (c == null) {
            return int1;
        }
        try {
            int1 = Integer.parseInt(c);
            return int1;
        }
        catch (NumberFormatException ex) {
            return -1;
        }
    }
    
    @Override
    public final String toString() {
        return this.a.toString();
    }
}
