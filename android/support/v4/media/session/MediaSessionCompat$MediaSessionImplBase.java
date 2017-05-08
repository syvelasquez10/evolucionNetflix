// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.support.v4.media.MediaMetadataCompat$Builder;
import android.os.Handler;
import android.os.SystemClock;
import android.os.RemoteException;
import android.os.Build$VERSION;
import android.content.Intent;
import android.util.Log;
import android.support.v4.media.VolumeProviderCompat;
import android.support.v4.media.VolumeProviderCompat$Callback;
import java.util.List;
import android.support.v4.media.MediaMetadataCompat;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.os.Bundle;
import android.os.RemoteCallbackList;
import android.content.Context;
import android.media.AudioManager;

class MediaSessionCompat$MediaSessionImplBase implements MediaSessionCompat$MediaSessionImpl
{
    final AudioManager mAudioManager;
    volatile MediaSessionCompat$Callback mCallback;
    private final Context mContext;
    final RemoteCallbackList<IMediaControllerCallback> mControllerCallbacks;
    boolean mDestroyed;
    Bundle mExtras;
    int mFlags;
    private MediaSessionCompat$MediaSessionImplBase$MessageHandler mHandler;
    private boolean mIsActive;
    private boolean mIsMbrRegistered;
    private boolean mIsRccRegistered;
    int mLocalStream;
    final Object mLock;
    private final ComponentName mMediaButtonReceiverComponentName;
    private final PendingIntent mMediaButtonReceiverIntent;
    MediaMetadataCompat mMetadata;
    final String mPackageName;
    List<MediaSessionCompat$QueueItem> mQueue;
    CharSequence mQueueTitle;
    int mRatingType;
    private final Object mRccObj;
    PendingIntent mSessionActivity;
    PlaybackStateCompat mState;
    private final MediaSessionCompat$MediaSessionImplBase$MediaSessionStub mStub;
    final String mTag;
    private final MediaSessionCompat$Token mToken;
    private VolumeProviderCompat$Callback mVolumeCallback;
    VolumeProviderCompat mVolumeProvider;
    int mVolumeType;
    
    public MediaSessionCompat$MediaSessionImplBase(final Context mContext, final String mTag, ComponentName mediaButtonReceiverComponent, final PendingIntent pendingIntent) {
        this.mLock = new Object();
        this.mControllerCallbacks = (RemoteCallbackList<IMediaControllerCallback>)new RemoteCallbackList();
        this.mDestroyed = false;
        this.mIsActive = false;
        this.mIsRccRegistered = false;
        this.mIsMbrRegistered = false;
        this.mVolumeCallback = new MediaSessionCompat$MediaSessionImplBase$1(this);
        ComponentName componentName = mediaButtonReceiverComponent;
        if (mediaButtonReceiverComponent == null) {
            mediaButtonReceiverComponent = MediaButtonReceiver.getMediaButtonReceiverComponent(mContext);
            if ((componentName = mediaButtonReceiverComponent) == null) {
                Log.w("MediaSessionCompat", "Couldn't find a unique registered media button receiver in the given context.");
                componentName = mediaButtonReceiverComponent;
            }
        }
        PendingIntent broadcast = pendingIntent;
        if (componentName != null && (broadcast = pendingIntent) == null) {
            final Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
            intent.setComponent(componentName);
            broadcast = PendingIntent.getBroadcast(mContext, 0, intent, 0);
        }
        if (componentName == null) {
            throw new IllegalArgumentException("MediaButtonReceiver component may not be null.");
        }
        this.mContext = mContext;
        this.mPackageName = mContext.getPackageName();
        this.mAudioManager = (AudioManager)mContext.getSystemService("audio");
        this.mTag = mTag;
        this.mMediaButtonReceiverComponentName = componentName;
        this.mMediaButtonReceiverIntent = broadcast;
        this.mStub = new MediaSessionCompat$MediaSessionImplBase$MediaSessionStub(this);
        this.mToken = new MediaSessionCompat$Token(this.mStub);
        this.mRatingType = 0;
        this.mVolumeType = 1;
        this.mLocalStream = 3;
        if (Build$VERSION.SDK_INT >= 14) {
            this.mRccObj = MediaSessionCompatApi14.createRemoteControlClient(broadcast);
            return;
        }
        this.mRccObj = null;
    }
    
    private void sendEvent(final String s, final Bundle bundle) {
        int n = this.mControllerCallbacks.beginBroadcast() - 1;
    Label_0036_Outer:
        while (true) {
            Label_0043: {
                if (n < 0) {
                    break Label_0043;
                }
                final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(n);
                while (true) {
                    try {
                        mediaControllerCallback.onEvent(s, bundle);
                        --n;
                        continue Label_0036_Outer;
                        this.mControllerCallbacks.finishBroadcast();
                    }
                    catch (RemoteException ex) {
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    private void sendExtras(final Bundle bundle) {
        int n = this.mControllerCallbacks.beginBroadcast() - 1;
    Label_0033_Outer:
        while (true) {
            Label_0040: {
                if (n < 0) {
                    break Label_0040;
                }
                final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(n);
                while (true) {
                    try {
                        mediaControllerCallback.onExtrasChanged(bundle);
                        --n;
                        continue Label_0033_Outer;
                        this.mControllerCallbacks.finishBroadcast();
                    }
                    catch (RemoteException ex) {
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    private void sendMetadata(final MediaMetadataCompat mediaMetadataCompat) {
        int n = this.mControllerCallbacks.beginBroadcast() - 1;
    Label_0033_Outer:
        while (true) {
            Label_0040: {
                if (n < 0) {
                    break Label_0040;
                }
                final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(n);
                while (true) {
                    try {
                        mediaControllerCallback.onMetadataChanged(mediaMetadataCompat);
                        --n;
                        continue Label_0033_Outer;
                        this.mControllerCallbacks.finishBroadcast();
                    }
                    catch (RemoteException ex) {
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    private void sendQueue(final List<MediaSessionCompat$QueueItem> list) {
        int n = this.mControllerCallbacks.beginBroadcast() - 1;
    Label_0033_Outer:
        while (true) {
            Label_0040: {
                if (n < 0) {
                    break Label_0040;
                }
                final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(n);
                while (true) {
                    try {
                        mediaControllerCallback.onQueueChanged(list);
                        --n;
                        continue Label_0033_Outer;
                        this.mControllerCallbacks.finishBroadcast();
                    }
                    catch (RemoteException ex) {
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    private void sendQueueTitle(final CharSequence charSequence) {
        int n = this.mControllerCallbacks.beginBroadcast() - 1;
    Label_0033_Outer:
        while (true) {
            Label_0040: {
                if (n < 0) {
                    break Label_0040;
                }
                final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(n);
                while (true) {
                    try {
                        mediaControllerCallback.onQueueTitleChanged(charSequence);
                        --n;
                        continue Label_0033_Outer;
                        this.mControllerCallbacks.finishBroadcast();
                    }
                    catch (RemoteException ex) {
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    private void sendSessionDestroyed() {
        int n = this.mControllerCallbacks.beginBroadcast() - 1;
    Label_0032_Outer:
        while (true) {
            Label_0039: {
                if (n < 0) {
                    break Label_0039;
                }
                final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(n);
                while (true) {
                    try {
                        mediaControllerCallback.onSessionDestroyed();
                        --n;
                        continue Label_0032_Outer;
                        this.mControllerCallbacks.finishBroadcast();
                        this.mControllerCallbacks.kill();
                    }
                    catch (RemoteException ex) {
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    private void sendState(final PlaybackStateCompat playbackStateCompat) {
        int n = this.mControllerCallbacks.beginBroadcast() - 1;
    Label_0033_Outer:
        while (true) {
            Label_0040: {
                if (n < 0) {
                    break Label_0040;
                }
                final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(n);
                while (true) {
                    try {
                        mediaControllerCallback.onPlaybackStateChanged(playbackStateCompat);
                        --n;
                        continue Label_0033_Outer;
                        this.mControllerCallbacks.finishBroadcast();
                    }
                    catch (RemoteException ex) {
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    private boolean update() {
        if (this.mIsActive) {
            if (!this.mIsMbrRegistered && (this.mFlags & 0x1) != 0x0) {
                if (Build$VERSION.SDK_INT >= 18) {
                    MediaSessionCompatApi18.registerMediaButtonEventReceiver(this.mContext, this.mMediaButtonReceiverIntent, this.mMediaButtonReceiverComponentName);
                }
                else {
                    ((AudioManager)this.mContext.getSystemService("audio")).registerMediaButtonEventReceiver(this.mMediaButtonReceiverComponentName);
                }
                this.mIsMbrRegistered = true;
            }
            else if (this.mIsMbrRegistered && (this.mFlags & 0x1) == 0x0) {
                if (Build$VERSION.SDK_INT >= 18) {
                    MediaSessionCompatApi18.unregisterMediaButtonEventReceiver(this.mContext, this.mMediaButtonReceiverIntent, this.mMediaButtonReceiverComponentName);
                }
                else {
                    ((AudioManager)this.mContext.getSystemService("audio")).unregisterMediaButtonEventReceiver(this.mMediaButtonReceiverComponentName);
                }
                this.mIsMbrRegistered = false;
            }
            if (Build$VERSION.SDK_INT >= 14) {
                if (!this.mIsRccRegistered && (this.mFlags & 0x2) != 0x0) {
                    MediaSessionCompatApi14.registerRemoteControlClient(this.mContext, this.mRccObj);
                    return this.mIsRccRegistered = true;
                }
                if (this.mIsRccRegistered && (this.mFlags & 0x2) == 0x0) {
                    MediaSessionCompatApi14.setState(this.mRccObj, 0);
                    MediaSessionCompatApi14.unregisterRemoteControlClient(this.mContext, this.mRccObj);
                    return this.mIsRccRegistered = false;
                }
            }
        }
        else {
            if (this.mIsMbrRegistered) {
                if (Build$VERSION.SDK_INT >= 18) {
                    MediaSessionCompatApi18.unregisterMediaButtonEventReceiver(this.mContext, this.mMediaButtonReceiverIntent, this.mMediaButtonReceiverComponentName);
                }
                else {
                    ((AudioManager)this.mContext.getSystemService("audio")).unregisterMediaButtonEventReceiver(this.mMediaButtonReceiverComponentName);
                }
                this.mIsMbrRegistered = false;
            }
            if (this.mIsRccRegistered) {
                MediaSessionCompatApi14.setState(this.mRccObj, 0);
                MediaSessionCompatApi14.unregisterRemoteControlClient(this.mContext, this.mRccObj);
                this.mIsRccRegistered = false;
            }
        }
        return false;
    }
    
    void adjustVolume(final int n, final int n2) {
        if (this.mVolumeType == 2) {
            if (this.mVolumeProvider != null) {
                this.mVolumeProvider.onAdjustVolume(n);
            }
            return;
        }
        this.mAudioManager.adjustStreamVolume(this.mLocalStream, n, n2);
    }
    
    @Override
    public String getCallingPackage() {
        return null;
    }
    
    @Override
    public Object getMediaSession() {
        return null;
    }
    
    @Override
    public Object getRemoteControlClient() {
        return this.mRccObj;
    }
    
    @Override
    public MediaSessionCompat$Token getSessionToken() {
        return this.mToken;
    }
    
    PlaybackStateCompat getStateWithUpdatedPosition() {
    Label_0174_Outer:
        while (true) {
            long lastPositionUpdateTime = -1L;
            Object o = this.mLock;
            while (true) {
                Label_0214: {
                    while (true) {
                        synchronized (o) {
                            final PlaybackStateCompat mState = this.mState;
                            long long1 = lastPositionUpdateTime;
                            if (this.mMetadata != null) {
                                long1 = lastPositionUpdateTime;
                                if (this.mMetadata.containsKey("android.media.metadata.DURATION")) {
                                    long1 = this.mMetadata.getLong("android.media.metadata.DURATION");
                                }
                            }
                            // monitorexit(o)
                            if (mState == null || (mState.getState() != 3 && mState.getState() != 4 && mState.getState() != 5)) {
                                break Label_0214;
                            }
                            lastPositionUpdateTime = mState.getLastPositionUpdateTime();
                            final long elapsedRealtime = SystemClock.elapsedRealtime();
                            if (lastPositionUpdateTime <= 0L) {
                                break Label_0214;
                            }
                            lastPositionUpdateTime = (long)(mState.getPlaybackSpeed() * (elapsedRealtime - lastPositionUpdateTime)) + mState.getPosition();
                            if (long1 >= 0L && lastPositionUpdateTime > long1) {
                                o = new PlaybackStateCompat$Builder(mState);
                                ((PlaybackStateCompat$Builder)o).setState(mState.getState(), long1, mState.getPlaybackSpeed(), elapsedRealtime);
                                o = ((PlaybackStateCompat$Builder)o).build();
                                if (o == null) {}
                                return (PlaybackStateCompat)o;
                            }
                        }
                        if (lastPositionUpdateTime < 0L) {
                            final long long1 = 0L;
                            continue Label_0174_Outer;
                        }
                        long long1 = lastPositionUpdateTime;
                        continue Label_0174_Outer;
                    }
                }
                o = null;
                continue;
            }
        }
    }
    
    @Override
    public boolean isActive() {
        return this.mIsActive;
    }
    
    void postToHandler(final int n) {
        this.postToHandler(n, null);
    }
    
    void postToHandler(final int n, final Object o) {
        this.postToHandler(n, o, null);
    }
    
    void postToHandler(final int n, final Object o, final Bundle bundle) {
        synchronized (this.mLock) {
            if (this.mHandler != null) {
                this.mHandler.post(n, o, bundle);
            }
        }
    }
    
    @Override
    public void release() {
        this.mIsActive = false;
        this.mDestroyed = true;
        this.update();
        this.sendSessionDestroyed();
    }
    
    @Override
    public void sendSessionEvent(final String s, final Bundle bundle) {
        this.sendEvent(s, bundle);
    }
    
    void sendVolumeInfoChanged(final ParcelableVolumeInfo parcelableVolumeInfo) {
        int n = this.mControllerCallbacks.beginBroadcast() - 1;
    Label_0033_Outer:
        while (true) {
            Label_0040: {
                if (n < 0) {
                    break Label_0040;
                }
                final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(n);
                while (true) {
                    try {
                        mediaControllerCallback.onVolumeInfoChanged(parcelableVolumeInfo);
                        --n;
                        continue Label_0033_Outer;
                        this.mControllerCallbacks.finishBroadcast();
                    }
                    catch (RemoteException ex) {
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    @Override
    public void setActive(final boolean mIsActive) {
        if (mIsActive != this.mIsActive) {
            this.mIsActive = mIsActive;
            if (this.update()) {
                this.setMetadata(this.mMetadata);
                this.setPlaybackState(this.mState);
            }
        }
    }
    
    @Override
    public void setCallback(final MediaSessionCompat$Callback mCallback, Handler handler) {
        this.mCallback = mCallback;
        if (mCallback == null) {
            if (Build$VERSION.SDK_INT >= 18) {
                MediaSessionCompatApi18.setOnPlaybackPositionUpdateListener(this.mRccObj, null);
            }
            if (Build$VERSION.SDK_INT >= 19) {
                MediaSessionCompatApi19.setOnMetadataUpdateListener(this.mRccObj, null);
            }
        }
        else {
            Handler handler2;
            if ((handler2 = handler) == null) {
                handler2 = new Handler();
            }
            handler = (Handler)this.mLock;
            synchronized (handler) {
                this.mHandler = new MediaSessionCompat$MediaSessionImplBase$MessageHandler(this, handler2.getLooper());
                // monitorexit(handler)
                final MediaSessionCompat$MediaSessionImplBase$2 mediaSessionCompat$MediaSessionImplBase$2 = new MediaSessionCompat$MediaSessionImplBase$2(this);
                if (Build$VERSION.SDK_INT >= 18) {
                    handler = (Handler)MediaSessionCompatApi18.createPlaybackPositionUpdateListener(mediaSessionCompat$MediaSessionImplBase$2);
                    MediaSessionCompatApi18.setOnPlaybackPositionUpdateListener(this.mRccObj, handler);
                }
                if (Build$VERSION.SDK_INT >= 19) {
                    MediaSessionCompatApi19.setOnMetadataUpdateListener(this.mRccObj, MediaSessionCompatApi19.createMetadataUpdateListener(mediaSessionCompat$MediaSessionImplBase$2));
                }
            }
        }
    }
    
    @Override
    public void setExtras(final Bundle mExtras) {
        this.sendExtras(this.mExtras = mExtras);
    }
    
    @Override
    public void setFlags(final int mFlags) {
        synchronized (this.mLock) {
            this.mFlags = mFlags;
            // monitorexit(this.mLock)
            this.update();
        }
    }
    
    @Override
    public void setMediaButtonReceiver(final PendingIntent pendingIntent) {
    }
    
    @Override
    public void setMetadata(final MediaMetadataCompat mediaMetadataCompat) {
        final Bundle bundle = null;
        final Bundle bundle2 = null;
        MediaMetadataCompat build = mediaMetadataCompat;
        if (mediaMetadataCompat != null) {
            build = new MediaMetadataCompat$Builder(mediaMetadataCompat, MediaSessionCompat.sMaxBitmapSize).build();
        }
        while (true) {
            synchronized (this.mLock) {
                this.mMetadata = build;
                // monitorexit(this.mLock)
                this.sendMetadata(build);
                if (!this.mIsActive) {
                    return;
                }
            }
            final MediaMetadataCompat mediaMetadataCompat2;
            if (Build$VERSION.SDK_INT >= 19) {
                final Object mRccObj = this.mRccObj;
                Bundle bundle3;
                if (mediaMetadataCompat2 == null) {
                    bundle3 = bundle2;
                }
                else {
                    bundle3 = mediaMetadataCompat2.getBundle();
                }
                long actions;
                if (this.mState == null) {
                    actions = 0L;
                }
                else {
                    actions = this.mState.getActions();
                }
                MediaSessionCompatApi19.setMetadata(mRccObj, bundle3, actions);
                return;
            }
            if (Build$VERSION.SDK_INT >= 14) {
                final Object mRccObj2 = this.mRccObj;
                Bundle bundle4;
                if (mediaMetadataCompat2 == null) {
                    bundle4 = bundle;
                }
                else {
                    bundle4 = mediaMetadataCompat2.getBundle();
                }
                MediaSessionCompatApi14.setMetadata(mRccObj2, bundle4);
            }
        }
    }
    
    @Override
    public void setPlaybackState(final PlaybackStateCompat mState) {
        while (true) {
            synchronized (this.mLock) {
                this.mState = mState;
                // monitorexit(this.mLock)
                this.sendState(mState);
                if (!this.mIsActive) {
                    return;
                }
            }
            final PlaybackStateCompat playbackStateCompat;
            if (playbackStateCompat == null) {
                if (Build$VERSION.SDK_INT >= 14) {
                    MediaSessionCompatApi14.setState(this.mRccObj, 0);
                    MediaSessionCompatApi14.setTransportControlFlags(this.mRccObj, 0L);
                }
            }
            else {
                if (Build$VERSION.SDK_INT >= 18) {
                    MediaSessionCompatApi18.setState(this.mRccObj, playbackStateCompat.getState(), playbackStateCompat.getPosition(), playbackStateCompat.getPlaybackSpeed(), playbackStateCompat.getLastPositionUpdateTime());
                }
                else if (Build$VERSION.SDK_INT >= 14) {
                    MediaSessionCompatApi14.setState(this.mRccObj, playbackStateCompat.getState());
                }
                if (Build$VERSION.SDK_INT >= 19) {
                    MediaSessionCompatApi19.setTransportControlFlags(this.mRccObj, playbackStateCompat.getActions());
                    return;
                }
                if (Build$VERSION.SDK_INT >= 18) {
                    MediaSessionCompatApi18.setTransportControlFlags(this.mRccObj, playbackStateCompat.getActions());
                    return;
                }
                if (Build$VERSION.SDK_INT >= 14) {
                    MediaSessionCompatApi14.setTransportControlFlags(this.mRccObj, playbackStateCompat.getActions());
                }
            }
        }
    }
    
    @Override
    public void setPlaybackToLocal(final int n) {
        if (this.mVolumeProvider != null) {
            this.mVolumeProvider.setCallback(null);
        }
        this.mVolumeType = 1;
        this.sendVolumeInfoChanged(new ParcelableVolumeInfo(this.mVolumeType, this.mLocalStream, 2, this.mAudioManager.getStreamMaxVolume(this.mLocalStream), this.mAudioManager.getStreamVolume(this.mLocalStream)));
    }
    
    @Override
    public void setPlaybackToRemote(final VolumeProviderCompat mVolumeProvider) {
        if (mVolumeProvider == null) {
            throw new IllegalArgumentException("volumeProvider may not be null");
        }
        if (this.mVolumeProvider != null) {
            this.mVolumeProvider.setCallback(null);
        }
        this.mVolumeType = 2;
        this.mVolumeProvider = mVolumeProvider;
        this.sendVolumeInfoChanged(new ParcelableVolumeInfo(this.mVolumeType, this.mLocalStream, this.mVolumeProvider.getVolumeControl(), this.mVolumeProvider.getMaxVolume(), this.mVolumeProvider.getCurrentVolume()));
        mVolumeProvider.setCallback(this.mVolumeCallback);
    }
    
    @Override
    public void setQueue(final List<MediaSessionCompat$QueueItem> mQueue) {
        this.sendQueue(this.mQueue = mQueue);
    }
    
    @Override
    public void setQueueTitle(final CharSequence mQueueTitle) {
        this.sendQueueTitle(this.mQueueTitle = mQueueTitle);
    }
    
    @Override
    public void setRatingType(final int mRatingType) {
        this.mRatingType = mRatingType;
    }
    
    @Override
    public void setSessionActivity(final PendingIntent mSessionActivity) {
        synchronized (this.mLock) {
            this.mSessionActivity = mSessionActivity;
        }
    }
    
    void setVolumeTo(final int n, final int n2) {
        if (this.mVolumeType == 2) {
            if (this.mVolumeProvider != null) {
                this.mVolumeProvider.onSetVolumeTo(n);
            }
            return;
        }
        this.mAudioManager.setStreamVolume(this.mLocalStream, n, n2);
    }
}
