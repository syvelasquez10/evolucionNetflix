// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import com.google.android.gms.internal.d;
import com.google.android.gms.internal.b;
import org.json.JSONObject;

class ba
{
    public static cq.c bG(final String s) throws JSONException {
        final d.a k = k(new JSONObject(s));
        final cq.d lh = cq.c.lh();
        for (int i = 0; i < k.fP.length; ++i) {
            lh.a(cq.a.ld().b(b.cI.toString(), k.fP[i]).b(b.cx.toString(), dh.bX(m.ka())).b(m.kb(), k.fQ[i]).lg());
        }
        return lh.lk();
    }
    
    private static d.a k(final Object o) throws JSONException {
        return dh.r(l(o));
    }
    
    static Object l(final Object o) throws JSONException {
        if (o instanceof JSONArray) {
            throw new RuntimeException("JSONArrays are not supported");
        }
        if (JSONObject.NULL.equals(o)) {
            throw new RuntimeException("JSON nulls are not supported");
        }
        Object o2 = o;
        if (o instanceof JSONObject) {
            final JSONObject jsonObject = (JSONObject)o;
            o2 = new HashMap<String, Object>();
            final Iterator keys = jsonObject.keys();
            while (keys.hasNext()) {
                final String s = keys.next();
                ((Map<String, Object>)o2).put(s, l(jsonObject.get(s)));
            }
        }
        return o2;
    }
}
