// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import java.util.List;
import android.support.v4.media.VolumeProviderCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.os.Handler;
import java.util.Iterator;
import android.os.Bundle;
import android.util.TypedValue;
import android.content.Intent;
import android.util.Log;
import android.text.TextUtils;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.os.Build$VERSION;
import android.content.Context;
import java.util.ArrayList;

public class MediaSessionCompat
{
    static final String ACTION_ARGUMENT_EXTRAS = "android.support.v4.media.session.action.ARGUMENT_EXTRAS";
    static final String ACTION_ARGUMENT_MEDIA_ID = "android.support.v4.media.session.action.ARGUMENT_MEDIA_ID";
    static final String ACTION_ARGUMENT_QUERY = "android.support.v4.media.session.action.ARGUMENT_QUERY";
    static final String ACTION_ARGUMENT_URI = "android.support.v4.media.session.action.ARGUMENT_URI";
    static final String ACTION_PLAY_FROM_URI = "android.support.v4.media.session.action.PLAY_FROM_URI";
    static final String ACTION_PREPARE = "android.support.v4.media.session.action.PREPARE";
    static final String ACTION_PREPARE_FROM_MEDIA_ID = "android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID";
    static final String ACTION_PREPARE_FROM_SEARCH = "android.support.v4.media.session.action.PREPARE_FROM_SEARCH";
    static final String ACTION_PREPARE_FROM_URI = "android.support.v4.media.session.action.PREPARE_FROM_URI";
    static final String EXTRA_BINDER = "android.support.v4.media.session.EXTRA_BINDER";
    public static final int FLAG_HANDLES_MEDIA_BUTTONS = 1;
    public static final int FLAG_HANDLES_TRANSPORT_CONTROLS = 2;
    private static final int MAX_BITMAP_SIZE_IN_DP = 320;
    static final String TAG = "MediaSessionCompat";
    static int sMaxBitmapSize;
    private final ArrayList<MediaSessionCompat$OnActiveChangeListener> mActiveListeners;
    private final MediaControllerCompat mController;
    private final MediaSessionCompat$MediaSessionImpl mImpl;
    
    private MediaSessionCompat(final Context context, final MediaSessionCompat$MediaSessionImpl mImpl) {
        this.mActiveListeners = new ArrayList<MediaSessionCompat$OnActiveChangeListener>();
        this.mImpl = mImpl;
        if (Build$VERSION.SDK_INT >= 21) {
            this.setCallback(new MediaSessionCompat$1(this));
        }
        this.mController = new MediaControllerCompat(context, this);
    }
    
    public MediaSessionCompat(final Context context, final String s) {
        this(context, s, null, null);
    }
    
    public MediaSessionCompat(final Context context, final String s, ComponentName mediaButtonReceiverComponent, final PendingIntent pendingIntent) {
        this.mActiveListeners = new ArrayList<MediaSessionCompat$OnActiveChangeListener>();
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("tag must not be null or empty");
        }
        ComponentName component;
        if ((component = mediaButtonReceiverComponent) == null) {
            mediaButtonReceiverComponent = MediaButtonReceiver.getMediaButtonReceiverComponent(context);
            if ((component = mediaButtonReceiverComponent) == null) {
                Log.w("MediaSessionCompat", "Couldn't find a unique registered media button receiver in the given context.");
                component = mediaButtonReceiverComponent;
            }
        }
        PendingIntent broadcast = pendingIntent;
        if (component != null && (broadcast = pendingIntent) == null) {
            final Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
            intent.setComponent(component);
            broadcast = PendingIntent.getBroadcast(context, 0, intent, 0);
        }
        if (Build$VERSION.SDK_INT >= 21) {
            (this.mImpl = new MediaSessionCompat$MediaSessionImplApi21(context, s)).setMediaButtonReceiver(broadcast);
        }
        else {
            this.mImpl = new MediaSessionCompat$MediaSessionImplBase(context, s, component, broadcast);
        }
        this.mController = new MediaControllerCompat(context, this);
        if (MediaSessionCompat.sMaxBitmapSize == 0) {
            MediaSessionCompat.sMaxBitmapSize = (int)TypedValue.applyDimension(1, 320.0f, context.getResources().getDisplayMetrics());
        }
    }
    
    public static MediaSessionCompat fromMediaSession(final Context context, final Object o) {
        if (context == null || o == null || Build$VERSION.SDK_INT < 21) {
            return null;
        }
        return new MediaSessionCompat(context, new MediaSessionCompat$MediaSessionImplApi21(o));
    }
    
    @Deprecated
    public static MediaSessionCompat obtain(final Context context, final Object o) {
        return fromMediaSession(context, o);
    }
    
    public void addOnActiveChangeListener(final MediaSessionCompat$OnActiveChangeListener mediaSessionCompat$OnActiveChangeListener) {
        if (mediaSessionCompat$OnActiveChangeListener == null) {
            throw new IllegalArgumentException("Listener may not be null");
        }
        this.mActiveListeners.add(mediaSessionCompat$OnActiveChangeListener);
    }
    
    public String getCallingPackage() {
        return this.mImpl.getCallingPackage();
    }
    
    public MediaControllerCompat getController() {
        return this.mController;
    }
    
    public Object getMediaSession() {
        return this.mImpl.getMediaSession();
    }
    
    public Object getRemoteControlClient() {
        return this.mImpl.getRemoteControlClient();
    }
    
    public MediaSessionCompat$Token getSessionToken() {
        return this.mImpl.getSessionToken();
    }
    
    public boolean isActive() {
        return this.mImpl.isActive();
    }
    
    public void release() {
        this.mImpl.release();
    }
    
    public void removeOnActiveChangeListener(final MediaSessionCompat$OnActiveChangeListener mediaSessionCompat$OnActiveChangeListener) {
        if (mediaSessionCompat$OnActiveChangeListener == null) {
            throw new IllegalArgumentException("Listener may not be null");
        }
        this.mActiveListeners.remove(mediaSessionCompat$OnActiveChangeListener);
    }
    
    public void sendSessionEvent(final String s, final Bundle bundle) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("event cannot be null or empty");
        }
        this.mImpl.sendSessionEvent(s, bundle);
    }
    
    public void setActive(final boolean active) {
        this.mImpl.setActive(active);
        final Iterator<MediaSessionCompat$OnActiveChangeListener> iterator = this.mActiveListeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onActiveChanged();
        }
    }
    
    public void setCallback(final MediaSessionCompat$Callback mediaSessionCompat$Callback) {
        this.setCallback(mediaSessionCompat$Callback, null);
    }
    
    public void setCallback(final MediaSessionCompat$Callback mediaSessionCompat$Callback, Handler handler) {
        final MediaSessionCompat$MediaSessionImpl mImpl = this.mImpl;
        if (handler == null) {
            handler = new Handler();
        }
        mImpl.setCallback(mediaSessionCompat$Callback, handler);
    }
    
    public void setExtras(final Bundle extras) {
        this.mImpl.setExtras(extras);
    }
    
    public void setFlags(final int flags) {
        this.mImpl.setFlags(flags);
    }
    
    public void setMediaButtonReceiver(final PendingIntent mediaButtonReceiver) {
        this.mImpl.setMediaButtonReceiver(mediaButtonReceiver);
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
    
    public void setQueue(final List<MediaSessionCompat$QueueItem> queue) {
        this.mImpl.setQueue(queue);
    }
    
    public void setQueueTitle(final CharSequence queueTitle) {
        this.mImpl.setQueueTitle(queueTitle);
    }
    
    public void setRatingType(final int ratingType) {
        this.mImpl.setRatingType(ratingType);
    }
    
    public void setSessionActivity(final PendingIntent sessionActivity) {
        this.mImpl.setSessionActivity(sessionActivity);
    }
}
