// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Map;
import com.google.android.gms.internal.d;
import com.google.android.gms.internal.c;

class ai
{
    private static void a(final DataLayer dataLayer, final c.d d) {
        final d.a[] fb = d.fB;
        for (int length = fb.length, i = 0; i < length; ++i) {
            dataLayer.cs(di.j(fb[i]));
        }
    }
    
    public static void a(final DataLayer dataLayer, final c.i i) {
        if (i.gq == null) {
            bh.W("supplemental missing experimentSupplemental");
            return;
        }
        a(dataLayer, i.gq);
        b(dataLayer, i.gq);
        c(dataLayer, i.gq);
    }
    
    private static void b(final DataLayer dataLayer, final c.d d) {
        final d.a[] fa = d.fA;
        for (int length = fa.length, i = 0; i < length; ++i) {
            final Map<String, Object> c = c(fa[i]);
            if (c != null) {
                dataLayer.push(c);
            }
        }
    }
    
    private static Map<String, Object> c(final d.a a) {
        final Object o = di.o(a);
        if (!(o instanceof Map)) {
            bh.W("value: " + o + " is not a map value, ignored.");
            return null;
        }
        return (Map<String, Object>)o;
    }
    
    private static void c(final DataLayer dataLayer, final c.d d) {
        final c.c[] fc = d.fC;
        for (int length = fc.length, i = 0; i < length; ++i) {
            final c.c c = fc[i];
            if (c.fv == null) {
                bh.W("GaExperimentRandom: No key");
            }
            else {
                Object o = dataLayer.get(c.fv);
                Long value;
                if (!(o instanceof Number)) {
                    value = null;
                }
                else {
                    value = ((Number)o).longValue();
                }
                final long fw = c.fw;
                final long fx = c.fx;
                if (!c.fy || value == null || value < fw || value > fx) {
                    if (fw > fx) {
                        bh.W("GaExperimentRandom: random range invalid");
                        continue;
                    }
                    o = Math.round(Math.random() * (fx - fw) + fw);
                }
                dataLayer.cs(c.fv);
                final Map<String, Object> c2 = dataLayer.c(c.fv, o);
                if (c.fz > 0L) {
                    if (!c2.containsKey("gtm")) {
                        c2.put("gtm", DataLayer.mapOf("lifetime", c.fz));
                    }
                    else {
                        final Object value2 = c2.get("gtm");
                        if (value2 instanceof Map) {
                            ((Map<String, Long>)value2).put("lifetime", c.fz);
                        }
                        else {
                            bh.W("GaExperimentRandom: gtm not a map");
                        }
                    }
                }
                dataLayer.push(c2);
            }
        }
    }
}
