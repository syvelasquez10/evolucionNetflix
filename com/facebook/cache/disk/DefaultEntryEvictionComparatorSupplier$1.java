// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.cache.disk;

class DefaultEntryEvictionComparatorSupplier$1 implements EntryEvictionComparator
{
    final /* synthetic */ DefaultEntryEvictionComparatorSupplier this$0;
    
    DefaultEntryEvictionComparatorSupplier$1(final DefaultEntryEvictionComparatorSupplier this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public int compare(final DiskStorage$Entry diskStorage$Entry, final DiskStorage$Entry diskStorage$Entry2) {
        final long timestamp = diskStorage$Entry.getTimestamp();
        final long timestamp2 = diskStorage$Entry2.getTimestamp();
        if (timestamp < timestamp2) {
            return -1;
        }
        if (timestamp2 == timestamp) {
            return 0;
        }
        return 1;
    }
}
