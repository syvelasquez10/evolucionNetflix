// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.c$c;
import java.util.Map;
import com.google.android.gms.internal.c$i;
import com.google.android.gms.internal.d$a;
import com.google.android.gms.internal.c$d;

class ai
{
    private static void a(final DataLayer dataLayer, final c$d c$d) {
        final d$a[] fb = c$d.fB;
        for (int length = fb.length, i = 0; i < length; ++i) {
            dataLayer.cs(di.j(fb[i]));
        }
    }
    
    public static void a(final DataLayer dataLayer, final c$i c$i) {
        if (c$i.gq == null) {
            bh.W("supplemental missing experimentSupplemental");
            return;
        }
        a(dataLayer, c$i.gq);
        b(dataLayer, c$i.gq);
        c(dataLayer, c$i.gq);
    }
    
    private static void b(final DataLayer dataLayer, final c$d c$d) {
        final d$a[] fa = c$d.fA;
        for (int length = fa.length, i = 0; i < length; ++i) {
            final Map<String, Object> c = c(fa[i]);
            if (c != null) {
                dataLayer.push(c);
            }
        }
    }
    
    private static Map<String, Object> c(final d$a d$a) {
        final Object o = di.o(d$a);
        if (!(o instanceof Map)) {
            bh.W("value: " + o + " is not a map value, ignored.");
            return null;
        }
        return (Map<String, Object>)o;
    }
    
    private static void c(final DataLayer dataLayer, final c$d c$d) {
        final c$c[] fc = c$d.fC;
        for (int length = fc.length, i = 0; i < length; ++i) {
            final c$c c$c = fc[i];
            if (c$c.fv == null) {
                bh.W("GaExperimentRandom: No key");
            }
            else {
                Object o = dataLayer.get(c$c.fv);
                Long value;
                if (!(o instanceof Number)) {
                    value = null;
                }
                else {
                    value = ((Number)o).longValue();
                }
                final long fw = c$c.fw;
                final long fx = c$c.fx;
                if (!c$c.fy || value == null || value < fw || value > fx) {
                    if (fw > fx) {
                        bh.W("GaExperimentRandom: random range invalid");
                        continue;
                    }
                    o = Math.round(Math.random() * (fx - fw) + fw);
                }
                dataLayer.cs(c$c.fv);
                final Map<String, Object> c = dataLayer.c(c$c.fv, o);
                if (c$c.fz > 0L) {
                    if (!c.containsKey("gtm")) {
                        c.put("gtm", DataLayer.mapOf("lifetime", c$c.fz));
                    }
                    else {
                        final Object value2 = c.get("gtm");
                        if (value2 instanceof Map) {
                            ((Map<String, Long>)value2).put("lifetime", c$c.fz);
                        }
                        else {
                            bh.W("GaExperimentRandom: gtm not a map");
                        }
                    }
                }
                dataLayer.push(c);
            }
        }
    }
}
