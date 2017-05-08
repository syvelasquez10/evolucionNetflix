// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.exoplayback;

import com.netflix.mediaclient.media.PlayoutMetadata;
import java.nio.ByteBuffer;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.Logblob;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface$ManifestCallback;
import com.netflix.mediaclient.service.player.exoplayback.logblob.StateChanged;
import com.netflix.mediaclient.service.player.exoplayback.logblob.StartPlay;
import com.netflix.mediaclient.service.player.exoplayback.logblob.ResumePlay;
import org.json.JSONException;
import com.netflix.mediaclient.service.logging.logblob.BaseLogblob;
import com.netflix.mediaclient.service.player.exoplayback.logblob.EndPlay;
import com.netflix.mediaclient.javabridge.ui.LogArguments$LogLevel;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.google.android.exoplayer.dash.mpd.MediaPresentationDescription;
import com.google.android.exoplayer.drm.DrmSessionManager;
import com.netflix.mediaclient.media.manifest.Stream;
import org.json.JSONObject;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface$OfflineManifest;
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

class OfflinePlaybackSession$2 implements Runnable
{
    final /* synthetic */ OfflinePlaybackSession this$0;
    
    OfflinePlaybackSession$2(final OfflinePlaybackSession this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        final long currentPosition = this.this$0.getCurrentPosition();
        if (currentPosition > this.this$0.mPlaybackTS) {
            this.this$0.mPlayTracker.updatePlayTime((int)(currentPosition - this.this$0.mPlaybackTS));
        }
        this.this$0.mPlaybackTS = currentPosition;
        this.this$0.mCallback.handleUpdatePts((int)currentPosition);
        this.this$0.handleSubtitleUpdate((int)currentPosition);
        this.this$0.mOfflineAgent.notifyPlayProgress(this.this$0.mMovieId, currentPosition);
        if (Log.isLoggable()) {
            Log.d("OfflinePlayback_Session", "TotalTime: updatePts " + currentPosition + " mMovieTotal: " + this.this$0.mPlayTracker.getMovieTotalInMs());
        }
        this.this$0.mPdsPlaySession.notifyPlayProgress(currentPosition);
        if (this.this$0.isPlaying()) {
            this.this$0.mMainHandler.postDelayed((Runnable)this, 1000L);
        }
    }
}
