// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.text.TextUtils;
import android.os.ResultReceiver;
import android.os.Handler;
import android.app.PendingIntent;
import java.util.List;
import android.support.v4.media.MediaMetadataCompat;
import android.os.Bundle;
import android.view.KeyEvent;
import android.support.v4.app.SupportActivity$ExtraData;
import android.os.RemoteException;
import android.util.Log;
import android.support.v4.app.SupportActivity;
import android.app.Activity;
import android.os.Build$VERSION;
import android.content.Context;

public final class MediaControllerCompat
{
    static final String COMMAND_GET_EXTRA_BINDER = "android.support.v4.media.session.command.GET_EXTRA_BINDER";
    static final String TAG = "MediaControllerCompat";
    private final MediaControllerCompat$MediaControllerImpl mImpl;
    private final MediaSessionCompat$Token mToken;
    
    public MediaControllerCompat(final Context context, final MediaSessionCompat$Token mToken) {
        if (mToken == null) {
            throw new IllegalArgumentException("sessionToken must not be null");
        }
        this.mToken = mToken;
        if (Build$VERSION.SDK_INT >= 24) {
            this.mImpl = new MediaControllerCompat$MediaControllerImplApi24(context, mToken);
            return;
        }
        if (Build$VERSION.SDK_INT >= 23) {
            this.mImpl = new MediaControllerCompat$MediaControllerImplApi23(context, mToken);
            return;
        }
        if (Build$VERSION.SDK_INT >= 21) {
            this.mImpl = new MediaControllerCompat$MediaControllerImplApi21(context, mToken);
            return;
        }
        this.mImpl = new MediaControllerCompat$MediaControllerImplBase(this.mToken);
    }
    
    public MediaControllerCompat(final Context context, final MediaSessionCompat mediaSessionCompat) {
        if (mediaSessionCompat == null) {
            throw new IllegalArgumentException("session must not be null");
        }
        this.mToken = mediaSessionCompat.getSessionToken();
        if (Build$VERSION.SDK_INT >= 24) {
            this.mImpl = new MediaControllerCompat$MediaControllerImplApi24(context, mediaSessionCompat);
            return;
        }
        if (Build$VERSION.SDK_INT >= 23) {
            this.mImpl = new MediaControllerCompat$MediaControllerImplApi23(context, mediaSessionCompat);
            return;
        }
        if (Build$VERSION.SDK_INT >= 21) {
            this.mImpl = new MediaControllerCompat$MediaControllerImplApi21(context, mediaSessionCompat);
            return;
        }
        this.mImpl = new MediaControllerCompat$MediaControllerImplBase(this.mToken);
    }
    
    public static MediaControllerCompat getMediaController(final Activity activity) {
        final MediaControllerCompat mediaControllerCompat = null;
        MediaControllerCompat mediaControllerCompat2;
        if (activity instanceof SupportActivity) {
            final MediaControllerCompat$MediaControllerExtraData mediaControllerCompat$MediaControllerExtraData = ((SupportActivity)activity).getExtraData(MediaControllerCompat$MediaControllerExtraData.class);
            MediaControllerCompat mediaController;
            if (mediaControllerCompat$MediaControllerExtraData != null) {
                mediaController = mediaControllerCompat$MediaControllerExtraData.getMediaController();
            }
            else {
                mediaController = null;
            }
            mediaControllerCompat2 = mediaController;
        }
        else {
            mediaControllerCompat2 = mediaControllerCompat;
            if (Build$VERSION.SDK_INT >= 21) {
                final Object mediaController2 = MediaControllerCompatApi21.getMediaController(activity);
                mediaControllerCompat2 = mediaControllerCompat;
                if (mediaController2 != null) {
                    final Object sessionToken = MediaControllerCompatApi21.getSessionToken(mediaController2);
                    try {
                        return new MediaControllerCompat((Context)activity, MediaSessionCompat$Token.fromToken(sessionToken));
                    }
                    catch (RemoteException ex) {
                        Log.e("MediaControllerCompat", "Dead object in getMediaController. " + ex);
                        return null;
                    }
                }
            }
        }
        return mediaControllerCompat2;
    }
    
    public static void setMediaController(final Activity activity, final MediaControllerCompat mediaControllerCompat) {
        if (activity instanceof SupportActivity) {
            ((SupportActivity)activity).putExtraData(new MediaControllerCompat$MediaControllerExtraData(mediaControllerCompat));
        }
        if (Build$VERSION.SDK_INT >= 21) {
            Object fromToken = null;
            if (mediaControllerCompat != null) {
                fromToken = MediaControllerCompatApi21.fromToken((Context)activity, mediaControllerCompat.getSessionToken().getToken());
            }
            MediaControllerCompatApi21.setMediaController(activity, fromToken);
        }
    }
    
    public void adjustVolume(final int n, final int n2) {
        this.mImpl.adjustVolume(n, n2);
    }
    
    public boolean dispatchMediaButtonEvent(final KeyEvent keyEvent) {
        if (keyEvent == null) {
            throw new IllegalArgumentException("KeyEvent may not be null");
        }
        return this.mImpl.dispatchMediaButtonEvent(keyEvent);
    }
    
    public Bundle getExtras() {
        return this.mImpl.getExtras();
    }
    
    public long getFlags() {
        return this.mImpl.getFlags();
    }
    
    public Object getMediaController() {
        return this.mImpl.getMediaController();
    }
    
    public MediaMetadataCompat getMetadata() {
        return this.mImpl.getMetadata();
    }
    
    public String getPackageName() {
        return this.mImpl.getPackageName();
    }
    
    public MediaControllerCompat$PlaybackInfo getPlaybackInfo() {
        return this.mImpl.getPlaybackInfo();
    }
    
    public PlaybackStateCompat getPlaybackState() {
        return this.mImpl.getPlaybackState();
    }
    
    public List<MediaSessionCompat$QueueItem> getQueue() {
        return this.mImpl.getQueue();
    }
    
    public CharSequence getQueueTitle() {
        return this.mImpl.getQueueTitle();
    }
    
    public int getRatingType() {
        return this.mImpl.getRatingType();
    }
    
    public PendingIntent getSessionActivity() {
        return this.mImpl.getSessionActivity();
    }
    
    public MediaSessionCompat$Token getSessionToken() {
        return this.mToken;
    }
    
    public MediaControllerCompat$TransportControls getTransportControls() {
        return this.mImpl.getTransportControls();
    }
    
    public void registerCallback(final MediaControllerCompat$Callback mediaControllerCompat$Callback) {
        this.registerCallback(mediaControllerCompat$Callback, null);
    }
    
    public void registerCallback(final MediaControllerCompat$Callback mediaControllerCompat$Callback, final Handler handler) {
        if (mediaControllerCompat$Callback == null) {
            throw new IllegalArgumentException("callback cannot be null");
        }
        Handler handler2;
        if ((handler2 = handler) == null) {
            handler2 = new Handler();
        }
        this.mImpl.registerCallback(mediaControllerCompat$Callback, handler2);
    }
    
    public void sendCommand(final String s, final Bundle bundle, final ResultReceiver resultReceiver) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("command cannot be null or empty");
        }
        this.mImpl.sendCommand(s, bundle, resultReceiver);
    }
    
    public void setVolumeTo(final int n, final int n2) {
        this.mImpl.setVolumeTo(n, n2);
    }
    
    public void unregisterCallback(final MediaControllerCompat$Callback mediaControllerCompat$Callback) {
        if (mediaControllerCompat$Callback == null) {
            throw new IllegalArgumentException("callback cannot be null");
        }
        this.mImpl.unregisterCallback(mediaControllerCompat$Callback);
    }
}
