// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.offline;

import android.text.Spannable;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import java.util.Comparator;
import java.util.Collections;
import java.util.HashSet;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmProfile;
import io.realm.Realm;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import android.text.TextUtils;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.offline.ActivityPageOfflineAgentListener$SnackbarMessage;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import java.util.Collection;
import android.text.style.ForegroundColorSpan;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import java.util.concurrent.TimeUnit;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmUtils;
import com.netflix.mediaclient.util.StringUtils;
import java.util.Iterator;
import com.netflix.mediaclient.ui.offline.OfflineUiHelper;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmVideoDetails;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class OfflinePlayableUiListImpl implements OfflinePlayableUiList
{
    private static final String TAG = "OfflinePlayableUiListImpl";
    private List<OfflineAdapterData> mOfflinePlayableUiItemList;
    private final Map<String, String> mPlayableToTitleMap;
    private Map<String, OfflinePlayableViewData> mVideoIdToOfflineData;
    
    public OfflinePlayableUiListImpl() {
        this.mPlayableToTitleMap = new HashMap<String, String>();
        this.mOfflinePlayableUiItemList = new ArrayList<OfflineAdapterData>();
    }
    
    private String buildShowCompositeStatus(final Context context, int n) {
        final RealmVideoDetails[] episodes = this.mOfflinePlayableUiItemList.get(n).getEpisodes();
        final int length = episodes.length;
        int i = 0;
        int n2 = 0;
        int n3 = 0;
        n = 0;
        int n4 = 0;
        while (i < length) {
            final RealmVideoDetails realmVideoDetails = episodes[i];
            int n6;
            if (realmVideoDetails.getType() == VideoType.EPISODE) {
                final DownloadState downloadState = this.mVideoIdToOfflineData.get(realmVideoDetails.getId()).getDownloadState();
                final int percentageDownloaded = this.getOfflinePlayableViewData(realmVideoDetails.getPlayable().getPlayableId()).getPercentageDownloaded();
                if (downloadState == DownloadState.InProgress) {
                    final int n5 = n + 1;
                    n = n2;
                    n6 = n5;
                }
                else if ((downloadState == DownloadState.Stopped && percentageDownloaded == 0) || downloadState == DownloadState.Creating || downloadState == DownloadState.CreateFailed) {
                    final int n7 = n2 + 1;
                    n6 = n;
                    n = n7;
                }
                else if (downloadState == DownloadState.Stopped && percentageDownloaded > 0) {
                    final int n8 = n;
                    n = n2;
                    ++n3;
                    n6 = n8;
                }
                else {
                    final int n9 = n;
                    n = n2;
                    n6 = n9;
                }
                if (downloadState != DownloadState.Complete) {
                    ++n4;
                }
            }
            else {
                final int n10 = n;
                n = n2;
                n6 = n10;
            }
            final int n11 = i + 1;
            final int n12 = n6;
            n2 = n;
            n = n12;
            i = n11;
        }
        if (n > 0) {
            return context.getString(2131296933, new Object[] { n, n4 });
        }
        if (n3 > 0) {
            return context.getString(2131296937, new Object[] { n2 + n3 });
        }
        if (n2 > 0) {
            return context.getString(2131296939, new Object[] { n2 });
        }
        return null;
    }
    
    private static boolean downloadCompleted(final OfflinePlayableViewData offlinePlayableViewData) {
        return offlinePlayableViewData.getDownloadState() == DownloadState.Complete;
    }
    
    private static boolean downloadPaused(final OfflinePlayableViewData offlinePlayableViewData) {
        return !OfflineUiHelper.hasErrorOrWarning(offlinePlayableViewData) && offlinePlayableViewData.getDownloadState() == DownloadState.Stopped;
    }
    
    private OfflinePlayableViewData getCurrentlyDownloading() {
        if (this.mVideoIdToOfflineData != null) {
            for (final OfflinePlayableViewData offlinePlayableViewData : this.mVideoIdToOfflineData.values()) {
                if (offlinePlayableViewData.getDownloadState() == DownloadState.InProgress) {
                    return offlinePlayableViewData;
                }
            }
        }
        return null;
    }
    
    private String getDownloadProgress(final Context context, final OfflinePlayableViewData offlinePlayableViewData) {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.getTitleOrEmpty(context, offlinePlayableViewData.getPlayableId())).append(" ").append("(" + StringUtils.getAsPercentString(offlinePlayableViewData.getPercentageDownloaded()) + ")");
        return sb.toString();
    }
    
    private String getTitleOrEmpty(final Context context, final String s) {
        final String s2 = this.mPlayableToTitleMap.get(s);
        if (s2 != null) {
            return s2;
        }
        final RealmVideoDetails offlineVideoDetails = RealmUtils.getOfflineVideoDetails(s);
        if (offlineVideoDetails == null) {
            return "";
        }
        String s3;
        if (offlineVideoDetails.getType() == VideoType.EPISODE && offlineVideoDetails.getPlayable() != null) {
            final RealmVideoDetails offlineVideoDetails2 = RealmUtils.getOfflineVideoDetails(offlineVideoDetails.getPlayable().getTopLevelId());
            if (offlineVideoDetails2 != null) {
                s3 = context.getString(2131296789, new Object[] { offlineVideoDetails2.getTitle(), offlineVideoDetails.getPlayable().getSeasonAbbrSeqLabel(), offlineVideoDetails.getPlayable().getEpisodeNumber(), offlineVideoDetails.getTitle() });
            }
            else {
                ErrorLoggingManager.logHandledException("No show found for episode: playableId=" + s + "/parent=" + offlineVideoDetails.getPlayable().getTopLevelId());
                s3 = context.getString(2131296645, new Object[] { offlineVideoDetails.getPlayable().getSeasonAbbrSeqLabel(), offlineVideoDetails.getPlayable().getEpisodeNumber(), offlineVideoDetails.getTitle() });
            }
        }
        else {
            s3 = offlineVideoDetails.getTitle();
        }
        this.mPlayableToTitleMap.put(s, s3);
        return s3;
    }
    
    public OfflineAdapterData get(final int n) {
        return this.mOfflinePlayableUiItemList.get(n);
    }
    
    @Override
    public CharSequence getColoredStatusString(final Context context, int n, String s, final VideoType videoType) {
        final int n2 = 2131755116;
        final String s2 = "";
        if (videoType == VideoType.SHOW) {
            s = this.buildShowCompositeStatus(context, n);
            n = n2;
        }
        else {
            if (videoType != VideoType.EPISODE && videoType != VideoType.MOVIE) {
                LogUtils.reportErrorSafely("Wrong VideoType (=" + videoType + ")");
                return null;
            }
            final OfflinePlayableViewData offlinePlayableViewData = this.mVideoIdToOfflineData.get(s);
            if (offlinePlayableViewData == null) {
                return null;
            }
            final int percentageDownloaded = offlinePlayableViewData.getPercentageDownloaded();
            final DownloadState downloadState = this.mVideoIdToOfflineData.get(s).getDownloadState();
            s = s2;
            n = n2;
            switch (OfflinePlayableUiListImpl$2.$SwitchMap$com$netflix$mediaclient$servicemgr$interface_$offline$DownloadState[downloadState.ordinal()]) {
                case 4: {
                    s = context.getString(2131296938);
                    n = n2;
                    break;
                }
                case 3: {
                    final WatchState watchState = offlinePlayableViewData.getWatchState();
                    s = s2;
                    n = n2;
                    switch (OfflinePlayableUiListImpl$2.$SwitchMap$com$netflix$mediaclient$servicemgr$interface_$offline$WatchState[watchState.ordinal()]) {
                        case 3:
                        case 4:
                        case 5: {
                            s = context.getString(2131296934);
                            n = 2131755134;
                        }
                        case 1:
                        case 6: {
                            break;
                        }
                        default: {
                            s = "";
                            n = 2131755116;
                            break;
                        }
                        case 2: {
                            final long expiringInMillis = offlinePlayableViewData.getExpiringInMillis();
                            s = s2;
                            n = n2;
                            if (expiringInMillis > 0L) {
                                if (expiringInMillis > TimeUnit.DAYS.toMillis(1L)) {
                                    n = (int)TimeUnit.MILLISECONDS.toDays(expiringInMillis);
                                    s = context.getResources().getQuantityString(2131361805, n, new Object[] { n });
                                }
                                else if (expiringInMillis > TimeUnit.HOURS.toMillis(1L)) {
                                    n = (int)TimeUnit.MILLISECONDS.toHours(expiringInMillis);
                                    s = context.getResources().getQuantityString(2131361806, n, new Object[] { n });
                                }
                                else {
                                    n = (int)TimeUnit.MILLISECONDS.toMinutes(expiringInMillis);
                                    s = context.getResources().getQuantityString(2131361807, n, new Object[] { n });
                                }
                                n = 2131755134;
                                break;
                            }
                            break;
                        }
                        case 7: {
                            n = 2131755134;
                            s = context.getString(2131296935);
                            break;
                        }
                    }
                    break;
                }
                case 1: {
                    s = context.getString(2131296932);
                    n = n2;
                }
                case 5:
                case 6:
                case 7:
                case 8: {
                    break;
                }
                default: {
                    LogUtils.reportErrorSafely("Wrong DownloadState (=" + downloadState + ")");
                    return null;
                }
                case 2: {
                    s = s2;
                    n = n2;
                    if (!offlinePlayableViewData.getStopReason().showBangIconErrorInUi()) {
                        if (percentageDownloaded > 0) {
                            s = context.getString(2131296936);
                        }
                        else {
                            s = context.getString(2131296938);
                        }
                        n = n2;
                        break;
                    }
                    break;
                }
            }
        }
        if (s != null) {
            final SpannableString spannableString = new SpannableString((CharSequence)s);
            ((Spannable)spannableString).setSpan((Object)new ForegroundColorSpan(ContextCompat.getColor(context, n)), 0, s.length(), 33);
            return (CharSequence)spannableString;
        }
        return null;
    }
    
    @Override
    public long getCurrentSpace(final int n) {
        return this.mOfflinePlayableUiItemList.get(n).getCurrentSpace(this.mVideoIdToOfflineData);
    }
    
    @Override
    public int getInProgressCount() {
        if (this.mVideoIdToOfflineData == null) {
            return 0;
        }
        final Iterator<OfflinePlayableViewData> iterator = this.mVideoIdToOfflineData.values().iterator();
        int n = 0;
        while (iterator.hasNext()) {
            if (iterator.next().getDownloadState() == DownloadState.InProgress) {
                ++n;
            }
        }
        return n;
    }
    
    @Override
    public OfflinePlayableViewData getOfflinePlayableViewData(final String s) {
        if (this.mVideoIdToOfflineData == null || s == null) {
            return null;
        }
        return this.mVideoIdToOfflineData.get(s);
    }
    
    @Override
    public Collection<OfflinePlayableViewData> getOfflinePlayableViewData() {
        return this.mVideoIdToOfflineData.values();
    }
    
    @Override
    public int getPercentage(final int n) {
        return this.mOfflinePlayableUiItemList.get(n).getPercentage(this.mVideoIdToOfflineData);
    }
    
    @Override
    public ActivityPageOfflineAgentListener$SnackbarMessage getSnackbarStatus(final Context context, final OfflineAgentInterface offlineAgentInterface) {
        if (this.mVideoIdToOfflineData == null || this.mVideoIdToOfflineData.size() == 0) {
            return null;
        }
        final int snackBarDownloadCompleteCount = OfflineUiHelper.getSnackBarDownloadCompleteCount(context);
        Log.i("OfflinePlayableUiListImpl", "getSnackbarStatus downloadCompleteSinceSwipe=%d", snackBarDownloadCompleteCount);
        final boolean requiresUnmeteredNetwork = offlineAgentInterface.getRequiresUnmeteredNetwork();
        boolean b;
        if (!ConnectivityUtils.isWiFiConnected(context)) {
            b = true;
        }
        else {
            b = false;
        }
        final boolean b2 = !ConnectivityUtils.isConnected(context);
        String s = null;
        if (requiresUnmeteredNetwork && b) {
            s = context.getString(2131296931);
        }
        else if (b2) {
            s = context.getString(2131296930);
        }
        final Collection<OfflinePlayableViewData> values = this.mVideoIdToOfflineData.values();
        if (values.size() == 1) {
            final OfflinePlayableViewData offlinePlayableViewData = values.iterator().next();
            final String titleOrEmpty = this.getTitleOrEmpty(context, offlinePlayableViewData.getPlayableId());
            if (OfflineUiHelper.hasErrorOrWarning(offlinePlayableViewData)) {
                if (s == null) {
                    s = titleOrEmpty;
                }
                return new ActivityPageOfflineAgentListener$SnackbarMessage(context.getResources().getString(2131296929, new Object[] { s }), 1);
            }
            if (downloadCompleted(offlinePlayableViewData)) {
                if (snackBarDownloadCompleteCount == 1) {
                    return new ActivityPageOfflineAgentListener$SnackbarMessage(context.getResources().getString(2131296926, new Object[] { titleOrEmpty }), 0);
                }
            }
            else {
                if (downloadPaused(offlinePlayableViewData)) {
                    if (s == null) {
                        s = context.getResources().getString(2131296936);
                    }
                    return new ActivityPageOfflineAgentListener$SnackbarMessage(context.getResources().getString(2131296927, new Object[] { s }), 0);
                }
                if (this.getCurrentlyDownloading() != null || s != null) {
                    if (s == null) {
                        s = this.getDownloadProgress(context, offlinePlayableViewData);
                    }
                    return new ActivityPageOfflineAgentListener$SnackbarMessage(context.getResources().getString(2131296927, new Object[] { s }), 0);
                }
            }
        }
        else {
            final Iterator<OfflinePlayableViewData> iterator = this.mVideoIdToOfflineData.values().iterator();
            int n = 0;
            int n2 = 0;
            int n3 = 0;
            int n4 = 0;
            int n5 = 0;
            while (iterator.hasNext()) {
                final OfflinePlayableViewData offlinePlayableViewData2 = iterator.next();
                final int n6 = n + 1;
                int n9;
                int n10;
                int n11;
                int n12;
                if (OfflineUiHelper.hasErrorOrWarning(offlinePlayableViewData2)) {
                    final int n7 = n5;
                    final int n8 = n4;
                    n9 = n2 + 1;
                    n10 = n3;
                    n11 = n8;
                    n12 = n7;
                }
                else if (downloadCompleted(offlinePlayableViewData2)) {
                    final int n13 = n3 + 1;
                    final int n14 = n2;
                    n12 = n5;
                    n11 = n4;
                    n10 = n13;
                    n9 = n14;
                }
                else if (downloadPaused(offlinePlayableViewData2)) {
                    final int n15 = n4 + 1;
                    n10 = n3;
                    final int n16 = n2;
                    n12 = n5;
                    n11 = n15;
                    n9 = n16;
                }
                else {
                    final int n17 = n5 + 1;
                    final int n18 = n3;
                    final int n19 = n2;
                    n12 = n17;
                    n11 = n4;
                    n10 = n18;
                    n9 = n19;
                }
                final int n20 = n9;
                final int n21 = n10;
                n5 = n12;
                n4 = n11;
                n3 = n21;
                n2 = n20;
                n = n6;
            }
            Log.i("OfflinePlayableUiListImpl", "total=%d failed=%d completed=%d paused=%d queued=%d downloadCompletedSinceSwipe=%d", n, n2, n3, n4, n5, snackBarDownloadCompleteCount);
            if (n == n3) {
                s = null;
            }
            int n22 = n3;
            if (snackBarDownloadCompleteCount < n3) {
                n22 = snackBarDownloadCompleteCount;
            }
            final int n23 = n5 + n4;
            if (n == n22) {
                return new ActivityPageOfflineAgentListener$SnackbarMessage(context.getResources().getQuantityString(2131361800, n22, new Object[] { n22 }), n2);
            }
            final OfflinePlayableViewData currentlyDownloading = this.getCurrentlyDownloading();
            if (n4 == n) {
                return new ActivityPageOfflineAgentListener$SnackbarMessage(context.getResources().getQuantityString(2131361801, n4, new Object[] { context.getResources().getString(2131296936), n4 }), n2);
            }
            if (n2 == n) {
                return new ActivityPageOfflineAgentListener$SnackbarMessage(context.getResources().getQuantityString(2131361802, n2, new Object[] { n2 }), n2);
            }
            if (s == null) {
                if (n23 > 0) {
                    s = context.getResources().getString(2131296936);
                }
                else {
                    s = "";
                }
                if (n2 > 0) {
                    s = context.getResources().getQuantityString(2131361803, n2, new Object[] { n2, "\ud83d\udca5" });
                }
                else if (currentlyDownloading != null) {
                    s = this.getDownloadProgress(context, currentlyDownloading);
                }
            }
            if (n23 > 0) {
                return new ActivityPageOfflineAgentListener$SnackbarMessage(context.getResources().getQuantityString(2131361801, n23, new Object[] { s, n23 }), n2);
            }
            if (n22 > 0) {
                if (TextUtils.isEmpty((CharSequence)s)) {
                    return new ActivityPageOfflineAgentListener$SnackbarMessage(context.getResources().getQuantityString(2131361800, n22, new Object[] { n22 }), n2);
                }
                return new ActivityPageOfflineAgentListener$SnackbarMessage(context.getResources().getQuantityString(2131361799, n22, new Object[] { s, n22 }), n2);
            }
        }
        return null;
    }
    
    @Override
    public int getTitleCount() {
        return this.getTitleCount(null);
    }
    
    @Override
    public int getTitleCount(final UserProfile userProfile) {
        if (this.mOfflinePlayableUiItemList == null) {
            return 0;
        }
        final Iterator<OfflineAdapterData> iterator = this.mOfflinePlayableUiItemList.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            final OfflineAdapterData offlineAdapterData = iterator.next();
            switch (OfflinePlayableUiListImpl$2.$SwitchMap$com$netflix$mediaclient$servicemgr$interface_$offline$OfflineAdapterData$ViewType[offlineAdapterData.getVideoAndProfileData().type.ordinal()]) {
                case 1:
                case 2: {
                    if (userProfile == null || offlineAdapterData.getVideoAndProfileData().video == null || TextUtils.equals((CharSequence)offlineAdapterData.getVideoAndProfileData().video.getProfileId(), (CharSequence)userProfile.getProfileGuid())) {
                        if (offlineAdapterData.getVideoAndProfileData().video == null) {
                            LogUtils.reportErrorSafely("adapterData.getVideoAndProfileData().video not supposed to be null but found null");
                        }
                        ++n;
                        continue;
                    }
                    continue;
                }
            }
        }
        return n;
    }
    
    @Override
    public int numberOfIncompleteItems() {
        if (this.mVideoIdToOfflineData == null) {
            return 0;
        }
        final Iterator<OfflinePlayableViewData> iterator = this.mVideoIdToOfflineData.values().iterator();
        int n = 0;
        while (iterator.hasNext()) {
            if (iterator.next().getDownloadState() != DownloadState.Complete) {
                ++n;
            }
        }
        return n;
    }
    
    @Override
    public int numberOfItemsToDownload() {
        if (this.mVideoIdToOfflineData == null) {
            return 0;
        }
        final Iterator<OfflinePlayableViewData> iterator = this.mVideoIdToOfflineData.values().iterator();
        int n = 0;
        while (iterator.hasNext()) {
            final OfflinePlayableViewData offlinePlayableViewData = iterator.next();
            if (offlinePlayableViewData.getDownloadState() != DownloadState.Complete && offlinePlayableViewData.getDownloadState() != DownloadState.CreateFailed) {
                ++n;
            }
        }
        return n;
    }
    
    @Override
    public void regenerate(final Realm realm, final Map<String, OfflinePlayableViewData> mVideoIdToOfflineData, final boolean b) {
        Log.i("OfflinePlayableUiListImpl", "Regenerating the list...");
        final ArrayList<OfflineAdapterData> mOfflinePlayableUiItemList = new ArrayList<OfflineAdapterData>();
        for (final RealmProfile realmProfile : realm.where(RealmProfile.class).findAll()) {
            List<OfflineAdapterData> list = null;
            final HashSet<String> set = new HashSet<String>();
            if (!b || realmProfile.isKids()) {
            Label_0322_Outer:
                for (RealmVideoDetails offlineVideoDetails : realm.where(RealmVideoDetails.class).equalTo("profileId", realmProfile.getId()).notEqualTo("videoType", VideoType.SHOW.getKey()).findAll()) {
                    List<Object> list2 = null;
                    int containsKey = 0;
                    Label_0333: {
                        if (offlineVideoDetails.getType() == VideoType.EPISODE) {
                            if (set.contains(offlineVideoDetails.getPlayable().getTopLevelId())) {
                                continue Label_0322_Outer;
                            }
                            offlineVideoDetails = RealmUtils.getOfflineVideoDetails(offlineVideoDetails.getPlayable().getTopLevelId());
                            if (offlineVideoDetails == null) {
                                ErrorLoggingManager.logHandledException("SPY-10714: getOfflineVideoDetails is null, probably deleted");
                                Log.e("OfflinePlayableUiListImpl", "SPY-10714: getOfflineVideoDetails is null, probably deleted");
                                continue Label_0322_Outer;
                            }
                            list2 = new ArrayList<Object>(realm.where(RealmVideoDetails.class).equalTo("profileId", realmProfile.getId()).equalTo("videoType", VideoType.EPISODE.getKey()).equalTo("playable.parentId", offlineVideoDetails.getId()).findAll());
                            Collections.sort(list2, (Comparator<? super Object>)new OfflinePlayableUiListImpl$1(this));
                            final Iterator<T> iterator3 = list2.iterator();
                            while (true) {
                                while (iterator3.hasNext()) {
                                    if (mVideoIdToOfflineData.containsKey(((VideoDetails)iterator3.next()).getId())) {
                                        containsKey = 1;
                                        set.add(offlineVideoDetails.getId());
                                        break Label_0333;
                                    }
                                }
                                containsKey = 0;
                                continue;
                            }
                        }
                        else {
                            containsKey = (mVideoIdToOfflineData.containsKey(offlineVideoDetails.getId()) ? 1 : 0);
                            list2 = null;
                        }
                    }
                    if (containsKey != 0) {
                        if (list == null) {
                            list = new ArrayList<OfflineAdapterData>();
                        }
                        list.add(new OfflineAdapterData(offlineVideoDetails, (List<RealmVideoDetails>)list2, mVideoIdToOfflineData));
                    }
                    else {
                        if (!Log.isLoggable()) {
                            continue;
                        }
                        Log.w("OfflinePlayableUiListImpl", "Video was not inside list (probably cancelled while offline. Video ID: " + offlineVideoDetails.getId());
                    }
                }
                if (list == null) {
                    continue;
                }
                mOfflinePlayableUiItemList.addAll((Collection<?>)list);
                set.clear();
            }
        }
        this.mVideoIdToOfflineData = mVideoIdToOfflineData;
        this.mOfflinePlayableUiItemList = mOfflinePlayableUiItemList;
        this.mPlayableToTitleMap.clear();
    }
    
    public int size() {
        return this.mOfflinePlayableUiItemList.size();
    }
}
