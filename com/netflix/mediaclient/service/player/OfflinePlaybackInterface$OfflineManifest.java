// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player;

import com.netflix.mediaclient.media.manifest.VideoTrack;
import java.util.List;
import com.netflix.mediaclient.media.Subtitle;
import com.google.android.exoplayer.dash.mpd.MediaPresentationDescription;
import org.json.JSONObject;
import com.netflix.mediaclient.service.pdslogging.DownloadContext;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.media.AudioSubtitleDefaultOrderInfo;
import android.util.Pair;

public interface OfflinePlaybackInterface$OfflineManifest
{
    Pair<Integer, Integer> getAspectWidthHeight();
    
    AudioSubtitleDefaultOrderInfo[] getAudioSubtitleDefaultOrderInfo();
    
    AudioSource[] getAudioTrackList();
    
    String getBifFile();
    
    DownloadContext getDownloadContext();
    
    String getDxId();
    
    JSONObject getLinks();
    
    MediaPresentationDescription getMpd();
    
    byte[] getOfflineKeySetId();
    
    String getOxId();
    
    Subtitle[] getSubtitleTrackList();
    
    List<VideoTrack> getVideoTrackList();
}
