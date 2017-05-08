// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.exoplayback;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface$ManifestCallback;
import java.io.IOException;
import com.google.android.exoplayer.upstream.UriLoadable$Parser;
import com.google.android.exoplayer.upstream.UriDataSource;
import com.google.android.exoplayer.upstream.UriLoadable;
import com.google.android.exoplayer.dash.mpd.MediaPresentationDescriptionParser;
import com.google.android.exoplayer.upstream.DefaultUriDataSource;
import com.google.android.exoplayer.util.Util;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface;
import com.netflix.mediaclient.media.manifest.VideoTrack;
import java.util.List;
import com.netflix.mediaclient.media.Subtitle;
import com.google.android.exoplayer.dash.mpd.MediaPresentationDescription;
import org.json.JSONObject;
import com.netflix.mediaclient.service.pdslogging.DownloadContext;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.media.AudioSubtitleDefaultOrderInfo;
import android.util.Pair;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface$OfflineManifest;

class DummyOfflineAgent$DummyOfflineManifest implements OfflinePlaybackInterface$OfflineManifest
{
    final /* synthetic */ DummyOfflineAgent this$0;
    
    DummyOfflineAgent$DummyOfflineManifest(final DummyOfflineAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public Pair<Integer, Integer> getAspectWidthHeight() {
        return null;
    }
    
    @Override
    public AudioSubtitleDefaultOrderInfo[] getAudioSubtitleDefaultOrderInfo() {
        return new AudioSubtitleDefaultOrderInfo[0];
    }
    
    @Override
    public AudioSource[] getAudioTrackList() {
        return new AudioSource[0];
    }
    
    @Override
    public String getBifFile() {
        return null;
    }
    
    @Override
    public DownloadContext getDownloadContext() {
        return null;
    }
    
    @Override
    public String getDxId() {
        return null;
    }
    
    @Override
    public JSONObject getLinks() {
        return null;
    }
    
    @Override
    public MediaPresentationDescription getMpd() {
        return this.this$0.buildMpdfromUrl(this.this$0.mContext, "file:///sdcard/offline/offline.mpd");
    }
    
    @Override
    public byte[] getOfflineKeySetId() {
        return new byte[0];
    }
    
    @Override
    public String getOxId() {
        return null;
    }
    
    @Override
    public Subtitle[] getSubtitleTrackList() {
        return new Subtitle[0];
    }
    
    @Override
    public List<VideoTrack> getVideoTrackList() {
        return null;
    }
}
