// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.util.Comparator;
import java.util.TreeMap;
import java.util.Map;

public final class p extends n
{
    public p(final Map map) {
        super(map);
        final TreeMap a = new TreeMap(new p$1(this));
        a.putAll(map);
        super.a = a;
    }
}
