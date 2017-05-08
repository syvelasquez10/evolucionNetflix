// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.cxxbridge;

import java.util.Map;
import android.os.Bundle;
import java.util.Iterator;
import java.util.List;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;

public class Arguments
{
    private static void addEntry(final WritableNativeMap writableNativeMap, final String s, Object nativeObject) {
        nativeObject = makeNativeObject(nativeObject);
        if (nativeObject == null) {
            writableNativeMap.putNull(s);
            return;
        }
        if (nativeObject instanceof Boolean) {
            writableNativeMap.putBoolean(s, (boolean)nativeObject);
            return;
        }
        if (nativeObject instanceof Integer) {
            writableNativeMap.putInt(s, (int)nativeObject);
            return;
        }
        if (nativeObject instanceof Number) {
            writableNativeMap.putDouble(s, ((Number)nativeObject).doubleValue());
            return;
        }
        if (nativeObject instanceof String) {
            writableNativeMap.putString(s, (String)nativeObject);
            return;
        }
        if (nativeObject instanceof WritableNativeArray) {
            writableNativeMap.putArray(s, (WritableArray)nativeObject);
            return;
        }
        if (nativeObject instanceof WritableNativeMap) {
            writableNativeMap.putMap(s, (WritableMap)nativeObject);
            return;
        }
        throw new IllegalArgumentException("Could not convert " + nativeObject.getClass());
    }
    
    public static <T> WritableNativeArray makeNativeArray(final Object o) {
        if (o == null) {
            return new WritableNativeArray();
        }
        return makeNativeArray(new Arguments$1(o));
    }
    
    public static WritableNativeArray makeNativeArray(final List list) {
        final WritableNativeArray writableNativeArray = new WritableNativeArray();
        if (list == null) {
            return writableNativeArray;
        }
        final Iterator<Object> iterator = list.iterator();
        while (iterator.hasNext()) {
            final Object nativeObject = makeNativeObject(iterator.next());
            if (nativeObject == null) {
                writableNativeArray.pushNull();
            }
            else if (nativeObject instanceof Boolean) {
                writableNativeArray.pushBoolean((boolean)nativeObject);
            }
            else if (nativeObject instanceof Integer) {
                writableNativeArray.pushInt((int)nativeObject);
            }
            else if (nativeObject instanceof Double) {
                writableNativeArray.pushDouble((double)nativeObject);
            }
            else if (nativeObject instanceof String) {
                writableNativeArray.pushString((String)nativeObject);
            }
            else if (nativeObject instanceof WritableNativeArray) {
                writableNativeArray.pushArray((WritableArray)nativeObject);
            }
            else {
                if (!(nativeObject instanceof WritableNativeMap)) {
                    throw new IllegalArgumentException("Could not convert " + ((WritableNativeMap)nativeObject).getClass());
                }
                writableNativeArray.pushMap((WritableMap)nativeObject);
            }
        }
        return writableNativeArray;
    }
    
    public static WritableNativeMap makeNativeMap(final Bundle bundle) {
        final WritableNativeMap writableNativeMap = new WritableNativeMap();
        if (bundle == null) {
            return writableNativeMap;
        }
        for (final String s : bundle.keySet()) {
            addEntry(writableNativeMap, s, bundle.get(s));
        }
        return writableNativeMap;
    }
    
    public static WritableNativeMap makeNativeMap(final Map<String, Object> map) {
        final WritableNativeMap writableNativeMap = new WritableNativeMap();
        if (map == null) {
            return writableNativeMap;
        }
        for (final Map.Entry<String, Object> entry : map.entrySet()) {
            addEntry(writableNativeMap, entry.getKey(), entry.getValue());
        }
        return writableNativeMap;
    }
    
    private static Object makeNativeObject(final Object o) {
        Object o2;
        if (o == null) {
            o2 = null;
        }
        else {
            if (o instanceof Float || o instanceof Long || o instanceof Byte || o instanceof Short) {
                return new Double(((Number)o).doubleValue());
            }
            if (o.getClass().isArray()) {
                return makeNativeArray(o);
            }
            if (o instanceof List) {
                return makeNativeArray((List)o);
            }
            if (o instanceof Map) {
                return makeNativeMap((Map<String, Object>)o);
            }
            o2 = o;
            if (o instanceof Bundle) {
                return makeNativeMap((Bundle)o);
            }
        }
        return o2;
    }
}
