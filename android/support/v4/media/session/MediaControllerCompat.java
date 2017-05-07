// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.support.v4.media.RatingCompat;
import android.text.TextUtils;
import android.os.ResultReceiver;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.media.MediaMetadataCompat;
import android.view.KeyEvent;
import android.os.RemoteException;
import android.os.Build$VERSION;
import android.content.Context;

public final class MediaControllerCompat
{
    private final MediaControllerImpl mImpl;
    
    public MediaControllerCompat(final Context context, final MediaSessionCompat.Token token) throws RemoteException {
        if (token == null) {
            throw new IllegalArgumentException("sessionToken must not be null");
        }
        if (Build$VERSION.SDK_INT >= 21) {
            this.mImpl = (MediaControllerImpl)new MediaControllerImplApi21(context, token);
            return;
        }
        this.mImpl = (MediaControllerImpl)new MediaControllerImplBase();
    }
    
    public MediaControllerCompat(final Context context, final MediaSessionCompat mediaSessionCompat) {
        if (mediaSessionCompat == null) {
            throw new IllegalArgumentException("session must not be null");
        }
        if (Build$VERSION.SDK_INT >= 21) {
            this.mImpl = (MediaControllerImpl)new MediaControllerImplApi21(context, mediaSessionCompat);
            return;
        }
        this.mImpl = (MediaControllerImpl)new MediaControllerImplBase();
    }
    
    public boolean dispatchMediaButtonEvent(final KeyEvent keyEvent) {
        if (keyEvent == null) {
            throw new IllegalArgumentException("KeyEvent may not be null");
        }
        return this.mImpl.dispatchMediaButtonEvent(keyEvent);
    }
    
    public Object getMediaController() {
        return this.mImpl.getMediaController();
    }
    
    public MediaMetadataCompat getMetadata() {
        return this.mImpl.getMetadata();
    }
    
    public PlaybackInfo getPlaybackInfo() {
        return this.mImpl.getPlaybackInfo();
    }
    
    public PlaybackStateCompat getPlaybackState() {
        return this.mImpl.getPlaybackState();
    }
    
    public int getRatingType() {
        return this.mImpl.getRatingType();
    }
    
    public TransportControls getTransportControls() {
        return this.mImpl.getTransportControls();
    }
    
    public void registerCallback(final Callback callback) {
        this.registerCallback(callback, null);
    }
    
    public void registerCallback(final Callback callback, final Handler handler) {
        if (callback == null) {
            throw new IllegalArgumentException("callback cannot be null");
        }
        Handler handler2;
        if ((handler2 = handler) == null) {
            handler2 = new Handler();
        }
        this.mImpl.registerCallback(callback, handler2);
    }
    
    public void sendCommand(final String s, final Bundle bundle, final ResultReceiver resultReceiver) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("command cannot be null or empty");
        }
        this.mImpl.sendCommand(s, bundle, resultReceiver);
    }
    
    public void unregisterCallback(final Callback callback) {
        if (callback == null) {
            throw new IllegalArgumentException("callback cannot be null");
        }
        this.mImpl.unregisterCallback(callback);
    }
    
    public abstract static class Callback
    {
        final Object mCallbackObj;
        
        public Callback() {
            if (Build$VERSION.SDK_INT >= 21) {
                this.mCallbackObj = MediaControllerCompatApi21.createCallback((MediaControllerCompatApi21.Callback)new StubApi21());
                return;
            }
            this.mCallbackObj = null;
        }
        
        public void onMetadataChanged(final MediaMetadataCompat mediaMetadataCompat) {
        }
        
        public void onPlaybackStateChanged(final PlaybackStateCompat playbackStateCompat) {
        }
        
        public void onSessionDestroyed() {
        }
        
        public void onSessionEvent(final String s, final Bundle bundle) {
        }
        
        private class StubApi21 implements MediaControllerCompatApi21.Callback
        {
            @Override
            public void onMetadataChanged(final Object o) {
                MediaControllerCompat.Callback.this.onMetadataChanged(MediaMetadataCompat.fromMediaMetadata(o));
            }
            
            @Override
            public void onPlaybackStateChanged(final Object o) {
                MediaControllerCompat.Callback.this.onPlaybackStateChanged(PlaybackStateCompat.fromPlaybackState(o));
            }
            
            @Override
            public void onSessionDestroyed() {
                MediaControllerCompat.Callback.this.onSessionDestroyed();
            }
            
            @Override
            public void onSessionEvent(final String s, final Bundle bundle) {
                MediaControllerCompat.Callback.this.onSessionEvent(s, bundle);
            }
        }
    }
    
    interface MediaControllerImpl
    {
        boolean dispatchMediaButtonEvent(final KeyEvent p0);
        
        Object getMediaController();
        
        MediaMetadataCompat getMetadata();
        
        PlaybackInfo getPlaybackInfo();
        
        PlaybackStateCompat getPlaybackState();
        
        int getRatingType();
        
        TransportControls getTransportControls();
        
        void registerCallback(final Callback p0, final Handler p1);
        
        void sendCommand(final String p0, final Bundle p1, final ResultReceiver p2);
        
        void unregisterCallback(final Callback p0);
    }
    
    static class MediaControllerImplApi21 implements MediaControllerImpl
    {
        private final Object mControllerObj;
        
        public MediaControllerImplApi21(final Context context, final MediaSessionCompat.Token token) throws RemoteException {
            this.mControllerObj = MediaControllerCompatApi21.fromToken(context, token.getToken());
            if (this.mControllerObj == null) {
                throw new RemoteException();
            }
        }
        
        public MediaControllerImplApi21(final Context context, final MediaSessionCompat mediaSessionCompat) {
            this.mControllerObj = MediaControllerCompatApi21.fromToken(context, mediaSessionCompat.getSessionToken().getToken());
        }
        
        @Override
        public boolean dispatchMediaButtonEvent(final KeyEvent keyEvent) {
            return MediaControllerCompatApi21.dispatchMediaButtonEvent(this.mControllerObj, keyEvent);
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
        public PlaybackInfo getPlaybackInfo() {
            final Object playbackInfo = MediaControllerCompatApi21.getPlaybackInfo(this.mControllerObj);
            if (playbackInfo != null) {
                return new PlaybackInfo(MediaControllerCompatApi21.PlaybackInfo.getPlaybackType(playbackInfo), MediaControllerCompatApi21.PlaybackInfo.getLegacyAudioStream(playbackInfo), MediaControllerCompatApi21.PlaybackInfo.getVolumeControl(playbackInfo), MediaControllerCompatApi21.PlaybackInfo.getMaxVolume(playbackInfo), MediaControllerCompatApi21.PlaybackInfo.getCurrentVolume(playbackInfo));
            }
            return null;
        }
        
        @Override
        public PlaybackStateCompat getPlaybackState() {
            final Object playbackState = MediaControllerCompatApi21.getPlaybackState(this.mControllerObj);
            if (playbackState != null) {
                return PlaybackStateCompat.fromPlaybackState(playbackState);
            }
            return null;
        }
        
        @Override
        public int getRatingType() {
            return MediaControllerCompatApi21.getRatingType(this.mControllerObj);
        }
        
        @Override
        public TransportControls getTransportControls() {
            final Object transportControls = MediaControllerCompatApi21.getTransportControls(this.mControllerObj);
            if (transportControls != null) {
                return new TransportControlsApi21(transportControls);
            }
            return null;
        }
        
        @Override
        public void registerCallback(final Callback callback, final Handler handler) {
            MediaControllerCompatApi21.registerCallback(this.mControllerObj, callback.mCallbackObj, handler);
        }
        
        @Override
        public void sendCommand(final String s, final Bundle bundle, final ResultReceiver resultReceiver) {
            MediaControllerCompatApi21.sendCommand(this.mControllerObj, s, bundle, resultReceiver);
        }
        
        @Override
        public void unregisterCallback(final Callback callback) {
            MediaControllerCompatApi21.unregisterCallback(this.mControllerObj, callback.mCallbackObj);
        }
    }
    
    static class MediaControllerImplBase implements MediaControllerImpl
    {
        @Override
        public boolean dispatchMediaButtonEvent(final KeyEvent keyEvent) {
            return false;
        }
        
        @Override
        public Object getMediaController() {
            return null;
        }
        
        @Override
        public MediaMetadataCompat getMetadata() {
            return null;
        }
        
        @Override
        public PlaybackInfo getPlaybackInfo() {
            return null;
        }
        
        @Override
        public PlaybackStateCompat getPlaybackState() {
            return null;
        }
        
        @Override
        public int getRatingType() {
            return 0;
        }
        
        @Override
        public TransportControls getTransportControls() {
            return null;
        }
        
        @Override
        public void registerCallback(final Callback callback, final Handler handler) {
        }
        
        @Override
        public void sendCommand(final String s, final Bundle bundle, final ResultReceiver resultReceiver) {
        }
        
        @Override
        public void unregisterCallback(final Callback callback) {
        }
    }
    
    public static final class PlaybackInfo
    {
        public static final int PLAYBACK_TYPE_LOCAL = 1;
        public static final int PLAYBACK_TYPE_REMOTE = 2;
        private final int mAudioStream;
        private final int mCurrentVolume;
        private final int mMaxVolume;
        private final int mPlaybackType;
        private final int mVolumeControl;
        
        PlaybackInfo(final int mPlaybackType, final int mAudioStream, final int mVolumeControl, final int mMaxVolume, final int mCurrentVolume) {
            this.mPlaybackType = mPlaybackType;
            this.mAudioStream = mAudioStream;
            this.mVolumeControl = mVolumeControl;
            this.mMaxVolume = mMaxVolume;
            this.mCurrentVolume = mCurrentVolume;
        }
        
        public int getAudioStream() {
            return this.mAudioStream;
        }
        
        public int getCurrentVolume() {
            return this.mCurrentVolume;
        }
        
        public int getMaxVolume() {
            return this.mMaxVolume;
        }
        
        public int getPlaybackType() {
            return this.mPlaybackType;
        }
        
        public int getVolumeControl() {
            return this.mVolumeControl;
        }
    }
    
    public abstract static class TransportControls
    {
        public abstract void fastForward();
        
        public abstract void pause();
        
        public abstract void play();
        
        public abstract void rewind();
        
        public abstract void seekTo(final long p0);
        
        public abstract void setRating(final RatingCompat p0);
        
        public abstract void skipToNext();
        
        public abstract void skipToPrevious();
        
        public abstract void stop();
    }
    
    static class TransportControlsApi21 extends TransportControls
    {
        private final Object mControlsObj;
        
        public TransportControlsApi21(final Object mControlsObj) {
            this.mControlsObj = mControlsObj;
        }
        
        @Override
        public void fastForward() {
            MediaControllerCompatApi21.TransportControls.fastForward(this.mControlsObj);
        }
        
        @Override
        public void pause() {
            MediaControllerCompatApi21.TransportControls.pause(this.mControlsObj);
        }
        
        @Override
        public void play() {
            MediaControllerCompatApi21.TransportControls.play(this.mControlsObj);
        }
        
        @Override
        public void rewind() {
            MediaControllerCompatApi21.TransportControls.rewind(this.mControlsObj);
        }
        
        @Override
        public void seekTo(final long n) {
            MediaControllerCompatApi21.TransportControls.seekTo(this.mControlsObj, n);
        }
        
        @Override
        public void setRating(final RatingCompat ratingCompat) {
            final Object mControlsObj = this.mControlsObj;
            Object rating;
            if (ratingCompat != null) {
                rating = ratingCompat.getRating();
            }
            else {
                rating = null;
            }
            MediaControllerCompatApi21.TransportControls.setRating(mControlsObj, rating);
        }
        
        @Override
        public void skipToNext() {
            MediaControllerCompatApi21.TransportControls.skipToNext(this.mControlsObj);
        }
        
        @Override
        public void skipToPrevious() {
            MediaControllerCompatApi21.TransportControls.skipToPrevious(this.mControlsObj);
        }
        
        @Override
        public void stop() {
            MediaControllerCompatApi21.TransportControls.stop(this.mControlsObj);
        }
    }
}
