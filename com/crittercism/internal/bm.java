// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.util.Iterator;
import org.json.JSONArray;

public final class bm
{
    public JSONArray a;
    
    public bm(final bq bq) {
        this.a = new JSONArray();
        final Iterator<bo> iterator = bq.c().iterator();
        while (iterator.hasNext()) {
            final Object a = iterator.next().a();
            if (a != null) {
                this.a.put(a);
            }
        }
    }
}
