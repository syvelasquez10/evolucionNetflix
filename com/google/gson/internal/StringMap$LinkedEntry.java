// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal;

import java.util.Map;

class StringMap$LinkedEntry<V> implements Entry<String, V>
{
    final int hash;
    final String key;
    StringMap$LinkedEntry<V> next;
    StringMap$LinkedEntry<V> nxt;
    StringMap$LinkedEntry<V> prv;
    V value;
    
    StringMap$LinkedEntry() {
        this(null, null, 0, null, null, null);
        this.prv = this;
        this.nxt = this;
    }
    
    StringMap$LinkedEntry(final String key, final V value, final int hash, final StringMap$LinkedEntry<V> next, final StringMap$LinkedEntry<V> nxt, final StringMap$LinkedEntry<V> prv) {
        this.key = key;
        this.value = value;
        this.hash = hash;
        this.next = next;
        this.nxt = nxt;
        this.prv = prv;
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o instanceof Entry) {
            final Entry entry = (Entry)o;
            final Object value = entry.getValue();
            if (this.key.equals(entry.getKey())) {
                if (this.value == null) {
                    if (value != null) {
                        return false;
                    }
                }
                else if (!this.value.equals(value)) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }
    
    public final String getKey() {
        return this.key;
    }
    
    @Override
    public final V getValue() {
        return this.value;
    }
    
    @Override
    public final int hashCode() {
        int hashCode = 0;
        int hashCode2;
        if (this.key == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.key.hashCode();
        }
        if (this.value != null) {
            hashCode = this.value.hashCode();
        }
        return hashCode2 ^ hashCode;
    }
    
    @Override
    public final V setValue(final V value) {
        final V value2 = this.value;
        this.value = value;
        return value2;
    }
    
    @Override
    public final String toString() {
        return this.key + "=" + this.value;
    }
}
