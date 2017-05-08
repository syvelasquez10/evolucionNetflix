// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.memory;

public class NoOpMemoryTrimmableRegistry implements MemoryTrimmableRegistry
{
    private static NoOpMemoryTrimmableRegistry sInstance;
    
    static {
        NoOpMemoryTrimmableRegistry.sInstance = null;
    }
    
    public static NoOpMemoryTrimmableRegistry getInstance() {
        synchronized (NoOpMemoryTrimmableRegistry.class) {
            if (NoOpMemoryTrimmableRegistry.sInstance == null) {
                NoOpMemoryTrimmableRegistry.sInstance = new NoOpMemoryTrimmableRegistry();
            }
            return NoOpMemoryTrimmableRegistry.sInstance;
        }
    }
    
    @Override
    public void registerMemoryTrimmable(final MemoryTrimmable memoryTrimmable) {
    }
}
