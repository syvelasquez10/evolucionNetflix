// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Set;
import java.util.Iterator;
import java.util.HashMap;
import java.util.ArrayList;
import com.google.android.gms.internal.zzag$zza;
import java.util.Map;
import java.util.List;

public class zzdf
{
    private static final Object zzaSN;
    private static Long zzaSO;
    private static Double zzaSP;
    private static zzde zzaSQ;
    private static String zzaSR;
    private static Boolean zzaSS;
    private static List<Object> zzaST;
    private static Map<Object, Object> zzaSU;
    private static zzag$zza zzaSV;
    
    static {
        zzaSN = null;
        zzdf.zzaSO = new Long(0L);
        zzdf.zzaSP = new Double(0.0);
        zzdf.zzaSQ = zzde.zzT(0L);
        zzdf.zzaSR = new String("");
        zzdf.zzaSS = new Boolean(false);
        zzdf.zzaST = new ArrayList<Object>(0);
        zzdf.zzaSU = new HashMap<Object, Object>();
        zzdf.zzaSV = zzK(zzdf.zzaSR);
    }
    
    private static double getDouble(final Object o) {
        if (o instanceof Number) {
            return ((Number)o).doubleValue();
        }
        zzbg.e("getDouble received non-Number");
        return 0.0;
    }
    
    public static Object zzBa() {
        return zzdf.zzaSN;
    }
    
    public static Long zzBb() {
        return zzdf.zzaSO;
    }
    
    public static Boolean zzBd() {
        return zzdf.zzaSS;
    }
    
    public static zzde zzBe() {
        return zzdf.zzaSQ;
    }
    
    public static String zzBf() {
        return zzdf.zzaSR;
    }
    
    public static zzag$zza zzBg() {
        return zzdf.zzaSV;
    }
    
    public static String zzF(final Object o) {
        if (o == null) {
            return zzdf.zzaSR;
        }
        return o.toString();
    }
    
    public static zzde zzG(final Object o) {
        if (o instanceof zzde) {
            return (zzde)o;
        }
        if (zzM(o)) {
            return zzde.zzT(zzN(o));
        }
        if (zzL(o)) {
            return zzde.zza(Double.valueOf(getDouble(o)));
        }
        return zzeZ(zzF(o));
    }
    
    public static Long zzH(final Object o) {
        if (zzM(o)) {
            return zzN(o);
        }
        return zzfa(zzF(o));
    }
    
    public static Boolean zzJ(final Object o) {
        if (o instanceof Boolean) {
            return (Boolean)o;
        }
        return zzfc(zzF(o));
    }
    
    public static zzag$zza zzK(final Object o) {
        boolean zzje = false;
        final zzag$zza zzag$zza = new zzag$zza();
        if (o instanceof zzag$zza) {
            return (zzag$zza)o;
        }
        if (o instanceof String) {
            zzag$zza.type = 1;
            zzag$zza.zziU = (String)o;
        }
        else if (o instanceof List) {
            zzag$zza.type = 2;
            final List list = (List)o;
            final ArrayList list2 = new ArrayList<zzag$zza>(list.size());
            final Iterator<Object> iterator = list.iterator();
            zzje = false;
            while (iterator.hasNext()) {
                final zzag$zza zzK = zzK(iterator.next());
                if (zzK == zzdf.zzaSV) {
                    return zzdf.zzaSV;
                }
                zzje = (zzje || zzK.zzje);
                list2.add(zzK);
            }
            zzag$zza.zziV = list2.toArray(new zzag$zza[0]);
        }
        else if (o instanceof Map) {
            zzag$zza.type = 3;
            final Set<Map.Entry<Object, V>> entrySet = (Set<Map.Entry<Object, V>>)((Map)o).entrySet();
            final ArrayList list3 = new ArrayList<zzag$zza>(entrySet.size());
            final ArrayList list4 = new ArrayList<zzag$zza>(entrySet.size());
            final Iterator<Map.Entry<Object, V>> iterator2 = entrySet.iterator();
            zzje = false;
            while (iterator2.hasNext()) {
                final Map.Entry<Object, V> entry = iterator2.next();
                final zzag$zza zzK2 = zzK(entry.getKey());
                final zzag$zza zzK3 = zzK(entry.getValue());
                if (zzK2 == zzdf.zzaSV || zzK3 == zzdf.zzaSV) {
                    return zzdf.zzaSV;
                }
                zzje = (zzje || zzK2.zzje || zzK3.zzje);
                list3.add(zzK2);
                list4.add(zzK3);
            }
            zzag$zza.zziW = list3.toArray(new zzag$zza[0]);
            zzag$zza.zziX = list4.toArray(new zzag$zza[0]);
        }
        else if (zzL(o)) {
            zzag$zza.type = 1;
            zzag$zza.zziU = o.toString();
        }
        else if (zzM(o)) {
            zzag$zza.type = 6;
            zzag$zza.zzja = zzN(o);
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
                zzbg.e(append.append(string).toString());
                return zzdf.zzaSV;
            }
            zzag$zza.type = 8;
            zzag$zza.zzjb = (boolean)o;
        }
        zzag$zza.zzje = zzje;
        return zzag$zza;
    }
    
    private static boolean zzL(final Object o) {
        return o instanceof Double || o instanceof Float || (o instanceof zzde && ((zzde)o).zzAV());
    }
    
    private static boolean zzM(final Object o) {
        return o instanceof Byte || o instanceof Short || o instanceof Integer || o instanceof Long || (o instanceof zzde && ((zzde)o).zzAW());
    }
    
    private static long zzN(final Object o) {
        if (o instanceof Number) {
            return ((Number)o).longValue();
        }
        zzbg.e("getInt64 received non-Number");
        return 0L;
    }
    
    private static zzde zzeZ(final String s) {
        try {
            return zzde.zzeX(s);
        }
        catch (NumberFormatException ex) {
            zzbg.e("Failed to convert '" + s + "' to a number.");
            return zzdf.zzaSQ;
        }
    }
    
    private static Long zzfa(final String s) {
        final zzde zzeZ = zzeZ(s);
        if (zzeZ == zzdf.zzaSQ) {
            return zzdf.zzaSO;
        }
        return zzeZ.longValue();
    }
    
    private static Boolean zzfc(final String s) {
        if ("true".equalsIgnoreCase(s)) {
            return Boolean.TRUE;
        }
        if ("false".equalsIgnoreCase(s)) {
            return Boolean.FALSE;
        }
        return zzdf.zzaSS;
    }
    
    public static String zzg(final zzag$zza zzag$zza) {
        return zzF(zzl(zzag$zza));
    }
    
    public static zzde zzh(final zzag$zza zzag$zza) {
        return zzG(zzl(zzag$zza));
    }
    
    public static Long zzi(final zzag$zza zzag$zza) {
        return zzH(zzl(zzag$zza));
    }
    
    public static Boolean zzk(final zzag$zza zzag$zza) {
        return zzJ(zzl(zzag$zza));
    }
    
    public static Object zzl(final zzag$zza zzag$zza) {
        final int n = 0;
        final int n2 = 0;
        int i = 0;
        if (zzag$zza == null) {
            return zzdf.zzaSN;
        }
        switch (zzag$zza.type) {
            default: {
                zzbg.e("Failed to convert a value of type: " + zzag$zza.type);
                return zzdf.zzaSN;
            }
            case 1: {
                return zzag$zza.zziU;
            }
            case 2: {
                final ArrayList<Object> list = new ArrayList<Object>(zzag$zza.zziV.length);
                for (zzag$zza[] zziV = zzag$zza.zziV; i < zziV.length; ++i) {
                    final Object zzl = zzl(zziV[i]);
                    if (zzl == zzdf.zzaSN) {
                        return zzdf.zzaSN;
                    }
                    list.add(zzl);
                }
                return list;
            }
            case 3: {
                if (zzag$zza.zziW.length != zzag$zza.zziX.length) {
                    zzbg.e("Converting an invalid value to object: " + zzag$zza.toString());
                    return zzdf.zzaSN;
                }
                final HashMap<Object, Object> hashMap = new HashMap<Object, Object>(zzag$zza.zziX.length);
                for (int j = n; j < zzag$zza.zziW.length; ++j) {
                    final Object zzl2 = zzl(zzag$zza.zziW[j]);
                    final Object zzl3 = zzl(zzag$zza.zziX[j]);
                    if (zzl2 == zzdf.zzaSN || zzl3 == zzdf.zzaSN) {
                        return zzdf.zzaSN;
                    }
                    hashMap.put(zzl2, zzl3);
                }
                return hashMap;
            }
            case 4: {
                zzbg.e("Trying to convert a macro reference to object");
                return zzdf.zzaSN;
            }
            case 5: {
                zzbg.e("Trying to convert a function id to object");
                return zzdf.zzaSN;
            }
            case 6: {
                return zzag$zza.zzja;
            }
            case 7: {
                final StringBuffer sb = new StringBuffer();
                final zzag$zza[] zzjc = zzag$zza.zzjc;
                for (int length = zzjc.length, k = n2; k < length; ++k) {
                    final String zzg = zzg(zzjc[k]);
                    if (zzg == zzdf.zzaSR) {
                        return zzdf.zzaSN;
                    }
                    sb.append(zzg);
                }
                return sb.toString();
            }
            case 8: {
                return zzag$zza.zzjb;
            }
        }
    }
}
