// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;
import org.json.JSONArray;
import com.google.android.gms.internal.d$a;
import com.google.android.gms.internal.b;
import org.json.JSONObject;

class ba
{
    public static cr$c cD(final String s) {
        final d$a n = n(new JSONObject(s));
        final cr$d ov = cr$c.oV();
        for (int i = 0; i < n.gx.length; ++i) {
            ov.a(cr$a.oR().b(b.df.toString(), n.gx[i]).b(b.cU.toString(), di.cU(m.nO())).b(m.nP(), n.gy[i]).oU());
        }
        return ov.oY();
    }
    
    private static d$a n(final Object o) {
        return di.u(o(o));
    }
    
    static Object o(final Object o) {
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
                ((Map<String, Object>)o2).put(s, o(jsonObject.get(s)));
            }
        }
        return o2;
    }
}
