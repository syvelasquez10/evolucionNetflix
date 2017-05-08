// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.offline;

public interface OfflineStorageVolume
{
    long getFreeSpace();
    
    long getNetflixUsedSpace();
    
    long getTotalSpace();
    
    boolean isCurrentlySelected();
    
    boolean isRemovable();
}
