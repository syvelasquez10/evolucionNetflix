// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.utils;

import java.io.UnsupportedEncodingException;
import android.util.Base64;
import java.util.concurrent.TimeUnit;
import com.netflix.mediaclient.media.manifest.Stream;
import com.netflix.mediaclient.media.manifest.VideoTrack;
import java.util.Collection;
import com.netflix.mediaclient.ui.offline.OfflineSubtitle;
import com.netflix.mediaclient.service.offline.download.DownloadableType;
import com.netflix.mediaclient.media.SubtitleUrl;
import com.netflix.mediaclient.media.SubtitleTrackData;
import java.util.HashMap;
import com.netflix.mediaclient.media.Subtitle;
import java.util.Map;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.offline.download.DownloadablePersistentData;
import java.util.Iterator;
import com.netflix.mediaclient.service.player.manifest.NfManifestUtils;
import java.util.ArrayList;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.service.player.manifest.NfManifest;
import com.netflix.mediaclient.util.AndroidUtils;
import java.io.File;
import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadState;
import com.netflix.mediaclient.service.offline.download.OfflinePlayable;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.offline.download.TrickPlayDownloadableInfo;
import com.netflix.mediaclient.service.offline.download.SubtitleDownloadableInfo;
import com.netflix.mediaclient.service.offline.download.VideoDownloadableInfo;
import com.netflix.mediaclient.service.offline.download.AudioDownloadableInfo;
import java.util.List;
import com.netflix.mediaclient.service.offline.download.OfflinePlayablePersistentData;

public class OfflineUtils
{
    public static final String DOWNLOADS_LOLOMO_GENRE_ID = "1647397";
    private static final String TAG = "nf_offlineUtils";
    
    public static boolean areAllDownloadablesStillFound(final OfflinePlayablePersistentData offlinePlayablePersistentData, final List<AudioDownloadableInfo> list, final List<VideoDownloadableInfo> list2, final List<SubtitleDownloadableInfo> list3, final List<TrickPlayDownloadableInfo> list4) {
        if (list.size() != offlinePlayablePersistentData.mAudioDownloadablePersistentList.size()) {
            Log.e("nf_offlineUtils", "missing audio downloadables. expected=" + offlinePlayablePersistentData.mAudioDownloadablePersistentList.size() + " got=" + list.size());
            return false;
        }
        if (list2.size() != offlinePlayablePersistentData.mVideoDownloadablePersistentList.size()) {
            Log.e("nf_offlineUtils", "missing video downloadables. expected=" + offlinePlayablePersistentData.mVideoDownloadablePersistentList.size() + " got=" + list2.size());
            return false;
        }
        if (list3.size() != offlinePlayablePersistentData.mSubtitleDownloadablePersistentList.size()) {
            Log.e("nf_offlineUtils", "missing timed-text downloadables. expected=" + offlinePlayablePersistentData.mSubtitleDownloadablePersistentList.size() + " got=" + list3.size());
            return false;
        }
        if (list4.size() != offlinePlayablePersistentData.mTrickPlayDownloadablePersistentList.size()) {
            Log.e("nf_offlineUtils", "missing tickPlay downloadables. expected=" + offlinePlayablePersistentData.mTrickPlayDownloadablePersistentList.size() + " got=" + list4.size());
            return false;
        }
        return true;
    }
    
    public static boolean canStartDownload(final OfflinePlayable offlinePlayable) {
        return offlinePlayable.getDownloadState() == DownloadState.Stopped && offlinePlayable.canResumeWithoutUserAction();
    }
    
    public static boolean cdnUrlExpiredOrMoved(final int n) {
        return n == 403 || n == 404;
    }
    
    public static boolean deletePlayableDirectory(final String s) {
        final File file = new File(s);
        boolean deleteDir = true;
        if (file.exists()) {
            deleteDir = AndroidUtils.deleteDir(file);
        }
        return deleteDir;
    }
    
    public static AudioSource[] getAudioSourceArrayForDownloadables(final NfManifest nfManifest, final List<String> list) {
        final ArrayList<AudioSource> list2 = new ArrayList<AudioSource>();
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            final AudioSource audioSourceForDownloadable = NfManifestUtils.getAudioSourceForDownloadable(nfManifest, iterator.next());
            if (audioSourceForDownloadable != null) {
                list2.add(audioSourceForDownloadable);
            }
        }
        return list2.toArray(new AudioSource[list2.size()]);
    }
    
    public static List<String> getDownloadableList(final List<DownloadablePersistentData> list) {
        final ArrayList<String> list2 = new ArrayList<String>();
        final Iterator<DownloadablePersistentData> iterator = list.iterator();
        while (iterator.hasNext()) {
            list2.add(iterator.next().mDownloadableId);
        }
        return list2;
    }
    
    public static byte[] getKeySetIdOrNull(final OfflinePlayablePersistentData offlinePlayablePersistentData) {
        if (StringUtils.isNotEmpty(offlinePlayablePersistentData.mLicenseData.mKeySetId)) {
            return keySetIdFromString(offlinePlayablePersistentData.mLicenseData.mKeySetId);
        }
        return null;
    }
    
    public static long getMaintenanceJobPeriodMsFromPref(final Context context, final long n) {
        return PreferenceUtils.getLongPref(context, "offline_maintenace_job_period", n);
    }
    
    private static Map<String, Subtitle> getOfflineSubtitleForDownloadables(final NfManifest nfManifest, final List<String> list, final String s) {
        final HashMap<String, OfflineSubtitle> hashMap = (HashMap<String, OfflineSubtitle>)new HashMap<String, Subtitle>();
        for (final SubtitleTrackData subtitleTrackData : nfManifest.getSubtitleTracks(System.nanoTime())) {
            for (final String s2 : list) {
                if (subtitleTrackData.getUrls().size() > 0 && StringUtils.notEmptyAndEquals(subtitleTrackData.getUrls().get(0).getDownloadableId(), s2)) {
                    final OfflineSubtitle instance = OfflineSubtitle.newInstance(subtitleTrackData.getSubtitleInfo(), (SubtitleUrl)subtitleTrackData.getUrls().get(0), OfflinePathUtils.getFilePathForDownloadable(s, s2, DownloadableType.Subtitle));
                    hashMap.put(s2, (Subtitle)instance);
                    Log.d("nf_offlineUtils", "Downloadable ID: %s: %s", s2, instance.toString());
                    break;
                }
            }
            if (hashMap.size() == list.size()) {
                break;
            }
        }
        return (Map<String, Subtitle>)hashMap;
    }
    
    public static Subtitle[] getSubTitleArrayForDownloadables(final NfManifest nfManifest, final List<String> list, final String s) {
        final Collection<Subtitle> values = getOfflineSubtitleForDownloadables(nfManifest, list, s).values();
        return values.toArray(new Subtitle[values.size()]);
    }
    
    public static List<VideoTrack> getVideoTracks(final NfManifest nfManifest, final List<String> list) {
        final ArrayList<VideoTrack> list2 = new ArrayList<VideoTrack>();
        for (final VideoTrack videoTrack : nfManifest.getVideoTracks()) {
            for (final Stream stream : videoTrack.streams) {
                if (list == null || list.contains(stream.downloadable_id)) {
                    list2.add(videoTrack);
                    break;
                }
            }
        }
        return list2;
    }
    
    public static boolean hasManifestExpired(final NfManifest nfManifest) {
        final long n = System.currentTimeMillis() - nfManifest.getManifestExpiryInEndPointTime();
        if (n > 0L) {
            Log.e("nf_offlineUtils", "manifestExpired by " + TimeUnit.MILLISECONDS.toSeconds(n) + " seconds");
            return true;
        }
        return false;
    }
    
    public static boolean isCdnUrlGeoCheckError(final int n) {
        return n == 420;
    }
    
    public static boolean isDownloading(final OfflinePlayable offlinePlayable) {
        return offlinePlayable.getDownloadState() == DownloadState.InProgress;
    }
    
    private static byte[] keySetIdFromString(final String s) {
        try {
            return Base64.decode(s.getBytes("utf-8"), 0);
        }
        catch (UnsupportedEncodingException ex) {
            Log.e("nf_offlineUtils", ex, "keySetIdFromString UnsupportedEncodingException", new Object[0]);
            return new byte[0];
        }
    }
    
    public static String keySetIdToString(final byte[] array) {
        try {
            return new String(Base64.encode(array, 0), "utf-8");
        }
        catch (UnsupportedEncodingException ex) {
            Log.e("nf_offlineUtils", ex, "keySetIdToString UnsupportedEncodingException", new Object[0]);
            return "";
        }
    }
    
    public static void setMaintenanceJobPeriodMsToPref(final Context context, final long n) {
        PreferenceUtils.putLongPref(context, "offline_maintenace_job_period", n);
    }
}
