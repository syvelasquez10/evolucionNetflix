// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.notification;

import android.graphics.Bitmap;
import android.util.Pair;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.service.NetflixService;
import android.app.PendingIntent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.mdx.MdxAgent;
import android.support.v4.app.NotificationCompat$Builder;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

public final class MdxNotificationManager implements IMdxNotificationManager
{
    private static final String TAG = "nf_mdxnotification";
    private final Context context;
    private boolean isEpisode;
    private boolean isPlaying;
    private boolean isPostplay;
    private final int mNotificationId;
    private MdxRemoteViews mdxPlayerRemoteViews;
    private MdxRemoteViews mdxPostPlayerRemoteViews;
    private NotificationManager notificationManager;
    private Notification notificationPlayer;
    private Notification notificationPostPlayer;
    private NotificationCompat$Builder playerBuilder;
    private NotificationCompat$Builder postPlayerBuilder;
    private boolean supportBigContent;
    
    public MdxNotificationManager(final Context context, final boolean isEpisode, final MdxAgent mdxAgent) {
        this.mNotificationId = 1;
        Log.d("nf_mdxnotification", "is episode " + isEpisode);
        this.isEpisode = isEpisode;
        this.context = context;
        this.init();
        this.createRemoteViews(mdxAgent);
        this.createBuilders();
    }
    
    private void createBuilders() {
        this.createPlayerBuilder();
        this.createPostPlayerBuilder();
    }
    
    private PendingIntent createNotificationPendingIntent() {
        if (this.context == null) {
            return null;
        }
        return PendingIntent.getBroadcast(this.context, 0, NetflixService.createShowMdxPlayerBroadcastIntent(), 134217728);
    }
    
    private void createPlayerBuilder() {
        this.playerBuilder = new NotificationCompat$Builder(this.context).setOngoing(true).setOnlyAlertOnce(true).setSmallIcon(2130837647).setWhen(System.currentTimeMillis());
    }
    
    private void createPostPlayerBuilder() {
        this.postPlayerBuilder = new NotificationCompat$Builder(this.context).setOngoing(true).setOnlyAlertOnce(true).setSmallIcon(2130837647).setWhen(System.currentTimeMillis());
    }
    
    private void createRemoteViews(final MdxAgent mdxAgent) {
        this.mdxPlayerRemoteViews = new MdxPlayerRemoteViews(this.context.getPackageName(), this.isEpisode, mdxAgent, this.context);
        this.mdxPostPlayerRemoteViews = new MdxPostPlayerRemoteViews(this.context.getPackageName(), this.isEpisode, mdxAgent, this.context);
    }
    
    private void init() {
        this.notificationManager = (NotificationManager)this.context.getSystemService("notification");
        if (AndroidUtils.getAndroidVersion() >= 16) {
            this.supportBigContent = true;
        }
    }
    
    private void setPlayerTitles(final String ticker, final String ticker2) {
        if (this.playerBuilder == null || this.mdxPlayerRemoteViews == null) {
            return;
        }
        this.playerBuilder.setContentIntent(this.createNotificationPendingIntent());
        if (this.isEpisode) {
            this.playerBuilder.setTicker(ticker2);
        }
        else {
            this.playerBuilder.setTicker(ticker);
        }
        this.mdxPlayerRemoteViews.setTitles(this.isEpisode, ticker, ticker2);
    }
    
    private void setPostPlayerTitles(final String ticker, final String ticker2) {
        if (this.postPlayerBuilder == null || this.mdxPostPlayerRemoteViews == null) {
            return;
        }
        this.postPlayerBuilder.setContentIntent(this.createNotificationPendingIntent());
        if (this.isEpisode) {
            this.postPlayerBuilder.setTicker(ticker2);
        }
        else {
            this.postPlayerBuilder.setTicker(ticker);
        }
        this.mdxPostPlayerRemoteViews.setTitles(this.isEpisode, ticker, ticker2);
    }
    
    private void updateNotification() {
        if (this.isPostplay) {
            this.updatePostPlayerNotification();
            return;
        }
        this.updatePlayerNotification();
    }
    
    private void updatePlayerNotification() {
        if (this.playerBuilder == null || this.mdxPlayerRemoteViews == null || this.notificationManager == null || !this.isPlaying) {
            return;
        }
        this.playerBuilder.setContent(this.mdxPlayerRemoteViews.getRemoteView());
        this.notificationPlayer = this.playerBuilder.build();
        if (this.supportBigContent) {
            this.notificationPlayer.bigContentView = this.mdxPlayerRemoteViews.getRemoteViewBigContetnt();
        }
        this.notificationManager.notify(1, this.notificationPlayer);
    }
    
    private void updatePostPlayerNotification() {
        if (this.postPlayerBuilder == null || this.mdxPostPlayerRemoteViews == null || this.notificationManager == null || !this.isPlaying) {
            return;
        }
        this.postPlayerBuilder.setContent(this.mdxPostPlayerRemoteViews.getRemoteView());
        this.notificationPostPlayer = this.postPlayerBuilder.build();
        if (this.supportBigContent) {
            this.notificationPostPlayer.bigContentView = this.mdxPostPlayerRemoteViews.getRemoteViewBigContetnt();
        }
        this.notificationManager.notify(1, this.notificationPostPlayer);
    }
    
    @Override
    public void cancelNotification() {
        if (this.notificationManager == null) {
            return;
        }
        this.notificationManager.cancel(1);
    }
    
    @Override
    public Pair<Integer, Notification> getNotification(final boolean b) {
        if (b) {
            if (this.postPlayerBuilder != null) {
                this.notificationPostPlayer = this.postPlayerBuilder.build();
            }
            return (Pair<Integer, Notification>)Pair.create((Object)1, (Object)this.notificationPostPlayer);
        }
        if (this.playerBuilder != null) {
            this.notificationPlayer = this.playerBuilder.build();
        }
        return (Pair<Integer, Notification>)Pair.create((Object)1, (Object)this.notificationPlayer);
    }
    
    @Override
    public boolean isInPostPlay() {
        return this.isPostplay;
    }
    
    @Override
    public void setBoxart(final Bitmap bitmap) {
        if (bitmap == null || this.mdxPlayerRemoteViews == null || this.mdxPostPlayerRemoteViews == null) {
            return;
        }
        this.mdxPlayerRemoteViews.setBoxart(bitmap);
        this.mdxPostPlayerRemoteViews.setBoxart(bitmap);
    }
    
    @Override
    public void setBoxartNotify(final Bitmap boxart) {
        if (boxart == null) {
            return;
        }
        this.setBoxart(boxart);
        this.updateNotification();
    }
    
    @Override
    public void setPlayerStateNotify(final boolean b, final boolean b2) {
        if (this.mdxPlayerRemoteViews == null) {
            return;
        }
        this.mdxPlayerRemoteViews.setState(b, b2);
        this.updatePlayerNotification();
    }
    
    @Override
    public void setTitlesNotify(final boolean isEpisode, final String s, final String s2) {
        if (Log.isLoggable()) {
            Log.d("nf_mdxnotification", "is episode " + isEpisode + ",>" + s + ",>" + s2);
        }
        this.isEpisode = isEpisode;
        this.setPlayerTitles(s, s2);
        this.setPostPlayerTitles(s, s2);
        this.updateNotification();
    }
    
    @Override
    public void setUpNextStateNotify(final boolean b, final boolean b2, final boolean b3) {
        if (b3 && this.mdxPlayerRemoteViews != null && this.mdxPostPlayerRemoteViews != null) {
            this.mdxPlayerRemoteViews.setState(b, b2);
            this.mdxPostPlayerRemoteViews.setState(b, b2);
            this.updateNotification();
        }
    }
    
    @Override
    public void startNotification(final Notification notification, final NetflixService netflixService, final boolean isPostplay) {
        this.stopNotification(netflixService);
        if (notification == null) {
            return;
        }
        netflixService.requestForegroundForNotification(1, notification);
        this.isPostplay = isPostplay;
        this.isPlaying = true;
    }
    
    @Override
    public void stopNotification(final NetflixService netflixService) {
        this.cancelNotification();
        netflixService.requestBackgroundForNotification(1, true);
        this.isPlaying = false;
    }
    
    @Override
    public void stopPostplayNotification(final NetflixService netflixService) {
        if (this.isPostplay) {
            netflixService.requestBackgroundForNotification(1, true);
            this.isPlaying = false;
        }
    }
}
