// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player;

import android.view.SurfaceHolder;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleProfile;
import com.netflix.mediaclient.media.Subtitle;
import java.nio.ByteBuffer;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.media.AudioSubtitleDefaultOrderInfo;
import java.util.concurrent.Executors;
import com.netflix.mediaclient.media.MediaPlayerHelperFactory;
import com.netflix.mediaclient.service.configuration.PlayerTypeFactory;
import com.netflix.mediaclient.javabridge.ui.EventListener;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import com.netflix.mediaclient.media.JPlayer2Helper;
import android.media.AudioManager;
import com.netflix.mediaclient.media.PlayoutMetadata;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleOutputMode;
import com.netflix.mediaclient.android.app.BackgroundTask;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.IPlayer$PlayerListener;
import com.netflix.mediaclient.javabridge.ui.IMedia$MediaEventEnum;
import com.netflix.mediaclient.event.nrdp.media.NccpActionId;
import android.net.NetworkInfo;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.event.nrdp.media.Warning;
import com.netflix.mediaclient.event.nrdp.media.Error;
import com.netflix.mediaclient.event.nrdp.media.BufferRange;
import com.netflix.mediaclient.event.nrdp.media.Statechanged;
import com.netflix.mediaclient.event.nrdp.media.AudioTrackChanged;
import com.netflix.mediaclient.event.nrdp.media.SubtitleData;
import com.netflix.mediaclient.event.nrdp.media.ShowSubtitle;
import com.netflix.mediaclient.event.nrdp.media.RemoveSubtitle;
import com.netflix.mediaclient.event.nrdp.media.Buffering;
import com.netflix.mediaclient.event.nrdp.media.GenericMediaEvent;
import android.content.Context;
import com.netflix.mediaclient.service.user.UserAgentWebCallback;
import android.content.BroadcastReceiver;
import android.os.PowerManager$WakeLock;
import java.util.Timer;
import android.view.Surface;
import com.netflix.mediaclient.service.player.subtitles.SubtitleParser;
import com.netflix.mediaclient.service.configuration.SubtitleConfiguration;
import com.netflix.mediaclient.media.PlayerType;
import java.util.concurrent.ExecutorService;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.javabridge.ui.Nrdp;
import com.netflix.mediaclient.javabridge.ui.IMedia;
import com.netflix.mediaclient.media.JPlayer.JPlayer$JplayerListener;
import com.netflix.mediaclient.media.MediaPlayerHelper;
import com.netflix.mediaclient.media.BifManager;
import com.netflix.mediaclient.media.bitrate.AudioBitrateRange;
import com.netflix.mediaclient.servicemgr.IPlayer;
import com.netflix.mediaclient.service.configuration.ConfigurationAgent$ConfigAgentListener;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.javabridge.invoke.media.Open$NetType;
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
    Label_0506_Outer:
        while (true) {
            Label_0596: {
                while (true) {
                Label_0571:
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
                            if (Log.isLoggable(PlayerAgent.TAG, 3)) {
                                Log.d(PlayerAgent.TAG, "Player state is " + this.this$0.mState);
                            }
                            if (this.this$0.mState != 4 && this.this$0.mState != -1) {
                                break Label_0596;
                            }
                            Log.d(PlayerAgent.TAG, "Player state was CLOSED or CREATED, cancel timeout task!");
                            this.this$0.mState = 5;
                            if (this.this$0.mStartPlayTimeoutTask != null) {
                                final boolean cancel = this.this$0.mStartPlayTimeoutTask.cancel();
                                if (Log.isLoggable(PlayerAgent.TAG, 3)) {
                                    Log.d(PlayerAgent.TAG, "Task was canceled " + cancel);
                                }
                            }
                            else {
                                Log.w(PlayerAgent.TAG, "Timer task was null!!!");
                            }
                            if (this.this$0.mTimer != null) {
                                final int purge = this.this$0.mTimer.purge();
                                if (Log.isLoggable(PlayerAgent.TAG, 3)) {
                                    Log.d(PlayerAgent.TAG, "Canceled tasks: " + purge);
                                }
                                this.this$0.reloadPlayer();
                                this.this$0.mMedia.setStreamingQoe(this.this$0.getConfigurationAgent().getStreamingQoe(), this.this$0.getConfigurationAgent().enableHTTPSAuth());
                                this.this$0.mMedia.open(this.this$0.mMovieId, this.this$0.mPlayContext, this.this$0.getCurrentNetType());
                                this.this$0.toOpenAfterClose = false;
                                final String value = this.this$0.getConfigurationAgent().getDeviceCategory().getValue();
                                if (Open$NetType.wifi.equals(this.this$0.getCurrentNetType())) {
                                    Log.i(PlayerAgent.TAG, "Setting WifiApInfo");
                                    this.this$0.mMedia.setWifiApsInfo(this.this$0.getContext(), value, true);
                                    this.this$0.sessionInitRxBytes = ConnectivityUtils.getApplicationRx();
                                    this.this$0.sessionInitTxBytes = ConnectivityUtils.getApplicationTx();
                                    this.this$0.ptsTicker = -1;
                                    return;
                                }
                                break Label_0571;
                            }
                        }
                        Log.w(PlayerAgent.TAG, "Timer was null!!!");
                        continue Label_0506_Outer;
                    }
                    final String s;
                    this.this$0.mMedia.setWifiApsInfo(this.this$0.getContext(), s, false);
                    continue;
                }
            }
            this.this$0.toOpenAfterClose = true;
            Log.d(PlayerAgent.TAG, "invokeMethod(open) has to wait...");
        }
    }
}
