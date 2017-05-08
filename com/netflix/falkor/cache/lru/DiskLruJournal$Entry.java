// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.cache.lru;

final class DiskLruJournal$Entry
{
    private DiskLruJournal$Editor currentEditor;
    private final String key;
    private boolean readable;
    private long sequenceNumber;
    final /* synthetic */ DiskLruJournal this$0;
    
    private DiskLruJournal$Entry(final DiskLruJournal this$0, final String key) {
        this.this$0 = this$0;
        this.key = key;
    }
}
