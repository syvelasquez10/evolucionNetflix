// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.exoplayback;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface$OfflineManifest;
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
import com.google.android.exoplayer.dash.mpd.MediaPresentationDescription;
import android.content.Context;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface;

public class DummyOfflineAgent implements OfflinePlaybackInterface
{
    private static final String MPD_URL = "file:///sdcard/offline/offline.mpd";
    private static final String TAG = "dummy_offline_agent";
    private final Context mContext;
    
    public DummyOfflineAgent(final Context mContext) {
        this.mContext = mContext;
    }
    
    private MediaPresentationDescription buildMpdfromUrl(final Context context, final String s) {
        Log.d("dummy_offline_agent", "generateMPD");
        final UriLoadable<MediaPresentationDescription> uriLoadable = new UriLoadable<MediaPresentationDescription>(s, new DefaultUriDataSource(context, Util.getUserAgent(context, "dummy_offline_agent")), new MediaPresentationDescriptionParser());
        try {
            uriLoadable.load();
            final MediaPresentationDescription mediaPresentationDescription = uriLoadable.getResult();
            Log.d("dummy_offline_agent", "generateMPD done");
            return mediaPresentationDescription;
        }
        catch (IOException ex) {
            Log.w("dummy_offline_agent", " fial to load manifest" + ex);
            return null;
        }
        catch (InterruptedException ex2) {
            Log.w("dummy_offline_agent", " fial to load manifest" + ex2);
            return null;
        }
    }
    
    @Override
    public void abortManifestRequest(final long n) {
    }
    
    @Override
    public void notifyPause(final long n) {
    }
    
    @Override
    public void notifyPlay(final long n) {
    }
    
    @Override
    public void notifyPlayError(final long n) {
    }
    
    @Override
    public void notifyPlayProgress(final long n, final long n2) {
    }
    
    @Override
    public void notifyStop(final long n) {
    }
    
    @Override
    public void requestOfflineManifest(final long n, final OfflinePlaybackInterface$ManifestCallback offlinePlaybackInterface$ManifestCallback) {
        offlinePlaybackInterface$ManifestCallback.onManifestResponse(n, new DummyOfflineAgent$DummyOfflineManifest(this), CommonStatus.OK);
    }
}
