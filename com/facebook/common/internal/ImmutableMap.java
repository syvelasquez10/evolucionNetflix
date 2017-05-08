// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.internal;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

public class ImmutableMap<K, V> extends HashMap<K, V>
{
    public static <K, V> Map<K, V> of(final K k, final V v) {
        final HashMap<K, V> hashMap = new HashMap<K, V>();
        hashMap.put(k, v);
        return Collections.unmodifiableMap((Map<? extends K, ? extends V>)hashMap);
    }
    
    public static <K, V> Map<K, V> of(final K k, final V v, final K i, final V v2, final K j, final V v3, final K l, final V v4) {
        final HashMap<K, V> hashMap = new HashMap<K, V>();
        hashMap.put(k, v);
        hashMap.put(i, v2);
        hashMap.put(j, v3);
        hashMap.put(l, v4);
        return Collections.unmodifiableMap((Map<? extends K, ? extends V>)hashMap);
    }
    
    public static <K, V> Map<K, V> of(final K k, final V v, final K i, final V v2, final K j, final V v3, final K l, final V v4, final K m, final V v5) {
        final HashMap<K, V> hashMap = new HashMap<K, V>();
        hashMap.put(k, v);
        hashMap.put(i, v2);
        hashMap.put(j, v3);
        hashMap.put(l, v4);
        hashMap.put(m, v5);
        return Collections.unmodifiableMap((Map<? extends K, ? extends V>)hashMap);
    }
}
