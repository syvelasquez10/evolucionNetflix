// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.util.Iterator;
import org.json.JSONArray;

public final class bo
{
    public JSONArray a;
    
    public bo(final bs bs) {
        this.a = new JSONArray();
        final Iterator<bq> iterator = bs.c().iterator();
        while (iterator.hasNext()) {
            final Object a = iterator.next().a();
            if (a != null) {
                this.a.put(a);
            }
        }
    }
}
