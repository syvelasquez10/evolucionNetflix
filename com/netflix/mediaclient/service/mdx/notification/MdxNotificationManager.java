// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.notification;

import android.app.Service;
import android.graphics.Bitmap;
import android.util.Pair;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.service.NetflixService;
import android.app.PendingIntent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.mdx.MdxAgent;
import android.app.Notification;
import android.support.v4.app.NotificationCompat;
import android.app.NotificationManager;
import android.content.Context;

public final class MdxNotificationManager
{
    private static final String TAG = "nf_mdxnotification";
    private boolean isPlaying;
    private boolean isPostplay;
    private Context mContext;
    private boolean mIsEpisode;
    private MdxRemoteViews mMdxPlayerRemoteViews;
    private MdxRemoteViews mMdxPostPlayerRemoteViews;
    private final int mNotificationId;
    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mPlayerBuilder;
    private NotificationCompat.Builder mPostPlayerBuilder;
    private boolean mSupportBigContent;
    private Notification notificationPlayer;
    private Notification notificationPostPlayer;
    
    public MdxNotificationManager(final Context mContext, final boolean mIsEpisode, final MdxAgent mdxAgent) {
        this.mNotificationId = 1;
        Log.d("nf_mdxnotification", "is episode " + mIsEpisode);
        this.mIsEpisode = mIsEpisode;
        this.mContext = mContext;
        this.init();
        this.createRemoteViews(mdxAgent);
        this.createBuilders();
    }
    
    private void createBuilders() {
        this.createPlayerBuilder();
        this.createPostPlayerBuilder();
    }
    
    private PendingIntent createNotificationPendingIntent() {
        if (this.mContext == null) {
            return null;
        }
        return PendingIntent.getBroadcast(this.mContext, 0, NetflixService.createShowMdxPlayerBroadcastIntent(), 134217728);
    }
    
    private void createPlayerBuilder() {
        this.mPlayerBuilder = new NotificationCompat.Builder(this.mContext).setOngoing(true).setOnlyAlertOnce(true).setSmallIcon(2130837772).setWhen(System.currentTimeMillis());
    }
    
    private void createPostPlayerBuilder() {
        this.mPostPlayerBuilder = new NotificationCompat.Builder(this.mContext).setOngoing(true).setOnlyAlertOnce(true).setSmallIcon(2130837772).setWhen(System.currentTimeMillis());
    }
    
    private void createRemoteViews(final MdxAgent mdxAgent) {
        this.mMdxPlayerRemoteViews = new MdxPlayerRemoteViews(this.mContext.getPackageName(), this.mIsEpisode, mdxAgent, this.mContext);
        this.mMdxPostPlayerRemoteViews = new MdxPostPlayerRemoteViews(this.mContext.getPackageName(), this.mIsEpisode, mdxAgent, this.mContext);
    }
    
    private void init() {
        this.mNotificationManager = (NotificationManager)this.mContext.getSystemService("notification");
        if (AndroidUtils.getAndroidVersion() >= 16) {
            this.mSupportBigContent = true;
        }
    }
    
    private void setPlayerTitles(final String ticker, final String ticker2) {
        if (this.mPlayerBuilder == null || this.mMdxPlayerRemoteViews == null) {
            return;
        }
        this.mPlayerBuilder.setContentIntent(this.createNotificationPendingIntent());
        if (this.mIsEpisode) {
            this.mPlayerBuilder.setTicker(ticker2);
        }
        else {
            this.mPlayerBuilder.setTicker(ticker);
        }
        this.mMdxPlayerRemoteViews.setTitles(this.mIsEpisode, ticker, ticker2);
    }
    
    private void setPostPlayerTitles(final String ticker, final String ticker2) {
        if (this.mPostPlayerBuilder == null || this.mMdxPostPlayerRemoteViews == null) {
            return;
        }
        this.mPostPlayerBuilder.setContentIntent(this.createNotificationPendingIntent());
        if (this.mIsEpisode) {
            this.mPostPlayerBuilder.setTicker(ticker2);
        }
        else {
            this.mPostPlayerBuilder.setTicker(ticker);
        }
        this.mMdxPostPlayerRemoteViews.setTitles(this.mIsEpisode, ticker, ticker2);
    }
    
    private void updateNotification() {
        if (this.isPostplay) {
            this.updatePostPlayerNotification();
            return;
        }
        this.updatePlayerNotification();
    }
    
    private void updatePlayerNotification() {
        if (this.mPlayerBuilder == null || this.mMdxPlayerRemoteViews == null || this.mNotificationManager == null || !this.isPlaying) {
            return;
        }
        this.mPlayerBuilder.setContent(this.mMdxPlayerRemoteViews.getRemoteView());
        this.notificationPlayer = this.mPlayerBuilder.build();
        if (this.mSupportBigContent) {
            this.notificationPlayer.bigContentView = this.mMdxPlayerRemoteViews.getRemoteViewBigContetnt();
        }
        this.mNotificationManager.notify(1, this.notificationPlayer);
    }
    
    private void updatePostPlayerNotification() {
        if (this.mPostPlayerBuilder == null || this.mMdxPostPlayerRemoteViews == null || this.mNotificationManager == null || !this.isPlaying) {
            return;
        }
        this.mPostPlayerBuilder.setContent(this.mMdxPostPlayerRemoteViews.getRemoteView());
        this.notificationPostPlayer = this.mPostPlayerBuilder.build();
        if (this.mSupportBigContent) {
            this.notificationPostPlayer.bigContentView = this.mMdxPostPlayerRemoteViews.getRemoteViewBigContetnt();
        }
        this.mNotificationManager.notify(1, this.notificationPostPlayer);
    }
    
    public void cancelNotification() {
        if (this.mNotificationManager == null) {
            return;
        }
        this.mNotificationManager.cancel(1);
    }
    
    public Pair<Integer, Notification> getNotification(final boolean b) {
        if (b) {
            if (this.mPostPlayerBuilder != null) {
                this.notificationPostPlayer = this.mPostPlayerBuilder.build();
            }
            return (Pair<Integer, Notification>)Pair.create((Object)1, (Object)this.notificationPostPlayer);
        }
        if (this.mPlayerBuilder != null) {
            this.notificationPlayer = this.mPlayerBuilder.build();
        }
        return (Pair<Integer, Notification>)Pair.create((Object)1, (Object)this.notificationPlayer);
    }
    
    public boolean isInPostPlay() {
        return this.isPostplay;
    }
    
    public void setBoxart(final Bitmap bitmap) {
        if (bitmap == null || this.mMdxPlayerRemoteViews == null || this.mMdxPostPlayerRemoteViews == null) {
            return;
        }
        this.mMdxPlayerRemoteViews.setBoxart(bitmap);
        this.mMdxPostPlayerRemoteViews.setBoxart(bitmap);
    }
    
    public void setBoxartNotify(final Bitmap boxart) {
        if (boxart == null) {
            return;
        }
        this.setBoxart(boxart);
        this.updateNotification();
    }
    
    public void setPlayerStateNotify(final boolean b, final boolean b2) {
        if (this.mMdxPlayerRemoteViews == null) {
            return;
        }
        this.mMdxPlayerRemoteViews.setState(b, b2);
        this.updatePlayerNotification();
    }
    
    public void setTitlesNotify(final boolean mIsEpisode, final String s, final String s2) {
        if (Log.isLoggable("nf_mdxnotification", 3)) {
            Log.d("nf_mdxnotification", "is episode " + mIsEpisode + ",>" + s + ",>" + s2);
        }
        this.mIsEpisode = mIsEpisode;
        this.setPlayerTitles(s, s2);
        this.setPostPlayerTitles(s, s2);
        this.updateNotification();
    }
    
    public void setUpNextStateNotify(final boolean b, final boolean b2, final boolean b3) {
        if (b3 && this.mMdxPlayerRemoteViews != null && this.mMdxPostPlayerRemoteViews != null) {
            this.mMdxPlayerRemoteViews.setState(b, b2);
            this.mMdxPostPlayerRemoteViews.setState(b, b2);
            this.updateNotification();
        }
    }
    
    public void startNotification(final Notification notification, final Service service, final boolean isPostplay) {
        this.stopNotification(service);
        service.startForeground(1, notification);
        this.isPostplay = isPostplay;
        this.isPlaying = true;
    }
    
    public void stopNotification(final Service service) {
        this.cancelNotification();
        service.stopForeground(true);
        this.isPlaying = false;
    }
    
    public void stopPostplayNotification(final Service service) {
        if (this.isPostplay) {
            service.stopForeground(true);
            this.isPlaying = false;
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
