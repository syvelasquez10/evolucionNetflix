// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.social.notifications.types;

import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import android.text.format.DateUtils;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.support.v4.app.NotificationCompat$BigPictureStyle;
import android.content.Intent;
import android.app.PendingIntent;
import com.netflix.mediaclient.util.NotificationUtils;
import com.netflix.mediaclient.ui.social.notifications.SocialNotificationsActivity;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.model.leafs.social.SocialNotificationsListSummary;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import android.support.v4.app.NotificationCompat$Builder;
import android.widget.Button;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.ui.social.notifications.NotificationViewHolder;
import com.netflix.model.leafs.social.SocialNotificationSummary$NotificationTypes;
import android.view.View;

public abstract class SocialNotification
{
    private static final String SOCIAL_NOTIFICATIONS_GROUP = "social_notifications";
    public static final String TAG;
    private static String mLastSocialNotificationIdSentToStatusBar;
    
    static {
        TAG = SocialNotification.class.getSimpleName();
    }
    
    public static final NotificationViewHolder getViewHolder(final View view, final SocialNotificationSummary$NotificationTypes socialNotificationSummary$NotificationTypes) {
        return new NotificationViewHolder((AdvancedImageView)view.findViewById(2131427791), (AdvancedImageView)view.findViewById(2131427575), (TextView)view.findViewById(2131427574), (TextView)view.findViewById(2131427572), (TextView)view.findViewById(2131427573), (TextView)view.findViewById(2131427822), (Button)view.findViewById(2131427823), (Button)view.findViewById(2131427824), view.findViewById(2131427821), view.findViewById(2131427820), (AdvancedImageView)view.findViewById(2131427571));
    }
    
    public static void showSingleLineText(final NotificationViewHolder notificationViewHolder, final int text) {
        notificationViewHolder.getMovieArtImage().setVisibility(8);
        notificationViewHolder.getTopTextView().setVisibility(8);
        if (notificationViewHolder.getFriendImage() != null) {
            notificationViewHolder.getFriendImage().setVisibility(8);
        }
        if (notificationViewHolder.getBottomTextView() != null) {
            notificationViewHolder.getBottomTextView().setVisibility(8);
        }
        if (notificationViewHolder.getTimeStampView() != null) {
            notificationViewHolder.getTimeStampView().setVisibility(8);
        }
        if (notificationViewHolder.getLeftButton() != null) {
            notificationViewHolder.getLeftButton().setVisibility(8);
        }
        if (notificationViewHolder.getRightButton() != null) {
            notificationViewHolder.getRightButton().setVisibility(8);
        }
        if (notificationViewHolder.getNSAPlayButton() != null) {
            notificationViewHolder.getNSAPlayButton().setVisibility(8);
        }
        if (notificationViewHolder.getPlayButton() != null) {
            notificationViewHolder.getPlayButton().setVisibility(8);
        }
        notificationViewHolder.getNSAArtImage().setVisibility(8);
        notificationViewHolder.getMiddleTextView().setText(text);
        notificationViewHolder.getMiddleTextView().setSingleLine(false);
        notificationViewHolder.getMiddleTextView().setGravity(17);
    }
    
    protected void addNotificationActions(final NotificationCompat$Builder notificationCompat$Builder, final SocialNotificationSummary socialNotificationSummary, final SocialNotificationsListSummary socialNotificationsListSummary, final MessageData messageData, final Context context) {
        if (Log.isLoggable()) {
            Log.d(SocialNotification.TAG, "SocialNotification::addNotificationActions " + messageData);
        }
        final Intent intent = SocialNotificationsActivity.getIntent(messageData);
        NotificationUtils.addNotificationSourceToIntent(intent);
        notificationCompat$Builder.setContentIntent(PendingIntent.getBroadcast(context.getApplicationContext(), 3, intent, 134217728));
    }
    
    protected abstract void addNotificationText(final NotificationCompat$Builder p0, final NotificationCompat$BigPictureStyle p1, final SocialNotificationSummary p2, final Context p3);
    
    public TextView getAddToMyListButton(final NotificationViewHolder notificationViewHolder) {
        return null;
    }
    
    public abstract SocialNotificationSummary$NotificationTypes getNotificationType();
    
    public View getPlayMovieButton(final NotificationViewHolder notificationViewHolder) {
        return (View)notificationViewHolder.getMovieArtImage();
    }
    
    public View getSayThanksButton(final NotificationViewHolder notificationViewHolder) {
        return null;
    }
    
    public void initView(final View view, final NotificationViewHolder notificationViewHolder, final SocialNotificationSummary socialNotificationSummary, final Context context) {
        int backgroundResource;
        if (socialNotificationSummary.getWasRead()) {
            backgroundResource = 2130837888;
        }
        else {
            backgroundResource = 2131230883;
        }
        view.setBackgroundResource(backgroundResource);
        if (notificationViewHolder.getFriendImage() != null) {
            notificationViewHolder.getFriendImage().setVisibility(0);
            NetflixActivity.getImageLoader(context).showImg(notificationViewHolder.getFriendImage(), socialNotificationSummary.getFriendProfile().getBigImageUrl(), IClientLogging$AssetType.profileAvatar, socialNotificationSummary.getFriendProfile().getFullName(), ImageLoader$StaticImgConfig.DARK, true);
        }
        notificationViewHolder.getNSAArtImage().setVisibility(8);
        notificationViewHolder.getMovieArtImage().setVisibility(0);
        NetflixActivity.getImageLoader(context).showImg(notificationViewHolder.getMovieArtImage(), socialNotificationSummary.getVideo().getBoxshotUrl(), IClientLogging$AssetType.boxArt, socialNotificationSummary.getVideo().getTitle(), ImageLoader$StaticImgConfig.DARK, true);
        notificationViewHolder.getTopTextView().setVisibility(0);
        notificationViewHolder.getTopTextView().setText((CharSequence)socialNotificationSummary.getFriendProfile().getFullName());
        notificationViewHolder.getMiddleTextView().setGravity(3);
        if (notificationViewHolder.getBottomTextView() != null) {
            if (socialNotificationSummary.getMessageString() == null || socialNotificationSummary.getMessageString().length() == 0) {
                notificationViewHolder.getBottomTextView().setVisibility(8);
            }
            else {
                notificationViewHolder.getBottomTextView().setVisibility(0);
                notificationViewHolder.getBottomTextView().setText((CharSequence)String.format("\"%s\"", socialNotificationSummary.getMessageString()));
            }
        }
        if (notificationViewHolder.getTimeStampView() != null) {
            notificationViewHolder.getTimeStampView().setVisibility(0);
            notificationViewHolder.getTimeStampView().setText(DateUtils.getRelativeTimeSpanString(context, socialNotificationSummary.getTimestamp()));
        }
        if (notificationViewHolder.getNSAPlayButton() != null) {
            notificationViewHolder.getNSAPlayButton().setVisibility(8);
        }
        if (notificationViewHolder.getPlayButton() != null) {
            notificationViewHolder.getPlayButton().setVisibility(0);
        }
        if (notificationViewHolder.getLeftButton() != null) {
            notificationViewHolder.getLeftButton().setVisibility(8);
        }
        if (notificationViewHolder.getRightButton() != null) {
            notificationViewHolder.getRightButton().setVisibility(8);
        }
    }
    
    public final void sendNotificationToStatusbar(final SocialNotificationSummary socialNotificationSummary, final SocialNotificationsListSummary socialNotificationsListSummary, final ImageLoader imageLoader, final MessageData messageData, final Context context) {
        final SocialNotification$1 socialNotification$1 = new SocialNotification$1(this, imageLoader, socialNotificationSummary, messageData, socialNotificationsListSummary, context);
        final String id = socialNotificationSummary.getId();
        if (SocialNotification.mLastSocialNotificationIdSentToStatusBar != null && SocialNotification.mLastSocialNotificationIdSentToStatusBar.equals(id)) {
            Log.i(SocialNotification.TAG, "Notification with such id was already shown - skipping...");
            return;
        }
        if (id.equals(PreferenceUtils.getStringPref(context, "notification_id_deleted_from_statusbar", "-1"))) {
            Log.i(SocialNotification.TAG, "Notification with such id was swiped out by user - skipping...");
            return;
        }
        String s;
        if (StringUtils.isEmpty(s = socialNotificationSummary.getFriendProfile().getImageUrl())) {
            s = socialNotificationSummary.getFriendProfile().getBigImageUrl();
        }
        imageLoader.getImg(s, IClientLogging$AssetType.profileAvatar, 0, 0, socialNotification$1);
    }
    
    public boolean supportsStatusBar() {
        return true;
    }
}
