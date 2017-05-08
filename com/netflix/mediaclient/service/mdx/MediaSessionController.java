// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.util.StringUtils;
import android.media.MediaMetadata;
import android.os.Handler;
import android.media.MediaMetadata$Builder;
import android.graphics.Bitmap;
import android.media.session.MediaSession$Callback;
import android.media.session.MediaSession$Token;
import android.media.session.PlaybackState$Builder;
import android.content.Intent;
import android.content.IntentFilter;
import com.netflix.mediaclient.Log;
import android.media.VolumeProvider;
import android.media.session.MediaSession;
import com.netflix.mediaclient.service.configuration.MdxConfiguration;
import android.content.Context;
import android.content.BroadcastReceiver;
import android.annotation.TargetApi;

@TargetApi(21)
public class MediaSessionController
{
    private static final int MDX_VOLUME_MULTIPLIER = 10;
    private static final String TAG = "nf_media_session_controller";
    private MdxAgent mAgent;
    private BroadcastReceiver mCapabilitiesReceiver;
    private Context mContext;
    private boolean mIsPostPlay;
    private boolean mIsVolumeControlSupported;
    private MdxConfiguration mMdxConfiguration;
    private MediaSession mMediaSession;
    private int mPostponedState;
    private boolean mRemoteControlVisible;
    private String mTitle;
    private int mVolume;
    private VolumeProvider mVolumeProvider;
    
    public MediaSessionController(final MdxAgent mAgent, final MdxConfiguration mMdxConfiguration) {
        this.mTitle = "";
        this.mPostponedState = 0;
        Log.i("nf_media_session_controller", "Initializing MediaSessionController");
        this.mContext = mAgent.getContext();
        this.mAgent = mAgent;
        this.mMdxConfiguration = mMdxConfiguration;
        if (this.mMediaSession != null) {
            Log.w("nf_media_session_controller", "MediaSession was not destroyed correctly! Destroying it now.");
            this.destroy();
        }
        this.mMediaSession = new MediaSession(this.mContext.getApplicationContext(), "Netflix media session");
        this.registerReceiver();
    }
    
    private void initVolumeProvider() {
        this.mVolumeProvider = new MediaSessionController$3(this, 2, 10, this.mVolume / 10);
    }
    
    private void registerReceiver() {
        this.unregisterReceiver();
        this.mCapabilitiesReceiver = new MediaSessionController$4(this);
        final IntentFilter intentFilter = new IntentFilter("com.netflix.mediaclient.intent.action.MDXUPDATE_CAPABILITY");
        intentFilter.addCategory("com.netflix.mediaclient.intent.category.MDX");
        this.mContext.getApplicationContext().registerReceiver(this.mCapabilitiesReceiver, intentFilter);
    }
    
    public static void sendVolumeUpdateBroadcast(final Context context, final int n) {
        final Intent intent = MdxAgent$Utils.createIntent(context, "com.netflix.mediaclient.intent.action.MDX_SETVOLUME", null);
        if (intent != null) {
            intent.putExtra("volume", n);
            context.startService(intent);
        }
    }
    
    private void setState(final int n) {
        if (this.mMediaSession != null) {
            final PlaybackState$Builder playbackState$Builder = new PlaybackState$Builder();
            if (this.mIsPostPlay) {
                playbackState$Builder.setActions(4L);
            }
            else {
                playbackState$Builder.setActions(512L);
            }
            playbackState$Builder.setState(n, -1L, 1.0f);
            this.mMediaSession.setPlaybackState(playbackState$Builder.build());
        }
    }
    
    private boolean shouldNotBeExecuted() {
        if (this.mMdxConfiguration.isRemoteControlLockScreenEnabled()) {
            return false;
        }
        if (this.mRemoteControlVisible) {
            Log.w("nf_media_session_controller", "Lock screen is visible and lock screen is NOT enabled! Remove it!");
            this.stopMediaSession();
        }
        return true;
    }
    
    private void unregisterReceiver() {
        if (this.mCapabilitiesReceiver != null) {
            this.mContext.getApplicationContext().unregisterReceiver(this.mCapabilitiesReceiver);
            this.mCapabilitiesReceiver = null;
        }
    }
    
    public void destroy() {
        Log.d("nf_media_session_controller", "destroy");
        this.unregisterReceiver();
        this.mMediaSession.release();
        this.mMediaSession = null;
    }
    
    public MediaSession$Token getMediaSessionToken() {
        return this.mMediaSession.getSessionToken();
    }
    
    public void startMediaSession() {
        Log.i("nf_media_session_controller", "startMediaSession");
        this.mRemoteControlVisible = true;
        this.mMediaSession.setActive(true);
        this.mMediaSession.setFlags(1);
        if (this.mIsVolumeControlSupported && this.mVolumeProvider == null) {
            this.initVolumeProvider();
            this.mMediaSession.setPlaybackToRemote(this.mVolumeProvider);
        }
        this.mMediaSession.setCallback((MediaSession$Callback)new MediaSessionController$2(this));
        this.updateState(false, false);
    }
    
    public void stopMediaSession() {
        Log.i("nf_media_session_controller", "stopMediaSession");
        this.mRemoteControlVisible = false;
        this.setState(1);
        this.mMediaSession.setActive(false);
    }
    
    public void updateCurrentVolume(final int mVolume, final boolean b) {
        this.mVolume = mVolume;
        if (this.mVolume > 100) {
            this.mVolume = 100;
        }
        if (this.mVolume < 0) {
            this.mVolume = 0;
        }
        if (!this.shouldNotBeExecuted()) {
            if (this.mVolumeProvider != null) {
                if (Log.isLoggable()) {
                    Log.i("nf_media_session_controller", "setCurrentVolume: " + this.mVolume);
                }
                if (this.mVolumeProvider != null && this.mMediaSession != null) {
                    this.mVolumeProvider.setCurrentVolume(this.mVolume / 10);
                }
            }
            if (b) {
                sendVolumeUpdateBroadcast(this.mContext, this.mVolume);
            }
        }
    }
    
    public void updateMetadata(final Bitmap bitmap) {
        if (Log.isLoggable()) {
            Log.i("nf_media_session_controller", "updateMetadata() boxart: " + bitmap);
        }
        final MediaMetadata metadata = this.mMediaSession.getController().getMetadata();
        MediaMetadata$Builder mediaMetadata$Builder;
        if (metadata == null) {
            mediaMetadata$Builder = new MediaMetadata$Builder();
        }
        else {
            mediaMetadata$Builder = new MediaMetadata$Builder(metadata);
        }
        mediaMetadata$Builder.putBitmap("android.media.metadata.ALBUM_ART", bitmap);
        this.mMediaSession.setMetadata(mediaMetadata$Builder.build());
        if (this.mPostponedState > 0) {
            new Handler().post((Runnable)new MediaSessionController$1(this));
            this.mPostponedState = 0;
        }
    }
    
    public void updateMetadata(final String mTitle, final boolean mIsPostPlay) {
        if (Log.isLoggable()) {
            Log.i("nf_media_session_controller", "updateMetadata() title: " + mTitle + "; isPostPlay: " + mIsPostPlay);
        }
        if (StringUtils.isNotEmpty(mTitle)) {
            this.mTitle = mTitle;
        }
        this.mIsPostPlay = mIsPostPlay;
        final MediaMetadata metadata = this.mMediaSession.getController().getMetadata();
        MediaMetadata$Builder mediaMetadata$Builder;
        if (metadata == null) {
            mediaMetadata$Builder = new MediaMetadata$Builder();
        }
        else {
            mediaMetadata$Builder = new MediaMetadata$Builder(metadata);
        }
        mediaMetadata$Builder.putText("android.media.metadata.TITLE", (CharSequence)this.mTitle);
        int n;
        if (this.mIsPostPlay) {
            n = 2131231129;
        }
        else {
            n = 2131231305;
        }
        mediaMetadata$Builder.putText("android.media.metadata.ALBUM", this.mContext.getText(n));
        this.mMediaSession.setMetadata(mediaMetadata$Builder.build());
    }
    
    public void updateState(final boolean b, final boolean mIsPostPlay) {
        int state = 2;
        if (Log.isLoggable()) {
            Log.i("nf_media_session_controller", "updateState paused: " + b + "; inPostPlay: " + mIsPostPlay);
        }
        this.mIsPostPlay = mIsPostPlay;
        if (this.mIsPostPlay) {
            this.mPostponedState = 2;
            return;
        }
        if (!b) {
            state = 3;
        }
        this.setState(state);
    }
}
