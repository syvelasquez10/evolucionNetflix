// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player;

import android.view.SurfaceHolder;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleFailure;
import com.netflix.mediaclient.event.nrdp.media.SubtitleUrl;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.ui.bandwidthsetting.BandwidthSaving;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleProfile;
import com.netflix.mediaclient.media.Subtitle;
import java.nio.ByteBuffer;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.media.AudioSubtitleDefaultOrderInfo;
import java.util.concurrent.Executors;
import com.netflix.mediaclient.media.MediaPlayerHelperFactory;
import com.netflix.mediaclient.javabridge.ui.EventListener;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.content.Intent;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.media.JPlayer2Helper;
import android.media.AudioManager;
import com.netflix.mediaclient.service.configuration.PlayerTypeFactory;
import com.netflix.mediaclient.media.PlayoutMetadata;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.IPlayer$PlayerListener;
import com.netflix.mediaclient.javabridge.ui.IMedia$MediaEventEnum;
import com.netflix.mediaclient.event.nrdp.media.NccpActionId;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.event.nrdp.media.Warning;
import com.netflix.mediaclient.event.nrdp.media.Error;
import com.netflix.mediaclient.event.nrdp.media.BufferRange;
import com.netflix.mediaclient.event.nrdp.media.Statechanged;
import com.netflix.mediaclient.event.nrdp.media.AudioTrackChanged;
import com.netflix.mediaclient.event.nrdp.media.SubtitleData;
import com.netflix.mediaclient.event.nrdp.media.Buffering;
import com.netflix.mediaclient.event.nrdp.media.GenericMediaEvent;
import com.netflix.mediaclient.service.user.UserAgentWebCallback;
import android.os.PowerManager$WakeLock;
import android.content.BroadcastReceiver;
import java.util.Timer;
import android.view.Surface;
import com.netflix.mediaclient.service.player.subtitles.SubtitleDownloadManager;
import com.netflix.mediaclient.service.configuration.SubtitleConfiguration;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.servicemgr.IPlayerFileCache;
import java.util.concurrent.ExecutorService;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.javabridge.ui.Nrdp;
import com.netflix.mediaclient.javabridge.ui.IMedia;
import com.netflix.mediaclient.servicemgr.IManifestCache;
import com.netflix.mediaclient.media.JPlayer.JPlayer$JplayerListener;
import com.netflix.mediaclient.media.MediaPlayerHelper;
import com.netflix.mediaclient.media.BifManager;
import com.netflix.mediaclient.media.bitrate.AudioBitrateRange;
import com.netflix.mediaclient.servicemgr.IPlayer;
import com.netflix.mediaclient.service.configuration.ConfigurationAgent$ConfigAgentListener;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.javabridge.invoke.media.AuthorizationParams$NetType;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.Log;
import java.util.TimerTask;
import com.netflix.mediaclient.event.nrdp.media.NccpError;

class PlayerAgent$1 implements Runnable
{
    final /* synthetic */ PlayerAgent this$0;
    
    PlayerAgent$1(final PlayerAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        while (true) {
            Label_0532: {
                while (true) {
                    synchronized (this.this$0) {
                        this.this$0.mMedia.reset();
                        this.this$0.prevEndPosition = -1;
                        this.this$0.validPtsRecieved = false;
                        this.this$0.mInPlayback = false;
                        this.this$0.inPlaybackSession = false;
                        this.this$0.splashScreenRemoved = false;
                        this.this$0.preparedCompleted = false;
                        this.this$0.seekedToPosition = (int)(Object)Long.valueOf(this.this$0.mBookmark);
                        this.this$0.mBufferingCompleted = false;
                        this.this$0.pendingError = null;
                        if (this.this$0.mTimer != null) {
                            this.this$0.mStartPlayTimeoutTask = new PlayerAgent$StartPlayTimeoutTask(this.this$0);
                            this.this$0.mTimer.schedule(this.this$0.mStartPlayTimeoutTask, 30000L);
                        }
                        if (Log.isLoggable()) {
                            Log.d(PlayerAgent.TAG, "Player state is " + this.this$0.mState);
                        }
                        if (this.this$0.mState != 4 && this.this$0.mState != -1) {
                            break Label_0532;
                        }
                        Log.d(PlayerAgent.TAG, "Player state was CLOSED or CREATED, cancel timeout task!");
                        this.this$0.mState = 5;
                        if (this.this$0.mStartPlayTimeoutTask != null) {
                            final boolean cancel = this.this$0.mStartPlayTimeoutTask.cancel();
                            this.this$0.mStartPlayTimeoutTask = null;
                            if (Log.isLoggable()) {
                                Log.d(PlayerAgent.TAG, "Task was canceled " + cancel);
                            }
                        }
                        else {
                            Log.w(PlayerAgent.TAG, "Timer task was null!!!");
                        }
                        if (this.this$0.mTimer != null) {
                            final int purge = this.this$0.mTimer.purge();
                            if (Log.isLoggable()) {
                                Log.d(PlayerAgent.TAG, "Canceled tasks: " + purge);
                            }
                            this.this$0.reloadPlayer();
                            final AuthorizationParams$NetType currentNetType = ConnectivityUtils.getCurrentNetType(this.this$0.getContext());
                            this.this$0.mMedia.setStreamingQoe(this.this$0.getConfigurationAgent().getStreamingQoe(), this.this$0.getConfigurationAgent().enableHTTPSAuth(), this.this$0.isMPPlayerType());
                            this.this$0.mMedia.open(this.this$0.mMovieId, this.this$0.mPlayContext, currentNetType, this.this$0.mBookmark);
                            this.this$0.toOpenAfterClose = false;
                            this.this$0.getConfigurationAgent().getDeviceCategory().getValue();
                            this.this$0.sessionInitRxBytes = ConnectivityUtils.getApplicationRx();
                            this.this$0.sessionInitTxBytes = ConnectivityUtils.getApplicationTx();
                            return;
                        }
                    }
                    Log.w(PlayerAgent.TAG, "Timer was null!!!");
                    continue;
                }
            }
            this.this$0.toOpenAfterClose = true;
            Log.d(PlayerAgent.TAG, "invokeMethod(open) has to wait...");
        }
    }
}
