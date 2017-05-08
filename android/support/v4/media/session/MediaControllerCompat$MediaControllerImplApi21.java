// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.os.IBinder$DeathRecipient;
import java.util.ArrayList;
import android.app.PendingIntent;
import android.util.Log;
import android.os.Build$VERSION;
import android.support.v4.media.MediaMetadataCompat;
import android.view.KeyEvent;
import android.os.ResultReceiver;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.content.Context;
import java.util.List;
import java.util.HashMap;

class MediaControllerCompat$MediaControllerImplApi21 implements MediaControllerCompat$MediaControllerImpl
{
    private HashMap<MediaControllerCompat$Callback, MediaControllerCompat$MediaControllerImplApi21$ExtraCallback> mCallbackMap;
    protected final Object mControllerObj;
    private IMediaSession mExtraBinder;
    private List<MediaControllerCompat$Callback> mPendingCallbacks;
    
    public MediaControllerCompat$MediaControllerImplApi21(final Context context, final MediaSessionCompat$Token mediaSessionCompat$Token) {
        this.mCallbackMap = new HashMap<MediaControllerCompat$Callback, MediaControllerCompat$MediaControllerImplApi21$ExtraCallback>();
        this.mControllerObj = MediaControllerCompatApi21.fromToken(context, mediaSessionCompat$Token.getToken());
        if (this.mControllerObj == null) {
            throw new RemoteException();
        }
        this.requestExtraBinder();
    }
    
    public MediaControllerCompat$MediaControllerImplApi21(final Context context, final MediaSessionCompat mediaSessionCompat) {
        this.mCallbackMap = new HashMap<MediaControllerCompat$Callback, MediaControllerCompat$MediaControllerImplApi21$ExtraCallback>();
        this.mControllerObj = MediaControllerCompatApi21.fromToken(context, mediaSessionCompat.getSessionToken().getToken());
        this.requestExtraBinder();
    }
    
    private void requestExtraBinder() {
        this.sendCommand("android.support.v4.media.session.command.GET_EXTRA_BINDER", null, new MediaControllerCompat$MediaControllerImplApi21$1(this, new Handler()));
    }
    
    @Override
    public void adjustVolume(final int n, final int n2) {
        MediaControllerCompatApi21.adjustVolume(this.mControllerObj, n, n2);
    }
    
    @Override
    public boolean dispatchMediaButtonEvent(final KeyEvent keyEvent) {
        return MediaControllerCompatApi21.dispatchMediaButtonEvent(this.mControllerObj, keyEvent);
    }
    
    @Override
    public Bundle getExtras() {
        return MediaControllerCompatApi21.getExtras(this.mControllerObj);
    }
    
    @Override
    public long getFlags() {
        return MediaControllerCompatApi21.getFlags(this.mControllerObj);
    }
    
    @Override
    public Object getMediaController() {
        return this.mControllerObj;
    }
    
    @Override
    public MediaMetadataCompat getMetadata() {
        final Object metadata = MediaControllerCompatApi21.getMetadata(this.mControllerObj);
        if (metadata != null) {
            return MediaMetadataCompat.fromMediaMetadata(metadata);
        }
        return null;
    }
    
    @Override
    public String getPackageName() {
        return MediaControllerCompatApi21.getPackageName(this.mControllerObj);
    }
    
    @Override
    public MediaControllerCompat$PlaybackInfo getPlaybackInfo() {
        final Object playbackInfo = MediaControllerCompatApi21.getPlaybackInfo(this.mControllerObj);
        if (playbackInfo != null) {
            return new MediaControllerCompat$PlaybackInfo(MediaControllerCompatApi21$PlaybackInfo.getPlaybackType(playbackInfo), MediaControllerCompatApi21$PlaybackInfo.getLegacyAudioStream(playbackInfo), MediaControllerCompatApi21$PlaybackInfo.getVolumeControl(playbackInfo), MediaControllerCompatApi21$PlaybackInfo.getMaxVolume(playbackInfo), MediaControllerCompatApi21$PlaybackInfo.getCurrentVolume(playbackInfo));
        }
        return null;
    }
    
    @Override
    public PlaybackStateCompat getPlaybackState() {
        if (Build$VERSION.SDK_INT < 22 && this.mExtraBinder != null) {
            try {
                return this.mExtraBinder.getPlaybackState();
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in getPlaybackState. " + ex);
            }
        }
        final Object playbackState = MediaControllerCompatApi21.getPlaybackState(this.mControllerObj);
        if (playbackState != null) {
            return PlaybackStateCompat.fromPlaybackState(playbackState);
        }
        return null;
    }
    
    @Override
    public List<MediaSessionCompat$QueueItem> getQueue() {
        final List<Object> queue = MediaControllerCompatApi21.getQueue(this.mControllerObj);
        if (queue != null) {
            return MediaSessionCompat$QueueItem.fromQueueItemList(queue);
        }
        return null;
    }
    
    @Override
    public CharSequence getQueueTitle() {
        return MediaControllerCompatApi21.getQueueTitle(this.mControllerObj);
    }
    
    @Override
    public int getRatingType() {
        if (Build$VERSION.SDK_INT < 22 && this.mExtraBinder != null) {
            try {
                return this.mExtraBinder.getRatingType();
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in getRatingType. " + ex);
            }
        }
        return MediaControllerCompatApi21.getRatingType(this.mControllerObj);
    }
    
    @Override
    public PendingIntent getSessionActivity() {
        return MediaControllerCompatApi21.getSessionActivity(this.mControllerObj);
    }
    
    @Override
    public MediaControllerCompat$TransportControls getTransportControls() {
        final Object transportControls = MediaControllerCompatApi21.getTransportControls(this.mControllerObj);
        if (transportControls != null) {
            return new MediaControllerCompat$TransportControlsApi21(transportControls);
        }
        return null;
    }
    
    @Override
    public final void registerCallback(final MediaControllerCompat$Callback mediaControllerCompat$Callback, final Handler handler) {
        MediaControllerCompatApi21.registerCallback(this.mControllerObj, mediaControllerCompat$Callback.mCallbackObj, handler);
        if (this.mExtraBinder != null) {
            mediaControllerCompat$Callback.setHandler(handler);
            final MediaControllerCompat$MediaControllerImplApi21$ExtraCallback mediaControllerCompat$MediaControllerImplApi21$ExtraCallback = new MediaControllerCompat$MediaControllerImplApi21$ExtraCallback(this, mediaControllerCompat$Callback);
            this.mCallbackMap.put(mediaControllerCompat$Callback, mediaControllerCompat$MediaControllerImplApi21$ExtraCallback);
            mediaControllerCompat$Callback.mHasExtraCallback = true;
            try {
                this.mExtraBinder.registerCallbackListener(mediaControllerCompat$MediaControllerImplApi21$ExtraCallback);
                return;
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in registerCallback. " + ex);
                return;
            }
        }
        if (this.mPendingCallbacks == null) {
            this.mPendingCallbacks = new ArrayList<MediaControllerCompat$Callback>();
        }
        mediaControllerCompat$Callback.setHandler(handler);
        this.mPendingCallbacks.add(mediaControllerCompat$Callback);
    }
    
    @Override
    public void sendCommand(final String s, final Bundle bundle, final ResultReceiver resultReceiver) {
        MediaControllerCompatApi21.sendCommand(this.mControllerObj, s, bundle, resultReceiver);
    }
    
    @Override
    public void setVolumeTo(final int n, final int n2) {
        MediaControllerCompatApi21.setVolumeTo(this.mControllerObj, n, n2);
    }
    
    @Override
    public final void unregisterCallback(final MediaControllerCompat$Callback mediaControllerCompat$Callback) {
        MediaControllerCompatApi21.unregisterCallback(this.mControllerObj, mediaControllerCompat$Callback.mCallbackObj);
        if (this.mExtraBinder != null) {
            try {
                final MediaControllerCompat$MediaControllerImplApi21$ExtraCallback mediaControllerCompat$MediaControllerImplApi21$ExtraCallback = this.mCallbackMap.remove(mediaControllerCompat$Callback);
                if (mediaControllerCompat$MediaControllerImplApi21$ExtraCallback != null) {
                    this.mExtraBinder.unregisterCallbackListener(mediaControllerCompat$MediaControllerImplApi21$ExtraCallback);
                }
                return;
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in unregisterCallback. " + ex);
                return;
            }
        }
        if (this.mPendingCallbacks == null) {
            this.mPendingCallbacks = new ArrayList<MediaControllerCompat$Callback>();
        }
        this.mPendingCallbacks.remove(mediaControllerCompat$Callback);
    }
}
