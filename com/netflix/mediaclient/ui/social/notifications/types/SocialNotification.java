// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.social.notifications.types;

import android.app.NotificationManager;
import android.content.Intent;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.graphics.Bitmap;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import android.text.format.DateUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.app.PendingIntent;
import com.netflix.mediaclient.ui.social.notifications.SocialNotificationsActivity;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialNotificationsListSummary;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialNotificationSummary;
import android.support.v4.app.NotificationCompat;
import android.widget.Button;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.ui.social.notifications.SocialNotificationViewHolder;
import android.view.View;

public abstract class SocialNotification
{
    private static final String SOCIAL_NOTIFICATIONS_GROUP = "social_notifications";
    public static final String TAG;
    private static String mLastSocialNotificationIdSentToStatusBar;
    
    static {
        TAG = SocialNotification.class.getSimpleName();
    }
    
    public static final int getLayoutResourceId() {
        return 2130903186;
    }
    
    public static final SocialNotificationViewHolder getViewHolder(final View view) {
        return new SocialNotificationViewHolder((AdvancedImageView)view.findViewById(2131165625), (AdvancedImageView)view.findViewById(2131165661), (TextView)view.findViewById(2131165658), (TextView)view.findViewById(2131165659), (TextView)view.findViewById(2131165660), (TextView)view.findViewById(2131165663), (Button)view.findViewById(2131165664), (Button)view.findViewById(2131165665), view.findViewById(2131165662));
    }
    
    public static void showSingleLineText(final SocialNotificationViewHolder socialNotificationViewHolder, final int text) {
        socialNotificationViewHolder.getFriendImage().setVisibility(8);
        socialNotificationViewHolder.getMovieArtImage().setVisibility(8);
        socialNotificationViewHolder.getBottomTextView().setVisibility(8);
        socialNotificationViewHolder.getTopTextView().setVisibility(8);
        socialNotificationViewHolder.getTimeStampView().setVisibility(8);
        socialNotificationViewHolder.getLeftButton().setVisibility(8);
        socialNotificationViewHolder.getRightButton().setVisibility(8);
        socialNotificationViewHolder.getPlayButton().setVisibility(8);
        socialNotificationViewHolder.getMiddleTextView().setText(text);
        socialNotificationViewHolder.getMiddleTextView().setSingleLine(false);
        socialNotificationViewHolder.getMiddleTextView().setGravity(17);
    }
    
    protected void addNotificationActions(final NotificationCompat.Builder builder, final SocialNotificationSummary socialNotificationSummary, final SocialNotificationsListSummary socialNotificationsListSummary, final MessageData messageData, final Context context) {
        if (Log.isLoggable(SocialNotification.TAG, 3)) {
            Log.d(SocialNotification.TAG, "SocialNotification::addNotificationActions " + messageData);
        }
        builder.setContentIntent(PendingIntent.getBroadcast(context.getApplicationContext(), 3, SocialNotificationsActivity.getIntent(messageData), 134217728));
    }
    
    protected abstract void addNotificationText(final NotificationCompat.Builder p0, final NotificationCompat.BigPictureStyle p1, final SocialNotificationSummary p2, final Context p3);
    
    public TextView getAddToMyListButton(final SocialNotificationViewHolder socialNotificationViewHolder) {
        return null;
    }
    
    public abstract SocialNotificationSummary.NotificationTypes getNotificationType();
    
    public View getPlayMovieButton(final SocialNotificationViewHolder socialNotificationViewHolder) {
        return (View)socialNotificationViewHolder.getMovieArtImage();
    }
    
    public View getSayThanksButton(final SocialNotificationViewHolder socialNotificationViewHolder) {
        return null;
    }
    
    public void initView(final View view, final SocialNotificationViewHolder socialNotificationViewHolder, final SocialNotificationSummary socialNotificationSummary, final Context context) {
        int backgroundResource;
        if (socialNotificationSummary.getWasRead()) {
            backgroundResource = 2130837845;
        }
        else {
            backgroundResource = 2131296439;
        }
        view.setBackgroundResource(backgroundResource);
        socialNotificationViewHolder.getFriendImage().setVisibility(0);
        NetflixActivity.getImageLoader(context).showImg(socialNotificationViewHolder.getFriendImage(), socialNotificationSummary.getFriendProfile().getImageUrl(), IClientLogging.AssetType.profileAvatar, socialNotificationSummary.getFriendProfile().getFullName(), true, true);
        socialNotificationViewHolder.getMovieArtImage().setVisibility(0);
        NetflixActivity.getImageLoader(context).showImg(socialNotificationViewHolder.getMovieArtImage(), socialNotificationSummary.getVideoSummary().getBoxshotURL(), IClientLogging.AssetType.boxArt, socialNotificationSummary.getVideoSummary().getTitle(), true, true);
        socialNotificationViewHolder.getTopTextView().setVisibility(0);
        socialNotificationViewHolder.getTopTextView().setText((CharSequence)socialNotificationSummary.getFriendProfile().getFullName());
        socialNotificationViewHolder.getMiddleTextView().setGravity(3);
        if (socialNotificationSummary.getMessageString() == null || socialNotificationSummary.getMessageString().length() == 0) {
            socialNotificationViewHolder.getBottomTextView().setVisibility(8);
        }
        else {
            socialNotificationViewHolder.getBottomTextView().setVisibility(0);
            socialNotificationViewHolder.getBottomTextView().setText((CharSequence)String.format("\"%s\"", socialNotificationSummary.getMessageString()));
        }
        socialNotificationViewHolder.getTimeStampView().setVisibility(0);
        socialNotificationViewHolder.getTimeStampView().setText(DateUtils.getRelativeTimeSpanString(context, socialNotificationSummary.getTimestamp()));
        socialNotificationViewHolder.getPlayButton().setVisibility(0);
        socialNotificationViewHolder.getLeftButton().setVisibility(8);
        socialNotificationViewHolder.getRightButton().setVisibility(8);
    }
    
    public final void sendNotificationToStatusbar(final SocialNotificationSummary socialNotificationSummary, final SocialNotificationsListSummary socialNotificationsListSummary, final ImageLoader imageLoader, final MessageData messageData, final Context context) {
        final ImageLoader.ImageLoaderListener imageLoaderListener = new ImageLoader.ImageLoaderListener() {
            @Override
            public void onErrorResponse(final String s) {
                if (Log.isLoggable(SocialNotification.TAG, 6)) {
                    Log.e(SocialNotification.TAG, "Failed to download: " + s);
                }
            }
            
            @Override
            public void onResponse(final Bitmap bitmap, final String s) {
                if (bitmap != null) {
                    imageLoader.getImg(socialNotificationSummary.getHorizontalBoxart(), IClientLogging.AssetType.boxArt, 0, 0, (ImageLoader.ImageLoaderListener)new onBoxArtFetched(bitmap, context, socialNotificationSummary, socialNotificationsListSummary, messageData));
                }
            }
        };
        final String id = socialNotificationSummary.getId();
        if (SocialNotification.mLastSocialNotificationIdSentToStatusBar != null && SocialNotification.mLastSocialNotificationIdSentToStatusBar.equals(id)) {
            Log.i(SocialNotification.TAG, "Notification with such id was already shown - skipping...");
            return;
        }
        if (id.equals(PreferenceUtils.getStringPref(context, "notification_id_deleted_from_statusbar", "-1"))) {
            Log.i(SocialNotification.TAG, "Notification with such id was swiped out by user - skipping...");
            return;
        }
        imageLoader.getImg(socialNotificationSummary.getFriendProfile().getImageUrl(), IClientLogging.AssetType.profileAvatar, 0, 0, (ImageLoader.ImageLoaderListener)imageLoaderListener);
    }
    
    class onBoxArtFetched implements ImageLoaderListener
    {
        private Bitmap mSocialProfile;
        final /* synthetic */ Context val$context;
        final /* synthetic */ SocialNotificationsListSummary val$listSummary;
        final /* synthetic */ MessageData val$msg;
        final /* synthetic */ SocialNotificationSummary val$notificationSummary;
        
        public onBoxArtFetched(final Bitmap mSocialProfile, final Context val$context, final SocialNotificationSummary val$notificationSummary, final SocialNotificationsListSummary val$listSummary, final Bitmap val$msg) {
            this.val$context = val$context;
            this.val$notificationSummary = val$notificationSummary;
            this.val$listSummary = val$listSummary;
            this.val$msg = (MessageData)val$msg;
            this.mSocialProfile = mSocialProfile;
        }
        
        @Override
        public void onErrorResponse(final String s) {
            if (Log.isLoggable(SocialNotification.TAG, 6)) {
                Log.e(SocialNotification.TAG, "Failed to download: " + s);
            }
        }
        
        @Override
        public void onResponse(final Bitmap bitmap, final String s) {
            if (bitmap != null) {
                final int color = this.val$context.getResources().getColor(2131296356);
                final NotificationCompat.BigPictureStyle bigPicture = new NotificationCompat.BigPictureStyle().bigPicture(bitmap);
                final NotificationCompat.Builder setColor = new NotificationCompat.Builder(this.val$context).setSmallIcon(2130837768).setLargeIcon(this.mSocialProfile).setContentTitle(this.val$notificationSummary.getFriendProfile().getFullName()).setStyle(bigPicture).setColor(color);
                setColor.setGroup("social_notifications");
                setColor.setGroupSummary(true);
                setColor.setAutoCancel(true);
                SocialNotification.this.addNotificationText(setColor, bigPicture, this.val$notificationSummary, this.val$context);
                SocialNotification.this.addNotificationActions(setColor, this.val$notificationSummary, this.val$listSummary, this.val$msg, this.val$context);
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
}
