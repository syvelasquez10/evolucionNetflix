// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.servicemgr.interface_.PlaybackBookmark;
import com.netflix.mediaclient.media.BookmarkStore;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.offline.registry.OfflineRegistry;
import com.netflix.mediaclient.service.user.UserAgent;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;
import java.util.ArrayList;
import com.netflix.mediaclient.util.AndroidUtils;
import java.io.File;
import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadState;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.offline.download.OfflinePlayable;
import java.util.List;
import java.util.Map;

class OfflineAgentHelper
{
    private static final long DISK_FREE_SPACE_SAFETY_MARGIN = 50000000L;
    private static final String TAG = "nf_offlineAgent";
    
    static void applyGeoPlayabilityFlags(final Map<String, Boolean> map, final List<OfflinePlayable> list) {
        if (map != null && map.size() > 0) {
            for (final OfflinePlayable offlinePlayable : list) {
                final Boolean b = map.get(offlinePlayable.getPlayableId());
                if (b != null) {
                    if (Log.isLoggable()) {
                        Log.i("nf_offlineAgent", "handleGeoPlayabilityUpdated playableId=" + offlinePlayable.getPlayableId() + " geoWatchable=" + b);
                    }
                    offlinePlayable.getOfflineViewablePersistentData().setGeoBlocked(!b);
                }
            }
        }
    }
    
    static boolean ensureEnoughDiskSpaceForNewRequest(final String s, final List<OfflinePlayable> list) {
        final Iterator<OfflinePlayable> iterator = list.iterator();
        long n = 50000000L;
        while (iterator.hasNext()) {
            final OfflinePlayable offlinePlayable = iterator.next();
            if (offlinePlayable.getDownloadState() != DownloadState.Complete) {
                n += offlinePlayable.getTotalEstimatedSpace() - offlinePlayable.getCurrentEstimatedSpace();
            }
        }
        final long freeSpaceOnFileSystem = AndroidUtils.getFreeSpaceOnFileSystem(new File(s));
        if (n > freeSpaceOnFileSystem) {
            Log.e("nf_offlineAgent", "ensureEnoughDiskSpaceForNewRequest freeSpaceNeeded=" + n + " freeSpaceOnFileSystem=" + freeSpaceOnFileSystem);
            return false;
        }
        return true;
    }
    
    static OfflinePlayable findNextCreatingStatePlayable(final List<OfflinePlayable> list) {
        for (final OfflinePlayable offlinePlayable : list) {
            if (offlinePlayable.getDownloadState() == DownloadState.Creating) {
                return offlinePlayable;
            }
        }
        return null;
    }
    
    static List<String> getCompletedVideoIds(final List<OfflinePlayable> list) {
        final ArrayList<String> list2 = new ArrayList<String>();
        for (final OfflinePlayable offlinePlayable : list) {
            if (offlinePlayable.getDownloadState() == DownloadState.Complete) {
                list2.add(offlinePlayable.getPlayableId());
            }
        }
        return list2;
    }
    
    static long getLastMaintenanceJobStartTime(final Context context) {
        return PreferenceUtils.getLongPref(context, "pref_offline_maintenance_job_start_time", -1L);
    }
    
    static OfflinePlayable getOfflineViewableByPlayableId(final String s, final List<OfflinePlayable> list) {
        if (s == null) {
            return null;
        }
        for (final OfflinePlayable offlinePlayable : list) {
            if (s.equals(offlinePlayable.getPlayableId())) {
                return offlinePlayable;
            }
        }
        return null;
    }
    
    static boolean hasPrimaryProfileGuidChanged(final UserAgent userAgent, final OfflineRegistry offlineRegistry) {
        final String primaryProfileGuid = userAgent.getPrimaryProfileGuid();
        final String primaryProfileGuid2 = offlineRegistry.getPrimaryProfileGuid();
        if (Log.isLoggable()) {
            Log.i("nf_offlineAgent", "newPrimaryProfileGuid=" + primaryProfileGuid + " primaryProfileGuidInRegistry=" + primaryProfileGuid2);
        }
        if (StringUtils.isNotEmpty(primaryProfileGuid) && StringUtils.isNotEmpty(primaryProfileGuid2)) {
            if (!primaryProfileGuid.equals(primaryProfileGuid2)) {
                Log.e("nf_offlineAgent", "primaryProfileGuid don't match... going to delete all content");
                return true;
            }
            if (Log.isLoggable()) {
                Log.i("nf_offlineAgent", "primaryProfileGuid match");
            }
        }
        return false;
    }
    
    static void setLastMaintenanceJobStartTime(final Context context, final long n) {
        PreferenceUtils.putLongPref(context, "pref_offline_maintenance_job_start_time", n);
    }
    
    static void updateBookmarkIfNewer(final String s, final VideoDetails videoDetails, final String s2) {
        int n = 1;
        if (videoDetails != null) {
            final Playable playable = videoDetails.getPlayable();
            if (playable != null) {
                final int playableBookmarkPosition = playable.getPlayableBookmarkPosition();
                final long playableBookmarkUpdateTime = playable.getPlayableBookmarkUpdateTime();
                final PlaybackBookmark bookmark = BookmarkStore.getInstance().getBookmark(s2, s);
                if (bookmark == null) {
                    Log.i("nf_offlineAgent", "updateBookmarkIfNewer bookmarkStore has no bookmark");
                }
                else if (bookmark.mBookmarkUpdateTimeInUTCMs < playableBookmarkUpdateTime) {
                    Log.i("nf_offlineAgent", "updateBookmarkIfNewer bookmarkStore is older");
                }
                else {
                    Log.i("nf_offlineAgent", "updateBookmarkIfNewer bookmarkStore is newer");
                    n = 0;
                }
                if (n != 0) {
                    Log.i("nf_offlineAgent", "updateBookmarkIfNewer calling BookmarkStore.setBookmark");
                    BookmarkStore.getInstance().setBookmark(s2, new PlaybackBookmark(playableBookmarkPosition, playableBookmarkUpdateTime, s2));
                }
            }
        }
    }
}
