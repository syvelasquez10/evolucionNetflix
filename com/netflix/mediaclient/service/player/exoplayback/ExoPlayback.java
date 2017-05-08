// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.exoplayback;

import android.view.SurfaceHolder;
import android.view.Surface;
import com.netflix.mediaclient.media.JPlayer.JPlayer$JplayerListener;
import com.netflix.mediaclient.servicemgr.ISubtitleDef$SubtitleFailure;
import com.netflix.mediaclient.media.SubtitleUrl;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.service.player.subtitles.SubtitleScreen;
import com.netflix.mediaclient.servicemgr.ISubtitleDef$SubtitleProfile;
import com.netflix.mediaclient.media.PlayoutMetadata;
import com.netflix.mediaclient.servicemgr.IManifestCache;
import android.graphics.Point;
import com.netflix.mediaclient.media.Subtitle;
import java.nio.ByteBuffer;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.media.AudioSubtitleDefaultOrderInfo;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.player.PlayerFileManager;
import com.netflix.mediaclient.Log;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.IPlayer$PlayerListener;
import com.netflix.mediaclient.service.player.PlayerListenerManager$PlayerListenerHandler;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.service.configuration.ConfigurationAgent;
import com.netflix.mediaclient.service.offline.subtitles.SubtitleOfflineManager;
import com.netflix.mediaclient.service.configuration.SubtitleConfiguration;
import com.netflix.mediaclient.service.player.PlayerListenerManager;
import com.netflix.mediaclient.servicemgr.IPlayerFileCache;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface;
import android.os.Handler;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.IPlayer;
import com.netflix.mediaclient.service.configuration.ConfigurationAgent$ConfigAgentListener;
import com.netflix.mediaclient.service.ServiceAgent;

public class ExoPlayback extends ServiceAgent implements ConfigurationAgent$ConfigAgentListener, IPlaybackSession$PlaybackSessionCallback, IPlayer
{
    private static final String TAG = "OfflinePlayback";
    private Context mContext;
    private IClientLogging mLoggingAgent;
    private Handler mMainHanlder;
    private OfflinePlaybackInterface mOfflineAgent;
    private IPlaybackSession mOfflineSession;
    private IPlayerFileCache mPlayerFileManager;
    PlayerListenerManager mPlayerListenerManager;
    private SubtitleConfiguration mSubtitleConfiguration;
    private SubtitleOfflineManager mSubtitles;
    
    public ExoPlayback(final Context mContext, final Handler mMainHanlder, final OfflinePlaybackInterface mOfflineAgent, final IClientLogging mLoggingAgent) {
        this.mPlayerListenerManager = new PlayerListenerManager();
        this.mContext = mContext;
        this.mMainHanlder = mMainHanlder;
        this.mOfflineAgent = mOfflineAgent;
        this.mLoggingAgent = mLoggingAgent;
    }
    
    private void addConfigurationChangeListener() {
        final ServiceAgent$ConfigurationAgentInterface configurationAgent = this.getConfigurationAgent();
        if (configurationAgent instanceof ConfigurationAgent) {
            ((ConfigurationAgent)configurationAgent).addConfigAgentListener(this);
        }
    }
    
    private SubtitleConfiguration findSubtitleConfiguration() {
        SubtitleConfiguration subtitleConfiguration = null;
        final ServiceAgent$ConfigurationAgentInterface configurationAgent = this.getConfigurationAgent();
        if (configurationAgent != null) {
            subtitleConfiguration = configurationAgent.getSubtitleConfiguration();
        }
        SubtitleConfiguration default1;
        if ((default1 = subtitleConfiguration) == null) {
            default1 = SubtitleConfiguration.DEFAULT;
        }
        return default1;
    }
    
    private void handlePlayerListener(final PlayerListenerManager$PlayerListenerHandler playerListenerManager$PlayerListenerHandler, final Object... array) {
        synchronized (this.mPlayerListenerManager) {
            for (final IPlayer$PlayerListener player$PlayerListener : this.mPlayerListenerManager.getListeners()) {
                if (player$PlayerListener != null && player$PlayerListener.isListening()) {
                    this.runInMainThread(new ExoPlayback$1(this, playerListenerManager$PlayerListenerHandler, player$PlayerListener, array));
                }
            }
        }
    }
    // monitorexit(playerListenerManager)
    
    private void runInMainThread(final Runnable runnable) {
        this.mMainHanlder.post(runnable);
    }
    
    private void updateSubtitleSettings(final boolean b) {
        final SubtitleConfiguration subtitleConfiguration = this.findSubtitleConfiguration();
        if (Log.isLoggable()) {
            Log.d("OfflinePlayback", "Subtitle configuration was " + this.mSubtitleConfiguration);
            Log.d("OfflinePlayback", "Sets subtitle configuration to " + subtitleConfiguration);
        }
        if (this.mSubtitleConfiguration == subtitleConfiguration && !b) {
            if (Log.isLoggable()) {
                Log.d("OfflinePlayback", "Already used subtitle configuration, do nothing ");
            }
            return;
        }
        if (b) {
            Log.d("OfflinePlayback", "Forced set of subtitle configuration");
        }
        this.mSubtitleConfiguration = subtitleConfiguration;
    }
    
    @Override
    public void addPlayerListener(final IPlayer$PlayerListener player$PlayerListener) {
        this.mPlayerListenerManager.addPlayerListener(player$PlayerListener);
    }
    
    @Override
    public boolean canUpdatePosition(final int n) {
        return this.isPlaying();
    }
    
    @Override
    public void close() {
        if (this.mOfflineSession != null) {
            this.mOfflineSession.stop();
            this.mOfflineSession.release();
        }
        this.mOfflineSession = null;
    }
    
    @Override
    protected void doInit() {
        this.addConfigurationChangeListener();
        this.updateSubtitleSettings(true);
        this.mPlayerFileManager = new PlayerFileManager(this.getContext());
        this.mSubtitles = new SubtitleOfflineManager(this.getUserAgent(), this);
        this.initCompleted(CommonStatus.OK);
    }
    
    @Override
    public AudioSubtitleDefaultOrderInfo[] getAudioSubtitleDefaultOrderInfo() {
        if (this.mOfflineSession != null) {
            return this.mOfflineSession.getAudioSubtitleDefaultOrderInfo();
        }
        return new AudioSubtitleDefaultOrderInfo[0];
    }
    
    @Override
    public AudioSource[] getAudioTrackList() {
        if (this.mOfflineSession != null) {
            return this.mOfflineSession.getAudioTrackList();
        }
        return new AudioSource[0];
    }
    
    @Override
    public ByteBuffer getBifFrame(final int n) {
        if (this.mOfflineSession != null) {
            return this.mOfflineSession.getBifFrame(n);
        }
        return null;
    }
    
    @Override
    public AudioSource getCurrentAudioTrack() {
        if (this.mOfflineSession != null) {
            return this.mOfflineSession.getCurrentAudioTrack();
        }
        return null;
    }
    
    @Override
    public long getCurrentPlayableId() {
        if (this.mOfflineSession != null) {
            return this.mOfflineSession.getCurrentPlayableId();
        }
        return 0L;
    }
    
    @Override
    public int getCurrentPositionMs() {
        if (this.mOfflineSession != null) {
            return (int)this.mOfflineSession.getCurrentPosition();
        }
        return 0;
    }
    
    @Override
    public int getCurrentProgress() {
        return this.getCurrentPositionMs();
    }
    
    @Override
    public Subtitle getCurrentSubtitleTrack() {
        if (this.mOfflineSession != null) {
            return this.mOfflineSession.getCurrentSubtitleTrack();
        }
        return null;
    }
    
    @Override
    public Point getDisplayAspectRatioDimension() {
        if (this.mOfflineSession != null) {
            return new Point((int)this.mOfflineSession.getWidthHeight().first, (int)this.mOfflineSession.getWidthHeight().second);
        }
        return null;
    }
    
    @Override
    public int getDuration() {
        if (this.mOfflineSession != null) {
            return (int)this.mOfflineSession.getDuration();
        }
        return 0;
    }
    
    @Override
    public IManifestCache getManifestCache() {
        return null;
    }
    
    @Override
    public IPlayerFileCache getPlayerFileCache() {
        return this.mPlayerFileManager;
    }
    
    @Override
    public PlayoutMetadata getPlayoutMetadata() {
        if (this.mOfflineSession != null) {
            return this.mOfflineSession.getPlayoutMetadata();
        }
        return null;
    }
    
    @Override
    public SubtitleConfiguration getSubtitleConfiguration() {
        return null;
    }
    
    @Override
    public ISubtitleDef$SubtitleProfile getSubtitleProfileFromMetadata() {
        final SubtitleOfflineManager mSubtitles = this.mSubtitles;
        if (mSubtitles != null && mSubtitles.getSubtitleParser() != null) {
            return mSubtitles.getSubtitleParser().getSubtitleProfile();
        }
        return null;
    }
    
    @Override
    public Subtitle[] getSubtitleTrackList() {
        if (this.mOfflineSession != null) {
            return this.mOfflineSession.getSubtitleTrackList();
        }
        return new Subtitle[0];
    }
    
    @Override
    public int getVideoHeight() {
        if (this.mOfflineSession != null) {
            return (int)this.mOfflineSession.getWidthHeight().second;
        }
        return 0;
    }
    
    @Override
    public int getVideoWidth() {
        if (this.mOfflineSession != null) {
            return (int)this.mOfflineSession.getWidthHeight().first;
        }
        return 0;
    }
    
    @Override
    public String getXid() {
        if (this.mOfflineSession != null) {
            return this.mOfflineSession.getSessionId();
        }
        return null;
    }
    
    @Override
    public void handleError(final ExoPlaybackError exoPlaybackError) {
        this.handlePlayerListener(this.mPlayerListenerManager.getPlayerListenerOnPlaybackErrorHandler(), exoPlaybackError);
    }
    
    @Override
    public void handlePlaying() {
        this.handlePlayerListener(this.mPlayerListenerManager.getPlayerListenerOnPlayingHandler(), new Object[0]);
    }
    
    @Override
    public void handlePrepared() {
        this.handlePlayerListener(this.mPlayerListenerManager.getPlayerListenerPrepareHandler(), new Object[0]);
    }
    
    @Override
    public void handleStarted() {
        this.handlePlayerListener(this.mPlayerListenerManager.getPlayerListenerOnStartedHandler(), new Object[0]);
    }
    
    @Override
    public void handleStopped() {
        this.handlePlayerListener(this.mPlayerListenerManager.getPlayerListenerOnCompletionHandler(), new Object[0]);
    }
    
    @Override
    public void handleSubtitleUpdate(final SubtitleScreen subtitleScreen) {
        this.handlePlayerListener(this.mPlayerListenerManager.getPlayerListenerOnSubtitleChangeHandler(), subtitleScreen);
    }
    
    @Override
    public void handleUpdatePts(final int n) {
        this.handlePlayerListener(this.mPlayerListenerManager.getPlayerListenerOnUpdatePtsHandler(), n);
    }
    
    @Override
    public boolean isBufferingCompleted() {
        return this.isPlaying();
    }
    
    @Override
    public boolean isManifestCacheEnabled() {
        return false;
    }
    
    @Override
    public boolean isPlaying() {
        return this.mOfflineSession != null && this.mOfflineSession.isPlaying();
    }
    
    @Override
    public void onConfigRefreshed(final Status status) {
        if (status.isSucces()) {
            this.updateSubtitleSettings(false);
        }
    }
    
    @Override
    public void open(final long n, final PlayContext playContext, final long n2) {
        Log.d("OfflinePlayback", "open() movieId=" + n);
        if (this.mOfflineSession != null) {
            this.close();
        }
        this.mOfflineSession = new OfflinePlaybackSession(this.mContext, this.mMainHanlder, this, this.mOfflineAgent, this.mLoggingAgent, this.getPdsAgentForPlay(), this.mSubtitles, n, n2, playContext);
    }
    
    @Override
    public void pause() {
        if (this.mOfflineSession != null) {
            this.mOfflineSession.pause();
        }
    }
    
    @Override
    public void play() {
        if (this.mOfflineSession != null) {
            this.mOfflineSession.play();
        }
    }
    
    @Override
    public void removePlayerListener(final IPlayer$PlayerListener player$PlayerListener) {
        this.mPlayerListenerManager.removePlayerListener(player$PlayerListener);
    }
    
    @Override
    public void reportFailedSubtitle(final String s, final SubtitleUrl subtitleUrl, final ISubtitleDef$SubtitleFailure subtitleDef$SubtitleFailure, final boolean b, final Status status, final String[] array) {
    }
    
    @Override
    public void reportSubtitleQoe(final String s, final int n, final int n2) {
    }
    
    @Override
    public void seekTo(final int n, final boolean b) {
        if (this.mOfflineSession != null) {
            this.mOfflineSession.seekTo(n);
        }
    }
    
    @Override
    public void seekWithFuzzRange(final int n, final int n2) {
        if (this.mOfflineSession != null) {
            this.mOfflineSession.seekTo(this.mOfflineSession.getCurrentPosition());
        }
    }
    
    @Override
    public boolean selectTracks(final AudioSource audioSource, final Subtitle subtitle, final boolean b) {
        synchronized (this) {
            return this.mOfflineSession != null && this.mOfflineSession.selectTracks(audioSource, subtitle, b);
        }
    }
    
    @Override
    public void setAudioDuck(final boolean audioDuck) {
        if (this.mOfflineSession != null) {
            this.mOfflineSession.setAudioDuck(audioDuck);
        }
    }
    
    @Override
    public void setJPlayerListener(final JPlayer$JplayerListener player$JplayerListener) {
    }
    
    @Override
    public void setSurface(final Surface surface) {
        if (this.mOfflineSession != null) {
            this.mOfflineSession.setSurface(surface);
        }
    }
    
    @Override
    public void setSurfaceHolder(final SurfaceHolder surfaceHolder) {
    }
    
    @Override
    public void setVideoBitrateRange(final int n, final int n2) {
    }
    
    @Override
    public void unpause() {
        if (this.mOfflineSession != null) {
            this.mOfflineSession.play();
        }
    }
}
