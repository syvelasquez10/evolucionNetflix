// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import java.util.Collection;
import java.util.Map;

public interface MultiValuedMap<K, V> extends Map<K, V>
{
    Map<K, Collection<V>> asMap();
    
    boolean containsMapping(final K p0, final V p1);
    
    Collection<V> getMappings(final K p0);
    
    int getNumberOfMappings(final K p0);
    
    boolean putAll(final K p0, final Iterable<? extends V> p1);
    
    boolean removeMapping(final K p0, final V p1);
    
    Collection<V> removeMappings(final K p0);
}
