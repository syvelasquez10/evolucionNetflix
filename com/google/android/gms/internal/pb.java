// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.Set;
import java.util.ArrayList;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.Asset;
import java.util.List;

public final class pb
{
    private static int a(final String s, final pc.a.a[] array) {
        final int length = array.length;
        int i = 0;
        int n = 14;
        while (i < length) {
            final pc.a.a a = array[i];
            int type;
            if (n == 14) {
                if (a.type == 9 || a.type == 2 || a.type == 6) {
                    type = a.type;
                }
                else {
                    type = n;
                    if (a.type != 14) {
                        throw new IllegalArgumentException("Unexpected TypedValue type: " + a.type + " for key " + s);
                    }
                }
            }
            else if (a.type != (type = n)) {
                throw new IllegalArgumentException("The ArrayList elements should all be the same type, but ArrayList with key " + s + " contains items of type " + n + " and " + a.type);
            }
            ++i;
            n = type;
        }
        return n;
    }
    
    static int a(final List<Asset> list, final Asset asset) {
        list.add(asset);
        return list.size() - 1;
    }
    
    public static a a(final DataMap dataMap) {
        final pc pc = new pc();
        final ArrayList<Asset> list = new ArrayList<Asset>();
        pc.avS = a(dataMap, list);
        return new a(pc, list);
    }
    
    private static pc.a.a a(final List<Asset> list, Object o) {
        final pc.a.a a = new pc.a.a();
        if (o == null) {
            a.type = 14;
            return a;
        }
        a.avW = new pc.a.a.a();
        if (o instanceof String) {
            a.type = 2;
            a.avW.avY = (String)o;
        }
        else if (o instanceof Integer) {
            a.type = 6;
            a.avW.awc = (int)o;
        }
        else if (o instanceof Long) {
            a.type = 5;
            a.avW.awb = (long)o;
        }
        else if (o instanceof Double) {
            a.type = 3;
            a.avW.avZ = (double)o;
        }
        else if (o instanceof Float) {
            a.type = 4;
            a.avW.awa = (float)o;
        }
        else if (o instanceof Boolean) {
            a.type = 8;
            a.avW.awe = (boolean)o;
        }
        else if (o instanceof Byte) {
            a.type = 7;
            a.avW.awd = (byte)o;
        }
        else if (o instanceof byte[]) {
            a.type = 1;
            a.avW.avX = (byte[])o;
        }
        else if (o instanceof String[]) {
            a.type = 11;
            a.avW.awh = (String[])o;
        }
        else if (o instanceof long[]) {
            a.type = 12;
            a.avW.awi = (long[])o;
        }
        else if (o instanceof float[]) {
            a.type = 15;
            a.avW.awj = (float[])o;
        }
        else if (o instanceof Asset) {
            a.type = 13;
            a.avW.awk = a(list, (Asset)o);
        }
        else if (o instanceof DataMap) {
            a.type = 9;
            final DataMap dataMap = (DataMap)o;
            final Set<String> keySet = dataMap.keySet();
            final pc.a[] awf = new pc.a[keySet.size()];
            final Iterator<String> iterator = keySet.iterator();
            int n = 0;
            while (iterator.hasNext()) {
                final String name = iterator.next();
                awf[n] = new pc.a();
                awf[n].name = name;
                awf[n].avU = a(list, dataMap.get(name));
                ++n;
            }
            a.avW.awf = awf;
        }
        else {
            if (!(o instanceof ArrayList)) {
                throw new RuntimeException("newFieldValueFromValue: unexpected value " + o.getClass().getSimpleName());
            }
            a.type = 10;
            final ArrayList list2 = (ArrayList)o;
            final pc.a.a[] awg = new pc.a.a[list2.size()];
            o = null;
            final int size = list2.size();
            int i = 0;
            int type = 14;
            while (i < size) {
                final Object value = list2.get(i);
                final pc.a.a a2 = a(list, value);
                if (a2.type != 14 && a2.type != 2 && a2.type != 6 && a2.type != 9) {
                    throw new IllegalArgumentException("The only ArrayList element types supported by DataBundleUtil are String, Integer, Bundle, and null, but this ArrayList contains a " + value.getClass());
                }
                if (type == 14 && a2.type != 14) {
                    type = a2.type;
                    o = value;
                }
                else if (a2.type != type) {
                    throw new IllegalArgumentException("ArrayList elements must all be of the sameclass, but this one contains a " + o.getClass() + " and a " + value.getClass());
                }
                awg[i] = a2;
                ++i;
            }
            a.avW.awg = awg;
        }
        return a;
    }
    
    public static DataMap a(final a a) {
        final DataMap dataMap = new DataMap();
        final pc.a[] avS = a.avQ.avS;
        for (int length = avS.length, i = 0; i < length; ++i) {
            final pc.a a2 = avS[i];
            a(a.avR, dataMap, a2.name, a2.avU);
        }
        return dataMap;
    }
    
    private static ArrayList a(final List<Asset> list, final pc.a.a.a a, final int n) {
        final ArrayList<DataMap> list2 = new ArrayList<DataMap>(a.awg.length);
        final pc.a.a[] awg = a.awg;
        for (int length = awg.length, i = 0; i < length; ++i) {
            final pc.a.a a2 = awg[i];
            if (a2.type == 14) {
                list2.add(null);
            }
            else if (n == 9) {
                final DataMap dataMap = new DataMap();
                final pc.a[] awf = a2.avW.awf;
                for (int length2 = awf.length, j = 0; j < length2; ++j) {
                    final pc.a a3 = awf[j];
                    a(list, dataMap, a3.name, a3.avU);
                }
                list2.add(dataMap);
            }
            else if (n == 2) {
                list2.add((DataMap)a2.avW.avY);
            }
            else {
                if (n != 6) {
                    throw new IllegalArgumentException("Unexpected typeOfArrayList: " + n);
                }
                list2.add((DataMap)a2.avW.awc);
            }
        }
        return list2;
    }
    
    private static void a(final List<Asset> list, final DataMap dataMap, final String s, final pc.a.a a) {
        final int type = a.type;
        if (type == 14) {
            dataMap.putString(s, null);
            return;
        }
        final pc.a.a.a avW = a.avW;
        if (type == 1) {
            dataMap.putByteArray(s, avW.avX);
            return;
        }
        if (type == 11) {
            dataMap.putStringArray(s, avW.awh);
            return;
        }
        if (type == 12) {
            dataMap.putLongArray(s, avW.awi);
            return;
        }
        if (type == 15) {
            dataMap.putFloatArray(s, avW.awj);
            return;
        }
        if (type == 2) {
            dataMap.putString(s, avW.avY);
            return;
        }
        if (type == 3) {
            dataMap.putDouble(s, avW.avZ);
            return;
        }
        if (type == 4) {
            dataMap.putFloat(s, avW.awa);
            return;
        }
        if (type == 5) {
            dataMap.putLong(s, avW.awb);
            return;
        }
        if (type == 6) {
            dataMap.putInt(s, avW.awc);
            return;
        }
        if (type == 7) {
            dataMap.putByte(s, (byte)avW.awd);
            return;
        }
        if (type == 8) {
            dataMap.putBoolean(s, avW.awe);
            return;
        }
        if (type == 13) {
            if (list == null) {
                throw new RuntimeException("populateBundle: unexpected type for: " + s);
            }
            dataMap.putAsset(s, list.get((int)avW.awk));
        }
        else {
            if (type == 9) {
                final DataMap dataMap2 = new DataMap();
                final pc.a[] awf = avW.awf;
                for (int length = awf.length, i = 0; i < length; ++i) {
                    final pc.a a2 = awf[i];
                    a(list, dataMap2, a2.name, a2.avU);
                }
                dataMap.putDataMap(s, dataMap2);
                return;
            }
            if (type != 10) {
                throw new RuntimeException("populateBundle: unexpected type " + type);
            }
            final int a3 = a(s, avW.awg);
            final ArrayList a4 = a(list, avW, a3);
            if (a3 == 14) {
                dataMap.putStringArrayList(s, a4);
                return;
            }
            if (a3 == 9) {
                dataMap.putDataMapArrayList(s, a4);
                return;
            }
            if (a3 == 2) {
                dataMap.putStringArrayList(s, a4);
                return;
            }
            if (a3 == 6) {
                dataMap.putIntegerArrayList(s, a4);
                return;
            }
            throw new IllegalStateException("Unexpected typeOfArrayList: " + a3);
        }
    }
    
    private static pc.a[] a(final DataMap dataMap, final List<Asset> list) {
        final Set<String> keySet = dataMap.keySet();
        final pc.a[] array = new pc.a[keySet.size()];
        final Iterator<String> iterator = keySet.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            final String name = iterator.next();
            final Object value = dataMap.get(name);
            array[n] = new pc.a();
            array[n].name = name;
            array[n].avU = a(list, value);
            ++n;
        }
        return array;
    }
    
    public static class a
    {
        public final pc avQ;
        public final List<Asset> avR;
        
        public a(final pc avQ, final List<Asset> avR) {
            this.avQ = avQ;
            this.avR = avR;
        }
    }
}
