// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import org.json.JSONObject;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.ir;
import com.google.android.gms.internal.iq;

public class RemoteMediaPlayer implements Cast$MessageReceivedCallback
{
    public static final int RESUME_STATE_PAUSE = 2;
    public static final int RESUME_STATE_PLAY = 1;
    public static final int RESUME_STATE_UNCHANGED = 0;
    public static final int STATUS_CANCELED = 2101;
    public static final int STATUS_FAILED = 2100;
    public static final int STATUS_REPLACED = 2103;
    public static final int STATUS_SUCCEEDED = 0;
    public static final int STATUS_TIMED_OUT = 2102;
    private final iq FG;
    private final RemoteMediaPlayer$a FH;
    private RemoteMediaPlayer$OnMetadataUpdatedListener FI;
    private RemoteMediaPlayer$OnStatusUpdatedListener FJ;
    private final Object mw;
    
    public RemoteMediaPlayer() {
        this.mw = new Object();
        this.FH = new RemoteMediaPlayer$a(this);
        (this.FG = new RemoteMediaPlayer$1(this)).a(this.FH);
    }
    
    private void onMetadataUpdated() {
        if (this.FI != null) {
            this.FI.onMetadataUpdated();
        }
    }
    
    private void onStatusUpdated() {
        if (this.FJ != null) {
            this.FJ.onStatusUpdated();
        }
    }
    
    public long getApproximateStreamPosition() {
        synchronized (this.mw) {
            return this.FG.getApproximateStreamPosition();
        }
    }
    
    public MediaInfo getMediaInfo() {
        synchronized (this.mw) {
            return this.FG.getMediaInfo();
        }
    }
    
    public MediaStatus getMediaStatus() {
        synchronized (this.mw) {
            return this.FG.getMediaStatus();
        }
    }
    
    public String getNamespace() {
        return this.FG.getNamespace();
    }
    
    public long getStreamDuration() {
        synchronized (this.mw) {
            return this.FG.getStreamDuration();
        }
    }
    
    public PendingResult<RemoteMediaPlayer$MediaChannelResult> load(final GoogleApiClient googleApiClient, final MediaInfo mediaInfo) {
        return this.load(googleApiClient, mediaInfo, true, 0L, null, null);
    }
    
    public PendingResult<RemoteMediaPlayer$MediaChannelResult> load(final GoogleApiClient googleApiClient, final MediaInfo mediaInfo, final boolean b) {
        return this.load(googleApiClient, mediaInfo, b, 0L, null, null);
    }
    
    public PendingResult<RemoteMediaPlayer$MediaChannelResult> load(final GoogleApiClient googleApiClient, final MediaInfo mediaInfo, final boolean b, final long n) {
        return this.load(googleApiClient, mediaInfo, b, n, null, null);
    }
    
    public PendingResult<RemoteMediaPlayer$MediaChannelResult> load(final GoogleApiClient googleApiClient, final MediaInfo mediaInfo, final boolean b, final long n, final JSONObject jsonObject) {
        return this.load(googleApiClient, mediaInfo, b, n, null, jsonObject);
    }
    
    public PendingResult<RemoteMediaPlayer$MediaChannelResult> load(final GoogleApiClient googleApiClient, final MediaInfo mediaInfo, final boolean b, final long n, final long[] array, final JSONObject jsonObject) {
        return googleApiClient.b((PendingResult<RemoteMediaPlayer$MediaChannelResult>)new RemoteMediaPlayer$4(this, googleApiClient, mediaInfo, b, n, array, jsonObject));
    }
    
    @Override
    public void onMessageReceived(final CastDevice castDevice, final String s, final String s2) {
        this.FG.aD(s2);
    }
    
    public PendingResult<RemoteMediaPlayer$MediaChannelResult> pause(final GoogleApiClient googleApiClient) {
        return this.pause(googleApiClient, null);
    }
    
    public PendingResult<RemoteMediaPlayer$MediaChannelResult> pause(final GoogleApiClient googleApiClient, final JSONObject jsonObject) {
        return googleApiClient.b((PendingResult<RemoteMediaPlayer$MediaChannelResult>)new RemoteMediaPlayer$5(this, googleApiClient, jsonObject));
    }
    
    public PendingResult<RemoteMediaPlayer$MediaChannelResult> play(final GoogleApiClient googleApiClient) {
        return this.play(googleApiClient, null);
    }
    
    public PendingResult<RemoteMediaPlayer$MediaChannelResult> play(final GoogleApiClient googleApiClient, final JSONObject jsonObject) {
        return googleApiClient.b((PendingResult<RemoteMediaPlayer$MediaChannelResult>)new RemoteMediaPlayer$7(this, googleApiClient, jsonObject));
    }
    
    public PendingResult<RemoteMediaPlayer$MediaChannelResult> requestStatus(final GoogleApiClient googleApiClient) {
        return googleApiClient.b((PendingResult<RemoteMediaPlayer$MediaChannelResult>)new RemoteMediaPlayer$11(this, googleApiClient));
    }
    
    public PendingResult<RemoteMediaPlayer$MediaChannelResult> seek(final GoogleApiClient googleApiClient, final long n) {
        return this.seek(googleApiClient, n, 0, null);
    }
    
    public PendingResult<RemoteMediaPlayer$MediaChannelResult> seek(final GoogleApiClient googleApiClient, final long n, final int n2) {
        return this.seek(googleApiClient, n, n2, null);
    }
    
    public PendingResult<RemoteMediaPlayer$MediaChannelResult> seek(final GoogleApiClient googleApiClient, final long n, final int n2, final JSONObject jsonObject) {
        return googleApiClient.b((PendingResult<RemoteMediaPlayer$MediaChannelResult>)new RemoteMediaPlayer$8(this, googleApiClient, n, n2, jsonObject));
    }
    
    public PendingResult<RemoteMediaPlayer$MediaChannelResult> setActiveMediaTracks(final GoogleApiClient googleApiClient, final long[] array) {
        return googleApiClient.b((PendingResult<RemoteMediaPlayer$MediaChannelResult>)new RemoteMediaPlayer$2(this, googleApiClient, array));
    }
    
    public void setOnMetadataUpdatedListener(final RemoteMediaPlayer$OnMetadataUpdatedListener fi) {
        this.FI = fi;
    }
    
    public void setOnStatusUpdatedListener(final RemoteMediaPlayer$OnStatusUpdatedListener fj) {
        this.FJ = fj;
    }
    
    public PendingResult<RemoteMediaPlayer$MediaChannelResult> setStreamMute(final GoogleApiClient googleApiClient, final boolean b) {
        return this.setStreamMute(googleApiClient, b, null);
    }
    
    public PendingResult<RemoteMediaPlayer$MediaChannelResult> setStreamMute(final GoogleApiClient googleApiClient, final boolean b, final JSONObject jsonObject) {
        return googleApiClient.b((PendingResult<RemoteMediaPlayer$MediaChannelResult>)new RemoteMediaPlayer$10(this, googleApiClient, b, jsonObject));
    }
    
    public PendingResult<RemoteMediaPlayer$MediaChannelResult> setStreamVolume(final GoogleApiClient googleApiClient, final double n) {
        return this.setStreamVolume(googleApiClient, n, null);
    }
    
    public PendingResult<RemoteMediaPlayer$MediaChannelResult> setStreamVolume(final GoogleApiClient googleApiClient, final double n, final JSONObject jsonObject) {
        if (Double.isInfinite(n) || Double.isNaN(n)) {
            throw new IllegalArgumentException("Volume cannot be " + n);
        }
        return googleApiClient.b((PendingResult<RemoteMediaPlayer$MediaChannelResult>)new RemoteMediaPlayer$9(this, googleApiClient, n, jsonObject));
    }
    
    public PendingResult<RemoteMediaPlayer$MediaChannelResult> setTextTrackStyle(final GoogleApiClient googleApiClient, final TextTrackStyle textTrackStyle) {
        if (textTrackStyle == null) {
            throw new IllegalArgumentException("trackStyle cannot be null");
        }
        return googleApiClient.b((PendingResult<RemoteMediaPlayer$MediaChannelResult>)new RemoteMediaPlayer$3(this, googleApiClient, textTrackStyle));
    }
    
    public PendingResult<RemoteMediaPlayer$MediaChannelResult> stop(final GoogleApiClient googleApiClient) {
        return this.stop(googleApiClient, null);
    }
    
    public PendingResult<RemoteMediaPlayer$MediaChannelResult> stop(final GoogleApiClient googleApiClient, final JSONObject jsonObject) {
        return googleApiClient.b((PendingResult<RemoteMediaPlayer$MediaChannelResult>)new RemoteMediaPlayer$6(this, googleApiClient, jsonObject));
    }
}
