// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player;

import com.netflix.mediaclient.Log;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.IPlayer$PlayerListener;
import java.util.List;

class PlayerListenerManager
{
    private static final String TAG;
    private PlayerAgent mPlayer;
    private PlayerListenerManager$PlayerListenerOnAudioChangeHandler mPlayerListenerOnAudioChangeHandler;
    private PlayerListenerManager$PlayerListenerOnBandwidthChangeHandler mPlayerListenerOnBandwidthChangeHandler;
    private PlayerListenerManager$PlayerListenerOnBufferingUpdateHandler mPlayerListenerOnBufferingUpdateHandler;
    private PlayerListenerManager$PlayerListenerOnCompletionHandler mPlayerListenerOnCompletionHandler;
    private PlayerListenerManager$PlayerListenerOnMediaErrorHandler mPlayerListenerOnMediaErrorHandler;
    private PlayerListenerManager$PlayerListenerOnNccpErrorHandler mPlayerListenerOnNccpErrorHandler;
    private PlayerListenerManager$PlayerListenerOnNrdFatalErrorHandler mPlayerListenerOnNrdFatalErrorHandler;
    private PlayerListenerManager$PlayerListenerOnPlayingHandler mPlayerListenerOnPlayingHandler;
    private PlayerListenerManager$PlayerListenerOnSeekCompleteHandler mPlayerListenerOnSeekCompleteHandler;
    private PlayerListenerManager$PlayerListenerOnStalledHandler mPlayerListenerOnStalledHandler;
    private PlayerListenerManager$PlayerListenerOnStartedHandler mPlayerListenerOnStartedHandler;
    private PlayerListenerManager$PlayerListenerOnSubtitleChangeHandler mPlayerListenerOnSubtitleChangeHandler;
    private PlayerListenerManager$PlayerListenerOnSubtitleFailedHandler mPlayerListenerOnSubtitleFailedHandler;
    private PlayerListenerManager$PlayerListenerOnSubtitleRemoveHandler mPlayerListenerOnSubtitleRemoveHandler;
    private PlayerListenerManager$PlayerListenerOnSubtitleShowHandler mPlayerListenerOnSubtitleShowHandler;
    private PlayerListenerManager$PlayerListenerOnUpdatePtsHandler mPlayerListenerOnUpdatePtsHandler;
    private PlayerListenerManager$PlayerListenerPlaybackClosedHandler mPlayerListenerPlaybackClosedHandler;
    private PlayerListenerManager$PlayerListenerPrepareHandler mPlayerListenerPrepareHandler;
    private PlayerListenerManager$PlayerListenerRestartPlaybackHandler mPlayerListenerRestartPlaybackHandler;
    private final List<IPlayer$PlayerListener> mPlayerListeners;
    
    static {
        TAG = PlayerAgent.class.getSimpleName();
    }
    
    PlayerListenerManager(final PlayerAgent mPlayer) {
        this.mPlayerListeners = new ArrayList<IPlayer$PlayerListener>();
        this.mPlayerListenerPrepareHandler = new PlayerListenerManager$PlayerListenerPrepareHandler(this, null);
        this.mPlayerListenerOnUpdatePtsHandler = new PlayerListenerManager$PlayerListenerOnUpdatePtsHandler(this, null);
        this.mPlayerListenerOnAudioChangeHandler = new PlayerListenerManager$PlayerListenerOnAudioChangeHandler(this, null);
        this.mPlayerListenerOnBandwidthChangeHandler = new PlayerListenerManager$PlayerListenerOnBandwidthChangeHandler(this, null);
        this.mPlayerListenerOnBufferingUpdateHandler = new PlayerListenerManager$PlayerListenerOnBufferingUpdateHandler(this, null);
        this.mPlayerListenerOnCompletionHandler = new PlayerListenerManager$PlayerListenerOnCompletionHandler(this, null);
        this.mPlayerListenerOnMediaErrorHandler = new PlayerListenerManager$PlayerListenerOnMediaErrorHandler(this, null);
        this.mPlayerListenerOnNccpErrorHandler = new PlayerListenerManager$PlayerListenerOnNccpErrorHandler(this, null);
        this.mPlayerListenerOnNrdFatalErrorHandler = new PlayerListenerManager$PlayerListenerOnNrdFatalErrorHandler(this, null);
        this.mPlayerListenerOnPlayingHandler = new PlayerListenerManager$PlayerListenerOnPlayingHandler(this, null);
        this.mPlayerListenerOnSeekCompleteHandler = new PlayerListenerManager$PlayerListenerOnSeekCompleteHandler(this, null);
        this.mPlayerListenerOnStalledHandler = new PlayerListenerManager$PlayerListenerOnStalledHandler(this, null);
        this.mPlayerListenerOnStartedHandler = new PlayerListenerManager$PlayerListenerOnStartedHandler(this, null);
        this.mPlayerListenerOnSubtitleChangeHandler = new PlayerListenerManager$PlayerListenerOnSubtitleChangeHandler(this, null);
        this.mPlayerListenerOnSubtitleFailedHandler = new PlayerListenerManager$PlayerListenerOnSubtitleFailedHandler(this, null);
        this.mPlayerListenerOnSubtitleRemoveHandler = new PlayerListenerManager$PlayerListenerOnSubtitleRemoveHandler(this, null);
        this.mPlayerListenerOnSubtitleShowHandler = new PlayerListenerManager$PlayerListenerOnSubtitleShowHandler(this, null);
        this.mPlayerListenerPlaybackClosedHandler = new PlayerListenerManager$PlayerListenerPlaybackClosedHandler(this, null);
        this.mPlayerListenerRestartPlaybackHandler = new PlayerListenerManager$PlayerListenerRestartPlaybackHandler(this, null);
        this.mPlayer = mPlayer;
    }
    
    void addPlayerListener(final IPlayer$PlayerListener player$PlayerListener) {
        synchronized (this) {
            if (!this.mPlayerListeners.contains(player$PlayerListener)) {
                this.mPlayerListeners.add(player$PlayerListener);
            }
            if (Log.isLoggable() && this.mPlayerListeners.size() > 2) {
                Log.e(PlayerListenerManager.TAG, "Listener count should not be generally greater than 2, count:" + this.mPlayerListeners.size());
            }
        }
    }
    
    List<IPlayer$PlayerListener> getListeners() {
        synchronized (this) {
            return this.mPlayerListeners;
        }
    }
    
    PlayerListenerManager$PlayerListenerOnAudioChangeHandler getPlayerListenerOnAudioChangeHandler() {
        return this.mPlayerListenerOnAudioChangeHandler;
    }
    
    PlayerListenerManager$PlayerListenerOnBandwidthChangeHandler getPlayerListenerOnBandwidthChangeHandler() {
        return this.mPlayerListenerOnBandwidthChangeHandler;
    }
    
    PlayerListenerManager$PlayerListenerOnBufferingUpdateHandler getPlayerListenerOnBufferingUpdateHandler() {
        return this.mPlayerListenerOnBufferingUpdateHandler;
    }
    
    PlayerListenerManager$PlayerListenerOnCompletionHandler getPlayerListenerOnCompletionHandler() {
        return this.mPlayerListenerOnCompletionHandler;
    }
    
    PlayerListenerManager$PlayerListenerOnMediaErrorHandler getPlayerListenerOnMediaErrorHandler() {
        return this.mPlayerListenerOnMediaErrorHandler;
    }
    
    PlayerListenerManager$PlayerListenerOnNccpErrorHandler getPlayerListenerOnNccpErrorHandler() {
        return this.mPlayerListenerOnNccpErrorHandler;
    }
    
    PlayerListenerManager$PlayerListenerOnNrdFatalErrorHandler getPlayerListenerOnNrdFatalErrorHandler() {
        return this.mPlayerListenerOnNrdFatalErrorHandler;
    }
    
    PlayerListenerManager$PlayerListenerOnPlayingHandler getPlayerListenerOnPlayingHandler() {
        return this.mPlayerListenerOnPlayingHandler;
    }
    
    PlayerListenerManager$PlayerListenerOnSeekCompleteHandler getPlayerListenerOnSeekCompleteHandler() {
        return this.mPlayerListenerOnSeekCompleteHandler;
    }
    
    PlayerListenerManager$PlayerListenerOnStalledHandler getPlayerListenerOnStalledHandler() {
        return this.mPlayerListenerOnStalledHandler;
    }
    
    PlayerListenerManager$PlayerListenerOnStartedHandler getPlayerListenerOnStartedHandler() {
        return this.mPlayerListenerOnStartedHandler;
    }
    
    PlayerListenerManager$PlayerListenerOnSubtitleChangeHandler getPlayerListenerOnSubtitleChangeHandler() {
        return this.mPlayerListenerOnSubtitleChangeHandler;
    }
    
    PlayerListenerManager$PlayerListenerOnSubtitleFailedHandler getPlayerListenerOnSubtitleFailedHandler() {
        return this.mPlayerListenerOnSubtitleFailedHandler;
    }
    
    PlayerListenerManager$PlayerListenerOnSubtitleRemoveHandler getPlayerListenerOnSubtitleRemoveHandler() {
        return this.mPlayerListenerOnSubtitleRemoveHandler;
    }
    
    PlayerListenerManager$PlayerListenerOnSubtitleShowHandler getPlayerListenerOnSubtitleShowHandler() {
        return this.mPlayerListenerOnSubtitleShowHandler;
    }
    
    PlayerListenerManager$PlayerListenerOnUpdatePtsHandler getPlayerListenerOnUpdatePtsHandler() {
        return this.mPlayerListenerOnUpdatePtsHandler;
    }
    
    PlayerListenerManager$PlayerListenerPlaybackClosedHandler getPlayerListenerPlaybackClosedHandler() {
        return this.mPlayerListenerPlaybackClosedHandler;
    }
    
    PlayerListenerManager$PlayerListenerPrepareHandler getPlayerListenerPrepareHandler() {
        return this.mPlayerListenerPrepareHandler;
    }
    
    PlayerListenerManager$PlayerListenerRestartPlaybackHandler getPlayerListenerRestartPlaybackHandler() {
        return this.mPlayerListenerRestartPlaybackHandler;
    }
    
    void removePlayerListener(final IPlayer$PlayerListener player$PlayerListener) {
        synchronized (this) {
            this.mPlayerListeners.remove(player$PlayerListener);
        }
    }
    
    void setPlayerListenerOnAudioChangeHandler(final PlayerListenerManager$PlayerListenerOnAudioChangeHandler mPlayerListenerOnAudioChangeHandler) {
        this.mPlayerListenerOnAudioChangeHandler = mPlayerListenerOnAudioChangeHandler;
    }
    
    void setPlayerListenerOnBandwidthChangeHandler(final PlayerListenerManager$PlayerListenerOnBandwidthChangeHandler mPlayerListenerOnBandwidthChangeHandler) {
        this.mPlayerListenerOnBandwidthChangeHandler = mPlayerListenerOnBandwidthChangeHandler;
    }
    
    void setPlayerListenerOnBufferingUpdateHandler(final PlayerListenerManager$PlayerListenerOnBufferingUpdateHandler mPlayerListenerOnBufferingUpdateHandler) {
        this.mPlayerListenerOnBufferingUpdateHandler = mPlayerListenerOnBufferingUpdateHandler;
    }
    
    void setPlayerListenerOnCompletionHandler(final PlayerListenerManager$PlayerListenerOnCompletionHandler mPlayerListenerOnCompletionHandler) {
        this.mPlayerListenerOnCompletionHandler = mPlayerListenerOnCompletionHandler;
    }
    
    void setPlayerListenerOnMediaErrorHandler(final PlayerListenerManager$PlayerListenerOnMediaErrorHandler mPlayerListenerOnMediaErrorHandler) {
        this.mPlayerListenerOnMediaErrorHandler = mPlayerListenerOnMediaErrorHandler;
    }
    
    void setPlayerListenerOnNccpErrorHandler(final PlayerListenerManager$PlayerListenerOnNccpErrorHandler mPlayerListenerOnNccpErrorHandler) {
        this.mPlayerListenerOnNccpErrorHandler = mPlayerListenerOnNccpErrorHandler;
    }
    
    void setPlayerListenerOnNrdFatalErrorHandler(final PlayerListenerManager$PlayerListenerOnNrdFatalErrorHandler mPlayerListenerOnNrdFatalErrorHandler) {
        this.mPlayerListenerOnNrdFatalErrorHandler = mPlayerListenerOnNrdFatalErrorHandler;
    }
    
    void setPlayerListenerOnPlayingHandler(final PlayerListenerManager$PlayerListenerOnPlayingHandler mPlayerListenerOnPlayingHandler) {
        this.mPlayerListenerOnPlayingHandler = mPlayerListenerOnPlayingHandler;
    }
    
    void setPlayerListenerOnSeekCompleteHandler(final PlayerListenerManager$PlayerListenerOnSeekCompleteHandler mPlayerListenerOnSeekCompleteHandler) {
        this.mPlayerListenerOnSeekCompleteHandler = mPlayerListenerOnSeekCompleteHandler;
    }
    
    void setPlayerListenerOnStalledHandler(final PlayerListenerManager$PlayerListenerOnStalledHandler mPlayerListenerOnStalledHandler) {
        this.mPlayerListenerOnStalledHandler = mPlayerListenerOnStalledHandler;
    }
    
    void setPlayerListenerOnStartedHandler(final PlayerListenerManager$PlayerListenerOnStartedHandler mPlayerListenerOnStartedHandler) {
        this.mPlayerListenerOnStartedHandler = mPlayerListenerOnStartedHandler;
    }
    
    void setPlayerListenerOnSubtitleChangeHandler(final PlayerListenerManager$PlayerListenerOnSubtitleChangeHandler mPlayerListenerOnSubtitleChangeHandler) {
        this.mPlayerListenerOnSubtitleChangeHandler = mPlayerListenerOnSubtitleChangeHandler;
    }
    
    void setPlayerListenerOnSubtitleFailedHandler(final PlayerListenerManager$PlayerListenerOnSubtitleFailedHandler mPlayerListenerOnSubtitleFailedHandler) {
        this.mPlayerListenerOnSubtitleFailedHandler = mPlayerListenerOnSubtitleFailedHandler;
    }
    
    void setPlayerListenerOnSubtitleRemoveHandler(final PlayerListenerManager$PlayerListenerOnSubtitleRemoveHandler mPlayerListenerOnSubtitleRemoveHandler) {
        this.mPlayerListenerOnSubtitleRemoveHandler = mPlayerListenerOnSubtitleRemoveHandler;
    }
    
    void setPlayerListenerOnSubtitleShowHandler(final PlayerListenerManager$PlayerListenerOnSubtitleShowHandler mPlayerListenerOnSubtitleShowHandler) {
        this.mPlayerListenerOnSubtitleShowHandler = mPlayerListenerOnSubtitleShowHandler;
    }
    
    void setPlayerListenerOnUpdatePtsHandler(final PlayerListenerManager$PlayerListenerOnUpdatePtsHandler mPlayerListenerOnUpdatePtsHandler) {
        this.mPlayerListenerOnUpdatePtsHandler = mPlayerListenerOnUpdatePtsHandler;
    }
    
    void setPlayerListenerPrepareHandler(final PlayerListenerManager$PlayerListenerPrepareHandler mPlayerListenerPrepareHandler) {
        this.mPlayerListenerPrepareHandler = mPlayerListenerPrepareHandler;
    }
}
