// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.notification;

import android.graphics.Bitmap;
import android.util.Pair;
import android.annotation.TargetApi;
import com.netflix.mediaclient.service.NetflixService;
import android.app.PendingIntent;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.Log;
import android.app.NotificationManager;
import com.netflix.mediaclient.service.mdx.MdxAgent;
import android.app.Notification;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

public final class MdxNotificationManager
{
    private static final String TAG = "nf_mdxnotification";
    NotificationCompat.Builder mBuilder;
    Context mContext;
    private boolean mInTransition;
    private boolean mIsEpisode;
    private boolean mIsLegacy;
    Notification mLegacyNotification;
    MdxAgent mMdxAgent;
    private final int mNotificationId;
    NotificationManager mNotificationManager;
    private boolean mPaused;
    MdxRemoteViewManager mRemoteViews;
    private boolean mSupportBigContent;
    
    public MdxNotificationManager(final Context mContext, final boolean mIsEpisode, final MdxAgent mMdxAgent) {
        this.mNotificationId = 1;
        Log.d("nf_mdxnotification", "is episode " + mIsEpisode);
        this.mContext = mContext;
        this.mIsEpisode = mIsEpisode;
        this.mMdxAgent = mMdxAgent;
        this.mNotificationManager = (NotificationManager)this.mContext.getSystemService("notification");
        this.mSupportBigContent = false;
        this.mIsLegacy = false;
        final int androidVersion = AndroidUtils.getAndroidVersion();
        if (androidVersion >= 16) {
            this.mSupportBigContent = true;
        }
        else if (androidVersion < 11) {
            this.mIsLegacy = true;
        }
        this.mRemoteViews = new MdxRemoteViewManager(this.mContext.getPackageName(), this.mIsEpisode, this.mIsLegacy, mMdxAgent);
        if (this.mIsLegacy) {
            this.mLegacyNotification = new Notification(2130837730, (CharSequence)"", System.currentTimeMillis());
            this.mLegacyNotification.contentView = this.mRemoteViews.getRemoteView();
            final Notification mLegacyNotification = this.mLegacyNotification;
            mLegacyNotification.flags |= 0x2;
            final Notification mLegacyNotification2 = this.mLegacyNotification;
            mLegacyNotification2.flags |= 0x8;
            this.mLegacyNotification.contentIntent = this.createNotificationPendingIntent();
            return;
        }
        this.mBuilder = new NotificationCompat.Builder(this.mContext).setOngoing(true).setOnlyAlertOnce(true).setSmallIcon(2130837730).setWhen(System.currentTimeMillis());
    }
    
    private PendingIntent createNotificationPendingIntent() {
        return PendingIntent.getBroadcast(this.mContext, 0, NetflixService.createShowMdxPlayerBroadcastIntent(), 134217728);
    }
    
    @TargetApi(16)
    private void updateNotification() {
        if (this.mIsLegacy) {
            this.mLegacyNotification.contentView = this.mRemoteViews.getRemoteView();
            this.mNotificationManager.notify(1, this.mLegacyNotification);
            return;
        }
        this.mBuilder.setContent(this.mRemoteViews.getRemoteView());
        final Notification build = this.mBuilder.build();
        if (this.mSupportBigContent) {
            build.bigContentView = this.mRemoteViews.getRemoteViewBigContetnt();
        }
        this.mNotificationManager.notify(1, build);
    }
    
    public void cancelNotification() {
        this.mNotificationManager.cancel(1);
    }
    
    public Pair<Integer, Notification> getNotification() {
        if (this.mIsLegacy) {
            return (Pair<Integer, Notification>)Pair.create((Object)1, (Object)this.mLegacyNotification);
        }
        return (Pair<Integer, Notification>)Pair.create((Object)1, (Object)this.mBuilder.build());
    }
    
    public void setBoxart(final Bitmap boxart) {
        this.mRemoteViews.setBoxart(boxart);
    }
    
    public void setBoxartNotify(final Bitmap boxart) {
        this.mRemoteViews.setBoxart(boxart);
        this.updateNotification();
    }
    
    public void setPauseStateNotify(final boolean mPaused, final boolean mInTransition) {
        if (this.mPaused != mPaused || this.mInTransition != mInTransition) {
            this.mPaused = mPaused;
            this.mInTransition = mInTransition;
            this.mRemoteViews.setPauseState(mPaused, mInTransition);
            this.updateNotification();
        }
    }
    
    public void setTitlesNotify(final boolean mIsEpisode, final String s, final String s2) {
        if (Log.isLoggable("nf_mdxnotification", 3)) {
            Log.d("nf_mdxnotification", "is episode " + mIsEpisode + ",>" + s + ",>" + s2);
        }
        this.mIsEpisode = mIsEpisode;
        if (this.mIsLegacy) {
            this.mLegacyNotification.contentIntent = this.createNotificationPendingIntent();
            if (this.mIsEpisode) {
                this.mLegacyNotification.tickerText = s2;
            }
            else {
                this.mLegacyNotification.tickerText = s;
            }
        }
        else {
            this.mBuilder.setContentIntent(this.createNotificationPendingIntent());
            if (this.mIsEpisode) {
                this.mBuilder.setTicker(s2);
            }
            else {
                this.mBuilder.setTicker(s);
            }
        }
        this.mRemoteViews.setTitles(this.mIsEpisode, s, s2);
        this.updateNotification();
    }
    
    public interface MdxNotificationIntentRetriever
    {
        PendingIntent getNoActionIntent();
        
        PendingIntent getPauseIntent();
        
        PendingIntent getResumeIntent();
        
        PendingIntent getSkipbackIntent(final int p0);
        
        PendingIntent getStopIntent();
    }
}
