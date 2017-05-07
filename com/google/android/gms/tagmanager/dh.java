// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Set;
import java.util.Iterator;
import java.util.HashMap;
import java.util.ArrayList;
import com.google.android.gms.internal.d;
import java.util.Map;
import java.util.List;

class dh
{
    private static final Object aaF;
    private static Long aaG;
    private static Double aaH;
    private static dg aaI;
    private static String aaJ;
    private static Boolean aaK;
    private static List<Object> aaL;
    private static Map<Object, Object> aaM;
    private static d.a aaN;
    
    static {
        aaF = null;
        dh.aaG = new Long(0L);
        dh.aaH = new Double(0.0);
        dh.aaI = dg.w(0L);
        dh.aaJ = new String("");
        dh.aaK = new Boolean(false);
        dh.aaL = new ArrayList<Object>(0);
        dh.aaM = new HashMap<Object, Object>();
        dh.aaN = r(dh.aaJ);
    }
    
    public static d.a bX(final String fs) {
        final d.a a = new d.a();
        a.type = 5;
        a.fS = fs;
        return a;
    }
    
    private static dg bY(final String s) {
        try {
            return dg.bW(s);
        }
        catch (NumberFormatException ex) {
            bh.w("Failed to convert '" + s + "' to a number.");
            return dh.aaI;
        }
    }
    
    private static Long bZ(final String s) {
        final dg by = bY(s);
        if (by == dh.aaI) {
            return dh.aaG;
        }
        return by.longValue();
    }
    
    private static Double ca(final String s) {
        final dg by = bY(s);
        if (by == dh.aaI) {
            return dh.aaH;
        }
        return by.doubleValue();
    }
    
    private static Boolean cb(final String s) {
        if ("true".equalsIgnoreCase(s)) {
            return Boolean.TRUE;
        }
        if ("false".equalsIgnoreCase(s)) {
            return Boolean.FALSE;
        }
        return dh.aaK;
    }
    
    private static double getDouble(final Object o) {
        if (o instanceof Number) {
            return ((Number)o).doubleValue();
        }
        bh.w("getDouble received non-Number");
        return 0.0;
    }
    
    public static String j(final d.a a) {
        return m(o(a));
    }
    
    public static dg k(final d.a a) {
        return n(o(a));
    }
    
    public static Long l(final d.a a) {
        return o(o(a));
    }
    
    public static Object lN() {
        return dh.aaF;
    }
    
    public static Long lO() {
        return dh.aaG;
    }
    
    public static Double lP() {
        return dh.aaH;
    }
    
    public static Boolean lQ() {
        return dh.aaK;
    }
    
    public static dg lR() {
        return dh.aaI;
    }
    
    public static String lS() {
        return dh.aaJ;
    }
    
    public static d.a lT() {
        return dh.aaN;
    }
    
    public static Double m(final d.a a) {
        return p(o(a));
    }
    
    public static String m(final Object o) {
        if (o == null) {
            return dh.aaJ;
        }
        return o.toString();
    }
    
    public static dg n(final Object o) {
        if (o instanceof dg) {
            return (dg)o;
        }
        if (t(o)) {
            return dg.w(u(o));
        }
        if (s(o)) {
            return dg.a(Double.valueOf(getDouble(o)));
        }
        return bY(m(o));
    }
    
    public static Boolean n(final d.a a) {
        return q(o(a));
    }
    
    public static Long o(final Object o) {
        if (t(o)) {
            return u(o);
        }
        return bZ(m(o));
    }
    
    public static Object o(final d.a a) {
        final int n = 0;
        final int n2 = 0;
        int i = 0;
        if (a == null) {
            return dh.aaF;
        }
        switch (a.type) {
            default: {
                bh.w("Failed to convert a value of type: " + a.type);
                return dh.aaF;
            }
            case 1: {
                return a.fN;
            }
            case 2: {
                final ArrayList<Object> list = new ArrayList<Object>(a.fO.length);
                for (d.a[] fo = a.fO; i < fo.length; ++i) {
                    final Object o = o(fo[i]);
                    if (o == dh.aaF) {
                        return dh.aaF;
                    }
                    list.add(o);
                }
                return list;
            }
            case 3: {
                if (a.fP.length != a.fQ.length) {
                    bh.w("Converting an invalid value to object: " + a.toString());
                    return dh.aaF;
                }
                final HashMap<Object, Object> hashMap = new HashMap<Object, Object>(a.fQ.length);
                for (int j = n; j < a.fP.length; ++j) {
                    final Object o2 = o(a.fP[j]);
                    final Object o3 = o(a.fQ[j]);
                    if (o2 == dh.aaF || o3 == dh.aaF) {
                        return dh.aaF;
                    }
                    hashMap.put(o2, o3);
                }
                return hashMap;
            }
            case 4: {
                bh.w("Trying to convert a macro reference to object");
                return dh.aaF;
            }
            case 5: {
                bh.w("Trying to convert a function id to object");
                return dh.aaF;
            }
            case 6: {
                return a.fT;
            }
            case 7: {
                final StringBuffer sb = new StringBuffer();
                final d.a[] fv = a.fV;
                for (int length = fv.length, k = n2; k < length; ++k) {
                    final String l = j(fv[k]);
                    if (l == dh.aaJ) {
                        return dh.aaF;
                    }
                    sb.append(l);
                }
                return sb.toString();
            }
            case 8: {
                return a.fU;
            }
        }
    }
    
    public static Double p(final Object o) {
        if (s(o)) {
            return getDouble(o);
        }
        return ca(m(o));
    }
    
    public static Boolean q(final Object o) {
        if (o instanceof Boolean) {
            return (Boolean)o;
        }
        return cb(m(o));
    }
    
    public static d.a r(final Object o) {
        boolean fx = false;
        final d.a a = new d.a();
        if (o instanceof d.a) {
            return (d.a)o;
        }
        if (o instanceof String) {
            a.type = 1;
            a.fN = (String)o;
        }
        else if (o instanceof List) {
            a.type = 2;
            final List list = (List)o;
            final ArrayList list2 = new ArrayList<d.a>(list.size());
            final Iterator<Object> iterator = list.iterator();
            fx = false;
            while (iterator.hasNext()) {
                final d.a r = r(iterator.next());
                if (r == dh.aaN) {
                    return dh.aaN;
                }
                fx = (fx || r.fX);
                list2.add(r);
            }
            a.fO = list2.toArray(new d.a[0]);
        }
        else if (o instanceof Map) {
            a.type = 3;
            final Set<Map.Entry<Object, V>> entrySet = (Set<Map.Entry<Object, V>>)((Map)o).entrySet();
            final ArrayList list3 = new ArrayList<d.a>(entrySet.size());
            final ArrayList list4 = new ArrayList<d.a>(entrySet.size());
            final Iterator<Map.Entry<Object, V>> iterator2 = entrySet.iterator();
            fx = false;
            while (iterator2.hasNext()) {
                final Map.Entry<Object, V> entry = iterator2.next();
                final d.a r2 = r(entry.getKey());
                final d.a r3 = r(entry.getValue());
                if (r2 == dh.aaN || r3 == dh.aaN) {
                    return dh.aaN;
                }
                fx = (fx || r2.fX || r3.fX);
                list3.add(r2);
                list4.add(r3);
            }
            a.fP = list3.toArray(new d.a[0]);
            a.fQ = list4.toArray(new d.a[0]);
        }
        else if (s(o)) {
            a.type = 1;
            a.fN = o.toString();
        }
        else if (t(o)) {
            a.type = 6;
            a.fT = u(o);
        }
        else {
            if (!(o instanceof Boolean)) {
                final StringBuilder append = new StringBuilder().append("Converting to Value from unknown object type: ");
                String string;
                if (o == null) {
                    string = "null";
                }
                else {
                    string = o.getClass().toString();
                }
                bh.w(append.append(string).toString());
                return dh.aaN;
            }
            a.type = 8;
            a.fU = (boolean)o;
        }
        a.fX = fx;
        return a;
    }
    
    private static boolean s(final Object o) {
        return o instanceof Double || o instanceof Float || (o instanceof dg && ((dg)o).lI());
    }
    
    private static boolean t(final Object o) {
        return o instanceof Byte || o instanceof Short || o instanceof Integer || o instanceof Long || (o instanceof dg && ((dg)o).lJ());
    }
    
    private static long u(final Object o) {
        if (o instanceof Number) {
            return ((Number)o).longValue();
        }
        bh.w("getInt64 received non-Number");
        return 0L;
    }
}
