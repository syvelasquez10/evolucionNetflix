// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import android.media.RemoteControlClient$MetadataEditor;
import android.annotation.TargetApi;
import android.app.PendingIntent;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import android.content.Intent;
import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
import com.netflix.mediaclient.Log;
import android.media.RemoteControlClient;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.AudioManager$OnAudioFocusChangeListener;

public final class RemoteControlClientManager implements AudioManager$OnAudioFocusChangeListener
{
    private static final boolean KEEP_EXISTING_METADATA_VALUE = false;
    private static final String TAG = "nf_mdx_RemoteClient";
    private String mAlbumTitle;
    private final AudioManager mAudioManager;
    private Bitmap mBoxart;
    private final Context mContext;
    private boolean mInTransition;
    private final ComponentName mIntentReceiverComponent;
    private final ComponentName mIntentReceiverComponentPostPlay;
    private boolean mPaused;
    private Bitmap mPrevBoxart;
    private RemoteControlClient mRemoteControlClient;
    private String mTitle;
    private boolean mWasInPostPlay;
    
    public RemoteControlClientManager(final Context mContext) {
        Log.d("nf_mdx_RemoteClient", "RemoteControlClientManager");
        this.mContext = mContext;
        this.mIntentReceiverComponent = new ComponentName(this.mContext, (Class)MediaButtonIntentReceiver.class);
        this.mIntentReceiverComponentPostPlay = new ComponentName(this.mContext, (Class)PostPlayMediaButtonIntentReceiver.class);
        this.mAudioManager = (AudioManager)this.mContext.getSystemService("audio");
    }
    
    private RemoteControlClient createRemoteControlClient(final boolean b, final VideoDetails videoDetails, final String s) {
        Log.d("nf_mdx_RemoteClient", "Creating RemoteControlClient");
        if (b && videoDetails != null) {
            this.mAudioManager.registerMediaButtonEventReceiver(this.mIntentReceiverComponentPostPlay);
            final Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
            intent.setComponent(this.mIntentReceiverComponentPostPlay);
            if (videoDetails instanceof EpisodeDetails) {
                intent.putExtra("catalogId", Integer.parseInt(videoDetails.getPlayable().getParentId()));
                intent.putExtra("episodeId", Integer.parseInt(((EpisodeDetails)videoDetails).getNextEpisodeId()));
            }
            intent.putExtra("uuid", s);
            return new RemoteControlClient(PendingIntent.getBroadcast(this.mContext, 0, intent, 134217728));
        }
        this.mAudioManager.registerMediaButtonEventReceiver(this.mIntentReceiverComponent);
        final Intent intent2 = new Intent("android.intent.action.MEDIA_BUTTON");
        intent2.setComponent(this.mIntentReceiverComponent);
        return new RemoteControlClient(PendingIntent.getBroadcast(this.mContext, 0, intent2, 0));
    }
    
    @TargetApi(18)
    private static void setupButtons(final RemoteControlClient remoteControlClient, final boolean b) {
        if (b) {
            remoteControlClient.setTransportControlFlags(308);
            remoteControlClient.setPlaybackState(2);
            return;
        }
        remoteControlClient.setTransportControlFlags(308);
        remoteControlClient.setPlaybackState(3);
    }
    
    private void updateMetadata() {
        if (this.mRemoteControlClient != null) {
            final RemoteControlClient$MetadataEditor editMetadata = this.mRemoteControlClient.editMetadata(false);
            editMetadata.putBitmap(100, this.mBoxart);
            editMetadata.putString(7, this.mTitle);
            editMetadata.putString(1, this.mAlbumTitle);
            editMetadata.apply();
        }
    }
    
    public boolean isInTransition() {
        return this.mInTransition;
    }
    
    public boolean isPaused() {
        return this.mPaused;
    }
    
    public void onAudioFocusChange(final int n) {
        if (n == 1 || n == 2 || n == 3) {
            if (Log.isLoggable("nf_mdx_RemoteClient", 3)) {
                Log.d("nf_mdx_RemoteClient", "onAudioFocusChange gained " + n);
            }
        }
        else if (Log.isLoggable("nf_mdx_RemoteClient", 3)) {
            Log.d("nf_mdx_RemoteClient", "onAudioFocusChange lost " + n);
        }
    }
    
    public void setBoxart(final Bitmap mPrevBoxart) {
        if (mPrevBoxart != null && mPrevBoxart != this.mPrevBoxart) {
            Log.d("nf_mdx_RemoteClient", "RemoteControlClientManager setBoxart - handling new bitmap");
            this.mPrevBoxart = mPrevBoxart;
            this.mBoxart = mPrevBoxart.copy(mPrevBoxart.getConfig(), false);
            this.updateMetadata();
        }
    }
    
    public void setState(final boolean mPaused, final boolean mInTransition, final boolean b) {
        Log.d("nf_mdx_RemoteClient", "RemoteControlClientManager setState " + mPaused + ", " + mInTransition);
        this.mPaused = mPaused;
        this.mInTransition = mInTransition;
        if (this.mRemoteControlClient != null) {
            if (!this.mPaused && !b) {
                this.mRemoteControlClient.setPlaybackState(3);
                return;
            }
            this.mRemoteControlClient.setPlaybackState(2);
        }
    }
    
    public void setTitles(final String mTitle, final String mAlbumTitle) {
        if (Log.isLoggable("nf_mdx_RemoteClient", 3)) {
            Log.d("nf_mdx_RemoteClient", "RemoteControlClientManager setTitles - title: " + mTitle + ", album: " + mAlbumTitle);
        }
        this.mTitle = mTitle;
        this.mAlbumTitle = mAlbumTitle;
        this.updateMetadata();
    }
    
    public void start(final boolean mWasInPostPlay, final VideoDetails videoDetails, final String s) {
        Log.d("nf_mdx_RemoteClient", "RemoteControlClientManager start");
        if (1 != this.mAudioManager.requestAudioFocus((AudioManager$OnAudioFocusChangeListener)this, 3, 1)) {
            Log.e("nf_mdx_RemoteClient", "can't gain audio focus");
        }
        if (this.mRemoteControlClient == null || this.mWasInPostPlay != mWasInPostPlay) {
            this.mRemoteControlClient = this.createRemoteControlClient(mWasInPostPlay, videoDetails, s);
            this.mAudioManager.registerRemoteControlClient(this.mRemoteControlClient);
        }
        setupButtons(this.mRemoteControlClient, mWasInPostPlay);
        this.mWasInPostPlay = mWasInPostPlay;
    }
    
    public void stop() {
        Log.d("nf_mdx_RemoteClient", "RemoteControlClientManager stop");
        this.mAudioManager.abandonAudioFocus((AudioManager$OnAudioFocusChangeListener)this);
        if (this.mRemoteControlClient != null) {
            this.mAudioManager.unregisterMediaButtonEventReceiver(this.mIntentReceiverComponent);
            this.mAudioManager.unregisterRemoteControlClient(this.mRemoteControlClient);
            this.mRemoteControlClient = null;
            this.mTitle = null;
            this.mAlbumTitle = null;
            this.mPrevBoxart = null;
        }
    }
}
