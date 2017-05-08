// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

import java.util.List;
import com.netflix.mediaclient.util.UserVisibleErrorCodeGenerator;
import com.netflix.mediaclient.servicemgr.interface_.offline.StopReason;
import com.netflix.mediaclient.android.app.Status;
import android.text.format.Formatter;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.ui.offline.OfflineActivity;
import android.support.v4.app.NotificationCompat$Style;
import android.support.v4.app.NotificationCompat$BigTextStyle;
import android.support.v4.app.NotificationCompat$Action;
import android.app.Notification$Style;
import android.app.Notification$BigTextStyle;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmVideoDetails;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.util.l10n.LocalizationUtils;
import com.netflix.mediaclient.util.l10n.BidiMarker;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmUtils;
import com.netflix.mediaclient.service.NetflixService;
import android.app.PendingIntent;
import android.content.Intent;
import android.app.NotificationManager;
import android.support.v4.content.ContextCompat;
import android.os.Build$VERSION;
import com.netflix.mediaclient.Log;
import android.app.Notification;
import android.graphics.Bitmap;
import android.app.Notification$Builder;
import android.os.Handler;
import android.util.LruCache;
import com.netflix.mediaclient.servicemgr.INetflixService;
import android.support.v4.app.NotificationCompat$Builder;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.offline.SimpleOfflineAgentListener;

abstract class DownloadNotificationManager extends SimpleOfflineAgentListener
{
    private static final String DELETE_DOWNLOAD = "com.netflix.mediaclient.intent.action.offline.delete_download";
    private static final String DOWNLOAD_COMPLETE_NOTIFICATION_DISMISSED = "com.netflix.mediaclient.intent.action.offline.download_complete_notification_dismissed";
    private static final String INTENT_EXTRA_PLAYABLE_ID = "playable_id";
    private static final String INTENT_EXTRA_VIDEO_TYPE = "videoType";
    private static final String LAUNCH_OFFLINE_ACTIVITY = "com.netflix.mediaclient.intent.action.offline.launch_offline_activity";
    private static final String START_DOWNLOAD = "com.netflix.mediaclient.intent.action.offline.start_download";
    private static final String STOP_DOWNLOAD = "com.netflix.mediaclient.intent.action.offline.stop_download";
    private static final String TAG = "nf_downloadNotification";
    private static final String WATCH_PLAYABLE = "com.netflix.mediaclient.intent.action.offline.watch_playable";
    private final int DOWNLOAD_COMPLETE_NOTIFICATION_ID;
    private final int DOWNLOAD_ERROR_NOTIFICATION_ID;
    private final int DOWNLOAD_PROGRESS_NOTIFICATION_ID;
    final Context mContext;
    private final ImageLoader mImageLoader;
    private NotificationCompat$Builder mKitKatCompatBuilder;
    private final int mLargeIconHeight;
    private final int mLargeIconWidth;
    private final INetflixService mNetflixService;
    private final LruCache<String, DownloadNotificationManager$NotificationData> mNotificationData;
    private int mNumDownloadCompletedForNotification;
    private final OfflineAgentInterface mOfflineAgent;
    
    DownloadNotificationManager(final Handler handler, final Context mContext, final INetflixService mNetflixService, final ImageLoader mImageLoader, final boolean b, final OfflineAgentInterface mOfflineAgent) {
        this.DOWNLOAD_PROGRESS_NOTIFICATION_ID = 101;
        this.DOWNLOAD_ERROR_NOTIFICATION_ID = 102;
        this.DOWNLOAD_COMPLETE_NOTIFICATION_ID = 103;
        this.mNumDownloadCompletedForNotification = 0;
        this.mNetflixService = mNetflixService;
        this.mImageLoader = mImageLoader;
        this.mContext = mContext;
        this.mOfflineAgent = mOfflineAgent;
        int n;
        if (b) {
            n = 2;
        }
        else {
            n = 4;
        }
        this.mNotificationData = (LruCache<String, DownloadNotificationManager$NotificationData>)new LruCache(n);
        handler.post((Runnable)new DownloadNotificationManager$1(this));
        this.mLargeIconWidth = this.mContext.getResources().getDimensionPixelSize(17104901);
        this.mLargeIconHeight = this.mContext.getResources().getDimensionPixelSize(17104902);
    }
    
    private Notification buildNotification(final Notification$Builder notification$Builder, final Bitmap largeIcon) {
        Log.i("nf_downloadNotification", "buildNotification");
        notification$Builder.setOnlyAlertOnce(true);
        if (Build$VERSION.SDK_INT >= 21) {
            notification$Builder.setVisibility(1);
            notification$Builder.setColor(ContextCompat.getColor(this.mContext, 2131755186));
        }
        if (largeIcon != null) {
            notification$Builder.setLargeIcon(largeIcon);
        }
        return notification$Builder.build();
    }
    
    private void cancelAndRemoveDownloadCompleteNotification(final NotificationManager notificationManager) {
        Log.d("nf_downloadNotification", "cancelAndRemoveDownloadProgressNotification");
        notificationManager.cancel(103);
        this.mNetflixService.requestBackgroundForNotification(103, true);
    }
    
    private void cancelAndRemoveDownloadProgressNotification(final NotificationManager notificationManager) {
        Log.d("nf_downloadNotification", "cancelAndRemoveDownloadProgressNotification");
        notificationManager.cancel(101);
        this.mNetflixService.requestBackgroundForNotification(101, true);
    }
    
    private void cancelAndRemoveErrorNotification(final NotificationManager notificationManager) {
        Log.d("nf_downloadNotification", "cancelAndRemoveErrorNotification");
        notificationManager.cancel(102);
    }
    
    public static DownloadNotificationManager createDownloadNotificationManager(final Handler handler, final Context context, final INetflixService netflixService, final ImageLoader imageLoader, final boolean b, final OfflineAgentInterface offlineAgentInterface) {
        if (Build$VERSION.SDK_INT >= 24) {
            return new DownloadNotificationManagerNPlus(handler, context, netflixService, imageLoader, b, offlineAgentInterface);
        }
        return new DownloadNotificationManagerPreN(handler, context, netflixService, imageLoader, b, offlineAgentInterface);
    }
    
    private PendingIntent createPendingIntent(final Intent intent, final String s) {
        intent.setClass(this.mContext, (Class)NetflixService.class).addCategory("com.netflix.mediaclient.intent.category.offline");
        if (s != null) {
            intent.putExtra("playable_id", s);
        }
        IntentCommandGroupType.setIntentGroupType(intent, IntentCommandGroupType.DownloadNotification);
        return PendingIntent.getService(this.mContext, 0, intent, 134217728);
    }
    
    private DownloadNotificationManager$NotificationData ensureImageAndVideoType(final String s) {
        DownloadNotificationManager$NotificationData downloadNotificationManager$NotificationData;
        if ((downloadNotificationManager$NotificationData = (DownloadNotificationManager$NotificationData)this.mNotificationData.get((Object)s)) == null) {
            downloadNotificationManager$NotificationData = new DownloadNotificationManager$NotificationData(this);
            this.mNotificationData.put((Object)s, (Object)downloadNotificationManager$NotificationData);
            this.fetchNotificationData(s, downloadNotificationManager$NotificationData);
        }
        return downloadNotificationManager$NotificationData;
    }
    
    private void fetchNotificationData(String string, final DownloadNotificationManager$NotificationData downloadNotificationManager$NotificationData) {
        final RealmVideoDetails offlineVideoDetails = RealmUtils.getOfflineVideoDetails(string);
        if (offlineVideoDetails != null) {
            final Playable playable = offlineVideoDetails.getPlayable();
            if (playable != null) {
                string = "";
                downloadNotificationManager$NotificationData.mVideoType = offlineVideoDetails.getType();
                String s;
                if (downloadNotificationManager$NotificationData.mVideoType == VideoType.EPISODE) {
                    s = new String(playable.getParentTitle());
                    string = this.mContext.getString(2131296642, new Object[] { playable.getSeasonAbbrSeqLabel(), playable.getEpisodeNumber(), offlineVideoDetails.getTitle() });
                }
                else {
                    s = new String(offlineVideoDetails.getTitle());
                }
                downloadNotificationManager$NotificationData.mTitle = LocalizationUtils.prependBidiMarkerIfRtl(s, BidiMarker.FORCED_RTL);
                downloadNotificationManager$NotificationData.mSecondaryTitle = LocalizationUtils.prependBidiMarkerIfRtl(string, BidiMarker.FORCED_RTL);
                if (Log.isLoggable()) {
                    Log.i("nf_downloadNotification", "onVideoSummaryFetched mVideoType=" + downloadNotificationManager$NotificationData.mVideoType);
                }
                this.mImageLoader.getImg(offlineVideoDetails.getBoxshotUrl(), IClientLogging$AssetType.boxArt, 0, 0, (ImageLoader$ImageLoaderListener)new DownloadNotificationManager$2(this, downloadNotificationManager$NotificationData));
            }
        }
    }
    
    private PendingIntent getContentIntent(final String s) {
        return this.createPendingIntent(new Intent("com.netflix.mediaclient.intent.action.offline.launch_offline_activity"), s);
    }
    
    private PendingIntent getDeleteIntent() {
        return this.createPendingIntent(new Intent("com.netflix.mediaclient.intent.action.offline.download_complete_notification_dismissed"), null);
    }
    
    private String getShortPercentageString(final OfflinePlayableViewData offlinePlayableViewData) {
        return StringUtils.getAsPercentString(offlinePlayableViewData.getPercentageDownloaded());
    }
    
    private void handleDownloadStoppedByUser(final OfflinePlayableViewData offlinePlayableViewData) {
        final DownloadNotificationManager$NotificationData ensureImageAndVideoType = this.ensureImageAndVideoType(offlinePlayableViewData.getPlayableId());
        final Notification$Builder smallIconForStoppedByUser = new Notification$Builder(this.mContext);
        smallIconForStoppedByUser.setProgress(100, offlinePlayableViewData.getPercentageDownloaded(), false);
        this.addResumeDownloadAction(smallIconForStoppedByUser, offlinePlayableViewData);
        this.addCancelDownloadAction(smallIconForStoppedByUser, offlinePlayableViewData);
        smallIconForStoppedByUser.setContentText((CharSequence)this.getShortPercentageString(offlinePlayableViewData));
        smallIconForStoppedByUser.setShowWhen(false).setOngoing(false).setAutoCancel(false);
        this.setSmallIconForStoppedByUser(smallIconForStoppedByUser);
        final String bigTextForStoppedByUser = this.getBigTextForStoppedByUser(offlinePlayableViewData, ensureImageAndVideoType);
        final Notification$BigTextStyle setBigContentTitle = new Notification$BigTextStyle().setBigContentTitle(ensureImageAndVideoType.mTitle);
        setBigContentTitle.bigText((CharSequence)bigTextForStoppedByUser);
        smallIconForStoppedByUser.setContentTitle(ensureImageAndVideoType.mTitle).setStyle((Notification$Style)setBigContentTitle);
        smallIconForStoppedByUser.setContentIntent(this.getContentIntent(offlinePlayableViewData.getPlayableId()));
        final Notification buildNotification = this.buildNotification(smallIconForStoppedByUser, ensureImageAndVideoType.mBoxShot);
        final NotificationManager notificationManager = (NotificationManager)this.mContext.getSystemService("notification");
        if (notificationManager != null) {
            this.cancelAndRemoveErrorNotification(notificationManager);
            this.mNetflixService.requestBackgroundForNotification(101, false);
            notificationManager.notify(101, buildNotification);
        }
    }
    
    private void onOfflinePlayableProgressAfterKitKat(final OfflinePlayableViewData offlinePlayableViewData, final int n) {
        Log.i("nf_downloadNotification", "onOfflinePlayableProgressAfterKitKat");
        final DownloadNotificationManager$NotificationData ensureImageAndVideoType = this.ensureImageAndVideoType(offlinePlayableViewData.getPlayableId());
        final Notification$Builder smallIconForInProgress = new Notification$Builder(this.mContext);
        smallIconForInProgress.setProgress(100, n, false);
        this.addStopDownloadAction(smallIconForInProgress, offlinePlayableViewData);
        this.addCancelDownloadAction(smallIconForInProgress, offlinePlayableViewData);
        smallIconForInProgress.setContentText((CharSequence)this.getShortPercentageString(offlinePlayableViewData)).setShowWhen(false).setOngoing(true).setAutoCancel(false);
        this.setSmallIconForInProgress(smallIconForInProgress);
        final String bigTextForInProgress = this.getBigTextForInProgress(offlinePlayableViewData, ensureImageAndVideoType);
        final Notification$BigTextStyle setBigContentTitle = new Notification$BigTextStyle().setBigContentTitle(ensureImageAndVideoType.mTitle);
        setBigContentTitle.bigText((CharSequence)bigTextForInProgress);
        smallIconForInProgress.setContentTitle(ensureImageAndVideoType.mTitle).setStyle((Notification$Style)setBigContentTitle);
        smallIconForInProgress.setContentIntent(this.getContentIntent(offlinePlayableViewData.getPlayableId()));
        final Notification buildNotification = this.buildNotification(smallIconForInProgress, ensureImageAndVideoType.mBoxShot);
        final NotificationManager notificationManager = (NotificationManager)this.mContext.getSystemService("notification");
        if (notificationManager != null) {
            this.cancelAndRemoveErrorNotification(notificationManager);
            this.mNetflixService.requestForegroundForNotification(101, buildNotification);
            notificationManager.notify(101, buildNotification);
        }
    }
    
    private void onOfflinePlayableProgressKitKat(final OfflinePlayableViewData offlinePlayableViewData, final int n) {
        Log.i("nf_downloadNotification", "onOfflinePlayableProgressKitKat");
        final DownloadNotificationManager$NotificationData ensureImageAndVideoType = this.ensureImageAndVideoType(offlinePlayableViewData.getPlayableId());
        if (this.mKitKatCompatBuilder == null) {
            this.mKitKatCompatBuilder = new NotificationCompat$Builder(this.mContext);
        }
        this.mKitKatCompatBuilder.mActions.clear();
        this.mKitKatCompatBuilder.setProgress(100, n, false);
        this.mKitKatCompatBuilder.addAction(new NotificationCompat$Action(2130837941, this.mContext.getString(2131296713), this.getStopDownloadIntent(offlinePlayableViewData.getPlayableId())));
        this.mKitKatCompatBuilder.addAction(new NotificationCompat$Action(2130837740, this.mContext.getString(2131296705), this.getDeletePlayableIntent(offlinePlayableViewData.getPlayableId())));
        this.mKitKatCompatBuilder.setContentText(this.getShortPercentageString(offlinePlayableViewData)).setShowWhen(false).setOngoing(true).setAutoCancel(false);
        this.mKitKatCompatBuilder.setSmallIcon(2130837967);
        final String bigTextForInProgress = this.getBigTextForInProgress(offlinePlayableViewData, ensureImageAndVideoType);
        final NotificationCompat$BigTextStyle setBigContentTitle = new NotificationCompat$BigTextStyle().setBigContentTitle(ensureImageAndVideoType.mTitle);
        setBigContentTitle.bigText(bigTextForInProgress);
        this.mKitKatCompatBuilder.setContentTitle(ensureImageAndVideoType.mTitle).setStyle(setBigContentTitle);
        this.mKitKatCompatBuilder.setContentIntent(this.getContentIntent(offlinePlayableViewData.getPlayableId()));
        this.mKitKatCompatBuilder.setOnlyAlertOnce(true);
        if (ensureImageAndVideoType.mBoxShot != null) {
            this.mKitKatCompatBuilder.setLargeIcon(ensureImageAndVideoType.mBoxShot);
        }
        final Notification build = this.mKitKatCompatBuilder.build();
        final NotificationManager notificationManager = (NotificationManager)this.mContext.getSystemService("notification");
        if (notificationManager != null) {
            this.cancelAndRemoveErrorNotification(notificationManager);
            this.mNetflixService.requestForegroundForNotification(101, build);
            notificationManager.notify(101, build);
        }
    }
    
    private void removeAllNotifications() {
        Log.i("nf_downloadNotification", "removeAllNotifications");
        final NotificationManager notificationManager = (NotificationManager)this.mContext.getSystemService("notification");
        if (notificationManager != null) {
            this.cancelAndRemoveDownloadProgressNotification(notificationManager);
            this.cancelAndRemoveErrorNotification(notificationManager);
            this.cancelAndRemoveDownloadCompleteNotification(notificationManager);
        }
    }
    
    private void removeSystemNotificationBar() {
        this.mContext.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
    }
    
    private void showDownloadCompleteNotification(final OfflinePlayableViewData offlinePlayableViewData) {
        final DownloadNotificationManager$NotificationData ensureImageAndVideoType = this.ensureImageAndVideoType(offlinePlayableViewData.getPlayableId());
        ++this.mNumDownloadCompletedForNotification;
        if (Log.isLoggable()) {
            Log.i("nf_downloadNotification", "showDownloadCompleteNotification mNumDownloadCompletedForNotification=" + this.mNumDownloadCompletedForNotification);
        }
        final Notification$Builder notification$Builder = new Notification$Builder(this.mContext);
        notification$Builder.setDeleteIntent(this.getDeleteIntent());
        notification$Builder.setShowWhen(true).setOngoing(false).setSmallIcon(this.getDownloadCompleteSmallIcon()).setAutoCancel(true);
        final String string = this.mContext.getString(2131296706);
        notification$Builder.setContentTitle((CharSequence)string).setTicker((CharSequence)string);
        Notification notification;
        if (this.mNumDownloadCompletedForNotification <= 1) {
            notification$Builder.setContentText(ensureImageAndVideoType.mTitle);
            CharSequence charSequence;
            if (StringUtils.isNotEmpty(ensureImageAndVideoType.mSecondaryTitle)) {
                charSequence = (Object)ensureImageAndVideoType.mTitle + "\n" + (Object)ensureImageAndVideoType.mSecondaryTitle;
            }
            else {
                charSequence = ensureImageAndVideoType.mTitle;
            }
            final Notification$BigTextStyle setBigContentTitle = new Notification$BigTextStyle().setBigContentTitle((CharSequence)string);
            setBigContentTitle.bigText(charSequence);
            notification$Builder.setStyle((Notification$Style)setBigContentTitle);
            this.addWatchAction(notification$Builder, offlinePlayableViewData, ensureImageAndVideoType.mVideoType);
            notification$Builder.setContentIntent(this.getContentIntent(offlinePlayableViewData.getPlayableId()));
            notification = this.buildNotification(notification$Builder, ensureImageAndVideoType.mBoxShot);
        }
        else {
            final int n = this.mNumDownloadCompletedForNotification - 1;
            final String quantityString = this.mContext.getResources().getQuantityString(2131361795, n, new Object[] { n, ensureImageAndVideoType.mTitle });
            notification$Builder.setContentText((CharSequence)quantityString);
            notification$Builder.setStyle((Notification$Style)new Notification$BigTextStyle().bigText((CharSequence)quantityString));
            notification$Builder.setContentIntent(this.getContentIntent(null));
            notification = this.buildNotification(notification$Builder, ensureImageAndVideoType.mBoxShot);
        }
        final NotificationManager notificationManager = (NotificationManager)this.mContext.getSystemService("notification");
        if (notificationManager != null) {
            this.cancelAndRemoveErrorNotification(notificationManager);
            this.mNetflixService.requestBackgroundForNotification(103, false);
            notificationManager.notify(103, notification);
            this.cancelAndRemoveDownloadProgressNotification(notificationManager);
        }
    }
    
    private void showNotificationForErrors(final OfflinePlayableViewData offlinePlayableViewData, final String s) {
        final DownloadNotificationManager$NotificationData ensureImageAndVideoType = this.ensureImageAndVideoType(offlinePlayableViewData.getPlayableId());
        final Notification$Builder notification$Builder = new Notification$Builder(this.mContext);
        notification$Builder.setContentText((CharSequence)s).setShowWhen(true).setOngoing(false).setSmallIcon(this.getErrorNotificationSmallIcon()).setAutoCancel(true);
        final Notification$BigTextStyle setBigContentTitle = new Notification$BigTextStyle().setBigContentTitle(ensureImageAndVideoType.mTitle);
        setBigContentTitle.bigText((CharSequence)s);
        notification$Builder.setContentTitle(ensureImageAndVideoType.mTitle).setStyle((Notification$Style)setBigContentTitle).setContentText((CharSequence)s);
        notification$Builder.setContentIntent(this.getContentIntent(offlinePlayableViewData.getPlayableId()));
        final Notification buildNotification = this.buildNotification(notification$Builder, ensureImageAndVideoType.mBoxShot);
        buildNotification.priority = 2;
        final NotificationManager notificationManager = (NotificationManager)this.mContext.getSystemService("notification");
        if (notificationManager != null) {
            this.cancelAndRemoveDownloadProgressNotification(notificationManager);
            notificationManager.notify(102, buildNotification);
        }
    }
    
    private void startOfflineActivity(final String s) {
        if (Log.isLoggable()) {
            Log.d("nf_downloadNotification", "startOfflineActivity playableId=" + s);
        }
        Intent intent;
        if (s != null) {
            intent = OfflineActivity.showAllDownloadsForPlayable(this.mContext, s, true);
        }
        else {
            intent = OfflineActivity.showAllDownloads(this.mContext, true);
        }
        this.mContext.startActivity(intent);
    }
    
    private void startPlayerActivity(final String s, final VideoType videoType) {
        if (Log.isLoggable()) {
            Log.d("nf_downloadNotification", "startPlayerActivity playableId=" + s);
        }
        final Intent coldStartIntent = PlayerActivity.createColdStartIntent(this.mContext, s, videoType, PlayContext.OFFLINE_NOTIFICATION_CONTEXT);
        coldStartIntent.addFlags(268435456);
        this.mContext.startActivity(coldStartIntent);
    }
    
    protected abstract void addCancelDownloadAction(final Notification$Builder p0, final OfflinePlayableViewData p1);
    
    protected abstract void addResumeDownloadAction(final Notification$Builder p0, final OfflinePlayableViewData p1);
    
    protected abstract void addStopDownloadAction(final Notification$Builder p0, final OfflinePlayableViewData p1);
    
    protected abstract void addWatchAction(final Notification$Builder p0, final OfflinePlayableViewData p1, final VideoType p2);
    
    public void cancelNotificationOnAccountInActive() {
        ThreadUtils.assertOnMain();
        this.removeAllNotifications();
    }
    
    protected abstract String getBigTextForInProgress(final OfflinePlayableViewData p0, final DownloadNotificationManager$NotificationData p1);
    
    protected abstract String getBigTextForStoppedByUser(final OfflinePlayableViewData p0, final DownloadNotificationManager$NotificationData p1);
    
    PendingIntent getDeletePlayableIntent(final String s) {
        return this.createPendingIntent(new Intent("com.netflix.mediaclient.intent.action.offline.delete_download"), s);
    }
    
    protected abstract int getDownloadCompleteSmallIcon();
    
    protected abstract int getErrorNotificationSmallIcon();
    
    protected String getSecondaryTitlePercentageSizeString(final OfflinePlayableViewData offlinePlayableViewData, final DownloadNotificationManager$NotificationData downloadNotificationManager$NotificationData) {
        final long currentEstimatedSpace = offlinePlayableViewData.getCurrentEstimatedSpace();
        final long totalEstimatedSpace = offlinePlayableViewData.getTotalEstimatedSpace();
        final StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotEmpty(downloadNotificationManager$NotificationData.mSecondaryTitle)) {
            sb.append(downloadNotificationManager$NotificationData.mSecondaryTitle).append("\n");
        }
        sb.append(this.getShortPercentageString(offlinePlayableViewData)).append(" (").append(Formatter.formatShortFileSize(this.mContext, currentEstimatedSpace)).append("/").append(Formatter.formatShortFileSize(this.mContext, totalEstimatedSpace)).append(")");
        return sb.toString();
    }
    
    PendingIntent getStartDownloadIntent(final String s) {
        return this.createPendingIntent(new Intent("com.netflix.mediaclient.intent.action.offline.start_download"), s);
    }
    
    PendingIntent getStopDownloadIntent(final String s) {
        return this.createPendingIntent(new Intent("com.netflix.mediaclient.intent.action.offline.stop_download"), s);
    }
    
    PendingIntent getWatchPlayableIntent(final String s, final VideoType videoType) {
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.offline.watch_playable");
        intent.putExtra("videoType", videoType.getValue());
        return this.createPendingIntent(intent, s);
    }
    
    public void handleDownloadNotificationIntent(final Intent intent) {
        Log.i("nf_downloadNotification", "intent=" + intent);
        final String stringExtra = intent.getStringExtra("playable_id");
        final String stringExtra2 = intent.getStringExtra("videoType");
        if (intent.getAction().equals("com.netflix.mediaclient.intent.action.offline.stop_download")) {
            this.mOfflineAgent.pauseDownload(stringExtra);
        }
        else if (intent.getAction().equals("com.netflix.mediaclient.intent.action.offline.start_download")) {
            this.mOfflineAgent.resumeDownload(stringExtra);
        }
        else if (intent.getAction().equals("com.netflix.mediaclient.intent.action.offline.watch_playable")) {
            this.removeAllNotifications();
            this.removeSystemNotificationBar();
            this.startPlayerActivity(stringExtra, VideoType.create(stringExtra2));
        }
        else if (intent.getAction().equals("com.netflix.mediaclient.intent.action.offline.delete_download")) {
            this.removeAllNotifications();
            this.mOfflineAgent.deleteOfflinePlayable(stringExtra);
        }
        else if (intent.getAction().equals("com.netflix.mediaclient.intent.action.offline.download_complete_notification_dismissed")) {
            this.mNumDownloadCompletedForNotification = 0;
        }
        if (intent.getAction().equals("com.netflix.mediaclient.intent.action.offline.launch_offline_activity")) {
            this.mNumDownloadCompletedForNotification = 0;
            this.startOfflineActivity(stringExtra);
        }
    }
    
    @Override
    public boolean isListenerDestroyed() {
        return false;
    }
    
    @Override
    public void onAllPlayablesDeleted(final Status status) {
        Log.i("nf_downloadNotification", "onAllPlayablesDeleted status=" + status);
        this.removeAllNotifications();
    }
    
    @Override
    public void onCreateRequestResponse(final String s, final Status status) {
        if (status.isSucces()) {
            this.ensureImageAndVideoType(s);
        }
    }
    
    @Override
    public void onDownloadCompleted(final OfflinePlayableViewData offlinePlayableViewData) {
        final DownloadNotificationManager$NotificationData ensureImageAndVideoType = this.ensureImageAndVideoType(offlinePlayableViewData.getPlayableId());
        if (Log.isLoggable()) {
            Log.i("nf_downloadNotification", "onDownloadCompleted before increment mNumDownloadCompletedForNotification=" + this.mNumDownloadCompletedForNotification);
        }
        if (ensureImageAndVideoType.mVideoType != null) {
            this.showDownloadCompleteNotification(offlinePlayableViewData);
            return;
        }
        Log.e("nf_downloadNotification", "mVideoType is not available.");
        this.removeAllNotifications();
    }
    
    @Override
    public void onDownloadResumedByUser(final OfflinePlayableViewData offlinePlayableViewData) {
    }
    
    @Override
    public void onDownloadStopped(final OfflinePlayableViewData offlinePlayableViewData, final StopReason stopReason) {
        if (Log.isLoggable()) {
            Log.i("nf_downloadNotification", "onDownloadStopped playableId=" + offlinePlayableViewData.getPlayableId() + " stopReason=" + stopReason);
        }
        if (stopReason == StopReason.StoppedFromAgentAPI) {
            this.handleDownloadStoppedByUser(offlinePlayableViewData);
            return;
        }
        String s;
        if (stopReason == StopReason.NoNetworkConnectivity || stopReason == StopReason.NotAllowedOnCurrentNetwork) {
            if (this.mOfflineAgent.getRequiresUnmeteredNetwork()) {
                s = this.mContext.getString(2131296711);
            }
            else {
                s = this.mContext.getString(2131296710);
            }
        }
        else if (stopReason == StopReason.NotEnoughSpace) {
            s = this.mContext.getString(2131296708);
        }
        else {
            if (stopReason != StopReason.EncodesAreNotAvailableAnyMore && stopReason != StopReason.GeoCheckError) {
                this.removeAllNotifications();
                return;
            }
            s = LocalizationUtils.prependBidiMarkerIfRtl(this.mContext.getString(2131296707), BidiMarker.FORCED_RTL) + LocalizationUtils.prependBidiMarkerIfRtl(UserVisibleErrorCodeGenerator.addParenthesisWithPrefixSpace(UserVisibleErrorCodeGenerator.getOfflineErrorCodeForStoppedDownload(stopReason)), BidiMarker.FORCED_RTL);
        }
        this.showNotificationForErrors(offlinePlayableViewData, s);
    }
    
    @Override
    public void onError(final Status status) {
        if (Log.isLoggable()) {
            Log.i("nf_downloadNotification", "onError status=" + status);
        }
        this.removeAllNotifications();
    }
    
    @Override
    public void onLicenseRefreshDone(final OfflinePlayableViewData offlinePlayableViewData, final Status status) {
    }
    
    @Override
    public void onOfflinePlayableDeleted(final String s, final Status status) {
        if (Log.isLoggable()) {
            Log.i("nf_downloadNotification", "onOfflinePlayableDeleted playableId=" + s);
        }
        this.mNumDownloadCompletedForNotification = 0;
        this.removeAllNotifications();
    }
    
    @Override
    public void onOfflinePlayableProgress(final OfflinePlayableViewData offlinePlayableViewData, final int n) {
        Log.i("nf_downloadNotification", "onOfflinePlayableProgress");
        if (Build$VERSION.SDK_INT == 19) {
            this.onOfflinePlayableProgressKitKat(offlinePlayableViewData, n);
            return;
        }
        this.onOfflinePlayableProgressAfterKitKat(offlinePlayableViewData, n);
    }
    
    @Override
    public void onOfflinePlayablesDeleted(final List<String> list, final Status status) {
        if (Log.isLoggable()) {
            Log.i("nf_downloadNotification", "onOfflinePlayableDeleted");
        }
        this.mNumDownloadCompletedForNotification = 0;
        this.removeAllNotifications();
    }
    
    @Override
    public void onOfflineStorageVolumeAddedOrRemoved(final boolean b) {
        this.removeAllNotifications();
    }
    
    @Override
    public void onPlayWindowRenewDone(final OfflinePlayableViewData offlinePlayableViewData, final Status status) {
    }
    
    protected abstract void setSmallIconForInProgress(final Notification$Builder p0);
    
    protected abstract void setSmallIconForStoppedByUser(final Notification$Builder p0);
}
