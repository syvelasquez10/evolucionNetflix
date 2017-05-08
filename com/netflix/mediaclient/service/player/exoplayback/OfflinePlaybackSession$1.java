// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.exoplayback;

import com.netflix.mediaclient.media.PlayoutMetadata;
import java.nio.ByteBuffer;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.Logblob;
import com.netflix.mediaclient.service.player.exoplayback.logblob.StateChanged;
import com.netflix.mediaclient.service.player.exoplayback.logblob.StartPlay;
import com.netflix.mediaclient.service.player.exoplayback.logblob.ResumePlay;
import org.json.JSONException;
import com.netflix.mediaclient.service.logging.logblob.BaseLogblob;
import com.netflix.mediaclient.service.player.exoplayback.logblob.EndPlay;
import com.netflix.mediaclient.javabridge.ui.LogArguments$LogLevel;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.google.android.exoplayer.drm.DrmSessionManager;
import com.netflix.mediaclient.media.manifest.Stream;
import org.json.JSONObject;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import com.netflix.mediaclient.util.Time;
import com.netflix.mediaclient.util.NetflixTransactionIdGenerator;
import com.netflix.mediaclient.service.pdslogging.PdsPlayInterface;
import com.netflix.mediaclient.media.manifest.VideoTrack;
import java.util.List;
import android.view.Surface;
import com.netflix.mediaclient.service.offline.subtitles.SubtitleOfflineManager;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.service.pdslogging.PdsPlaySessionInterface;
import com.netflix.mediaclient.service.player.bif.OfflineBifManager;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface;
import android.os.Handler;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.media.Subtitle;
import android.content.Context;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.media.AudioSubtitleDefaultOrderInfo;
import com.netflix.mediaclient.media.AudioSource;
import android.util.Pair;
import com.netflix.mediaclient.util.activitytracking.ActivityTracker;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface$OfflineManifest;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface$ManifestCallback;

class OfflinePlaybackSession$1 implements OfflinePlaybackInterface$ManifestCallback
{
    final /* synthetic */ OfflinePlaybackSession this$0;
    
    OfflinePlaybackSession$1(final OfflinePlaybackSession this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onManifestResponse(final long n, final OfflinePlaybackInterface$OfflineManifest offlinePlaybackInterface$OfflineManifest, final Status status) {
        Log.d("OfflinePlayback_Session", "onManifestResponse movieId=" + n + " offlineManifest=" + offlinePlaybackInterface$OfflineManifest);
        if (status.isSucces()) {
            this.this$0.handleManifest(offlinePlaybackInterface$OfflineManifest);
            return;
        }
        Log.d("OfflinePlayback_Session", "onManifestResponse error =" + status);
        this.this$0.reportStartPlay(OfflinePlaybackState.MANIFEST_FETCH.toString(), "OfflinePlayback.ManifestRequestFailure", status.toString());
        this.this$0.mCallback.handleError(new ExoPlaybackError(ExoPlaybackError$ExoPlaybackErrorCode.MANIFEST_FAILURE, "onManifestResponse failed " + status, OfflinePlaybackState.MANIFEST_FETCH.toString(), null));
    }
}
