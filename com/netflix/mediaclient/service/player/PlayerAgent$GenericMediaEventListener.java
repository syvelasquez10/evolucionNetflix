// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player;

import android.view.SurfaceHolder;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleFailure;
import com.netflix.mediaclient.media.JPlayer.AdaptiveMediaDecoderHelper;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleProfile;
import android.graphics.Point;
import java.nio.ByteBuffer;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.media.AudioSubtitleDefaultOrderInfo;
import java.util.concurrent.Executors;
import com.netflix.mediaclient.media.MediaPlayerHelperFactory;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import java.util.TimerTask;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.util.Coppola1Utils;
import com.netflix.mediaclient.event.nrdp.media.SubtitleUrl;
import com.netflix.mediaclient.service.player.subtitles.SubtitleParser;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.media.Subtitle;
import android.content.Intent;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.media.JPlayer2Helper;
import com.netflix.mediaclient.service.preapp.PreAppAgentDataHandler;
import com.netflix.mediaclient.service.configuration.PlayerTypeFactory;
import android.content.Context;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.IPlayer$PlayerListener;
import com.netflix.mediaclient.javabridge.ui.IMedia$MediaEventEnum;
import com.netflix.mediaclient.event.nrdp.media.NccpActionId;
import android.annotation.SuppressLint;
import android.media.AudioDeviceInfo;
import com.netflix.mediaclient.util.AndroidUtils;
import android.media.AudioManager;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.media.PlayoutMetadata;
import com.netflix.mediaclient.ui.bandwidthsetting.BandwidthUtility;
import com.netflix.mediaclient.service.user.UserAgentWebCallback;
import android.telephony.TelephonyManager;
import android.os.PowerManager$WakeLock;
import android.content.BroadcastReceiver;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.Timer;
import android.view.Surface;
import com.netflix.mediaclient.service.player.subtitles.SubtitleDownloadManager;
import com.netflix.mediaclient.service.configuration.SubtitleConfiguration;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.servicemgr.IPlayerFileCache;
import java.util.concurrent.ExecutorService;
import com.netflix.mediaclient.util.PlaybackVolumeMetric;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.javabridge.ui.Nrdp;
import com.netflix.mediaclient.javabridge.ui.IMedia;
import com.netflix.mediaclient.servicemgr.IManifestCache;
import com.netflix.mediaclient.media.JPlayer.JPlayer$JplayerListener;
import com.netflix.mediaclient.media.MediaPlayerHelper;
import com.netflix.mediaclient.ui.bandwidthsetting.BandwidthDelayedBifDownload;
import android.telephony.PhoneStateListener;
import com.netflix.mediaclient.media.BifManager;
import com.netflix.mediaclient.media.bitrate.AudioBitrateRange;
import com.netflix.mediaclient.servicemgr.IPlayer;
import com.netflix.mediaclient.service.configuration.ConfigurationAgent$ConfigAgentListener;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.event.nrdp.media.Warning;
import com.netflix.mediaclient.event.nrdp.media.Error;
import com.netflix.mediaclient.event.nrdp.media.UpdatePts;
import com.netflix.mediaclient.event.nrdp.media.BufferRange;
import com.netflix.mediaclient.event.nrdp.media.Statechanged;
import com.netflix.mediaclient.event.nrdp.media.AudioTrackChanged;
import com.netflix.mediaclient.event.nrdp.media.SubtitleData;
import com.netflix.mediaclient.event.nrdp.media.ShowSubtitle;
import com.netflix.mediaclient.event.nrdp.media.RemoveSubtitle;
import com.netflix.mediaclient.event.nrdp.media.Buffering;
import com.netflix.mediaclient.event.nrdp.media.OpenComplete;
import com.netflix.mediaclient.event.nrdp.media.NccpError;
import com.netflix.mediaclient.event.nrdp.media.GenericMediaEvent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.event.UIEvent;
import com.netflix.mediaclient.javabridge.ui.EventListener;

class PlayerAgent$GenericMediaEventListener implements EventListener
{
    final /* synthetic */ PlayerAgent this$0;
    
    private PlayerAgent$GenericMediaEventListener(final PlayerAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void received(final UIEvent uiEvent) {
        Log.d(PlayerAgent.TAG, "Received a media event ");
        if (uiEvent instanceof GenericMediaEvent) {
            this.this$0.handleGenericMediaEvent((GenericMediaEvent)uiEvent);
            return;
        }
        if (uiEvent instanceof NccpError) {
            this.this$0.handleError((NccpError)uiEvent);
            return;
        }
        if (uiEvent instanceof OpenComplete) {
            this.this$0.handlePrepare((OpenComplete)uiEvent);
            return;
        }
        if (uiEvent instanceof GenericMediaEvent) {
            this.this$0.handleGenericMediaEvent((GenericMediaEvent)uiEvent);
            return;
        }
        if (uiEvent instanceof Buffering) {
            this.this$0.handleBufferring((Buffering)uiEvent);
            return;
        }
        if (uiEvent instanceof RemoveSubtitle) {
            Log.w(PlayerAgent.TAG, "RemoveSubtitle NOT supported since 4.0");
            return;
        }
        if (uiEvent instanceof ShowSubtitle) {
            Log.e(PlayerAgent.TAG, "ShowSubtitle NOT supported since 4.0");
            return;
        }
        if (uiEvent instanceof SubtitleData) {
            this.this$0.handleSubtitleData((SubtitleData)uiEvent);
            return;
        }
        if (uiEvent instanceof AudioTrackChanged) {
            this.this$0.handleAudioTrackChanged((AudioTrackChanged)uiEvent);
            return;
        }
        if (uiEvent instanceof Statechanged) {
            this.this$0.handleStatechanged((Statechanged)uiEvent);
            return;
        }
        if (uiEvent instanceof BufferRange) {
            this.this$0.handleBufferRange((BufferRange)uiEvent);
            return;
        }
        if (uiEvent instanceof UpdatePts) {
            this.this$0.handleUpdatePts(((UpdatePts)uiEvent).getPts());
            return;
        }
        if (uiEvent instanceof Error) {
            this.this$0.handleMediaError((Error)uiEvent);
            return;
        }
        if (uiEvent instanceof Warning) {
            this.this$0.handleMediaWarning((Warning)uiEvent);
            return;
        }
        Log.e(PlayerAgent.TAG, "Uknown event: " + uiEvent.getType());
    }
}
