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
import com.netflix.mediaclient.util.ConnectivityUtils;
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
import com.netflix.mediaclient.javabridge.invoke.media.Open$NetType;
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
import com.netflix.mediaclient.event.nrdp.media.NccpError;
import com.netflix.mediaclient.servicemgr.IPlayer;
import com.netflix.mediaclient.service.configuration.ConfigurationAgent$ConfigAgentListener;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.Log;

class PlayerAgent$3 implements Runnable
{
    final /* synthetic */ PlayerAgent this$0;
    
    PlayerAgent$3(final PlayerAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        while (true) {
            while (true) {
                int n;
                synchronized (this.this$0) {
                    this.this$0.prevEndPosition = this.this$0.getCurrentPositionMs();
                    this.this$0.validPtsRecieved = false;
                    this.this$0.seeking = true;
                    this.this$0.mInPlayback = false;
                    final int duration = this.this$0.getDuration();
                    final int access$800 = this.this$0.seekedToPosition;
                    if (this.this$0.seekedToPosition + 10000 >= duration && duration > 0) {
                        Log.d(PlayerAgent.TAG, "seek to close to EOS, defaulting to 10 seconss before EOS.");
                        n = duration - 10000;
                    }
                    else {
                        n = access$800;
                        if (Log.isLoggable(PlayerAgent.TAG, 3)) {
                            Log.d(PlayerAgent.TAG, "seek to position " + this.this$0.seekedToPosition + ", duration " + duration);
                            n = access$800;
                        }
                    }
                    if (this.this$0.mFuzz != 0) {
                        this.this$0.mMedia.swim(this.this$0.mRelativeSeekPosition, false, this.this$0.mFuzz, true);
                        this.this$0.seekedToPosition = n;
                        this.this$0.mBufferingCompleted = false;
                        return;
                    }
                }
                this.this$0.mMedia.seekTo(n, this.this$0.mForcedRebuffer);
                continue;
            }
        }
    }
}
