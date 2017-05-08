// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;
import java.util.Collection;
import java.util.LinkedHashMap;

public class MultiValuedHashMap<K, V> implements MultiValuedMap<K, V>
{
    private LinkedHashMap<K, Collection<V>> mImplMap;
    
    public MultiValuedHashMap() {
        this.mImplMap = new LinkedHashMap<K, Collection<V>>();
    }
    
    public MultiValuedHashMap(final int n) {
        this.mImplMap = new LinkedHashMap<K, Collection<V>>(n);
    }
    
    @Override
    public Map<K, Collection<V>> asMap() {
        return this.mImplMap;
    }
    
    @Override
    public void clear() {
        this.mImplMap.clear();
    }
    
    @Override
    public boolean containsKey(final Object o) {
        return this.mImplMap.containsKey(o);
    }
    
    @Override
    public boolean containsMapping(final K k, final V v) {
        final Collection<V> collection = this.mImplMap.get(k);
        return collection != null && collection.contains(v);
    }
    
    @Override
    public boolean containsValue(final Object o) {
        final Iterator<K> iterator = this.mImplMap.keySet().iterator();
        while (iterator.hasNext()) {
            final Collection<V> collection = this.mImplMap.get(iterator.next());
            if (collection != null && collection.contains(o)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public Set<Entry<K, V>> entrySet() {
        final LinkedHashSet<MultiValuedHashMap$MultiValuedMapEntry<K, Object>> set = (LinkedHashSet<MultiValuedHashMap$MultiValuedMapEntry<K, Object>>)new LinkedHashSet<Entry<K, V>>();
        for (final Map.Entry<K, Collection<V>> entry : this.mImplMap.entrySet()) {
            final Iterator<V> iterator2 = entry.getValue().iterator();
            while (iterator2.hasNext()) {
                set.add((MultiValuedHashMap$MultiValuedMapEntry<K, Object>)new MultiValuedHashMap$MultiValuedMapEntry<K, Object>(entry.getKey(), iterator2.next()));
            }
        }
        return (Set<Entry<K, V>>)set;
    }
    
    @Override
    public V get(final Object o) {
        final Collection<V> collection = this.mImplMap.get(o);
        if (collection == null || collection.size() < 1) {
            return null;
        }
        final ArrayList<V> list = (ArrayList<V>)collection;
        return list.get(list.size() - 1);
    }
    
    @Override
    public Collection<V> getMappings(final K k) {
        return this.mImplMap.get(k);
    }
    
    @Override
    public int getNumberOfMappings(final K k) {
        final Collection<V> collection = this.mImplMap.get(k);
        if (collection != null) {
            return collection.size();
        }
        return 0;
    }
    
    @Override
    public boolean isEmpty() {
        return this.mImplMap.isEmpty();
    }
    
    @Override
    public Set<K> keySet() {
        return this.mImplMap.keySet();
    }
    
    @Override
    public V put(final K k, final V v) {
        Collection<V> collection;
        if ((collection = this.mImplMap.get(k)) == null) {
            collection = new ArrayList<V>();
            this.mImplMap.put(k, collection);
        }
        collection.add(v);
        return v;
    }
    
    @Override
    public void putAll(final Map<? extends K, ? extends V> map) {
        if (map instanceof MultiValuedMap) {
            final MultiValuedMap<Object, Object> multiValuedMap = (MultiValuedMap<Object, Object>)map;
            for (final K next : map.keySet()) {
                final Collection<V> mappings = multiValuedMap.getMappings(next);
                final Collection<V> collection = this.mImplMap.get(next);
                if (collection != null) {
                    collection.addAll((Collection<? extends V>)mappings);
                }
                else {
                    this.mImplMap.put(next, mappings);
                }
            }
        }
        else {
            for (final K next2 : map.keySet()) {
                this.put(next2, map.get(next2));
            }
        }
    }
    
    @Override
    public boolean putAll(final K k, final Iterable<? extends V> iterable) {
        return false;
    }
    
    @Override
    public V remove(final Object o) {
        final Collection collection = this.mImplMap.remove(o);
        if (collection == null || collection.size() < 1) {
            return null;
        }
        final ArrayList<V> list = (ArrayList<V>)collection;
        return list.get(list.size() - 1);
    }
    
    @Override
    public boolean removeMapping(final K k, final V v) {
        final Collection<V> collection = this.mImplMap.get(k);
        return collection != null && collection.remove(v);
    }
    
    @Override
    public Collection<V> removeMappings(final K k) {
        return this.mImplMap.remove(k);
    }
    
    @Override
    public int size() {
        return this.mImplMap.size();
    }
    
    @Override
    public Collection<V> values() {
        final ArrayList<V> list = new ArrayList<V>();
        final Iterator<Map.Entry<K, Collection<V>>> iterator = this.mImplMap.entrySet().iterator();
        while (iterator.hasNext()) {
            final Iterator<V> iterator2 = iterator.next().getValue().iterator();
            while (iterator2.hasNext()) {
                list.add(iterator2.next());
            }
        }
        return list;
    }
}
