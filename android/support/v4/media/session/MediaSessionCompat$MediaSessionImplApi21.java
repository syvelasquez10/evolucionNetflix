// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import android.support.v4.media.VolumeProviderCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.app.PendingIntent;
import java.lang.ref.WeakReference;
import android.os.Handler;
import android.os.RemoteException;
import android.os.Bundle;
import android.os.Build$VERSION;
import android.content.Context;
import android.os.RemoteCallbackList;

class MediaSessionCompat$MediaSessionImplApi21 implements MediaSessionCompat$MediaSessionImpl
{
    private boolean mDestroyed;
    private final RemoteCallbackList<IMediaControllerCallback> mExtraControllerCallbacks;
    private MediaSessionCompat$MediaSessionImplApi21$ExtraSession mExtraSessionBinder;
    private PlaybackStateCompat mPlaybackState;
    int mRatingType;
    private final Object mSessionObj;
    private final MediaSessionCompat$Token mToken;
    
    public MediaSessionCompat$MediaSessionImplApi21(final Context context, final String s) {
        this.mDestroyed = false;
        this.mExtraControllerCallbacks = (RemoteCallbackList<IMediaControllerCallback>)new RemoteCallbackList();
        this.mSessionObj = MediaSessionCompatApi21.createSession(context, s);
        this.mToken = new MediaSessionCompat$Token(MediaSessionCompatApi21.getSessionToken(this.mSessionObj));
    }
    
    public MediaSessionCompat$MediaSessionImplApi21(final Object o) {
        this.mDestroyed = false;
        this.mExtraControllerCallbacks = (RemoteCallbackList<IMediaControllerCallback>)new RemoteCallbackList();
        this.mSessionObj = MediaSessionCompatApi21.verifySession(o);
        this.mToken = new MediaSessionCompat$Token(MediaSessionCompatApi21.getSessionToken(this.mSessionObj));
    }
    
    @Override
    public String getCallingPackage() {
        if (Build$VERSION.SDK_INT < 24) {
            return null;
        }
        return MediaSessionCompatApi24.getCallingPackage(this.mSessionObj);
    }
    
    MediaSessionCompat$MediaSessionImplApi21$ExtraSession getExtraSessionBinder() {
        if (this.mExtraSessionBinder == null) {
            this.mExtraSessionBinder = new MediaSessionCompat$MediaSessionImplApi21$ExtraSession(this);
        }
        return this.mExtraSessionBinder;
    }
    
    @Override
    public Object getMediaSession() {
        return this.mSessionObj;
    }
    
    @Override
    public Object getRemoteControlClient() {
        return null;
    }
    
    @Override
    public MediaSessionCompat$Token getSessionToken() {
        return this.mToken;
    }
    
    @Override
    public boolean isActive() {
        return MediaSessionCompatApi21.isActive(this.mSessionObj);
    }
    
    @Override
    public void release() {
        this.mDestroyed = true;
        MediaSessionCompatApi21.release(this.mSessionObj);
    }
    
    @Override
    public void sendSessionEvent(final String s, final Bundle bundle) {
    Label_0018_Outer:
        while (true) {
            if (Build$VERSION.SDK_INT >= 23) {
                break Label_0058;
            }
            int n = this.mExtraControllerCallbacks.beginBroadcast() - 1;
        Label_0044_Outer:
            while (true) {
                Label_0051: {
                    if (n < 0) {
                        break Label_0051;
                    }
                    final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mExtraControllerCallbacks.getBroadcastItem(n);
                    while (true) {
                        try {
                            mediaControllerCallback.onEvent(s, bundle);
                            --n;
                            continue Label_0044_Outer;
                            MediaSessionCompatApi21.sendSessionEvent(this.mSessionObj, s, bundle);
                            return;
                            this.mExtraControllerCallbacks.finishBroadcast();
                            continue Label_0018_Outer;
                        }
                        catch (RemoteException ex) {
                            continue;
                        }
                        break;
                    }
                }
                break;
            }
            break;
        }
    }
    
    @Override
    public void setActive(final boolean b) {
        MediaSessionCompatApi21.setActive(this.mSessionObj, b);
    }
    
    @Override
    public void setCallback(final MediaSessionCompat$Callback mediaSessionCompat$Callback, final Handler handler) {
        final Object mSessionObj = this.mSessionObj;
        Object mCallbackObj;
        if (mediaSessionCompat$Callback == null) {
            mCallbackObj = null;
        }
        else {
            mCallbackObj = mediaSessionCompat$Callback.mCallbackObj;
        }
        MediaSessionCompatApi21.setCallback(mSessionObj, mCallbackObj, handler);
        if (mediaSessionCompat$Callback != null) {
            mediaSessionCompat$Callback.mSessionImpl = new WeakReference<MediaSessionCompat$MediaSessionImpl>(this);
        }
    }
    
    @Override
    public void setExtras(final Bundle bundle) {
        MediaSessionCompatApi21.setExtras(this.mSessionObj, bundle);
    }
    
    @Override
    public void setFlags(final int n) {
        MediaSessionCompatApi21.setFlags(this.mSessionObj, n);
    }
    
    @Override
    public void setMediaButtonReceiver(final PendingIntent pendingIntent) {
        MediaSessionCompatApi21.setMediaButtonReceiver(this.mSessionObj, pendingIntent);
    }
    
    @Override
    public void setMetadata(final MediaMetadataCompat mediaMetadataCompat) {
        final Object mSessionObj = this.mSessionObj;
        Object mediaMetadata;
        if (mediaMetadataCompat == null) {
            mediaMetadata = null;
        }
        else {
            mediaMetadata = mediaMetadataCompat.getMediaMetadata();
        }
        MediaSessionCompatApi21.setMetadata(mSessionObj, mediaMetadata);
    }
    
    @Override
    public void setPlaybackState(PlaybackStateCompat playbackState) {
    Label_0023_Outer:
        while (true) {
            if (Build$VERSION.SDK_INT >= 22) {
                break Label_0060;
            }
            this.mPlaybackState = playbackState;
            int n = this.mExtraControllerCallbacks.beginBroadcast() - 1;
        Label_0046_Outer:
            while (true) {
                Label_0053: {
                    if (n < 0) {
                        break Label_0053;
                    }
                    final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mExtraControllerCallbacks.getBroadcastItem(n);
                    while (true) {
                        try {
                            mediaControllerCallback.onPlaybackStateChanged(playbackState);
                            --n;
                            continue Label_0046_Outer;
                            Label_0077: {
                                playbackState = (PlaybackStateCompat)playbackState.getPlaybackState();
                            }
                            while (true) {
                                final Object mSessionObj;
                                MediaSessionCompatApi21.setPlaybackState(mSessionObj, playbackState);
                                return;
                                mSessionObj = this.mSessionObj;
                                Block_5: {
                                    break Block_5;
                                    this.mExtraControllerCallbacks.finishBroadcast();
                                    continue Label_0023_Outer;
                                }
                                playbackState = null;
                                continue;
                            }
                        }
                        // iftrue(Label_0077:, playbackState != null)
                        catch (RemoteException ex) {
                            continue;
                        }
                        break;
                    }
                }
                break;
            }
            break;
        }
    }
    
    @Override
    public void setPlaybackToLocal(final int n) {
        MediaSessionCompatApi21.setPlaybackToLocal(this.mSessionObj, n);
    }
    
    @Override
    public void setPlaybackToRemote(final VolumeProviderCompat volumeProviderCompat) {
        MediaSessionCompatApi21.setPlaybackToRemote(this.mSessionObj, volumeProviderCompat.getVolumeProvider());
    }
    
    @Override
    public void setQueue(final List<MediaSessionCompat$QueueItem> list) {
        List<Object> list2 = null;
        if (list != null) {
            list2 = new ArrayList<Object>();
            final Iterator<MediaSessionCompat$QueueItem> iterator = list.iterator();
            while (iterator.hasNext()) {
                list2.add(iterator.next().getQueueItem());
            }
        }
        MediaSessionCompatApi21.setQueue(this.mSessionObj, list2);
    }
    
    @Override
    public void setQueueTitle(final CharSequence charSequence) {
        MediaSessionCompatApi21.setQueueTitle(this.mSessionObj, charSequence);
    }
    
    @Override
    public void setRatingType(final int mRatingType) {
        if (Build$VERSION.SDK_INT < 22) {
            this.mRatingType = mRatingType;
            return;
        }
        MediaSessionCompatApi22.setRatingType(this.mSessionObj, mRatingType);
    }
    
    @Override
    public void setSessionActivity(final PendingIntent pendingIntent) {
        MediaSessionCompatApi21.setSessionActivity(this.mSessionObj, pendingIntent);
    }
}
