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

class di
{
    private static final Object arJ;
    private static Long arK;
    private static Double arL;
    private static dh arM;
    private static String arN;
    private static Boolean arO;
    private static List<Object> arP;
    private static Map<Object, Object> arQ;
    private static d.a arR;
    
    static {
        arJ = null;
        di.arK = new Long(0L);
        di.arL = new Double(0.0);
        di.arM = dh.z(0L);
        di.arN = new String("");
        di.arO = new Boolean(false);
        di.arP = new ArrayList<Object>(0);
        di.arQ = new HashMap<Object, Object>();
        di.arR = u(di.arN);
    }
    
    public static d.a cU(final String ga) {
        final d.a a = new d.a();
        a.type = 5;
        a.gA = ga;
        return a;
    }
    
    private static dh cV(final String s) {
        try {
            return dh.cT(s);
        }
        catch (NumberFormatException ex) {
            bh.T("Failed to convert '" + s + "' to a number.");
            return di.arM;
        }
    }
    
    private static Long cW(final String s) {
        final dh cv = cV(s);
        if (cv == di.arM) {
            return di.arK;
        }
        return cv.longValue();
    }
    
    private static Double cX(final String s) {
        final dh cv = cV(s);
        if (cv == di.arM) {
            return di.arL;
        }
        return cv.doubleValue();
    }
    
    private static Boolean cY(final String s) {
        if ("true".equalsIgnoreCase(s)) {
            return Boolean.TRUE;
        }
        if ("false".equalsIgnoreCase(s)) {
            return Boolean.FALSE;
        }
        return di.arO;
    }
    
    private static double getDouble(final Object o) {
        if (o instanceof Number) {
            return ((Number)o).doubleValue();
        }
        bh.T("getDouble received non-Number");
        return 0.0;
    }
    
    public static String j(final d.a a) {
        return p(o(a));
    }
    
    public static dh k(final d.a a) {
        return q(o(a));
    }
    
    public static Long l(final d.a a) {
        return r(o(a));
    }
    
    public static Double m(final d.a a) {
        return s(o(a));
    }
    
    public static Boolean n(final d.a a) {
        return t(o(a));
    }
    
    public static Object o(final d.a a) {
        final int n = 0;
        final int n2 = 0;
        int i = 0;
        if (a == null) {
            return di.arJ;
        }
        switch (a.type) {
            default: {
                bh.T("Failed to convert a value of type: " + a.type);
                return di.arJ;
            }
            case 1: {
                return a.gv;
            }
            case 2: {
                final ArrayList<Object> list = new ArrayList<Object>(a.gw.length);
                for (d.a[] gw = a.gw; i < gw.length; ++i) {
                    final Object o = o(gw[i]);
                    if (o == di.arJ) {
                        return di.arJ;
                    }
                    list.add(o);
                }
                return list;
            }
            case 3: {
                if (a.gx.length != a.gy.length) {
                    bh.T("Converting an invalid value to object: " + a.toString());
                    return di.arJ;
                }
                final HashMap<Object, Object> hashMap = new HashMap<Object, Object>(a.gy.length);
                for (int j = n; j < a.gx.length; ++j) {
                    final Object o2 = o(a.gx[j]);
                    final Object o3 = o(a.gy[j]);
                    if (o2 == di.arJ || o3 == di.arJ) {
                        return di.arJ;
                    }
                    hashMap.put(o2, o3);
                }
                return hashMap;
            }
            case 4: {
                bh.T("Trying to convert a macro reference to object");
                return di.arJ;
            }
            case 5: {
                bh.T("Trying to convert a function id to object");
                return di.arJ;
            }
            case 6: {
                return a.gB;
            }
            case 7: {
                final StringBuffer sb = new StringBuffer();
                final d.a[] gd = a.gD;
                for (int length = gd.length, k = n2; k < length; ++k) {
                    final String l = j(gd[k]);
                    if (l == di.arN) {
                        return di.arJ;
                    }
                    sb.append(l);
                }
                return sb.toString();
            }
            case 8: {
                return a.gC;
            }
        }
    }
    
    public static String p(final Object o) {
        if (o == null) {
            return di.arN;
        }
        return o.toString();
    }
    
    public static Object pC() {
        return di.arJ;
    }
    
    public static Long pD() {
        return di.arK;
    }
    
    public static Double pE() {
        return di.arL;
    }
    
    public static Boolean pF() {
        return di.arO;
    }
    
    public static dh pG() {
        return di.arM;
    }
    
    public static String pH() {
        return di.arN;
    }
    
    public static d.a pI() {
        return di.arR;
    }
    
    public static dh q(final Object o) {
        if (o instanceof dh) {
            return (dh)o;
        }
        if (w(o)) {
            return dh.z(x(o));
        }
        if (v(o)) {
            return dh.a(Double.valueOf(getDouble(o)));
        }
        return cV(p(o));
    }
    
    public static Long r(final Object o) {
        if (w(o)) {
            return x(o);
        }
        return cW(p(o));
    }
    
    public static Double s(final Object o) {
        if (v(o)) {
            return getDouble(o);
        }
        return cX(p(o));
    }
    
    public static Boolean t(final Object o) {
        if (o instanceof Boolean) {
            return (Boolean)o;
        }
        return cY(p(o));
    }
    
    public static d.a u(final Object o) {
        boolean gf = false;
        final d.a a = new d.a();
        if (o instanceof d.a) {
            return (d.a)o;
        }
        if (o instanceof String) {
            a.type = 1;
            a.gv = (String)o;
        }
        else if (o instanceof List) {
            a.type = 2;
            final List list = (List)o;
            final ArrayList list2 = new ArrayList<d.a>(list.size());
            final Iterator<Object> iterator = list.iterator();
            gf = false;
            while (iterator.hasNext()) {
                final d.a u = u(iterator.next());
                if (u == di.arR) {
                    return di.arR;
                }
                gf = (gf || u.gF);
                list2.add(u);
            }
            a.gw = list2.toArray(new d.a[0]);
        }
        else if (o instanceof Map) {
            a.type = 3;
            final Set<Map.Entry<Object, V>> entrySet = (Set<Map.Entry<Object, V>>)((Map)o).entrySet();
            final ArrayList list3 = new ArrayList<d.a>(entrySet.size());
            final ArrayList list4 = new ArrayList<d.a>(entrySet.size());
            final Iterator<Map.Entry<Object, V>> iterator2 = entrySet.iterator();
            gf = false;
            while (iterator2.hasNext()) {
                final Map.Entry<Object, V> entry = iterator2.next();
                final d.a u2 = u(entry.getKey());
                final d.a u3 = u(entry.getValue());
                if (u2 == di.arR || u3 == di.arR) {
                    return di.arR;
                }
                gf = (gf || u2.gF || u3.gF);
                list3.add(u2);
                list4.add(u3);
            }
            a.gx = list3.toArray(new d.a[0]);
            a.gy = list4.toArray(new d.a[0]);
        }
        else if (v(o)) {
            a.type = 1;
            a.gv = o.toString();
        }
        else if (w(o)) {
            a.type = 6;
            a.gB = x(o);
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
                bh.T(append.append(string).toString());
                return di.arR;
            }
            a.type = 8;
            a.gC = (boolean)o;
        }
        a.gF = gf;
        return a;
    }
    
    private static boolean v(final Object o) {
        return o instanceof Double || o instanceof Float || (o instanceof dh && ((dh)o).px());
    }
    
    private static boolean w(final Object o) {
        return o instanceof Byte || o instanceof Short || o instanceof Integer || o instanceof Long || (o instanceof dh && ((dh)o).py());
    }
    
    private static long x(final Object o) {
        if (o instanceof Number) {
            return ((Number)o).longValue();
        }
        bh.T("getInt64 received non-Number");
        return 0L;
    }
}
