// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.cache.disk;

public class DefaultEntryEvictionComparatorSupplier implements EntryEvictionComparatorSupplier
{
    @Override
    public EntryEvictionComparator get() {
        return new DefaultEntryEvictionComparatorSupplier$1(this);
    }
}
