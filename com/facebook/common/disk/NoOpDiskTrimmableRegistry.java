// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.disk;

public class NoOpDiskTrimmableRegistry implements DiskTrimmableRegistry
{
    private static NoOpDiskTrimmableRegistry sInstance;
    
    static {
        NoOpDiskTrimmableRegistry.sInstance = null;
    }
    
    public static NoOpDiskTrimmableRegistry getInstance() {
        synchronized (NoOpDiskTrimmableRegistry.class) {
            if (NoOpDiskTrimmableRegistry.sInstance == null) {
                NoOpDiskTrimmableRegistry.sInstance = new NoOpDiskTrimmableRegistry();
            }
            return NoOpDiskTrimmableRegistry.sInstance;
        }
    }
    
    @Override
    public void registerDiskTrimmable(final DiskTrimmable diskTrimmable) {
    }
}
