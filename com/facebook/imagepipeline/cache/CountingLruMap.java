// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.cache;

import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;
import com.android.internal.util.Predicate;
import java.util.LinkedHashMap;

public class CountingLruMap<K, V>
{
    private final LinkedHashMap<K, V> mMap;
    private int mSizeInBytes;
    private final ValueDescriptor<V> mValueDescriptor;
    
    public CountingLruMap(final ValueDescriptor<V> mValueDescriptor) {
        this.mMap = new LinkedHashMap<K, V>();
        this.mSizeInBytes = 0;
        this.mValueDescriptor = mValueDescriptor;
    }
    
    private int getValueSizeInBytes(final V v) {
        if (v == null) {
            return 0;
        }
        return this.mValueDescriptor.getSizeInBytes(v);
    }
    
    public V get(final K k) {
        synchronized (this) {
            return this.mMap.get(k);
        }
    }
    
    public int getCount() {
        synchronized (this) {
            return this.mMap.size();
        }
    }
    
    public K getFirstKey() {
        synchronized (this) {
            K next;
            if (this.mMap.isEmpty()) {
                next = null;
            }
            else {
                next = this.mMap.keySet().iterator().next();
            }
            return next;
        }
    }
    
    public ArrayList<Map.Entry<K, V>> getMatchingEntries(final Predicate<K> predicate) {
        final ArrayList<Map.Entry<Object, V>> list;
        synchronized (this) {
            list = (ArrayList<Map.Entry<Object, V>>)new ArrayList<Map.Entry<K, V>>();
            for (final Map.Entry<K, V> entry : this.mMap.entrySet()) {
                if (predicate == null || predicate.apply((Object)entry.getKey())) {
                    list.add(entry);
                }
            }
        }
        // monitorexit(this)
        return (ArrayList<Map.Entry<K, V>>)list;
    }
    
    public int getSizeInBytes() {
        synchronized (this) {
            return this.mSizeInBytes;
        }
    }
    
    public V put(final K k, final V v) {
        synchronized (this) {
            final V remove = this.mMap.remove(k);
            this.mSizeInBytes -= this.getValueSizeInBytes(remove);
            this.mMap.put(k, v);
            this.mSizeInBytes += this.getValueSizeInBytes(v);
            return remove;
        }
    }
    
    public V remove(final K k) {
        synchronized (this) {
            final V remove = this.mMap.remove(k);
            this.mSizeInBytes -= this.getValueSizeInBytes(remove);
            return remove;
        }
    }
    
    public ArrayList<V> removeAll(final Predicate<K> predicate) {
        final ArrayList<Object> list;
        synchronized (this) {
            list = new ArrayList<Object>();
            final Iterator<Map.Entry<K, V>> iterator = this.mMap.entrySet().iterator();
            while (iterator.hasNext()) {
                final Map.Entry<K, V> entry = iterator.next();
                if (predicate == null || predicate.apply((Object)entry.getKey())) {
                    list.add(entry.getValue());
                    this.mSizeInBytes -= this.getValueSizeInBytes((V)entry.getValue());
                    iterator.remove();
                }
            }
        }
        // monitorexit(this)
        return (ArrayList<V>)list;
    }
}
