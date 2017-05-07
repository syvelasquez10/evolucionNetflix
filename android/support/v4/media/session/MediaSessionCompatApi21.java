// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.media.Rating;
import android.content.Intent;
import android.os.ResultReceiver;
import android.media.VolumeProvider;
import android.media.AudioAttributes$Builder;
import android.media.session.PlaybackState;
import android.media.MediaMetadata;
import android.media.session.MediaSession$Callback;
import android.os.Handler;
import android.os.Bundle;
import android.os.Parcelable;
import android.media.session.MediaSession;
import android.content.Context;

class MediaSessionCompatApi21
{
    public static Object createCallback(final Callback callback) {
        return new CallbackProxy(callback);
    }
    
    public static Object createSession(final Context context, final String s) {
        return new MediaSession(context, s);
    }
    
    public static Parcelable getSessionToken(final Object o) {
        return (Parcelable)((MediaSession)o).getSessionToken();
    }
    
    public static boolean isActive(final Object o) {
        return ((MediaSession)o).isActive();
    }
    
    public static void release(final Object o) {
        ((MediaSession)o).release();
    }
    
    public static void sendSessionEvent(final Object o, final String s, final Bundle bundle) {
        ((MediaSession)o).sendSessionEvent(s, bundle);
    }
    
    public static void setActive(final Object o, final boolean active) {
        ((MediaSession)o).setActive(active);
    }
    
    public static void setCallback(final Object o, final Object o2, final Handler handler) {
        ((MediaSession)o).setCallback((MediaSession$Callback)o2, handler);
    }
    
    public static void setFlags(final Object o, final int flags) {
        ((MediaSession)o).setFlags(flags);
    }
    
    public static void setMetadata(final Object o, final Object o2) {
        ((MediaSession)o).setMetadata((MediaMetadata)o2);
    }
    
    public static void setPlaybackState(final Object o, final Object o2) {
        ((MediaSession)o).setPlaybackState((PlaybackState)o2);
    }
    
    public static void setPlaybackToLocal(final Object o, final int legacyStreamType) {
        final AudioAttributes$Builder audioAttributes$Builder = new AudioAttributes$Builder();
        audioAttributes$Builder.setLegacyStreamType(legacyStreamType);
        ((MediaSession)o).setPlaybackToLocal(audioAttributes$Builder.build());
    }
    
    public static void setPlaybackToRemote(final Object o, final Object o2) {
        ((MediaSession)o).setPlaybackToRemote((VolumeProvider)o2);
    }
    
    public static Object verifySession(final Object o) {
        if (o instanceof MediaSession) {
            return o;
        }
        throw new IllegalArgumentException("mediaSession is not a valid MediaSession object");
    }
    
    public interface Callback
    {
        void onCommand(final String p0, final Bundle p1, final ResultReceiver p2);
        
        void onFastForward();
        
        boolean onMediaButtonEvent(final Intent p0);
        
        void onPause();
        
        void onPlay();
        
        void onRewind();
        
        void onSeekTo(final long p0);
        
        void onSetRating(final Object p0);
        
        void onSkipToNext();
        
        void onSkipToPrevious();
        
        void onStop();
    }
    
    static class CallbackProxy<T extends Callback> extends MediaSession$Callback
    {
        protected final T mCallback;
        
        public CallbackProxy(final T mCallback) {
            this.mCallback = mCallback;
        }
        
        public void onCommand(final String s, final Bundle bundle, final ResultReceiver resultReceiver) {
            ((Callback)this.mCallback).onCommand(s, bundle, resultReceiver);
        }
        
        public void onFastForward() {
            ((Callback)this.mCallback).onFastForward();
        }
        
        public boolean onMediaButtonEvent(final Intent intent) {
            return ((Callback)this.mCallback).onMediaButtonEvent(intent);
        }
        
        public void onPause() {
            ((Callback)this.mCallback).onPause();
        }
        
        public void onPlay() {
            ((Callback)this.mCallback).onPlay();
        }
        
        public void onRewind() {
            ((Callback)this.mCallback).onRewind();
        }
        
        public void onSeekTo(final long n) {
            ((Callback)this.mCallback).onSeekTo(n);
        }
        
        public void onSetRating(final Rating rating) {
            ((Callback)this.mCallback).onSetRating(rating);
        }
        
        public void onSkipToNext() {
            ((Callback)this.mCallback).onSkipToNext();
        }
        
        public void onSkipToPrevious() {
            ((Callback)this.mCallback).onSkipToPrevious();
        }
        
        public void onStop() {
            ((Callback)this.mCallback).onStop();
        }
    }
}
