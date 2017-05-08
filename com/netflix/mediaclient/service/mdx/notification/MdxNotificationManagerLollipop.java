// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.notification;

import android.app.Service;
import android.util.Pair;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import android.graphics.BitmapFactory;
import com.netflix.mediaclient.service.NetflixService;
import android.app.PendingIntent;
import android.app.Notification$MediaStyle;
import com.netflix.mediaclient.Log;
import android.app.NotificationManager;
import android.app.Notification;
import com.netflix.mediaclient.service.mdx.MediaSessionController;
import com.netflix.mediaclient.service.mdx.MdxAgent;
import android.content.Context;
import android.app.Notification$Builder;
import android.graphics.Bitmap;
import android.annotation.TargetApi;

@TargetApi(21)
public final class MdxNotificationManagerLollipop implements IMdxNotificationManager
{
    private static final String TAG = "nf_mdxnotification";
    private Bitmap boxart;
    private Notification$Builder builder;
    private MdxNotificationManagerLollipop$BuilderFactory builderFactory;
    private Context context;
    private boolean isEpisode;
    private boolean isPlaying;
    private boolean isPostplay;
    private final int mNotificationId;
    private String mainTitle;
    private MdxAgent mdxAgent;
    private MediaSessionController mediaSessionController;
    private Notification notification;
    private NotificationManager notificationManager;
    private int runningServiceNotificationId;
    private String secondTitle;
    
    public MdxNotificationManagerLollipop(final Context context, final boolean isEpisode, final MdxAgent mdxAgent, final MediaSessionController mediaSessionController) {
        this.mNotificationId = 1;
        this.builderFactory = new MdxNotificationManagerLollipop$BuilderFactory(this, null);
        Log.d("nf_mdxnotification", "is episode " + isEpisode);
        this.isEpisode = isEpisode;
        this.context = context;
        this.mdxAgent = mdxAgent;
        this.mediaSessionController = mediaSessionController;
        this.init();
        this.createInitialBuilder();
    }
    
    private void createInitialBuilder() {
        this.builder = this.builderFactory.getBuilder(false, false);
    }
    
    private PendingIntent createNotificationPendingIntent() {
        if (this.context == null) {
            return null;
        }
        return PendingIntent.getBroadcast(this.context, 0, NetflixService.createShowMdxPlayerBroadcastIntent(), 134217728);
    }
    
    private Bitmap getDefaultBoxArt() {
        return BitmapFactory.decodeResource(this.context.getResources(), 2130837781);
    }
    
    private Notification$MediaStyle getStyle() {
        final Notification$MediaStyle setShowActionsInCompactView = new Notification$MediaStyle().setShowActionsInCompactView(new int[] { 0, 1 });
        if (this.mediaSessionController != null && this.mediaSessionController.getMediaSessionToken() != null) {
            setShowActionsInCompactView.setMediaSession(this.mediaSessionController.getMediaSessionToken());
            return setShowActionsInCompactView;
        }
        ErrorLoggingManager.logHandledException(new Throwable("SPY-7597 - Got null mediaSessionController for MdxNotificationManagerLollipop"));
        return setShowActionsInCompactView;
    }
    
    private void init() {
        this.notificationManager = (NotificationManager)this.context.getSystemService("notification");
    }
    
    private void notifyChange() {
        if (this.builder == null) {
            return;
        }
        if (this.boxart == null) {
            this.boxart = this.getDefaultBoxArt();
        }
        if (this.boxart == null) {
            Log.e("nf_mdxnotification", "We failed to decode resource!");
        }
        else {
            this.builder.setLargeIcon(ViewUtils.createSquaredBitmap(this.boxart));
        }
        if (this.mainTitle != null) {
            this.builder.setContentText((CharSequence)this.mainTitle);
        }
        if (this.secondTitle != null) {
            this.builder.setSubText((CharSequence)this.secondTitle);
        }
        if (this.isPostplay) {
            this.builder.setContentTitle((CharSequence)this.context.getResources().getString(2131231105));
        }
        else {
            this.builder.setContentTitle((CharSequence)this.context.getResources().getString(2131231249));
        }
        this.builder.setSmallIcon(2130837754);
        this.notification = this.builder.build();
        this.notificationManager.notify(1, this.notification);
    }
    
    private void setPlayerTitles(final String s, final String s2) {
        if (this.builder == null) {
            return;
        }
        this.mainTitle = s;
        this.secondTitle = s2;
        if (this.isEpisode) {
            this.builder.setTicker((CharSequence)s2);
            return;
        }
        this.builder.setTicker((CharSequence)s);
    }
    
    private void updateNotification(final boolean b, final boolean b2, final boolean b3) {
        this.updatePlayerNotification(b, b2, b3);
    }
    
    private void updatePlayerNotification(final boolean b, final boolean b2, final boolean isPostplay) {
        if (this.builder == null || this.notificationManager == null || !this.isPlaying) {
            return;
        }
        this.isPostplay = isPostplay;
        (this.builder = this.builderFactory.getBuilder(isPostplay, b)).setContentIntent(this.createNotificationPendingIntent());
        this.notifyChange();
    }
    
    @Override
    public void cancelNotification() {
        if (this.notificationManager == null) {
            return;
        }
        this.notificationManager.cancel(1);
    }
    
    @Override
    public Pair<Integer, Notification> getNotification(final boolean isPostplay) {
        this.isPostplay = isPostplay;
        this.builder = this.builderFactory.getBuilder(isPostplay, false);
        if (this.builder != null) {
            this.notification = this.builder.build();
        }
        return (Pair<Integer, Notification>)Pair.create((Object)1, (Object)this.notification);
    }
    
    @Override
    public boolean isInPostPlay() {
        return this.isPostplay;
    }
    
    @Override
    public void setBoxart(final Bitmap boxart) {
        if (boxart == null) {
            return;
        }
        this.boxart = boxart;
    }
    
    @Override
    public void setBoxartNotify(final Bitmap boxart) {
        if (boxart == null) {
            return;
        }
        this.setBoxart(boxart);
        this.notifyChange();
    }
    
    @Override
    public void setPlayerStateNotify(final boolean b, final boolean b2) {
        this.updatePlayerNotification(b, b2, false);
    }
    
    @Override
    public void setTitlesNotify(final boolean isEpisode, final String s, final String s2) {
        if (Log.isLoggable()) {
            Log.d("nf_mdxnotification", "is episode " + isEpisode + ",>" + s + ",>" + s2);
        }
        this.isEpisode = isEpisode;
        this.setPlayerTitles(s, s2);
        this.notifyChange();
    }
    
    @Override
    public void setUpNextStateNotify(final boolean b, final boolean b2, final boolean b3) {
        if (b3) {
            this.updateNotification(b, b2, b3);
        }
    }
    
    @Override
    public void startNotification(final Notification notification, final Service service, final boolean isPostplay) {
        if (notification == null) {
            return;
        }
        if (1 != this.runningServiceNotificationId) {
            service.startForeground(1, notification);
            this.runningServiceNotificationId = 1;
        }
        this.isPostplay = isPostplay;
        this.isPlaying = true;
    }
    
    @Override
    public void stopNotification(final Service service) {
        this.cancelNotification();
        service.stopForeground(true);
        this.runningServiceNotificationId = 0;
        this.isPlaying = false;
    }
    
    @Override
    public void stopPostplayNotification(final Service service) {
        if (this.isPostplay) {
            service.stopForeground(true);
            this.runningServiceNotificationId = 0;
            this.isPlaying = false;
        }
    }
}
