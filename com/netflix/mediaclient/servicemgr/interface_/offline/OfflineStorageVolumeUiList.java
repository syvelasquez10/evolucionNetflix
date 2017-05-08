// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.offline;

import com.netflix.mediaclient.util.ReadOnlyList;

public interface OfflineStorageVolumeUiList extends ReadOnlyList<OfflineStorageVolume>
{
    int getCurrentSelectedVolumeIndex();
    
    boolean isStorageRemovable(final int p0);
}
