// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;
import com.google.android.gms.analytics.Tracker;
import java.util.HashMap;
import com.google.android.gms.internal.d;
import java.util.HashSet;
import android.content.Context;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.a;
import java.util.Set;
import java.util.Map;

class di extends df
{
    private static final String ID;
    private static final String aaO;
    private static final String aaP;
    private static final String aaQ;
    private static final String aaR;
    private static final String aaS;
    private static final String aaT;
    private static Map<String, String> aaU;
    private static Map<String, String> aaV;
    private final DataLayer WK;
    private final Set<String> aaW;
    private final de aaX;
    
    static {
        ID = a.aF.toString();
        aaO = b.aV.toString();
        aaP = b.be.toString();
        aaQ = b.bd.toString();
        aaR = b.eg.toString();
        aaS = b.ei.toString();
        aaT = b.ek.toString();
    }
    
    public di(final Context context, final DataLayer dataLayer) {
        this(context, dataLayer, new de(context));
    }
    
    di(final Context context, final DataLayer wk, final de aaX) {
        super(di.ID, new String[0]);
        this.WK = wk;
        this.aaX = aaX;
        (this.aaW = new HashSet<String>()).add("");
        this.aaW.add("0");
        this.aaW.add("false");
    }
    
    private Map<String, String> H(final Map<String, d.a> map) {
        final d.a a = map.get(di.aaS);
        if (a != null) {
            return this.c(a);
        }
        if (di.aaU == null) {
            final HashMap<String, String> aaU = new HashMap<String, String>();
            aaU.put("transactionId", "&ti");
            aaU.put("transactionAffiliation", "&ta");
            aaU.put("transactionTax", "&tt");
            aaU.put("transactionShipping", "&ts");
            aaU.put("transactionTotal", "&tr");
            aaU.put("transactionCurrency", "&cu");
            di.aaU = aaU;
        }
        return di.aaU;
    }
    
    private Map<String, String> I(final Map<String, d.a> map) {
        final d.a a = map.get(di.aaT);
        if (a != null) {
            return this.c(a);
        }
        if (di.aaV == null) {
            final HashMap<String, String> aaV = new HashMap<String, String>();
            aaV.put("name", "&in");
            aaV.put("sku", "&ic");
            aaV.put("category", "&iv");
            aaV.put("price", "&ip");
            aaV.put("quantity", "&iq");
            aaV.put("currency", "&cu");
            di.aaV = aaV;
        }
        return di.aaV;
    }
    
    private void a(final Tracker tracker, final Map<String, d.a> map) {
        final String cc = this.cc("transactionId");
        if (cc == null) {
            bh.w("Cannot find transactionId in data layer.");
        }
        else {
            final LinkedList<Map<String, String>> list = new LinkedList<Map<String, String>>();
            Map<String, String> p2;
            try {
                p2 = this.p(map.get(di.aaQ));
                p2.put("&t", "transaction");
                for (final Map.Entry<String, String> entry : this.H(map).entrySet()) {
                    this.b(p2, entry.getValue(), this.cc(entry.getKey()));
                }
            }
            catch (IllegalArgumentException ex) {
                bh.b("Unable to send transaction", ex);
                return;
            }
            list.add(p2);
            final List<Map<String, String>> lu = this.lU();
            if (lu != null) {
                for (final Map<String, String> map2 : lu) {
                    if (map2.get("name") == null) {
                        bh.w("Unable to send transaction item hit due to missing 'name' field.");
                        return;
                    }
                    final Map<String, String> p3 = this.p(map.get(di.aaQ));
                    p3.put("&t", "item");
                    p3.put("&ti", cc);
                    for (final Map.Entry<String, String> entry2 : this.I(map).entrySet()) {
                        this.b(p3, entry2.getValue(), map2.get(entry2.getKey()));
                    }
                    list.add(p3);
                }
            }
            final Iterator<Object> iterator4 = list.iterator();
            while (iterator4.hasNext()) {
                tracker.send(iterator4.next());
            }
        }
    }
    
    private void b(final Map<String, String> map, final String s, final String s2) {
        if (s2 != null) {
            map.put(s, s2);
        }
    }
    
    private Map<String, String> c(final d.a a) {
        final Object o = dh.o(a);
        if (!(o instanceof Map)) {
            return null;
        }
        final Map<Object, V> map = (Map<Object, V>)o;
        final LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        for (final Map.Entry<Object, V> entry : map.entrySet()) {
            linkedHashMap.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return linkedHashMap;
    }
    
    private String cc(final String s) {
        final Object value = this.WK.get(s);
        if (value == null) {
            return null;
        }
        return value.toString();
    }
    
    private boolean e(final Map<String, d.a> map, final String s) {
        final d.a a = map.get(s);
        return a != null && dh.n(a);
    }
    
    private List<Map<String, String>> lU() {
        final Object value = this.WK.get("transactionProducts");
        if (value == null) {
            return null;
        }
        if (!(value instanceof List)) {
            throw new IllegalArgumentException("transactionProducts should be of type List.");
        }
        final Iterator<Map<String, String>> iterator = ((List<Map<String, String>>)value).iterator();
        while (iterator.hasNext()) {
            if (!(iterator.next() instanceof Map)) {
                throw new IllegalArgumentException("Each element of transactionProducts should be of type Map.");
            }
        }
        return (List<Map<String, String>>)value;
    }
    
    private Map<String, String> p(final d.a a) {
        if (a == null) {
            return new HashMap<String, String>();
        }
        final Map<String, String> c = this.c(a);
        if (c == null) {
            return new HashMap<String, String>();
        }
        final String s = c.get("&aip");
        if (s != null && this.aaW.contains(s.toLowerCase())) {
            c.remove("&aip");
        }
        return c;
    }
    
    @Override
    public void z(final Map<String, d.a> map) {
        final Tracker bu = this.aaX.bU("_GTM_DEFAULT_TRACKER_");
        if (this.e(map, di.aaP)) {
            bu.send(this.p(map.get(di.aaQ)));
            return;
        }
        if (this.e(map, di.aaR)) {
            this.a(bu, map);
            return;
        }
        bh.z("Ignoring unknown tag.");
    }
}
