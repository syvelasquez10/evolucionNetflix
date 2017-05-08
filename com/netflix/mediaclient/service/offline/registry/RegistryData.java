// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.registry;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;
import com.netflix.mediaclient.service.offline.download.OfflinePlayablePersistentData;
import java.util.List;

public class RegistryData
{
    @SerializedName("deletedPlayableList")
    public List<OfflinePlayablePersistentData> mDeletedPlayableList;
    @SerializedName("writeCounter")
    public int mMetaRegistryWriteCounter;
    @SerializedName("offlinePlayableList")
    public List<OfflinePlayablePersistentData> mOfflinePlayablePersistentDataList;
    public transient String mOfflineRootStorageDirPath;
    @SerializedName("regId")
    public final int mRegId;
    
    public RegistryData(final int mRegId, final String mOfflineRootStorageDirPath) {
        this.mDeletedPlayableList = new ArrayList<OfflinePlayablePersistentData>();
        this.mOfflinePlayablePersistentDataList = new ArrayList<OfflinePlayablePersistentData>();
        this.mRegId = mRegId;
        this.mOfflineRootStorageDirPath = mOfflineRootStorageDirPath;
    }
}
