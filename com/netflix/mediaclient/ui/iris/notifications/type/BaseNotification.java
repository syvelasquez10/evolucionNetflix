// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iris.notifications.type;

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
import com.netflix.mediaclient.ui.iris.notifications.NotificationsActivity;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.model.leafs.social.IrisNotificationsListSummary;
import com.netflix.model.leafs.social.IrisNotificationSummary;
import android.support.v4.app.NotificationCompat$Builder;
import android.widget.Button;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.ui.iris.notifications.NotificationViewHolder;
import com.netflix.model.leafs.social.IrisNotificationSummary$NotificationTypes;
import android.view.View;

public abstract class BaseNotification
{
    private static final String NOTIFICATIONS_GROUP = "iris_notifications";
    public static final String TAG;
    private static String mLastSocialNotificationIdSentToStatusBar;
    
    static {
        TAG = BaseNotification.class.getSimpleName();
    }
    
    public static final NotificationViewHolder getViewHolder(final View view, final IrisNotificationSummary$NotificationTypes irisNotificationSummary$NotificationTypes) {
        return new NotificationViewHolder((AdvancedImageView)view.findViewById(2131755909), (AdvancedImageView)view.findViewById(2131755486), (TextView)view.findViewById(2131755485), (TextView)view.findViewById(2131755483), (TextView)view.findViewById(2131755484), (TextView)view.findViewById(2131755911), (Button)view.findViewById(2131755912), (Button)view.findViewById(2131755913), view.findViewById(2131755910), view.findViewById(2131755908), (AdvancedImageView)view.findViewById(2131755482), view.findViewById(2131755481));
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
    
    protected void addNotificationActions(final NotificationCompat$Builder notificationCompat$Builder, final IrisNotificationSummary irisNotificationSummary, final IrisNotificationsListSummary irisNotificationsListSummary, final MessageData messageData, final Context context) {
        if (Log.isLoggable()) {
            Log.d(BaseNotification.TAG, "SocialNotification::addNotificationActions " + messageData);
        }
        final Intent intent = NotificationsActivity.getIntent(messageData);
        NotificationUtils.addNotificationSourceToIntent(intent);
        notificationCompat$Builder.setContentIntent(PendingIntent.getBroadcast(context.getApplicationContext(), 3, intent, 134217728));
    }
    
    protected abstract void addNotificationText(final NotificationCompat$Builder p0, final NotificationCompat$BigPictureStyle p1, final IrisNotificationSummary p2, final Context p3);
    
    public TextView getAddToMyListButton(final NotificationViewHolder notificationViewHolder) {
        return null;
    }
    
    public abstract IrisNotificationSummary$NotificationTypes getNotificationType();
    
    public View getPlayMovieButton(final NotificationViewHolder notificationViewHolder) {
        return (View)notificationViewHolder.getMovieArtImage();
    }
    
    public View getSayThanksButton(final NotificationViewHolder notificationViewHolder) {
        return null;
    }
    
    public void initView(final NotificationViewHolder notificationViewHolder, final IrisNotificationSummary irisNotificationSummary, final Context context) {
        if (notificationViewHolder.getUnreadIndicator() != null) {
            final View unreadIndicator = notificationViewHolder.getUnreadIndicator();
            int visibility;
            if (irisNotificationSummary.getWasRead()) {
                visibility = 4;
            }
            else {
                visibility = 0;
            }
            unreadIndicator.setVisibility(visibility);
        }
        if (notificationViewHolder.getFriendImage() != null && irisNotificationSummary.getFriendProfile() != null) {
            notificationViewHolder.getFriendImage().setVisibility(0);
            NetflixActivity.getImageLoader(context).showImg(notificationViewHolder.getFriendImage(), irisNotificationSummary.getFriendProfile().getBigImageUrl(), IClientLogging$AssetType.profileAvatar, irisNotificationSummary.getFriendProfile().getFullName(), ImageLoader$StaticImgConfig.DARK, true);
        }
        notificationViewHolder.getNSAArtImage().setVisibility(8);
        notificationViewHolder.getMovieArtImage().setVisibility(0);
        NetflixActivity.getImageLoader(context).showImg(notificationViewHolder.getMovieArtImage(), irisNotificationSummary.getTVCardUrl(), IClientLogging$AssetType.boxArt, irisNotificationSummary.getVideoTitle(), ImageLoader$StaticImgConfig.DARK, true);
        notificationViewHolder.getTopTextView().setVisibility(0);
        if (irisNotificationSummary.getFriendProfile() != null) {
            notificationViewHolder.getTopTextView().setText((CharSequence)irisNotificationSummary.getFriendProfile().getFullName());
        }
        notificationViewHolder.getMiddleTextView().setGravity(8388611);
        if (notificationViewHolder.getBottomTextView() != null) {
            if (irisNotificationSummary.getMessageString() == null || irisNotificationSummary.getMessageString().length() == 0) {
                notificationViewHolder.getBottomTextView().setVisibility(8);
            }
            else {
                notificationViewHolder.getBottomTextView().setVisibility(0);
                notificationViewHolder.getBottomTextView().setText((CharSequence)String.format("\"%s\"", irisNotificationSummary.getMessageString()));
            }
        }
        if (notificationViewHolder.getTimeStampView() != null) {
            notificationViewHolder.getTimeStampView().setVisibility(0);
            notificationViewHolder.getTimeStampView().setText(DateUtils.getRelativeTimeSpanString(context, irisNotificationSummary.getTimestamp()));
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
    
    public final void sendNotificationToStatusbar(final IrisNotificationSummary irisNotificationSummary, final IrisNotificationsListSummary irisNotificationsListSummary, final ImageLoader imageLoader, final MessageData messageData, final Context context) {
        final BaseNotification$1 baseNotification$1 = new BaseNotification$1(this, imageLoader, irisNotificationSummary, messageData, irisNotificationsListSummary, context);
        final String id = irisNotificationSummary.getId();
        if (BaseNotification.mLastSocialNotificationIdSentToStatusBar != null && BaseNotification.mLastSocialNotificationIdSentToStatusBar.equals(id)) {
            Log.i(BaseNotification.TAG, "Notification with such id was already shown - skipping...");
            return;
        }
        if (id.equals(PreferenceUtils.getStringPref(context, "notification_id_deleted_from_statusbar", "-1"))) {
            Log.i(BaseNotification.TAG, "Notification with such id was swiped out by user - skipping...");
            return;
        }
        String s;
        if (StringUtils.isEmpty(s = irisNotificationSummary.getFriendProfile().getImageUrl())) {
            s = irisNotificationSummary.getFriendProfile().getBigImageUrl();
        }
        imageLoader.getImg(s, IClientLogging$AssetType.profileAvatar, 0, 0, (ImageLoader$ImageLoaderListener)baseNotification$1);
    }
    
    public boolean supportsStatusBar() {
        return true;
    }
}
