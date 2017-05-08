// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import com.netflix.mediaclient.servicemgr.interface_.PlaybackBookmark;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmVideoDetails;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import com.netflix.mediaclient.util.TimeUtils;
import com.netflix.mediaclient.media.BookmarkStore;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmUtils;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadState;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;

public class OfflineUiHelper
{
    private static final String TAG = "offlineUiHelper";
    
    public static int getSnackBarDownloadCompleteCount(final Context context) {
        return PreferenceUtils.getIntPref(context, "prefs_offline_snackbar_dl_complete_count", 0);
    }
    
    public static void incrementSnackBarDownloadCompleteCount(final Context context) {
        final int n = getSnackBarDownloadCompleteCount(context) + 1;
        Log.i("offlineUiHelper", "incrementSnackBarDownloadCompleteCount count=%d", n);
        PreferenceUtils.putIntPref(context, "prefs_offline_snackbar_dl_complete_count", n);
    }
    
    public static boolean isFullyDownloadedAndNotWatchable(final OfflinePlayableViewData offlinePlayableViewData) {
        return offlinePlayableViewData.getDownloadState() == DownloadState.Complete && offlinePlayableViewData.getWatchState().hasError();
    }
    
    public static boolean isFullyDownloadedAndWatchable(final OfflinePlayableViewData offlinePlayableViewData) {
        return offlinePlayableViewData.getDownloadState() == DownloadState.Complete && !offlinePlayableViewData.getWatchState().hasError();
    }
    
    public static boolean isUserSwiped(final Context context) {
        return PreferenceUtils.getBooleanPref(context, "prefs_offline_snackbar_user_swiped", false);
    }
    
    public static void resetSnackBarDownloadCompleteCount(final Context context) {
        Log.i("offlineUiHelper", "resetSnackBarDownloadCompleteCount count=0");
        PreferenceUtils.putIntPref(context, "prefs_offline_snackbar_dl_complete_count", 0);
    }
    
    public static void setUserSwiped(final Context context, final boolean b) {
        PreferenceUtils.putBooleanPref(context, "prefs_offline_snackbar_user_swiped", b);
    }
    
    public static void showAvailableDownloadsGenreList(final NetflixActivity netflixActivity) {
        if (netflixActivity == null || netflixActivity.getServiceManager() == null) {
            return;
        }
        netflixActivity.getServiceManager().getBrowse().fetchGenreLists(new OfflineUiHelper$1("offlineUiHelper", netflixActivity));
    }
    
    public static void startOfflinePlayback(final Context context, final String s, final VideoType videoType, final PlayContext playContext) {
        if (context == null) {
            return;
        }
        final NetflixActivity netflixActivity = AndroidUtils.getContextAs(context, NetflixActivity.class);
        if (netflixActivity == null) {
            Log.i("offlineUiHelper", "netflixActivity is null");
            return;
        }
        final ServiceManager serviceManager = netflixActivity.getServiceManager();
        if (serviceManager == null) {
            Log.i("offlineUiHelper", "serviceManager is null");
            return;
        }
        final OfflineAgentInterface offlineAgentOrNull = NetflixActivity.getOfflineAgentOrNull(netflixActivity);
        if (offlineAgentOrNull == null) {
            Log.i("offlineUiHelper", "offlineAgentInterface is null");
            return;
        }
        final RealmVideoDetails offlineVideoDetails = RealmUtils.getOfflineVideoDetails(s);
        if (offlineVideoDetails == null) {
            Log.i("offlineUiHelper", "videoDetails is null");
            return;
        }
        final Playable playable = offlineVideoDetails.getPlayable();
        if (playable == null) {
            Log.i("offlineUiHelper", "playable is null");
            return;
        }
        if (videoType == null) {
            Log.i("offlineUiHelper", "type is null");
            return;
        }
        final OfflinePlayableViewData offlinePlayableViewData = offlineAgentOrNull.getLatestOfflinePlayableList().getOfflinePlayableViewData(s);
        if (offlinePlayableViewData == null) {
            Log.i("offlineUiHelper", "offlinePlayableViewData is null");
            return;
        }
        if (offlinePlayableViewData.getDownloadState() != DownloadState.Complete) {
            Log.i("offlineUiHelper", "download is not complete yet");
            return;
        }
        final Asset create = Asset.create(offlineVideoDetails.getPlayable(), playContext, false);
        int n = playable.getPlayableBookmarkPosition();
        final PlaybackBookmark bookmark = BookmarkStore.getInstance().getBookmark(serviceManager.getCurrentProfileGuidOrNull(), s);
        if (bookmark != null) {
            n = bookmark.mBookmarkInSecond;
        }
        final int computePlayPos = TimeUtils.computePlayPos(n, playable.getEndtime(), playable.getRuntime());
        create.setPlaybackBookmark(computePlayPos);
        PlaybackLauncher.startPlaybackAfterPIN(netflixActivity, create, computePlayPos);
    }
}
