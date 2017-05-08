// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.offline;

import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmVideoDetails;
import java.util.Comparator;

class OfflinePlayableUiListImpl$1 implements Comparator<RealmVideoDetails>
{
    final /* synthetic */ OfflinePlayableUiListImpl this$0;
    
    OfflinePlayableUiListImpl$1(final OfflinePlayableUiListImpl this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public int compare(final RealmVideoDetails realmVideoDetails, final RealmVideoDetails realmVideoDetails2) {
        if (realmVideoDetails.getPlayable().getSeasonNumber() != realmVideoDetails2.getPlayable().getSeasonNumber()) {
            return realmVideoDetails.getPlayable().getSeasonNumber() - realmVideoDetails2.getPlayable().getSeasonNumber();
        }
        return realmVideoDetails.getPlayable().getEpisodeNumber() - realmVideoDetails2.getPlayable().getEpisodeNumber();
    }
}
