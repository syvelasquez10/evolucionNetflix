// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player;

import com.netflix.mediaclient.javabridge.ui.IMedia;
import com.netflix.mediaclient.service.player.subtitles.SubtitleScreen;
import com.netflix.mediaclient.event.nrdp.media.NccpError;
import com.netflix.mediaclient.event.nrdp.media.Error;
import com.netflix.mediaclient.Log;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.IPlayer;
import java.util.List;

class PlayerListenerManager
{
    private static final String TAG;
    private PlayerAgent mPlayer;
    private PlayerListenerOnAudioChangeHandler mPlayerListenerOnAudioChangeHandler;
    private PlayerListenerOnBandwidthChangeHandler mPlayerListenerOnBandwidthChangeHandler;
    private PlayerListenerOnBufferingUpdateHandler mPlayerListenerOnBufferingUpdateHandler;
    private PlayerListenerOnCompletionHandler mPlayerListenerOnCompletionHandler;
    private PlayerListenerOnMediaErrorHandler mPlayerListenerOnMediaErrorHandler;
    private PlayerListenerOnNccpErrorHandler mPlayerListenerOnNccpErrorHandler;
    private PlayerListenerOnNrdFatalErrorHandler mPlayerListenerOnNrdFatalErrorHandler;
    private PlayerListenerOnPlayingHandler mPlayerListenerOnPlayingHandler;
    private PlayerListenerOnSeekCompleteHandler mPlayerListenerOnSeekCompleteHandler;
    private PlayerListenerOnStalledHandler mPlayerListenerOnStalledHandler;
    private PlayerListenerOnStartedHandler mPlayerListenerOnStartedHandler;
    private PlayerListenerOnSubtitleChangeHandler mPlayerListenerOnSubtitleChangeHandler;
    private PlayerListenerOnSubtitleFailedHandler mPlayerListenerOnSubtitleFailedHandler;
    private PlayerListenerOnSubtitleRemoveHandler mPlayerListenerOnSubtitleRemoveHandler;
    private PlayerListenerOnSubtitleShowHandler mPlayerListenerOnSubtitleShowHandler;
    private PlayerListenerOnUpdatePtsHandler mPlayerListenerOnUpdatePtsHandler;
    private PlayerListenerPlaybackClosedHandler mPlayerListenerPlaybackClosedHandler;
    private PlayerListenerPrepareHandler mPlayerListenerPrepareHandler;
    private PlayerListenerRestartPlaybackHandler mPlayerListenerRestartPlaybackHandler;
    private final List<IPlayer.PlayerListener> mPlayerListeners;
    
    static {
        TAG = PlayerAgent.class.getSimpleName();
    }
    
    PlayerListenerManager(final PlayerAgent mPlayer) {
        this.mPlayerListeners = new ArrayList<IPlayer.PlayerListener>();
        this.mPlayerListenerPrepareHandler = new PlayerListenerPrepareHandler();
        this.mPlayerListenerOnUpdatePtsHandler = new PlayerListenerOnUpdatePtsHandler();
        this.mPlayerListenerOnAudioChangeHandler = new PlayerListenerOnAudioChangeHandler();
        this.mPlayerListenerOnBandwidthChangeHandler = new PlayerListenerOnBandwidthChangeHandler();
        this.mPlayerListenerOnBufferingUpdateHandler = new PlayerListenerOnBufferingUpdateHandler();
        this.mPlayerListenerOnCompletionHandler = new PlayerListenerOnCompletionHandler();
        this.mPlayerListenerOnMediaErrorHandler = new PlayerListenerOnMediaErrorHandler();
        this.mPlayerListenerOnNccpErrorHandler = new PlayerListenerOnNccpErrorHandler();
        this.mPlayerListenerOnNrdFatalErrorHandler = new PlayerListenerOnNrdFatalErrorHandler();
        this.mPlayerListenerOnPlayingHandler = new PlayerListenerOnPlayingHandler();
        this.mPlayerListenerOnSeekCompleteHandler = new PlayerListenerOnSeekCompleteHandler();
        this.mPlayerListenerOnStalledHandler = new PlayerListenerOnStalledHandler();
        this.mPlayerListenerOnStartedHandler = new PlayerListenerOnStartedHandler();
        this.mPlayerListenerOnSubtitleChangeHandler = new PlayerListenerOnSubtitleChangeHandler();
        this.mPlayerListenerOnSubtitleFailedHandler = new PlayerListenerOnSubtitleFailedHandler();
        this.mPlayerListenerOnSubtitleRemoveHandler = new PlayerListenerOnSubtitleRemoveHandler();
        this.mPlayerListenerOnSubtitleShowHandler = new PlayerListenerOnSubtitleShowHandler();
        this.mPlayerListenerPlaybackClosedHandler = new PlayerListenerPlaybackClosedHandler();
        this.mPlayerListenerRestartPlaybackHandler = new PlayerListenerRestartPlaybackHandler();
        this.mPlayer = mPlayer;
    }
    
    void addPlayerListener(final IPlayer.PlayerListener playerListener) {
        synchronized (this) {
            if (!this.mPlayerListeners.contains(playerListener)) {
                this.mPlayerListeners.add(playerListener);
            }
            if (Log.isLoggable(PlayerListenerManager.TAG, 6) && this.mPlayerListeners.size() > 2) {
                Log.e(PlayerListenerManager.TAG, "Listener count should not be generally greater than 2, count:" + this.mPlayerListeners.size());
            }
        }
    }
    
    List<IPlayer.PlayerListener> getListeners() {
        synchronized (this) {
            return this.mPlayerListeners;
        }
    }
    
    PlayerListenerOnAudioChangeHandler getPlayerListenerOnAudioChangeHandler() {
        return this.mPlayerListenerOnAudioChangeHandler;
    }
    
    PlayerListenerOnBandwidthChangeHandler getPlayerListenerOnBandwidthChangeHandler() {
        return this.mPlayerListenerOnBandwidthChangeHandler;
    }
    
    PlayerListenerOnBufferingUpdateHandler getPlayerListenerOnBufferingUpdateHandler() {
        return this.mPlayerListenerOnBufferingUpdateHandler;
    }
    
    PlayerListenerOnCompletionHandler getPlayerListenerOnCompletionHandler() {
        return this.mPlayerListenerOnCompletionHandler;
    }
    
    PlayerListenerOnMediaErrorHandler getPlayerListenerOnMediaErrorHandler() {
        return this.mPlayerListenerOnMediaErrorHandler;
    }
    
    PlayerListenerOnNccpErrorHandler getPlayerListenerOnNccpErrorHandler() {
        return this.mPlayerListenerOnNccpErrorHandler;
    }
    
    PlayerListenerOnNrdFatalErrorHandler getPlayerListenerOnNrdFatalErrorHandler() {
        return this.mPlayerListenerOnNrdFatalErrorHandler;
    }
    
    PlayerListenerOnPlayingHandler getPlayerListenerOnPlayingHandler() {
        return this.mPlayerListenerOnPlayingHandler;
    }
    
    PlayerListenerOnSeekCompleteHandler getPlayerListenerOnSeekCompleteHandler() {
        return this.mPlayerListenerOnSeekCompleteHandler;
    }
    
    PlayerListenerOnStalledHandler getPlayerListenerOnStalledHandler() {
        return this.mPlayerListenerOnStalledHandler;
    }
    
    PlayerListenerOnStartedHandler getPlayerListenerOnStartedHandler() {
        return this.mPlayerListenerOnStartedHandler;
    }
    
    PlayerListenerOnSubtitleChangeHandler getPlayerListenerOnSubtitleChangeHandler() {
        return this.mPlayerListenerOnSubtitleChangeHandler;
    }
    
    PlayerListenerOnSubtitleFailedHandler getPlayerListenerOnSubtitleFailedHandler() {
        return this.mPlayerListenerOnSubtitleFailedHandler;
    }
    
    PlayerListenerOnSubtitleRemoveHandler getPlayerListenerOnSubtitleRemoveHandler() {
        return this.mPlayerListenerOnSubtitleRemoveHandler;
    }
    
    PlayerListenerOnSubtitleShowHandler getPlayerListenerOnSubtitleShowHandler() {
        return this.mPlayerListenerOnSubtitleShowHandler;
    }
    
    PlayerListenerOnUpdatePtsHandler getPlayerListenerOnUpdatePtsHandler() {
        return this.mPlayerListenerOnUpdatePtsHandler;
    }
    
    PlayerListenerPlaybackClosedHandler getPlayerListenerPlaybackClosedHandler() {
        return this.mPlayerListenerPlaybackClosedHandler;
    }
    
    PlayerListenerPrepareHandler getPlayerListenerPrepareHandler() {
        return this.mPlayerListenerPrepareHandler;
    }
    
    PlayerListenerRestartPlaybackHandler getPlayerListenerRestartPlaybackHandler() {
        return this.mPlayerListenerRestartPlaybackHandler;
    }
    
    void removePlayerListener(final IPlayer.PlayerListener playerListener) {
        synchronized (this) {
            this.mPlayerListeners.remove(playerListener);
        }
    }
    
    void setPlayerListenerOnAudioChangeHandler(final PlayerListenerOnAudioChangeHandler mPlayerListenerOnAudioChangeHandler) {
        this.mPlayerListenerOnAudioChangeHandler = mPlayerListenerOnAudioChangeHandler;
    }
    
    void setPlayerListenerOnBandwidthChangeHandler(final PlayerListenerOnBandwidthChangeHandler mPlayerListenerOnBandwidthChangeHandler) {
        this.mPlayerListenerOnBandwidthChangeHandler = mPlayerListenerOnBandwidthChangeHandler;
    }
    
    void setPlayerListenerOnBufferingUpdateHandler(final PlayerListenerOnBufferingUpdateHandler mPlayerListenerOnBufferingUpdateHandler) {
        this.mPlayerListenerOnBufferingUpdateHandler = mPlayerListenerOnBufferingUpdateHandler;
    }
    
    void setPlayerListenerOnCompletionHandler(final PlayerListenerOnCompletionHandler mPlayerListenerOnCompletionHandler) {
        this.mPlayerListenerOnCompletionHandler = mPlayerListenerOnCompletionHandler;
    }
    
    void setPlayerListenerOnMediaErrorHandler(final PlayerListenerOnMediaErrorHandler mPlayerListenerOnMediaErrorHandler) {
        this.mPlayerListenerOnMediaErrorHandler = mPlayerListenerOnMediaErrorHandler;
    }
    
    void setPlayerListenerOnNccpErrorHandler(final PlayerListenerOnNccpErrorHandler mPlayerListenerOnNccpErrorHandler) {
        this.mPlayerListenerOnNccpErrorHandler = mPlayerListenerOnNccpErrorHandler;
    }
    
    void setPlayerListenerOnNrdFatalErrorHandler(final PlayerListenerOnNrdFatalErrorHandler mPlayerListenerOnNrdFatalErrorHandler) {
        this.mPlayerListenerOnNrdFatalErrorHandler = mPlayerListenerOnNrdFatalErrorHandler;
    }
    
    void setPlayerListenerOnPlayingHandler(final PlayerListenerOnPlayingHandler mPlayerListenerOnPlayingHandler) {
        this.mPlayerListenerOnPlayingHandler = mPlayerListenerOnPlayingHandler;
    }
    
    void setPlayerListenerOnSeekCompleteHandler(final PlayerListenerOnSeekCompleteHandler mPlayerListenerOnSeekCompleteHandler) {
        this.mPlayerListenerOnSeekCompleteHandler = mPlayerListenerOnSeekCompleteHandler;
    }
    
    void setPlayerListenerOnStalledHandler(final PlayerListenerOnStalledHandler mPlayerListenerOnStalledHandler) {
        this.mPlayerListenerOnStalledHandler = mPlayerListenerOnStalledHandler;
    }
    
    void setPlayerListenerOnStartedHandler(final PlayerListenerOnStartedHandler mPlayerListenerOnStartedHandler) {
        this.mPlayerListenerOnStartedHandler = mPlayerListenerOnStartedHandler;
    }
    
    void setPlayerListenerOnSubtitleChangeHandler(final PlayerListenerOnSubtitleChangeHandler mPlayerListenerOnSubtitleChangeHandler) {
        this.mPlayerListenerOnSubtitleChangeHandler = mPlayerListenerOnSubtitleChangeHandler;
    }
    
    void setPlayerListenerOnSubtitleFailedHandler(final PlayerListenerOnSubtitleFailedHandler mPlayerListenerOnSubtitleFailedHandler) {
        this.mPlayerListenerOnSubtitleFailedHandler = mPlayerListenerOnSubtitleFailedHandler;
    }
    
    void setPlayerListenerOnSubtitleRemoveHandler(final PlayerListenerOnSubtitleRemoveHandler mPlayerListenerOnSubtitleRemoveHandler) {
        this.mPlayerListenerOnSubtitleRemoveHandler = mPlayerListenerOnSubtitleRemoveHandler;
    }
    
    void setPlayerListenerOnSubtitleShowHandler(final PlayerListenerOnSubtitleShowHandler mPlayerListenerOnSubtitleShowHandler) {
        this.mPlayerListenerOnSubtitleShowHandler = mPlayerListenerOnSubtitleShowHandler;
    }
    
    void setPlayerListenerOnUpdatePtsHandler(final PlayerListenerOnUpdatePtsHandler mPlayerListenerOnUpdatePtsHandler) {
        this.mPlayerListenerOnUpdatePtsHandler = mPlayerListenerOnUpdatePtsHandler;
    }
    
    void setPlayerListenerPrepareHandler(final PlayerListenerPrepareHandler mPlayerListenerPrepareHandler) {
        this.mPlayerListenerPrepareHandler = mPlayerListenerPrepareHandler;
    }
    
    public interface PlayerListenerHandler
    {
        void handle(final IPlayer.PlayerListener p0, final Object... p1);
    }
    
    private class PlayerListenerOnAudioChangeHandler implements PlayerListenerHandler
    {
        @Override
        public void handle(final IPlayer.PlayerListener playerListener, final Object... array) {
            if (playerListener.isListening() && array != null && array.length >= 1) {
                playerListener.onAudioChange((int)array[0]);
            }
        }
    }
    
    private class PlayerListenerOnBandwidthChangeHandler implements PlayerListenerHandler
    {
        @Override
        public void handle(final IPlayer.PlayerListener playerListener, final Object... array) {
            if (playerListener.isListening() && array != null && array.length >= 1) {
                playerListener.onBandwidthChange((int)array[0]);
            }
        }
    }
    
    private class PlayerListenerOnBufferingUpdateHandler implements PlayerListenerHandler
    {
        @Override
        public void handle(final IPlayer.PlayerListener playerListener, final Object... array) {
            if (playerListener.isListening() && array != null && array.length >= 1) {
                playerListener.onBufferingUpdate((int)array[0]);
            }
        }
    }
    
    private class PlayerListenerOnCompletionHandler implements PlayerListenerHandler
    {
        @Override
        public void handle(final IPlayer.PlayerListener playerListener, final Object... array) {
            if (playerListener.isListening()) {
                playerListener.onCompletion();
            }
        }
    }
    
    private class PlayerListenerOnMediaErrorHandler implements PlayerListenerHandler
    {
        @Override
        public void handle(final IPlayer.PlayerListener playerListener, final Object... array) {
            if (playerListener.isListening() && array != null && array.length >= 1 && array[0] instanceof Error) {
                playerListener.onMediaError((Error)array[0]);
            }
        }
    }
    
    private class PlayerListenerOnNccpErrorHandler implements PlayerListenerHandler
    {
        @Override
        public void handle(final IPlayer.PlayerListener playerListener, final Object... array) {
            if (playerListener.isListening() && array != null && array.length >= 1 && array[0] instanceof NccpError) {
                playerListener.onNccpError((NccpError)array[0]);
            }
        }
    }
    
    private class PlayerListenerOnNrdFatalErrorHandler implements PlayerListenerHandler
    {
        @Override
        public void handle(final IPlayer.PlayerListener playerListener, final Object... array) {
            if (playerListener.isListening()) {
                playerListener.onNrdFatalError();
            }
        }
    }
    
    private class PlayerListenerOnPlayingHandler implements PlayerListenerHandler
    {
        @Override
        public void handle(final IPlayer.PlayerListener playerListener, final Object... array) {
            if (playerListener.isListening()) {
                playerListener.onPlaying();
            }
        }
    }
    
    private class PlayerListenerOnSeekCompleteHandler implements PlayerListenerHandler
    {
        @Override
        public void handle(final IPlayer.PlayerListener playerListener, final Object... array) {
            if (playerListener.isListening()) {
                playerListener.onSeekComplete();
            }
        }
    }
    
    private class PlayerListenerOnStalledHandler implements PlayerListenerHandler
    {
        @Override
        public void handle(final IPlayer.PlayerListener playerListener, final Object... array) {
            if (playerListener.isListening()) {
                playerListener.onStalled();
            }
        }
    }
    
    private class PlayerListenerOnStartedHandler implements PlayerListenerHandler
    {
        @Override
        public void handle(final IPlayer.PlayerListener playerListener, final Object... array) {
            if (playerListener.isListening()) {
                playerListener.onStarted();
            }
        }
    }
    
    private class PlayerListenerOnSubtitleChangeHandler implements PlayerListenerHandler
    {
        @Override
        public void handle(final IPlayer.PlayerListener playerListener, final Object... array) {
            if (playerListener.isListening() && array != null && array.length >= 1 && array[0] instanceof SubtitleScreen) {
                playerListener.onSubtitleChange((SubtitleScreen)array[0]);
            }
        }
    }
    
    private class PlayerListenerOnSubtitleFailedHandler implements PlayerListenerHandler
    {
        @Override
        public void handle(final IPlayer.PlayerListener playerListener, final Object... array) {
            if (playerListener.isListening()) {
                playerListener.onSubtitleFailed();
            }
        }
    }
    
    private class PlayerListenerOnSubtitleRemoveHandler implements PlayerListenerHandler
    {
        @Override
        public void handle(final IPlayer.PlayerListener playerListener, final Object... array) {
            if (playerListener.isListening()) {
                playerListener.onSubtitleRemove();
            }
        }
    }
    
    private class PlayerListenerOnSubtitleShowHandler implements PlayerListenerHandler
    {
        @Override
        public void handle(final IPlayer.PlayerListener playerListener, final Object... array) {
            if (playerListener.isListening() && array != null && array.length >= 1 && array[0] instanceof String) {
                playerListener.onSubtitleShow((String)array[0]);
            }
        }
    }
    
    private class PlayerListenerOnUpdatePtsHandler implements PlayerListenerHandler
    {
        @Override
        public void handle(final IPlayer.PlayerListener playerListener, final Object... array) {
            if (playerListener.isListening() && array != null && array.length >= 1) {
                playerListener.onUpdatePts((int)array[0]);
            }
        }
    }
    
    private class PlayerListenerPlaybackClosedHandler implements PlayerListenerHandler
    {
        @Override
        public void handle(final IPlayer.PlayerListener playerListener, final Object... array) {
            playerListener.playbackClosed();
        }
    }
    
    private class PlayerListenerPrepareHandler implements PlayerListenerHandler
    {
        @Override
        public void handle(final IPlayer.PlayerListener playerListener, final Object... array) {
            if (playerListener.isListening()) {
                playerListener.onPrepared();
                final IMedia media = PlayerListenerManager.this.mPlayer.getNrdController().getNrdp().getMedia();
                playerListener.onVideoSizeChanged(media.getVideoWidth(), media.getVideoHeight());
            }
        }
    }
    
    private class PlayerListenerRestartPlaybackHandler implements PlayerListenerHandler
    {
        @Override
        public void handle(final IPlayer.PlayerListener playerListener, final Object... array) {
            if (playerListener.isListening() && array != null && array.length >= 1 && array[0] instanceof NccpError) {
                playerListener.restartPlayback((NccpError)array[0]);
            }
        }
    }
}
