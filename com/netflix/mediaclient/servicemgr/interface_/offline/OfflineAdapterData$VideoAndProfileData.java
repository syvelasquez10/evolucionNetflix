// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.offline;

import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmVideoDetails;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmProfile;

public class OfflineAdapterData$VideoAndProfileData
{
    public final int numEpisodes;
    public final RealmProfile profile;
    public final OfflineAdapterData$ViewType type;
    public final RealmVideoDetails video;
    
    public OfflineAdapterData$VideoAndProfileData(final OfflineAdapterData$ViewType type, final RealmProfile profile, final RealmVideoDetails video, final int numEpisodes) {
        this.type = type;
        this.profile = profile;
        this.video = video;
        this.numEpisodes = numEpisodes;
    }
}
