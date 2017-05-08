// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.download;

import com.netflix.mediaclient.media.manifest.VideoTrack;
import com.netflix.mediaclient.media.TrickplayUrl;
import java.util.Collection;
import com.netflix.mediaclient.media.SubtitleUrl;
import java.util.HashMap;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.media.SubtitleTrackData;
import java.util.Iterator;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.media.manifest.Stream;
import java.util.ArrayList;
import com.netflix.mediaclient.service.player.manifest.NfManifest;
import java.util.Comparator;
import java.util.Collections;
import java.util.Arrays;
import com.netflix.mediaclient.servicemgr.ISubtitleDef$SubtitleProfile;
import java.util.List;

class DownloadableSelector
{
    private static final int MAX_AUDIO_TRACKS = 5;
    private static final int MAX_SUBTITLE_TRACKS = 5;
    private static final String TAG = "nf_downloadableSelect";
    
    private static List<ISubtitleDef$SubtitleProfile> getOrderedListOfProfiles() {
        final List<ISubtitleDef$SubtitleProfile> list = Arrays.asList(ISubtitleDef$SubtitleProfile.values());
        Collections.sort((List<Object>)list, (Comparator<? super Object>)new DownloadableSelector$1());
        return list;
    }
    
    public static List<AudioDownloadableInfo> selectAudioDownloadables(final NfManifest nfManifest, final List<String> list) {
        final ArrayList<AudioDownloadableInfo> list2 = new ArrayList<AudioDownloadableInfo>();
        final AudioSource[] audioTrackList = nfManifest.getAudioTrackList();
        for (int length = audioTrackList.length, i = 0; i < length; ++i) {
            for (final Stream stream : audioTrackList[i].getStreams()) {
                if (list == null || list.contains(stream.downloadable_id)) {
                    final AudioDownloadableInfo create = AudioDownloadableInfo.create(stream);
                    if (create == null) {
                        continue;
                    }
                    list2.add(create);
                }
            }
            if (list2.size() >= 5) {
                break;
            }
        }
        return list2;
    }
    
    public static List<SubtitleDownloadableInfo> selectSubtitleDownloadables(final NfManifest nfManifest, final List<String> list) {
        final List<ISubtitleDef$SubtitleProfile> orderedListOfProfiles = getOrderedListOfProfiles();
        final ArrayList<SubtitleDownloadableInfo> list2 = new ArrayList<SubtitleDownloadableInfo>();
        for (final SubtitleTrackData subtitleTrackData : nfManifest.getSubtitleTracks(System.nanoTime())) {
            if (subtitleTrackData.getUrls().size() > 0) {
                final String languageCodeIso639_1 = subtitleTrackData.getSubtitleInfo().getLanguageCodeIso639_1();
                final int trackType = subtitleTrackData.getSubtitleInfo().getTrackType();
                if (Log.isLoggable()) {
                    Log.i("nf_downloadableSelect", String.format("language=%s trackType=%d", languageCodeIso639_1, trackType));
                }
                final HashMap<Object, ArrayList<SubtitleUrl>> hashMap = new HashMap<Object, ArrayList<SubtitleUrl>>();
                for (final SubtitleUrl subtitleUrl : subtitleTrackData.getUrls()) {
                    if (list == null || list.contains(subtitleUrl.getDownloadableId())) {
                        if (hashMap.get(subtitleUrl.getProfile()) == null) {
                            hashMap.put(subtitleUrl.getProfile(), new ArrayList<SubtitleUrl>());
                        }
                        ((ArrayList<SubtitleUrl>)hashMap.get(subtitleUrl.getProfile())).add(subtitleUrl);
                    }
                }
                final ArrayList<SubtitleUrl> list3 = new ArrayList<SubtitleUrl>();
                for (final ISubtitleDef$SubtitleProfile subtitleDef$SubtitleProfile : orderedListOfProfiles) {
                    if (hashMap.get(subtitleDef$SubtitleProfile) != null) {
                        list3.addAll((Collection<?>)hashMap.get(subtitleDef$SubtitleProfile));
                        break;
                    }
                }
                final SubtitleDownloadableInfo create = SubtitleDownloadableInfo.create(subtitleTrackData, list3);
                if (create != null) {
                    list2.add(create);
                }
                if (list2.size() >= 5) {
                    break;
                }
                continue;
            }
        }
        return list2;
    }
    
    public static List<TrickPlayDownloadableInfo> selectTrickPlayDownloadables(final NfManifest nfManifest, final List<String> list) {
        final ArrayList<TrickPlayDownloadableInfo> list2 = new ArrayList<TrickPlayDownloadableInfo>();
        final TrickplayUrl[] trickplayUrls = nfManifest.getTrickplayUrls();
        for (int length = trickplayUrls.length, i = 0; i < length; ++i) {
            final TrickplayUrl trickplayUrl = trickplayUrls[i];
            if ((list == null || list.contains(trickplayUrl.getDownloadableId())) && trickplayUrl.hasAtLeastOneUrl()) {
                final TrickPlayDownloadableInfo create = TrickPlayDownloadableInfo.create(trickplayUrl);
                if (create != null) {
                    list2.add(create);
                    return list2;
                }
            }
        }
        return Collections.emptyList();
    }
    
    public static List<VideoDownloadableInfo> selectVideoDownloadables(final NfManifest nfManifest, final List<String> list) {
        final ArrayList<VideoDownloadableInfo> list2 = new ArrayList<VideoDownloadableInfo>();
        final Iterator<VideoTrack> iterator = nfManifest.getVideoTracks().iterator();
        while (iterator.hasNext()) {
            for (final Stream stream : iterator.next().streams) {
                Log.i("nf_downloadableSelect", "video_tracks has bitrate " + stream.bitrate);
                if (list == null || list.contains(stream.downloadable_id)) {
                    final VideoDownloadableInfo create = VideoDownloadableInfo.create(stream);
                    if (create != null) {
                        list2.add(create);
                        return list2;
                    }
                    continue;
                }
            }
        }
        return Collections.emptyList();
    }
}
