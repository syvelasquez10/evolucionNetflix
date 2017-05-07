// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import android.media.RemoteControlClient$MetadataEditor;
import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import android.view.KeyEvent;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.media.RemoteControlClient;
import android.content.ComponentName;
import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.AudioManager$OnAudioFocusChangeListener;

public final class RemoteControlClientManager implements AudioManager$OnAudioFocusChangeListener
{
    private static final boolean KEEP_EXISTING_METADATA_VALUE = false;
    private static final String TAG = "RemoteControlClientManager";
    private String mAlbumTitle;
    private final AudioManager mAudioManager;
    private Bitmap mBoxart;
    private final Context mContext;
    private VideoDetails mEpisodeDetails;
    private boolean mInTransition;
    private boolean mIsPostPlay;
    private boolean mPaused;
    private Bitmap mPrevBoxart;
    private final ComponentName mProxyReceiverComponentName;
    private final RemoteControlClient mRemoteControlClient;
    private String mTargetUUID;
    private String mTitle;
    private final BroadcastReceiver mediaButtonIntentHandler;
    
    public RemoteControlClientManager(final Context mContext) {
        this.mediaButtonIntentHandler = new BroadcastReceiver() {
            private final PostPlayMediaButtonHandler postPlayMediaButtonHandler = new PostPlayMediaButtonHandler();
            private final StandardMediaButtonHandler standardMediaButtonHandler = new StandardMediaButtonHandler();
            
            public void onReceive(final Context context, final Intent intent) {
                if (!intent.getAction().equals("com.netflix.mediaclient.service.mdx.MediaButtonIntentHandlerProxy")) {
                    Log.w("RemoteControlClientManager", "Received broadcast event but not for Media Button proxy action!");
                }
                else {
                    final KeyEvent keyEvent = (KeyEvent)intent.getExtras().get("android.intent.extra.KEY_EVENT");
                    if (keyEvent.getAction() == 0) {
                        if (Log.isLoggable("RemoteControlClientManager", 2)) {
                            Log.v("RemoteControlClientManager", "received ACTION_MEDIA_BUTTON, key down event, keyCode: " + keyEvent.getKeyCode());
                        }
                        if (RemoteControlClientManager.this.mIsPostPlay) {
                            final int n = -1;
                            final int n2 = -1;
                            int int1 = n;
                            int int2 = n2;
                            if (RemoteControlClientManager.this.mEpisodeDetails instanceof EpisodeDetails) {
                                final EpisodeDetails episodeDetails = (EpisodeDetails)RemoteControlClientManager.this.mEpisodeDetails;
                                int1 = n;
                                int2 = n2;
                                if (episodeDetails.getPlayable() != null) {
                                    int1 = Integer.parseInt(episodeDetails.getPlayable().getParentId());
                                    int2 = Integer.parseInt(episodeDetails.getId());
                                }
                            }
                            this.postPlayMediaButtonHandler.handleButtonDown(context, keyEvent, int1, int2, RemoteControlClientManager.this.mTargetUUID);
                            return;
                        }
                        this.standardMediaButtonHandler.handleButtonDown(context, keyEvent);
                    }
                }
            }
        };
        Log.d("RemoteControlClientManager", "Creating RemoteControlClientManager");
        this.mContext = mContext;
        this.mProxyReceiverComponentName = new ComponentName(this.mContext, (Class)MediaButtonIntentHandlerProxy.class);
        this.mAudioManager = (AudioManager)this.mContext.getSystemService("audio");
        this.mRemoteControlClient = this.createRemoteControlClient();
        LocalBroadcastManager.getInstance(this.mContext).registerReceiver(this.mediaButtonIntentHandler, new IntentFilter("com.netflix.mediaclient.service.mdx.MediaButtonIntentHandlerProxy"));
    }
    
    private RemoteControlClient createRemoteControlClient() {
        Log.d("RemoteControlClientManager", "Creating RemoteControlClient");
        final Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
        intent.setComponent(this.mProxyReceiverComponentName);
        return new RemoteControlClient(PendingIntent.getBroadcast(this.mContext, 0, intent, 0));
    }
    
    @TargetApi(18)
    private static void setupButtons(final RemoteControlClient remoteControlClient, final boolean b) {
        remoteControlClient.setTransportControlFlags(308);
        if (b) {
            remoteControlClient.setPlaybackState(2);
            return;
        }
        remoteControlClient.setPlaybackState(3);
    }
    
    private void updateMetadata() {
        if (this.mRemoteControlClient != null) {
            Log.d("RemoteControlClientManager", "Updating RemoteControlClient metadata");
            final RemoteControlClient$MetadataEditor editMetadata = this.mRemoteControlClient.editMetadata(false);
            editMetadata.putBitmap(100, this.mBoxart);
            editMetadata.putString(7, this.mTitle);
            editMetadata.putString(1, this.mAlbumTitle);
            editMetadata.apply();
        }
    }
    
    public void destroy() {
        LocalBroadcastManager.getInstance(this.mContext).unregisterReceiver(this.mediaButtonIntentHandler);
    }
    
    public boolean isInTransition() {
        return this.mInTransition;
    }
    
    public boolean isPaused() {
        return this.mPaused;
    }
    
    public void onAudioFocusChange(final int n) {
        if (n == 1 || n == 2 || n == 3) {
            if (Log.isLoggable("RemoteControlClientManager", 3)) {
                Log.d("RemoteControlClientManager", "onAudioFocusChange gained " + n);
            }
        }
        else if (Log.isLoggable("RemoteControlClientManager", 3)) {
            Log.d("RemoteControlClientManager", "onAudioFocusChange lost " + n);
        }
    }
    
    public void setBoxart(final Bitmap mPrevBoxart) {
        if (mPrevBoxart != null && mPrevBoxart != this.mPrevBoxart) {
            Log.d("RemoteControlClientManager", "setBoxart - handling new bitmap");
            this.mPrevBoxart = mPrevBoxart;
            this.mBoxart = mPrevBoxart.copy(mPrevBoxart.getConfig(), false);
            this.updateMetadata();
        }
    }
    
    public void setState(final boolean mPaused, final boolean mInTransition, final boolean mIsPostPlay) {
        if (Log.isLoggable("RemoteControlClientManager", 3)) {
            Log.d("RemoteControlClientManager", "setState, paused: " + mPaused + ", transitioning: " + mInTransition + ", inPostPlay: " + mIsPostPlay);
        }
        this.mPaused = mPaused;
        this.mInTransition = mInTransition;
        this.mIsPostPlay = mIsPostPlay;
        if (this.mRemoteControlClient != null) {
            if (!this.mPaused && !mIsPostPlay) {
                this.mRemoteControlClient.setPlaybackState(3);
                return;
            }
            this.mRemoteControlClient.setPlaybackState(2);
        }
    }
    
    public void setTitles(final String mTitle, final String mAlbumTitle) {
        if (Log.isLoggable("RemoteControlClientManager", 3)) {
            Log.d("RemoteControlClientManager", "setTitles - title: " + mTitle + ", album: " + mAlbumTitle);
        }
        this.mTitle = mTitle;
        this.mAlbumTitle = mAlbumTitle;
        this.updateMetadata();
    }
    
    public void start(final boolean mIsPostPlay, final VideoDetails mEpisodeDetails, final String mTargetUUID) {
        if (Log.isLoggable("RemoteControlClientManager", 3)) {
            Log.d("RemoteControlClientManager", "start, isPostPlay: " + mIsPostPlay + ", episodeDetails: " + mEpisodeDetails + ", uuid: " + mTargetUUID);
        }
        this.mAudioManager.registerMediaButtonEventReceiver(this.mProxyReceiverComponentName);
        this.mAudioManager.registerRemoteControlClient(this.mRemoteControlClient);
        if (this.mAudioManager.requestAudioFocus((AudioManager$OnAudioFocusChangeListener)this, 3, 1) != 1) {
            Log.e("RemoteControlClientManager", "Can't gain audio focus");
        }
        this.mIsPostPlay = mIsPostPlay;
        this.mEpisodeDetails = mEpisodeDetails;
        this.mTargetUUID = mTargetUUID;
        setupButtons(this.mRemoteControlClient, mIsPostPlay);
    }
    
    public void stop() {
        Log.d("RemoteControlClientManager", "stop - clearing all state");
        this.mAudioManager.abandonAudioFocus((AudioManager$OnAudioFocusChangeListener)this);
        this.mAudioManager.unregisterMediaButtonEventReceiver(this.mProxyReceiverComponentName);
        this.mAudioManager.unregisterRemoteControlClient(this.mRemoteControlClient);
        this.mTitle = null;
        this.mAlbumTitle = null;
        this.mPrevBoxart = null;
        this.mBoxart = null;
        this.mIsPostPlay = false;
        this.mEpisodeDetails = null;
        this.mTargetUUID = null;
    }
    
    public static class MediaButtonIntentHandlerProxy extends BroadcastReceiver
    {
        public static final String RESEND_MEDIA_BUTTON_ACTION = "com.netflix.mediaclient.service.mdx.MediaButtonIntentHandlerProxy";
        
        public void onReceive(final Context context, final Intent intent) {
            Log.d("RemoteControlClientManager", "Re-sending media button event as local broadcast...");
            intent.setAction("com.netflix.mediaclient.service.mdx.MediaButtonIntentHandlerProxy");
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
    }
}
