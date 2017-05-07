// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.social.notifications.types;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import android.text.format.DateUtils;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.NotificationUtils;
import com.netflix.mediaclient.ui.social.notifications.SocialNotificationsActivity;
import android.widget.Button;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.ui.social.notifications.NotificationViewHolder;
import com.netflix.model.leafs.social.SocialNotificationSummary$NotificationTypes;
import android.view.View;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat$Style;
import android.support.v4.app.NotificationCompat$Builder;
import android.support.v4.app.NotificationCompat$BigPictureStyle;
import com.netflix.mediaclient.Log;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.model.leafs.social.SocialNotificationsListSummary;
import android.content.Context;
import android.graphics.Bitmap;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;

class SocialNotification$1onBoxArtFetched implements ImageLoader$ImageLoaderListener
{
    private final Bitmap mSocialProfile;
    final /* synthetic */ SocialNotification this$0;
    final /* synthetic */ Context val$context;
    final /* synthetic */ SocialNotificationsListSummary val$listSummary;
    final /* synthetic */ MessageData val$msg;
    final /* synthetic */ SocialNotificationSummary val$notificationSummary;
    
    public SocialNotification$1onBoxArtFetched(final SocialNotification this$0, final Bitmap mSocialProfile, final Context val$context, final SocialNotificationSummary val$notificationSummary, final SocialNotificationsListSummary val$listSummary, final Bitmap val$msg) {
        this.this$0 = this$0;
        this.val$context = val$context;
        this.val$notificationSummary = val$notificationSummary;
        this.val$listSummary = val$listSummary;
        this.val$msg = (MessageData)val$msg;
        this.mSocialProfile = mSocialProfile;
    }
    
    @Override
    public void onErrorResponse(final String s) {
        if (Log.isLoggable()) {
            Log.e(SocialNotification.TAG, "Failed to download: " + s);
        }
    }
    
    @Override
    public void onResponse(final Bitmap bitmap, final String s) {
        if (bitmap != null) {
            final int color = this.val$context.getResources().getColor(2131558527);
            final NotificationCompat$BigPictureStyle bigPicture = new NotificationCompat$BigPictureStyle().bigPicture(bitmap);
            final NotificationCompat$Builder setColor = new NotificationCompat$Builder(this.val$context).setSmallIcon(2130837771).setLargeIcon(this.mSocialProfile).setContentTitle(this.val$notificationSummary.getFriendProfile().getFullName()).setStyle(bigPicture).setColor(color);
            setColor.setGroup("social_notifications");
            setColor.setGroupSummary(true);
            setColor.setAutoCancel(true);
            this.this$0.addNotificationText(setColor, bigPicture, this.val$notificationSummary, this.val$context);
            this.this$0.addNotificationActions(setColor, this.val$notificationSummary, this.val$listSummary, this.val$msg, this.val$context);
            final Intent intent = new Intent("com.netflix.mediaclient.intent.action.NOTIFICATION_CANCELED");
            MessageData.addMessageDataToIntent(intent, this.val$msg);
            intent.putExtra("swiped_social_notification_id", this.val$notificationSummary.getId());
            setColor.setDeleteIntent(PendingIntent.getBroadcast(this.val$context.getApplicationContext(), 0, intent, 268435456));
            final NotificationManager notificationManager = (NotificationManager)this.val$context.getSystemService("notification");
            notificationManager.cancel(1000);
            notificationManager.notify(1000, setColor.build());
            SocialNotification.mLastSocialNotificationIdSentToStatusBar = this.val$notificationSummary.getId();
        }
    }
}
