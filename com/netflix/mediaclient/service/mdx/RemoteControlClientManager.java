// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RemoteControlClient$MetadataEditor;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import android.media.RemoteControlClient;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.AudioManager$OnAudioFocusChangeListener;

public final class RemoteControlClientManager implements AudioManager$OnAudioFocusChangeListener
{
    private static final String TAG = "nf_mdx_RemoteClient";
    private String mAlbumTitle;
    private final AudioManager mAudioManager;
    private Bitmap mBoxart;
    private final Context mContext;
    private boolean mInTransition;
    private final ComponentName mIntentReceiverComponent;
    private boolean mPaused;
    private RemoteControlClient mRemoteControlClient;
    private String mTitle;
    
    public RemoteControlClientManager(final Context mContext) {
        Log.d("nf_mdx_RemoteClient", "RemoteControlClientManager");
        this.mContext = mContext;
        this.mIntentReceiverComponent = new ComponentName(this.mContext, (Class)MediaButtonIntentReceiver.class);
        this.mAudioManager = (AudioManager)this.mContext.getSystemService("audio");
    }
    
    private void updateMatadata() {
        if (this.mRemoteControlClient != null) {
            final RemoteControlClient$MetadataEditor editMetadata = this.mRemoteControlClient.editMetadata(true);
            if (this.mBoxart != null) {
                editMetadata.putBitmap(100, this.mBoxart);
            }
            if (StringUtils.isNotEmpty(this.mTitle)) {
                editMetadata.putString(7, this.mTitle);
            }
            if (StringUtils.isNotEmpty(this.mAlbumTitle)) {
                editMetadata.putString(1, this.mAlbumTitle);
            }
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
    
    public void setBoxart(final Bitmap bitmap) {
        Log.d("nf_mdx_RemoteClient", "RemoteControlClientManager setBoxart ");
        if (bitmap != null) {
            this.mBoxart = bitmap.copy(bitmap.getConfig(), true);
        }
        this.updateMatadata();
    }
    
    public void setState(final boolean mPaused, final boolean mInTransition) {
        Log.d("nf_mdx_RemoteClient", "RemoteControlClientManager setState " + mPaused + ", " + mInTransition);
        this.mPaused = mPaused;
        this.mInTransition = mInTransition;
        if (this.mRemoteControlClient != null) {
            if (!this.mPaused) {
                this.mRemoteControlClient.setPlaybackState(3);
                return;
            }
            this.mRemoteControlClient.setPlaybackState(2);
        }
    }
    
    public void setTitles(final String mTitle, final String mAlbumTitle) {
        this.mTitle = mTitle;
        this.mAlbumTitle = mAlbumTitle;
        this.updateMatadata();
    }
    
    @TargetApi(18)
    public void start() {
        Log.d("nf_mdx_RemoteClient", "RemoteControlClientManager start");
        if (1 != this.mAudioManager.requestAudioFocus((AudioManager$OnAudioFocusChangeListener)this, 3, 1)) {
            Log.e("nf_mdx_RemoteClient", "can't gain audio focus");
        }
        this.mAudioManager.registerMediaButtonEventReceiver(this.mIntentReceiverComponent);
        final Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
        intent.setComponent(this.mIntentReceiverComponent);
        this.mRemoteControlClient = new RemoteControlClient(PendingIntent.getBroadcast(this.mContext, 0, intent, 0));
        this.mAudioManager.registerRemoteControlClient(this.mRemoteControlClient);
        this.mRemoteControlClient.setPlaybackState(3);
        this.mRemoteControlClient.setTransportControlFlags(308);
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
        }
    }
}
