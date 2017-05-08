// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

import java.util.Collection;
import java.util.Set;
import io.realm.internal.Table;
import java.util.Map;

final class RealmObjectSchema$DynamicColumnMap implements Map<String, Long>
{
    private final Table table;
    
    public RealmObjectSchema$DynamicColumnMap(final Table table) {
        this.table = table;
    }
    
    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean containsKey(final Object o) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean containsValue(final Object o) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Set<Entry<String, Long>> entrySet() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Long get(final Object o) {
        final long columnIndex = this.table.getColumnIndex((String)o);
        if (columnIndex < 0L) {
            return null;
        }
        return columnIndex;
    }
    
    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Set<String> keySet() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Long put(final String s, final Long n) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void putAll(final Map<? extends String, ? extends Long> map) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Long remove(final Object o) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public int size() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Collection<Long> values() {
        throw new UnsupportedOperationException();
    }
}
