// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.social.notifications.types;

import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import android.text.format.DateUtils;
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
import com.netflix.mediaclient.ui.social.notifications.SocialNotificationViewHolder;
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
    
    public static final int getLayoutResourceId() {
        return 2130903201;
    }
    
    public static final SocialNotificationViewHolder getViewHolder(final View view, final SocialNotificationSummary$NotificationTypes socialNotificationSummary$NotificationTypes) {
        return new SocialNotificationViewHolder((AdvancedImageView)view.findViewById(2131165654), (AdvancedImageView)view.findViewById(2131165688), (TextView)view.findViewById(2131165685), (TextView)view.findViewById(2131165686), (TextView)view.findViewById(2131165687), (TextView)view.findViewById(2131165690), (Button)view.findViewById(2131165691), (Button)view.findViewById(2131165692), view.findViewById(2131165689), view.findViewById(2131165684), (AdvancedImageView)view.findViewById(2131165683));
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
        socialNotificationViewHolder.getNSAPlayButton().setVisibility(8);
        socialNotificationViewHolder.getNSAArtImage().setVisibility(8);
        socialNotificationViewHolder.getMiddleTextView().setText(text);
        socialNotificationViewHolder.getMiddleTextView().setSingleLine(false);
        socialNotificationViewHolder.getMiddleTextView().setGravity(17);
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
    
    public TextView getAddToMyListButton(final SocialNotificationViewHolder socialNotificationViewHolder) {
        return null;
    }
    
    public abstract SocialNotificationSummary$NotificationTypes getNotificationType();
    
    public View getPlayMovieButton(final SocialNotificationViewHolder socialNotificationViewHolder) {
        return (View)socialNotificationViewHolder.getMovieArtImage();
    }
    
    public View getSayThanksButton(final SocialNotificationViewHolder socialNotificationViewHolder) {
        return null;
    }
    
    public void initView(final View view, final SocialNotificationViewHolder socialNotificationViewHolder, final SocialNotificationSummary socialNotificationSummary, final Context context) {
        int backgroundResource;
        if (socialNotificationSummary.getWasRead()) {
            backgroundResource = 2130837872;
        }
        else {
            backgroundResource = 2131296425;
        }
        view.setBackgroundResource(backgroundResource);
        socialNotificationViewHolder.getFriendImage().setVisibility(0);
        NetflixActivity.getImageLoader(context).showImg(socialNotificationViewHolder.getFriendImage(), socialNotificationSummary.getFriendProfile().getBigImageUrl(), IClientLogging$AssetType.profileAvatar, socialNotificationSummary.getFriendProfile().getFullName(), true, true);
        socialNotificationViewHolder.getNSAArtImage().setVisibility(8);
        socialNotificationViewHolder.getMovieArtImage().setVisibility(0);
        NetflixActivity.getImageLoader(context).showImg(socialNotificationViewHolder.getMovieArtImage(), socialNotificationSummary.getVideo().getBoxshotUrl(), IClientLogging$AssetType.boxArt, socialNotificationSummary.getVideo().getTitle(), true, true);
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
        socialNotificationViewHolder.getNSAPlayButton().setVisibility(8);
        socialNotificationViewHolder.getPlayButton().setVisibility(0);
        socialNotificationViewHolder.getLeftButton().setVisibility(8);
        socialNotificationViewHolder.getRightButton().setVisibility(8);
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
