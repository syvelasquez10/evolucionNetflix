// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.manifest;

import com.netflix.mediaclient.media.manifest.VideoTrack;
import android.util.Pair;
import java.util.Iterator;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.media.manifest.Stream;
import com.netflix.mediaclient.media.AudioSource;

public class NfManifestUtils
{
    public static AudioSource getAudioSourceForDownloadable(final NfManifest nfManifest, final String s) {
        final AudioSource[] audioTrackList = nfManifest.getAudioTrackList();
        for (int length = audioTrackList.length, i = 0; i < length; ++i) {
            final AudioSource audioSource = audioTrackList[i];
            final Iterator<Stream> iterator = audioSource.getStreams().iterator();
            while (iterator.hasNext()) {
                if (StringUtils.notEmptyAndEquals(iterator.next().downloadable_id, s)) {
                    return audioSource;
                }
            }
        }
        return null;
    }
    
    public static Pair<AudioSource, Stream> getAudioSourceStreamForDownloadable(final NfManifest nfManifest, final String s) {
        final AudioSource[] audioTrackList = nfManifest.getAudioTrackList();
        for (int length = audioTrackList.length, i = 0; i < length; ++i) {
            final AudioSource audioSource = audioTrackList[i];
            for (final Stream stream : audioSource.getStreams()) {
                if (StringUtils.notEmptyAndEquals(stream.downloadable_id, s)) {
                    return (Pair<AudioSource, Stream>)new Pair((Object)audioSource, (Object)stream);
                }
            }
        }
        return null;
    }
    
    public static String getDownloadableForAudioTrackId(final NfManifest nfManifest, final String s) {
        final AudioSource[] audioTrackList = nfManifest.getAudioTrackList();
        for (int length = audioTrackList.length, i = 0; i < length; ++i) {
            final AudioSource audioSource = audioTrackList[i];
            if (StringUtils.notEmptyAndEquals(audioSource.getId(), s)) {
                return audioSource.getStreams().get(0).downloadable_id;
            }
        }
        return null;
    }
    
    public static Pair<VideoTrack, Stream> getVideoTrackStreamForDownloadable(final NfManifest nfManifest, final String s) {
        for (final VideoTrack videoTrack : nfManifest.getVideoTracks()) {
            for (final Stream stream : videoTrack.streams) {
                if (StringUtils.notEmptyAndEquals(stream.downloadable_id, s)) {
                    return (Pair<VideoTrack, Stream>)new Pair((Object)videoTrack, (Object)stream);
                }
            }
        }
        return null;
    }
}
