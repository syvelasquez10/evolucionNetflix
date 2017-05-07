// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.notification;

import android.annotation.SuppressLint;
import android.app.Notification$Style;
import android.app.Notification$MediaStyle;
import android.app.Service;
import android.util.Pair;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.service.NetflixService;
import android.app.PendingIntent;
import com.netflix.mediaclient.Log;
import android.app.NotificationManager;
import android.app.Notification;
import com.netflix.mediaclient.service.mdx.MdxAgent;
import android.content.Context;
import android.app.Notification$Builder;
import android.graphics.Bitmap;

public final class MdxNotificationManagerLollipop implements IMdxNotificationManager
{
    private static final String TAG = "nf_mdxnotification";
    private Bitmap boxart;
    private Notification$Builder builder;
    private BuilderFactory builderFactory;
    private Context context;
    private boolean isEpisode;
    private boolean isPlaying;
    private boolean isPostplay;
    private final int mNotificationId;
    private String mainTitle;
    private MdxAgent mdxAgent;
    private Notification notification;
    private NotificationManager notificationManager;
    private String secondTitle;
    
    public MdxNotificationManagerLollipop(final Context context, final boolean isEpisode, final MdxAgent mdxAgent) {
        this.mNotificationId = 1;
        this.builderFactory = new BuilderFactory();
        Log.d("nf_mdxnotification", "is episode " + isEpisode);
        this.isEpisode = isEpisode;
        this.context = context;
        this.mdxAgent = mdxAgent;
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
    
    private void init() {
        this.notificationManager = (NotificationManager)this.context.getSystemService("notification");
    }
    
    private void notifyChange() {
        if (this.builder == null) {
            return;
        }
        if (this.boxart != null) {
            this.builder.setLargeIcon(ViewUtils.createSquaredBitmap(this.boxart));
            this.builder.setColor(this.context.getResources().getColor(2131296356));
        }
        if (this.mainTitle != null) {
            this.builder.setContentText((CharSequence)this.mainTitle);
        }
        if (this.secondTitle != null) {
            this.builder.setSubText((CharSequence)this.secondTitle);
        }
        if (this.isPostplay) {
            this.builder.setContentTitle((CharSequence)this.context.getResources().getString(2131493251));
        }
        else {
            this.builder.setContentTitle((CharSequence)this.context.getResources().getString(2131493250));
        }
        this.builder.setSmallIcon(2130837768);
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
        if (Log.isLoggable("nf_mdxnotification", 3)) {
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
        this.stopNotification(service);
        service.startForeground(1, notification);
        this.isPostplay = isPostplay;
        this.isPlaying = true;
    }
    
    @Override
    public void stopNotification(final Service service) {
        this.cancelNotification();
        service.stopForeground(true);
        this.isPlaying = false;
    }
    
    @Override
    public void stopPostplayNotification(final Service service) {
        if (this.isPostplay) {
            service.stopForeground(true);
            this.isPlaying = false;
        }
    }
    
    private class BuilderFactory
    {
        private static final String PAUSE = "Pause";
        private static final String PLAY = "Play";
        private static final String REWIND = "Rewind";
        private static final int SKIPBACK_SECONDS = -30;
        private static final String STOP = "Stop";
        
        @SuppressLint({ "InlinedApi" })
        private Notification$Builder createPlayerBuilder() {
            return new Notification$Builder(MdxNotificationManagerLollipop.this.context).setOngoing(true).setVisibility(1).setOnlyAlertOnce(true).setShowWhen(false).setSmallIcon(2130837768).setStyle((Notification$Style)new Notification$MediaStyle().setShowActionsInCompactView(new int[] { 0, 1 })).addAction(2130837775, (CharSequence)"Rewind", MdxNotificationManagerLollipop.this.mdxAgent.getSkipbackIntent(-30)).addAction(2130837779, (CharSequence)"Pause", MdxNotificationManagerLollipop.this.mdxAgent.getResumeIntent()).addAction(2130837781, (CharSequence)"Stop", MdxNotificationManagerLollipop.this.mdxAgent.getStopIntent());
        }
        
        @SuppressLint({ "InlinedApi" })
        private Notification$Builder createPlayerPausedBuilder() {
            return new Notification$Builder(MdxNotificationManagerLollipop.this.context).setOngoing(true).setVisibility(1).setOnlyAlertOnce(true).setShowWhen(false).setSmallIcon(2130837768).setStyle((Notification$Style)new Notification$MediaStyle().setShowActionsInCompactView(new int[] { 0, 1 })).addAction(2130837775, (CharSequence)"Rewind", MdxNotificationManagerLollipop.this.mdxAgent.getSkipbackIntent(-30)).addAction(2130837777, (CharSequence)"Play", MdxNotificationManagerLollipop.this.mdxAgent.getPauseIntent()).addAction(2130837781, (CharSequence)"Stop", MdxNotificationManagerLollipop.this.mdxAgent.getStopIntent());
        }
        
        @SuppressLint({ "InlinedApi" })
        private Notification$Builder createPostPlayerBuilder() {
            return new Notification$Builder(MdxNotificationManagerLollipop.this.context).setOngoing(true).setVisibility(1).setShowWhen(false).setOnlyAlertOnce(true).setSmallIcon(2130837768).setStyle((Notification$Style)new Notification$MediaStyle().setShowActionsInCompactView(new int[] { 0, 1 })).addAction(2130837779, (CharSequence)"Play", MdxNotificationManagerLollipop.this.mdxAgent.getPlayNextIntent()).addAction(2130837781, (CharSequence)"Stop", MdxNotificationManagerLollipop.this.mdxAgent.getStopIntent());
        }
        
        Notification$Builder getBuilder(final boolean b, final boolean b2) {
            if (b) {
                return this.createPostPlayerBuilder();
            }
            if (b2) {
                return this.createPlayerBuilder();
            }
            return this.createPlayerPausedBuilder();
        }
    }
    
    public interface MdxNotificationIntentRetriever
    {
        PendingIntent getNoActionIntent();
        
        PendingIntent getPauseIntent();
        
        PendingIntent getPlayNextIntent();
        
        PendingIntent getResumeIntent();
        
        PendingIntent getSkipbackIntent(final int p0);
        
        PendingIntent getStopIntent();
    }
}
