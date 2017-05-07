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
        final d.a[] es = d.eS;
        for (int length = es.length, i = 0; i < length; ++i) {
            dataLayer.bv(dh.j(es[i]));
        }
    }
    
    public static void a(final DataLayer dataLayer, final c.i i) {
        if (i.fI == null) {
            bh.z("supplemental missing experimentSupplemental");
            return;
        }
        a(dataLayer, i.fI);
        b(dataLayer, i.fI);
        c(dataLayer, i.fI);
    }
    
    private static void b(final DataLayer dataLayer, final c.d d) {
        final d.a[] er = d.eR;
        for (int length = er.length, i = 0; i < length; ++i) {
            final Map<String, Object> c = c(er[i]);
            if (c != null) {
                dataLayer.push(c);
            }
        }
    }
    
    private static Map<String, Object> c(final d.a a) {
        final Object o = dh.o(a);
        if (!(o instanceof Map)) {
            bh.z("value: " + o + " is not a map value, ignored.");
            return null;
        }
        return (Map<String, Object>)o;
    }
    
    private static void c(final DataLayer dataLayer, final c.d d) {
        final c.c[] et = d.eT;
        for (int length = et.length, i = 0; i < length; ++i) {
            final c.c c = et[i];
            if (c.eM == null) {
                bh.z("GaExperimentRandom: No key");
            }
            else {
                Object o = dataLayer.get(c.eM);
                Long value;
                if (!(o instanceof Number)) {
                    value = null;
                }
                else {
                    value = ((Number)o).longValue();
                }
                final long en = c.eN;
                final long eo = c.eO;
                if (!c.eP || value == null || value < en || value > eo) {
                    if (en > eo) {
                        bh.z("GaExperimentRandom: random range invalid");
                        continue;
                    }
                    o = Math.round(Math.random() * (eo - en) + en);
                }
                dataLayer.bv(c.eM);
                final Map<String, Object> c2 = dataLayer.c(c.eM, o);
                if (c.eQ > 0L) {
                    if (!c2.containsKey("gtm")) {
                        c2.put("gtm", DataLayer.mapOf("lifetime", c.eQ));
                    }
                    else {
                        final Object value2 = c2.get("gtm");
                        if (value2 instanceof Map) {
                            ((Map<String, Long>)value2).put("lifetime", c.eQ);
                        }
                        else {
                            bh.z("GaExperimentRandom: gtm not a map");
                        }
                    }
                }
                dataLayer.push(c2);
            }
        }
    }
}
