// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player;

import com.netflix.mediaclient.Log;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.IPlayer$PlayerListener;
import java.util.List;

public class PlayerListenerManager
{
    private static final String TAG;
    private PlayerListenerManager$PlayerListenerOnAudioChangeHandler mPlayerListenerOnAudioChangeHandler;
    private PlayerListenerManager$PlayerListenerOnBandwidthChangeHandler mPlayerListenerOnBandwidthChangeHandler;
    private PlayerListenerManager$PlayerListenerOnBufferingUpdateHandler mPlayerListenerOnBufferingUpdateHandler;
    private PlayerListenerManager$PlayerListenerOnCompletionHandler mPlayerListenerOnCompletionHandler;
    private PlayerListenerManager$PlayerListenerOnMediaErrorHandler mPlayerListenerOnMediaErrorHandler;
    private PlayerListenerManager$PlayerListenerOnNccpErrorHandler mPlayerListenerOnNccpErrorHandler;
    private PlayerListenerManager$PlayerListenerOnNrdFatalErrorHandler mPlayerListenerOnNrdFatalErrorHandler;
    private PlayerListenerManager$PlayerListenerOnPlaybackErrorHandler mPlayerListenerOnPlaybackErrorHandler;
    private PlayerListenerManager$PlayerListenerOnPlayingHandler mPlayerListenerOnPlayingHandler;
    private PlayerListenerManager$PlayerListenerOnSeekCompleteHandler mPlayerListenerOnSeekCompleteHandler;
    private PlayerListenerManager$PlayerListenerOnStalledHandler mPlayerListenerOnStalledHandler;
    private PlayerListenerManager$PlayerListenerOnStartedHandler mPlayerListenerOnStartedHandler;
    private PlayerListenerManager$PlayerListenerOnSubtitleChangeHandler mPlayerListenerOnSubtitleChangeHandler;
    private PlayerListenerManager$PlayerListenerOnSubtitleFailedHandler mPlayerListenerOnSubtitleFailedHandler;
    private PlayerListenerManager$PlayerListenerOnUpdatePtsHandler mPlayerListenerOnUpdatePtsHandler;
    private PlayerListenerManager$PlayerListenerPlaybackClosedHandler mPlayerListenerPlaybackClosedHandler;
    private PlayerListenerManager$PlayerListenerPrepareHandler mPlayerListenerPrepareHandler;
    private PlayerListenerManager$PlayerListenerRestartPlaybackHandler mPlayerListenerRestartPlaybackHandler;
    private final List<IPlayer$PlayerListener> mPlayerListeners;
    
    static {
        TAG = PlayerAgent.class.getSimpleName();
    }
    
    public PlayerListenerManager() {
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
        this.mPlayerListenerPlaybackClosedHandler = new PlayerListenerManager$PlayerListenerPlaybackClosedHandler(this, null);
        this.mPlayerListenerRestartPlaybackHandler = new PlayerListenerManager$PlayerListenerRestartPlaybackHandler(this, null);
        this.mPlayerListenerOnPlaybackErrorHandler = new PlayerListenerManager$PlayerListenerOnPlaybackErrorHandler(this, null);
    }
    
    public void addPlayerListener(final IPlayer$PlayerListener player$PlayerListener) {
        synchronized (this) {
            if (!this.mPlayerListeners.contains(player$PlayerListener)) {
                this.mPlayerListeners.add(player$PlayerListener);
            }
            if (Log.isLoggable() && this.mPlayerListeners.size() > 2) {
                Log.e(PlayerListenerManager.TAG, "Listener count should not be generally greater than 2, count:" + this.mPlayerListeners.size());
            }
        }
    }
    
    public List<IPlayer$PlayerListener> getListeners() {
        synchronized (this) {
            return this.mPlayerListeners;
        }
    }
    
    public PlayerListenerManager$PlayerListenerOnAudioChangeHandler getPlayerListenerOnAudioChangeHandler() {
        return this.mPlayerListenerOnAudioChangeHandler;
    }
    
    public PlayerListenerManager$PlayerListenerOnBandwidthChangeHandler getPlayerListenerOnBandwidthChangeHandler() {
        return this.mPlayerListenerOnBandwidthChangeHandler;
    }
    
    PlayerListenerManager$PlayerListenerOnBufferingUpdateHandler getPlayerListenerOnBufferingUpdateHandler() {
        return this.mPlayerListenerOnBufferingUpdateHandler;
    }
    
    public PlayerListenerManager$PlayerListenerOnCompletionHandler getPlayerListenerOnCompletionHandler() {
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
    
    public PlayerListenerManager$PlayerListenerOnPlaybackErrorHandler getPlayerListenerOnPlaybackErrorHandler() {
        return this.mPlayerListenerOnPlaybackErrorHandler;
    }
    
    public PlayerListenerManager$PlayerListenerOnPlayingHandler getPlayerListenerOnPlayingHandler() {
        return this.mPlayerListenerOnPlayingHandler;
    }
    
    public PlayerListenerManager$PlayerListenerOnSeekCompleteHandler getPlayerListenerOnSeekCompleteHandler() {
        return this.mPlayerListenerOnSeekCompleteHandler;
    }
    
    public PlayerListenerManager$PlayerListenerOnStalledHandler getPlayerListenerOnStalledHandler() {
        return this.mPlayerListenerOnStalledHandler;
    }
    
    public PlayerListenerManager$PlayerListenerOnStartedHandler getPlayerListenerOnStartedHandler() {
        return this.mPlayerListenerOnStartedHandler;
    }
    
    public PlayerListenerManager$PlayerListenerOnSubtitleChangeHandler getPlayerListenerOnSubtitleChangeHandler() {
        return this.mPlayerListenerOnSubtitleChangeHandler;
    }
    
    public PlayerListenerManager$PlayerListenerOnSubtitleFailedHandler getPlayerListenerOnSubtitleFailedHandler() {
        return this.mPlayerListenerOnSubtitleFailedHandler;
    }
    
    public PlayerListenerManager$PlayerListenerOnUpdatePtsHandler getPlayerListenerOnUpdatePtsHandler() {
        return this.mPlayerListenerOnUpdatePtsHandler;
    }
    
    public PlayerListenerManager$PlayerListenerPlaybackClosedHandler getPlayerListenerPlaybackClosedHandler() {
        return this.mPlayerListenerPlaybackClosedHandler;
    }
    
    public PlayerListenerManager$PlayerListenerPrepareHandler getPlayerListenerPrepareHandler() {
        return this.mPlayerListenerPrepareHandler;
    }
    
    public PlayerListenerManager$PlayerListenerRestartPlaybackHandler getPlayerListenerRestartPlaybackHandler() {
        return this.mPlayerListenerRestartPlaybackHandler;
    }
    
    public void removePlayerListener(final IPlayer$PlayerListener player$PlayerListener) {
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
    
    void setPlayerListenerOnUpdatePtsHandler(final PlayerListenerManager$PlayerListenerOnUpdatePtsHandler mPlayerListenerOnUpdatePtsHandler) {
        this.mPlayerListenerOnUpdatePtsHandler = mPlayerListenerOnUpdatePtsHandler;
    }
    
    void setPlayerListenerPrepareHandler(final PlayerListenerManager$PlayerListenerPrepareHandler mPlayerListenerPrepareHandler) {
        this.mPlayerListenerPrepareHandler = mPlayerListenerPrepareHandler;
    }
}
