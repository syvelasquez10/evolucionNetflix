// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import java.util.Map;

class MultiValuedHashMap$MultiValuedMapEntry<K, V> implements Entry<K, V>
{
    private K key;
    private V value;
    
    public MultiValuedHashMap$MultiValuedMapEntry(final K key, final V value) {
        this.key = key;
        this.value = value;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final MultiValuedHashMap$MultiValuedMapEntry multiValuedHashMap$MultiValuedMapEntry = (MultiValuedHashMap$MultiValuedMapEntry)o;
            Label_0059: {
                if (this.key != null) {
                    if (this.key.equals(multiValuedHashMap$MultiValuedMapEntry.key)) {
                        break Label_0059;
                    }
                }
                else if (multiValuedHashMap$MultiValuedMapEntry.key == null) {
                    break Label_0059;
                }
                return false;
            }
            if (this.value != null) {
                return this.value.equals(multiValuedHashMap$MultiValuedMapEntry.value);
            }
            if (multiValuedHashMap$MultiValuedMapEntry.value != null) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public K getKey() {
        return this.key;
    }
    
    @Override
    public V getValue() {
        return this.value;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        int hashCode2;
        if (this.key != null) {
            hashCode2 = this.key.hashCode();
        }
        else {
            hashCode2 = 0;
        }
        if (this.value != null) {
            hashCode = this.value.hashCode();
        }
        return hashCode2 * 31 + hashCode;
    }
    
    @Override
    public V setValue(final V value) {
        return this.value = value;
    }
}
