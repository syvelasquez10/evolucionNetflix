// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.registry;

import java.io.File;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineStorageVolume;

class OfflineStorageVolumeImpl implements OfflineStorageVolume
{
    private final MetaRegistry mMetaRegistry;
    final OfflineStorageVolumeInfo mOfflineStorageVolumeInfo;
    final RegistryData mRegistryData;
    
    public OfflineStorageVolumeImpl(final MetaRegistry mMetaRegistry, final RegistryData mRegistryData, final OfflineStorageVolumeInfo mOfflineStorageVolumeInfo) {
        this.mRegistryData = mRegistryData;
        this.mOfflineStorageVolumeInfo = mOfflineStorageVolumeInfo;
        this.mMetaRegistry = mMetaRegistry;
    }
    
    File getDownloadDir() {
        return this.mOfflineStorageVolumeInfo.getDownloadDir();
    }
    
    @Override
    public long getFreeSpace() {
        return this.mOfflineStorageVolumeInfo.getFreeSpace();
    }
    
    @Override
    public long getNetflixUsedSpace() {
        return this.mOfflineStorageVolumeInfo.getNetflixUsedSpace();
    }
    
    @Override
    public long getTotalSpace() {
        return this.mOfflineStorageVolumeInfo.getTotalSpace();
    }
    
    int getVolumeRegId() {
        return this.mRegistryData.mRegId;
    }
    
    @Override
    public boolean isCurrentlySelected() {
        return this.mMetaRegistry.isCurrentlySelected(this.mRegistryData);
    }
    
    @Override
    public boolean isRemovable() {
        return this.mOfflineStorageVolumeInfo.isRemovable();
    }
}
