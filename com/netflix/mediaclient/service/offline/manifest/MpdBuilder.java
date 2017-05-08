// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.manifest;

import com.google.android.exoplayer.dash.mpd.UtcTimingElement;
import com.google.android.exoplayer.dash.mpd.MediaPresentationDescription;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.google.android.exoplayer.dash.mpd.RangedUri;
import com.google.android.exoplayer.dash.mpd.SegmentBase$SingleSegmentBase;
import com.netflix.mediaclient.service.offline.utils.OfflinePathUtils;
import com.google.android.exoplayer.dash.mpd.SegmentBase;
import java.util.Collections;
import com.google.android.exoplayer.dash.mpd.Period;
import android.util.Pair;
import java.util.Iterator;
import com.google.android.exoplayer.dash.mpd.ContentProtection;
import com.netflix.mediaclient.service.offline.download.DownloadableType;
import com.netflix.mediaclient.media.manifest.VideoTrack;
import com.google.android.exoplayer.chunk.Format;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.media.manifest.Stream;
import com.netflix.mediaclient.service.player.manifest.NfManifestUtils;
import com.google.android.exoplayer.dash.mpd.Representation;
import java.util.ArrayList;
import com.google.android.exoplayer.dash.mpd.AdaptationSet;
import com.netflix.mediaclient.service.player.manifest.NfManifest;
import java.util.List;

class MpdBuilder
{
    private static final String TAG = "nf_offline_mpd_builder";
    private final List<String> mAudioDownloadables;
    private final String mDefaultAudioTrackDownloadableId;
    private final String mDirPathOfPlayable;
    private final NfManifest mNfManifest;
    private final List<String> mVideoDownloadables;
    
    public MpdBuilder(final String mDirPathOfPlayable, final NfManifest mNfManifest, final List<String> mAudioDownloadables, final List<String> mVideoDownloadables, final String mDefaultAudioTrackDownloadableId) {
        this.mDirPathOfPlayable = mDirPathOfPlayable;
        this.mNfManifest = mNfManifest;
        this.mAudioDownloadables = mAudioDownloadables;
        this.mVideoDownloadables = mVideoDownloadables;
        this.mDefaultAudioTrackDownloadableId = mDefaultAudioTrackDownloadableId;
    }
    
    private AdaptationSet buildAdaptationSet(final int n, final List<String> list, final boolean b) {
        final ArrayList<Representation> list2 = new ArrayList<Representation>();
        for (final String s : list) {
            String s2;
            Format format;
            if (b) {
                final Pair<AudioSource, Stream> audioSourceStreamForDownloadable = NfManifestUtils.getAudioSourceStreamForDownloadable(this.mNfManifest, s);
                if (audioSourceStreamForDownloadable == null || audioSourceStreamForDownloadable.first == null || audioSourceStreamForDownloadable.second == null) {
                    continue;
                }
                s2 = ((Stream)audioSourceStreamForDownloadable.second).downloadable_id;
                format = new Format(((AudioSource)audioSourceStreamForDownloadable.first).getId(), "audio/mp4", -1, -1, -1.0f, ((AudioSource)audioSourceStreamForDownloadable.first).getNumChannels(), 48000, ((Stream)audioSourceStreamForDownloadable.second).bitrate * 1000, ((AudioSource)audioSourceStreamForDownloadable.first).getLanguageCodeIso639_1(), "mp4a.40.2");
            }
            else {
                final Pair<VideoTrack, Stream> videoTrackStreamForDownloadable = NfManifestUtils.getVideoTrackStreamForDownloadable(this.mNfManifest, s);
                if (videoTrackStreamForDownloadable == null || videoTrackStreamForDownloadable.first == null || videoTrackStreamForDownloadable.second == null) {
                    continue;
                }
                s2 = ((Stream)videoTrackStreamForDownloadable.second).downloadable_id;
                format = new Format(((VideoTrack)videoTrackStreamForDownloadable.first).track_id, "video/mp4", -1, -1, -1.0f, -1, 48000, ((Stream)videoTrackStreamForDownloadable.second).bitrate * 1000, null, "avc3.4d400d");
            }
            DownloadableType downloadableType;
            if (b) {
                downloadableType = DownloadableType.Audio;
            }
            else {
                downloadableType = DownloadableType.Video;
            }
            list2.add(this.buildRepresentation(format, s2, downloadableType));
        }
        int n2;
        if (b) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        return new AdaptationSet(n, n2, list2, null);
    }
    
    private List<Period> buildPeriods() {
        final ArrayList<AdaptationSet> list = new ArrayList<AdaptationSet>();
        if (this.mAudioDownloadables.size() > 0) {
            list.add(this.buildAdaptationSet(0, this.setDefaultAudio(this.mAudioDownloadables, this.mDefaultAudioTrackDownloadableId), true));
        }
        if (this.mVideoDownloadables.size() > 0) {
            list.add(this.buildAdaptationSet(1, this.mVideoDownloadables, false));
        }
        return Collections.singletonList(new Period(null, 0L, list));
    }
    
    private Representation buildRepresentation(final Format format, final String s, final DownloadableType downloadableType) {
        return Representation.newInstance(String.valueOf(this.mNfManifest.getMovieId()), -1L, format, this.getSegmentBase(s, downloadableType));
    }
    
    private SegmentBase getSegmentBase(String filePathForDownloadable, final DownloadableType downloadableType) {
        filePathForDownloadable = OfflinePathUtils.getFilePathForDownloadable(this.mDirPathOfPlayable, filePathForDownloadable, downloadableType);
        final NetflixFMP4Parser$SidxInfo sidxInfo = NetflixFMP4Parser.parseSidxInfo(filePathForDownloadable);
        if (sidxInfo != null) {
            return new SegmentBase$SingleSegmentBase(null, 1L, 0L, "file://" + filePathForDownloadable, 0L, sidxInfo.getSidxOffset() + sidxInfo.getSidxlength());
        }
        return null;
    }
    
    private List<String> setDefaultAudio(final List<String> list, final String s) {
        if (StringUtils.isEmpty(s)) {
            return list;
        }
        final String s2 = null;
        final ArrayList<String> list2 = new ArrayList<String>(list.size());
        final Iterator<String> iterator = list.iterator();
        String s3 = s2;
        while (iterator.hasNext()) {
            final String s4 = iterator.next();
            if (s != null && s.equals(s4)) {
                Log.d("nf_offline_mpd_builder", "setDefaultAudio:: default audio track found %s", s4);
                s3 = s4;
            }
            else {
                list2.add(s4);
            }
        }
        if (s3 != null) {
            list2.add(0, s3);
        }
        else {
            Log.w("nf_offline_mpd_builder", "setDefaultAudio:: default audio track NOT found for %s", s);
        }
        return list2;
    }
    
    public MediaPresentationDescription buildMpd() {
        try {
            return new MediaPresentationDescription(0L, this.mNfManifest.getDuration(), -1L, false, -1L, -1L, null, null, this.buildPeriods());
        }
        catch (RuntimeException ex) {
            Log.e("nf_offline_mpd_builder", "Unable to buildMpd: " + ex.getMessage());
            return null;
        }
    }
}
