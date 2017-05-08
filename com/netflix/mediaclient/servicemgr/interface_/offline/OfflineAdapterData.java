// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.offline;

import java.util.Collection;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmPlayable;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.Log;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmProfile;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import java.util.Map;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmVideoDetails;

public class OfflineAdapterData
{
    private static final String TAG = "OfflineAdapterData";
    private final RealmVideoDetails[] episodes;
    private final OfflineAdapterData$VideoAndProfileData videoAndProfileData;
    
    public OfflineAdapterData(final RealmVideoDetails realmVideoDetails, final List<RealmVideoDetails> list, final Map<String, OfflinePlayableViewData> map) {
        if (realmVideoDetails.getType() == VideoType.MOVIE) {
            this.videoAndProfileData = new OfflineAdapterData$VideoAndProfileData(OfflineAdapterData$ViewType.MOVIE, null, realmVideoDetails, 1);
            this.episodes = null;
            return;
        }
        final ArrayList<RealmVideoDetails> list2 = new ArrayList<RealmVideoDetails>();
        int i = 0;
        int n = 0;
        List<RealmVideoDetails> list3 = null;
        while (i < list.size()) {
            final RealmVideoDetails realmVideoDetails2 = list.get(i);
            final int seasonNumber = realmVideoDetails2.getPlayable().getSeasonNumber();
            if (map.get(realmVideoDetails2.getId()) != null) {
                if (list3 == null) {
                    list3 = new ArrayList<RealmVideoDetails>();
                }
                list3.add(realmVideoDetails2);
                ++n;
            }
            else if (Log.isLoggable()) {
                Log.i("OfflineAdapterData", "Skipping the episode since its ID was not found inside our core data: " + realmVideoDetails2.getId());
            }
            List<RealmVideoDetails> list4 = null;
            Label_0270: {
                if (i != list.size() - 1) {
                    list4 = list3;
                    if (seasonNumber == list.get(i + 1).getPlayable().getSeasonNumber()) {
                        break Label_0270;
                    }
                }
                if ((list4 = list3) != null) {
                    final RealmVideoDetails realmVideoDetails3 = list3.get(0);
                    final RealmVideoDetails realmVideoDetails4 = new RealmVideoDetails();
                    realmVideoDetails4.fillForRealm(realmVideoDetails3);
                    realmVideoDetails4.setPlayableAndVideoType((RealmPlayable)realmVideoDetails3.getPlayable(), VideoType.SEASON, realmVideoDetails.getSeasonLongLabel(seasonNumber));
                    list2.add(realmVideoDetails4);
                    list2.addAll((Collection<?>)list3);
                    list4 = null;
                }
            }
            ++i;
            list3 = list4;
        }
        this.episodes = list2.toArray(new RealmVideoDetails[list2.size()]);
        this.videoAndProfileData = new OfflineAdapterData$VideoAndProfileData(OfflineAdapterData$ViewType.SHOW, null, realmVideoDetails, n);
    }
    
    private VideoDetails getEpisodeDetailsForVideoId(final String s) {
        final RealmVideoDetails[] episodes = this.episodes;
        for (int length = episodes.length, i = 0; i < length; ++i) {
            final RealmVideoDetails realmVideoDetails = episodes[i];
            if (s.equalsIgnoreCase(realmVideoDetails.getId())) {
                return realmVideoDetails;
            }
        }
        return null;
    }
    
    public boolean containsPlayable(final String s) {
        switch (OfflineAdapterData$1.$SwitchMap$com$netflix$mediaclient$servicemgr$interface_$offline$OfflineAdapterData$ViewType[this.videoAndProfileData.type.ordinal()]) {
            case 1: {
                return this.videoAndProfileData.video.getId().equalsIgnoreCase(s);
            }
            case 2: {
                if (this.getEpisodeDetailsForVideoId(s) != null) {
                    return true;
                }
                break;
            }
        }
        return false;
    }
    
    protected long getCurrentSpace(final Map<String, OfflinePlayableViewData> map) {
        long n = 0L;
        long n2 = 0L;
        Label_0044: {
            switch (OfflineAdapterData$1.$SwitchMap$com$netflix$mediaclient$servicemgr$interface_$offline$OfflineAdapterData$ViewType[this.videoAndProfileData.type.ordinal()]) {
                default: {
                    n2 = n;
                    break;
                }
                case 1: {
                    return map.get(this.videoAndProfileData.video.getId()).getTotalEstimatedSpace();
                }
                case 2: {
                    final RealmVideoDetails[] episodes = this.episodes;
                    final int length = episodes.length;
                    int n3 = 0;
                    while (true) {
                        n2 = n;
                        if (n3 >= length) {
                            break Label_0044;
                        }
                        final RealmVideoDetails realmVideoDetails = episodes[n3];
                        if (realmVideoDetails.getType() == VideoType.EPISODE) {
                            n += map.get(realmVideoDetails.getId()).getTotalEstimatedSpace();
                        }
                        ++n3;
                    }
                    break;
                }
            }
        }
        return n2;
    }
    
    public RealmVideoDetails[] getEpisodes() {
        return this.episodes;
    }
    
    protected int getPercentage(final Map<String, OfflinePlayableViewData> map) {
        switch (OfflineAdapterData$1.$SwitchMap$com$netflix$mediaclient$servicemgr$interface_$offline$OfflineAdapterData$ViewType[this.videoAndProfileData.type.ordinal()]) {
            default: {
                return 0;
            }
            case 1: {
                return map.get(this.videoAndProfileData.video.getId()).getPercentageDownloaded();
            }
            case 2: {
                final RealmVideoDetails[] episodes = this.episodes;
                final int length = episodes.length;
                int i = 0;
                int n = 0;
                int n2 = 0;
                while (i < length) {
                    final RealmVideoDetails realmVideoDetails = episodes[i];
                    int n4;
                    int n5;
                    if (realmVideoDetails.getType() == VideoType.EPISODE) {
                        final int percentageDownloaded = map.get(realmVideoDetails.getId()).getPercentageDownloaded();
                        final int n3 = n + 1;
                        n4 = n2 + percentageDownloaded;
                        n5 = n3;
                    }
                    else {
                        final int n6 = n2;
                        n5 = n;
                        n4 = n6;
                    }
                    final int n7 = i + 1;
                    final int n8 = n4;
                    n = n5;
                    n2 = n8;
                    i = n7;
                }
                return n2 / n;
            }
        }
    }
    
    public OfflineAdapterData$VideoAndProfileData getVideoAndProfileData() {
        return this.videoAndProfileData;
    }
}
