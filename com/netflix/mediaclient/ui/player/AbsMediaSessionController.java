// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.media.session.MediaSessionCompat$Callback;
import java.util.List;
import android.content.pm.ResolveInfo;
import android.content.Intent;
import android.content.ComponentName;
import android.app.PendingIntent;
import android.os.Build$VERSION;
import com.netflix.mediaclient.Log;
import android.support.v4.media.session.MediaSessionCompat;
import android.content.Context;

public abstract class AbsMediaSessionController
{
    private static final String PRE21_MEDIA_BUTTON_RECEIVER_NAME = "android.support.v4.media.session.MediaButtonReceiver";
    private static final String TAG;
    private Context mContext;
    private MediaSessionCompat mMediaSession;
    
    static {
        TAG = AbsMediaSessionController.class.getSimpleName();
    }
    
    public AbsMediaSessionController(final Context mContext) {
        Log.i(AbsMediaSessionController.TAG, "Initializing MediaSessionController");
        this.mContext = mContext;
        if (this.mMediaSession != null) {
            Log.w(AbsMediaSessionController.TAG, "MediaSession was not destroyed correctly! Destroying it now.");
            this.destroy();
        }
        if (Build$VERSION.SDK_INT >= 21) {
            this.mMediaSession = new MediaSessionCompat(this.mContext.getApplicationContext(), "Netflix media session");
        }
        else {
            this.mMediaSession = new MediaSessionCompat(this.mContext.getApplicationContext(), "Netflix media sesssion", getMediaButtonReceiverComponent(this.mContext.getApplicationContext()), null);
        }
        this.mMediaSession.setFlags(1);
    }
    
    private static ComponentName getMediaButtonReceiverComponent(final Context context) {
        final Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
        intent.setPackage(context.getPackageName());
        final List queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 0);
        if (queryBroadcastReceivers.size() == 1) {
            final ResolveInfo resolveInfo = queryBroadcastReceivers.get(0);
            return new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
        }
        if (queryBroadcastReceivers.size() > 1) {
            android.util.Log.w(AbsMediaSessionController.TAG, "More than one BroadcastReceiver that handles android.intent.action.MEDIA_BUTTON was found, returning first one.");
            return new ComponentName(queryBroadcastReceivers.get(0).activityInfo.packageName, "android.support.v4.media.session.MediaButtonReceiver");
        }
        return null;
    }
    
    public void destroy() {
        Log.i(AbsMediaSessionController.TAG, "destroy");
        this.mMediaSession.release();
        this.mMediaSession = null;
    }
    
    protected void setActive(final boolean active) {
        if (this.mMediaSession != null) {
            this.mMediaSession.setActive(active);
        }
    }
    
    public void setCallback(final MediaSessionCompat$Callback callback) {
        this.mMediaSession.setCallback(callback);
    }
    
    protected void setPlaybackState(final PlaybackStateCompat playbackState) {
        Log.i(AbsMediaSessionController.TAG, "setPlayerState to " + playbackState);
        if (this.mMediaSession != null) {
            this.mMediaSession.setPlaybackState(playbackState);
        }
    }
    
    protected abstract void startMediaSession();
    
    protected abstract void stopMediaSession();
}
