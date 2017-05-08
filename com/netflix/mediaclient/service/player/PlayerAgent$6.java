// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player;

import android.view.SurfaceHolder;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleFailure;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleProfile;
import android.graphics.Point;
import java.nio.ByteBuffer;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.media.AudioSubtitleDefaultOrderInfo;
import java.util.concurrent.Executors;
import com.netflix.mediaclient.media.MediaPlayerHelperFactory;
import com.netflix.mediaclient.javabridge.ui.EventListener;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import java.util.TimerTask;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.util.Coppola1Utils;
import com.netflix.mediaclient.event.nrdp.media.SubtitleUrl;
import com.netflix.mediaclient.service.player.subtitles.SubtitleParser;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.media.JPlayer2Helper;
import com.netflix.mediaclient.service.preapp.PreAppAgentDataHandler;
import com.netflix.mediaclient.service.configuration.PlayerTypeFactory;
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
import com.netflix.mediaclient.event.nrdp.media.Warning;
import com.netflix.mediaclient.event.nrdp.media.Error;
import com.netflix.mediaclient.event.nrdp.media.BufferRange;
import com.netflix.mediaclient.event.nrdp.media.Statechanged;
import com.netflix.mediaclient.event.nrdp.media.AudioTrackChanged;
import com.netflix.mediaclient.event.nrdp.media.SubtitleData;
import com.netflix.mediaclient.event.nrdp.media.Buffering;
import com.netflix.mediaclient.event.nrdp.media.OpenComplete;
import com.netflix.mediaclient.event.nrdp.media.GenericMediaEvent;
import com.netflix.mediaclient.service.user.UserAgentWebCallback;
import android.os.PowerManager$WakeLock;
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
import com.netflix.mediaclient.media.BifManager;
import com.netflix.mediaclient.media.bitrate.AudioBitrateRange;
import com.netflix.mediaclient.event.nrdp.media.NccpError;
import com.netflix.mediaclient.servicemgr.IPlayer;
import com.netflix.mediaclient.service.configuration.ConfigurationAgent$ConfigAgentListener;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class PlayerAgent$6 extends BroadcastReceiver
{
    final /* synthetic */ PlayerAgent this$0;
    
    PlayerAgent$6(final PlayerAgent this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (Log.isLoggable()) {
            Log.v(PlayerAgent.TAG, "Received intent " + intent);
        }
        final String action = intent.getAction();
        if ("com.netflix.mediaclient.intent.action.PLAYER_SUBTITLE_CONFIG_CHANGED".equals(action)) {
            Log.d(PlayerAgent.TAG, "subtitle configuration is changed");
            this.this$0.updateSubtitleSettingsFromQaLocalOverride(intent.getIntExtra("lookupType", -1));
        }
        else if (Log.isLoggable()) {
            Log.d(PlayerAgent.TAG, "We do not support action " + action);
        }
    }
}
