// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.common;

import java.util.Map;
import java.util.HashMap;

public class MapBuilder
{
    public static <K, V> MapBuilder$Builder<K, V> builder() {
        return new MapBuilder$Builder<K, V>(null);
    }
    
    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap<K, V>();
    }
    
    public static <K, V> Map<K, V> of() {
        return (Map<K, V>)newHashMap();
    }
    
    public static <K, V> Map<K, V> of(final K k, final V v) {
        final Map<K, V> of = of();
        of.put(k, v);
        return of;
    }
    
    public static <K, V> Map<K, V> of(final K k, final V v, final K i, final V v2) {
        final Map<K, V> of = of();
        of.put(k, v);
        of.put(i, v2);
        return of;
    }
    
    public static <K, V> Map<K, V> of(final K k, final V v, final K i, final V v2, final K j, final V v3) {
        final Map<K, V> of = of();
        of.put(k, v);
        of.put(i, v2);
        of.put(j, v3);
        return of;
    }
    
    public static <K, V> Map<K, V> of(final K k, final V v, final K i, final V v2, final K j, final V v3, final K l, final V v4) {
        final Map<K, V> of = of();
        of.put(k, v);
        of.put(i, v2);
        of.put(j, v3);
        of.put(l, v4);
        return of;
    }
    
    public static <K, V> Map<K, V> of(final K k, final V v, final K i, final V v2, final K j, final V v3, final K l, final V v4, final K m, final V v5) {
        final Map<K, V> of = of();
        of.put(k, v);
        of.put(i, v2);
        of.put(j, v3);
        of.put(l, v4);
        of.put(m, v5);
        return of;
    }
    
    public static <K, V> Map<K, V> of(final K k, final V v, final K i, final V v2, final K j, final V v3, final K l, final V v4, final K m, final V v5, final K k2, final V v6) {
        final Map<K, V> of = of();
        of.put(k, v);
        of.put(i, v2);
        of.put(j, v3);
        of.put(l, v4);
        of.put(m, v5);
        of.put(k2, v6);
        return of;
    }
}
