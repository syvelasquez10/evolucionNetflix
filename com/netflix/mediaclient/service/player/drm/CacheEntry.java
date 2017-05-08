// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.drm;

public class CacheEntry implements Comparable<CacheEntry>
{
    private long age;
    private Long id;
    private boolean inUse;
    private int priority;
    
    public CacheEntry(final Long id, final int priority, final long age, final boolean inUse) {
        this.id = id;
        this.priority = priority;
        this.age = age;
        this.inUse = inUse;
    }
    
    @Override
    public int compareTo(final CacheEntry cacheEntry) {
        if (!this.inUse || cacheEntry.inUse) {
            if (!this.inUse && cacheEntry.inUse) {
                return -1;
            }
            if (this.getPriority() > cacheEntry.getPriority()) {
                return -1;
            }
            if (this.getPriority() >= cacheEntry.getPriority()) {
                if (this.getAge() > cacheEntry.getAge()) {
                    return -1;
                }
                if (this.getAge() >= cacheEntry.getAge()) {
                    return 0;
                }
            }
        }
        return 1;
    }
    
    public long getAge() {
        return this.age;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public int getPriority() {
        return this.priority;
    }
}
