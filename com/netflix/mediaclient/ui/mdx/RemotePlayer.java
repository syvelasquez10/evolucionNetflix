// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.ui.mdx.events.MdxEventHandler;
import com.netflix.mediaclient.util.ThreadUtils;
import android.content.IntentFilter;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.content.Context;
import com.netflix.mediaclient.service.mdx.MdxAgent$Utils;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import android.content.Intent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.mdx.events.MdxEventHandlerFactory;
import com.netflix.mediaclient.media.Language;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import java.util.Set;
import android.content.BroadcastReceiver;

public class RemotePlayer extends BroadcastReceiver implements RemotePlaybackListener
{
    private static final Set<String> SHOW_MINI_PLAYER_STATES;
    private static final int SKIP_BACK_TIME_SECONDS = -30;
    private static final String STATUS_AUTO_ADVANCE = "AUTO_ADVANCE";
    private static final String STATUS_END_PLAYBACK = "END_PLAYBACK";
    private static final String STATUS_PAUSE = "PAUSE";
    private static final String STATUS_PLAY = "PLAY";
    private static final String STATUS_PLAYING = "PLAYING";
    private static final String STATUS_PREPAUSE = "prepause";
    private static final String STATUS_PREPLAY = "preplay";
    private static final String STATUS_PRESEEK = "preseek";
    private static final String STATUS_PROGRESS = "PROGRESS";
    private static final String STATUS_STALLED = "STALLED";
    private static final String STATUS_STOP = "STOP";
    private static final String TAG = "mdx_remote_player";
    private final NetflixActivity mActivity;
    private Language mCachedLanguage;
    private final RemotePlayer$RemoteTargetUiListener mCallback;
    private MdxTargetCapabilities mCapabilities;
    private int mDuration;
    private final MdxEventHandlerFactory mMdxEventFactory;
    private int mPositionInSeconds;
    private boolean mReady;
    private boolean mReceiverIsRegistered;
    private boolean mRequestForLanguageDataSent;
    private String mState;
    private int mVolume;
    private boolean mVolumeControllEnabled;
    private boolean userDidPlayPause;
    private boolean userDidSeek;
    
    static {
        SHOW_MINI_PLAYER_STATES = new RemotePlayer$1();
    }
    
    public RemotePlayer(final NetflixActivity mActivity, final RemotePlayer$RemoteTargetUiListener mCallback) {
        this.mMdxEventFactory = new MdxEventHandlerFactory();
        this.mState = "PLAY";
        Log.v("mdx_remote_player", "Remote player created");
        if (mActivity == null) {
            throw new IllegalArgumentException("activity can not be null!");
        }
        if (mCallback == null) {
            throw new IllegalArgumentException("owner can not be null!");
        }
        this.mActivity = mActivity;
        this.mCallback = mCallback;
        this.registerReceiver();
    }
    
    private Intent createIntent(final String s) {
        final ServiceManager serviceManager = this.mActivity.getServiceManager();
        if (ServiceManagerUtils.isMdxAgentAvailable(serviceManager)) {
            return MdxAgent$Utils.createIntent((Context)this.mActivity, s, serviceManager.getMdx().getCurrentTarget());
        }
        return null;
    }
    
    private void registerReceiver() {
        final IntentFilter intentFilter = new IntentFilter("com.netflix.mediaclient.intent.action.MDXUPDATE_READY");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.MDXUPDATE_NOTREADY");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.MDXUPDATE_AUDIOSUB");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.MDXUPDATE_CAPABILITY");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.MDXUPDATE_DIALOGCANCEL");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.MDXUPDATE_DIALOGSHOW");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.MDXUPDATE_ERROR");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.MDXUPDATE_MOVIEMETADATA_AVAILABLE");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.MDXUPDATE_MOVIEMETADA");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.MDXUPDATE_STATE");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.MDXUPDATE_TARGETLIST");
        intentFilter.addCategory("com.netflix.mediaclient.intent.category.MDX");
        intentFilter.setPriority(999);
        try {
            this.mActivity.registerReceiver((BroadcastReceiver)this, intentFilter);
            this.mReceiverIsRegistered = true;
        }
        catch (Throwable t) {
            Log.e("mdx_remote_player", "Failed to register ", t);
        }
    }
    
    private void resetLanguageData() {
        Log.v("mdx_remote_player", "Resetting language data...");
        this.mRequestForLanguageDataSent = false;
        this.mCachedLanguage = null;
    }
    
    private void skip(final int n) {
        if (Log.isLoggable()) {
            Log.d("mdx_remote_player", "Skip by " + n);
        }
        final Intent intent = this.createIntent("com.netflix.mediaclient.intent.action.MDX_SKIP");
        if (intent != null) {
            intent.putExtra("time", n);
            this.mActivity.startService(intent);
        }
        this.mState = "PLAY";
    }
    
    private void unregisterReceiver() {
        try {
            if (this.mReceiverIsRegistered) {
                this.mReceiverIsRegistered = false;
                this.mActivity.unregisterReceiver((BroadcastReceiver)this);
            }
        }
        catch (Throwable t) {
            Log.e("mdx_remote_player", "Failed to unregister ", t);
        }
    }
    
    public void cancelDialog() {
        this.mCallback.cancelDialog();
    }
    
    public void changeLanguage(final Language language) {
        if (language == null) {
            Log.e("mdx_remote_player", "Language is null!");
        }
        else {
            if (language.getSelectedAudio() == null) {
                Log.e("mdx_remote_player", "Language selected audio is null!");
                return;
            }
            if (language.getSelectedSubtitle() == null) {
                Log.e("mdx_remote_player", "Language selected subtitle is null!");
                return;
            }
            this.resetLanguageData();
            final Intent intent = this.createIntent("com.netflix.mediaclient.intent.action.MDX_SETAUDIOSUB");
            if (intent != null) {
                intent.putExtra("audioTrackId", language.getSelectedAudio().getId());
                intent.putExtra("subtitleTrackId", language.getSelectedSubtitle().getId());
                this.mActivity.startService(intent);
            }
        }
    }
    
    public void destroy() {
        this.unregisterReceiver();
    }
    
    public void error(final int n, final String s) {
        this.resetLanguageData();
        this.mCallback.error(n, s);
    }
    
    public MdxTargetCapabilities getCapabilities() {
        return this.mCapabilities;
    }
    
    public int getDuration() {
        return this.mDuration;
    }
    
    public Language getLanguage() {
        if (Log.isLoggable()) {
            Log.v("mdx_remote_player", "getLanguage: " + this.mCachedLanguage);
        }
        return this.mCachedLanguage;
    }
    
    public int getPositionInSeconds() {
        return this.mPositionInSeconds;
    }
    
    public String getState() {
        return this.mState;
    }
    
    public int getVolume() {
        return this.mVolume;
    }
    
    public boolean isPaused() {
        return "PAUSE".equalsIgnoreCase(this.mState) || "prepause".equalsIgnoreCase(this.mState);
    }
    
    public boolean isPlaying() {
        return "PLAYING".equalsIgnoreCase(this.mState) || "preplay".equalsIgnoreCase(this.mState);
    }
    
    public boolean isReady() {
        return this.mReady;
    }
    
    public boolean isStalled() {
        return "PLAY".equalsIgnoreCase(this.mState) || "PROGRESS".equalsIgnoreCase(this.mState) || "STALLED".equalsIgnoreCase(this.mState);
    }
    
    public boolean isStopped() {
        return "STOP".equalsIgnoreCase(this.mState);
    }
    
    public boolean isVolumeControllEnabled() {
        return this.mVolumeControllEnabled;
    }
    
    public void mdxStateChanged(final boolean mReady) {
        this.mReady = mReady;
        this.mCallback.mdxStateChanged(mReady);
    }
    
    public void onReceive(final Context context, final Intent intent) {
        ThreadUtils.assertOnMain();
        if (Log.isLoggable()) {
            Log.v("mdx_remote_player", "Received intent " + intent);
        }
        final MdxEventHandler handler = this.mMdxEventFactory.getHandler(intent.getAction());
        if (handler != null) {
            handler.handle(this, intent);
            return;
        }
        Log.e("mdx_remote_player", "Event not supported " + intent.getAction());
    }
    
    public void pause() {
        this.mActivity.startService(this.createIntent("com.netflix.mediaclient.intent.action.MDX_PAUSE"));
        this.mState = "PAUSE";
        this.userDidPlayPause = true;
    }
    
    public void play(final Asset asset) {
        this.resetLanguageData();
        if (!MdxAgent$Utils.playVideo(this.mActivity, asset, false)) {
            return;
        }
        this.userDidPlayPause = true;
        this.mState = "preplay";
    }
    
    public void requestAudioAndSubtitleData() {
        Log.d("mdx_remote_player", "Sending request for subtitle/audio data...");
        this.mActivity.startService(this.createIntent("com.netflix.mediaclient.intent.action.MDX_GETAUDIOSUB"));
        this.mRequestForLanguageDataSent = true;
    }
    
    public void resume() {
        this.mActivity.startService(this.createIntent("com.netflix.mediaclient.intent.action.MDX_RESUME"));
        this.mState = "PLAYING";
        this.userDidPlayPause = true;
    }
    
    public void seek(final int n) {
        if (Log.isLoggable()) {
            Log.d("mdx_remote_player", "Seek to " + n);
        }
        final Intent intent = this.createIntent("com.netflix.mediaclient.intent.action.MDX_SEEK");
        if (intent != null) {
            intent.putExtra("time", n);
            this.mActivity.startService(intent);
        }
        this.mState = "PLAY";
    }
    
    public void sendDialogResponse(final String s) {
        if (Log.isLoggable()) {
            Log.d("mdx_remote_player", "User selected " + s);
        }
        final Intent intent = this.createIntent("com.netflix.mediaclient.intent.action.MDX_DIALOGRESP");
        if (intent != null) {
            intent.putExtra("data", s);
            this.mActivity.startService(intent);
        }
    }
    
    public void setVolume(int n) {
        final int n2 = 100;
        if (this.mVolume <= 0 && n <= 0) {
            Log.d("mdx_remote_player", "Volume is already less than 0 and it can not be turned down more. Do nothing.");
        }
        else {
            if (this.mVolume >= 100 && n >= 100) {
                Log.d("mdx_remote_player", "Volume is already more than 100 and it can not be turned up more. Do nothing.");
                return;
            }
            if (n > 100) {
                n = n2;
            }
            int mVolume = n;
            if (n < 0) {
                mVolume = 0;
            }
            this.mVolume = mVolume;
            if (Log.isLoggable()) {
                Log.d("mdx_remote_player", "Set volume to " + this.mVolume);
            }
            final Intent intent = this.createIntent("com.netflix.mediaclient.intent.action.MDX_SETVOLUME");
            if (intent != null) {
                intent.putExtra("volume", this.mVolume);
                this.mActivity.startService(intent);
            }
        }
    }
    
    public boolean shouldIgnorePlayPauseUpdates() {
        return this.userDidPlayPause || this.userDidSeek;
    }
    
    public boolean shouldIgnoreVideoPositionUpdates() {
        return this.userDidPlayPause || this.userDidSeek;
    }
    
    public void showDialog(final RemoteDialog remoteDialog) {
        this.mCallback.showDialog(remoteDialog);
    }
    
    public void skipBackThirtySeconds() {
        this.skip(-30);
    }
    
    public void stop(final boolean b) {
        Log.d("mdx_remote_player", "stop sending...");
        this.mActivity.startService(this.createIntent("com.netflix.mediaclient.intent.action.MDX_STOP"));
        Log.d("mdx_remote_player", "stop sent");
        this.mState = "STOP";
        if (b) {
            this.mActivity.finish();
        }
    }
    
    public void sync() {
        this.mActivity.startService(this.createIntent("com.netflix.mediaclient.intent.action.MDX_GETCAPABILITY"));
        this.mActivity.startService(this.createIntent("com.netflix.mediaclient.intent.action.MDX_GETSTATE"));
    }
    
    public void targetListChanged() {
        this.mCallback.targetListChanged();
    }
    
    public void updateDuration(final int mDuration) {
        this.mDuration = mDuration;
        this.mCallback.updateDuration(mDuration);
    }
    
    public void updateLanguage(final Language mCachedLanguage) {
        if (Log.isLoggable()) {
            Log.v("mdx_remote_player", "updateLanguage: " + mCachedLanguage);
        }
        this.mCachedLanguage = mCachedLanguage;
        this.mCallback.updateLanguage(mCachedLanguage);
    }
    
    public void updateState(final String mState, final int mPositionInSeconds, final int mVolume) {
        if ("END_PLAYBACK".equalsIgnoreCase(mState)) {
            Log.d("mdx_remote_player", "DESTROY: end of playback");
            this.resetLanguageData();
            this.mCallback.endOfPlayback();
        }
        else if ("PLAYING".equalsIgnoreCase(mState)) {
            if (this.shouldIgnorePlayPauseUpdates()) {
                Log.d("mdx_remote_player", "PLAYING: Do nothing, user just did trickplay");
                return;
            }
            if (!this.mRequestForLanguageDataSent) {
                this.requestAudioAndSubtitleData();
            }
            else {
                Log.d("mdx_remote_player", "Video is playing");
            }
        }
        else if ("PAUSE".equalsIgnoreCase(mState)) {
            if (this.shouldIgnorePlayPauseUpdates()) {
                Log.d("mdx_remote_player", "PAUSE: Do nothing, user just did trickplay");
                return;
            }
            Log.d("mdx_remote_player", "Paused...");
        }
        else {
            if ("prepause".equalsIgnoreCase(mState)) {
                Log.d("mdx_remote_player", "PREPAUSE: Start listening to play/pause from target again");
                this.userDidPlayPause = false;
                return;
            }
            if ("preplay".equalsIgnoreCase(mState)) {
                Log.d("mdx_remote_player", "PREPLAY: Start listening to play/pause from target again");
                this.userDidPlayPause = false;
                return;
            }
            if ("preseek".equalsIgnoreCase(mState)) {
                Log.d("mdx_remote_player", "PRESEEK: Start listening to video position updates from target again");
                this.userDidSeek = false;
                return;
            }
            if ("PLAY".equalsIgnoreCase(mState)) {
                Log.d("mdx_remote_player", "Play, do nothing...");
            }
            else if ("PROGRESS".equalsIgnoreCase(mState)) {
                Log.d("mdx_remote_player", "Progress...");
            }
            else if ("STALLED".equalsIgnoreCase(mState)) {
                Log.d("mdx_remote_player", "Stalled...");
            }
            else {
                if (Log.isLoggable()) {
                    Log.d("mdx_remote_player", "Not supported state " + mState + ". Do nothing.");
                }
                return;
            }
        }
        this.mState = mState;
        this.mPositionInSeconds = mPositionInSeconds;
        this.mVolume = mVolume;
        this.mCallback.updateUi(new RemotePlayer$RemoteTargetState(this, this.isPaused(), this.isStalled(), mPositionInSeconds, this.mDuration, mVolume, RemotePlayer.SHOW_MINI_PLAYER_STATES.contains(mState), null));
    }
    
    public void updateTargetCapabilities(final MdxTargetCapabilities mCapabilities) {
        this.mCapabilities = mCapabilities;
        this.mCallback.updateTargetCapabilities(mCapabilities);
    }
    
    public void updateVideoMetadata() {
        this.mCallback.updateVideoMetadata();
    }
}
