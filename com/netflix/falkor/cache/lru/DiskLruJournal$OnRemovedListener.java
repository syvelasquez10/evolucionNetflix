// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.cache.lru;

public interface DiskLruJournal$OnRemovedListener
{
    void onRemoved(final String p0, final DiskLruJournal$Snapshot p1);
}
