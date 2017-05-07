// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.service.mdx.message.target.PinNotRequired;
import com.netflix.mediaclient.service.mdx.message.target.PinRequired;
import com.netflix.mediaclient.service.mdx.message.target.PlayerStateChanged;
import com.netflix.mediaclient.service.mdx.message.target.PlayerCurrentState;
import org.json.JSONException;
import com.netflix.mediaclient.service.mdx.message.target.HandshakeAccepted;
import org.json.JSONObject;
import java.security.InvalidParameterException;
import com.netflix.mediaclient.util.WebApiUtils;
import com.netflix.mediaclient.util.WebApiUtils$VideoIds;
import android.os.Message;
import com.netflix.mediaclient.service.mdx.message.MdxMessage;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.RemoteDevice;
import android.os.HandlerThread;
import android.os.Handler;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.MdxController;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.mdx.message.target.PlayerState;

class TargetContext$PlayerStateManager
{
    private static final long TIMEOUT_WAITING_FOR_STATE_CHANGE = 30000L;
    private static final long TIME_WINDOW_IGNORE_VOLUME = 3000L;
    private String mCatalogId;
    private String mCurrentState;
    private int mDuration;
    private String mEpisodeId;
    private int mExpectedVolume;
    private boolean mTargetStateTransitionStarted;
    private int mTime;
    private long mTimeMarked4StateTransition;
    private long mTimeSetVolume;
    private int mVolume;
    final /* synthetic */ TargetContext this$0;
    
    TargetContext$PlayerStateManager(final TargetContext this$0) {
        this.this$0 = this$0;
        this.mTime = -1;
        this.mVolume = -1;
        this.mDuration = -1;
        this.mTargetStateTransitionStarted = true;
        this.mTimeMarked4StateTransition = 0L;
    }
    
    private void notifyState(final String s, final PlayerState playerState) {
        this.mTime = playerState.getTime();
        if (System.currentTimeMillis() > this.mTimeSetVolume + 3000L) {
            this.mVolume = playerState.getVolume();
        }
        else {
            this.mVolume = this.mExpectedVolume;
            Log.d("nf_mdx", "TargetContext: PlayerStateManager overide volume");
        }
        if (Log.isLoggable("nf_mdx", 3)) {
            Log.d("nf_mdx", "TargetContext: PlayerStateManager notifyState " + this.mCurrentState + ", volume = " + this.mVolume + ", time = " + this.mTime);
        }
        this.this$0.mNotifier.state(s, this.mCurrentState, this.mTime, this.mVolume);
        if (!StringUtils.safeEquals(this.mCatalogId, playerState.getCatalogId()) || !StringUtils.safeEquals(this.mEpisodeId, playerState.getEpisodeId()) || this.mDuration != playerState.getDuration()) {
            this.mCatalogId = playerState.getCatalogId();
            this.mEpisodeId = playerState.getEpisodeId();
            this.mDuration = playerState.getDuration();
            this.this$0.mNotifier.movieMetaData(s, this.mCatalogId, this.mEpisodeId, this.mDuration);
        }
    }
    
    public void changeState(final PlayerState playerState) {
        if (playerState != null) {
            String s;
            if (StringUtils.isNotEmpty(this.this$0.mDialUuid)) {
                s = this.this$0.mDialUuid;
            }
            else {
                s = this.this$0.mUuid;
            }
            final String currentState = playerState.getCurrentState();
            if (StringUtils.isEmpty(currentState)) {
                Log.e("nf_mdx", "TargetContext: changeState, new state is null");
                return;
            }
            final String postplayState = playerState.getPostplayState();
            if ("PLAYING".equals(currentState) && !"PAUSE".equals(this.mCurrentState) && !"prepause".equals(this.mCurrentState) && !"preseek".equals(this.mCurrentState) && !"PLAYING".equals(this.mCurrentState)) {
                this.this$0.playbackStart(s);
            }
            else if ("STOP".equals(currentState) || "END_PLAYBACK".equals(currentState) || "FATAL_ERROR".equals(currentState)) {
                this.this$0.playbackEnd(s, postplayState);
            }
            if ("PLAYING".equals(currentState) && !currentState.equals(this.mCurrentState)) {
                this.this$0.mNotifier.simplePlaybackState(s, false, false, postplayState);
            }
            else if ("PAUSE".equals(currentState) && !currentState.equals(this.mCurrentState)) {
                this.this$0.mNotifier.simplePlaybackState(s, true, false, postplayState);
            }
            if ("PLAY".equals(currentState)) {
                this.this$0.mNotifier.state(s, "preplay", this.mTime, this.mVolume);
            }
            if ("PROGRESS".equals(currentState) || "PLAY".equals(currentState)) {
                this.mTargetStateTransitionStarted = true;
                this.mTimeMarked4StateTransition = System.currentTimeMillis();
            }
            else if (this.mTargetStateTransitionStarted) {
                this.mCurrentState = currentState;
                this.notifyState(s, playerState);
            }
            if (Log.isLoggable("nf_mdx", 3)) {
                Log.d("nf_mdx", "TargetContext: PlayerStateManager state changed to " + this.mCurrentState);
            }
        }
    }
    
    public void forceToEndPlayback(final String s, final String s2) {
        this.mCurrentState = "END_PLAYBACK";
        this.this$0.playbackEnd(s, s2);
    }
    
    public String getCatalogId() {
        return this.mCatalogId;
    }
    
    public String getEpisodeId() {
        return this.mEpisodeId;
    }
    
    public String getTargetPlayerState() {
        return this.mCurrentState;
    }
    
    public void receivedCommand(final String s) {
        String s2;
        if (StringUtils.isNotEmpty(this.this$0.mDialUuid)) {
            s2 = this.this$0.mDialUuid;
        }
        else {
            s2 = this.this$0.mUuid;
        }
        if ("PLAYER_PLAY".equals(s)) {
            this.mTargetStateTransitionStarted = false;
            this.mTimeMarked4StateTransition = System.currentTimeMillis();
            this.mCurrentState = "preplay";
            this.mCatalogId = new String();
            this.mEpisodeId = new String();
            this.mTime = -1;
            this.mVolume = -1;
            this.mDuration = -1;
            this.this$0.mNotifier.simplePlaybackState(s2, false, true, null);
        }
        else if ("PLAYER_RESUME".equals(s)) {
            this.mTimeMarked4StateTransition = System.currentTimeMillis();
            this.mTargetStateTransitionStarted = false;
            this.mCurrentState = "preplay";
            this.this$0.mNotifier.simplePlaybackState(s2, false, true, null);
        }
        else if ("PLAYER_PAUSE".endsWith(s)) {
            this.mTimeMarked4StateTransition = System.currentTimeMillis();
            this.mTargetStateTransitionStarted = true;
            this.mCurrentState = "prepause";
            this.this$0.mNotifier.simplePlaybackState(s2, true, true, null);
        }
        else if ("PLAYER_SKIP".equals(s) || "PLAYER_SET_CURRENT_TIME".equals(s)) {
            this.mTimeMarked4StateTransition = System.currentTimeMillis();
            this.mTargetStateTransitionStarted = false;
            this.mCurrentState = "preseek";
            this.this$0.mNotifier.simplePlaybackState(s2, false, true, null);
        }
        else {
            if (!"PLAYER_GET_CURRENT_STATE".equals(s)) {
                return;
            }
            this.this$0.mNotifier.state(s2, this.mCurrentState, this.mTime, this.mVolume);
            this.this$0.mNotifier.movieMetaData(s2, this.mCatalogId, this.mEpisodeId, this.mDuration);
        }
        this.this$0.mNotifier.state(s2, this.mCurrentState, this.mTime, this.mVolume);
    }
    
    public void setTargetVolume(final int mExpectedVolume) {
        this.mExpectedVolume = mExpectedVolume;
        this.mTimeSetVolume = System.currentTimeMillis();
    }
    
    public void updateState(final PlayerState playerState) {
        if (playerState == null) {
            return;
        }
        String s;
        if (StringUtils.isNotEmpty(this.this$0.mDialUuid)) {
            s = this.this$0.mDialUuid;
        }
        else {
            s = this.this$0.mUuid;
        }
        final String currentState = playerState.getCurrentState();
        if (StringUtils.isEmpty(currentState)) {
            Log.e("nf_mdx", "TargetContext: updateState, new state is null");
            return;
        }
        final String postplayState = playerState.getPostplayState();
        int n;
        if (System.currentTimeMillis() - this.mTimeMarked4StateTransition >= 30000L) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n == 0) {
            boolean b;
            if ("PLAYING".equals(currentState) && this.mTargetStateTransitionStarted) {
                b = true;
            }
            else {
                b = false;
            }
            if ("preplay".equals(this.mCurrentState) && !b) {
                Log.d("nf_mdx", "TargetContext: updateState, still in preplay");
                return;
            }
            if ("prepause".equals(this.mCurrentState) && "PAUSE".equals(currentState)) {
                Log.d("nf_mdx", "TargetContext: updateState, still in prepause");
                return;
            }
            if ("preseek".equals(this.mCurrentState) && !b) {
                Log.d("nf_mdx", "TargetContext: updateState, still in preseek");
                return;
            }
        }
        if ("PLAYING".equals(currentState) && !currentState.equals(this.mCurrentState)) {
            this.this$0.playbackStart(s);
            this.this$0.mNotifier.simplePlaybackState(s, false, false, postplayState);
        }
        else if ("PAUSE".equals(currentState) && !currentState.equals(this.mCurrentState)) {
            this.this$0.playbackStart(s);
            this.this$0.mNotifier.simplePlaybackState(s, true, false, postplayState);
        }
        if (StringUtils.isEmpty(this.mCurrentState)) {
            this.mCurrentState = currentState;
        }
        else if (!currentState.equals(this.mCurrentState)) {
            this.mCurrentState = currentState;
            Log.e("nf_mdx", "TargetContext: updateState, state updated before stateChange from " + this.mCurrentState + " to " + currentState);
        }
        this.notifyState(s, playerState);
    }
}
