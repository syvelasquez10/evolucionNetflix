// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.Asset;
import android.app.Notification;
import android.text.TextUtils;
import android.app.Notification$Builder;
import android.support.v4.app.NotificationCompat$Builder;
import com.netflix.mediaclient.util.AndroidUtils;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.widget.RemoteViews;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Context;
import android.content.Intent;
import android.app.PendingIntent;
import android.graphics.Bitmap;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.app.NotificationManager;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.annotation.SuppressLint;

@SuppressLint({ "InlinedApi" })
public final class PlayerSuspendNotification
{
    private static final int NOTIFICATION_ID = 2;
    private static final String PLAYER_SUSPEND_NOTIFICATION_ACTION_DELETE = "com.netflix.mediaclient.intent.action.PLAYER_DELETE";
    private static final String TAG;
    private final NetflixActivity mActivity;
    private final NotificationManager mNotificationManager;
    private String mSecondaryTitle;
    private final ServiceManager mServiceManager;
    private AtomicBoolean mShowNotification;
    private String mTitle;
    
    static {
        TAG = PlayerSuspendNotification.class.getSimpleName();
    }
    
    PlayerSuspendNotification(final NetflixActivity mActivity, final ServiceManager mServiceManager) {
        this.mShowNotification = new AtomicBoolean(false);
        this.mActivity = mActivity;
        this.mServiceManager = mServiceManager;
        this.mNotificationManager = (NotificationManager)mActivity.getSystemService("notification");
    }
    
    private PendingIntent createNotificationPendingIntentDelete() {
        return PendingIntent.getBroadcast((Context)this.mActivity, 0, new Intent("com.netflix.mediaclient.intent.action.PLAYER_DELETE"), 134217728);
    }
    
    private PendingIntent createNotificationPendingIntentResume() {
        return PendingIntent.getActivity((Context)this.mActivity, 0, this.mActivity.getIntent(), 268435456);
    }
    
    private void fetchImageWithLoader(final String s) {
        if (StringUtils.isEmpty(s)) {
            Log.e(PlayerSuspendNotification.TAG, "Loader url empty");
            return;
        }
        final ImageLoader imageLoader = this.mServiceManager.getImageLoader();
        if (imageLoader != null) {
            imageLoader.getImg(s, IClientLogging$AssetType.boxArt, 0, 0, new PlayerSuspendNotification$2(this));
            return;
        }
        Log.e(PlayerSuspendNotification.TAG, "ImageLoader is null!");
        this.show(null);
    }
    
    private RemoteViews getContentView(final String s, final String s2, final Bitmap bitmap, final boolean b) {
        final String packageName = this.mActivity.getPackageName();
        final boolean notEmpty = StringUtils.isNotEmpty(s2);
        RemoteViews remoteViews;
        if (b) {
            if (notEmpty) {
                remoteViews = new RemoteViews(packageName, 2130903275);
            }
            else {
                remoteViews = new RemoteViews(packageName, 2130903277);
            }
        }
        else if (notEmpty) {
            remoteViews = new RemoteViews(packageName, 2130903274);
        }
        else {
            remoteViews = new RemoteViews(packageName, 2130903276);
        }
        if (bitmap != null) {
            remoteViews.setImageViewBitmap(2131690281, bitmap);
        }
        if (StringUtils.isNotEmpty(s)) {
            remoteViews.setTextViewText(2131690282, (CharSequence)s);
        }
        else {
            remoteViews.setTextViewText(2131690282, (CharSequence)"");
        }
        if (notEmpty) {
            remoteViews.setTextViewText(2131690283, (CharSequence)s2);
        }
        return remoteViews;
    }
    
    private Bitmap getDefaultBoxArt() {
        return BitmapFactory.decodeResource(this.mActivity.getResources(), 2130837785);
    }
    
    public static IntentFilter getNotificationIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.netflix.mediaclient.intent.action.PLAYER_DELETE");
        return intentFilter;
    }
    
    public static boolean isDelete(final String s) {
        return "com.netflix.mediaclient.intent.action.PLAYER_DELETE".equalsIgnoreCase(s);
    }
    
    private void show(final Bitmap largeIcon) {
        if (!this.mShowNotification.get()) {
            return;
        }
        Notification notification2;
        if (AndroidUtils.getAndroidVersion() < 21) {
            Bitmap defaultBoxArt;
            if ((defaultBoxArt = largeIcon) == null) {
                defaultBoxArt = this.getDefaultBoxArt();
            }
            final Notification notification = notification2 = new NotificationCompat$Builder((Context)this.mActivity).setOngoing(0 != 0).setOnlyAlertOnce(1 != 0).setSmallIcon(2130837758).setTicker(this.mTitle).setContentIntent(this.createNotificationPendingIntentResume()).setDeleteIntent(this.createNotificationPendingIntentDelete()).setContent(this.getContentView(this.mTitle, this.mSecondaryTitle, defaultBoxArt, (boolean)(0 != 0))).setWhen(System.currentTimeMillis()).build();
            if (AndroidUtils.getAndroidVersion() >= 16) {
                notification.bigContentView = this.getContentView(this.mTitle, this.mSecondaryTitle, defaultBoxArt, true);
                notification2 = notification;
            }
        }
        else {
            final int color = this.mActivity.getResources().getColor(2131624081);
            final String string = this.mActivity.getResources().getString(2131231207);
            final Notification$Builder setVisibility = new Notification$Builder((Context)this.mActivity).setOngoing(false).setOnlyAlertOnce(true).setSmallIcon(2130837758).setTicker((CharSequence)this.mTitle).setContentTitle((CharSequence)this.mTitle).setColor(color).setContentIntent(this.createNotificationPendingIntentResume()).setDeleteIntent(this.createNotificationPendingIntentDelete()).setWhen(System.currentTimeMillis()).setVisibility(-1);
            if (largeIcon != null) {
                setVisibility.setLargeIcon(largeIcon);
            }
            if (TextUtils.isEmpty((CharSequence)this.mSecondaryTitle)) {
                setVisibility.setContentText((CharSequence)string);
            }
            else {
                setVisibility.setContentText((CharSequence)this.mSecondaryTitle);
                setVisibility.setSubText((CharSequence)string);
            }
            notification2 = setVisibility.build();
        }
        this.mNotificationManager.notify(2, notification2);
    }
    
    void cancelNotification() {
        this.mShowNotification.set(false);
        this.mNotificationManager.cancel(2);
    }
    
    void showNotification(final Asset asset) {
        if (asset == null) {
            return;
        }
        this.mShowNotification.set(true);
        final PlayerSuspendNotification$1 playerSuspendNotification$1 = new PlayerSuspendNotification$1(this);
        if (asset.isEpisode()) {
            this.mSecondaryTitle = this.mActivity.getApplicationContext().getString(2131231061, new Object[] { asset.getSeasonAbbrSeqLabel(), asset.getEpisodeNumber(), asset.getTitle() });
            this.mTitle = asset.getParentTitle();
            this.mServiceManager.getBrowse().fetchEpisodeDetails(String.valueOf(asset.getPlayableId()), null, playerSuspendNotification$1);
            return;
        }
        this.mServiceManager.getBrowse().fetchMovieDetails(String.valueOf(asset.getPlayableId()), null, playerSuspendNotification$1);
        this.mSecondaryTitle = null;
        this.mTitle = asset.getTitle();
    }
}
