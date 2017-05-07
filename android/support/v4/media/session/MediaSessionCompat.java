// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.support.v4.media.RatingCompat;
import android.content.Intent;
import android.os.ResultReceiver;
import android.support.v4.media.VolumeProviderCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.os.Handler;
import android.os.Bundle;
import android.os.Build$VERSION;
import android.text.TextUtils;
import android.content.Context;

public class MediaSessionCompat
{
    public static final int FLAG_HANDLES_MEDIA_BUTTONS = 1;
    public static final int FLAG_HANDLES_TRANSPORT_CONTROLS = 2;
    private final MediaSessionImpl mImpl;
    
    public MediaSessionCompat(final Context context, final String s) {
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("tag must not be null or empty");
        }
        if (Build$VERSION.SDK_INT >= 21) {
            this.mImpl = (MediaSessionImpl)new MediaSessionImplApi21(context, s);
            return;
        }
        this.mImpl = (MediaSessionImpl)new MediaSessionImplBase();
    }
    
    private MediaSessionCompat(final MediaSessionImpl mImpl) {
        this.mImpl = mImpl;
    }
    
    public static MediaSessionCompat obtain(final Object o) {
        return new MediaSessionCompat((MediaSessionImpl)new MediaSessionImplApi21(o));
    }
    
    public Object getMediaSession() {
        return this.mImpl.getMediaSession();
    }
    
    public Token getSessionToken() {
        return this.mImpl.getSessionToken();
    }
    
    public boolean isActive() {
        return this.mImpl.isActive();
    }
    
    public void release() {
        this.mImpl.release();
    }
    
    public void sendSessionEvent(final String s, final Bundle bundle) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("event cannot be null or empty");
        }
        this.mImpl.sendSessionEvent(s, bundle);
    }
    
    public void setActive(final boolean active) {
        this.mImpl.setActive(active);
    }
    
    public void setCallback(final Callback callback) {
        this.setCallback(callback, null);
    }
    
    public void setCallback(final Callback callback, Handler handler) {
        final MediaSessionImpl mImpl = this.mImpl;
        if (handler == null) {
            handler = new Handler();
        }
        mImpl.setCallback(callback, handler);
    }
    
    public void setFlags(final int flags) {
        this.mImpl.setFlags(flags);
    }
    
    public void setMetadata(final MediaMetadataCompat metadata) {
        this.mImpl.setMetadata(metadata);
    }
    
    public void setPlaybackState(final PlaybackStateCompat playbackState) {
        this.mImpl.setPlaybackState(playbackState);
    }
    
    public void setPlaybackToLocal(final int playbackToLocal) {
        this.mImpl.setPlaybackToLocal(playbackToLocal);
    }
    
    public void setPlaybackToRemote(final VolumeProviderCompat playbackToRemote) {
        if (playbackToRemote == null) {
            throw new IllegalArgumentException("volumeProvider may not be null!");
        }
        this.mImpl.setPlaybackToRemote(playbackToRemote);
    }
    
    public abstract static class Callback
    {
        final Object mCallbackObj;
        
        public Callback() {
            if (Build$VERSION.SDK_INT >= 21) {
                this.mCallbackObj = MediaSessionCompatApi21.createCallback((MediaSessionCompatApi21.Callback)new StubApi21());
                return;
            }
            this.mCallbackObj = null;
        }
        
        public void onCommand(final String s, final Bundle bundle, final ResultReceiver resultReceiver) {
        }
        
        public void onFastForward() {
        }
        
        public boolean onMediaButtonEvent(final Intent intent) {
            return false;
        }
        
        public void onPause() {
        }
        
        public void onPlay() {
        }
        
        public void onRewind() {
        }
        
        public void onSeekTo(final long n) {
        }
        
        public void onSetRating(final RatingCompat ratingCompat) {
        }
        
        public void onSkipToNext() {
        }
        
        public void onSkipToPrevious() {
        }
        
        public void onStop() {
        }
        
        private class StubApi21 implements MediaSessionCompatApi21.Callback
        {
            @Override
            public void onCommand(final String s, final Bundle bundle, final ResultReceiver resultReceiver) {
                MediaSessionCompat.Callback.this.onCommand(s, bundle, resultReceiver);
            }
            
            @Override
            public void onFastForward() {
                MediaSessionCompat.Callback.this.onFastForward();
            }
            
            @Override
            public boolean onMediaButtonEvent(final Intent intent) {
                return MediaSessionCompat.Callback.this.onMediaButtonEvent(intent);
            }
            
            @Override
            public void onPause() {
                MediaSessionCompat.Callback.this.onPause();
            }
            
            @Override
            public void onPlay() {
                MediaSessionCompat.Callback.this.onPlay();
            }
            
            @Override
            public void onRewind() {
                MediaSessionCompat.Callback.this.onRewind();
            }
            
            @Override
            public void onSeekTo(final long n) {
                MediaSessionCompat.Callback.this.onSeekTo(n);
            }
            
            @Override
            public void onSetRating(final Object o) {
                MediaSessionCompat.Callback.this.onSetRating(RatingCompat.fromRating(o));
            }
            
            @Override
            public void onSkipToNext() {
                MediaSessionCompat.Callback.this.onSkipToNext();
            }
            
            @Override
            public void onSkipToPrevious() {
                MediaSessionCompat.Callback.this.onSkipToPrevious();
            }
            
            @Override
            public void onStop() {
                MediaSessionCompat.Callback.this.onStop();
            }
        }
    }
    
    interface MediaSessionImpl
    {
        Object getMediaSession();
        
        Token getSessionToken();
        
        boolean isActive();
        
        void release();
        
        void sendSessionEvent(final String p0, final Bundle p1);
        
        void setActive(final boolean p0);
        
        void setCallback(final Callback p0, final Handler p1);
        
        void setFlags(final int p0);
        
        void setMetadata(final MediaMetadataCompat p0);
        
        void setPlaybackState(final PlaybackStateCompat p0);
        
        void setPlaybackToLocal(final int p0);
        
        void setPlaybackToRemote(final VolumeProviderCompat p0);
    }
    
    static class MediaSessionImplApi21 implements MediaSessionImpl
    {
        private final Object mSessionObj;
        private final Token mToken;
        
        public MediaSessionImplApi21(final Context context, final String s) {
            this.mSessionObj = MediaSessionCompatApi21.createSession(context, s);
            this.mToken = new Token(MediaSessionCompatApi21.getSessionToken(this.mSessionObj));
        }
        
        public MediaSessionImplApi21(final Object o) {
            this.mSessionObj = MediaSessionCompatApi21.verifySession(o);
            this.mToken = new Token(MediaSessionCompatApi21.getSessionToken(this.mSessionObj));
        }
        
        @Override
        public Object getMediaSession() {
            return this.mSessionObj;
        }
        
        @Override
        public Token getSessionToken() {
            return this.mToken;
        }
        
        @Override
        public boolean isActive() {
            return MediaSessionCompatApi21.isActive(this.mSessionObj);
        }
        
        @Override
        public void release() {
            MediaSessionCompatApi21.release(this.mSessionObj);
        }
        
        @Override
        public void sendSessionEvent(final String s, final Bundle bundle) {
            MediaSessionCompatApi21.sendSessionEvent(this.mSessionObj, s, bundle);
        }
        
        @Override
        public void setActive(final boolean b) {
            MediaSessionCompatApi21.setActive(this.mSessionObj, b);
        }
        
        @Override
        public void setCallback(final Callback callback, final Handler handler) {
            MediaSessionCompatApi21.setCallback(this.mSessionObj, callback.mCallbackObj, handler);
        }
        
        @Override
        public void setFlags(final int n) {
            MediaSessionCompatApi21.setFlags(this.mSessionObj, n);
        }
        
        @Override
        public void setMetadata(final MediaMetadataCompat mediaMetadataCompat) {
            MediaSessionCompatApi21.setMetadata(this.mSessionObj, mediaMetadataCompat.getMediaMetadata());
        }
        
        @Override
        public void setPlaybackState(final PlaybackStateCompat playbackStateCompat) {
            MediaSessionCompatApi21.setPlaybackState(this.mSessionObj, playbackStateCompat.getPlaybackState());
        }
        
        @Override
        public void setPlaybackToLocal(final int n) {
            MediaSessionCompatApi21.setPlaybackToLocal(this.mSessionObj, n);
        }
        
        @Override
        public void setPlaybackToRemote(final VolumeProviderCompat volumeProviderCompat) {
            MediaSessionCompatApi21.setPlaybackToRemote(this.mSessionObj, volumeProviderCompat.getVolumeProvider());
        }
    }
    
    static class MediaSessionImplBase implements MediaSessionImpl
    {
        @Override
        public Object getMediaSession() {
            return null;
        }
        
        @Override
        public Token getSessionToken() {
            return null;
        }
        
        @Override
        public boolean isActive() {
            return false;
        }
        
        @Override
        public void release() {
        }
        
        @Override
        public void sendSessionEvent(final String s, final Bundle bundle) {
        }
        
        @Override
        public void setActive(final boolean b) {
        }
        
        @Override
        public void setCallback(final Callback callback, final Handler handler) {
        }
        
        @Override
        public void setFlags(final int n) {
        }
        
        @Override
        public void setMetadata(final MediaMetadataCompat mediaMetadataCompat) {
        }
        
        @Override
        public void setPlaybackState(final PlaybackStateCompat playbackStateCompat) {
        }
        
        @Override
        public void setPlaybackToLocal(final int n) {
        }
        
        @Override
        public void setPlaybackToRemote(final VolumeProviderCompat volumeProviderCompat) {
        }
    }
    
    public static final class Token implements Parcelable
    {
        public static final Parcelable$Creator<Token> CREATOR;
        private final Parcelable mInner;
        
        static {
            CREATOR = (Parcelable$Creator)new Parcelable$Creator<Token>() {
                public Token createFromParcel(final Parcel parcel) {
                    return new Token(parcel.readParcelable((ClassLoader)null));
                }
                
                public Token[] newArray(final int n) {
                    return new Token[n];
                }
            };
        }
        
        Token(final Parcelable mInner) {
            this.mInner = mInner;
        }
        
        public int describeContents() {
            return this.mInner.describeContents();
        }
        
        public Object getToken() {
            return this.mInner;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            parcel.writeParcelable(this.mInner, n);
        }
    }
}
