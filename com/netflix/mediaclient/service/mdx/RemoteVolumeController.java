// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import android.media.session.PlaybackState$Builder;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.VolumeProvider;
import android.media.session.MediaSession;
import com.netflix.mediaclient.service.configuration.MdxConfiguration;
import android.content.Context;
import android.content.BroadcastReceiver;
import android.annotation.TargetApi;

@TargetApi(21)
public class RemoteVolumeController
{
    private static final int MDX_VOLUME_MULTIPLIER = 10;
    private static final String TAG = "nf_remote_volume_controller";
    private BroadcastReceiver mCapabilitiesReceiver;
    private Context mContext;
    private boolean mIsVolumeControlSupported;
    private MdxConfiguration mMdxConfiguration;
    private MediaSession mMediaSession;
    private boolean mRemoteControlVisible;
    private int mVolume;
    private VolumeProvider mVolumeProvider;
    
    public RemoteVolumeController(final Context mContext, final MdxConfiguration mMdxConfiguration) {
        this.mContext = mContext;
        this.mMdxConfiguration = mMdxConfiguration;
        this.registerReceiver();
    }
    
    private void initVolumeProvider() {
        this.mVolumeProvider = new RemoteVolumeController$1(this, 2, 10, this.mVolume / 10);
    }
    
    private void registerReceiver() {
        this.unregisterReceiver();
        this.mCapabilitiesReceiver = new RemoteVolumeController$2(this);
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
    
    private boolean shouldNotBeExecuted() {
        if (this.mMdxConfiguration.isRemoteControlLockScreenEnabled()) {
            return false;
        }
        if (this.mRemoteControlVisible) {
            Log.w("nf_remote_volume_controller", "Lock screen is visible and lock screen is NOT enabled! Remove it!");
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
        this.unregisterReceiver();
    }
    
    public void startMediaSession() {
        Log.i("nf_remote_volume_controller", "initMediaSessions");
        if (this.mMediaSession != null) {
            Log.w("nf_remote_volume_controller", "Media session was initialized before");
            this.mMediaSession.release();
        }
        if (!this.mMdxConfiguration.isRemoteControlLockScreenEnabled()) {
            Log.w("nf_remote_volume_controller", "This device doesn't have lock screen enabled");
            return;
        }
        this.mRemoteControlVisible = true;
        if (this.mIsVolumeControlSupported) {
            (this.mMediaSession = new MediaSession(this.mContext.getApplicationContext(), "Netflix media session")).setActive(true);
            this.mMediaSession.setFlags(1);
            if (this.mVolumeProvider == null) {
                this.initVolumeProvider();
            }
            this.mMediaSession.setPlaybackToRemote(this.mVolumeProvider);
            final PlaybackState$Builder playbackState$Builder = new PlaybackState$Builder();
            playbackState$Builder.setState(3, -1L, 1.0f);
            this.mMediaSession.setPlaybackState(playbackState$Builder.build());
            return;
        }
        Log.w("nf_remote_volume_controller", "This device doesn't support MediaSession");
    }
    
    public void stopMediaSession() {
        this.mRemoteControlVisible = false;
        if (this.mMediaSession != null) {
            final PlaybackState$Builder playbackState$Builder = new PlaybackState$Builder();
            playbackState$Builder.setState(1, -1L, 1.0f);
            this.mMediaSession.setPlaybackState(playbackState$Builder.build());
            this.mMediaSession.release();
            this.mMediaSession = null;
        }
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
                if (Log.isLoggable("nf_remote_volume_controller", 4)) {
                    Log.i("nf_remote_volume_controller", "setCurrentVolume: " + this.mVolume);
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
}
