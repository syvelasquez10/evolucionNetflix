// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal;

import java.util.AbstractCollection;
import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Random;
import java.util.Collection;
import java.util.Set;
import java.util.Map;
import java.util.AbstractMap;

public final class StringMap<V> extends AbstractMap<String, V>
{
    private static final Entry[] EMPTY_TABLE;
    private static final int MAXIMUM_CAPACITY = 1073741824;
    private static final int MINIMUM_CAPACITY = 4;
    private static final int seed;
    private Set<Entry<String, V>> entrySet;
    private LinkedEntry<V> header;
    private Set<String> keySet;
    private int size;
    private LinkedEntry<V>[] table;
    private int threshold;
    private Collection<V> values;
    
    static {
        EMPTY_TABLE = new LinkedEntry[2];
        seed = new Random().nextInt();
    }
    
    public StringMap() {
        this.table = (LinkedEntry<V>[])StringMap.EMPTY_TABLE;
        this.threshold = -1;
        this.header = new LinkedEntry<V>();
    }
    
    private void addNewEntry(final String s, final V v, final int n, final int n2) {
        final LinkedEntry<V> header = this.header;
        final LinkedEntry<V> prv = header.prv;
        final LinkedEntry linkedEntry = new LinkedEntry<Object>(s, v, n, (LinkedEntry<Object>)this.table[n2], (LinkedEntry<Object>)header, (LinkedEntry<Object>)prv);
        final LinkedEntry<V>[] table = this.table;
        header.prv = (LinkedEntry<V>)linkedEntry;
        table[n2] = (prv.nxt = (LinkedEntry<V>)linkedEntry);
    }
    
    private LinkedEntry<V>[] doubleCapacity() {
        final LinkedEntry<V>[] table = this.table;
        final int length = table.length;
        if (length == 1073741824) {
            return table;
        }
        final LinkedEntry<V>[] table2 = this.makeTable(length * 2);
        if (this.size == 0) {
            return table2;
        }
        for (int i = 0; i < length; ++i) {
            LinkedEntry<V> linkedEntry = table[i];
            if (linkedEntry != null) {
                int n = linkedEntry.hash & length;
                table2[i | n] = linkedEntry;
                LinkedEntry<V> next = linkedEntry.next;
                LinkedEntry<V> linkedEntry2 = null;
                while (next != null) {
                    final int n2 = next.hash & length;
                    if (n2 != n) {
                        if (linkedEntry2 == null) {
                            table2[i | n2] = next;
                        }
                        else {
                            linkedEntry2.next = next;
                        }
                        n = n2;
                    }
                    else {
                        linkedEntry = linkedEntry2;
                    }
                    final LinkedEntry<V> next2 = next.next;
                    linkedEntry2 = linkedEntry;
                    linkedEntry = next;
                    next = next2;
                }
                if (linkedEntry2 != null) {
                    linkedEntry2.next = null;
                }
            }
        }
        return table2;
    }
    
    private LinkedEntry<V> getEntry(final String s) {
        if (s != null) {
            final int hash = hash(s);
            final LinkedEntry<V>[] table = this.table;
            for (LinkedEntry<V> next = table[table.length - 1 & hash]; next != null; next = next.next) {
                final String key = next.key;
                if (key == s || (next.hash == hash && s.equals(key))) {
                    return next;
                }
            }
        }
        return null;
    }
    
    private static int hash(final String s) {
        int seed = StringMap.seed;
        for (int i = 0; i < s.length(); ++i) {
            final char c = (char)(seed + s.charAt(i));
            final char c2 = (char)(c + c << 10);
            seed = (c2 ^ c2 >>> 6);
        }
        final char c3 = (char)(seed >>> 20 ^ seed >>> 12 ^ seed);
        return c3 >>> 4 ^ (c3 >>> 7 ^ c3);
    }
    
    private LinkedEntry<V>[] makeTable(final int n) {
        final LinkedEntry[] table = new LinkedEntry[n];
        this.table = (LinkedEntry<V>[])table;
        this.threshold = (n >> 1) + (n >> 2);
        return (LinkedEntry<V>[])table;
    }
    
    private boolean removeMapping(final Object o, final Object o2) {
        if (o == null || !(o instanceof String)) {
            return false;
        }
        final int hash = hash((String)o);
        final LinkedEntry<V>[] table = this.table;
        final int n = hash & table.length - 1;
        LinkedEntry<V> linkedEntry = table[n];
        LinkedEntry<V> linkedEntry2 = null;
        while (linkedEntry != null) {
            if (linkedEntry.hash == hash && o.equals(linkedEntry.key)) {
                Label_0098: {
                    if (o2 == null) {
                        if (linkedEntry.value == null) {
                            break Label_0098;
                        }
                    }
                    else if (o2.equals(linkedEntry.value)) {
                        break Label_0098;
                    }
                    return false;
                }
                if (linkedEntry2 == null) {
                    table[n] = linkedEntry.next;
                }
                else {
                    linkedEntry2.next = linkedEntry.next;
                }
                --this.size;
                this.unlink(linkedEntry);
                return true;
            }
            final LinkedEntry<V> next = linkedEntry.next;
            linkedEntry2 = linkedEntry;
            linkedEntry = next;
        }
        return false;
    }
    
    private void unlink(final LinkedEntry<V> linkedEntry) {
        linkedEntry.prv.nxt = linkedEntry.nxt;
        linkedEntry.nxt.prv = linkedEntry.prv;
        linkedEntry.prv = null;
        linkedEntry.nxt = null;
    }
    
    @Override
    public void clear() {
        if (this.size != 0) {
            Arrays.fill(this.table, null);
            this.size = 0;
        }
        final LinkedEntry<V> header = this.header;
        LinkedEntry<V> nxt2;
        for (LinkedEntry<V> nxt = header.nxt; nxt != header; nxt = nxt2) {
            nxt2 = nxt.nxt;
            nxt.prv = null;
            nxt.nxt = null;
        }
        header.prv = header;
        header.nxt = header;
    }
    
    @Override
    public boolean containsKey(final Object o) {
        return o instanceof String && this.getEntry((String)o) != null;
    }
    
    @Override
    public Set<Entry<String, V>> entrySet() {
        final Set<Entry<String, V>> entrySet = this.entrySet;
        if (entrySet != null) {
            return entrySet;
        }
        return this.entrySet = new EntrySet();
    }
    
    @Override
    public V get(final Object o) {
        Object value;
        final Object o2 = value = null;
        if (o instanceof String) {
            final LinkedEntry<V> entry = this.getEntry((String)o);
            value = o2;
            if (entry != null) {
                value = entry.value;
            }
        }
        return (V)value;
    }
    
    @Override
    public Set<String> keySet() {
        final Set<String> keySet = this.keySet;
        if (keySet != null) {
            return keySet;
        }
        return this.keySet = new KeySet();
    }
    
    @Override
    public V put(final String s, final V value) {
        if (s == null) {
            throw new NullPointerException("key == null");
        }
        final int hash = hash(s);
        final LinkedEntry<V>[] table = this.table;
        int n = table.length - 1 & hash;
        for (LinkedEntry<V> next = table[n]; next != null; next = next.next) {
            if (next.hash == hash && s.equals(next.key)) {
                final V value2 = next.value;
                next.value = value;
                return value2;
            }
        }
        if (this.size++ > this.threshold) {
            n = (this.doubleCapacity().length - 1 & hash);
        }
        this.addNewEntry(s, value, hash, n);
        return null;
    }
    
    @Override
    public V remove(final Object o) {
        if (o == null || !(o instanceof String)) {
            return null;
        }
        final int hash = hash((String)o);
        final LinkedEntry<V>[] table = this.table;
        final int n = hash & table.length - 1;
        LinkedEntry<V> linkedEntry = table[n];
        LinkedEntry<V> linkedEntry2 = null;
        while (linkedEntry != null) {
            if (linkedEntry.hash == hash && o.equals(linkedEntry.key)) {
                if (linkedEntry2 == null) {
                    table[n] = linkedEntry.next;
                }
                else {
                    linkedEntry2.next = linkedEntry.next;
                }
                --this.size;
                this.unlink(linkedEntry);
                return linkedEntry.value;
            }
            final LinkedEntry<V> next = linkedEntry.next;
            linkedEntry2 = linkedEntry;
            linkedEntry = next;
        }
        return null;
    }
    
    @Override
    public int size() {
        return this.size;
    }
    
    @Override
    public Collection<V> values() {
        final Collection<V> values = this.values;
        if (values != null) {
            return values;
        }
        return this.values = new Values();
    }
    
    private final class EntrySet extends AbstractSet<Entry<String, V>>
    {
        @Override
        public void clear() {
            StringMap.this.clear();
        }
        
        @Override
        public boolean contains(final Object o) {
            if (o instanceof Entry) {
                final Entry entry = (Entry)o;
                final V value = StringMap.this.get(entry.getKey());
                if (value != null && value.equals(entry.getValue())) {
                    return true;
                }
            }
            return false;
        }
        
        @Override
        public Iterator<Entry<String, V>> iterator() {
            return new LinkedHashIterator<Entry<String, V>>() {
                @Override
                public final Entry<String, V> next() {
                    return ((LinkedHashIterator)this).nextEntry();
                }
            };
        }
        
        @Override
        public boolean remove(final Object o) {
            if (!(o instanceof Entry)) {
                return false;
            }
            final Entry entry = (Entry)o;
            return StringMap.this.removeMapping(entry.getKey(), entry.getValue());
        }
        
        @Override
        public int size() {
            return StringMap.this.size;
        }
    }
    
    private final class KeySet extends AbstractSet<String>
    {
        @Override
        public void clear() {
            StringMap.this.clear();
        }
        
        @Override
        public boolean contains(final Object o) {
            return StringMap.this.containsKey(o);
        }
        
        @Override
        public Iterator<String> iterator() {
            return new LinkedHashIterator<String>() {
                @Override
                public final String next() {
                    return ((LinkedHashIterator)this).nextEntry().key;
                }
            };
        }
        
        @Override
        public boolean remove(final Object o) {
            final int access$500 = StringMap.this.size;
            StringMap.this.remove(o);
            return StringMap.this.size != access$500;
        }
        
        @Override
        public int size() {
            return StringMap.this.size;
        }
    }
    
    static class LinkedEntry<V> implements Entry<String, V>
    {
        final int hash;
        final String key;
        LinkedEntry<V> next;
        LinkedEntry<V> nxt;
        LinkedEntry<V> prv;
        V value;
        
        LinkedEntry() {
            this(null, null, 0, null, null, null);
            this.prv = this;
            this.nxt = this;
        }
        
        LinkedEntry(final String key, final V value, final int hash, final LinkedEntry<V> next, final LinkedEntry<V> nxt, final LinkedEntry<V> prv) {
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
    
    private abstract class LinkedHashIterator<T> implements Iterator<T>
    {
        LinkedEntry<V> lastReturned;
        LinkedEntry<V> next;
        
        private LinkedHashIterator() {
            this.next = StringMap.this.header.nxt;
            this.lastReturned = null;
        }
        
        @Override
        public final boolean hasNext() {
            return this.next != StringMap.this.header;
        }
        
        final LinkedEntry<V> nextEntry() {
            final LinkedEntry<V> next = this.next;
            if (next == StringMap.this.header) {
                throw new NoSuchElementException();
            }
            this.next = next.nxt;
            return this.lastReturned = next;
        }
        
        @Override
        public final void remove() {
            if (this.lastReturned == null) {
                throw new IllegalStateException();
            }
            StringMap.this.remove(this.lastReturned.key);
            this.lastReturned = null;
        }
    }
    
    private final class Values extends AbstractCollection<V>
    {
        @Override
        public void clear() {
            StringMap.this.clear();
        }
        
        @Override
        public boolean contains(final Object o) {
            return StringMap.this.containsValue(o);
        }
        
        @Override
        public Iterator<V> iterator() {
            return new LinkedHashIterator<V>() {
                @Override
                public final V next() {
                    return ((LinkedHashIterator)this).nextEntry().value;
                }
            };
        }
        
        @Override
        public int size() {
            return StringMap.this.size;
        }
    }
}
